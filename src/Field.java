import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Field extends TablePrint{
    private int size=0;
    private ArrayList<ArrayList<String>> basicField=new ArrayList<>();
//    private ArrayList<String> stepField;

    private ArrayList<String> symbol=new ArrayList<>(Arrays.asList("*","!","#","@"));
    public void addSymbol(String symbol)
    {
        this.symbol.add(symbol);
    }
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


    public Field(ArrayList<String> myUnits, ArrayList<String> botUnits) {

    }
    public void changeBasicField(int x,int y, String k){
        this.basicField.get(y).set(x,k);
    }

    public void fillBasicFieldFromFile(ArrayList<String> myUnits,ArrayList<String> botUnits, String map){

        File nfile =new File("C:\\Users\\Пользователь\\IdeaProjects\\untitled\\"+map);
        int sizee=0;
        try {
            BufferedReader bufferedReader=new BufferedReader(new FileReader ("C:\\Users\\Пользователь\\IdeaProjects\\untitled\\"+map));
            String line;
            while ((line=bufferedReader.readLine())!=null)
            {
                ArrayList<String> myList = new ArrayList<String>(Arrays.asList(line.split("")));
                basicField.add(myList);
                sizee++;
            }
            size=sizee;

            bufferedReader.close();
        }catch (IOException e)
        {
            System.out.println("Невозможно открыть файл");
        }
        for (int i=0;i<size;i++)
        {
            if (i==0)
                basicField.add(botUnits);
            else if (i==size-1) {
                basicField.add(myUnits);
            }
        }

        System.out.println(size);

        for (int i=0;i<myUnits.size();i++)
            basicField.get(size-1).set(i,myUnits.get(i));
        for (int i=0;i<botUnits.size();i++)
            basicField.get(0).set(i,botUnits.get(i));


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
                    randomList.add(symbol.get(random.nextInt(symbol.size())));
                basicField.add(randomList);
            }
        }

    }
    public int getxSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList<ArrayList<String>> getBasicField() {
        return basicField;
    }

}
