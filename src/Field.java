import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Field extends TablePrint{
    private int size;
    private ArrayList<ArrayList<String>> basicField=new ArrayList<>();
//    private ArrayList<String> stepField;

    private ArrayList<String> symbol=new ArrayList<>(Arrays.asList("*","!","#","@"));
    public void addUnitOnField(Unit unit)
    {
        boolean k=false;
        for (int i=0;i<size;i++)
        {
            if (k==true)
                break;
            for (int t=0;t<size;t++)
            {
                if (basicField.get(i).get(t).equals("*"))
                {
                    basicField.get(i).set(t,unit.getNumber());
                    unit.setxCoord(t);
                    unit.setyCoord(i);
                    k=true;
                    break;
                }
            }
        }
    }


    public Field(int size, ArrayList<String> myUnits, ArrayList<String> botUnits) {
        this.size = size;
        fillBasicField(size, myUnits, botUnits);
    }
    public void changeBasicField(int x,int y, String k){
        this.basicField.get(y).set(x,k);
    }
    public void fillBasicField(int size, ArrayList<String> myUnits,ArrayList<String> botUnits){
        Random random=new Random();
        for (int i=myUnits.size();i<size;i++){
            botUnits.add("*");
            myUnits.add("*");
        }
        for (int i=0;i<size;i++)
        {
            if (i==0)
                basicField.add(botUnits);
            else if (i==size-1) {
                basicField.add(myUnits);
            } else
            {
                ArrayList<String> randomList=new ArrayList<>();
                for (int k=0;k<size;k++)
                    randomList.add(symbol.get(random.nextInt(4)));
                basicField.add(randomList);
            }
        }

    }
    public int getxSize() {
        return size;
    }

    public ArrayList<ArrayList<String>> getBasicField() {
        return basicField;
    }

}
