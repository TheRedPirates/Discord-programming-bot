package Games;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class russianRoulleteGame {
    public gameMember GameMembers[];
    public int aliveCount;
    public int Queue;
    public int bulletInChamber = (int) (Math.random() * 5 + 1);
	public russianRoulleteGame(MessageReceivedEvent event) {
        java.util.List<Member> russianRoulleteMembers = event.getMessage().getMentionedMembers();
        this.GameMembers = new gameMember[event.getMessage().getMentionedMembers().size() + 1];
        this.GameMembers[0] = new gameMember(event.getAuthor().getAsMention());
        this.GameMembers[0].Accept();
        this.aliveCount = this.GameMembers.length;
        event.getChannel().sendMessage("The user: "+this.GameMembers[0].getMention()+" has challanged the users:").queue();
        for (int i = 1; i < this.GameMembers.length; i++) {
            this.GameMembers[i] = new gameMember(russianRoulleteMembers.get(i-1).getAsMention());
            event.getChannel().sendMessage(this.GameMembers[i].getMention()).queue();
        }
        event.getChannel().sendMessage("to accept write '*accept' ").queue();
    }
    public void Accept(MessageReceivedEvent event,int place){
        if(event.getAuthor().getAsMention().equals(this.GameMembers[place].getMention())) {
            if(this.GameMembers[place].hasAccepted() == false){
                event.getChannel().sendMessage(this.GameMembers[place].getMention() + " Has successfully accepted the game").queue();
                this.GameMembers[place].Accept();
            }else
                event.getChannel().sendMessage(this.GameMembers[place].getMention() + " You already accepted").queue();
            
            
       }
    }
    public void Spin(MessageReceivedEvent event,int place){
        if(this.GameMembers[place].isAlive() == false){
            event.getChannel().sendMessage(GameMembers[place].getMention()+" , You are dead!").queue();
            return;
        }
        for (int i = 0; i < GameMembers.length; i++) {
            if(GameMembers[i].hasAccepted() == false){
                event.getChannel().sendMessage("Not everyone accepted the invite").queue();
                return;
            }
        }
        if(GameMembers[Queue].getMention().equals(event.getAuthor().getAsMention())){
            bulletInChamber = (int) (Math.random() * 5 + 1);
            event.getChannel().sendMessage("You spinned the gun successfully").queue();
            return;
        }
        event.getChannel().sendMessage("Its not you turn, Its: "+GameMembers[Queue].getMention()+"'s turn").queue();
    }
    public void Shot(MessageReceivedEvent event, int place){
        if(Queue >= GameMembers.length)
                Queue = 0;
        if(GameMembers[place].isAlive() == false){
            event.getChannel().sendMessage(GameMembers[place].getMention()+" , You are dead!").queue();
            return;
        }
        for (int i = 0; i < GameMembers.length; i++) {
            if(GameMembers[i].hasAccepted() == false){
                event.getChannel().sendMessage("Not everyone accepted the invite").queue();
                return;
            }
        }
        if(GameMembers[Queue].getMention().equals(event.getAuthor().getAsMention())){
            if(bulletInChamber == 0){
                    event.getChannel().sendMessage(event.getAuthor().getAsMention() + " Just shot himself, ").queue();
                    bulletInChamber = (int) (Math.random() * 5 + 1);
                    aliveCount--;
                    GameMembers[place].Kill();
                    if(aliveCount != 1)
                        event.getChannel().sendMessage("Remaining players are: ").queue();
                    for (int i = 0; i < GameMembers.length; i++) {
                        if(GameMembers[i].isAlive() == true){
                            if(aliveCount == 1){
                                event.getChannel().sendMessage(GameMembers[i].getMention()+" Is the winner").queue();
                                for (int j = 0; j < GameMembers.length; j++) {
                                    GameMembers[i] = new gameMember("");
                                }
                                return;
                            }else
                                event.getChannel().sendMessage(GameMembers[i].getMention()).queue();  
                        }
                    }
            }else
                event.getChannel().sendMessage("You tried to shot but there was no bullet in the chamber, try again").queue();
            Queue++;
            while(GameMembers[Queue].isAlive() == false)
                    Queue++;
            bulletInChamber--;
            return;
        }
        event.getChannel().sendMessage("Its not you turn, Its: "+GameMembers[Queue].getMention()+"'s turn").queue();
    }
}