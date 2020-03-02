package org.kavus.inyakost.ntt;

import javax.persistence.Entity;

@Entity
public class Character extends Identifiable<MDefinition> {
    public Character() {
        super();
    }

    public Character(Definer<MDefinition> definer) {
        super(definer);
    }
}
