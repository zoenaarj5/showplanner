package org.kavus.inyacost.ntt;

import javax.persistence.*;
@Entity
public class Scene extends Translatable<LTranslation>{
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="SHOW_ID")
    protected Show show;
    public Scene() {
        super();
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public Scene(Show show) {
        this.show = show;
    }

    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder(super.toString());
        sb.append("\n");
        sb.append("Scene from show:\t");
        if(show==null){
            sb.append("Show not defined");
        }else {
            LTranslation sTran = show.getTranslation();
            sb.append(sTran == null ? show.getId() : sTran.getTitle());
            sb.append("\n");
        }
        return sb.toString();
    }
}
