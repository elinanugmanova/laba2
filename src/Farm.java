import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Farm extends Building implements Serializable {

    private int amountOfWool=0;
    private boolean isFeeded=false;
    private boolean wasFightOver=false;
    private int numberOfFigtsNotFeed=0;

    @Override
    protected void Characteristic(Wallet wallet, ArrayList<Unit> myUnit){

        amountOfWool+=5;
        System.out.println("Количество шерсти, получаемое от фермы, теперь равно "+(amountOfWool));
        toFeed(wallet);
    }

    public void visit(Wallet wallet)
    {
        toFeed(wallet);
    }

    private void toFeed(Wallet wallet){


        if (isFeeded==true && wasFightOver==true)
        {
            System.out.println("Поздравляю, вы получили "+amountOfWool+" шерсти");
            wallet.setWool(wallet.getWool()+amountOfWool);
            wallet.balance();
            isFeeded=false;
        }

        Scanner scanner=new Scanner(System.in);
        if (isFeeded==false)
        {

            System.out.print("Если сейчас покормить альпак, то после боя вы сможете получить "+amountOfWool+" шерсти\n " +
                    "Будете кормить альпак?\n 1 - да\n 2 - нет\n Ваш выбор - ");
            int choice=scanner.nextInt();
            if (choice==1)
            {
                System.out.println("Вы покормили альпак");
                isFeeded=true;
                wasFightOver=false;
                numberOfFigtsNotFeed=0;
            }
            else
                isFeeded=false;

        }
        if (isFeeded==true && isWasFightOver()==false)
            System.out.println("Альпаки накормлены, но битва еще не прошла, вы не можете получить шерсть");

    }

    public Farm(Wallet wallet, ArrayList<Unit> myUnit, String name, int costInStoneBuy, int costInTreeBuy,
                int costInStoneLevel, int costInTreeLevel) {
        super(wallet, myUnit, name, costInStoneBuy, costInTreeBuy, costInStoneLevel, costInTreeLevel);
        property.add("Получение шерсти");
        Scanner scanner=new Scanner(System.in);
    }

    public boolean isWasFightOver() {
        return wasFightOver;
    }

    public void setWasFightOver(boolean wasFightOver) {
        this.wasFightOver = wasFightOver;
    }

    public int getNumberOfFigtsNotFeed() {
        return numberOfFigtsNotFeed;
    }


    public void setNumberOfFigtsNotFeed(int numberOfFigtsNotFeed) {
        this.numberOfFigtsNotFeed = numberOfFigtsNotFeed;
    }

    public int getAmountOfWool() {
        return amountOfWool;
    }

    public void setAmountOfWool(int amountOfWool) {
        this.amountOfWool = amountOfWool;
    }
}
