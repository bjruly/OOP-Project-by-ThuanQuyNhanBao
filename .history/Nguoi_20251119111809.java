public class Nguoi
{
String ho;
String ten;
public Nguoi(){ }
public Nguoi(String h,String t) {
ho=h; ten=t;
}
public Nguoi(Nguoi ng1) {
ho=ng1.ho; ten=ng1.ten;
}
public void xuat() {
System.out.print(“Ho va ten: ” + ho + “ “ + ten);
}
} 
    
}
