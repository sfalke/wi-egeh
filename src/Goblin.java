import java.awt.Point;
public class Goblin extends Monster {

    private boolean isAlive;

    public Goblin(){
        this.isAlive = true;
        this.setName("Monsieur Goblin");
        this.setAvatar("👹");
    }

    public void dies(){
        this.isAlive = false;
        this.setAvatar("💀");
    }

    public boolean isAlive(){
        return this.isAlive;
    }

    public int attack(){
        // do something
        return 1;
    }



}
