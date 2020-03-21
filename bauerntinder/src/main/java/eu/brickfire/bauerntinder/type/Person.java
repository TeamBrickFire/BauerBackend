package eu.brickfire.bauerntinder.type;

import org.json.simple.JSONObject;

import java.io.Serializable;

public class Person implements Serializable {

    private String id;
    private String name;
    private String firstname;
    private String email;
    private String phone;
    private String password;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name +
                ", firstname='" + firstname +
                ", email='" + email +
                ", phone='" + phone +
                '}';
    }

    public JSONObject getJSON() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("name", name);
        json.put("firstname", firstname);
        json.put("email", email);
        json.put("phone", phone);
        return json;
    }
}
