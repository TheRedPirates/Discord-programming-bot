import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Commands{
    public static String coinflipOppTag;
    public static String coinflipSndTag;
    public static String russianTag[];
    public static boolean russianRoulleteAccept[];
    public static boolean russianRoulleteAlive[];
    public static int aliveCount;
    public static int Queue = 0;
    public static int bulletInChamber = (int) (Math.random() * 5 + 1);
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
        russianRoulleteAlive = new boolean[event.getMessage().getMentionedUsers().size()+1];
        aliveCount = event.getMessage().getMentionedUsers().size()+1;
        for (int i = 0; i < russianRoulleteAlive.length; i++) {
            russianRoulleteAlive[i] = true;
        }
        russianRoulleteAccept = new boolean[event.getMessage().getMentionedUsers().size()];
        russianTag = new String[event.getMessage().getMentionedUsers().size()+1];
        russianTag[0] = event.getAuthor().getAsMention();
        event.getChannel().sendMessage("The user: "+russianTag[0]+" has challanged the users:").queue();
        for (int i = 1; i < russianTag.length; i++) {
            russianTag[i] = event.getMessage().getMentionedUsers().get(i-1).getAsMention();
            event.getChannel().sendMessage(russianTag[i]).queue();
        }
        event.getChannel().sendMessage("For a russian Roullete game, to accept write 'accept' ").queue();
    }
    public static void russianRoulleteAccept(MessageReceivedEvent event,int i){
           if(event.getAuthor().getAsMention().equals(russianTag[i + 1])){
                russianRoulleteAccept[i] = true;
                event.getChannel().sendMessage(russianTag[i + 1]+" Has successfully accepted the game").queue();
           }  
    }
    public static void russianRoulleteSpin(MessageReceivedEvent event, int place){
        if(russianRoulleteAlive[place] == false){
            event.getChannel().sendMessage(russianTag[place]+" , You are dead!").queue();
            return;
        }
        for (int i = 0; i < russianRoulleteAccept.length; i++) {
            if(russianRoulleteAccept[i] == false){
                event.getChannel().sendMessage("Not everyone accepted the invite").queue();
                return;
            }
        }
        if(russianTag[Queue].equals(event.getAuthor().getAsMention())){
            bulletInChamber = (int) (Math.random() * 5 + 1);
            event.getChannel().sendMessage("You spinned the gun successfully").queue();
            return;
        }
        event.getChannel().sendMessage("Its not you turn, Its: "+russianTag[Queue]+"'s turn").queue();
    }
    public static void russianRoulleteShot(MessageReceivedEvent event, int place){
        if(Queue >= aliveCount)
                Queue = 0;
        if(russianRoulleteAlive[place] == false){
            event.getChannel().sendMessage(russianTag[place]+" , You are dead!").queue();
            return;
        }
        for (int i = 0; i < russianRoulleteAccept.length; i++) {
            if(russianRoulleteAccept[i] == false){
                event.getChannel().sendMessage("Not everyone accepted the invite").queue();
                return;
            }
        }
        if(russianTag[Queue].equals(event.getAuthor().getAsMention())){
            if(bulletInChamber == 0){
                    event.getChannel().sendMessage(event.getAuthor().getAsMention() + " Just shot himself, ").queue();
                    bulletInChamber = (int) (Math.random() * 5 + 1);
                    aliveCount--;
                    for (int i = 0; i < russianTag.length; i++) {
                        if(russianTag[i].equals(event.getAuthor().getAsMention()))
                            russianRoulleteAlive[i] = false;
                    }
                    event.getChannel().sendMessage("Remaining players are: ").queue();
                     for (int i = 0; i < russianRoulleteAlive.length; i++) {
                        if(russianRoulleteAlive[i] == true){
                            event.getChannel().sendMessage(russianTag[i]).queue();
                            if(aliveCount == 1) {
                                event.getChannel().sendMessage(russianTag[i]+" Is the winner").queue();
                                for (int j = 0; j < russianTag.length; j++) {
                                    russianTag[i] = "";
                                }

                                for (int k = 0; k < russianRoulleteAccept.length; k++) {
                                    russianRoulleteAccept[i] = false;
                                }
                                return;
                            }
                        }
                     }
                    Queue++;
                    if(russianRoulleteAlive[Queue-1]==false)
                        Queue++;
                    return;
            }
            event.getChannel().sendMessage("You tried to shot but there was no bullet in the chamber, try again").queue();
            Queue++;
            if(russianRoulleteAlive[Queue-1]==false)
                Queue++;
            bulletInChamber--;
            return;
        }
        event.getChannel().sendMessage("Its not you turn, Its: "+russianTag[Queue]+"'s turn").queue();
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
