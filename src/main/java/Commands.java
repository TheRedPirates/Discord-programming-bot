import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Commands{
    public static String coinflipOppTag;
    public static String coinflipSndTag;
    public static int coinflipCount = 0;
    public static void coinflipStart(MessageReceivedEvent event){
        coinflipOppTag = event.getMessage().getMentionedUsers().get(0).getAsMention();
        coinflipSndTag = event.getAuthor().getAsMention();
        event.getChannel().sendMessage("The user: "+coinflipSndTag+" has challenged user:"+coinflipOppTag+ " to accept write 'accept'").queue();
    }
    public static void coinflipGame(MessageReceivedEvent event) {
        if(Math.random()>0.5) 
            event.getChannel().sendMessage(coinflipOppTag +" Is the winner!!!!!").queue();
        else
            event.getChannel().sendMessage(coinflipSndTag +" Is the winner!!!!!").queue();
        
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
        event.getChannel().sendMessage("*help - shows this page. \n *coinflip [@user] - Creates a Coinflip game with a specific user. \n *upload [File_attachment] - Uploads a file to the pirate net. \n *manual - Sends the FRC manual link.").queue();
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
}
