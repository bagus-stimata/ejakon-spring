package com.desgreen.gov.database.presentation.SecurityConfig;

import com.desgreen.gov.database.data.source.local.jpa_dao.UsersRepository;
import com.desgreen.gov.database.data.source.entity.FUser;
import com.desgreen.gov.database.data.source.entity.FUserRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 *  Ini akan disamngkan dengan spring security SystemConfiguration
 */
@Service
public class AuthUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(AuthUserDetailsService.class);

    @Autowired
    private UsersRepository usersRepository;
 
    private org.springframework.security.core.userdetails.User springUser;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        FUser user = getUserDetail(username);
        if (user != null) {
            springUser = new org.springframework.security.core.userdetails.User(user.getUsername(),
                    user.getPassword(),
                    enabled,
                    accountNonExpired,
                    credentialsNonExpired,
                    accountNonLocked,
                    getAuthorities(user)
                    // getAuthorities(user.getFuserRoles())
            );
            return springUser;
        } else {
            springUser = new org.springframework.security.core.userdetails.User("empty",
                    "empty",
                    false,
                    true,
                    true,
                    false,
                    getAuthorities(3)
            );
            return springUser;
        }
    }

    public List<GrantedAuthority> getAuthorities(Integer role) {

        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        if (role == 1) {
            authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else if (role == 2) {
            authList.add(new SimpleGrantedAuthority("ROLE_USER"));
        } else if (role == 3) {
            authList.add(new SimpleGrantedAuthority("ROLE_GUEST"));
        }

        return authList;
    }

    public List<GrantedAuthority> getAuthorities(FUser fuser) {

        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        /**
         * Spring Security Selalu membutuhkan SimplleGranted Autority menggunanakn awalan ROLE_
         * sedangkan vaadin harus sama persis
         * Sehingga diambil jalan tengahnya menggunakan awalal ROLE_ untuk 
         * Enumerasi Role
         */

        for (FUserRoles userRole: fuser.getFuserRoles()) {
            if (userRole.getRoleID().contains("ROLE_") ) {
                authList.add(new SimpleGrantedAuthority( userRole.getRoleID()));
            }else {
             authList.add(new SimpleGrantedAuthority("ROLE_" + userRole.getRoleID()));
            }
        }
       
        return authList;
    }

    private FUser getUserDetail(String usernameOrEmail) {
        FUser user = usersRepository.findByUsername(usernameOrEmail);
        if (user ==null) {
            user = usersRepository.findByEmail(usernameOrEmail);
        }

        if (user == null) {
            logger.warn("user '" + usernameOrEmail + "' on null!");
        } else {
            logger.info(user.toString());
        }
        return user;
    }

    

}
