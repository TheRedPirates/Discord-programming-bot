import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Commands{
    public static String coinflipOppTag;
    public static String coinflipSndTag;
    public static String russianOppTag;
    public static String russianSndTag;
    public static boolean russianAccepted;
    public static int bulletInChamber;
    public static int coinflipCount = 0;
    public static void coinflipStart(MessageReceivedEvent event){
        coinflipOppTag = event.getMessage().getMentionedUsers().get(0).getAsMention();
        coinflipSndTag = event.getAuthor().getAsMention();
        event.getChannel().sendMessage("The user: "+coinflipSndTag+" has challenged user:"+coinflipOppTag+ " for a coinflip game, to accept write 'accept'").queue();
    }
    public static void coinflipGame(MessageReceivedEvent event) {
        if(Math.random()>0.5) 
            event.getChannel().sendMessage(coinflipOppTag +" Is the winner!!!!!").queue();
        else
            event.getChannel().sendMessage(coinflipSndTag +" Is the winner!!!!!").queue();
        
    }
    public static void coinflipDecline(MessageReceivedEvent event) {
        event.getChannel().sendMessage(coinflipOppTag+" has declined the coinflip invitation").queue();
        coinflipOppTag = "";
        coinflipSndTag = "";
    }
    public static void russianRoulleteStart(MessageReceivedEvent event){
        russianOppTag = event.getMessage().getMentionedUsers().get(0).getAsMention();
        russianSndTag = event.getAuthor().getAsMention();
        event.getChannel().sendMessage("The user: "+russianSndTag+" has challanged the user:"+russianOppTag+ " for a russian roullete game, to accept write 'accept'").queue();
    }
    public static void russianRoulleteAccept(MessageReceivedEvent event){
        russianAccepted = true;
        event.getChannel().sendMessage("You accepted the invitation successfully").queue();
    }
    public static void russianRoulleteSpin(MessageReceivedEvent event){
        if(russianAccepted == false){
            event.getChannel().sendMessage("There are no active games").queue();
            return;
        }
        if(bulletInChamber != 0){
            event.getChannel().sendMessage("You already spun the chamber").queue();
            return;
        }
        bulletInChamber = (int) (Math.random() * 5 + 1);
        event.getChannel().sendMessage("You spinned the gun successfully").queue();
    }
    public static void russianRoulleteShot(MessageReceivedEvent event){
        if(russianAccepted == false){
            event.getChannel().sendMessage("There are no active games").queue();
            return;
        }
        if(bulletInChamber == 0){
            if(event.getAuthor().getAsMention().equals(russianSndTag)){
                event.getChannel().sendMessage(event.getAuthor().getAsMention() + " Just shot himself, " + russianOppTag+" Is the winner!").queue();
                russianAccepted = false;
                return;
            }
            else{
                event.getChannel().sendMessage(event.getAuthor().getAsMention() + " Just shot himself, " + russianSndTag+" Is the winner!").queue();
                russianAccepted = false;
                return;
            }
        }
        event.getChannel().sendMessage("You tried to shot but there was no bullet in the chamber, try again").queue();
        bulletInChamber--;
    }
    public static void fileUpload(MessageReceivedEvent event){
        Message.Attachment attachment =  event.getMessage().getAttachments().get(0);
        event.getMessage().delete().queue();
        if(attachment.getFileName().endsWith("java")){
            attachment.downloadToFile("./downloads/" + attachment.getFileName());
            event.getChannel().sendMessage("File succsefully uploaded to the pirate net").queue();
        }else
            event.getChannel().sendMessage("The only accepted file format is java files").queue();

        
    }
    public static void manualLink(MessageReceivedEvent event){
        event.getChannel().sendMessage("https://firstfrc.blob.core.windows.net/frc2020/Manual/2020FRCGameSeasonManual.pdf").queue();
    }
    public static void Help(MessageReceivedEvent event){
        event.getChannel().sendMessage("*help - shows this page. \n *coinflip [@user] - Creates a Coinflip game with a specific user. \n *roullete [Playstyle - [1-18] [19-36] [red] [black] [0]] [Bet] \n *upload [File_attachment] - Uploads a file to the pirate net. \n *manual - Sends the FRC manual link.").queue();
    }
	public static void syntaxFor(MessageReceivedEvent event) {
        event.getChannel().sendMessage("```java\n for(int i = 10; i>0 ; i++){ \n\n\n //Runs 10 time \n\n\n } \n ```").queue();
    }
    public static void syntaxWhile(MessageReceivedEvent event){
        event.getChannel().sendMessage("```java\n while(expression){ \n\n\n //Runs until expression is false \n\n\n } \n ```").queue();
    }
    public static void syntaxIf(MessageReceivedEvent event){
        event.getChannel().sendMessage("```java\n if(expression){ \n\n\n //Activates only if expression is true \n\n\n } \n ```").queue();
    }
    public static void syntaxSwitch(MessageReceivedEvent event){
        event.getChannel().sendMessage("```java\n int n;\n switch(n){ \n    case x: \n\n break; \n    case y: \n\n break; \n } \n ```").queue();
    }
    public static void syntaxFunction(MessageReceivedEvent event){
        event.getChannel().sendMessage("```java\n public static void methodName() { \n\n\n //code to run \n\n\n } \n ```").queue();
    }
    public static void syntaxClass(MessageReceivedEvent event){
        event.getChannel().sendMessage("```java\n public class className{ \n\n\n\n } \n ```").queue();
    }
	public static void Roullete(MessageReceivedEvent event, String cmd) {
        String message[] = cmd.split(" ");
        int win = (int) Math.random() * 31 + 1;
        int bet = Integer.parseInt(message[2]);
        if(message[1].equals("1-18")){
            event.getChannel().sendMessage("The winning number is: "+win).queue();
            if(win >= 1 && win <= 18)
                event.getChannel().sendMessage("You won: "+bet*2).queue();
            else
                event.getChannel().sendMessage("You lost: "+bet).queue(); 
        }
        else if(message[1].equals("19-36")){
            event.getChannel().sendMessage("The winning number is: "+win).queue();
            if(win >= 19 && win <= 36)
                event.getChannel().sendMessage("You won: "+bet*2).queue();
            else
                event.getChannel().sendMessage("You lost: "+bet).queue(); 
        }
        else if(message[1].equals("red")){
            event.getChannel().sendMessage("The winning number is: "+win).queue();
            if(win >= 1 && win <= 18)
                event.getChannel().sendMessage("You won: "+bet*2).queue();
            else
                event.getChannel().sendMessage("You lost: "+bet).queue(); 
        }
        else if(message[1].equals("black")){
            event.getChannel().sendMessage("The winning number is: "+win).queue();
            if(win >= 19 && win <= 36)
                event.getChannel().sendMessage("You won: "+bet*2).queue();
            else
                event.getChannel().sendMessage("You lost: "+bet).queue(); 
        }
        else if(message[1].equals("0")){
            event.getChannel().sendMessage("The winning number is: "+win).queue();
            if(win == 0)
                event.getChannel().sendMessage("You won: "+bet*8).queue();
            else
                event.getChannel().sendMessage("You lost: "+bet).queue(); 
        }
        
	}
	
}
