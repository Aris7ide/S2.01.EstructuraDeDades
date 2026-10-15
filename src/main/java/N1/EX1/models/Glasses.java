package N1.EX1.models;

public class Glasses {

    private String brand;
    private double graduationLeft;
    private double graduationRight;
    private String type;
    private String color;
    private String colorLeft;
    private String colorRight;
    private double price;
    private Provider provider;

    public Glasses(String brand, double graduationLeft, double graduationRight, String type,
                   String color, String colorLeft, String colorRight, double price, Provider provider) {
        this.brand = brand;
        this.graduationLeft = graduationLeft;
        this.graduationRight = graduationRight;
        this.type = type;
        this.color = color;
        this.colorLeft = colorLeft;
        this.colorRight = colorRight;
        this.price = price;
        this.provider = provider;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getGraduationLeft() {
        return graduationLeft;
    }

    public void setGraduationLeft(double graduationLeft) {
        this.graduationLeft = graduationLeft;
    }

    public double getGraduationRight() {
        return graduationRight;
    }

    public void setGraduationRight(double graduationRight) {
        this.graduationRight = graduationRight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColorLeft() {
        return colorLeft;
    }

    public void setColorLeft(String colorLeft) {
        this.colorLeft = colorLeft;
    }

    public String getColorRight() {
        return colorRight;
    }

    public void setColorRight(String colorRight) {
        this.colorRight = colorRight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
}
