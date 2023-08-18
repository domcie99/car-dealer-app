package pl.ciesielski.dominik.app.cardealerapp.controller.model;

public class AddressBuilder {
    private long id;
    private String street;
    private String city;
    private String zipCode;
    private String country;

    public AddressBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public AddressBuilder setStreet(String street) {
        this.street = street;
        return this;
    }

    public AddressBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    public AddressBuilder setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public AddressBuilder setCountry(String country) {
        this.country = country;
        return this;
    }

    public Address build() {
        if (street == null || city == null || zipCode == null || country == null) {
            throw new IllegalStateException("Street, city, zipCode and country are required.");
        }

        return new Address(id, street, city, zipCode, country);
    }
}
