
public class Dragon extends Monster {

    Dragon(){
        this.setName("Drache");
    }
    public String toString(){
        return this.getName();
    }

    public int attack(){
        return 1;
    }

}
