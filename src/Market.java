import java.util.Random;
import java.util.Scanner;

public class Market extends BuildingNotLevelUp{

    @Override
    protected void Do(Wallet wallet){
        Random random = new Random();
        int rateStone=random.nextInt(15)+1;
        int rateTree=random.nextInt(15)+1;
        int rateBetween=random.nextInt(2)+1;
        Rate(rateStone,rateTree,rateBetween);
        wallet.balance();
        System.out.print("Наши услуги: \n 1 - Камень -> Монеты\n 2 - Дерево -> Монеты\n 3 - Камень -> Дерево\n 4 - Дерево -> Камень\n Ваш выбор - ");
        Scanner scanner=new Scanner(System.in);
        int choice = scanner.nextInt();
        System.out.print("Количество - ");
        int kol = scanner.nextInt();
        switch (choice)
        {
            case 1:
            {
                if (kol<=wallet.getTree()){
                    wallet.setMoney(wallet.getMoney()+kol*rateTree);
                    System.out.println("Вы обменяли "+kol+" дерева на "+rateTree*kol +" монет");
                }
                else
                    System.out.println("Обмен невозможен");
                break;
            }
            case 2:
            {
                if (kol<=wallet.getStone()){
                    wallet.setMoney(wallet.getMoney()+kol*rateStone);
                    System.out.println("Вы обменяли "+kol+" камней на "+rateStone*kol +" монет");
                }
                else
                    System.out.println("Обмен невозможен");
                break;
            }
            case 3:
            {
                if (kol<=wallet.getStone()){
                    wallet.setTree(wallet.getTree()+kol*rateBetween);
                    System.out.println("Вы обменяли "+kol+" камней на "+rateBetween*kol +" дерево");
                }
                else
                    System.out.println("Обмен невозможен");
                break;
            }
            case 4:
            {
                if (kol<=wallet.getTree()){
                    wallet.setStone(wallet.getStone()+kol*rateBetween);
                    System.out.println("Вы обменяли "+kol+" дерево на "+rateBetween*kol +" камней");
                }
                else
                    System.out.println("Обмен невозможен");
                break;
            }
        }
    }

    public Market(String name, int costInStoneBuy, int costInTreeBuy, Wallet wallet) {
        super(name, costInStoneBuy, costInTreeBuy, wallet);
        property.add("Обмен ресурсов");
    }
    private void Rate(int stoneRate, int treezRate, int betweenRate)
    {
        System.out.print("\n\nКурс на данный момент времени\n Камень - "+stoneRate+" монет\n Дерево - "+treezRate+" монет\n Дерево - "+betweenRate+" мкамней");
    }


}
