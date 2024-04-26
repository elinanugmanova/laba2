import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

abstract public class BuildingNotLevelUp implements Serializable {

    private final static String S="Нельзя";
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
        property.add(S);
        property.add(S);
        property.add(S);
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
