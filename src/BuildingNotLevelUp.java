import java.util.ArrayList;
import java.util.Arrays;

abstract public class BuildingNotLevelUp {

    protected String name;
    protected int costInStoneBuy;
    protected int costInTreeBuy;
    ArrayList<String> property=new ArrayList<>();

    protected abstract void Do(Wallet wallet);
    public BuildingNotLevelUp(String name, int costInStoneBuy, int costInTreeBuy, Wallet wallet) {
        this.costInStoneBuy=costInStoneBuy;
        this.costInTreeBuy=costInTreeBuy;
        this.name=name;
        property.add(name);
        property.add(Integer.toString(costInStoneBuy));
        property.add(Integer.toString(costInTreeBuy));
        property.add("Нельзя");
        property.add("Нельзя");
        property.add("Нельзя");
    }
    public void useBuilding(Wallet wallet)
    {
        Do(wallet);
    }

    public int getCostInStoneBuy() {
        return costInStoneBuy;
    }

    public int getCostInTreeBuy() {
        return costInTreeBuy;
    }


    public String getName() {
        return name;
    }
}
