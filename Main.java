import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        ClothingManager clothingManager = new ClothingManager();
        CustomerManager customerManager = new CustomerManager();
        OrderManager orderManager = new OrderManager();

        int choice;
        do {
            System.out.println("\n========== MENU CHÍNH ==========");
            System.out.println("1. Quản lý sản phẩm");
            System.out.println("2. Quản lý khách hàng");
            System.out.println("3. Quản lý đơn hàng");
            System.out.println("0. Thoát chương trình");
            System.out.print("→ Nhập lựa chọn: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> { 
                    
                    int c1;
                    do {
                        System.out.println("\n----- MENU QUẢN LÝ SẢN PHẨM -----");
                        System.out.println("1. Thêm sản phẩm");
                        System.out.println("2. Hiển thị danh sách");
                        System.out.println("3. Tìm kiếm theo ID");
                        System.out.println("4. Xóa sản phẩm");
                        System.out.println("5. Sửa sản phẩm");
                        System.out.println("6. Đọc danh sách từ file input.txt");
                        System.out.println("7. Ghi danh sách ra file output.txt");
                        System.out.println("0. Quay lại menu chính");
                        System.out.print("→ Chọn: ");
                        c1 = sc.nextInt();
                        sc.nextLine();

                        switch (c1) {
                            case 1 -> clothingManager.themTuBanPhim(sc);
                            case 2 -> clothingManager.displayAll();
                            case 3 -> clothingManager.timTheoId(sc);
                            case 4 -> clothingManager.xoaTheoId(sc);
                            case 5 -> clothingManager.suaTheoId(sc);
                            case 6 -> clothingManager.importFromFile("input.txt");
                            case 7 -> clothingManager.exportToFile("output.txt");
                        }
                    } while (c1 != 0);
                }

                case 2 -> { 
                    int c2;
                    do {
                        System.out.println("\n----- MENU QUẢN LÝ KHÁCH HÀNG -----");
                        System.out.println("1. Thêm khách hàng");
                        System.out.println("2. Hiển thị danh sách");
                        System.out.println("3. Tìm kiếm theo ID");
                        System.out.println("4. Xóa khách hàng");
                        System.out.println("5. Sửa thông tin khách hàng");
                        System.out.println("0. Quay lại menu chính");
                        System.out.print("→ Chọn: ");
                        c2 = sc.nextInt();
                        sc.nextLine();

                        switch (c2) {
                            case 1 -> CustomerManager.add(sc);
                            case 2 -> CustomerManager.displayAll();
                            case 3 -> CustomerManager.searchById(id)(sc);
                            case 4 -> CustomerManager.deleteById(sc);
                            case 5 -> CustomerManager.update(sc);
                        }
                    } while (c2 != 0);
                }

                case 3 -> { 
                    int c3;
                    do {
                        System.out.println("\n----- MENU QUẢN LÝ ĐƠN HÀNG -----");
                        System.out.println("1. Tạo đơn hàng mới");
                        System.out.println("2. Hiển thị danh sách đơn hàng");
                        System.out.println("3. Tìm đơn hàng theo ID");
                        System.out.println("4. Xóa đơn hàng");
                        System.out.println("0. Quay lại menu chính");
                        System.out.print("→ Chọn: ");
                        c3 = sc.nextInt();
                        sc.nextLine();

                        switch (c3) {
                            case 1 -> orderManager.taoDonHang(sc, customerManager, clothingManager);
                            case 2 -> orderManager.displayAll();
                            case 3 -> orderManager.timTheoId(sc);
                            case 4 -> orderManager.xoaTheoId(sc);
                        }
                    } while (c3 != 0);
                }
            }

        } while (choice != 0);

        System.out.println("\n thoát chương trình");
        sc.close();
    }
}
