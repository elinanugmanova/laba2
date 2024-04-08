import com.sun.source.tree.BreakTree;

import java.beans.FeatureDescriptor;
import java.util.Arrays;

public class Alpaka {
    int xCoord;
    int yCoord;
    String numberUnitOnMe="-1";

    public Alpaka(int xCoord, int yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;

    }
    public void getOnAlpaka(Unit unit, Field field){
        field.changeBasicField(unit.getxCoord(),unit.getyCoord(),"*");
        field.changeBasicField(this.xCoord,this.yCoord,unit.getNumber()+"$");
        unit.setxCoord(this.xCoord);
        unit.setyCoord(this.yCoord);
        unit.setMovement(unit.getMovement()*2);
        unit.setHealth(unit.getHealth()+3);
        unit.getDefence1(5);
        unit.setEvasion(4);
        unit.setNumber(unit.getNumber()+"$");
        numberUnitOnMe=unit.getNumber();
        System.out.println("Игрок "+unit.getName()+" сел на альпаку, его характеристики изменились");

    }
    public void getOffAlpaka(Unit unit, Field field){
        if (unit.getyCoord()<field.getxSize()-1)
        {
            this.xCoord=unit.getxCoord();
            this.yCoord=unit.getyCoord()+1;
        }
        else {
            this.xCoord=unit.getxCoord();
            this.yCoord=unit.getyCoord()-1;
        }
        field.changeBasicField(unit.getxCoord(),unit.getyCoord(),(Character.toString(unit.getNumber().charAt(0))));
        field.changeBasicField(this.xCoord,this.yCoord,"$");
        unit.setMovement(unit.getMovement()/2);
        unit.setHealth(unit.getHealth()-3);
        unit.setDefense(unit.getDefense()+5);
        unit.setNumber(Character.toString(unit.getNumber().charAt(0)));
        numberUnitOnMe="-1";
        unit.setEvasion(2);
        System.out.println("Игрок "+unit.getNumber()+" слез с альпаки, его характеристики вернклись к изначальным");

    }
    public boolean isGetOffAlpaka(){
        if (!numberUnitOnMe.equals("-1"))
            return true;
        System.out.println("Невозможно слезть с альпаки, сделайте ход еще раз");
        return false;
    }


    public boolean isGetOnAlpaka(Unit unit, Field field)
    {
        if (numberUnitOnMe=="-1")
        {

            int xunit=unit.getxCoord();
            int yunit=unit.getyCoord();

            Integer[] mas=new Integer[4];
            mas[0]=((xCoord-1)>=0)?1:0;//left
            mas[1]=((xCoord+1)<field.getxSize())?1:0;//right
            mas[2]=((yCoord-1)>=0)?1:0;//up
            mas[3]=((yCoord+1)<field.getxSize())?1:0;//down
            int x1=xCoord-mas[0];
            int x2=xCoord+mas[1];
            int y1=yCoord-mas[3];
            int y2=yCoord+mas[2];
//            System.out.println(Arrays.toString(mas));
//            System.out.println(x1);
//            System.out.println(x2);
//            System.out.println(y1);
//            System.out.println(y2);
            for (int i=x1;i<=x2;i++)
            {
                for (int t=y1;t<=y2;t++)
                {
                    if (!(i==xCoord && t==yCoord))
                    {
                        if (unit.getxCoord()==i&&unit.getyCoord()==t)
                        {
                            return true;
                        }
                    }
                }
            }
            System.out.println("Невозможно сесть на альпаку, сделайте ход еще раз");
            return false;
        }
        System.out.println("Невозможно сесть на альпаку, сделайте ход еще раз");
        return false;
    }


    public int getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }
}
