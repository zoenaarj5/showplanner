package org.kavus.inyakost.ntt;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Place extends DefSubject<LDefinition> {
    @Column(name="CREATION_DATE")
    protected LocalDate creationDate;

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Place() {
        super();
    }

    public Place(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
