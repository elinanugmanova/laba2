import java.io.Serializable;

public class Wallet implements Serializable {
    private int stone;
    private int tree;
    private int money;
    private int numperOfCraft=0;
    private int wool;

    public int getWool() {
        return wool;
    }

    public void setWool(int wool) {
        this.wool = wool;
    }

    public int getNumperOfCraft() {
        return numperOfCraft;
    }

    public void setNumperOfCraft(int numperOfCraft) {
        this.numperOfCraft = numperOfCraft;
    }


    public Wallet(int money, int stone, int tree, int wool) {
        this.stone = stone;
        this.money=money;
        this.tree=tree;
        this.wool=wool;
    }

    public void balance(){
        System.out.println("\n\n\nВаш Баланс\n Камень - "+stone+" шт\n Дерево - "+tree+" шт\n Монеты - "+ money+"\n Шерсть - "+ wool);
    }

    public int getStone() {
        return stone;
    }

    public int getTree() {
        return tree;
    }

    public int getMoney() {
        return money;
    }

    public void setStone(int stone) {
        this.stone = stone;
    }

    public void setTree(int tree) {
        this.tree = tree;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
