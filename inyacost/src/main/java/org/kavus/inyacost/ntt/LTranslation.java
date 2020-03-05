package org.kavus.inyacost.ntt;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
//@Table(name="L_TRANSLATION")
public class LTranslation extends Translation {
    public LTranslation(String title, String intro, String body) {
        this.title = title;
        this.intro = intro;
        this.body = body;
    }
    @Size(max = 150)
    protected String title;
    @Size(max = 250)
    protected String intro;
    @Size(max = 600)
    protected String body;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LTranslation() {
        super();
    }

    public LTranslation(LanguageCode languageCode, Translatable subject, @Size(max = 150) String title, @Size(max = 250) String intro, @Size(max = 600) String body) {
        super(languageCode, subject);
        this.title = title;
        this.intro = intro;
        this.body = body;
    }

    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("\nID:\t");
        sb.append(this.id);
        sb.append("\nTRANSLATABLE ID:\t");
        sb.append(translatable==null?"None":translatable.getId());
        sb.append("\nTITLE:\t");
        sb.append(this.title);
        sb.append("\nINTRO:\t");
        sb.append(this.intro);
        sb.append("\nBODY:\t");
        sb.append(this.body);
        sb.append("\n");
        return sb.toString();
    }
}
