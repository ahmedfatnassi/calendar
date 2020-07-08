package com.ERP.authentification.Models;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

public class Address {
    @Column(name ="city")
    private String  city;
    @Column(name ="streetName")
    private String  streetname;
    @Column(name ="streetNumber")
    private String  streetNumber;
    @NotNull
    @Size(max = 10)
    @Column(name = "postcode", length = 10, nullable = false)
    private String postcode;
    @NotNull
    @Size(max = 2)
    @Column(name = "country", length = 30, nullable = false)
    private String country;
    @OneToOne
    @JoinColumn(unique = true)
    private Person person;
}
