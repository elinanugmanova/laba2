import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Field extends TablePrint{
    private int size;
    private ArrayList<ArrayList<String>> basicField=new ArrayList<>();
//    private ArrayList<String> stepField;
    private ArrayList<Unit> Myplayers=new ArrayList<>();
    private ArrayList<Unit> Botplayers=new ArrayList<>();

    private ArrayList<String> symbol=new ArrayList<>(Arrays.asList("*","!","#","@"));

    public Field(int size, ArrayList<String> myUnits, ArrayList<String> botUnits, ArrayList<Unit> myPlayers, ArrayList<Unit> botPlayers) {
        this.size = size;
        fillBasicField(size, myUnits, botUnits);
        this.Myplayers=myPlayers;
        this.Botplayers=botPlayers;
    }
    public void printBasicField(){
        StringBuilder table = new StringBuilder();
        ArrayList<String> numbers=new ArrayList<>();
        numbers.add("y/x");
        for (int i=0;i<size;i++)
            numbers.add(Integer.toString(i));
        printHorizontalLine(table, size+1);
        printRow(table,numbers);
        printHorizontalLine(table, size+1);
        for (int i=0;i<size;i++) {
            ArrayList<String> numbersAndField=new ArrayList<>();
            numbersAndField.add(Integer.toString(i));
            numbersAndField.addAll(basicField.get(i));
            printRow(table, numbersAndField);
            printHorizontalLine(table, size+1);
        }
        System.out.println(table.toString());
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

    public ArrayList<Unit> getMyplayers() {
        return Myplayers;
    }

    public ArrayList<Unit> getBotplayers() {
        return Botplayers;
    }

    public void setMyplayers(ArrayList<Unit> myplayers) {
        Myplayers = myplayers;
    }

    public void setBotplayers(ArrayList<Unit> botplayers) {
        Botplayers = botplayers;
    }
}
