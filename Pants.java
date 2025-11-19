public class Pants extends Clothing implements Discountable {
    private final String material;
    private final int waistSize;
    private final int length;
    private final String brand;
    private int stock; // Bỏ final để có thể thay đổi
    
    public Pants(String id, String name, double price, String material, int waistSize, int length, String brand, int stock) {
        super(id, name, price);
        this.material = material;
        this.waistSize = waistSize;
        this.length = length;
        this.brand = brand;
        this.stock = stock;
    }
    
    public String getMaterial() { return material; }
    public int getWaist() { return waistSize; }
    public int getLength() { return length; }
    public String getBrand() { return brand; }
    public int getStock() { return stock; }
    
    // Thêm phương thức giảm tồn kho
    public void reduceStock(int quantity) {
        if (quantity > 0 && this.stock >= quantity) {
            this.stock -= quantity;
        }
    }
    
    // Thêm phương thức thiết lập tồn kho
    public void setStock(int stock) {
        if (stock >= 0) {
            this.stock = stock;
        }
    }
    
    @Override
    public void displayInfo(){
        System.out.println("----Quần----");
        System.out.println("Id:" + this.id);
        System.out.println("Tên:" + this.name);
        System.out.println("Giá:" + this.price);
        System.out.println("Chất liệu:" + this.material);
        System.out.println("Size lưng:" + this.waistSize);
        System.out.println("Chiều dài:" + this.length);
        System.out.println("Thương hiệu:" + this.brand);
        System.out.println("Tồn kho:" + this.stock);
    }
    
    @Override
    public double getDiscountedPrice(double percent){
        double specialDiscount = 0.05;
        return this.price * (1.0 - (percent / 100) - specialDiscount);
    }
}