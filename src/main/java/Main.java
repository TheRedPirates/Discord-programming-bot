import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.AnnotatedEventManager;
import net.dv8tion.jda.api.hooks.SubscribeEvent;

public class Main {
    public static final char botPrefix = '*';
    public static JDA client;
    public static void main(String[] args) throws LoginException {
        String tokenPartA = "NTgxODIwNz";
        String tokenPartB = "k0NzU0MTcwOTAw.XnU-x";
        String tokenPartC = "Q.uLgrpXG47kR7QOnStmmYZfj4jk8";

        client = JDABuilder.createDefault(tokenPartA + tokenPartB + tokenPartC)
                .setEventManager(new AnnotatedEventManager())
                .addEventListeners(new Main())
                .build();
        
        client.getPresence().setActivity(Activity.playing("with the pirates 🛠️☠️"));

        
    }

    @SubscribeEvent
    public void onMessageReceived(MessageReceivedEvent event)
    {
        System.out.println(event.getMessage().getContentDisplay());
        MessageChannel channel = event.getChannel();
        String[] args = event.getMessage().getContentRaw().split(" ");
        if (!event.getAuthor().isBot() && event.getMessage().getContentDisplay().startsWith(botPrefix + "hi")) { 
            channel.sendMessage("Hello!").queue();
        }
        else if (!event.getAuthor().isBot() && event.getMessage().getContentDisplay().startsWith(botPrefix + "coinflip")){
            Commands.coinflipStart(event,args);
        }
        else if (event.getAuthor().getAsTag().equals(Commands.coinflipOppTag) && args[0].equalsIgnoreCase("accept")){
            
            channel.sendMessage(Commands.coinflipGame(event,args) + " Is the winner!!!!!!").queue();
        }
    }
    
}
