package org.kavus.inyacost.ntt;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Translatable <T extends Translation>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    @OneToOne
    @JoinColumn(name = "TRANSLATOR_ID")
    protected Translator<T> translator=new Translator();

    public Translator<T> getTranslator() {
        return translator;
    }

    public void setTranslator(Translator<T> translator) {
        this.translator = translator;
    }

    public long getId() {
        return id;
    }
    public Translatable() {
        super();
    }
    public void setId(long id) {
        this.id = id;
    }
    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder("\n. . . PRINTING TRANSLATABLE . . .\n\n<<< TRANSLATABLE ID="+id);
        sb.append(translator.toString());
        return sb.toString();
    }
}
