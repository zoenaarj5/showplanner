package org.kavus.inyacost.ntt;

import javax.persistence.*;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Translation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    @Column(name="LANGUAGE_CODE")
    @Enumerated(EnumType.STRING)
    protected LanguageCode languageCode;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="TRANSLATABLE_ID")
    protected Translatable translatable;

    public long getId() {
        return id;
    }

    public Translation() {
        super();
    }

    public Translation(LanguageCode languageCode, Translatable translatable) {
        this.languageCode = languageCode;
        this.translatable = translatable;
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

    public Translatable getTranslatable() {
        return translatable;
    }

    public void setTranslatable(Translatable translatable) {
        this.translatable = translatable;
    }
}
