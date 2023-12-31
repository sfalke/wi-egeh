import java.awt.Point;
public abstract class Monster {

    private int level;
    private Point position;
    private String name;
    private String avatar;

    public Monster(){
        this.level = 1;
        this.position = new Point(0,0);
        this.name = "";
        this.avatar = "M";
    }

    public void setLevel(int level){ this.level = level; }
    public int getLevel(){ return this.level; }

    public void setName(String name){ this.name = name; }
    public String getName(){ return this.name; }

    public void setPosition(Point position){ this.position.setLocation(position); }
    public Point getPosition(){ return this.position; }

    public void setAvatar(String avatar){ this.avatar = avatar; }
    public String getAvatar(){ return this.avatar; }


    public void moveUp(){ this.position.y--; }
    public void moveDown(){ this.position.y++; }
    public void moveLeft(){ this.position.x--; }
    public void moveRight(){ this.position.x++; }

    public boolean currentPosition(Point p){ return this.position.equals(p); }

    public abstract int attack();


}
