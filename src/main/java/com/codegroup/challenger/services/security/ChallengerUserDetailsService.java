package com.codegroup.challenger.services.security;

import com.codegroup.challenger.constants.UserRoles;
import com.codegroup.challenger.services.UserService;
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

        com.codegroup.challenger.model.User user = userService.findByLogin(username);
        List<GrantedAuthority> authorities = buildUserAuthority(user.getRole());

        return buildUserForAuthentication(user, authorities);
    }

    // Converts User user to org.springframework.security.core.userdetails.User
    private User buildUserForAuthentication(com.codegroup.challenger.model.User user, List<GrantedAuthority> authorities) {
        return new User(user.getLogin(),
                user.getPass(),
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
