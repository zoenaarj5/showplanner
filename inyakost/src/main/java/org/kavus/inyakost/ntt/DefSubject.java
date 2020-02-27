package org.kavus.inyakost.ntt;

import javax.persistence.*;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class DefSubject<T extends Definition> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="DEFINITION_ID")
    protected Set<Definition> definitionSet;

    public DefSubject() {
        super();
    }

    public DefSubject(Set<Definition> definitionSet) {
        this.definitionSet = definitionSet;
        updateDefinition();
    }

    public Set<Definition> getDefinitionSet() {
        return definitionSet;
    }

    public void setDefinitionSet(Set<Definition> definitionSet) {
        this.definitionSet = definitionSet;
        updateDefinition();
    }

    public Definition getDefinition() {
        return definition;
    }

    public void setDefinition(Definition definition) {
        this.definition = definition;
    }

    @Transient
    protected Definition definition;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void updateDefinition(){
        if(definitionSet!=null) {
            for (Definition def : definitionSet) {
                if (def.getLanguageCode() == LanguageCode.getCurrentLanguageCode()) {
                    setDefinition(def);
                    break;
                }
            }
        }
    }
    @Override
    public String toString(){
        if(definition==null){
            return null;
        }else{
            return definition.toString();
        }
    }
}
