public class Fruit extends Item {
    private String type;

    public Fruit(String type, String name, int quantity) {
        super(name, quantity);
        this.type = type;
    }

    @Override
    public void displayInfo() {
        System.out.print("Abstract function successfully override!");
    }
}
