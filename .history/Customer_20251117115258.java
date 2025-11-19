public class Customer {
    
    protected String customerId;
    protected String phone;
    
    public Customer(String customerId, String phone){
        this.customerId = customerId;
        this.phone = phone;
    }
    public void displayCustomer(){
        System.out.println("----Khách Hàng----");
        System.out.println("Id:" + this.customerId);
        System.out.println("Số điện thoại:" + this.phone);
    }
    public String getCustomerId(){
        return this.customerId;
    }
    public String getPhone() {
        return this.phone;
    }
}