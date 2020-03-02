package org.kavus.inyakost.ntt;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    @Embedded
    @AttributeOverrides(value = {
        @AttributeOverride(name="firstName",column = @Column(name="FIRST_NAME")),
        @AttributeOverride(name="lastName",column = @Column(name="LAST_NAME")),
        @AttributeOverride(name="birthDate",column = @Column(name="BIRTH_DATE")),
        @AttributeOverride(name="sex",column = @Column(name="SEX"))
    })
    protected PersonalData personalData;
    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "login",column = @Column(name="LOGIN")),
            @AttributeOverride(name = "password",column = @Column(name="PASSWORD"))
    })
    protected SigninData signinData;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name="GROUP_HAS_MEMBER")
    protected Set<MemberGroup> memberGroupSet;
    @Override
    public String toString(){
        return personalData.toString()+signinData.toString();
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }

    public SigninData getSigninData() {
        return signinData;
    }

    public void setSigninData(SigninData signinData) {
        this.signinData = signinData;
    }

    public Member() {
        super();
    }

    public Member(PersonalData personalData, SigninData signinData, Set<MemberGroup> memberGroupSet) {
        this.personalData = personalData;
        this.signinData = signinData;
        this.memberGroupSet = memberGroupSet;
    }

    public Member(PersonalData personalData, SigninData signinData) {
        this.personalData = personalData;
        this.signinData = signinData;
    }

    public Set<MemberGroup> getMemberGroupSet() {
        return memberGroupSet;
    }

    public void setMemberGroupSet(Set<MemberGroup> memberGroupSet) {
        this.memberGroupSet = memberGroupSet;
    }
}
