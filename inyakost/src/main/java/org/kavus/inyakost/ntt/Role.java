package org.kavus.inyakost.ntt;

import javax.persistence.*;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    @ManyToOne
    @JoinColumn(name="SHOW_ID")
    protected Show show;
    @ManyToOne
    @JoinColumn(name="CHARACTER_ID")
    protected Character character;

    public Role() {
        super();
    }

    public Role(Show show, Character character) {
        this.show = show;
        this.character = character;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }
}
