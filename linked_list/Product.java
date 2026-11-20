package linked_list;

// Product class representing individual items
class Product {

    String id;
    String name;
    int quantity;
    double price;

    public Product(String id, String name, int quantity, double price) {

        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {

        return "ID: " + id
                + ", Name: " + name
                + ", Quantity: " + quantity
                + ", Price: " + price;
    }
}