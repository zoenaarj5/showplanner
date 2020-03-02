package org.kavus.inyakost.ntt;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Definition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    @Enumerated(EnumType.STRING)
    protected LanguageCode languageCode;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="SUBJECT_ID")
    protected Definer subject;

    public Definition() {
        super();
    }

    public Definition(LanguageCode languageCode, Definer subject) {
        this.languageCode = languageCode;
        this.subject = subject;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Definer getSubject() {
        return subject;
    }

    public void setSubject(Definer subject) {
        this.subject = subject;
    }

    public LanguageCode getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(LanguageCode languageCode) {
        this.languageCode = languageCode;
    }
}
