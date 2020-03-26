package Games;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class coinflipCommands {
    public static gameMember[] gameMembers;
    public static void coinflipStart(MessageReceivedEvent event){
        gameMembers = new gameMember[2];
        gameMembers[0] = new gameMember(event.getAuthor().getAsMention());
        gameMembers[1] = new gameMember(event.getMessage().getMentionedUsers().get(0).getAsMention());
        event.getChannel().sendMessage("The user: "+gameMembers[0].getMention()+" has challenged user:"+gameMembers[1].getMention()+ " for a coinflip game, to accept write '*accept'").queue();
    }
    public static void coinflipGame(MessageReceivedEvent event) {
        if(Math.random()>0.5)
            event.getChannel().sendMessage(gameMembers[1].getMention() +" Is the winner!!!!!").queue();
        else
            event.getChannel().sendMessage(gameMembers[0].getMention() +" Is the winner!!!!!").queue();
        
    }
    public static void coinflipDecline(MessageReceivedEvent event) {
        event.getChannel().sendMessage(gameMembers[1].getMention()+" has declined the coinflip invitation, game is canceled").queue();
        gameMembers[0] = new gameMember("");
        gameMembers[1] = new gameMember("");
    }
}