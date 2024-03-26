import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Game extends TablePrint {
    public void battle(Bot bot, Field field, Movement movement, Attack attack) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Состояние вашей армии");
        printPlayers(field.getMyplayers());
        System.out.println("Состояние армии противника");
        printPlayers(field.getBotplayers());
        System.out.println("Поле битвы");
        field.printBasicField();
        System.out.print("Возможные действия:\n1 - перемещение\n2 - атака\n3 - пермещение и атака\n4 - пропуск хода\n");

        boolean k1=true;

        while (k1==true) {
            System.out.print("Мой выбор - ");
            int choise = scanner.nextInt();
            if (choise != 4) {
                System.out.print("Номер воина - ");
                String numberOfWarrior = Integer.toString(scanner.nextInt());
                int IndexOfwarIsAct=findUnit(numberOfWarrior, field.getMyplayers());
                System.out.println(field.getMyplayers().get(IndexOfwarIsAct).getxCoord() + "x coord");
                switch (choise) {
                    case 1: {
                        int x2, y2;
                        System.out.print("Координаты перемещения:\nx - ");
                        x2 = scanner.nextInt();
                        System.out.print("y - ");
                        y2 = scanner.nextInt();
                        if (movement.isMove(field, x2, y2, field.getMyplayers().get(IndexOfwarIsAct))) {
                            movement.movementAction(field, x2, y2, field.getMyplayers().get(IndexOfwarIsAct));
                            k1 = false;
                            break;
                        } else
                            System.out.println("Перемещение невозможно, сделайте ход еще раз");
                        break;

                    }
                    case 2: {
                        int x2, y2;
                        System.out.print("Координаты атаки:\nx - ");
                        x2 = scanner.nextInt();
                        System.out.print("y - ");
                        y2 = scanner.nextInt();
                        String symbolOfDamagingWarrior = field.getBasicField().get(y2).get(x2);
                        int IndexOfwarIsAttacted=findUnit(symbolOfDamagingWarrior, field.getBotplayers());
                        if (attack.isAttack(field, x2, y2, field.getMyplayers().get(IndexOfwarIsAct))) {
                            attack.attackAction(field, x2, y2, field.getMyplayers().get(IndexOfwarIsAct), field.getBotplayers().get(IndexOfwarIsAttacted));
                            k1 = false;
                            break;
                        } else
                            System.out.println("Атака невозможна, сделайте ход еще раз");
                        break;

                    }
                    case 3: {
                        int x2, y2;
                        System.out.print("Координаты перемещения:\nx - ");
                        x2 = scanner.nextInt();
                        System.out.print("y - ");
                        y2 = scanner.nextInt();
                        if (movement.isMove(field, x2, y2, field.getMyplayers().get(IndexOfwarIsAct)))
                            movement.movementAction(field, x2, y2, field.getMyplayers().get(IndexOfwarIsAct));
                        else
                        {
                            System.out.println("Перемещение невозможно, сделайте ход еще раз");
                            break;
                        }
                        int x3, y3;
                        System.out.print("Координаты атаки:\nx - ");
                        x3 = scanner.nextInt();
                        System.out.print("y - ");
                        y3 = scanner.nextInt();
                        String symbolOfDamagingWarrior = field.getBasicField().get(y3).get(x3);
                        int IndexOfwarIsAttacted=findUnit(symbolOfDamagingWarrior, field.getBotplayers());

                        if (attack.isAttack(field, x3, y3, field.getMyplayers().get(IndexOfwarIsAct))) {
                            attack.attackAction(field, x3, y3, field.getMyplayers().get(IndexOfwarIsAct), field.getBotplayers().get(IndexOfwarIsAttacted));
                            k1 = false;
                            break;
                        } else
                        {
                            System.out.println("Атака невозможна, сделайте ход еще раз");
                            break;
                        }
                    }
                }
            }
        }
        System.out.println("Состояние вашей армии");
        printPlayers(field.getMyplayers());
        System.out.println("Состояние армии противника");
        printPlayers(field.getBotplayers());
        System.out.println("Поле битвы");
        field.printBasicField();
        bot.botAction(field.getMyplayers(), field.getBotplayers(), field, movement, attack);
    }


    private int findUnit(String k, ArrayList<Unit> units) {
        for (int i = 0; i < units.size(); i++) {
            System.out.println(k);
            System.out.println(units.get(i).getNumber());
            System.out.println((units.get(i).getNumber().equals(k)));
            if (units.get(i).getNumber().equals(k))
                return i;
        }
        return 0;
    }

    public void start() {

        Random random = new Random();
        Bot bot=new Bot();
        Movement movement=new Movement();
        Attack attack=new Attack();
        int money = 50;
        int dificulty;
        int size;
        System.out.print("Добро пожаловать в NastyCity\nНа наш город произошло нападение, и только ты в силах спасти тысячи мирных жителей!\nУровни сложность игры:\nлегкий - 1\nсредний - 2\nсложный - 3\nМой уровень - ");
        Scanner scanner = new Scanner(System.in);
        dificulty = scanner.nextInt();
        switch (dificulty) {
            case 1:
                size = 3;
                break;
            case 2:
                size = 5;
                break;
            case 3:
                size = 7;
                break;
            default:
                size=3;
        }
        System.out.println("В твоем распоряжении " + money + " монет\nПоочередно выбери воинов в свою армию");
        ArrayList<Unit> shop = new ArrayList<>();
        FootSoldier p1 = new FootSoldier("Мечник", "1", 50, 5, 1, 8, 3, 10);
        FootSoldier p2 = new FootSoldier("Копьеносец", "2", 35, 3, 1, 4, 6, 15);
        FootSoldier p3 = new FootSoldier("Топорщик", "3", 45, 9, 1, 3, 4, 20);
        Bowman p4 = new Bowman("Лучник(дл. лук)", "4", 30, 6, 5, 8, 2, 15);
        Bowman p5 = new Bowman("Лучник(кор. лук)", "5", 25, 3, 3, 4, 4, 19);
        Bowman p6 = new Bowman("Арбалетчик", "6", 40, 7, 6, 3, 2, 23);
        Horseman p7 = new Horseman("Рыцарь", "7", 30, 5, 1, 3, 6, 20);
        Horseman p8 = new Horseman("Кирасир", "8", 50, 2, 1, 7, 5, 23);
        Horseman p9 = new Horseman("Конный лучник", "9", 25, 3, 3, 2, 5, 25);

        shop.add((Unit) p1);
        shop.add((Unit) p2);
        shop.add((Unit) p3);
        shop.add((Unit) p4);
        shop.add((Unit) p5);
        shop.add((Unit) p6);
        shop.add((Unit) p7);
        shop.add((Unit) p8);
        shop.add((Unit) p9);
        printPlayers(shop);
        ArrayList<Unit> myPlayers = new ArrayList<>();
        ArrayList<String> myNumberPlayers = new ArrayList<>();
        int index=0;
        while (true) {
            System.out.print("ВВедите номер воина - ");
            int t = scanner.nextInt();
            money -= shop.get(t - 1).getCost();
            if (money < 0) {
                System.out.println("Недостаточно средств, ваша армия сформирована");
                break;
            }
            generateUnit(shop,t,myPlayers,index);
            myNumberPlayers.add(myPlayers.get(index).getNumber());
            myPlayers.get(index).setxCoord(index);
            myPlayers.get(index).setyCoord(size-1);
            System.out.println("Воин " + myPlayers.get(index).getName() + " вступил в армию");
            index++;
        }
        ArrayList<Unit> botPlayers = new ArrayList<>();
        ArrayList<String> botSymbolPlayers = new ArrayList<>();
        int index2=0;
        ArrayList<String> symbol=new ArrayList<>(Arrays.asList("a","b","c","d","e","f","g","h"));

        for (int i = 0; i < myPlayers.size(); i++) {
            generateUnit(shop,random.nextInt(9)+1,botPlayers,i);
            botPlayers.get(i).setNumber(symbol.get(i));
            botSymbolPlayers.add(botPlayers.get(i).getNumber());
            botPlayers.get(i).setxCoord(index2);
            botPlayers.get(i).setyCoord(0);
            index2++;
        }

        Field field = new Field(size, myNumberPlayers, botSymbolPlayers, myPlayers, botPlayers);
        field.getMyplayers().get(0).printUnit();
        field.getMyplayers().get(1).printUnit();
        field.getBotplayers().get(0).printUnit();
        field.getBotplayers().get(1).printUnit();
        System.out.println("Битва началась");
        while (true) {
            if (field.getBotplayers().isEmpty()){
                System.out.println("Игра окончена\nВы одержали победу");
                break;
            }
            if (field.getMyplayers().isEmpty())
            {
                System.out.println("Игра окончена\nВы потерпели поражение");
                break;
            }
            battle(bot,field,movement,attack);
        }
    }
    private void generateUnit(ArrayList<Unit> shop, int t,ArrayList<Unit> myPlayers,int index){
        if (shop.get(t - 1) instanceof Bowman)
        {
            Bowman warrior =new Bowman(shop.get(t - 1).getName(),Integer.toString(index+1),shop.get(t - 1).getHealth(),shop.get(t - 1).getAttack(),shop.get(t - 1).getRangeAttack(),shop.get(t - 1).getDefense(),shop.get(t - 1).getMovement(),shop.get(t - 1).getCost());
            myPlayers.add(warrior);
        }
        else if (shop.get(t - 1) instanceof FootSoldier)
        {
            FootSoldier warrior =new FootSoldier(shop.get(t - 1).getName(),Integer.toString(index+1),shop.get(t - 1).getHealth(),shop.get(t - 1).getAttack(),shop.get(t - 1).getRangeAttack(),shop.get(t - 1).getDefense(),shop.get(t - 1).getMovement(),shop.get(t - 1).getCost());
            myPlayers.add(warrior);
        }
        else
        {
            Horseman warrior =new Horseman(shop.get(t - 1).getName(),Integer.toString(index+1),shop.get(t - 1).getHealth(),shop.get(t - 1).getAttack(),shop.get(t - 1).getRangeAttack(),shop.get(t - 1).getDefense(),shop.get(t - 1).getMovement(),shop.get(t - 1).getCost());
            myPlayers.add(warrior);
        }
    }


    public void printPlayers(ArrayList<Unit> unit) {
        ArrayList<String> header = new ArrayList<>();
        header.add("Название");
        header.add("Номер");
        header.add("Здоровье");
        header.add("Атака");
        header.add("Дальность Атаки");
        header.add("Защита");
        header.add("Перемещение");
        header.add("Стоимость");
        StringBuilder table = new StringBuilder();
        printHorizontalLine(table, 8);
        printRow(table, header);
        printHorizontalLine(table, 8);
        for (int i = 0; i < unit.size(); i++) {
            printRow(table, unit.get(i).getCharacter());
        }
        printHorizontalLine(table, 8);
        System.out.println(table.toString());
    }
}
