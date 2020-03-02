package org.kavus.inyakost.ntt;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Set;

@Entity
public class Scene extends Identifiable<LDefinition> {
    @ManyToOne(cascade =CascadeType.ALL)
    @JoinColumn(name="SHOW_ID")
    protected Show show;

    public Scene(Show show, int duration) {
        this.show = show;
        this.duration = duration;
    }

    public Scene() {
        super();
    }

    public Scene(Definer<LDefinition> definer, Show show, int duration) {
        super(definer);
        this.show = show;
        this.duration = duration;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    protected int duration;
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

}
