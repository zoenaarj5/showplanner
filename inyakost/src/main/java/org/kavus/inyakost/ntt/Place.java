package org.kavus.inyakost.ntt;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Place extends Identifiable<LDefinition> {
    @Column(name="CREATION_DATE")
    protected LocalDate creationDate;
    @Embedded
    @AttributeOverrides(
        value={
            @AttributeOverride(name="streetName",column = @Column(name="STREET_NAME")),
            @AttributeOverride(name="streetNumber",column = @Column(name="STREET_NUMBER")),
            @AttributeOverride(name="streetBox",column = @Column(name="STREET_BOX")),
            @AttributeOverride(name="poBox",column = @Column(name="PO_BOX")),
            @AttributeOverride(name="zipCode",column = @Column(name="ZIP_CODE")),
            @AttributeOverride(name="city",column = @Column(name="CITY")),
            @AttributeOverride(name="countryCode",column = @Column(name="COUNTRY_CODE"))
        }
    )
    protected Address address;

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Place() {
        super();
    }

    public Place(Definer<LDefinition> definer, LocalDate creationDate, Address address) {
        super(definer);
        this.creationDate = creationDate;
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
