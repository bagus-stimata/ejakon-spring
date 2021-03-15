package com.desgreen.gov.database.presentation.view.master_data;

import com.desgreen.gov.database.presentation.SecurityConfig.SecurityUtils;
import com.desgreen.gov.database.data.source.local.jpa_dao.TbFungsiJalanRepository;
import com.desgreen.gov.database.data.source.entity.TbFungsiJalan;
import com.desgreen.gov.database.domain.model_enum.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
public class FungsiJalanController {

    private static final Logger logger = LoggerFactory.getLogger(FungsiJalanController.class);


    @Autowired
    private TbFungsiJalanRepository jenisJalanRepository;
    

    @Autowired
    private SecurityUtils securityUtils;


    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping("/master/fungsijalan")
    public String listIndex(final Model viewModel) {

        // final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

  
        final TbFungsiJalan newDomain =new TbFungsiJalan();
        viewModel.addAttribute("newDomain", newDomain);
        // viewModel.addAttribute("allTask", usersRepository.findByUserIdStatus(securityUtils.getLoginUser().getId(), Status.ACTIVE.getValue()));
        viewModel.addAttribute("allData", jenisJalanRepository.findAll());

        logger.info("# Form Task");
      
        return "master/fungsijalan/fungsijalan_list"; 
    }


    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = {"/master/fungsijalan_form/save_process"}, method = RequestMethod.POST)
    public String saveProcess(@ModelAttribute("domain") final TbFungsiJalan domain, @ModelAttribute("domainDetil1") final TbFungsiJalan domainDetil1, final RedirectAttributes redirectAttributes) {
        if (domain.getTempInt1()==0) {
            TbFungsiJalan newDomain = new TbFungsiJalan();

            newDomain.setTempInt1(domain.getTempInt1()); //0.New Form, 1.Edit Form, 3.Delete

            newDomain.setKode1(domain.getKode1());
            newDomain.setDescription(domain.getDescription());


            // //Sementara otomatis
            // List<TbJenisJalanRoles> listTbJenisJalanLRoles = new ArrayList<>();
            // TbJenisJalanRoles userRole1 = new TbJenisJalanRoles();
            // userRole1.setFuserBean(newDomain);
            // userRole1.setRoleID(Role.USER);
            // TbJenisJalanRoles userRole2 = new TbJenisJalanRoles();
            // userRole2.setFuserBean(newDomain);
            // userRole2.setRoleID(Role.ADMIN);
    
            // listTbJenisJalanLRoles.add(userRole1);
            // listTbJenisJalanLRoles.add(userRole2);
    
            // newDomain.setFuserRoles(listTbJenisJalanLRoles);
    
            if (jenisJalanRepository.save(newDomain) != null) {
                
                redirectAttributes.addFlashAttribute("saveUser", "success");
                newDomain.setTempInt1(1);

    
            } else {
                redirectAttributes.addFlashAttribute("saveUser", "fail");
            }

    
            return "redirect:/master/fungsijalan/edit_form/" + newDomain.getId();

        }else if (domain.getTempInt1()==1) {
            TbFungsiJalan domainUpdate = jenisJalanRepository.findById(domain.getId() );
            domainUpdate.setTempInt1(domain.getTempInt1()); //0.New Form, 1.Edit Form, 3.Delete

            domainUpdate.setKode1(domain.getKode1());
            domainUpdate.setDescription(domain.getDescription());

            jenisJalanRepository.save(domainUpdate);

            redirectAttributes.addFlashAttribute("saveUser", "success");
            return "redirect:/master/fungsijalan/edit_form/" + domain.getId();
        }
       
        
        return "redirect:/master/fungsijalan/";
    }


    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = "/master/fungsijalan/{operation}/{id}", method = RequestMethod.GET)
    public String toFormOperation(@PathVariable("operation") final String operation,
                                @PathVariable("id") final int id, final RedirectAttributes redirectAttributes,
                                final Model model) {

        logger.info("/master/fungsijalan/operation: {} ", operation);

       if (operation.equals("delete")) {
            try {
                TbFungsiJalan domainToDelete = jenisJalanRepository.findById(id);        
                // hati-hati kecamatanRepository.deleteAll(domainToDelete.getListDesa() );
                jenisJalanRepository.delete(domainToDelete);               
                redirectAttributes.addFlashAttribute("msg", "del");
                redirectAttributes.addFlashAttribute("msgText", " Deleted permanently");
            } catch (Exception e) {
                    redirectAttributes.addFlashAttribute("msg", "del_fail");
                    redirectAttributes.addFlashAttribute("msgText", " Task could not deleted. Please try later");
                e.printStackTrace();
            }
           
        } else if (operation.equals("new_form")) {
            TbFungsiJalan newDomain = new TbFungsiJalan();
            if (newDomain != null) {
                newDomain.setTempInt1(0); //0.New Form, 1.Edit Form, 3.Delete
                model.addAttribute("domain", newDomain);                
                return "master/fungsijalan/fungsijalan_form";
            }

        } else if (operation.equals("edit_form")) {

            TbFungsiJalan domain = jenisJalanRepository.findById(id);
            if (domain != null) {
                domain.setTempInt1(1); //0.New Form, 1.Edit Form, 3.Delete
                //Password tidak bisa di decode ya?


                // TbJenisJalanRoles domainDetil1 = new TbJenisJalanRoles();
                // TbJenisJalanRoles domainDetil2 = new TbJenisJalanRoles();
                // // for (TbJenisJalanRoles domainDetil: domain.getFuserRoles()) {
                // //     domainDetil1 = domainDetil; 
                // // }

                model.addAttribute("domain", domain);                

                // model.addAttribute("domainDetil1", domainDetil1);                
                // model.addAttribute("domainDetil2", domainDetil2);                

                // model.addAttribute("allDataDetil", domain.getFuserRoles());                

                return "master/fungsijalan/fungsijalan_form";
            } else {
                redirectAttributes.addFlashAttribute("msg", "notfound");
            }
        }

        return "redirect:/master/fungsijalan/";
    }

    

    @RequestMapping("/master/fungsijalan/perbaikan")
    @ResponseBody
    public String perbaikan(){
        String result = "Perbaikan desa error";

    

        return result;
    }

}
