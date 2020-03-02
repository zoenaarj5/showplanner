package org.kavus.inyakost.ntt;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name="MEMBER_GROUP")
public class MemberGroup extends Identifiable<MDefinition> {
    @Column(unique = true)
    @Size(max = 100)
    protected String name;
    protected LocalDateTime creationDate;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name="GROUP_HAS_MEMBER")
    protected Set<Member> memberSet;
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Member> getMemberSet() {
        return memberSet;
    }

    public void setMemberSet(Set<Member> memberSet) {
        this.memberSet = memberSet;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public MemberGroup() {
        super();
    }

    public MemberGroup(@Size(max = 100) String name, LocalDateTime creationDate) {
        this.name = name;
        this.creationDate = creationDate;
    }

    public MemberGroup(@Size(max = 100) String name, LocalDateTime creationDate, Set<Member> memberSet) {
        this.name = name;
        this.creationDate = creationDate;
        this.memberSet = memberSet;
    }
}
