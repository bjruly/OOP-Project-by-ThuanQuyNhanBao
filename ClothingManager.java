import java.util.List;
import java.util.ArrayList;
public class ClothingManager implements chucnang<Clothing>
{
    private final List<Clothing> list = new ArrayList<>();

    @Override
    public void add(Clothing item) 
    {
        list.add(item);
    }
    @Override
    public Clothing searchById(String id) 
    {
        for (Clothing cl : list) {
            if (cl.getId().equals(id)) 
            {
                return cl;
            }
        }
        return null;
    }
    @Override
    public boolean deleteById(String id) 
    {
        return list.removeIf(cl -> cl.getId().equals(id));
    }
    @Override
    public void displayAll() 
    {
        for (Clothing cl : list) {
            System.out.println(cl);
        }
    }
}
