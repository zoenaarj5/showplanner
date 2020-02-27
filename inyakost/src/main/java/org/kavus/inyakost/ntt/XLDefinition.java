package org.kavus.inyakost.ntt;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="XL_DEFINITION")
public class XLDefinition extends Definition {
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

    public XLDefinition() {
        super();
    }

    public XLDefinition(LanguageCode languageCode, @Size(max = 150) String title, @Size(max = 500) String intro, @Size(max = 2000) String body) {
        super(languageCode);
        this.title = title;
        this.intro = intro;
        this.body = body;
    }
}
