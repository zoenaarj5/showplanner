package org.kavus.inyakost.ntt;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="SCHEDULED_SHOW")
public class ScheduledShow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    @ManyToOne
    @JoinColumn(name="EVENT_ID")
    protected Event event;
    @ManyToOne
    @JoinColumn(name="SHOW_ID")
    protected Show show;
    protected LocalDateTime startingTime;
    protected LocalDateTime endingTime;

    public ScheduledShow() {
        super();
    }

    public ScheduledShow(Event event, Show show, LocalDateTime startingTime, LocalDateTime endingTime) {
        this.event = event;
        this.show = show;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(LocalDateTime startingTime) {
        this.startingTime = startingTime;
    }

    public LocalDateTime getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(LocalDateTime endingTime) {
        this.endingTime = endingTime;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }
}
