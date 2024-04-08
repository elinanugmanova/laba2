import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.SortedMap;

public class Bot {
    public void botAction(Wallet wallet, ArrayList<Unit> enemy, ArrayList<Unit> my, Field field, Movement movement, Attack attack){
        boolean k=false;
        for (int i=0;i<my.size();i++)
        {
            if (k==true)
                break;
            for (int t=0;t<enemy.size();t++)
            {
                int x2=enemy.get(t).getxCoord();
                int y2=enemy.get(t).getyCoord();
                if (attack.isAttack(field,x2,y2, my.get(i)))
                {
                    attack.attackAction(wallet, field,x2,y2,my.get(i),enemy.get(t),my,enemy);
                    k=true;
                    break;
                }
            }
        }

        for (int i=0;i<my.size();i++) {
            if (k == true)
                break;
            int size= field.getxSize();
            int xNow=my.get(i).getxCoord();
            int yNow=my.get(i).getyCoord();
            ArrayList<ArrayList<Integer>> variationOfMove=new ArrayList<>(4);
            for (int t=0;t<4;t++)
            {
                variationOfMove.add(findStepsBySides(xNow,yNow,t,size,my.get(i)));
               if (movement.isPermitted(variationOfMove.get(t).get(0),variationOfMove.get(t).get(1),field) && !(variationOfMove.get(t).get(0)==xNow && yNow==variationOfMove.get(t).get(1))) {
                    if (movement.isMove(field, variationOfMove.get(t).get(0), variationOfMove.get(t).get(1), my.get(i)))
                    {
                        movement.movementAction(field, variationOfMove.get(t).get(0), variationOfMove.get(t).get(1), my.get(i));
                        k = true;
                        break;
                    }
                }
            }
        }

    }
    private ArrayList<Integer> findStepsBySides(int x,int y,int koef, int size, Unit myUnit){
        int borderDist;
        ArrayList<Integer> res=new ArrayList<>();
        switch (koef){
            case 0:
                borderDist=findDist((int)myUnit.getMovement(),x);;//right
                res.add(x-borderDist);
                res.add(y);
                break;
            case 1:
                borderDist=findDist((int)myUnit.getMovement(),size-x-1);//left
                res.add(borderDist+x);
                res.add(y);
                break;
            case 2:
                borderDist=findDist((int)myUnit.getMovement(),size-y-1);//forward
                res.add(x);
                res.add(y+borderDist);
                break;
            case 3:
                borderDist=findDist((int) myUnit.getMovement(),y);//back
                res.add(x);
                res.add(y);
                break;

        }
        return res;
    }
    private int findDist(int range,int borderDist){
        if (borderDist>range)
            return range;
        else
            return borderDist;
    }




}
