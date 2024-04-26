import java.io.*;
import java.util.*;

public class Game extends TablePrint {

    private Map<String, Double> newFines = new HashMap<>();
    public void battle(Wallet wallet, ArrayList<Building> myBuildings, ArrayList<BuildingNotLevelUp> myBuildingsNotLEvelUp, ArrayList<Building> shopBuilding, ArrayList<BuildingNotLevelUp> shopBuildintNotLevel, Alpaka alpaka, Bot bot, Field field, Movement movement, Attack attack, ArrayList<Unit> botPlayers, ArrayList<Unit> myPlayers) {

        Scanner scanner = new Scanner(System.in);
        while (true) {


            wallet.balance();
            printBuildings(shopBuilding, shopBuildintNotLevel);
            System.out.print("Выберите одно из действий для постройки вашей деревни. Если не хотите ничего " +
                    "делать - введите 0\nВаш выбор - ");
            int choice1 = scanner.nextInt();
            if (choice1 != 0) {
                if (choice1 <= 5) {
                    if (shopBuilding.get(choice1 - 1).property.get(1).equals("Нельзя")) {
                        if (shopBuilding.get(choice1 - 1).property.get(3).equals("Нельзя") && shopBuilding.get(choice1 - 1).property.get(5).equals("Нельзя")) {
                            System.out.println("Действие недоступно");
                            continue;
                        }
                        if (choice1 == 2 || choice1 == 5) {
                            System.out.print(" 1 - обновить\n 2 - посетить\n Ваш выбор - ");
                            int choiceWhatToDo = scanner.nextInt();
                            if (choiceWhatToDo == 1) {
                                System.out.println("Вы прокачали " + shopBuilding.get(choice1 - 1).getName());
                                shopBuilding.get(choice1 - 1).levelUp(wallet, myPlayers);
                                wallet.setStone(wallet.getStone() - shopBuilding.get(choice1 - 1).getCostInStoneLevel());
                                wallet.setTree(wallet.getTree() - shopBuilding.get(choice1 - 1).getCostInTreeLevel());
                                printPlayers(myPlayers);
                                printFines(myPlayers);
                            } else {
                                if (choice1 == 5)
                                    ((Farm) (shopBuilding.get(choice1 - 1))).visit(wallet);
                                else
                                {
                                    ((Tavern) (shopBuilding.get(choice1 - 1))).visit(wallet, myPlayers);
                                    printPlayers(myPlayers);
                                    printFines(myPlayers);
                                }
                            }
                        }

                    } else {
                        myBuildings.add(shopBuilding.get(choice1 - 1));
                        System.out.println("Вы построили " + shopBuilding.get(choice1 - 1).getName());
                        shopBuilding.get(choice1 - 1).levelUp(wallet, myPlayers);
                        shopBuilding.get(choice1 - 1).property.set(1, "Нельзя");
                        shopBuilding.get(choice1 - 1).property.set(2, "Нельзя");
                        shopBuilding.get(choice1 - 1).property.set(3, Integer.toString(shopBuilding.get(choice1 - 1).getCostInStoneLevel()));
                        shopBuilding.get(choice1 - 1).property.set(4, Integer.toString(shopBuilding.get(choice1 - 1).getCostInTreeLevel()));
                        wallet.setStone(wallet.getStone() - shopBuilding.get(choice1 - 1).getCostInStoneBuy());
                        wallet.setTree(wallet.getTree() - shopBuilding.get(choice1 - 1).getCostInTreeBuy());
                        printPlayers(myPlayers);
                        printFines(myPlayers);
                        if (choice1 == 2 || choice1 == 5) {
                            shopBuilding.get(choice1 - 1).property.set(5, "Можно");
                        }
                    }
                } else {
                    if (shopBuildintNotLevel.get(choice1 - 6).property.get(1).equals("Нельзя")) {
                        if (shopBuildintNotLevel.get(choice1 - 6).property.get(3).equals("Нельзя") && shopBuildintNotLevel.get(choice1 - 5).property.get(5).equals("Нельзя")) {
                            System.out.println("Действие недоступно");
                            continue;
                        }
                        shopBuildintNotLevel.get(choice1 - 6).useBuilding(wallet);
                        System.out.println("Вы посетили " + shopBuildintNotLevel.get(choice1 - 6).getName());
                        if (shopBuildintNotLevel.get(choice1 - 6) instanceof Craft)
                            shopBuildintNotLevel.get(choice1 - 6).property.set(5, "Нельзя");
                    } else {
                        myBuildingsNotLEvelUp.add(shopBuildintNotLevel.get(choice1 - 6));
                        System.out.println("Вы построили " + shopBuildintNotLevel.get(choice1 - 6).getName());
                        shopBuildintNotLevel.get(choice1 - 6).useBuilding(wallet);
                        shopBuildintNotLevel.get(choice1 - 6).property.set(1, "Нельзя");
                        shopBuildintNotLevel.get(choice1 - 6).property.set(2, "Нельзя");
                        shopBuildintNotLevel.get(choice1 - 6).property.set(5, "Можно");
                        if (shopBuildintNotLevel.get(choice1 - 6) instanceof Craft)
                            shopBuildintNotLevel.get(choice1 - 6).property.set(5, "Нельзя");
                        wallet.setStone(wallet.getStone() - shopBuildintNotLevel.get(choice1 - 6).getCostInStoneBuy());
                        wallet.setTree(wallet.getTree() - shopBuildintNotLevel.get(choice1 - 6).getCostInTreeBuy());
                    }
                    if (choice1 == 5) {
                        if (((Academy) shopBuildintNotLevel.get(0)).isThereNewUnit() == true) {
                            GeneretedUnit newUnit = (GeneretedUnit) ((Academy) shopBuildintNotLevel.get(0)).getNewUnits().getLast();
                            GeneretedUnit newUnitInArmy = new GeneretedUnit(newUnit.getName(), newUnit.getNumber(), newUnit.getHealth(), newUnit.getAttack(), newUnit.getRangeAttack(), newUnit.getDefense(), newUnit.getMovement(), newUnit.getCost(), newUnit.getFines().get("#"), newUnit.getFines().get("@"), newUnit.getFines().get("!"));
                            newUnitInArmy.setNumber(Integer.toString(myPlayers.size() + 1));
                            myPlayers.add(newUnitInArmy);
                            for (String key : newFines.keySet())
                                newUnitInArmy.addFine(key, newFines.get(key));
                            field.addUnitOnField(newUnitInArmy);
                            printPlayers(myPlayers);
                        }
                    }

                }

                System.out.print("Хотите еще разобраться с деревней? \n 1 - Да\n 2 - Нет\n Ваш выбор - ");
                int choice2 = scanner.nextInt();
                if (choice2 == 2) {
                    wallet.balance();
                    break;
                }
            }
            for (int i = 0; i < myBuildingsNotLEvelUp.size(); i++) {
                if (myBuildingsNotLEvelUp.get(i) instanceof Craft) {
                    (myBuildingsNotLEvelUp.get(i)).property.set(5, "Можно");
                }

            }

        }


        System.out.println("Битва началась");
        output(scanner, myPlayers, botPlayers, field);
        System.out.println("Возможные действия:\n1 - перемещение\n2 - атака\n3 - пермещение и атака\n4 - сесть на альпакy\n5 - слезть с альпаки\n6 - пропуск хода");
        boolean k1 = true;

        while (k1 == true) {
            System.out.print("Мой выбор - ");
            int choise = scanner.nextInt();

            System.out.print("Номер воина - ");
            Scanner scanner1 = new Scanner(System.in);

            String numberOfWarrior = scanner1.nextLine();
            int IndexOfwarIsAct = findUnit(numberOfWarrior, myPlayers);
            //System.out.println(myPlayers.get(IndexOfwarIsAct).getxCoord() + "x coord");
            switch (choise) {
                case 1: {
                    k1 = doMove(scanner1, field, movement, myPlayers, IndexOfwarIsAct);
                    if (k1 == false)
                        break;
                    else {
                        System.out.println("Перемещение невозможно, сделайте ход еще раз");
                        output(scanner, myPlayers, botPlayers, field);
                        System.out.println("Возможные действия:\n1 - перемещение\n2 - атака\n3 - пермещение и атака\n4 - сесть на альпакy\n5 - слезть с альпаки\n6 - пропуск хода");


                    }
                    break;
                }
                case 2: {
                    k1 = doAttack(wallet, scanner1, field, attack, myPlayers, IndexOfwarIsAct, botPlayers);
                    if (k1 == false)
                        break;
                    else {
                        System.out.println("Атака невозможна, сделайте ход еще раз");
                        output(scanner, myPlayers, botPlayers, field);
                        System.out.println("Возможные действия:\n1 - перемещение\n2 - атака\n3 - пермещение и атака\n4 - сесть на альпакy\n5 - слезть с альпаки\n6 - пропуск хода");
                    }
                    break;
                }
                case 3: {
                    k1 = doMove(scanner1, field, movement, myPlayers, IndexOfwarIsAct);
                    if (k1 == true) {
                        System.out.println("Перемещение невозможно, сделайте ход еще раз");
                        output(scanner, myPlayers, botPlayers, field);
                        System.out.println("Возможные действия:\n1 - перемещение\n2 - атака\n3 - пермещение и атака\n4 - сесть на альпакy\n5 - слезть с альпаки\n6 - пропуск хода");
                        break;
                    } else
                        printBasicField(field);
                    k1 = doAttack(wallet, scanner1, field, attack, myPlayers, IndexOfwarIsAct, botPlayers);
                    if (k1 == false)
                        break;
                    else {
                        System.out.println("Атака невозможна, сделайте ход еще раз");
                        output(scanner, myPlayers, botPlayers, field);
                        System.out.println("Возможные действия:\n1 - перемещение\n2 - атака\n3 - пермещение и атака\n4 - сесть на альпакy\n5 - слезть с альпаки\n6 - пропуск хода");
                    }
                    break;
                }
                case 4: {
                    if (alpaka.isGetOnAlpaka(myPlayers.get(IndexOfwarIsAct), field)) {
                        alpaka.getOnAlpaka(myPlayers.get(IndexOfwarIsAct), field);
                        myPlayers.get(IndexOfwarIsAct).getNumber();
                        ArrayList<Unit> changes = new ArrayList<>();
                        changes.add(myPlayers.get(IndexOfwarIsAct));
                        printPlayers(changes);
                        k1 = false;
                        break;
                    } else {
                        output(scanner, myPlayers, botPlayers, field);
                        System.out.println("Возможные действия:\n1 - перемещение\n2 - атака\n3 - пермещение и атака\n4 - сесть на альпакy\n5 - слезть с альпаки\n6 - пропуск хода");
                        k1 = true;
                    }
                    break;
                }
                case 5: {
                    if (alpaka.isGetOffAlpaka()) {
                        alpaka.getOffAlpaka(myPlayers.get(IndexOfwarIsAct), field);
                        ArrayList<Unit> changes = new ArrayList<>();
                        changes.add(myPlayers.get(IndexOfwarIsAct));
                        printPlayers(changes);
                        k1 = false;
                        break;
                    } else {
                        System.out.println();
                        output(scanner, myPlayers, botPlayers, field);
                        System.out.println("Возможные действия:\n1 - перемещение\n2 - атака\n3 - пермещение и атака\n4 - сесть на альпакy\n5 - слезть с альпаки\n6 - пропуск хода");
                        k1 = true;
                    }
                    break;
                }
                case 6:
                    break;
            }
        }
        output(scanner, myPlayers, botPlayers, field);

        bot.botAction(wallet, myPlayers, botPlayers, field, movement, attack);
        if (shopBuilding.get(4).property.get(1).equals("Нельзя")) {
            ((Farm) (shopBuilding.get(4))).setWasFightOver(true);
            ((Farm) (shopBuilding.get(4))).setNumberOfFigtsNotFeed(((Farm) (shopBuilding.get(4))).getNumberOfFigtsNotFeed()+1);
            if (((Farm) (shopBuilding.get(4))).getNumberOfFigtsNotFeed() >= 2) {
                System.out.println("Вы не кормили альпак 2 боя подряд. Они сбежали. Ферма закрыта");
                ((shopBuilding.get(4))).property.set(1, Integer.toString((shopBuilding.get(4)).getCostInStoneBuy()));
                ((shopBuilding.get(4))).property.set(2, Integer.toString((shopBuilding.get(4)).getCostInTreeBuy()));
                ((shopBuilding.get(4))).property.set(3, "Нельзя");
                ((shopBuilding.get(4))).property.set(4, "Нельзя");
                ((shopBuilding.get(4))).property.set(5, "Нельзя");
                ((Farm) (shopBuilding.get(4))).setNumberOfFigtsNotFeed(0);
                ((Farm) (shopBuilding.get(4))).setWasFightOver(false);
                ((Farm) (shopBuilding.get(4))).setAmountOfWool(5);
                ((shopBuilding.get(4))).setLevel(1);

            }

        }
    }

    private void output(Scanner scanner, ArrayList<Unit> myPlayers, ArrayList<Unit> botPlayers, Field field) {
        System.out.println("Состояние вашей армии");
        printPlayers(myPlayers);
        printFines(myPlayers);
        System.out.println("Состояние армии противника");
        printPlayers(botPlayers);
        System.out.println("Поле битвы");
        printBasicField(field);
    }


    private boolean doMove(Scanner scanner1, Field field, Movement movement, ArrayList<Unit> myPlayers, int IndexOfwarIsAct) {
        System.out.print("Координаты перемещения: x y - ");
        String line = scanner1.nextLine();  // reads the single input line from the console
        String[] strings = line.split(" ");  // splits the string wherever a space character is encountered, returns the result as a String[]
        int x2 = Integer.parseInt(strings[0]);
        int y2 = Integer.parseInt(strings[1]);
        System.out.println("x : "+myPlayers.get(IndexOfwarIsAct).getxCoord());
        System.out.println("y : "+myPlayers.get(IndexOfwarIsAct).getyCoord());
        if (movement.isMove(field, x2, y2, myPlayers.get(IndexOfwarIsAct))) {
            movement.movementAction(field, x2, y2, myPlayers.get(IndexOfwarIsAct));

            return false;
        }
        return true;
    }

    private boolean doAttack(Wallet wallet, Scanner scanner1, Field field, Attack attack, ArrayList<Unit> myPlayers, int IndexOfwarIsAct, ArrayList<Unit> botPlayers) {
        System.out.print("Координаты атаки: x y - ");
        String line = scanner1.nextLine();  // reads the single input line from the console
        String[] strings = line.split(" ");  // splits the string wherever a space character is encountered, returns the result as a String[]
        int x2 = Integer.parseInt(strings[0]);
        int y2 = Integer.parseInt(strings[1]);
        String symbolOfDamagingWarrior = field.getBasicField().get(y2).get(x2);
        int IndexOfwarIsAttacted = findUnit(symbolOfDamagingWarrior, botPlayers);
        if (attack.isAttack(field, x2, y2, myPlayers.get(IndexOfwarIsAct))) {
            attack.attackAction(wallet, field, x2, y2, myPlayers.get(IndexOfwarIsAct), botPlayers.get(IndexOfwarIsAttacted), botPlayers, myPlayers);
            return false;
        }
        return true;
    }


    private int findUnit(String k, ArrayList<Unit> units) {
        for (int i = 0; i < units.size(); i++) {

            if (units.get(i).getNumber().equals(k))
                return i;
        }
        return 0;
    }

    public void start() {

        Random random = new Random();
        Bot bot = new Bot();
        Movement movement = new Movement();
        Scanner scanner = new Scanner(System.in);


        // десериализация в новый список
        ArrayList<Building> myBuildings=new ArrayList<>();
        ArrayList<BuildingNotLevelUp> myBuildingsNotLEvelUp=new ArrayList<>();
        ArrayList<Building> shopBuildings=new ArrayList<>();
        ArrayList<BuildingNotLevelUp> shopBuildingsNotLEvelUp=new ArrayList<>();
        Wallet wallet=new Wallet(0,0,0,0);

        System.out.print("Что вы хотите?\n 1 - Загрузить игру\n 2 - Начать новую\n Ваш выбор - ");
        int choiseNewOrOldGame=scanner.nextInt();
        if (choiseNewOrOldGame==1)
        {
            int moneyInWallet=0;
            int stoneInWallet=0;
            int treeInWallet=0;
            int woolInWallet=0;


            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("savedGame.dat")))
            {

                shopBuildings=((ArrayList<Building>)ois.readObject());
                shopBuildingsNotLEvelUp=((ArrayList<BuildingNotLevelUp>)ois.readObject());
                myBuildings=((ArrayList<Building>)ois.readObject());
                myBuildingsNotLEvelUp=((ArrayList<BuildingNotLevelUp>)ois.readObject());
                moneyInWallet=(Integer)ois.readObject();
                stoneInWallet=(Integer)ois.readObject();
                treeInWallet=(Integer)ois.readObject();
                woolInWallet=(Integer)ois.readObject();
            }
            catch(Exception ex){

                System.out.println(ex.getMessage());
            }
            printBuildings(shopBuildings,shopBuildingsNotLEvelUp);
            wallet=new Wallet(moneyInWallet+10,stoneInWallet,treeInWallet,woolInWallet);
            System.out.println("Вы получаете +10 монет за начало игры");

            wallet.balance();

        }
        else
            wallet = new Wallet(50, 100, 100,0);


        Attack attack = new Attack();
        int money = wallet.getMoney();
        int dificulty;
        int size;

        System.out.println("Добро пожаловать в NiceVillage\nНа нашу деревнюceh произошло нападение, и только ты в силах спасти тысячи мирных жителей!\n");


        System.out.println("На вашем счету " + money + " монет\nПоочередно выбери воинов в свою армию");
        ArrayList<Unit> shop = new ArrayList<>();
        FootSoldier p1 = new FootSoldier("Мечник", "1", 50, 5, 1, 8, 3, 10, 1.5, 2.0, 1.2);
        FootSoldier p2 = new FootSoldier("Копьеносец", "2", 35, 3, 1, 4, 6, 15, 1.5, 2.0, 1.2);
        FootSoldier p3 = new FootSoldier("Топорщик", "3", 45, 9, 1, 3, 4, 20, 1.5, 2.0, 1.2);
        Bowman p4 = new Bowman("Лучник(дл. лук)", "4", 30, 6, 5, 8, 2, 15, 1.8, 2.2, 1.0);
        Bowman p5 = new Bowman("Лучник(кор. лук)", "5", 25, 3, 3, 4, 4, 19, 1.8, 2.2, 1.0);
        Bowman p6 = new Bowman("Арбалетчик", "6", 40, 7, 6, 3, 2, 23, 1.8, 2.2, 1.0);
        Horseman p7 = new Horseman("Рыцарь", "7", 30, 5, 1, 3, 6, 20, 2.2, 1.2, 1.5);
        Horseman p8 = new Horseman("Кирасир", "8", 50, 2, 1, 7, 5, 23, 2.2, 1.2, 1.5);
        Horseman p9 = new Horseman("Конный лучник", "9", 25, 3, 3, 2, 5, 25, 2.2, 1.2, 1.5);
        Superman p10 = new Superman("Супермен", "10", 10000, 10000, 10000, 10000, 10000, 30, 0.0, 0.0, 0.0);

        shop.add((Unit) p1);
        shop.add((Unit) p2);
        shop.add((Unit) p3);
        shop.add((Unit) p4);
        shop.add((Unit) p5);
        shop.add((Unit) p6);
        shop.add((Unit) p7);
        shop.add((Unit) p8);
        shop.add((Unit) p9);
        shop.add((Unit) p10);
        printPlayers(shop);
        ArrayList<Unit> myPlayers = new ArrayList<>();
        ArrayList<String> myNumberPlayers = new ArrayList<>();
        int index = 0;
        while (true) {
            System.out.print("ВВедите номер воина - ");
            int t = scanner.nextInt();
            money -= shop.get(t - 1).getCost();
            if (money < 0) {
                System.out.println("Недостаточно средств, ваша армия сформирована");
                money += shop.get(t - 1).getCost();
                break;
            }
            wallet.setMoney(money);
            generateUnit(shop, t, myPlayers, index);
            myNumberPlayers.add(myPlayers.get(index).getNumber());

            System.out.println("Воин " + myPlayers.get(index).getName() + " вступил в армию");
            index++;
            System.out.println("На вашем счету " + money + " монет");
        }
        ArrayList<Unit> botPlayers = new ArrayList<>();
        ArrayList<String> botSymbolPlayers = new ArrayList<>();
        int index2 = 0;
        ArrayList<String> symbol = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h"));

        for (int i = 0; i < myPlayers.size(); i++) {
            generateUnit(shop, random.nextInt(9) + 1, botPlayers, i);
            botPlayers.get(i).setNumber(symbol.get(i));
            botSymbolPlayers.add(botPlayers.get(i).getNumber());
            botPlayers.get(i).setxCoord(index2);
            botPlayers.get(i).setyCoord(0);
            index2++;
        }
        ArrayList<Integer> numbersOfNewUnitsInArmy=new ArrayList<>();


        Field field = new Field(myNumberPlayers, botSymbolPlayers);
        if (choiseNewOrOldGame==2)
        {
            DoctorHouse doctorHouse = new DoctorHouse(wallet, myPlayers, "Дом лекаря", 3, 4, 2, 2);
            Tavern tavern = new Tavern(wallet, myPlayers, "Таверня", 4, 3, 3, 1);
            Forge forge = new Forge(wallet, myPlayers, "Кузница", 6, 0, 3, 0);
            Arcenal arcenal = new Arcenal(wallet, myPlayers, "Арсенал", 0, 6, 0, 3);
            Farm farm = new Farm(wallet, myPlayers, "Ферма", 0, 6, 0, 3);

            Academy academy = new Academy("Академия", 4, 2, wallet);
            Market market = new Market("Рынок", 2, 2, wallet);
            Craft craft1 = new Craft("Мастерская 1", 4, 4, wallet);
            Craft craft2 = new Craft("Мастерская 2", 4, 4, wallet);
            Craft craft3 = new Craft("Мастерская 3", 4, 4, wallet);
            Craft craft4 = new Craft("Мастерская 4", 4, 4, wallet);
            shopBuildingsNotLEvelUp.add(academy);
            shopBuildingsNotLEvelUp.add(market);
            shopBuildingsNotLEvelUp.add(craft1);
            shopBuildingsNotLEvelUp.add(craft2);
            shopBuildingsNotLEvelUp.add(craft3);
            shopBuildingsNotLEvelUp.add(craft4);
            shopBuildings.add( doctorHouse);
            shopBuildings.add( tavern);
            shopBuildings.add( forge);
            shopBuildings.add(arcenal);
            shopBuildings.add(farm);
        }
        else {
            ArrayList<Unit> arrOfNewUnits=((Academy)shopBuildingsNotLEvelUp.get(0)).getNewUnits();
            int sizeOfArmy=myPlayers.size();
            for (int i=0;i<arrOfNewUnits.size();i++)
            {
                arrOfNewUnits.get(i).setNumber(Integer.toString(myPlayers.size()+i+1));
                arrOfNewUnits.get(i).setCost(0);
            }

            System.out.println("Вам доступны ранее разработанные юниты");
            printPlayers(arrOfNewUnits);


            int index10 = 0;
            while (true) {

                System.out.print("Введите номер воина для добавления в армию. Если не хотите добавлять введите 0 - ");
                int t = scanner.nextInt();
                if (t==0)
                    break;
//                money -= arrOfNewUnits.get(t - 1-sizeOfArmy).getCost();
//                if (money < 0) {
//                    System.out.println("Недостаточно средств, ваша армия сформирована");
//                    break;
//                }
//                wallet.setMoney(money);
                GeneretedUnit newUnit = (GeneretedUnit) (arrOfNewUnits.get(t-1-sizeOfArmy));
                GeneretedUnit newUnitInArmy = new GeneretedUnit(newUnit.getName(), newUnit.getNumber(),
                        newUnit.getHealth(), newUnit.getAttack(), newUnit.getRangeAttack(), newUnit.getDefense(),
                        newUnit.getMovement(), newUnit.getCost(), newUnit.getFines().get("#"), newUnit.getFines().get("@"),
                        newUnit.getFines().get("!"));

                myPlayers.add(newUnitInArmy);

                for (String key:newFines.keySet())
                    newUnitInArmy.addFine(key,newFines.get(key));

                numbersOfNewUnitsInArmy.add(Integer.parseInt(newUnitInArmy.getNumber()));
                System.out.println("Воин " + myPlayers.getLast().getName() + " вступил в армию");
                index++;
                System.out.println("На вашем счету " + money + " монет");

            }
            printPlayers(myPlayers);



        }


        File finesFile = new File("C:\\Users\\Пользователь\\IdeaProjects\\untitled\\barriers.txt");
        try {
            BufferedReader bufferedReader5 = new BufferedReader(new FileReader("C:\\Users\\Пользователь\\IdeaProjects\\untitled\\barriers.txt"));
            String lineInFile;
            int indexChetn = 0;
            String symbolOfNewFine = "";
            Double znach = 0.0;
            while ((lineInFile = bufferedReader5.readLine()) != null) {
                if (indexChetn % 2 == 0) {
                    symbolOfNewFine = lineInFile;
                } else {
                    znach = Double.parseDouble(lineInFile);
                    newFines.put(symbolOfNewFine,znach);
                }
                indexChetn++;
                for (int i = 0; i < myPlayers.size(); i++)
                    myPlayers.get(i).addFine(symbolOfNewFine, znach);
                for (int i = 0; i < botPlayers.size(); i++)
                    botPlayers.get(i).addFine(symbolOfNewFine, znach);
                field.addSymbol(symbolOfNewFine);
            }
            bufferedReader5.close();
        } catch (IOException e) {
            System.out.println("Невозможно открыть файл");
        }


        System.out.print("\n\nВы хотите использовать\n 1 - раннее созданную карту\n 2 - сгенерировать ее\n Ваш выбор -  ");
        int choice2 = scanner.nextInt();
        switch (choice2) {
            case 1: {
                File nfile = new File("C:\\Users\\Пользователь\\IdeaProjects\\untitled\\allMaps.txt");
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\Пользователь\\IdeaProjects\\untitled\\allMaps.txt"));
                    String line;
                    System.out.println("Доступные файлы :");
                    while ((line = bufferedReader.readLine()) != null)
                        System.out.println(line);
                    bufferedReader.close();
                } catch (IOException e) {
                    System.out.println("Невозможно открыть файл");
                }
                System.out.print("Ваш выбор - ");
                Scanner scanner1 = new Scanner(System.in);
                String fileToOpen = scanner1.nextLine();
                field.fillBasicFieldFromFile(myNumberPlayers, botSymbolPlayers, fileToOpen);
                size = field.getxSize();

                break;
            }
            case 2: {
                System.out.print("Уровни сложность игры:\nлегкий - 1\nсредний - 2\nсложный - 3\nМой уровень - ");
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
                        size = 3;
                }
                field.fillBasicField(size, myNumberPlayers, botSymbolPlayers);
                field.setSize(size);

                break;
            }
            default:
                size = 3;

        }
        for (int i = 0; i < myPlayers.size(); i++) {
            myPlayers.get(i).setxCoord(i);
            myPlayers.get(i).setyCoord(size - 1);
        }
        Alpaka alpaka = new Alpaka(random.nextInt(size), random.nextInt(size - 2) + 1);
        for (int t=0;t<numbersOfNewUnitsInArmy.size();t++)
            field.addUnitOnField(myPlayers.get(numbersOfNewUnitsInArmy.get(t)-1));


        field.changeBasicField(alpaka.getxCoord(), alpaka.getyCoord(), "$");
        int squirrelKoef = random.nextInt(1);
        int indexBattle = 0;


        while (true) {

            System.out.print("Хотите закончить игру?\n 1 - Да\n 2 - Нет\n Ваш выбор - ");
            int choiceIfGameStop = scanner.nextInt();
            boolean isWantToendTheGame = false;

            switch (choiceIfGameStop) {
                case 1: {
                    serrialize(wallet, myBuildings, myBuildingsNotLEvelUp, shopBuildings, shopBuildingsNotLEvelUp);
                    isWantToendTheGame = true;
                    System.out.println("Игра окончена");
                    break;
                }
                case 2:
                    break;
            }
            if (isWantToendTheGame == true)
                break;
            if (botPlayers.isEmpty()) {
                System.out.println("Игра окончена\nВы одержали победу");
                System.out.println("Вы получили 30 дерево и 30 монет за гибель воина противника");
                wallet.setTree(wallet.getTree() + 30);
                wallet.setStone(wallet.getStone() + 30);
                wallet.balance();
                break;
            }
            if (myPlayers.isEmpty()) {
                System.out.println("Игра окончена\nВы потерпели поражение");
                break;
            }
            battle(wallet, myBuildings, myBuildingsNotLEvelUp, shopBuildings, shopBuildingsNotLEvelUp, alpaka, bot, field, movement, attack, botPlayers, myPlayers);
            squirrelOnYou(squirrelKoef, indexBattle, myPlayers);
            indexBattle++;


        }
    }

    private void serrialize(Wallet wallet, ArrayList<Building> myBuildings, ArrayList<BuildingNotLevelUp> myBuildingsNotLEvelUp, ArrayList<Building> shopBuilding, ArrayList<BuildingNotLevelUp> shopBuildintNotLevel) {
        String filename = "savedGame.dat";
        // создадим список объектов, которые будем записывать

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(shopBuilding);
            oos.writeObject(shopBuildintNotLevel);
            oos.writeObject(myBuildings);
            oos.writeObject(myBuildingsNotLEvelUp);
            oos.writeObject(wallet.getMoney());
            oos.writeObject(wallet.getStone());
            oos.writeObject(wallet.getTree());
            oos.writeObject(wallet.getWool());


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        printBuildings(shopBuilding, shopBuildintNotLevel);
        wallet.balance();
        printPlayers(((Academy) shopBuildintNotLevel.get(0)).getNewUnits());
    }

    private void squirrelOnYou(int squirrelKoef, int indexBattle, ArrayList<Unit> myPlayers) {
        if (indexBattle == squirrelKoef) {
            for (int i = 0; i < myPlayers.size(); i++) {
                System.out.println("Mомент настал");
                if (myPlayers.get(i) instanceof Bowman) {
                    System.out.println("Поздравляю, на " + myPlayers.get(i).getNumber() + " села белка, атака увеличилась вдвое");
                    ((Bowman) myPlayers.get(i)).withSquirrel();
                    ArrayList<Unit> changes = new ArrayList<>();
                    changes.add(myPlayers.get(i));
                    printPlayers(changes);
                    break;
                }
            }
        }

    }

    public void printBasicField(Field field) {
        StringBuilder table = new StringBuilder();
        ArrayList<String> numbers = new ArrayList<>();
        numbers.add("y/x");
        for (int i = 0; i < field.getxSize(); i++)
            numbers.add(Integer.toString(i));
        printHorizontalLine(table, field.getxSize() + 1);
        printRow(table, numbers);
        printHorizontalLine(table, field.getxSize() + 1);
        for (int i = 0; i < field.getxSize(); i++) {
            ArrayList<String> numbersAndField = new ArrayList<>();
            numbersAndField.add(Integer.toString(i));
            numbersAndField.addAll(field.getBasicField().get(i));
            printRow(table, numbersAndField);
            printHorizontalLine(table, field.getxSize() + 1);
        }
        System.out.println(table.toString());
    }

    private void generateUnit(ArrayList<Unit> shop, int t, ArrayList<Unit> myPlayers, int index) {
        if (shop.get(t - 1) instanceof Bowman) {
            Bowman warrior = new Bowman(shop.get(t - 1).getName(), Integer.toString(index + 1), shop.get(t - 1).getHealth(), shop.get(t - 1).getAttack(), shop.get(t - 1).getRangeAttack(), shop.get(t - 1).getDefense(), shop.get(t - 1).getMovement(), shop.get(t - 1).getCost(), shop.get(t - 1).getFines().get("#"), shop.get(t - 1).getFines().get("@"), shop.get(t - 1).getFines().get("!"));
            myPlayers.add(warrior);
        } else if (shop.get(t - 1) instanceof FootSoldier) {
            FootSoldier warrior = new FootSoldier(shop.get(t - 1).getName(), Integer.toString(index + 1), shop.get(t - 1).getHealth(), shop.get(t - 1).getAttack(), shop.get(t - 1).getRangeAttack(), shop.get(t - 1).getDefense(), shop.get(t - 1).getMovement(), shop.get(t - 1).getCost(), shop.get(t - 1).getFines().get("#"), shop.get(t - 1).getFines().get("@"), shop.get(t - 1).getFines().get("!"));
            myPlayers.add(warrior);
        } else if (shop.get(t - 1) instanceof Superman) {
            Superman warrior = new Superman(shop.get(t - 1).getName(), Integer.toString(index + 1), shop.get(t - 1).getHealth(), shop.get(t - 1).getAttack(), shop.get(t - 1).getRangeAttack(), shop.get(t - 1).getDefense(), shop.get(t - 1).getMovement(), shop.get(t - 1).getCost(), shop.get(t - 1).getFines().get("#"), shop.get(t - 1).getFines().get("@"), shop.get(t - 1).getFines().get("!"));
            myPlayers.add(warrior);
        } else {
            Horseman warrior = new Horseman(shop.get(t - 1).getName(), Integer.toString(index + 1), shop.get(t - 1).getHealth(), shop.get(t - 1).getAttack(), shop.get(t - 1).getRangeAttack(), shop.get(t - 1).getDefense(), shop.get(t - 1).getMovement(), shop.get(t - 1).getCost(), shop.get(t - 1).getFines().get("#"), shop.get(t - 1).getFines().get("@"), shop.get(t - 1).getFines().get("!"));
            myPlayers.add(warrior);
        }
    }

    public void printBuildings(ArrayList<Building> shopBuilding, ArrayList<BuildingNotLevelUp> shopBuildintNotLevel) {
        ArrayList<String> header = new ArrayList<>();
        header.add("Номер");
        header.add("Название");
        header.add("Купить(камни)");
        header.add("Купить(дерево)");
        header.add("Улучшить(камни)");
        header.add("Улучшить(дерево)");
        header.add("Посетить");
        header.add("Предназначение");

        StringBuilder table = new StringBuilder();
        printHorizontalLine(table, 8);
        printRow(table, header);
        printHorizontalLine(table, 8);
        for (int i = 0; i < shopBuilding.size(); i++) {
            ArrayList<String> stroka = new ArrayList<>();
            stroka.add(Integer.toString(i + 1));
            stroka.addAll(shopBuilding.get(i).property);
            printRow(table, stroka);
        }
        for (int i = 0; i < shopBuildintNotLevel.size(); i++) {
            ArrayList<String> stroka = new ArrayList<>();
            stroka.add(Integer.toString(shopBuilding.size() + i + 1));
            stroka.addAll(shopBuildintNotLevel.get(i).property);
            printRow(table, stroka);
        }
        printHorizontalLine(table, 8);
        System.out.println(table.toString());
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

    public void printFines(ArrayList<Unit> unit) {
        System.out.println("Штрафы");
        ArrayList<String> header = new ArrayList<>();
        header.add("Воин");
        header.add("Номер");
        int n = unit.get(0).getFines().size() + 1;
        for (String key : unit.get(0).getFines().keySet()) {
            if (!(key.equals("*")))
                header.add(key);
        }
        StringBuilder table = new StringBuilder();
        printHorizontalLine(table, n);
        printRow(table, header);
        printHorizontalLine(table, n);
        for (int i = 0; i < unit.size(); i++) {
            ArrayList<String> stroka = new ArrayList<>();
            stroka.add(unit.get(i).getName());
            stroka.add(unit.get(i).getNumber());
            for (String key : unit.get(i).getFines().keySet()) {
                if (!(key.equals("*")))
                    stroka.add(Double.toString(unit.get(i).getFines().get(key)));
            }
            printRow(table, stroka);
        }

        printHorizontalLine(table, n);
        System.out.println(table.toString());
    }
}


