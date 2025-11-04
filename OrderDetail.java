public class OrderDetail {
    private  Clothing item;
    private int quantity;
    public OrderDetail(Clothing item, int quantity){
        this.item = item;
        this.quantity = quantity;
    }
    public double getSubtotal(){
        return item.getPrice() * quantity;
    }
    public void displayDetail(){
        System.out.println("Tên: " + item.getName() + " | SL: " + this.quantity + " | Subtotal: " + this.getSubtotal());
    }
}
