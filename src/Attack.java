import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Attack extends Movement{
    private ArrayList<String> symbol=new ArrayList<>(Arrays.asList("a","b","c","d","e","f","g","h"));
    public boolean isAttack(Field field, int x2,int y2, Unit unit1){
        Double stepValue=getStepValue(x2,y2,unit1,field,false);
//        System.out.println(stepValue+"Дальность атаки");
        if (stepValue<=(double)unit1.getRangeAttack())
            return true;
        else
            return false;
    }

    public void attackAction(Wallet wallet, Field field, int x2, int y2, Unit unit1, Unit unit2, ArrayList<Unit> botPlayers, ArrayList<Unit> myPlayers)
    {

        if (unit2.Evasion()==0)
        {
            System.out.println("Воин "+unit1.getNumber()+" аттаковал "+field.getBasicField().get(y2).get(x2));
            unit2.getDefence1(unit1.getAttack());
            if (unit2.getHealth()<=0) {
                System.out.println("Игрок "+unit2.getNumber()+" погиб");
                if (symbol.contains(unit2.getNumber()))
                {
                    for (int i=0;i<botPlayers.size();i++){
                        if (botPlayers.get(i).getNumber()==field.getBasicField().get(y2).get(x2))
                            botPlayers.remove(i);
                    }
                    System.out.println("Вы получили 10 дерево и 10 монет за гибель воина противника");
                    wallet.setTree(wallet.getTree()+10);
                    wallet.setStone(wallet.getStone()+10);
                    wallet.balance();

                }
                else
                {
                    for (int i=0;i<myPlayers.size();i++){
                        if (myPlayers.get(i).getNumber()==field.getBasicField().get(y2).get(x2))
                            myPlayers.remove(i);
                    }
                }
                field.changeBasicField(x2,y2,"*");
            }

        }
        else
            System.out.println("Воин "+ unit2.getNumber() +" уклонился от урона");

    }


}
