public class Craft extends BuildingNotLevelUp{
    private boolean isHasTakenMoney;
    @Override
    protected void Do(Wallet wallet){
        wallet.setMoney(wallet.getMoney()+10);
        System.out.println("Вы получили 10 монет за жильцов");
        isHasTakenMoney=true;

    }

    public boolean isHasTakenMoney() {
        return isHasTakenMoney;
    }

    public void setHasTakenMoney(boolean hasTakenMoney) {
        isHasTakenMoney = hasTakenMoney;
    }


    public Craft(String name, int costInStoneBuy, int costInTreeBuy, Wallet wallet) {
        super(name, costInStoneBuy, costInTreeBuy, wallet);
        property.add("Получ-е прибыли");
    }

}
