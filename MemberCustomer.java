public class MemberCustomer extends Customer {
    private final int loyaltyPoints;
    public MemberCustomer(String customerId, String phone, int loyaltyPoints){
        super(customerId, phone);
        this.loyaltyPoints = loyaltyPoints;
    }
    @Override
    public void displayCustomer(){
        System.out.println("----Khách Hàng----");
        System.out.println("Id:" + this.customerId);
        System.out.println("Số điện thoại:" + this.phone);
        System.out.println("Điểm tích lũy:" + this.loyaltyPoints);
    }
}
