package pl.ciesielski.dominik.app.cardealerapp.dao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "VEHICLES")
public class VehicleEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String brand;
    private String model;
    private int yearOfProduction;
    private String technicalCondition;
    private int mileage;
    private String vinNumber;
    private Date registrationDate;

    public VehicleEntity() {

    }
}
