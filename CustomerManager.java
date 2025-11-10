import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CustomerManager implements chucnang<Customer> 
{
    private final List<Customer> list = new ArrayList<>();

    @Override
    public void add(Customer customer) 
    {
        list.add(customer);
    }

    @Override
    public Customer searchById(String id) 
    {
        for (Customer c : list) {
            if (c.getCustomerId().equals(id)) 
            {
                return c;
            }
        }
        return null;
    }

    @Override
    public boolean deleteById(String id) 
    {
        return list.removeIf(c -> c.getCustomerId().equals(id));
    }

    @Override
    public void displayAll() 
    {
        for (Customer c : list) {
            c.displayCustomer();
            System.out.println(); // Thêm dòng trống giữa các khách hàng
        }
    }

    @Override
    public boolean update(String id, Customer newItem) 
    {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCustomerId().equals(id)) 
            {
                list.set(i, newItem);
                return true;
            }
        }
        return false;
    }

    // Phương thức đọc dữ liệu từ file - ĐÃ CẬP NHẬT ĐỂ HỖ TRỢ MemberCustomer
    public void loadFromFile() {
        String filename = "resource/Customer.txt";
        list.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            String customerId = null;
            String phone = null;
            int loyaltyPoints = 0;
            boolean isMember = false;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                if (line.startsWith("ID:")) {
                    customerId = line.substring(3).trim();
                } else if (line.startsWith("SĐT:")) {
                    phone = line.substring(4).trim();
                } else if (line.startsWith("Điểm tích lũy:")) {
                    loyaltyPoints = Integer.parseInt(line.substring(14).trim());
                    isMember = true;
                } else if (line.startsWith("----Khách hàng----")) {
                    if (customerId != null && phone != null) {
                        Customer customer;
                        if (isMember) {
                            customer = new MemberCustomer(customerId, phone, loyaltyPoints);
                        } else {
                            customer = new Customer(customerId, phone);
                        }
                        list.add(customer);
                    }
                    // reset
                    customerId = null;
                    phone = null;
                    loyaltyPoints = 0;
                    isMember = false;
                }
            }

            // Thêm khách hàng cuối cùng nếu có
            if (customerId != null && phone != null) {
                Customer customer;
                if (isMember) {
                    customer = new MemberCustomer(customerId, phone, loyaltyPoints);
                } else {
                    customer = new Customer(customerId, phone);
                }
                list.add(customer);
            }

            System.out.println("✅ Đã nạp dữ liệu từ Customer.txt (" + list.size() + " khách hàng)");
        } catch (IOException e) {
            System.err.println("❌ Lỗi đọc file Customer.txt: " + e.getMessage());
        }
    }

    // Phương thức xuất dữ liệu ra file 
    public void exportToFile() {
        String filename = "resource/Customer_output.txt";
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (int i = 0; i < list.size(); i++) {
                Customer customer = list.get(i);
                writer.println("----Khách hàng----");
                writer.println("ID:" + customer.getCustomerId());
                writer.println("SĐT:" + customer.getPhone());
                
                // Ghi điểm tích lũy nếu là MemberCustomer
                if (customer instanceof MemberCustomer member) {
                    writer.println("Điểm tích lũy:" + member.getLoyaltyPoints());
                }
                
                // Thêm dòng trống giữa các khách hàng (trừ khách hàng cuối cùng)
                if (i < list.size() - 1) {
                    writer.println();
                }
            }
            
            System.out.println("✅ Đã xuất dữ liệu ra file Customer_output.txt (" + list.size() + " khách hàng)");
        } catch (IOException e) {
            System.err.println("❌ Lỗi ghi file Customer_output.txt: " + e.getMessage());
        }
    }
}