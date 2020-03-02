package org.kavus.inyakost.ntt;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name="PASSWORD_CHANGE_REQUEST")
public class PasswordChangeRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MEMBER_ID")
    protected Member member;
    @Column(name="SENDER_IP_ADDRESS")
    @Size(max = 48)
    protected String senderIPAddress;
    @Column(name="SENDING_DATE")
    protected LocalDateTime sendingDate;
    @Column(name="CONFIRMATION_DATE")
    protected LocalDateTime confirmationDate;
    @Column(name="DENYING_DATE")
    protected LocalDateTime denyingDate;
    @Column(name="EXECUTION_DATE")
    protected LocalDateTime executionDate;
    @Column(name="CANCELLING_DATE")
    protected LocalDateTime cancellingDate;

    public PasswordChangeRequest() {
        super();
    }

    public PasswordChangeRequest(long id, Member member, @Size(max = 48) String senderIPAddress, LocalDateTime sendingDate) {
        this.id = id;
        this.member = member;
        this.senderIPAddress = senderIPAddress;
        this.sendingDate = sendingDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getSenderIPAddress() {
        return senderIPAddress;
    }

    public void setSenderIPAddress(String senderIPAddress) {
        this.senderIPAddress = senderIPAddress;
    }

    public LocalDateTime getSendingDate() {
        return sendingDate;
    }

    public void setSendingDate(LocalDateTime sendingDate) {
        this.sendingDate = sendingDate;
    }

    public LocalDateTime getConfirmationDate() {
        return confirmationDate;
    }

    public void setConfirmationDate(LocalDateTime confirmationDate) {
        this.confirmationDate = confirmationDate;
    }

    public LocalDateTime getDenyingDate() {
        return denyingDate;
    }

    public void setDenyingDate(LocalDateTime denyingDate) {
        this.denyingDate = denyingDate;
    }

    public LocalDateTime getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(LocalDateTime executionDate) {
        this.executionDate = executionDate;
    }

    public LocalDateTime getCancellingDate() {
        return cancellingDate;
    }

    public void setCancellingDate(LocalDateTime cancellingDate) {
        this.cancellingDate = cancellingDate;
    }
}
