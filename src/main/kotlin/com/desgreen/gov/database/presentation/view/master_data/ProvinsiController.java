package com.desgreen.gov.database.presentation.view.master_data;

import com.desgreen.gov.database.presentation.SecurityConfig.SecurityUtils;
import com.desgreen.gov.database.data.source.local.jpa_dao.TbKabupatenRepository;
import com.desgreen.gov.database.data.source.local.jpa_dao.TbProvinsiRepository;
import com.desgreen.gov.database.data.source.entity.TbDesa;
import com.desgreen.gov.database.data.source.entity.TbProvinsi;
import com.desgreen.gov.database.domain.model_enum.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * The TodoController  Class
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 4/27/2018.
 */
@Controller
@ComponentScan
public class ProvinsiController {

    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private TbProvinsiRepository provinsiRepository;

    @Autowired
    private TbKabupatenRepository kabupatenRepository;

   
    @Autowired
    private SecurityUtils securityUtils;


    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping("/master/provinsi")
    public String listIndex(final Model viewModel) {

        // final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

  
        final TbProvinsi newDomain =new TbProvinsi();
        viewModel.addAttribute("newDomain", newDomain);
        // viewModel.addAttribute("allTask", usersRepository.findByUserIdStatus(securityUtils.getLoginUser().getId(), Status.ACTIVE.getValue()));
        viewModel.addAttribute("allData", provinsiRepository.findAll());

        logger.info("# Form Task");
      
        return "master/provinsi/provinsi_list"; 
    }


    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = {"/master/provinsi_form/save_process"}, method = RequestMethod.POST)
    public String saveProcess(@ModelAttribute("domain") final TbProvinsi domain, @ModelAttribute("domainDetil1") final TbDesa domainDetil1, final RedirectAttributes redirectAttributes) {
        if (domain.getTempInt1()==0) {
            TbProvinsi newDomain = new TbProvinsi();

            newDomain.setTempInt1(domain.getTempInt1()); //0.New Form, 1.Edit Form, 3.Delete

            newDomain.setKode1(domain.getKode1());
            newDomain.setDescription(domain.getDescription());


            // //Sementara otomatis
            // List<TbProvinsiRoles> listTbProvinsiLRoles = new ArrayList<>();
            // TbProvinsiRoles userRole1 = new TbProvinsiRoles();
            // userRole1.setFuserBean(newDomain);
            // userRole1.setRoleID(Role.USER);
            // TbProvinsiRoles userRole2 = new TbProvinsiRoles();
            // userRole2.setFuserBean(newDomain);
            // userRole2.setRoleID(Role.ADMIN);
    
            // listTbProvinsiLRoles.add(userRole1);
            // listTbProvinsiLRoles.add(userRole2);
    
            // newDomain.setFuserRoles(listTbProvinsiLRoles);
    
            if (provinsiRepository.save(newDomain) != null) {
                
                // if (provinsiRepository.save(userRole1) !=null &&  
                // provinsiRepository.save(userRole2) !=null) {
                //         redirectAttributes.addFlashAttribute("saveUser", "success");
                // }
                redirectAttributes.addFlashAttribute("saveUser", "success");
    
    
            } else {
                redirectAttributes.addFlashAttribute("saveUser", "fail");
            }

    
            return "redirect:/master/provinsi/edit_form/" + newDomain.getId();

        }else if (domain.getTempInt1()==1) {
            TbProvinsi domainUpdate = provinsiRepository.findById(domain.getId() );
            domainUpdate.setTempInt1(domain.getTempInt1()); //0.New Form, 1.Edit Form, 3.Delete

            domainUpdate.setKode1(domain.getKode1());
            domainUpdate.setDescription(domain.getDescription());

            provinsiRepository.save(domainUpdate);

            redirectAttributes.addFlashAttribute("saveUser", "success");
            return "redirect:/master/provinsi/edit_form/" + domain.getId();
        }
       
        
        return "redirect:/master/provinsi/";
    }


    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = "/master/provinsi/{operation}/{id}", method = RequestMethod.GET)
    public String toFormOperation(@PathVariable("operation") final String operation,
                                @PathVariable("id") final int id, final RedirectAttributes redirectAttributes,
                                final Model model) {

        logger.info("/master/provinsi/operation: {} ", operation);

       if (operation.equals("delete")) {
            try {
                TbProvinsi domainToDelete = provinsiRepository.findById(id);        
                // hati-hati provinsiRepository.deleteAll(domainToDelete.getListDesa() );
                provinsiRepository.delete(domainToDelete);               
                redirectAttributes.addFlashAttribute("msg", "del");
                redirectAttributes.addFlashAttribute("msgText", " Deleted permanently");
            } catch (Exception e) {
                    redirectAttributes.addFlashAttribute("msg", "del_fail");
                    redirectAttributes.addFlashAttribute("msgText", " Task could not deleted. Please try later");
                e.printStackTrace();
            }
           
        } else if (operation.equals("new_form")) {
            TbProvinsi newDomain = new TbProvinsi();
            if (newDomain != null) {
                newDomain.setTempInt1(0); //0.New Form, 1.Edit Form, 3.Delete
                model.addAttribute("domain", newDomain);                
                return "master/provinsi/provinsi_form";
            }

        } else if (operation.equals("edit_form")) {

            TbProvinsi domain = provinsiRepository.findById(id);
            if (domain != null) {
                domain.setTempInt1(1); //0.New Form, 1.Edit Form, 3.Delete
                //Password tidak bisa di decode ya?


                // TbProvinsiRoles domainDetil1 = new TbProvinsiRoles();
                // TbProvinsiRoles domainDetil2 = new TbProvinsiRoles();
                // // for (TbProvinsiRoles domainDetil: domain.getFuserRoles()) {
                // //     domainDetil1 = domainDetil; 
                // // }

                model.addAttribute("domain", domain);                

                // model.addAttribute("domainDetil1", domainDetil1);                
                // model.addAttribute("domainDetil2", domainDetil2);                

                // model.addAttribute("allDataDetil", domain.getFuserRoles());                

                return "master/provinsi/provinsi_form";
            } else {
                redirectAttributes.addFlashAttribute("msg", "notfound");
            }
        }

        return "redirect:/master/provinsi/";
    }


}
