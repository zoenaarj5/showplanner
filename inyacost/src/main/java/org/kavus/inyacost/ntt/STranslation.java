package org.kavus.inyacost.ntt;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
//@Table(name="S_TRANSLATION")
public class STranslation extends Translation {
    @Size(max = 100)
    protected String name;
    @Size(max = 200)
    protected String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public STranslation() {
        super();
    }

    public STranslation(LanguageCode languageCode, Translatable subject, @Size(max = 100) String name, @Size(max = 200) String description) {
        super(languageCode, subject);
        this.name = name;
        this.description = description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("ID\n:\t");
        sb.append(this.id);
        sb.append("\nNAME:\t");
        sb.append(this.name);
        sb.append("\nDESCRIPTION:\t");
        sb.append(this.description);
        sb.append("\n");
        return sb.toString();
    }
}
