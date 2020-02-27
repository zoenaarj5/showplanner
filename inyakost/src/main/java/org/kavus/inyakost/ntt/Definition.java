package org.kavus.inyakost.ntt;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Definition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    @Enumerated(EnumType.STRING)
    protected LanguageCode languageCode;

    public Definition() {
        super();
    }

    public Definition(LanguageCode languageCode) {
        this.languageCode = languageCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LanguageCode getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(LanguageCode languageCode) {
        this.languageCode = languageCode;
    }
}
