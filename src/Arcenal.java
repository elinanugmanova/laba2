import java.util.ArrayList;

public class Arcenal extends Building{
    @Override
    protected void Characteristic(ArrayList<Unit> myUnit){
        for (int i=0;i<myUnit.size();i++)
            myUnit.get(i).setDefense(myUnit.get(i).getDefense()+1);
        System.out.println("Защита всех ваших юнитов увеличилось на 1");
    }

    public Arcenal(ArrayList<Unit> myUnit, String name, int costInStoneBuy, int costInTreeBuy, int costInStoneLevel, int costInTreeLevel) {
        super(myUnit, name, costInStoneBuy, costInTreeBuy, costInStoneLevel, costInTreeLevel);
        property.add("Повышение защиты");
    }
}
