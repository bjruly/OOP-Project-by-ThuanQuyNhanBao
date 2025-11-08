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
            System.out.println(c);
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
}