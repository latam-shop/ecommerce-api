package com.latamshop.ecommerce.domain.model.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Address {

    @EqualsAndHashCode.Include private final AddressId id;
    private String street;
    private String city;
    private String postalCode;
    private String provinceState;
    private String country;
    private Boolean isDefault;

    private Address(
             AddressId id,
             String street,
             String city,
             String postalCode,
             String provinceState,
             String country,
             Boolean isDefault
    ){
        this.id= id;
        this.street=street;
        this.city =city;
        this.postalCode=postalCode;
        this.provinceState=provinceState;
        this.country=country;
        this.isDefault=isDefault;
    }

    public static Address registerNew(String street, String city, String postalCode, String provinceState, String country, Boolean isDefault) {
        if (city == null || city.isBlank())
            throw new IllegalArgumentException("city cannot be null or empty");
        if (country == null || country.isBlank())
            throw new IllegalArgumentException("country cannot be null or empty");

        return new Address(
                AddressId.generate() , street, city, postalCode, provinceState, country, isDefault);
    }

}
