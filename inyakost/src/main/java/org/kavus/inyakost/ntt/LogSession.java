package org.kavus.inyakost.ntt;
import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name="LOG_SESSION")
public class LogSession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="MEMBER_ID")
    protected Member member;
    @Column(name="LOGGING_TIME")
    protected LocalDateTime loggingTime;
    @Column(name="IP_ADDRESS")
    protected String ipAddress;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public LocalDateTime getLoggingTime() {
        return loggingTime;
    }

    public void setLoggingTime(LocalDateTime loggingTime) {
        this.loggingTime = loggingTime;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
