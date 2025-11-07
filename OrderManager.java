import java.util.ArrayList;
import java.util.List;

public class OrderManager implements chucnang<Order> {
    private final List<Order> list = new ArrayList<>();

    @Override
    public void add(Order order) {
        list.add(order);
    }

    @Override
    public Order searchById(String id) {
        for (Order o : list) {
            if (o.getOrderId().equals(id)) {
                return o;
            }
        }
        return null;
    }

    @Override
    public boolean deleteById(String id) {
        return list.removeIf(o -> o.getOrderId().equals(id));
    }

    @Override
    public void displayAll() {
        for (Order o : list) {
            System.out.println(o);
        }
    }
}