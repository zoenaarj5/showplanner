package org.kavus.inyakost.ntt;

import javax.persistence.Embeddable;

@Embeddable
public class SigninData {
    protected String login;
    protected String password;
    protected String email;

    public SigninData(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }
    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("\n");
        sb.append("LOGIN:\t");
        sb.append(login);
        sb.append("\n");
        sb.append("PASSWORD:\t");
        sb.append(password);
        sb.append("\n");
        sb.append("EMAIL:\t");
        sb.append(email);
        return sb.toString();
    }
    public SigninData() {
        super();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
