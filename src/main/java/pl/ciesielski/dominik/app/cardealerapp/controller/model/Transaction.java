package pl.ciesielski.dominik.app.cardealerapp.controller.model;

import java.util.Date;

public class Transaction {
    private long id;
    private Vehicle vehicle;
    private Client client;
    private double price;
    private Date transactionDate;

    public Transaction(long id, Vehicle vehicle, Client client, double price, Date transactionDate) {
        this.id = id;
        this.vehicle = vehicle;
        this.client = client;
        this.price = price;
        this.transactionDate = transactionDate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}
