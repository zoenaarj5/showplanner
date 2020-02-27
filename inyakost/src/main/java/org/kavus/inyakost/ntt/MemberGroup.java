package org.kavus.inyakost.ntt;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class MemberGroup extends DefSubject<MDefinition> {
    @Column(unique = true)
    @Size(max = 100)
    protected String name;
    protected LocalDateTime creationDate;
    @ManyToMany(fetch = FetchType.EAGER)
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
