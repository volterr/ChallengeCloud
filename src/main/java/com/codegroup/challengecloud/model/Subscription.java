package com.codegroup.challengecloud.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * This class is an entity 'Subscribe' which interacts
 * with MySql database through Hibernate framework
 * Created by Nipel-Crumple on 23.02.2015.
 */
@Entity
@Table( name = "subscriptions",
        schema = "challenger",
        uniqueConstraints = {
            @UniqueConstraint( columnNames = {"SUBSCRIPTION_ID"})})
public class Subscription {
    String id;
    Date date;

    User user;
    String challengeId;

    public Subscription() {

    }

    @Id
    @Column( name = "SUBSCRIPTION_ID", unique = true, nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Cannot user while there is no challengeDao
     */
    /*@ManyToOne
    @JoinColumn( name = "CHALLENGE_ID", unique = false, nullable = false)*/
    @Column( name = "CHALLENGE_ID", unique = false, nullable = false)
    public String getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(String challengeId) {
        this.challengeId = challengeId;
    }



    @Column( name = "DATE", unique = false, nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @ManyToOne annotation means that every User can have a lot of
     * subscriptions
     * Nipel-Crumple
     */
    @ManyToOne
    @JoinColumn( name = "USER_ID" , unique = false, nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}