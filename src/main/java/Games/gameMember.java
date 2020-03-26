package Games;

public class gameMember {
    private String Mention;
    private boolean hasAccepted;
    private boolean isAlive;
    gameMember(String Mention){
        this.Mention = Mention;
        isAlive=true;
    }
    public String getMention(){
        return Mention;
    }
    public boolean isAlive(){
        return isAlive;
    }
    public boolean hasAccepted(){
        return hasAccepted;
    }
    public void Kill(){
        isAlive = false;
    }
	public void Accept() {
        hasAccepted = true;
	}
    
}