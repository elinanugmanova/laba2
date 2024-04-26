import javax.sound.midi.Soundbank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Academy extends BuildingNotLevelUp implements Serializable {

    private ArrayList<Unit> newUnits = new ArrayList<>();
    public int indexOfNewUnit = 0;
    private boolean isThereNewUnit = false;

    public boolean isThereNewUnit() {
        return isThereNewUnit;
    }

    @Override
    protected void Do(Wallet wallet) {
        System.out.print("Вам доступна возможность создать собственного воина\n Введите характеристики юнита\n Имя - ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.print("Здоровье - ");
        int health = scanner.nextInt();
        System.out.print("Аттака - ");
        int attack = scanner.nextInt();
        System.out.print("Дальность атаки - ");
        int rangeAttack = scanner.nextInt();
        System.out.print("Защита  - ");
        int defense = scanner.nextInt();
        System.out.print("Перемещение - ");
        double movement = scanner.nextDouble();
        System.out.print("Штраф на болото - ");
        double costOfSwamp = scanner.nextDouble();
        System.out.print("Штраф на холм - ");
        double costOfHill = scanner.nextDouble();
        System.out.print("Штраф на дерево - ");
        double costOfTree = scanner.nextDouble();

        int cost = Math.abs(health * 10 - attack * 2 + rangeAttack * 5 - defense ^ 2);
        System.out.println("Стоимость разработки такого воина - " + cost);
        if (wallet.getMoney() >= cost) {
            System.out.print("У вас достаточно средств для исследования\n Хотите потратить их?\n 1 - да\n 2 - нет\n Ваш выбор - ");
            int otvet = scanner.nextInt();
            switch (otvet) {
                case 1: {
                    GeneretedUnit newUnit = new GeneretedUnit(name, Integer.toString(indexOfNewUnit), health, attack,
                            rangeAttack, defense, movement, cost, costOfSwamp, costOfHill, costOfTree);
                    System.out.println("Воин " + name + " создан");
                    newUnits.add(newUnit);
                    isThereNewUnit = true;
                    wallet.setMoney(wallet.getMoney() - cost);
                    break;
                }
                case 2: {
                    System.out.println("Воин " + name + "не создан");
                    isThereNewUnit = false;
                    break;
                }
            }
        } else {
            isThereNewUnit = false;
            System.out.println("У вас недостаточно средств для создания юнита");
        }
    }

    public ArrayList<Unit> getNewUnits() {
        return newUnits;
    }

    public Academy(String name, int costInStoneBuy, int costInTreeBuy, Wallet wallet) {
        super(name, costInStoneBuy, costInTreeBuy, wallet);
        property.add("Создание юнитов");
    }
}
