package com.desgreen.gov.database.presentation.view.master_data;

import com.desgreen.gov.database.presentation.SecurityConfig.SecurityUtils;
import com.desgreen.gov.database.data.source.local.jpa_dao.TbDesaRepository;
import com.desgreen.gov.database.data.source.local.jpa_dao.TbKabupatenRepository;
import com.desgreen.gov.database.data.source.local.jpa_dao.TbKecamatanRepository;
import com.desgreen.gov.database.data.source.entity.TbDesa;
import com.desgreen.gov.database.data.source.entity.TbKabupaten;
import com.desgreen.gov.database.data.source.entity.TbKecamatan;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * The TodoController  Class
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 4/27/2018.
 */
@Controller
@ComponentScan
public class KecamatanController {

    private static final Logger logger = LoggerFactory.getLogger(KecamatanController.class);

    @Autowired
    private TbKabupatenRepository kabupatenRepository;

    @Autowired
    private TbKecamatanRepository kecamatanRepository;
    @Autowired
    private TbDesaRepository desaRepository;

    @Autowired
    private SecurityUtils securityUtils;


    private List<TbKabupaten> listKabupaten = new ArrayList<>();

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping("/master/kecamatan")
    public String listIndex(final Model viewModel) {

        // final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

  
        final TbKecamatan newDomain =new TbKecamatan();
        viewModel.addAttribute("newDomain", newDomain);
        // viewModel.addAttribute("allTask", usersRepository.findByUserIdStatus(securityUtils.getLoginUser().getId(), Status.ACTIVE.getValue()));
        viewModel.addAttribute("allData", kecamatanRepository.findAll());

        logger.info("# Form Task");
      
        return "master/kecamatan/kecamatan_list"; 
    }


    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = {"/master/kecamatan_form/save_process"}, method = RequestMethod.POST)
    public String saveProcess(@ModelAttribute("domain") final TbKecamatan domain, @ModelAttribute("domainDetil1") final TbDesa domainDetil1, final RedirectAttributes redirectAttributes) {
        if (domain.getTempInt1()==0) {
            TbKecamatan newDomain = new TbKecamatan();

            newDomain.setTempInt1(domain.getTempInt1()); //0.New Form, 1.Edit Form, 3.Delete

            newDomain.setKode1(domain.getKode1());
            newDomain.setDescription(domain.getDescription());
            newDomain.setKabupatenBean(domain.getKabupatenBean());

    
            if (kecamatanRepository.save(newDomain) != null) {                
                redirectAttributes.addFlashAttribute("saveUser", "success");
                newDomain.setTempInt1(1);
    
            } else {
                redirectAttributes.addFlashAttribute("saveUser", "fail");
            }

    
            return "redirect:/master/kecamatan/edit_form/" + newDomain.getId();

        }else if (domain.getTempInt1()==1) {
            TbKecamatan domainUpdate = kecamatanRepository.findById(domain.getId() );
            domainUpdate.setTempInt1(domain.getTempInt1()); //0.New Form, 1.Edit Form, 3.Delete

            domainUpdate.setKode1(domain.getKode1());
            domainUpdate.setDescription(domain.getDescription());
            domainUpdate.setKabupatenBean(domain.getKabupatenBean());

            kecamatanRepository.save(domainUpdate);

            redirectAttributes.addFlashAttribute("saveUser", "success");
            return "redirect:/master/kecamatan/edit_form/" + domain.getId();
        }
       
        
        return "redirect:/master/kecamatan/";
    }


    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = "/master/kecamatan/{operation}/{id}", method = RequestMethod.GET)
    public String toFormOperation(@PathVariable("operation") final String operation,
                                @PathVariable("id") final int id, final RedirectAttributes redirectAttributes,
                                final Model model) {

        logger.info("/master/kecamatan/operation: {} ", operation);

       if (operation.equals("delete")) {
            try {
                TbKecamatan domainToDelete = kecamatanRepository.findById(id);        
                // hati-hati kecamatanRepository.deleteAll(domainToDelete.getListDesa() );
                kecamatanRepository.delete(domainToDelete);               
                redirectAttributes.addFlashAttribute("msg", "del");
                redirectAttributes.addFlashAttribute("msgText", " Deleted permanently");
            } catch (Exception e) {
                    redirectAttributes.addFlashAttribute("msg", "del_fail");
                    redirectAttributes.addFlashAttribute("msgText", " Task could not deleted. Please try later");
                e.printStackTrace();
            }
           
        } else if (operation.equals("new_form")) {
            TbKecamatan newDomain = new TbKecamatan();
            if (newDomain != null) {
                newDomain.setTempInt1(0); //0.New Form, 1.Edit Form, 3.Delete
                model.addAttribute("domain", newDomain);        
                
                listKabupaten = kabupatenRepository.findAll();
                model.addAttribute("listKecamatan", listKabupaten);
                
                // Map<Integer, String> mapCountries = countryDAO.getMapCountries();
                Map<Integer, String> mapKabupaten = new HashMap<>();
                for (TbKabupaten kabupatenBean: listKabupaten) {
                    mapKabupaten.put(kabupatenBean.getId(), kabupatenBean.getDescription());
                }
                 model.addAttribute("mapKabupaten", mapKabupaten);          
                
                return "master/kecamatan/kecamatan_form";
            }

        } else if (operation.equals("edit_form")) {

            TbKecamatan domain = kecamatanRepository.findById(id);
            if (domain != null) {
                domain.setTempInt1(1); //0.New Form, 1.Edit Form, 3.Delete
                //Password tidak bisa di decode ya?


             
                model.addAttribute("domain", domain);                

                // model.addAttribute("domainDetil1", domainDetil1);                
                // model.addAttribute("domainDetil2", domainDetil2);                

                // model.addAttribute("allDataDetil", domain.getFuserRoles());                
                listKabupaten = kabupatenRepository.findAll();
                model.addAttribute("listKecamatan", listKabupaten);
                
                // Map<Integer, String> mapCountries = countryDAO.getMapCountries();
                Map<Integer, String> mapKabupaten = new HashMap<>();
                for (TbKabupaten kabupatenBean: listKabupaten) {
                    mapKabupaten.put(kabupatenBean.getId(), kabupatenBean.getDescription());
                }
                 model.addAttribute("mapKabupaten", mapKabupaten);          


                return "master/kecamatan/kecamatan_form";
            } else {
                redirectAttributes.addFlashAttribute("msg", "notfound");
            }
        }

        return "redirect:/master/kecamatan/";
    }


}
