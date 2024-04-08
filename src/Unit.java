import java.util.*;

public abstract class Unit implements Cloneable{
    private int health;
    private int attack;
    private int rangeAttack;
    private int defense;
    private double movement;
    private int cost;
    private int xCoord=-1;
    private int yCoord=-1;
    private String name;
    private String number;
    private ArrayList<String> character=new ArrayList<>(8);
    protected ArrayList<String> symbolArr=new ArrayList<>(Arrays.asList("a","b","c","d","e","f","g","h"));
    protected ArrayList<String> numberArr=new ArrayList<>(Arrays.asList("1","2","3","4","5","6","7","8"));
    private int evasionKoeff=2;
    private HashMap<String,Double> fines=new HashMap<>();
    public Double getFine(String symbol) {
        if (symbolArr.contains(symbol) || numberArr.contains(symbol))
            return Double.MAX_VALUE;
        if (Character.toString(symbol.charAt(0))=="$")
            return Double.MAX_VALUE;
        if (symbol=="$")
            return Double.MAX_VALUE;
        return fines.get(symbol);
    }

//    public void printFines(){
//        for (String key:fines.keySet())
//            System.out.println(key+" - "+fines.get(key));
//    }

    public void setFines(HashMap<String, Double> fines) {
        this.fines = fines;
    }

    public void decrementFines(double decrement)
    {
        for (String key: fines.keySet())
        {
            if ((fines.get(key)-decrement)<=0)
                fines.put(key,0.0);
            else
                fines.put(key,Math.ceil((fines.get(key)-decrement) * 100) / 100);
        }
    }

    public HashMap<String, Double> getFines() {
        return fines;
    }

    public int getEvasion() {
        return evasionKoeff;
    }

    public void setEvasion(int evasionKoeff) {
        this.evasionKoeff = evasionKoeff;
    }

    public int Evasion()
    {
        Random random1=new Random();
        return random1.nextInt(evasionKoeff);
    }


    public ArrayList<String> getCharacter() {
        return character;
    }

    public void getDefence1(int damage) {
        int bad;
        if (damage > defense) {
            bad = damage - defense;
            defense=0;
            health-=bad;
        }
        else
           defense-=damage;
        character.set(5, Integer.toString(defense));
        character.set(2, Integer.toString(health));
    }


    public void addFine(String s, double cost)
    {
        fines.put(s,cost);
    }
//    public void printFines


    public Unit(String name, String number, int health, int attack, int rangeAttack, int defense, double movement, int cost, double costOfSwamp, double costOfHill, double costOfTree) {
        this.defense=defense;
        this.attack = attack;
        this.health=health;
        this.cost=cost;
        this.movement=movement;
        this.rangeAttack=rangeAttack;
        this.name=name;
        this.number=number;
        fines.put("*", 0.0);
        fines.put("#", costOfSwamp);
        fines.put("@", costOfHill);
        fines.put("!", costOfTree);

        character.add(name);
        character.add(number);
        character.add(Integer.toString(health));
        character.add(Integer.toString(attack));
        character.add(Integer.toString(rangeAttack));
        character.add(Integer.toString(defense));
        character.add(Double.toString(movement));
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

    public double getMovement() {
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
        character.set(3,Integer.toString(attack));
    }

    public void setRangeAttack(int rangeAttack) {
        this.rangeAttack = rangeAttack;
    }

    public void setMovement(double movement) {
        this.movement = movement;
        character.set(6,Double.toString(movement));
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
