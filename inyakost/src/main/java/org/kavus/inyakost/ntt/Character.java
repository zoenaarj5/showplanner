package org.kavus.inyakost.ntt;

import javax.persistence.Entity;
import java.util.Set;

@Entity
public class Character extends DefSubject<MDefinition> {
    public Character() {
        super();
    }

    public Character(Set<Definition> definitionSet) {
        super(definitionSet);
    }
}
