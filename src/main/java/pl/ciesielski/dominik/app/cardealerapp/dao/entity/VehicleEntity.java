package pl.ciesielski.dominik.app.cardealerapp.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

@Entity(name = "VEHICLE")
@Table(name = "VEHICLES")
public class VehicleEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String brand;
    private String model;

    @Column(name = "YEAR_OF_PRODUCTION")
    private int yearOfProduction;

    @Column(name = "TECHNICAL_CONDITION")
    private String technicalCondition;
    private int mileage;

    @Column(name = "VIN_NUMBER", unique = true)
    private String vinNumber;

    @Column(name = "REGISTRATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    @ManyToOne
//    @JoinColumn(name = "VEHICLE_ID")
    private SellerEntity seller;

    public VehicleEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public String getTechnicalCondition() {
        return technicalCondition;
    }

    public void setTechnicalCondition(String technicalCondition) {
        this.technicalCondition = technicalCondition;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public SellerEntity getSeller() {
        return seller;
    }

    public void setSeller(SellerEntity seller) {
        this.seller = seller;
    }

    @Override
    public String toString() {
        return "VehicleEntity{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", yearOfProduction=" + yearOfProduction +
                ", technicalCondition='" + technicalCondition + '\'' +
                ", mileage=" + mileage +
                ", vinNumber='" + vinNumber + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
