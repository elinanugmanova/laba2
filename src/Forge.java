import java.io.Serializable;
import java.util.ArrayList;

public class Forge extends Building implements Serializable {
    @Override
    protected void Characteristic(Wallet wallet, ArrayList<Unit> myUnit){
        for (int i=0;i<myUnit.size();i++)
            myUnit.get(i).setAttack(myUnit.get(i).getAttack()+1);
        System.out.println("Аттака всех ваших юнитов увеличилось на 1");
    }

    public Forge(Wallet wallet, ArrayList<Unit> myUnit, String name, int costInStoneBuy, int costInTreeBuy, int costInStoneLevel, int costInTreeLevel) {
        super(wallet, myUnit, name, costInStoneBuy, costInTreeBuy, costInStoneLevel, costInTreeLevel);
        property.add("Повышение атаки");
    }
}
