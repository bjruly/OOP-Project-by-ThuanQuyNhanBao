import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class OrderManager implements chucnang<Order> {
    private final List<Order> list = new ArrayList<>();
    private final ClothingManager clothingManager;
    private final CustomerManager customerManager;

    // Constructor để truyền dependencies
    public OrderManager(ClothingManager clothingManager, CustomerManager customerManager) {
        this.clothingManager = clothingManager;
        this.customerManager = customerManager;
    }

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
            o.displayOrder();
            System.out.println(); // Thêm dòng trống giữa các đơn hàng
        }
    }

    @Override
    public boolean update(String id, Order newItem) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getOrderId().equals(id)) {
                list.set(i, newItem);
                return true;
            }
        }
        return false;
    }

    // Phương thức đọc dữ liệu từ file
    public void loadFromFile() {
        String filename = System.getProperty("user.dir") + "/resource/Order.txt";
        list.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line; // dong
            String orderId = null;
            String customerId = null;
            List<String> productLines = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                line = line.trim(); // bo khoang trang dau va cuoi
                if (line.isEmpty()) continue; // bo qua dong trong
                if (line.startsWith("ID:")) { // neu dong bat dau voi 
                    orderId = line.substring(3).trim(); // cat chuoi tu vi tri 3
                } else if (line.startsWith("Khách hàng:")) {
                    customerId = line.substring(11).trim();
                } else if (line.startsWith("Sản phẩm:")) {
                    productLines.add(line.substring(9).trim());// them vao danh sach productlines kiểu 
                } else if (line.startsWith("----Đơn hàng----")) {
                    if (orderId != null && customerId != null) {
                        createAndAddOrder(orderId, customerId, productLines);
                    }
                    // reset
                    orderId = null;
                    customerId = null;
                    productLines.clear();
                }
            }

            // Thêm đơn hàng cuối cùng nếu có
            if (orderId != null && customerId != null) {
                createAndAddOrder(orderId, customerId, productLines);
            }

            System.out.println("✅ Đã nạp dữ liệu từ Order.txt (" + list.size() + " đơn hàng)");
        } catch (IOException e) {
            System.err.println("❌ Lỗi đọc file Order.txt: " + e.getMessage());
        }
    }

    // Phương thức tạo và thêm đơn hàng
    private void createAndAddOrder(String orderId, String customerId, List<String> productLines) {
        // Tìm khách hàng
        Customer customer = customerManager.searchById(customerId);
        if (customer == null) {
            System.err.println("❌ Không tìm thấy khách hàng: " + customerId);
            return;
        }

        // Tạo đơn hàng
        Order order = new Order(orderId, customer);

        // Thêm sản phẩm vào đơn hàng
        for (String productLine : productLines) {
            String[] parts = productLine.split(",Số lượng:");
            if (parts.length == 2) {
                String productId = parts[0].trim();
                int quantity = Integer.parseInt(parts[1].trim());
                
                // Tìm sản phẩm
                Clothing item = clothingManager.searchById(productId);
                if (item != null) {
                    order.addOrderDetail(item, quantity);
                } else {
                    System.err.println("❌ Không tìm thấy sản phẩm: " + productId);
                }
            }
        }
        list.add(order);
    }
    // Phương thức xuất dữ liệu ra file - ĐÃ SỬA DÙNG GETTER
    public void exportToFile() {
        String filename = System.getProperty("user.dir") + "/resource/Order_output.txt";
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (int i = 0; i < list.size(); i++) {
                Order order = list.get(i);
                writer.println("----Đơn hàng----");
                writer.println("ID:" + order.getOrderId());
                writer.println("Khách hàng:" + order.getCustomer().getCustomerId());
                
                // Ghi chi tiết sản phẩm
                for (OrderDetail detail : order.getDetails()) {
                    writer.println("Sản phẩm:" + detail.getItem().getId() + ",Số lượng:" + detail.getQuantity());
                }
                
                // Thêm dòng trống giữa các đơn hàng (trừ đơn hàng cuối cùng)
                if (i < list.size() - 1) {
                    writer.println();
                }
            }
            
            System.out.println("✅ Đã xuất dữ liệu ra file Order_output.txt (" + list.size() + " đơn hàng)");
        } catch (IOException e) {
            System.err.println("❌ Lỗi ghi file Order_output.txt: " + e.getMessage());
        }
    }
}