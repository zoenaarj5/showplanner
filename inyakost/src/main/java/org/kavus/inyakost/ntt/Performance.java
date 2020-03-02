package org.kavus.inyakost.ntt;

import javax.persistence.*;

@Entity
public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SCHEDULED_SHOW_ID")
    protected ScheduledShow scheduledShow;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="ROLE_ID")
    protected Role role;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="PERFORMER_ID")
    protected Member performer;
    @ManyToOne(cascade = CascadeType.ALL)
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

    public ScheduledShow getScheduledShow() {
        return scheduledShow;
    }

    public void setScheduledShow(ScheduledShow scheduledShow) {
        this.scheduledShow = scheduledShow;
    }

    public Performance() {
        super();
    }

    public Performance(ScheduledShow scheduledShow, Role role, Member performer, Scene scene) {
        this.scheduledShow = scheduledShow;
        this.role = role;
        this.performer = performer;
        this.scene = scene;
    }
}
