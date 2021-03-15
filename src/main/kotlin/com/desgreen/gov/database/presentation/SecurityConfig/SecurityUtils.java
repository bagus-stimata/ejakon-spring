package com.desgreen.gov.database.presentation.SecurityConfig;

import com.desgreen.gov.database.data.source.local.jpa_dao.UsersRepository;
import com.desgreen.gov.database.data.source.entity.FUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * The GlobalController  Class
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 4/27/2018.
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SecurityUtils {

    @Autowired
    private UsersRepository usersRepository;

    private FUser loginUser;

    public FUser getLoginUser() {
        if (loginUser == null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            loginUser = usersRepository.findByUsername(auth.getName());
        }
        return loginUser;
    }

    /**
     *  user for ##### VAADIN FLOW ONLY #####
     */
	// static boolean isFrameworkInternalRequest(HttpServletRequest request) {
	// 	final String parameterValue = request.getParameter(ApplicationConstants.REQUEST_TYPE_PARAMETER);
	// 	return parameterValue != null
	// 			&& Stream.of(RequestType.values()).anyMatch(r -> r.getIdentifier().equals(parameterValue));
	// }

    /**
	 * Tests if some user is authenticated. As Spring Security always will create an {@link AnonymousAuthenticationToken}
	 * we have to ignore those tokens explicitly.
	 */
	static boolean isUserLoggedIn() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication != null
				&& !(authentication instanceof AnonymousAuthenticationToken)
				&& authentication.isAuthenticated();
	}
}
