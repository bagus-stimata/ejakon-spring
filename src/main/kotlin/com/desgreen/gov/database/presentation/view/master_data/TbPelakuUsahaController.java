package com.desgreen.gov.database.presentation.view.master_data;

import com.desgreen.gov.database.presentation.SecurityConfig.SecurityUtils;
import com.desgreen.gov.database.data.source.local.jpa_dao.TbPelakuUsahaRepository;
import com.desgreen.gov.database.data.source.entity.TbPelakuUsaha;
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
public class TbPelakuUsahaController {

    private static final Logger logger = LoggerFactory.getLogger(DinasController.class);

    @Autowired
    private TbPelakuUsahaRepository pelakuUsahaRepository;
    
    @Autowired
    private SecurityUtils securityUtils;


    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping("/master/pelakuusaha")
    public String listIndex(final Model viewModel) {

        // final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

  
        final TbPelakuUsaha newDomain =new TbPelakuUsaha();
        viewModel.addAttribute("newDomain", newDomain);
        // viewModel.addAttribute("allTask", usersRepository.findByUserIdStatus(securityUtils.getLoginUser().getId(), Status.ACTIVE.getValue()));
        viewModel.addAttribute("allData", pelakuUsahaRepository.findAll());
        // viewModel.addAttribute("allData", new ArrayList<TbPelakuUsaha>());

        logger.info("# Form Task");
      
        return "master/pelakuusaha/pelakuusaha_list"; 
    }


    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = {"/master/pelakuusaha_form/save_process"}, method = RequestMethod.POST)
    public String saveProcess(@ModelAttribute("domain") final TbPelakuUsaha domain, @ModelAttribute("domainDetil1") final TbPelakuUsaha domainDetil1, final RedirectAttributes redirectAttributes) {
        if (domain.getTempInt1()==0) {
            TbPelakuUsaha newDomain = new TbPelakuUsaha();

            newDomain.setTempInt1(domain.getTempInt1()); //0.New Form, 1.Edit Form, 3.Delete

            newDomain.setKode1(domain.getKode1());
            newDomain.setNamaPemilik(domain.getNamaPemilik());

    
            if (pelakuUsahaRepository.save(newDomain) != null) {
                redirectAttributes.addFlashAttribute("saveUser", "success");
                newDomain.setTempInt1(1); //Karena langsung edit
            } else {
                redirectAttributes.addFlashAttribute("saveUser", "fail");
            }

    
            return "redirect:/master/pelakuusaha/edit_form/" + newDomain.getId();

        }else if (domain.getTempInt1()==1) {
            TbPelakuUsaha domainUpdate = pelakuUsahaRepository.findById(domain.getId() );
            domainUpdate.setTempInt1(domain.getTempInt1()); //0.New Form, 1.Edit Form, 3.Delete

            domainUpdate.setKode1(domain.getKode1());
            domainUpdate.setNamaPemilik(domain.getNamaPemilik());

            pelakuUsahaRepository.save(domainUpdate);

            redirectAttributes.addFlashAttribute("saveUser", "success");
            return "redirect:/master/pelakuusaha/edit_form/" + domain.getId();
        }
       
        
        return "redirect:/master/pelakuusaha/";
    }


    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = "/master/pelakuusaha/{operation}/{id}", method = RequestMethod.GET)
    public String toFormOperation(@PathVariable("operation") final String operation,
                                @PathVariable("id") final int id, final RedirectAttributes redirectAttributes,
                                final Model model) {

        logger.info("/master/pelakuusaha/operation: {} ", operation);

       if (operation.equals("delete")) {
            try {
                TbPelakuUsaha domainToDelete = pelakuUsahaRepository.findById(id);        
                // hati-hati kecamatanRepository.deleteAll(domainToDelete.getListDesa() );
                pelakuUsahaRepository.delete(domainToDelete);               
                redirectAttributes.addFlashAttribute("msg", "del");
                redirectAttributes.addFlashAttribute("msgText", " Deleted permanently");
            } catch (Exception e) {
                    redirectAttributes.addFlashAttribute("msg", "del_fail");
                    redirectAttributes.addFlashAttribute("msgText", " Task could not deleted. Please try later");
                e.printStackTrace();
            }
           
        } else if (operation.equals("new_form")) {
            TbPelakuUsaha newDomain = new TbPelakuUsaha();
            if (newDomain != null) {
                newDomain.setTempInt1(0); //0.New Form, 1.Edit Form, 3.Delete
                model.addAttribute("domain", newDomain);                
                return "master/pelakuusaha/pelakuusaha_form";
            }

        } else if (operation.equals("edit_form")) {

            TbPelakuUsaha domain = pelakuUsahaRepository.findById(id);
            if (domain != null) {
                domain.setTempInt1(1); //0.New Form, 1.Edit Form, 3.Delete
                //Password tidak bisa di decode ya?


                // TbPelakuUsahaRoles domainDetil1 = new TbPelakuUsahaRoles();
                // TbPelakuUsahaRoles domainDetil2 = new TbPelakuUsahaRoles();
                // // for (TbPelakuUsahaRoles domainDetil: domain.getFuserRoles()) {
                // //     domainDetil1 = domainDetil; 
                // // }

                model.addAttribute("domain", domain);                

                // model.addAttribute("domainDetil1", domainDetil1);                
                // model.addAttribute("domainDetil2", domainDetil2);                

                // model.addAttribute("allDataDetil", domain.getFuserRoles());                

                return "master/pelakuusaha/pelakuusaha_form";
            } else {
                redirectAttributes.addFlashAttribute("msg", "notfound");
            }
        }

        return "redirect:/master/pelakuusaha/";
    }

    

    @RequestMapping("/master/pelakuusaha/perbaikan")
    @ResponseBody
    public String perbaikan(){
        String result = "Perbaikan desa error";
        return result;
    }

}
