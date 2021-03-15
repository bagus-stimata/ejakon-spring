package com.desgreen.gov.database.presentation.view.master_data;

import com.desgreen.gov.database.presentation.SecurityConfig.SecurityUtils;
import com.desgreen.gov.database.data.source.local.jpa_dao.TbRencanaPemanfaatanRepository;
import com.desgreen.gov.database.data.source.entity.TbRencanaPemanfaatan;
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
public class TbRencanaPemanfaatanController {

    private static final Logger logger = LoggerFactory.getLogger(DinasController.class);

    @Autowired
    private TbRencanaPemanfaatanRepository kegiatanDimohonRepository;
    
    @Autowired
    private SecurityUtils securityUtils;


    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping("/master/rencanapemanfaatan")
    public String listIndex(final Model viewModel) {

        // final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
  
        final TbRencanaPemanfaatan newDomain =new TbRencanaPemanfaatan();
        viewModel.addAttribute("newDomain", newDomain);
        // viewModel.addAttribute("allTask", usersRepository.findByUserIdStatus(securityUtils.getLoginUser().getId(), Status.ACTIVE.getValue()));
        viewModel.addAttribute("allData", kegiatanDimohonRepository.findAll());
        // viewModel.addAttribute("allData", new ArrayList<TbKegiatanDimohon>());

        logger.info("# Form Task");
      
        return "master/rencanapemanfaatan/rencanapemanfaatan_list"; 
    }


    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = {"/master/rencanapemanfaatan_form/save_process"}, method = RequestMethod.POST)
    public String saveProcess(@ModelAttribute("domain") final TbRencanaPemanfaatan domain, @ModelAttribute("domainDetil1") final TbRencanaPemanfaatan domainDetil1, final RedirectAttributes redirectAttributes) {
        if (domain.getTempInt1()==0) {
            TbRencanaPemanfaatan newDomain = new TbRencanaPemanfaatan();

            newDomain.setTempInt1(domain.getTempInt1()); //0.New Form, 1.Edit Form, 3.Delete

            newDomain.setKode1(domain.getKode1());
            newDomain.setDescription(domain.getDescription());

    
            if (kegiatanDimohonRepository.save(newDomain) != null) {
                redirectAttributes.addFlashAttribute("saveUser", "success");
                newDomain.setTempInt1(1); //Karena langsung edit
            } else {
                redirectAttributes.addFlashAttribute("saveUser", "fail");
            }

    
            return "redirect:/master/rencanapemanfaatan/edit_form/" + newDomain.getId();

        }else if (domain.getTempInt1()==1) {
            TbRencanaPemanfaatan domainUpdate = kegiatanDimohonRepository.findById(domain.getId() );
            domainUpdate.setTempInt1(domain.getTempInt1()); //0.New Form, 1.Edit Form, 3.Delete

            domainUpdate.setKode1(domain.getKode1());
            domainUpdate.setDescription(domain.getDescription());

            kegiatanDimohonRepository.save(domainUpdate);

            redirectAttributes.addFlashAttribute("saveUser", "success");
            return "redirect:/master/rencanapemanfaatan/edit_form/" + domain.getId();
        }
       
        
        return "redirect:/master/rencanapemanfaatan/";
    }


    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = "/master/rencanapemanfaatan/{operation}/{id}", method = RequestMethod.GET)
    public String toFormOperation(@PathVariable("operation") final String operation,
                                @PathVariable("id") final int id, final RedirectAttributes redirectAttributes,
                                final Model model) {

        logger.info("/master/rencanapemanfaatan/operation: {} ", operation);

       if (operation.equals("delete")) {
            try {
                TbRencanaPemanfaatan domainToDelete = kegiatanDimohonRepository.findById(id);        
                // hati-hati kecamatanRepository.deleteAll(domainToDelete.getListDesa() );
                kegiatanDimohonRepository.delete(domainToDelete);               
                redirectAttributes.addFlashAttribute("msg", "del");
                redirectAttributes.addFlashAttribute("msgText", " Deleted permanently");
            } catch (Exception e) {
                    redirectAttributes.addFlashAttribute("msg", "del_fail");
                    redirectAttributes.addFlashAttribute("msgText", " Task could not deleted. Please try later");
                e.printStackTrace();
            }
           
        } else if (operation.equals("new_form")) {
            TbRencanaPemanfaatan newDomain = new TbRencanaPemanfaatan();
            if (newDomain != null) {
                newDomain.setTempInt1(0); //0.New Form, 1.Edit Form, 3.Delete
                model.addAttribute("domain", newDomain);                
                return "master/rencanapemanfaatan/rencanapemanfaatan_form";
            }

        } else if (operation.equals("edit_form")) {

            TbRencanaPemanfaatan domain = kegiatanDimohonRepository.findById(id);
            if (domain != null) {
                domain.setTempInt1(1); //0.New Form, 1.Edit Form, 3.Delete
                //Password tidak bisa di decode ya?


                // TbKegiatanDimohonRoles domainDetil1 = new TbKegiatanDimohonRoles();
                // TbKegiatanDimohonRoles domainDetil2 = new TbKegiatanDimohonRoles();
                // // for (TbKegiatanDimohonRoles domainDetil: domain.getFuserRoles()) {
                // //     domainDetil1 = domainDetil; 
                // // }

                model.addAttribute("domain", domain);                

                // model.addAttribute("domainDetil1", domainDetil1);                
                // model.addAttribute("domainDetil2", domainDetil2);                

                // model.addAttribute("allDataDetil", domain.getFuserRoles());                

                return "master/rencanapemanfaatan/rencanapemanfaatan_form";
            } else {
                redirectAttributes.addFlashAttribute("msg", "notfound");
            }
        }

        return "redirect:/master/rencanapemanfaatan/";
    }

    

    @RequestMapping("/master/rencanapemanfaatan/perbaikan")
    @ResponseBody
    public String perbaikan(){
        String result = "Perbaikan desa error";
        return result;
    }

}
