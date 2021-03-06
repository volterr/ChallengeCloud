package com.codegroup.challengecloud.services.security;

import com.codegroup.challengecloud.constants.UserRoles;
import com.codegroup.challengecloud.services.UserService;

import com.codegroup.challengecloud.utils.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Max on 25.01.2015.
 */
@Service("userDetailsService")
public class ChallengerUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        com.codegroup.challengecloud.model.User user = userService.findByLogin(username);
        return convertUserToUserDetails(user);
    }

    public UserDetails loadUserByEmail(final String email) {
        com.codegroup.challengecloud.model.User user = userService.findByEmail(email);
        return convertUserToUserDetails(user);
    }
    public UserDetails loadUserById(final String id){
        com.codegroup.challengecloud.model.User user = userService.findById(id);
        return convertUserToUserDetails(user);
    }
    
    private UserDetails convertUserToUserDetails(com.codegroup.challengecloud.model.User user){
    	List<GrantedAuthority> authorities = buildUserAuthority(user.getRole());
        return buildUserForAuthentication(user, authorities);
    }

    // Converts User user to org.springframework.security.core.userdetails.User
    private User buildUserForAuthentication(com.codegroup.challengecloud.model.User user, List<GrantedAuthority> authorities) {

        //By Yefim
    	if (user.getPassword() == null) {
        	user.setPassword(Generator.generateRandomPass());
        }
    	return new User(user.getId(),
                user.getPassword(),
                true, true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Integer userRole) {
        List<GrantedAuthority> result = new ArrayList<GrantedAuthority>();
        // Build user's authorities
        for(String role :UserRoles.getSetOfRolesById(userRole))
            result.add(new SimpleGrantedAuthority(role));
        return result;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
