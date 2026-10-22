package N1.EX1.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Client {

    private int id;
    private String name;
    private String address;
    private int phone;
    private String email;
    private LocalDate CREATED;
    private Client recommendedBy;
    private String employee;

    public Client() {}

    public Client(String name, String address, int phone, String email, Client recommendedBy, String employee) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.CREATED = LocalDate.now();
        this.recommendedBy = recommendedBy;
        this.employee = employee;
    }

    @Override
    public String toString() {
        return this.name + ", with email: " + this.email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDate getCreated() {
        return CREATED;
    }
}
