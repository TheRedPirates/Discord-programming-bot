import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.AnnotatedEventManager;
import net.dv8tion.jda.api.hooks.SubscribeEvent;
import javax.security.auth.login.LoginException;

public class Main {
    final char botPrefix = '*';

    public static void main(String[] args) throws LoginException {
        JDA client = JDABuilder.createDefault("NTgxODIwNzk0NzU0MTcwOTAw.XnU3WQ.d_IChqM9bJ780IUR5Rrd7pMS33I")
                .setEventManager(new AnnotatedEventManager())
                .addEventListeners(new Main())
                .build();
    }

    @SubscribeEvent
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if (!event.getAuthor().isBot() && event.getMessage().getContentDisplay().startsWith(botPrefix + "hi")) {
            MessageChannel channel = event.getChannel();
            channel.sendMessage("Hello!").queue();
        }
    }
}
