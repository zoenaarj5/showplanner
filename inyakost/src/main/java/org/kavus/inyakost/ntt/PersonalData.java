package org.kavus.inyakost.ntt;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Embeddable
public class PersonalData {
    protected String firstName;
    protected String lastName;
    protected LocalDate birthDate;

    public PersonalData() {
        super();
    }

    public PersonalData(String firstName, String lastName, LocalDate birthDate, Sex sex) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.sex = sex;
    }
    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("\n");
        sb.append("FIRST NAME:\t");
        sb.append(firstName);
        sb.append("\n");
        sb.append("LAST NAME:\t");
        sb.append(lastName);
        sb.append("\n");
        sb.append("BIRTH DATE:\t");
        sb.append(birthDate);
        sb.append("\n");
        sb.append("SEX:\t");
        sb.append(sex);
        sb.append("\n");
        return sb.toString();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    @Enumerated(EnumType.STRING)
    protected Sex sex;
}
