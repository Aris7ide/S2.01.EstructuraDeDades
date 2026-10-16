package N1.EX1.models;

import java.time.LocalDateTime;

public class Client {

    private String name;
    private String address;
    private int phone;
    private String email;
    private final LocalDateTime CREATED;
    private Client recommendedBy;
    private String employee;

    public Client(String email, int phone, String address, String name, String employee, Client recommendedBy) {
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.name = name;
        this.employee = employee;
        this.recommendedBy = recommendedBy;
        this.CREATED = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Client getRecommendedBy() {
        return recommendedBy;
    }

    public void setRecommendedBy(Client recommendedBy) {
        this.recommendedBy = recommendedBy;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public LocalDateTime getCreated() {
        return CREATED;
    }
}
