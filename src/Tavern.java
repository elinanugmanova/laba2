import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Tavern extends Building implements Serializable {
    @Override

    protected void Characteristic(Wallet wallet,ArrayList<Unit> myUnit){
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
    public Tavern(Wallet wallet, ArrayList<Unit> myUnit, String name, int costInStoneBuy, int costInTreeBuy, int costInStoneLevel, int costInTreeLevel) {
        super(wallet, myUnit, name, costInStoneBuy, costInTreeBuy, costInStoneLevel, costInTreeLevel);
        property.add("Повыш-е перемещ-я");

    }

    public void visit(Wallet wallet,ArrayList<Unit> myUnit)
    {
        wallet.balance();
        System.out.print("Вы можете обменять шерсть на варежки, тогда здоровье всех ваших юнитов " +
                "увеличится на 2 и уменьшится штраф на болото\n Это стоит 10 шерсти. Будете обменивать?\n 1 - Да\n 2 - Нет\n" +
                " Ваш выбор - ");
        Scanner scanner =new Scanner(System.in);
        int choice=scanner.nextInt();
        if (choice==1)
        {
            if (wallet.getWool()>=10)
            {
                for (int i=0;i<myUnit.size();i++)
                {
                    myUnit.get(i).setHealth(myUnit.get(i).getHealth()+2);
                    myUnit.get(i).decrementFinesSwam(0.5);
                }
                wallet.setWool(wallet.getWool()-10);
                System.out.println("Здоровье всех ваших юнитов увеличилось на 2. " +
                        "Штрафы на перемещение по болотам всех ваших юнитов уменьшились на 0.5");
            }

            else
                System.out.println("Недостаточно шерсти");
        }
        wallet.balance();
    }

}

