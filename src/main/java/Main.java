import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
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


        DatabaseAccess dataAccess = new DatabaseAccess();

    }

    @SubscribeEvent
    public void onMessageReceived(MessageReceivedEvent event)
    {
        String cmd = event.getMessage().getContentDisplay();
        String Author = event.getAuthor().getAsMention();
        if(!event.getAuthor().isBot() && cmd.startsWith(botPrefix + "help")){
            Commands.Help(event);
        }
        //Coinflip started
        else if (!event.getAuthor().isBot() && cmd.startsWith(botPrefix + "coinflip")){
            Commands.coinflipStart(event);
        }
        //Coinflip accepted
        else if (!event.getAuthor().isBot() && Author.equals(Commands.coinflipOppTag) && cmd.startsWith("accept")){
            Commands.coinflipGame(event);
        }
        else if (!event.getAuthor().isBot() && Author.equals(Commands.coinflipOppTag) && cmd.startsWith("decline")){
            Commands.coinflipDecline(event);
        }
        else if(!event.getAuthor().isBot() && cmd.startsWith(botPrefix + "russianRoullete")){
            Commands.russianRoulleteStart(event);
        }
        else if (!event.getAuthor().isBot() && Author.equals(Commands.russianOppTag) && cmd.startsWith("accept")){
            Commands.russianRoulleteAccept(event);
        }
        else if (!event.getAuthor().isBot() && Author.equals(Commands.russianOppTag) && cmd.startsWith("decline")){
            Commands.coinflipDecline(event);
        }
        else if(!event.getAuthor().isBot() && (Author.equals(Commands.russianOppTag) || Author.equals(Commands.russianOppTag)) && cmd.startsWith(botPrefix+"spin")){
            Commands.russianRoulleteSpin(event);
        }
        else if(!event.getAuthor().isBot() && (Author.equals(Commands.russianOppTag) || Author.equals(Commands.russianOppTag)) && cmd.startsWith(botPrefix+"shot")){
            Commands.russianRoulleteShot(event);
        }
        //File upload
        else if(!event.getAuthor().isBot() && cmd.startsWith(botPrefix + "upload")){
            Commands.fileUpload(event);
        }
        //Manual link
        else if(!event.getAuthor().isBot() && cmd.startsWith(botPrefix + "manual")){
            Commands.manualLink(event);
        }
        else if(!event.getAuthor().isBot() && cmd.startsWith(botPrefix + "syntax")){
            switch(cmd.split(" ")[1]){
                case "for":
                    Commands.syntaxFor(event);
                    break;
                case "while":
                    Commands.syntaxWhile(event);
                    break;
                case "if":
                    Commands.syntaxIf(event);
                    break;
                case "switch":
                    Commands.syntaxSwitch(event);
                    break;
                case "func":
                    Commands.syntaxFunction(event);
                    break;
                case "class":
                    Commands.syntaxClass(event);
            }
        }
        else if(!event.getAuthor().isBot() && cmd.startsWith(botPrefix + "roullete")){
            Commands.Roullete(event,cmd);
        }

    }
}
