package com.codegroup.challengecloud.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Max on 24.01.2015.
 */
@Entity
@Table(name="users", catalog = "challenger", uniqueConstraints = {
        @UniqueConstraint(columnNames = "EMAIL"),
        @UniqueConstraint(columnNames = "LOGIN") })
public class User implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String id;
    String login;
    String pass;
    String email;
    String name;
    /**
     * 1st bit = User
     * 2nd bit = Admin
     * 3rd bit = Moderator
     */
    Integer role;



    Set<Subscription> subscriptions;

    public User() {
    }

    @Id
    @Column(name = "USER_ID", unique = true, nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "LOGIN", unique = true, nullable = false)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "PASS")
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Column(name = "EMAIL", nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "NAME", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "ROLES", nullable = false)
    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @Transient
    public boolean isUser(){
        return (role&1)!=0;
    }

    @Transient
    public boolean isAdmin(){
        return (role&2)!=0;
    }

    @Transient
    public boolean isModerator(){
        return (role&4)!=0;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", role=" + role +
                '}';
    }

    /**
     * This method returns Set of Subscriptions of the User.
     * Using @OneToMany annotation means that every user can have
     * several subscriptions at the same time.
<<<<<<< HEAD
     * @return Set of subscriptions
     * mappedby = 'field name' on the other side (see here: Subscription field called "user")
=======
     * @return Set of subscriptions 
>>>>>>> ea87d5f31ab3f271bd2b2d3da79334ac4fdb0ffd
     * Nipel-Crumple
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public Set<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Set<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }
}
