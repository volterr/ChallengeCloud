package com.codegroup.challengecloud.services;

import com.codegroup.challengecloud.dao.SubscriptionDao;
import com.codegroup.challengecloud.model.Challenge;
import com.codegroup.challengecloud.model.Post;
import com.codegroup.challengecloud.model.Subscription;
import com.codegroup.challengecloud.model.User;
import com.codegroup.challengecloud.services.events.CCloudEvent;
import com.codegroup.challengecloud.services.events.SubscriptionEvent;
import com.codegroup.challengecloud.utils.Generator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Nipel-Crumple on 23.02.2015.
 */
@Service("subscriptionService")
public class SubscriptionService {
    @Autowired
    SubscriptionDao subscriptionDao;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    UserService userService;

    private static final Logger log = Logger.getLogger(SubscriptionService.class);

    public void setSubscriptionDao(SubscriptionDao subscriptionDao) {
        this.subscriptionDao = subscriptionDao;
    }

    @Transactional
    public Subscription createSubscription(User user, Challenge challenge, Date date, Set<Post> posts) {
        Subscription subscription = new Subscription();

        subscription.setId(Generator.generateId());
        subscription.setUser(user);
        subscription.setPosts(posts);
        subscription.setChallenge(challenge);
        subscription.setDate(date);

        subscriptionDao.save(subscription);

        return subscription;
    }
    
    // Created on 23.04.2015 by Vladimir Zhdanov
    @Transactional
    public Subscription createSubscription(User user, Challenge challenge, Date date) {
        Subscription subscription = new Subscription();
        subscription.setId(Generator.generateId());
        subscription.setUser(user);
        subscription.setChallenge(challenge);
        subscription.setDate(date);
        subscriptionDao.save(subscription);

        CCloudEvent event = new SubscriptionEvent(applicationContext, "User subscribed",
                user, date.getTime(), subscription);
        applicationContext.publishEvent(event);

        return subscription;
    }

    @Transactional
    public Subscription findById(String subscriptionId) {
        return subscriptionDao.findById(subscriptionId);
    }

    @Transactional
    public List<Subscription> findByUserId(String userId) {
        return subscriptionDao.findByUserId(userId);
    }

    /**
     * @return List of subscriptions of the user, that is currently logged in
     * @author Created by Andrey on 29.03 2015
     */
    @Transactional
    public List<Subscription> findForCurrentUser() {
        String userId = userService.getCurrentUser().getId();
        log.debug("Got userId: " + userId + ". About to get subscriptions");
        return subscriptionDao.findByUserId(userId);
    }
}
