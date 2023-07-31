package pl.ciesielski.dominik.app.cardealerapp.model;

import java.util.Date;

public class Transaction {
    private Vehicle vehicle;
    private Client client;
    private double price;
    private Date transactionDate;

    public Transaction(Vehicle vehicle, Client client, double price, Date transactionDate) {
        this.vehicle = vehicle;
        this.client = client;
        this.price = price;
        this.transactionDate = transactionDate;
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
