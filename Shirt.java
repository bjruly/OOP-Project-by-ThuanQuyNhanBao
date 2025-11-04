public class Shirt extends Clothing implements Discountable{
    private String size;
    private String color;
    private String brand;
    private String gender;
    private int stock;
    public Shirt(String id, String name, double price, String size, String color, String brand, String gender, int stock) {
        super(id, name, price);
        this.size = size;
        this.color = color;
        this.brand = brand;
        this.gender = gender;
        this.stock = stock;
    }
    @Override
    public void displayInfo(){
        System.out.println("----Áo Sơ Mi----");
        System.out.println("ID:" +this.id);
        System.out.println("Tên:" +this.name);
        System.out.println("Giá:" +this.price);
        System.out.println("Size:" +this.size);
        System.out.println("Màu:" +this.color);
        System.out.println("Thương hiệu:" +this.brand);
        System.out.println("Giới tính:" +this.gender);
        System.out.println("Tồn kho:" +this.stock);
    }
    @Override
    public double getDiscountedPrice(double percent){
        return this.price * (1.0 - (percent / 100.0));
    }
}
