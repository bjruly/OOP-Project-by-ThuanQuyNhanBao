public interface chucnang<T> 
{
    void add(T item);                        // Thêm một đối tượng kiểu T
    T searchById(String id);                // Tìm kiếm đối tượng theo ID
    boolean deleteById(String id);          // Xóa đối tượng theo ID
    void displayAll();                      // Hiển thị tất cả đối tượng
}