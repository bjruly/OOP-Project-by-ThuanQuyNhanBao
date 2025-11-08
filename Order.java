import java.util.ArrayList;
import java.util.List;
public class Order {
    private final String orderId;
    private final Customer customer;
    private final List<OrderDetail> details;
    public Order( String orderId,Customer customer){
        this.orderId = orderId;
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
        System.out.println("Tổng tiền:" + this.getTotalPrice());
        System.out.println("---------------------------");
    }
    public String getOrderId() {
        return this.orderId;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public List<OrderDetail> getDetails() {
        return this.details;
    }
}
