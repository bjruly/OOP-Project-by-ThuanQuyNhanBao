
public abstract class Clothing { // lớp con dc truy xuất protected và public cua lop cha (clothing la lop cha )
    protected String id;
    protected String name;
    protected double price;
    protected static int totalProducts = 0;
    public Clothing(String id, String name, double price){
        this.id = id;
        this.name = name;
        this.price = price;
        totalProducts++;
    }
    public abstract void displayInfo();
    public double getPrice() {
        return this.price;
    }
    public String getName() {
        return this.name;
    }
    public String getId() {
        return this.id;
    }
    public static int getTotalProducts() {
        return totalProducts;
    }
}