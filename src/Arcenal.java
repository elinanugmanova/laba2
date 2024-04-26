import java.io.Serializable;
import java.util.ArrayList;

public class Arcenal extends Building implements Serializable {
    @Override
    protected void Characteristic(Wallet wallet, ArrayList<Unit> myUnit){
        for (int i=0;i<myUnit.size();i++)
            myUnit.get(i).setDefense(myUnit.get(i).getDefense()+1);
        System.out.println("Защита всех ваших юнитов увеличилось на 1");
    }

    public Arcenal(Wallet wallet, ArrayList<Unit> myUnit, String name, int costInStoneBuy, int costInTreeBuy, int costInStoneLevel, int costInTreeLevel) {
        super(wallet, myUnit, name, costInStoneBuy, costInTreeBuy, costInStoneLevel, costInTreeLevel);
        property.add("Повышение защиты");
    }
}
