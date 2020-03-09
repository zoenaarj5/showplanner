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
    @JoinColumn(name="TRANSLATOR_ID")
    protected Translator translator;

    public long getId() {
        return id;
    }

    public Translation() {
        super();
    }

    public Translation(LanguageCode languageCode, Translator translator) {
        this.languageCode = languageCode;
        this.translator = translator;
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

    public Translator getTranslator() {
        return translator;
    }

    public void setTranslator(Translator translator) {
        this.translator = translator;
    }
}
