import java.awt.Point;
public class Character {

    private String name;
    private String avatar;
    private Point position;
    private int level;
    private boolean hasTreasure;

    public Character(){
        this.name = "";
        this.avatar = "";
        this.position = new Point(0, 0);
        this.hasTreasure = false;
        this.level = 1;
    }

    public Character(String name, String avatar, Point position){
        this.name = name;
        this.avatar = avatar;
        this.position = new Point(position);
        this.hasTreasure = false;
        this.level = 1;
    }


    public void setName(String name){ this.name = name; }
    public String getName(){ return this.name; }

    public void setAvatar(String avatar){ this.avatar = avatar; }
    public String getAvatar(){ return this.avatar; }

    public void setPosition(Point position){ this.position.setLocation(position); }
    public Point getPosition(){ return this.position; }

    public void pickUpTreasure(){ this.hasTreasure = true; }
    public boolean hasTreasure(){ return this.hasTreasure; }

    public void levelUp(){ this.level++; }
    public int getLevel(){ return this.level; }


    public void move(Point p){ this.position.setLocation(p); }
    public void move(int x, int y){ this.position.move(x, y); }

    public Point move(String direction){
        switch(direction){
            case "w" -> this.position.y--;
            case "s" -> this.position.y++;
            case "a" -> this.position.x--;
            case "d" -> this.position.x++;
        }
        return this.position;
    }

    public boolean currentPosition(Point p){
        return this.position.equals(p);
    }

    public String toString(){
        return "Name: " + this.name +
                "\nAvatar: " + this.avatar +
                "\nSchatz gefunden? " + ((this.hasTreasure) ? "ja" : "nein") +
                "\nPosition: x=" + this.position.x + ", y=" + this.position.y;
    }







}
