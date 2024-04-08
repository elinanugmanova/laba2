import java.util.ArrayList;
import java.util.Scanner;

public class Tavern extends Building{
    @Override

    protected void Characteristic(ArrayList<Unit> myUnit){
        System.out.print("Какую характеристику вы хотите изменить?\n 1 - перемещение\n 2 - штраф\n Ваш выбор - ");
        Scanner scanner=new Scanner(System.in);
        int choise=scanner.nextInt();
        switch (choise){
            case 1:
            {
                for (int i=0;i<myUnit.size();i++)
                    myUnit.get(i).setMovement(myUnit.get(i).getMovement()+0.5);
                System.out.println("Перемещение всех ваших юнитов увеличилось на 0.5");
                break;
            }
            case 2:
            {
                for (int i=0;i<myUnit.size();i++)
                    myUnit.get(i).decrementFines(0.5);
                System.out.println("Штрафы на перемещение всех ваших юнитов уменьшились на 0.5");
            }

        }
    }
    public Tavern(ArrayList<Unit> myUnit, String name, int costInStoneBuy, int costInTreeBuy, int costInStoneLevel, int costInTreeLevel) {
        super(myUnit, name, costInStoneBuy, costInTreeBuy, costInStoneLevel, costInTreeLevel);
        property.add("Повыш-е перемещ-я");

    }
}

