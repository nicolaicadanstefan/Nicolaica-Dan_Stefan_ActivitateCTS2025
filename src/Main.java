public class Main {
    public static void main(String[] args) {
        Item fruit = new Fruit("Fuji","Apple", 15);

        Inventory inventory = new Inventory();
        inventory.addItem(fruit);

        fruit.displayInfo();
    }
}