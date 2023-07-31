package pl.ciesielski.dominik.app.cardealerapp.model;

import java.util.Date;

public class Vehicle {
    private String brand;
    private String model;
    private int yearOfProduction;
    private String technicalCondition;
    private int mileage;
    private String vinNumber;
    private Date registrationDate;

    public Vehicle(String brand, String model, int yearOfProduction, String technicalCondition, int mileage, String vinNumber, Date registrationDate) {
        this.brand = brand;
        this.model = model;
        this.yearOfProduction = yearOfProduction;
        this.technicalCondition = technicalCondition;
        this.mileage = mileage;
        this.vinNumber = vinNumber;
        this.registrationDate = registrationDate;
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
}
