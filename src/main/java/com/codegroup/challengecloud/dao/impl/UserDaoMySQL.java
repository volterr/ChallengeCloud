package com.codegroup.challengecloud.dao.impl;

import com.codegroup.challengecloud.dao.UserDao;
import com.codegroup.challengecloud.model.Challenge;
import com.codegroup.challengecloud.model.User;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Max on 24.01.2015.
 */
@Repository("userDao")
public class UserDaoMySQL extends HibernateDao implements UserDao {

	private static final Logger log = Logger.getLogger(UserDaoMySQL.class);
	
    @Override
    public void save(User user) {
    	log.debug("saving user " + user.getId());
        getSession().save(user);
    }

    @Override
    public void update(User user) {
    	log.debug("updating user " + user.getId());
        getSession().update(user);
    }

    @Override
    public void delete(User user) {
    	log.debug("removing user " + user.getId());
        getSession().delete(user);
    }

    @Override
    public User findById(String id) {
    	log.debug("looking for user by id = " + id);
        List list = find("from User where user_id = ?", id);
        return (User) list.get(0);
    }

    @Override
    public User findByLogin(String login) {
    	log.debug("looking for user by login = " + login);
        List list = find("from User where login = ?", login);
        if (list.size() > 0) {
            return (User) list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public User findByEmail(String email) throws IndexOutOfBoundsException {
        log.debug("looking for user by email = " + email);
        /**
         * In this query from ... there should be name of the Class which
         * maps given table.
         * For example, we should write 'from User..'(case-sensitive) because User is our Entity class.
         * The same with Subscription (in SubscriptionDaoMySQL)
         * @author Nipel-Crumple
         */
        List list = find("from User where email = ?", email);
        if (list.size() != 0){
            return (User) list.get(0);
        } else {
            return null;
        }
    }

    /**
     * Created on 07.04.2015 by Vladimir Zhdanov
     */
	@Override
	public List<User> findAll() {
    	log.debug("looking for all users");
        List list = find("from User");
        return (List<User>) list;
	}
}
