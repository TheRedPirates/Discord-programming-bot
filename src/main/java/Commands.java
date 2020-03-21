import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Commands{
    public static String coinflipOppTag;
    public static String coinflipSndTag;
    public static String opp1;
    public static void coinflipStart(MessageReceivedEvent event,String[] args){
        coinflipOppTag = event.getMessage().getMentionedUsers().get(0).getAsTag();
        opp1 = event.getMessage().getMentionedUsers().get(0).getAsMention();
        coinflipSndTag = event.getAuthor().getAsMention();
        event.getChannel().sendMessage("The user: "+event.getAuthor().getAsMention()+" has challenged user:"+args[1]+ " to accept write 'accept'").queue();
    }
    public static String coinflipGame(MessageReceivedEvent event, String[] args){
        if(Math.random()>0.5) {
            System.out.println("win1");
            return opp1;
        }
        else {
            System.out.println("win2");
            return coinflipSndTag;
        }
    }
}
