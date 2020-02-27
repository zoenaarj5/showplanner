package org.kavus.inyakost.ntt;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Event extends DefSubject<LDefinition>{
    @ManyToOne
    @JoinColumn(name="PLACE_ID")
    protected Place place;
    @OneToMany(fetch = FetchType.EAGER)
    protected Set<ScheduledShow> scheduledShowSet;

    public Place getPlace() {
        return place;
    }

    public Event() {
    }

    public Event(Place place, Set<ScheduledShow> scheduledShowSet) {
        this.place = place;
        this.scheduledShowSet = scheduledShowSet;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Set<ScheduledShow> getScheduledShowSet() {
        return scheduledShowSet;
    }

    public void setScheduledShowSet(Set<ScheduledShow> scheduledShowSet) {
        this.scheduledShowSet = scheduledShowSet;
    }
}
