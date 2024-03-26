import java.util.ArrayList;
import java.util.Arrays;

public class Attack extends Movement{
    private ArrayList<String> symbol=new ArrayList<>(Arrays.asList("a","b","c","d","e","f","g","h"));
    public boolean isAttack(Field field, int x2,int y2, Unit unit1){
        Double stepValue=getStepValue(x2,y2,unit1,field,false);
        System.out.println(stepValue+"Дальность атаки");
        if (stepValue<=(double)unit1.getRangeAttack())
            return true;
        else
            return false;
    }

    public void attackAction(Field field, int x2,int y2, Unit unit1, Unit unit2)
    {
        int lastAttackPoint=unit1.getAttack();
        int damage=unit1.getAttack();
        int defense= unit2.getDefense();
        int health= unit2.getHealth();
        int bad;
        if (damage>defense)
        {
            bad=damage-defense;
            unit2.setDefense(0);
            if (health>bad)
            {
                unit2.setHealth(health-bad);
            }
            else {
                System.out.println("Игрок "+unit2.getNumber()+" погиб");
                if (symbol.contains(unit2.getNumber()))
                {
                    for (int i=0;i<field.getBotplayers().size();i++){
                        if (field.getBotplayers().get(i).getNumber()==field.getBasicField().get(y2).get(x2))
                            field.getBotplayers().remove(i);
                    }
                }
                else
                {
                    for (int i=0;i<field.getMyplayers().size();i++){
                        if (field.getMyplayers().get(i).getNumber()==field.getBasicField().get(y2).get(x2))
                            field.getMyplayers().remove(i);
                    }
                }
                field.changeBasicField(x2,y2,"*");
            }
        }
        else
        {
            unit2.setDefense(defense-damage);
        }
    }


}
