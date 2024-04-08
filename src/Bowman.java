public class Bowman extends Unit implements Squirrel {


    public void withSquirrel()
    {
        setAttack(getAttack()*2);
    }

    public Bowman(String name, String number, int health, int attack, int rangeAttack, int defense, double movement, int cost, double costOfSwamp, double costOfHill, double costOfTree) {
        super(name, number, health, attack, rangeAttack, defense, movement, cost, costOfSwamp, costOfHill, costOfTree);
    }
}
