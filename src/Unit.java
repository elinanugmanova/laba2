import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public abstract class Unit implements Cloneable{
    private int health;
    private int attack;
    private int rangeAttack;
    private int defense;
    private int movement;
    private int cost;
    private int xCoord=-1;
    private int yCoord=-1;
    private String name;
    private String number;
    private ArrayList<String> character=new ArrayList<>(8);
    protected abstract Double getFine(String symbol);
    protected ArrayList<String> symbolArr=new ArrayList<>(Arrays.asList("a","b","c","d","e","f","g","h"));
    protected ArrayList<String> numberArr=new ArrayList<>(Arrays.asList("1","2","3","4","5","6","7","8"));


    public ArrayList<String> getCharacter() {
        return character;
    }

    public Unit(String name, String number, int health, int attack, int rangeAttack, int defense, int movement, int cost) {
        this.defense=defense;
        this.attack = attack;
        this.health=health;
        this.cost=cost;
        this.movement=movement;
        this.rangeAttack=rangeAttack;
        this.name=name;
        this.number=number;
        character.add(name);
        character.add(number);
        character.add(Integer.toString(health));
        character.add(Integer.toString(attack));
        character.add(Integer.toString(rangeAttack));
        character.add(Integer.toString(defense));
        character.add(Integer.toString(movement));
        character.add(Integer.toString(cost));

    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
        character.set(1,number);
    }

    public int getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getHealth() {
        return health;
    }

    public int getRangeAttack() {
        return rangeAttack;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getMovement() {
        return movement;

    }

    public int getCost() {
        return cost;
    }

    public void setHealth(int health) {
        this.health = health;
        character.set(2,Integer.toString(health));
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setRangeAttack(int rangeAttack) {
        this.rangeAttack = rangeAttack;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }

    public void setDefense(int defense) {
        this.defense = defense;
        this.character.set(5,Integer.toString(defense));
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    public void printUnit(){
        System.out.println(name+" "+number+" "+xCoord+" "+yCoord);
    }

}
