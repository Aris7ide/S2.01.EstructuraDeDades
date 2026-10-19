package N1.EX1.models;

import java.time.LocalDate;

public class Sale {

    private int id;
    private Glasses glasses;
    private Client client;
    private Employee employee;
    private LocalDate date;

    public Sale() {}

    public Sale(Glasses glasses, Client client, Employee employee) {
        this.glasses = glasses;
        this.client = client;
        this.employee = employee;
        this.date = LocalDate.now();
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Glasses getGlasses() {
        return glasses;
    }

    public void setGlasses(Glasses glasses) {
        this.glasses = glasses;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
