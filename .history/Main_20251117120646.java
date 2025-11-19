import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

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
                case "1" -> manageClothing(sc, clothingManager);
                case "2" -> manageCustomer(sc, customerManager);
                case "3" -> manageOrder(sc, orderManager, clothingManager, customerManager);
                case "0" -> {
                    System.out.println("Thoát chương trình.");
                    sc.close();
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ!");
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
            System.out.println("5. Cập nhật thông tin sản phẩm");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            String c = sc.nextLine();

            switch (c) {
                case "1" -> cm.displayAll();

                case "2" -> addClothing(sc, cm);

                case "3" -> {
                    System.out.print("Nhập ID sản phẩm cần xóa: ");
                    String delId = sc.nextLine();
                    if (cm.deleteById(delId)) System.out.println("Đã xóa sản phẩm!");
                    else System.out.println("Không tìm thấy sản phẩm.");
                }

                case "4" -> {
                    System.out.print("Nhập ID sản phẩm cần tìm: ");
                    String searchId = sc.nextLine();
                    Clothing item = cm.searchById(searchId);
                    if (item != null) item.displayInfo();
                    else System.out.println("Không tìm thấy sản phẩm.");
                }

                case "5" -> updateClothing(sc, cm);

                case "0" -> {
                    return;
                }

                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void addClothing(Scanner sc, ClothingManager cm) {
        System.out.print("Nhập ID sản phẩm: ");
        String id = sc.nextLine();

        System.out.print("Nhập tên sản phẩm: ");
        String name = sc.nextLine();

        System.out.print("Nhập giá sản phẩm: ");
        double price;
        try {
            price = Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Giá không hợp lệ, mặc định = 0");
            price = 0;
        }

        String lowerName = name.toLowerCase();
        if (lowerName.contains("khoác")) {
            System.out.print("Nhập màu: ");
            String color = sc.nextLine();
            System.out.print("Nhập chất liệu: ");
            String material = sc.nextLine();
            System.out.print("Nhập size: ");
            String size = sc.nextLine();
            System.out.print("Nhập số lượng tồn kho: ");
            int stock = Integer.parseInt(sc.nextLine());
            cm.add(new Jacket(id, name, price, color, material, size, stock));
        } else if (lowerName.contains("quần")) {
            System.out.print("Nhập chất liệu: ");
            String material = sc.nextLine();
            System.out.print("Nhập vòng eo (waist): ");
            int waist = Integer.parseInt(sc.nextLine());
            System.out.print("Nhập chiều dài: ");
            int length = Integer.parseInt(sc.nextLine());
            System.out.print("Nhập thương hiệu: ");
            String brand = sc.nextLine();
            System.out.print("Nhập số lượng tồn kho: ");
            int stock = Integer.parseInt(sc.nextLine());
            cm.add(new Pants(id, name, price, material, waist, length, brand, stock));
        } else {
            System.out.print("Nhập size: ");
            String size = sc.nextLine();
            System.out.print("Nhập màu: ");
            String color = sc.nextLine();
            System.out.print("Nhập thương hiệu: ");
            String brand = sc.nextLine();
            System.out.print("Nhập giới tính: ");
            String gender = sc.nextLine();
            System.out.print("Nhập số lượng tồn kho: ");
            int stock = Integer.parseInt(sc.nextLine());
            cm.add(new Shirt(id, name, price, size, color, brand, gender, stock));
        }

        System.out.println(" Đã thêm sản phẩm mới!");
    }

    private static void updateClothing(Scanner sc, ClothingManager cm) {
        System.out.print("Nhập ID sản phẩm cần cập nhật: ");
        String id = sc.nextLine();
        Clothing oldItem = cm.searchById(id);
        if (oldItem == null) {
            System.out.println(" Không tìm thấy sản phẩm!");
            return;
        }

        System.out.println("Thông tin hiện tại:");
        oldItem.displayInfo();
        System.out.println("\n--- Nhập thông tin mới ---");

        System.out.print("Tên mới: ");
        String name = sc.nextLine();
        System.out.print("Giá mới: ");
        double price;
        try {
            price = Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Giá không hợp lệ, mặc định = 0");
            price = 0;
        }

        Clothing newItem;
        String lowerName = name.toLowerCase();
        if (lowerName.contains("khoác")) {
            System.out.print("Màu: ");
            String color = sc.nextLine();
            System.out.print("Chất liệu: ");
            String material = sc.nextLine();
            System.out.print("Size: ");
            String size = sc.nextLine();
            System.out.print("Số lượng tồn kho: ");
            int stock = Integer.parseInt(sc.nextLine());
            newItem = new Jacket(id, name, price, color, material, size, stock);
        } else if (lowerName.contains("quần")) {
            System.out.print("Chất liệu: ");
            String material = sc.nextLine();
            System.out.print("Vòng eo: ");
            int waist = Integer.parseInt(sc.nextLine());
            System.out.print("Chiều dài: ");
            int length = Integer.parseInt(sc.nextLine());
            System.out.print("Thương hiệu: ");
            String brand = sc.nextLine();
            System.out.print("Số lượng tồn kho: ");
            int stock = Integer.parseInt(sc.nextLine());
            newItem = new Pants(id, name, price, material, waist, length, brand, stock);
        } else {
            System.out.print("Size: ");
            String size = sc.nextLine();
            System.out.print("Màu: ");
            String color = sc.nextLine();
            System.out.print("Thương hiệu: ");
            String brand = sc.nextLine();
            System.out.print("Giới tính: ");
            String gender = sc.nextLine();
            System.out.print("Số lượng tồn kho: ");
            int stock = Integer.parseInt(sc.nextLine());
            newItem = new Shirt(id, name, price, size, color, brand, gender, stock);
        }

        if (cm.update(id, newItem)) System.out.println("✅ Đã cập nhật sản phẩm!");
        else System.out.println(" Lỗi cập nhật!");
    }

    private static void manageCustomer(Scanner sc, CustomerManager cm) {
        while (true) {
            System.out.println("\n--- QUẢN LÝ KHÁCH HÀNG ---");
            System.out.println("1. Xem tất cả khách hàng");
            System.out.println("2. Thêm khách hàng");
            System.out.println("3. Xóa khách hàng theo ID");
            System.out.println("4. Tìm khách hàng theo ID");
            System.out.println("5. Cập nhật thông tin khách hàng");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            String c = sc.nextLine();

            switch (c) {
                case "1" -> cm.displayAll();

                case "2" -> {
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
                }

                case "3" -> {
                    System.out.print("Nhập ID khách hàng cần xóa: ");
                    String delId = sc.nextLine();
                    if (cm.deleteById(delId)) System.out.println("Đã xóa khách hàng!");
                    else System.out.println("Không tìm thấy khách hàng.");
                }

                case "4" -> {
                    System.out.print("Nhập ID khách hàng cần tìm: ");
                    String searchId = sc.nextLine();
                    Customer customer = cm.searchById(searchId);
                    if (customer != null) customer.displayCustomer();
                    else System.out.println("Không tìm thấy khách hàng.");
                }

                case "5" -> updateCustomer(sc, cm);

                case "0" -> {
                    return;
                }

                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void updateCustomer(Scanner sc, CustomerManager cm) {
        System.out.print("Nhập ID khách hàng cần cập nhật: ");
        String id = sc.nextLine();
        Customer c = cm.searchById(id);
        if (c == null) {
            System.out.println(" Không tìm thấy khách hàng!");
            return;
        }

        System.out.println("Thông tin hiện tại:");
        c.displayCustomer();

        System.out.println("\n--- Nhập thông tin mới ---");
        System.out.print("Nhập số điện thoại mới: ");
        String phone = sc.nextLine();

        System.out.print("Có phải thành viên (y/n)? ");
        String member = sc.nextLine();

        Customer newC;
        if (member.equalsIgnoreCase("y")) {
            System.out.print("Nhập điểm tích lũy: ");
            int points = Integer.parseInt(sc.nextLine());
            newC = new MemberCustomer(id, phone, points);
        } else {
            newC = new Customer(id, phone);
        }

        if (cm.update(id, newC)) System.out.println(" Đã cập nhật khách hàng!");
        else System.out.println(" Lỗi cập nhật!");
    }

    private static void manageOrder(Scanner sc, OrderManager om, ClothingManager cm, CustomerManager custM) {
        while (true) {
            System.out.println("\n--- QUẢN LÝ ĐƠN HÀNG ---");
            System.out.println("1. Xem tất cả đơn hàng");
            System.out.println("2. Thêm đơn hàng");
            System.out.println("3. Xóa đơn hàng theo ID");
            System.out.println("4. Tìm đơn hàng theo ID");
            System.out.println("5. Cập nhật đơn hàng");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            String c = sc.nextLine();

            switch (c) {
                case "1" -> om.displayAll();

                case "2" -> {
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
                }

                case "3" -> {
                    System.out.print("Nhập ID đơn hàng cần xóa: ");
                    String delId = sc.nextLine();
                    if (om.deleteById(delId)) System.out.println("Đã xóa đơn hàng!");
                    else System.out.println("Không tìm thấy đơn hàng.");
                }

                case "4" -> {
                    System.out.print("Nhập ID đơn hàng cần tìm: ");
                    String searchId = sc.nextLine();
                    Order o = om.searchById(searchId);
                    if (o != null) o.displayOrder();
                    else System.out.println("Không tìm thấy đơn hàng.");
                }

                case "5" -> updateOrder(sc, om, cm, custM);

                case "0" -> {
                    return;
                }

                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void updateOrder(Scanner sc, OrderManager om, ClothingManager cm, CustomerManager custM) {
        System.out.print("Nhập ID đơn hàng cần cập nhật: ");
        String id = sc.nextLine();
        Order oldOrder = om.searchById(id);
        if (oldOrder == null) {
            System.out.println(" Không tìm thấy đơn hàng!");
            return;
        }

        System.out.println("Thông tin hiện tại:");
        oldOrder.displayOrder();

        System.out.println("\n--- Nhập thông tin mới ---");
        System.out.print("Nhập ID khách hàng mới: ");
        String custId = sc.nextLine();
        Customer cust = custM.searchById(custId);
        if (cust == null) {
            System.out.println("Không tìm thấy khách hàng.");
            return;
        }

        System.out.print("Nhập ID sản phẩm mới: ");
        String prodId = sc.nextLine();
        Clothing item = cm.searchById(prodId);
        if (item == null) {
            System.out.println("Không tìm thấy sản phẩm.");
            return;
        }

        System.out.print("Nhập số lượng mới: ");
        int qty = Integer.parseInt(sc.nextLine());

        Order newOrder = new Order(id, cust);
        newOrder.addOrderDetail(item, qty);

        if (om.update(id, newOrder)) System.out.println(" Đã cập nhật đơn hàng!");
        else System.out.println(" Lỗi cập nhật!");
    }
}
