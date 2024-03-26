public class Horseman extends Unit{
    public Horseman(String name, String number, int health, int attack, int rangeAttack, int defense, int movement, int cost) {
        super(name, number, health, attack, rangeAttack, defense, movement, cost);
    }

    public FootSoldier clonee()
    {
        FootSoldier t=new FootSoldier(getName(),getNumber(),getHealth(),getAttack(),getRangeAttack(),getDefense(),getMovement(),getCost());
        return t;
    }


    @Override
    protected Double getFine(String symbol) {
        if (symbolArr.contains(symbol) || numberArr.contains(symbol))
            return Double.MAX_VALUE;
        switch (symbol){
            case "#":
                return 2.2;
            case "@":
                return 1.2;
            case "!":
                return 1.5;
            default:
                return 0.0;
        }
    }
}
