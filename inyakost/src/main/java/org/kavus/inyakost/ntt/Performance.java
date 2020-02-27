package org.kavus.inyakost.ntt;

import javax.persistence.*;

@Entity
public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    @ManyToOne
    @JoinColumn(name="ROLE_ID")
    protected Role role;
    @ManyToOne
    @JoinColumn(name="PERFORMER_ID")
    protected Member performer;
    @ManyToOne
    @JoinColumn(name="SCENE_ID")
    protected Scene scene;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Member getPerformer() {
        return performer;
    }

    public void setPerformer(Member performer) {
        this.performer = performer;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Performance() {
        super();
    }

    public Performance(Role role, Member performer, Scene scene) {
        this.role = role;
        this.performer = performer;
        this.scene = scene;
    }
}
