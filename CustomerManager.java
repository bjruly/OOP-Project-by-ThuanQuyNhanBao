import java.util.ArrayList;
import java.util.List;

public class CustomerManager implements chucnang<Customer> {
    private List<Customer> list = new ArrayList<>();

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
}