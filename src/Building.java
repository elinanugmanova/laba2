import java.lang.reflect.Array;
import java.util.ArrayList;

abstract public class Building {
    protected int level=0;
    protected String name;
    protected int costInStoneBuy;
    protected int costInTreeBuy;
    protected int costInStoneLevel;
    protected int costInTreeLevel;
    public ArrayList<String> property=new ArrayList<>();


    protected abstract void Characteristic(ArrayList<Unit> myUnit);
    public void levelUp(ArrayList<Unit> myUnit)
    {
        Characteristic(myUnit);
        level+=1;
        System.out.println("Уровень здания - "+level);
    }
    public Building(ArrayList<Unit> myUnit, String name, int costInStoneBuy, int costInTreeBuy, int costInStoneLevel, int costInTreeLevel) {
        this.costInStoneBuy=costInStoneBuy;
        this.costInTreeBuy=costInTreeBuy;
        this.costInStoneLevel=costInStoneLevel;
        this.costInTreeLevel=costInTreeLevel;
        this.name=name;
        property.add(name);
        property.add(Integer.toString(costInStoneBuy));
        property.add(Integer.toString(costInTreeBuy));
        property.add("Нельзя");
        property.add("Нельзя");
        property.add("Нельзя");
    }

    public int getCostInStoneBuy() {
        return costInStoneBuy;
    }

    public int getCostInTreeBuy() {
        return costInTreeBuy;
    }

    public int getCostInTreeLevel() {
        return costInTreeLevel;
    }

    public int getCostInStoneLevel() {
        return costInStoneLevel;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }
}
