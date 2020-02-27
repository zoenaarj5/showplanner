package org.kavus.inyakost.ntt;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Show extends DefSubject<MDefinition> {
    @Column(name="CREATION_DATE")
    protected LocalDate creationDate;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="SHOW_ID")
    protected Set<Scene> sceneSet;
    @Transient
    protected int duration;

    public Show() {
        super();
    }

    public Show(LocalDate creationDate, Set<Scene> sceneSet, int duration) {
        this.creationDate = creationDate;
        this.sceneSet = sceneSet;
        this.duration = duration;
    }

    public Show(Set<Definition> definitionSet, LocalDate creationDate, Set<Scene> sceneSet, int duration) {
        super(definitionSet);
        this.creationDate = creationDate;
        this.sceneSet = sceneSet;
        this.duration = duration;
    }

    public Set<Scene> getSceneSet() {
        return sceneSet;
    }

    public void setSceneSet(Set<Scene> sceneSet) {
        this.sceneSet = sceneSet;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
