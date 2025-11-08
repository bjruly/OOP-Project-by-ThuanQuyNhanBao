import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClothingManager implements chucnang<Clothing> {
    private final List<Clothing> list = new ArrayList<>();

    @Override
    public void add(Clothing item) {
        list.add(item);
    }

    @Override
    public Clothing searchById(String id) {
        for (Clothing cl : list) {
            if (cl.getId().equals(id)) {
                return cl;
            }
        }
        return null;
    }

    @Override
    public boolean deleteById(String id) {
        return list.removeIf(cl -> cl.getId().equals(id));
    }

    @Override
    public void displayAll() {
        for (Clothing cl : list) {
            cl.displayInfo();
        }
    }

    @Override
    public boolean update(String id, Clothing newItem) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(id)) {
                list.set(i, newItem);
                return true;
            }
        }
        return false;
    }

    public void loadFromFile() {
        String filename = "resource/Clothing.txt";
        list.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            String id = null, name = null, color = null, material = null, size = null;
            String brand = null, gender = null;
            int stock = 0, waist = 0, length = 0;
            double price = 0;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                if (line.startsWith("ID:")) {
                    id = line.substring(3).trim();
                } else if (line.startsWith("Tên:")) {
                    name = line.substring(4).trim();
                } else if (line.startsWith("Giá:")) {
                    try {
                        price = Double.parseDouble(line.substring(4).trim());
                    } catch (NumberFormatException e) {
                        price = 0;
                    }
                } else if (line.startsWith("Màu:")) {
                    color = line.substring(4).trim();
                } else if (line.startsWith("Chất liệu:")) {
                    material = line.substring(10).trim();
                } else if (line.startsWith("Size:")) {
                    size = line.substring(5).trim();
                } else if (line.startsWith("Tồn kho:")) {
                    stock = Integer.parseInt(line.substring(8).trim());
                } else if (line.startsWith("Thương hiệu:")) {
                    brand = line.substring(13).trim();
                } else if (line.startsWith("Giới tính:")) {
                    gender = line.substring(11).trim();
                } else if (line.startsWith("Chiều dài:")) {
                    length = Integer.parseInt(line.substring(11).trim());
                } else if (line.startsWith("E")) { // ví dụ: E32
                    try {
                        waist = Integer.parseInt(line.substring(1).trim());
                    } catch (NumberFormatException e) {
                        waist = 0;
                    }
                } else if (line.startsWith("----Sản phẩm----")) {
                    if (id != null && name != null) {
                        Clothing item = createClothingObject(id, name, price, color, material, size, stock, brand, gender, waist, length);
                        if (item != null) list.add(item);
                    }
                    // reset
                    id = name = color = material = size = brand = gender = null;
                    stock = waist = length = 0;
                    price = 0;
                }
            }

            if (id != null && name != null) {
                Clothing item = createClothingObject(id, name, price, color, material, size, stock, brand, gender, waist, length);
                if (item != null) list.add(item);
            }

            System.out.println("✅ Đã nạp dữ liệu từ Clothing.txt (" + list.size() + " sản phẩm)");
        } catch (IOException e) {
            System.err.println("❌ Lỗi đọc file Clothing.txt: " + e.getMessage());
        }
    }

    private Clothing createClothingObject(String id, String name, double price,
                                          String color, String material, String size, int stock,
                                          String brand, String gender, int waist, int length) {
        String lower = name.toLowerCase();
        if (lower.contains("khoác") || lower.contains("hoodie")) {
            return new Jacket(id, name, price, color, material, size, stock);
        } else if (lower.contains("quần")) {
            return new Pants(id, name, price, material, waist, length, brand, stock);
        } else {
            return new Shirt(id, name, price, size, color, brand, gender, stock);
        }
    }
}