package ru.geekbrains;

import ru.geekbrains.behavioral.chains.Account;
import ru.geekbrains.behavioral.chains.Bank;
import ru.geekbrains.behavioral.chains.Bitcoin;
import ru.geekbrains.behavioral.chains.Paypal;
import ru.geekbrains.behavioral.command.Bulb;
import ru.geekbrains.behavioral.command.RemoteControl;
import ru.geekbrains.behavioral.command.TurnOff;
import ru.geekbrains.behavioral.command.TurnOn;
import ru.geekbrains.behavioral.mediator.ChatRoom;
import ru.geekbrains.behavioral.mediator.ChatRoomMediator;
import ru.geekbrains.behavioral.mediator.User;
import ru.geekbrains.behavioral.state.Default;
import ru.geekbrains.behavioral.state.Editor;
import ru.geekbrains.behavioral.state.LowerCase;
import ru.geekbrains.behavioral.state.UpperCase;
import ru.geekbrains.structural.adapter.AsianLion;
import ru.geekbrains.structural.adapter.Hunter;
import ru.geekbrains.structural.adapter.Lion;
import ru.geekbrains.structural.adapter.WildDogAdapter;
import ru.geekbrains.structural.bridge.*;
import ru.geekbrains.structural.composite.Developer;
import ru.geekbrains.structural.composite.Employee;
import ru.geekbrains.structural.composite.Organization;
import ru.geekbrains.structural.decorator.Coffee;
import ru.geekbrains.structural.decorator.MilkCoffee;
import ru.geekbrains.structural.decorator.SimpleCoffee;
import ru.geekbrains.structural.decorator.VanillaCoffee;
import ru.geekbrains.structural.facade.Computer;
import ru.geekbrains.structural.facade.ComputerFacade;
import ru.geekbrains.structural.flyweight.TeaMaker;
import ru.geekbrains.structural.flyweight.TeaShop;
import ru.geekbrains.structural.proxy.LabDoor;
import ru.geekbrains.structural.proxy.SecuredDoor;

public class Main {

    public static void main(String[] args) {

        //adapter
        Hunter hunter = new Hunter();
        Lion lion = new AsianLion();
        WildDogAdapter dogAdapter = new WildDogAdapter();
        hunter.hunt(lion);
        hunter.hunt(dogAdapter);

        // bridge
        WebPage aboutPage = new AboutPage(new DarkTheme());
        WebPage careerPage = new CareerPage(new AquaTheme());
        System.out.println(aboutPage.getContent());
        System.out.println(careerPage.getContent());

        //composite
        Organization organization = new Organization();
        Employee john = new Developer(1234.32, "John");
        Employee david = new Developer(7253.83, "David");
        Employee mary = new Developer(2452.2, "Mary");
        organization.addEmployee(john);
        organization.addEmployee(david);
        organization.addEmployee(mary);
        System.out.println("Общая зарплата сотрудников  " + organization.getNetSalary());

        //decorator
        Coffee coffee = new SimpleCoffee();
        System.out.println(coffee.getDescription());
        System.out.println(coffee.getCost());
        coffee = new MilkCoffee(coffee);
        System.out.println(coffee.getDescription());
        System.out.println(coffee.getCost());
        coffee = new VanillaCoffee(coffee);
        System.out.println(coffee.getDescription());
        System.out.println(coffee.getCost());

        //facade
        ComputerFacade facade = new ComputerFacade(new Computer());
        facade.turnOn();
        facade.turnOff();

        //flyweight
        TeaShop teaShop = new TeaShop(new TeaMaker());
        teaShop.takeOrder("Улун", 1);
        teaShop.takeOrder("Ягодный", 2);
        teaShop.takeOrder("С молоком", 5);
        teaShop.serve();

        //proxy
        SecuredDoor door = new SecuredDoor(new LabDoor());
        door.open("12431");
        door.open("1234qwerty");
        door.close();

        //chains
        Account bank = new Bank(100);
        Account paypal = new Paypal(200);
        Account bitcoin = new Bitcoin(300);
        bank.setNext(paypal);
        paypal.setNext(bitcoin);
        bank.pay(125);

        //command
        Bulb bulb = new Bulb();
        RemoteControl remoteControl = new RemoteControl();
        remoteControl.submit(new TurnOn(bulb));
        remoteControl.submit(new TurnOff(bulb));

        //mediator
        ChatRoomMediator chatRoomMediator = new ChatRoom();
        User josh = new User("John", chatRoomMediator);
        User jane = new User("Mary", chatRoomMediator);
        jane.sendMessage("Hello");
        josh.sendMessage("Hello");

        //state
        Editor editor = new Editor(new Default());
        editor.type("Hello");
        editor.setState(new LowerCase());
        editor.type("Hello");
        editor.setState(new UpperCase());
        editor.type("Hello");
    }
}
