import java.util.ArrayList;
import java.util.Arrays;

public class Movement {
    private ArrayList<String> symbol = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e", "1", "2", "3", "4", "5", "*", "!", "#", "@"));

    public void movementAction(Field field, int x2, int y2, Unit unit) {
        changeField(field, x2, y2, unit);
        unit.setxCoord(x2);
        System.out.println(unit.getxCoord());
        unit.setyCoord(y2);
        System.out.println(unit.getyCoord());
    }
    public boolean isMove(Field field, int x2, int y2, Unit unit){
        Double stepValue = getStepValue(x2, y2, unit,field, true);
        System.out.println(stepValue+"Дальность перемещения");
        if (isPermitted(x2, y2, field) && (double)unit.getMovement() >= stepValue)
            return true;
        else
            return false;
    }

    private void changeField(Field field, int x2, int y2, Unit unit) {
        field.changeBasicField(x2,y2,unit.getNumber());
        System.out.println(field.getBasicField().get(y2).get(x2));
        field.changeBasicField(unit.getxCoord(),unit.getyCoord(),"*");
        System.out.println(field.getBasicField().get(unit.getxCoord()).get(unit.getyCoord()));
    }

    public boolean isPermitted(int x, int y, Field field) {
        System.out.println(field.getBasicField().get(y).get(x));
        System.out.println(field.getBasicField().get(y).get(x).equals("*"));
        return field.getBasicField().get(y).get(x).equals("*") ;
    }

    protected Double getStepValue(int x2, int y2, Unit unit, Field field, boolean k) {
        int x1 = unit.getxCoord();
        int y1 = unit.getyCoord();
        int xDistance = Math.abs(x1 - x2) + 1;
        int yDistance = Math.abs(y1 - y2) + 1;
        int val;
        Double[][] newField = new Double[yDistance][xDistance];
        double costOfDiogonal = Math.sqrt(2.0);
        System.out.println(unit.getxCoord()+" x coord");
        System.out.println(unit.getyCoord()+" y coord");


        if (x1 <= x2 && y1 >= y2) { //right up
            for (int i = yDistance - 1; i >= 0; i--) {
                for (int t = 0; t <= xDistance - 1; t++) {
                    Double[] ways = new Double[3];
                    int ind = 0;
                    for (int z = 0; z < 3; z++)
                        ways[z] = Double.MAX_VALUE;
                    Double costOfCell = unit.getFine(field.getBasicField().get(Math.min(y1,y2) + i).get(Math.min(x1,x2) + t));
                    if (i==0 && t==xDistance-1 && costOfCell==Double.MAX_VALUE)
                        costOfCell=0.0;
                    if (i == yDistance - 1 && t == 0)
                    {
                        costOfCell=0.0;
                        ways[0] = 0.0;
                    }
                    else {
                        if (t - 1 >= 0) {
                            ways[ind] = newField[i][t - 1]+1;
                            double k1=ways[ind];
                            System.out.println(k1);
                            ind += 1;
                        }
                        if (i + 1 <= yDistance - 1 && t - 1 >= 0) {
                            ways[ind] = newField[i + 1][t - 1] + costOfDiogonal;
                            double k2=ways[ind];
                            System.out.println(k2);
                            ind += 1;
                        }
                        if (i + 1 <= yDistance - 1) {
                            ways[ind] = newField[i + 1][t]+1;
                            double k3=ways[ind];
                            System.out.println(k3);
                            ind += 1;
                        }
                    }
                    Arrays.sort(ways);
                    if (k==true){
                        System.out.println(costOfCell);
                        newField[i][t] = ways[0] + costOfCell;
                    }
                    else
                        newField[i][t] = ways[0];

                    double k4=newField[i][t];
                    System.out.println(k4 +" itog");
                }
            }
            System.out.println(newField[0][xDistance - 1]+" step");
            return newField[0][xDistance - 1];
        } else if (x1 <= x2 && y1 < y2) {//right down
            for (int i = 0; i <= yDistance - 1; i++) {
                for (int t = 0; t <= xDistance - 1; t++) {
                    Double[] ways = new Double[3];
                    int ind = 0;
                    for (int z = 0; z < 3; z++)
                        ways[z] = Double.MAX_VALUE;
                    Double costOfCell = unit.getFine(field.getBasicField().get(Math.min(y1,y2) + i).get(Math.min(x1,x2) + t));
                    if (i==yDistance-1 && t==xDistance-1 && costOfCell==Double.MAX_VALUE)
                        costOfCell=0.0;
                    if (i == 0 && t == 0)
                    {
                        costOfCell=0.0;
                        ways[0] = 0.0;
                    }
                    else {
                        if (i - 1 >= 0) {
                            ways[ind] = newField[i - 1][t]+1;
                            ind += 1;
                        }
                        if (i - 1 >= 0 && t - 1 >= 0) {
                            ways[ind] = newField[i - 1][t - 1] + costOfDiogonal;
                            ind += 1;
                        }
                        if (t - 1 >= 0) {
                            ways[ind] = newField[i][t - 1]+1;
                            ind += 1;
                        }
                    }
                    Arrays.sort(ways);
                    if (k==true)
                        newField[i][t] = ways[0] + costOfCell;
                    else
                        newField[i][t] = ways[0];
                    double k4=newField[i][t];
                    System.out.println(k4 +" itog");
                }
            }
            System.out.println(newField[yDistance - 1][xDistance - 1] + " step");
            return newField[yDistance - 1][xDistance - 1];
        }
        else if (x1 > x2 && y1 >= y2) {//left up
            for (int i = yDistance - 1; i >= 0; i--) {
                for (int t = xDistance - 1; t >= 0; t--) {
                    Double[] ways = new Double[3];
                    int ind = 0;
                    for (int z = 0; z < 3; z++)
                        ways[z] = Double.MAX_VALUE;
                    Arrays.toString(ways);
                    Double costOfCell = unit.getFine(field.getBasicField().get(Math.min(y1,y2) + i).get(Math.min(x1,x2) + t));
                    if (i==0 && t==0 && costOfCell==Double.MAX_VALUE)
                        costOfCell=0.0;
                    if (i == yDistance - 1 && t == xDistance - 1)
                    {
                        costOfCell=0.0;
                        ways[0] = 0.0;
                    }
                    else {
                        if (i + 1 <= yDistance - 1) {
                            ways[ind] = newField[i + 1][t]+1;
                            ind += 1;
                        }
                        if (i + 1 <= yDistance - 1 && t + 1 <= xDistance - 1) {
                            ways[ind] = newField[i + 1][t + 1] + costOfDiogonal;
                            ind += 1;
                        }
                        if (t + 1 <= xDistance - 1) {
                            ways[ind] = newField[i][t + 1]+1;
                            ind += 1;
                        }
                    }
                    Arrays.sort(ways);
                    if (k==true)
                        newField[i][t] = ways[0] + costOfCell;
                    else
                        newField[i][t] = ways[0];
                    double k4=newField[i][t];
                    System.out.println(k4 +" itog");
                }
            }
            System.out.println(newField[0][0] + " step");
            return newField[0][0];

        } else {//left down
            for (int i = 0; i <= yDistance - 1; i++) {
                for (int t = xDistance - 1; t >= 0; t--) {
                    Double costOfCell = unit.getFine(field.getBasicField().get(Math.min(y1,y2) + i).get(Math.min(x1,x2) + t));
                    Double[] ways = new Double[3];
                    int ind = 0;
                    for (int z = 0; z < 3; z++)
                        ways[z] = Double.MAX_VALUE;
                    Arrays.toString(ways);
                    if (i==yDistance-1 && t==0 && costOfCell==Double.MAX_VALUE)
                        costOfCell=0.0;
                    if (i == 0 && t == xDistance - 1)
                    {
                        costOfCell=0.0;
                        ways[0] = 0.0;
                    }
                    else {
                        if (i - 1 >= 0) {
                            ways[ind] = newField[i - 1][t]+1;
                            ind += 1;
                        }
                        if (i - 1 >= 0 && t + 1 <= xDistance - 1) {
                            ways[ind] = newField[i - 1][t + 1] + costOfDiogonal;
                            ind += 1;
                        }
                        if (t + 1 <= xDistance-1) {
                            ways[ind] = newField[i][t + 1]+1;
                            ind += 1;
                        }
                    }

                    Arrays.sort(ways);
                    if (k==true)
                        newField[i][t] = ways[0] + costOfCell;
                    else
                        newField[i][t] = ways[0];
                    double k4=newField[i][t];
                    System.out.println(k4 +" itog");
                }

            }
            System.out.println(newField[yDistance - 1][0] + " step");
            return newField[yDistance - 1][0];
        }

    }
}
