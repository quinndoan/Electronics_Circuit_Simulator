package application;

public class Resistor extends element { // Đổi tên lớp thành Resistor

    public Resistor(double value) {
        super(value); // Gọi constructor của lớp cha
    }
    
    public static void main(String[] args) {
        Resistor resistor = new Resistor(10); // Tạo một đối tượng Resistor mới
        System.out.println(resistor.getValue()); // Sử dụng phương thức getValue để lấy giá trị của resistor
    }
}
