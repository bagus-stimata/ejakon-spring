package com.desgreen.gov.database.presentation.view;

import com.desgreen.gov.database.presentation.SecurityConfig.PassEncoding;
import com.desgreen.gov.database.data.source.local.jpa_dao.UserRolesRepository;
import com.desgreen.gov.database.data.source.local.jpa_dao.UsersRepository;
import com.desgreen.gov.database.data.source.entity.FUser;
import com.desgreen.gov.database.data.source.entity.FUserRoles;
import com.desgreen.gov.database.domain.model_enum.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainView {
    
    private static final Logger logger = LoggerFactory.getLogger(MainView.class);

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserRolesRepository userRolesRepository;

    // @PreAuthorize("hasAnyRole({'" + Role.GUEST + "', '" + Role.USER + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = "/", method = RequestMethod.GET)
    // @ResponseBody
    public String homeIndex(){
        return "index";
        // return "my-fragments/general-fragment";
        // return "/users/modal_dialog";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    // @ResponseBody
    public String home(){
        // return "home";
        return "index";
        // return "my-fragments/general-fragment";
        // return "/users/modal_dialog";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model){
        FUser fuser = new FUser();
        fuser.setUsername("123");
        fuser.setPassword("");

        model.addAttribute("fuser", fuser );
        return "login-form/login-page";
        // return "/login-form/login";
    }


    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("reqUser", new FUser() );
        logger.info("#Form Register");
        return "login-form/signup-page";
    }    

    @RequestMapping(value = {"/register/register_proses"}, method = RequestMethod.POST)
    public String register(@ModelAttribute("reqUser") FUser reqUser,
                           final RedirectAttributes redirectAttributes) {

        logger.info("#/user/register_proses");
        
        FUser user = usersRepository.findByUsername(reqUser.getUsername() );
        if (user != null) {
            redirectAttributes.addFlashAttribute("saveUser", "exist-name");
            return "redirect:/register";
        }

        user = usersRepository.findByEmail(reqUser.getEmail());
        if (user != null) {
            redirectAttributes.addFlashAttribute("saveUser", "exist-email");
            return "redirect:/register";
        }

        reqUser.setPassword(PassEncoding.getInstance().passwordEncoder.encode(reqUser.getPassword().trim() ));
        
        List<FUserRoles> listFUserLRoles = new ArrayList<>();
        FUserRoles userRole1 = new FUserRoles();
        userRole1.setFuserBean(reqUser);
        userRole1.setRoleID(Role.USER1);
        FUserRoles userRole2 = new FUserRoles();
        userRole2.setFuserBean(reqUser);
        userRole2.setRoleID(Role.USER);

        listFUserLRoles.add(userRole1);
        listFUserLRoles.add(userRole2);

        reqUser.setFuserRoles(listFUserLRoles);

        if (usersRepository.save(reqUser) != null) {
            
            if (userRolesRepository.save(userRole1) !=null &&  
                userRolesRepository.save(userRole2) !=null) {

                redirectAttributes.addFlashAttribute("saveUser", "success");
            }


        } else {
            redirectAttributes.addFlashAttribute("saveUser", "fail");
        }

        return "redirect:/register";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "login-error/access-denied";
    }    

    @PreAuthorize("hasRole('" + Role.ADMIN + "')") 
    @GetMapping(value = "/admin/index")
    public String Admin(){
        return "admin/index";
    }
    @PreAuthorize("hasRole('" + Role.ADMIN + "')") 
    @RequestMapping(value = "/accounting/index", method = RequestMethod.GET)
    public String Accounting(){
        return "accounting/index";
    }

    @PreAuthorize("hasAnyRole({'" + Role.USER + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = "/profile/index", method = RequestMethod.GET)
    public String Profile(){
        return "profile/index";
    }
}