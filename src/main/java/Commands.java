import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Commands{
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
