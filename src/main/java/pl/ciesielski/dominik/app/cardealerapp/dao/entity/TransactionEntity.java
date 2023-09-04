package pl.ciesielski.dominik.app.cardealerapp.dao.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "TRANSACTIONS")
public class TransactionEntity {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "vehicle_id")
    private VehicleEntity vehicle;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    @Column(name = "PRICE")
    private double price;

    @Column(name = "TRANSACTION_DATE")
    private LocalDate transactionDate;

    public TransactionEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public VehicleEntity getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleEntity vehicle) {
        this.vehicle = vehicle;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public String toString() {
        return "TransactionEntity{" +
                "id=" + id +
                ", vehicle=" + vehicle +
                ", client=" + client +
                ", price=" + price +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
