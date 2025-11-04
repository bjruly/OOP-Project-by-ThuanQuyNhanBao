import java.util.ArrayList;
import java.util.List;
public class Order {
    private Customer customer;
    private List<OrderDetail> details;
    public Order(Customer customer){
        this.customer = customer;
        this.details = new ArrayList<>();
    }
    public void addOrderDetail(Clothing item, int quantity) {
        OrderDetail newDetail = new OrderDetail(item, quantity);
        this.details.add(newDetail);
    }
    public double getTotalPrice(){
        double total = 0;
        for(OrderDetail detail : this.details){
            total += detail.getSubtotal();
        }
        return total;
    }
    public void displayOrder(){
        System.out.println("----Hóa đơn----");
        System.out.println("Khách hàng: " +this.customer.getCustomerId());
        System.out.println("Chi tiết mua hàng: ");
        for(OrderDetail detail : this.details){
            detail.displayDetail();
        }
        System.out.println("---------------------------");
        System.out.println("Tốne tiền:" + this.getTotalPrice());
        System.out.println("---------------------------");
    }
}
