public class Jacket extends Clothing implements Discountable {
    private String color;
    private String material;
    private String size;
    private int stock;
    public Jacket(String id, String name, double price, String color, String material, String size, int stock){
        super(id, name, price);
        this.color = color;
        this.material = material;
        this.size = size;
        this.stock = stock;
    }
    @Override
    public void displayInfo(){
        System.out.println("----Áo Khoác----");
        System.out.println("Id:" +this.id);
        System.out.println("Tên:" +this.name);
        System.out.println("Giá:" +this.price);
        System.out.println("Màu:" +this.color);
        System.out.println("Chất liệu:" +this.material);
        System.out.println("Size:" +this.size);
        System.out.println("Tồn kho:" +this.stock);
    }
    @Override
    public double getDiscountedPrice(double percent){
        System.out.println("Sản phẩm này không áp dụng giảm giá");
        return this.price;
    }
}
