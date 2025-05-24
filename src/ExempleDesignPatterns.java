// ========== PATTERNURI CREATIONALE ==========

// 1. SINGLETON - Student Registry
class StudentRegistry {
    private static StudentRegistry instance;
    private String schoolName;

    private StudentRegistry() {
        this.schoolName = "University of Bucharest";
    }

    public static StudentRegistry getInstance() {
        if (instance == null) {
            instance = new StudentRegistry();
        }
        return instance;
    }

    public void printInfo() {
        System.out.println("Registry for: " + schoolName);
    }
}

// 2. BUILDER - Student builder
class Student {
    private String name;
    private int age;
    private String faculty;
    private int year;

    private Student(StudentBuilder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.faculty = builder.faculty;
        this.year = builder.year;
    }

    public static class StudentBuilder {
        private String name;
        private int age;
        private String faculty;
        private int year;

        public StudentBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public StudentBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        public StudentBuilder setFaculty(String faculty) {
            this.faculty = faculty;
            return this;
        }

        public StudentBuilder setYear(int year) {
            this.year = year;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }

    public String toString() {
        return "Student: " + name + ", " + age + " years, " + faculty + ", year " + year;
    }
}

// 3. FACTORY - Animal factory
interface Animal {
    void makeSound();
}

class Dog implements Animal {
    public void makeSound() {
        System.out.println("Woof woof!");
    }
}

class Cat implements Animal {
    public void makeSound() {
        System.out.println("Meow!");
    }
}

class AnimalFactory {
    public static Animal createAnimal(String type) {
        if (type.equals("dog")) {
            return new Dog();
        } else if (type.equals("cat")) {
            return new Cat();
        }
        return null;
    }
}

// 4. FACTORY METHOD - Game factory method
abstract class Game {
    public abstract void play();
}

class Football extends Game {
    public void play() {
        System.out.println("Playing football with 22 players");
    }
}

class Basketball extends Game {
    public void play() {
        System.out.println("Playing basketball with 10 players");
    }
}

abstract class GameCreator {
    public abstract Game createGame();

    public void startGame() {
        Game game = createGame();
        game.play();
    }
}

class FootballCreator extends GameCreator {
    public Game createGame() {
        return new Football();
    }
}

class BasketballCreator extends GameCreator {
    public Game createGame() {
        return new Basketball();
    }
}

// 5. PROTOTYPE - Book prototype
class Book implements Cloneable {
    private String title;
    private String author;
    private int pages;

    public Book(String title, String author, int pages) {
        this.title = title;
        this.author = author;
        this.pages = pages;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String toString() {
        return "Book: " + title + " by " + author + " (" + pages + " pages)";
    }
}

// ========== PATTERNURI STRUCTURALE ==========

// 6. ADAPTER - Phone charger adapter
interface EuropeanSocket {
    void plugIn();
}

class AmericanCharger {
    public void chargeWithAmericanPlug() {
        System.out.println("Charging with American plug");
    }
}

class SocketAdapter implements EuropeanSocket {
    private AmericanCharger charger;

    public SocketAdapter(AmericanCharger charger) {
        this.charger = charger;
    }

    public void plugIn() {
        System.out.println("Using adapter...");
        charger.chargeWithAmericanPlug();
    }
}

// 7. COMPOSITE - University structure
interface UniversityComponent {
    void showDetails();
}

class Department implements UniversityComponent {
    private String name;

    public Department(String name) {
        this.name = name;
    }

    public void showDetails() {
        System.out.println("Department: " + name);
    }
}

class Faculty implements UniversityComponent {
    private String name;
    private java.util.List<UniversityComponent> components;

    public Faculty(String name) {
        this.name = name;
        this.components = new java.util.ArrayList<>();
    }

    public void add(UniversityComponent component) {
        components.add(component);
    }

    public void showDetails() {
        System.out.println("Faculty: " + name);
        for (UniversityComponent component : components) {
            component.showDetails();
        }
    }
}

// 8. DECORATOR - Pizza decorator
interface Pizza {
    String getDescription();
    double getPrice();
}

class BasicPizza implements Pizza {
    public String getDescription() {
        return "Basic pizza";
    }

    public double getPrice() {
        return 10.0;
    }
}

abstract class PizzaDecorator implements Pizza {
    protected Pizza pizza;

    public PizzaDecorator(Pizza pizza) {
        this.pizza = pizza;
    }
}

class CheeseDecorator extends PizzaDecorator {
    public CheeseDecorator(Pizza pizza) {
        super(pizza);
    }

    public String getDescription() {
        return pizza.getDescription() + " + cheese";
    }

    public double getPrice() {
        return pizza.getPrice() + 2.0;
    }
}

class PepperoniDecorator extends PizzaDecorator {
    public PepperoniDecorator(Pizza pizza) {
        super(pizza);
    }

    public String getDescription() {
        return pizza.getDescription() + " + pepperoni";
    }

    public double getPrice() {
        return pizza.getPrice() + 3.0;
    }
}

// 9. FACADE - Home theater facade
class TV {
    public void turnOn() {
        System.out.println("TV is on");
    }

    public void turnOff() {
        System.out.println("TV is off");
    }
}

class SoundSystem {
    public void turnOn() {
        System.out.println("Sound system is on");
    }

    public void setVolume(int level) {
        System.out.println("Volume set to " + level);
    }
}

class DVDPlayer {
    public void play() {
        System.out.println("DVD is playing");
    }
}

class HomeTheaterFacade {
    private TV tv;
    private SoundSystem sound;
    private DVDPlayer dvd;

    public HomeTheaterFacade() {
        this.tv = new TV();
        this.sound = new SoundSystem();
        this.dvd = new DVDPlayer();
    }

    public void watchMovie() {
        System.out.println("Getting ready to watch movie...");
        tv.turnOn();
        sound.turnOn();
        sound.setVolume(5);
        dvd.play();
    }
}

// 10. FLYWEIGHT - Character flyweight
interface Character {
    void display(int size);
}

class ConcreteCharacter implements Character {
    private char letter;
    private String font;

    public ConcreteCharacter(char letter, String font) {
        this.letter = letter;
        this.font = font;
    }

    public void display(int size) {
        System.out.println("Character '" + letter + "' in " + font + " font, size " + size);
    }
}

class CharacterFactory {
    private static java.util.Map<String, Character> characters = new java.util.HashMap<>();

    public static Character getCharacter(char letter, String font) {
        String key = letter + font;
        Character character = characters.get(key);

        if (character == null) {
            character = new ConcreteCharacter(letter, font);
            characters.put(key, character);
        }

        return character;
    }

    public static int getCreatedCharacters() {
        return characters.size();
    }
}

// 11. PROXY - Internet proxy
interface Internet {
    void connectTo(String website);
}

class RealInternet implements Internet {
    public void connectTo(String website) {
        System.out.println("Connecting to " + website);
    }
}

class ProxyInternet implements Internet {
    private RealInternet realInternet;
    private java.util.List<String> blockedSites;

    public ProxyInternet() {
        this.realInternet = new RealInternet();
        this.blockedSites = new java.util.ArrayList<>();
        blockedSites.add("facebook.com");
        blockedSites.add("youtube.com");
    }

    public void connectTo(String website) {
        if (blockedSites.contains(website)) {
            System.out.println("Access denied to " + website);
        } else {
            realInternet.connectTo(website);
        }
    }
}

// ========== PATTERNURI COMPORTAMENTALE ==========

// 12. CHAIN OF RESPONSIBILITY - Support system
abstract class SupportHandler {
    protected SupportHandler nextHandler;

    public void setNext(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleRequest(String issue);
}

class TechnicalSupport extends SupportHandler {
    public void handleRequest(String issue) {
        if (issue.equals("technical")) {
            System.out.println("Technical support: I'll fix your technical issue");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(issue);
        }
    }
}

class BillingSupport extends SupportHandler {
    public void handleRequest(String issue) {
        if (issue.equals("billing")) {
            System.out.println("Billing support: I'll help with your bill");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(issue);
        }
    }
}

class GeneralSupport extends SupportHandler {
    public void handleRequest(String issue) {
        System.out.println("General support: I'll help you with " + issue);
    }
}

// 13. STRATEGY - Transportation strategy
interface TransportStrategy {
    void travel(String destination);
}

class CarStrategy implements TransportStrategy {
    public void travel(String destination) {
        System.out.println("Driving to " + destination + " by car");
    }
}

class TrainStrategy implements TransportStrategy {
    public void travel(String destination) {
        System.out.println("Taking train to " + destination);
    }
}

class BusStrategy implements TransportStrategy {
    public void travel(String destination) {
        System.out.println("Taking bus to " + destination);
    }
}

class Traveler {
    private TransportStrategy strategy;

    public void setTransportStrategy(TransportStrategy strategy) {
        this.strategy = strategy;
    }

    public void goTo(String destination) {
        strategy.travel(destination);
    }
}

// 14. TEMPLATE METHOD - Daily routine template
abstract class DailyRoutine {
    // Template method
    public final void performDailyRoutine() {
        wakeUp();
        eat();
        work();
        sleep();
    }

    private void wakeUp() {
        System.out.println("Wake up at 7 AM");
    }

    protected abstract void eat();
    protected abstract void work();

    private void sleep() {
        System.out.println("Go to sleep at 10 PM");
    }
}

class StudentRoutine extends DailyRoutine {
    protected void eat() {
        System.out.println("Eat breakfast quickly");
    }

    protected void work() {
        System.out.println("Attend classes and study");
    }
}

class WorkerRoutine extends DailyRoutine {
    protected void eat() {
        System.out.println("Have a proper breakfast");
    }

    protected void work() {
        System.out.println("Go to office and work");
    }
}

public class ExempleDesignPattens {
    public static void main(String[] args) {
        System.out.println("=== Design Patterns Examples ===\n");

        // Singleton test
        System.out.println("1. SINGLETON:");
        StudentRegistry registry1 = StudentRegistry.getInstance();
        StudentRegistry registry2 = StudentRegistry.getInstance();
        System.out.println("Same registry? " + (registry1 == registry2));
        registry1.printInfo();

        // Builder test
        System.out.println("\n2. BUILDER:");
        Student student = new Student.StudentBuilder()
                .setName("Ion Popescu")
                .setAge(20)
                .setFaculty("Computer Science")
                .setYear(2)
                .build();
        System.out.println(student);

        // Factory test
        System.out.println("\n3. FACTORY:");
        Animal dog = AnimalFactory.createAnimal("dog");
        Animal cat = AnimalFactory.createAnimal("cat");
        dog.makeSound();
        cat.makeSound();

        // Factory Method test
        System.out.println("\n4. FACTORY METHOD:");
        GameCreator footballCreator = new FootballCreator();
        GameCreator basketballCreator = new BasketballCreator();
        footballCreator.startGame();
        basketballCreator.startGame();

        // Prototype test
        System.out.println("\n5. PROTOTYPE:");
        Book originalBook = new Book("Java Programming", "John Doe", 300);
        Book clonedBook = (Book) originalBook.clone();
        clonedBook.setTitle("Advanced Java");
        System.out.println("Original: " + originalBook);
        System.out.println("Cloned: " + clonedBook);

        // Adapter test
        System.out.println("\n6. ADAPTER:");
        AmericanCharger americanCharger = new AmericanCharger();
        EuropeanSocket adapter = new SocketAdapter(americanCharger);
        adapter.plugIn();

        // Composite test
        System.out.println("\n7. COMPOSITE:");
        Faculty csf = new Faculty("Computer Science Faculty");
        Department csDept = new Department("CS Department");
        Department mathDept = new Department("Math Department");
        csf.add(csDept);
        csf.add(mathDept);
        csf.showDetails();

        // Decorator test
        System.out.println("\n8. DECORATOR:");
        Pizza pizza = new BasicPizza();
        pizza = new CheeseDecorator(pizza);
        pizza = new PepperoniDecorator(pizza);
        System.out.println(pizza.getDescription() + " - $" + pizza.getPrice());

        // Facade test
        System.out.println("\n9. FACADE:");
        HomeTheaterFacade homeTheater = new HomeTheaterFacade();
        homeTheater.watchMovie();

        // Flyweight test
        System.out.println("\n10. FLYWEIGHT:");
        Character a1 = CharacterFactory.getCharacter('A', "Arial");
        Character a2 = CharacterFactory.getCharacter('A', "Arial");
        a1.display(12);
        a2.display(14);
        System.out.println("Characters created: " + CharacterFactory.getCreatedCharacters());

        // Proxy test
        System.out.println("\n11. PROXY:");
        Internet internet = new ProxyInternet();
        internet.connectTo("google.com");
        internet.connectTo("facebook.com");

        // Chain of Responsibility test
        System.out.println("\n12. CHAIN OF RESPONSIBILITY:");
        SupportHandler technical = new TechnicalSupport();
        SupportHandler billing = new BillingSupport();
        SupportHandler general = new GeneralSupport();

        technical.setNext(billing);
        billing.setNext(general);

        technical.handleRequest("technical");
        technical.handleRequest("billing");
        technical.handleRequest("other");

        // Strategy test
        System.out.println("\n13. STRATEGY:");
        Traveler traveler = new Traveler();
        traveler.setTransportStrategy(new CarStrategy());
        traveler.goTo("Cluj");

        traveler.setTransportStrategy(new TrainStrategy());
        traveler.goTo("Brasov");

        // Template Method test
        System.out.println("\n14. TEMPLATE METHOD:");
        DailyRoutine studentDay = new StudentRoutine();
        DailyRoutine workerDay = new WorkerRoutine();

        System.out.println("Student's day:");
        studentDay.performDailyRoutine();

        System.out.println("\nWorker's day:");
        workerDay.performDailyRoutine();
    }
}