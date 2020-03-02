package org.kavus.inyakost.ntt;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Identifiable<T extends Definition> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DEF_SUBJECT_ID")
    protected Definer<T> definer;

    public Identifiable() {
    }

    public Identifiable(Definer<T> definer) {
        this.definer = definer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Definer<T> getDefiner() {
        return definer;
    }

    public void setDefiner(Definer<T> definer) {
        this.definer = definer;
    }
    public void updateDefinition(){
        if(definer!=null){
            definer.updateDefinition();
        }
    }
    @Override
    public String toString(){
        return definer==null?null: definer.toString();
    }
 }
