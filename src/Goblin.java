import java.awt.Point;
public class Goblin {

    private int level;
    private Point position;

    public Goblin(){
        this.level = 1;
        this.position = new Point(0,0);
    }

    public void setLevel(int level){ this.level = level; }
    public int getLevel(){ return this.level; }

    public void setPosition(Point position){ this.position.setLocation(position); }
    public Point getPosition(){ return this.position; }

    public void moveUp(){ this.position.y--; }
    public void moveDown(){ this.position.y++; }
    public void moveLeft(){ this.position.x--; }
    public void moveRight(){ this.position.x++; }

    public boolean currentPosition(Point p){ return this.position.equals(p); }


}
