package org.kavus.inyacost.ntt;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
//@Table(name="XL_TRANSLATION")
public class XLTranslation extends Translation {
    @Size(max = 150)
    protected String title;
    @Size(max = 500)
    protected String intro;
    @Size(max = 2000)
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

    public XLTranslation() {
        super();
    }

    public XLTranslation(LanguageCode languageCode, Translatable subject, @Size(max = 150) String title, @Size(max = 500) String intro, @Size(max = 2000) String body) {
        super(languageCode, subject);
        this.title = title;
        this.intro = intro;
        this.body = body;
    }
    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("ID:\t");
        sb.append(this.id);
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
