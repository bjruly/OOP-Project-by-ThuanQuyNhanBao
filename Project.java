import java.io.*;
import java.util.*;

// Interface
interface Discountable {
    double getDiscountedPrice(double percent);
}

// Abstract class
abstract class Clothing {
    protected String id;
    protected String name;
    protected double price;
    protected static int count = 0;

    public Clothing(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        count++;
    }

    public abstract void display();

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
}

// Shirt
class Shirt extends Clothing implements Discountable {
    private String size;
    public Shirt(String id, String name, double price, String size) {
        super(id, name, price);
        this.size = size;
    }
    @Override
    public void display() {
        System.out.println("Shirt - ID: " + id + ", Name: " + name + ", Price: " + price + ", Size: " + size);
    }
    @Override
    public double getDiscountedPrice(double percent) {
        return price * (1 - percent / 100);
    }
    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }
}

// Pants
class Pants extends Clothing implements Discountable {
    private String material;
    public Pants(String id, String name, double price, String material) {
        super(id, name, price);
        this.material = material;
    }
    @Override
    public void display() {
        System.out.println("Pants - ID: " + id + ", Name: " + name + ", Price: " + price + ", Material: " + material);
    }
    @Override
    public double getDiscountedPrice(double percent) {
        return price * (1 - percent / 100);
    }
    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }
}

// Jacket
class Jacket extends Clothing implements Discountable {
    private String color;
    public Jacket(String id, String name, double price, String color) {
        super(id, name, price);
        this.color = color;
    }
    @Override
    public void display() {
        System.out.println("Jacket - ID: " + id + ", Name: " + name + ", Price: " + price + ", Color: " + color);
    }
    @Override
    public double getDiscountedPrice(double percent) {
        return price * (1 - percent / 100);
    }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
}

// Manager
class ClothingManager {
    private List<Clothing> list = new ArrayList<>();

    public void add(Clothing c) { list.add(c); }

    public void displayAll() {
        if (list.isEmpty()) {
            System.out.println("Danh sach rong!");
        } else {
            for (Clothing c : list) c.display();
        }
    }

    public Clothing searchById(String id) {
        for (Clothing c : list) {
            if (c.getId().equalsIgnoreCase(id)) return c;
        }
        return null;
    }

    public boolean deleteById(String id) {
        Clothing c = searchById(id);
        if (c != null) {
            list.remove(c);
            return true;
        }
        return false;
    }

    public boolean updateClothing(String id, String newName, double newPrice, String extra) {
        Clothing c = searchById(id);
        if (c != null) {
            c.setName(newName);
            c.setPrice(newPrice);
            if (c instanceof Shirt) ((Shirt) c).setSize(extra);
            else if (c instanceof Pants) ((Pants) c).setMaterial(extra);
            else if (c instanceof Jacket) ((Jacket) c).setColor(extra);
            return true;
        }
        return false;
    }

    // 📤 Xuất danh sách ra file
    public void exportToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Clothing c : list) {
                if (c instanceof Shirt s)
                    writer.write("Shirt," + s.getId() + "," + s.getName() + "," + s.getPrice() + "," + s.getSize());
                else if (c instanceof Pants p)
                    writer.write("Pants," + p.getId() + "," + p.getName() + "," + p.getPrice() + "," + p.getMaterial());
                else if (c instanceof Jacket j)
                    writer.write("Jacket," + j.getId() + "," + j.getName() + "," + j.getPrice() + "," + j.getColor());
                writer.newLine();
            }
        }
    }

    // 📥 Đọc danh sách từ file input.txt
    public void importFromFile(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("⚠️ File input.txt chua ton tai!");
            return;
        }
        list.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 5) continue;
                String type = data[0];
                String id = data[1];
                String name = data[2];
                double price = Double.parseDouble(data[3]);
                String extra = data[4];
                switch (type) {
                    case "Shirt" -> list.add(new Shirt(id, name, price, extra));
                    case "Pants" -> list.add(new Pants(id, name, price, extra));
                    case "Jacket" -> list.add(new Jacket(id, name, price, extra));
                }
            }
        }
    }

    public List<Clothing> getList() {
        return list;
    }
}

// Main
public class Project {
    public static void main(String[] args) throws IOException {
        ClothingManager manager = new ClothingManager();
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n===== MENU QUAN LY QUAN AO =====");
            System.out.println("1. Them quan ao (tu dong ghi vao file input.txt)");
            System.out.println("2. Hien thi danh sach");
            System.out.println("3. Tim kiem theo ID");
            System.out.println("4. Xoa theo ID");
            System.out.println("5. Sua thong tin");
            System.out.println("6. Xuat danh sach ra file output.txt");
            System.out.println("7. Doc danh sach tu file input.txt");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("1. Ao - 2. Quan - 3. Ao khoac");
                    int type = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nhap ID: ");
                    String id = sc.nextLine();
                    System.out.print("Nhap ten: ");
                    String name = sc.nextLine();
                    System.out.print("Nhap gia: ");
                    double price = sc.nextDouble();
                    sc.nextLine();

                    Clothing c = null;
                    if (type == 1) {
                        System.out.print("Nhap size: ");
                        String size = sc.nextLine();
                        c = new Shirt(id, name, price, size);
                    } else if (type == 2) {
                        System.out.print("Nhap chat lieu: ");
                        String material = sc.nextLine();
                        c = new Pants(id, name, price, material);
                    } else if (type == 3) {
                        System.out.print("Nhap mau: ");
                        String color = sc.nextLine();
                        c = new Jacket(id, name, price, color);
                    }

                    if (c != null) {
                        manager.add(c);
                        // 🔥 Tự động ghi danh sách mới vào file input.txt
                        manager.exportToFile("input.txt");
                        System.out.println("✅ Da them va cap nhat file input.txt!");
                    }
                }
                case 2 -> manager.displayAll();
                case 3 -> {
                    System.out.print("Nhap ID can tim: ");
                    String sid = sc.nextLine();
                    Clothing c = manager.searchById(sid);
                    if (c != null) c.display();
                    else System.out.println("Khong tim thay!");
                }
                case 4 -> {
                    System.out.print("Nhap ID can xoa: ");
                    String did = sc.nextLine();
                    if (manager.deleteById(did)) {
                        System.out.println("Xoa thanh cong!");
                        manager.exportToFile("input.txt"); // Cập nhật lại file input.txt
                    } else System.out.println("Khong tim thay!");
                }
                case 5 -> {
                    System.out.print("Nhap ID can sua: ");
                    String uid = sc.nextLine();
                    Clothing cc = manager.searchById(uid);
                    if (cc == null) {
                        System.out.println("Khong tim thay!");
                    } else {
                        System.out.print("Nhap ten moi: ");
                        String newName = sc.nextLine();
                        System.out.print("Nhap gia moi: ");
                        double newPrice = sc.nextDouble();
                        sc.nextLine();
                        String extra = "";
                        if (cc instanceof Shirt) {
                            System.out.print("Nhap size moi: ");
                            extra = sc.nextLine();
                        } else if (cc instanceof Pants) {
                            System.out.print("Nhap chat lieu moi: ");
                            extra = sc.nextLine();
                        } else if (cc instanceof Jacket) {
                            System.out.print("Nhap mau moi: ");
                            extra = sc.nextLine();
                        }
                        if (manager.updateClothing(uid, newName, newPrice, extra)) {
                            System.out.println("Sua thanh cong!");
                            manager.exportToFile("input.txt"); // Cập nhật file input.txt sau khi sửa
                        }
                    }
                }
                case 6 -> {
                    manager.exportToFile("output.txt");
                    System.out.println("✅ Da xuat danh sach ra file output.txt!");
                }
                case 7 -> {
                    manager.importFromFile("input.txt");
                    System.out.println("✅ Da doc danh sach tu file input.txt!");
                }
            }
        } while (choice != 0);
        sc.close();
    }
}
