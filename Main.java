import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.setProperty("file.encoding", "UTF-8");
        Scanner sc = new Scanner(System.in, "UTF-8");
         
        ClothingManager clothingManager = new ClothingManager();
        CustomerManager customerManager = new CustomerManager();
        OrderManager orderManager = new OrderManager(clothingManager, customerManager);

        clothingManager.loadFromFile();
        customerManager.loadFromFile();
        orderManager.loadFromFile();

        while (true) {
            System.out.println("===== QUẢN LÝ SHOP QUẦN ÁO =====");
            System.out.println("1. Quản lý sản phẩm");
            System.out.println("2. Quản lý khách hàng");
            System.out.println("3. Quản lý đơn hàng");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    manageClothing(sc, clothingManager);
                    break;
                case "2":
                    manageCustomer(sc, customerManager);
                    break;
                case "3":
                    manageOrder(sc, orderManager, clothingManager, customerManager);
                    break;
                case "0":
                    System.out.println("Thoát chương trình.");
                    sc.close();
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void manageClothing(Scanner sc, ClothingManager cm) {
        while (true) {
            System.out.println("\n--- QUẢN LÝ SẢN PHẨM ---");
            System.out.println("1. Xem tất cả sản phẩm");
            System.out.println("2. Thêm sản phẩm");
            System.out.println("3. Xóa sản phẩm theo ID");
            System.out.println("4. Tìm sản phẩm theo ID");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            String c = sc.nextLine();
            switch (c) {
                case "1":
                    cm.displayAll();
                    break;
                case "2":
                     System.out.print("Nhập ID đơn hàng: ");
                    String orderId = sc.nextLine();
                    System.out.print("Nhập ID khách hàng: ");
                    String custId = sc.nextLine();
                    Customer cust = custM.searchById(custId);
                    if (cust == null) {
                        System.out.println("Không tìm thấy khách hàng.");
                        break;
                    }
                    Order order = new Order(orderId, cust);
                    System.out.print("Nhập ID sản phẩm: ");
                    String prodId = sc.nextLine();
                    Clothing item = cm.searchById(prodId);
                    if (item == null) {
                        System.out.println("Không tìm thấy sản phẩm.");
                        break;
                    }
                    System.out.print("Nhập số lượng: ");
                    int qty = Integer.parseInt(sc.nextLine());
                    order.addOrderDetail(item, qty);
                    om.add(order);
                    System.out.println("Đã thêm đơn hàng.");
                    break;
                case "3":
                    System.out.print("Nhập ID sản phẩm cần xóa: ");
                    String delId = sc.nextLine();
                    if (cm.deleteById(delId)) System.out.println("Đã xóa sản phẩm!");
                    else System.out.println("Không tìm thấy sản phẩm.");
                    break;
                case "4":
                    System.out.print("Nhập ID sản phẩm cần tìm: ");
                    String searchId = sc.nextLine();
                    Clothing item = cm.searchById(searchId);
                    if (item != null) item.displayInfo();
                    else System.out.println("Không tìm thấy sản phẩm.");
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
    private static void manageCustomer(Scanner sc, CustomerManager cm) {
        while (true) {
            System.out.println("\n--- QUẢN LÝ KHÁCH HÀNG ---");
            System.out.println("1. Xem tất cả khách hàng");
            System.out.println("2. Thêm khách hàng");
            System.out.println("3. Xóa khách hàng theo ID");
            System.out.println("4. Tìm khách hàng theo ID");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            String c = sc.nextLine();
            switch (c) {
                case "1":
                    cm.displayAll();
                    break;
                case "2":
                    System.out.print("Nhập ID: ");
                    String id = sc.nextLine();
                    System.out.print("Nhập số điện thoại: ");
                    String phone = sc.nextLine();
                    System.out.print("Có phải thành viên (y/n)? ");
                    String member = sc.nextLine();
                    if (member.equalsIgnoreCase("y")) {
                        System.out.print("Nhập điểm tích lũy: ");
                        int points = Integer.parseInt(sc.nextLine());
                        cm.add(new MemberCustomer(id, phone, points));
                    } else {
                        cm.add(new Customer(id, phone));
                    }
                    System.out.println("Đã thêm khách hàng.");
                    break;
                case "3":
                    System.out.print("Nhập ID khách hàng cần xóa: ");
                    String delId = sc.nextLine();
                    if (cm.deleteById(delId)) System.out.println("Đã xóa khách hàng!");
                    else System.out.println("Không tìm thấy khách hàng.");
                    break;
                case "4":
                    System.out.print("Nhập ID khách hàng cần tìm: ");
                    String searchId = sc.nextLine();
                    Customer customer = cm.searchById(searchId);
                    if (customer != null) customer.displayCustomer();
                    else System.out.println("Không tìm thấy khách hàng.");
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
    private static void manageOrder(Scanner sc, OrderManager om, ClothingManager cm, CustomerManager custM) {
        while (true) {
            System.out.println("\n--- QUẢN LÝ ĐƠN HÀNG ---");
            System.out.println("1. Xem tất cả đơn hàng");
            System.out.println("2. Thêm đơn hàng");
            System.out.println("3. Xóa đơn hàng theo ID");
            System.out.println("4. Tìm đơn hàng theo ID");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            String c = sc.nextLine();
            switch (c) {
                case "1":
                    om.displayAll();
                    break;
                case "2":
                    System.out.print("Nhập ID đơn hàng: ");
                    String orderId = sc.nextLine();
                    System.out.print("Nhập ID khách hàng: ");
                    String custId = sc.nextLine();
                    Customer cust = custM.searchById(custId);
                    if (cust == null) {
                        System.out.println("Không tìm thấy khách hàng.");
                        break;
                    }
                    Order order = new Order(orderId, cust);
                    System.out.print("Nhập ID sản phẩm: ");
                    String prodId = sc.nextLine();
                    Clothing item = cm.searchById(prodId);
                    if (item == null) {
                        System.out.println("Không tìm thấy sản phẩm.");
                        break;
                    }
                    System.out.print("Nhập số lượng: ");
                    int qty = Integer.parseInt(sc.nextLine());
                    order.addOrderDetail(item, qty);
                    om.add(order);
                    System.out.println("Đã thêm đơn hàng.");
                    break;
                case "3":
                    System.out.print("Nhập ID đơn hàng cần xóa: ");
                    String delId = sc.nextLine();
                    if (om.deleteById(delId)) System.out.println("Đã xóa đơn hàng!");
                    else System.out.println("Không tìm thấy đơn hàng.");
                    break;
                case "4":
                    System.out.print("Nhập ID đơn hàng cần tìm: ");
                    String searchId = sc.nextLine();
                    Order o = om.searchById(searchId);
                    if (o != null) o.displayOrder();
                    else System.out.println("Không tìm thấy đơn hàng.");
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}

