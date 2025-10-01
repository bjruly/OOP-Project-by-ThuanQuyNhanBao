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
    protected static int count = 0; // static attribute

    public Clothing(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        count++;
    }

    public abstract void display(); // abstract method

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}

// Shirt class
class Shirt extends Clothing implements Discountable {
    private String size;

    public Shirt(String id, String name, double price, String size) {
        super(id, name, price);
        this.size = size;
    }

    public void setSize(String size) {
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
}

// Pants class
class Pants extends Clothing implements Discountable {
    private String material;

    public Pants(String id, String name, double price, String material) {
        super(id, name, price);
        this.material = material;
    }

    public void setMaterial(String material) {
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
}

// Jacket class
class Jacket extends Clothing implements Discountable {
    private String color;

    public Jacket(String id, String name, double price, String color) {
        super(id, name, price);
        this.color = color;
    }

    public void setColor(String color) {
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
}

// Manager class
class ClothingManager {
    private List<Clothing> list = new ArrayList<>();

    // Add
    public void add(Clothing c) {
        list.add(c);
    }

    // View
    public void displayAll() {
        for (Clothing c : list) {
            c.display(); // Polymorphism
        }
    }

    // Search
    public Clothing searchById(String id) {
        for (Clothing c : list) {
            if (c.getId().equalsIgnoreCase(id)) return c;
        }
        return null;
    }

    // Delete
    public boolean deleteById(String id) {
        Clothing c = searchById(id);
        if (c != null) {
            list.remove(c);
            return true;
        }
        return false;
    }

    // Update (sửa toàn bộ thông tin)
    public boolean updateClothing(String id, String newName, double newPrice, String extra) {
        Clothing c = searchById(id);
        if (c != null) {
            c.setName(newName);
            c.setPrice(newPrice);
            if (c instanceof Shirt) {
                ((Shirt)c).setSize(extra);
            } else if (c instanceof Pants) {
                ((Pants)c).setMaterial(extra);
            } else if (c instanceof Jacket) {
                ((Jacket)c).setColor(extra);
            }
            return true;
        }
        return false;
    }

    // Save to file
    public void saveToFile(String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for (Clothing c : list) {
            writer.write(c.getClass().getSimpleName() + "," + c.getId() + "," + c.getName() + "," + c.getPrice());
            writer.newLine();
        }
        writer.close();
    }

    // Load from file
    public void loadFromFile(String filename) throws IOException {
        list.clear();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data[0].equals("Shirt")) {
                list.add(new Shirt(data[1], data[2], Double.parseDouble(data[3]), "M")); 
            } else if (data[0].equals("Pants")) {
                list.add(new Pants(data[1], data[2], Double.parseDouble(data[3]), "Cotton"));
            } else if (data[0].equals("Jacket")) {
                list.add(new Jacket(data[1], data[2], Double.parseDouble(data[3]), "Black"));
            }
        }
        reader.close();
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
            System.out.println("1. Them quan ao");
            System.out.println("2. Hien thi danh sach");
            System.out.println("3. Tim kiem theo ID");
            System.out.println("4. Xoa theo ID");
            System.out.println("5. Sua thong tin");
            System.out.println("6. Luu file");
            System.out.println("7. Doc file");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
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
                    if (type == 1) {
                        System.out.print("Nhap size: ");
                        String size = sc.nextLine();
                        manager.add(new Shirt(id, name, price, size));
                    } else if (type == 2) {
                        System.out.print("Nhap chat lieu: ");
                        String material = sc.nextLine();
                        manager.add(new Pants(id, name, price, material));
                    } else if (type == 3) {
                        System.out.print("Nhap mau: ");
                        String color = sc.nextLine();
                        manager.add(new Jacket(id, name, price, color));
                    }
                    break;
                case 2:
                    manager.displayAll();
                    break;
                case 3:
                    System.out.print("Nhap ID can tim: ");
                    String sid = sc.nextLine();
                    Clothing c = manager.searchById(sid);
                    if (c != null) c.display();
                    else System.out.println("Khong tim thay!");
                    break;
                case 4:
                    System.out.print("Nhap ID can xoa: ");
                    String did = sc.nextLine();
                    if (manager.deleteById(did)) System.out.println("Xoa thanh cong!");
                    else System.out.println("Khong tim thay!");
                    break;
                case 5:
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
                        if (manager.updateClothing(uid, newName, newPrice, extra))
                            System.out.println("Sua thanh cong!");
                    }
                    break;
                case 6:
                    manager.saveToFile("clothing.txt");
                    System.out.println("Da luu file!");
                    break;
                case 7:
                    manager.loadFromFile("clothing.txt");
                    System.out.println("Da doc file!");
                    break;
            }
        } while (choice != 0);
        sc.close();
    }
}
