import java.util.ArrayList;

public class DoctorHouse extends Building{
    @Override
    protected void Characteristic(ArrayList<Unit> myUnit){
        for (int i=0;i<myUnit.size();i++)
            myUnit.get(i).setHealth(myUnit.get(i).getHealth()+1);
        System.out.println("Здоровье всех ваших юнитов увеличилось на 1");
    }

    public DoctorHouse(ArrayList<Unit> myUnit, String name, int costInStoneBuy, int costInTreeBuy, int costInStoneLevel, int costInTreeLevel) {
        super(myUnit, name, costInStoneBuy, costInTreeBuy, costInStoneLevel, costInTreeLevel);
        property.add("Повыш-е здоровья");

    }
}
