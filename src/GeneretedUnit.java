import java.io.Serializable;

public class GeneretedUnit extends Unit implements Serializable {
    public GeneretedUnit(String name, String number, int health, int attack, int rangeAttack, int defense,
                         double movement, int cost, double costOfSwamp, double costOfHill, double costOfTree) {
        super(name, number, health, attack, rangeAttack, defense, movement, cost, costOfSwamp, costOfHill, costOfTree);
    }
}
