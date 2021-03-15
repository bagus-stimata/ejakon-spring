package com.desgreen.gov.database.presentation.view.master_data;

import com.desgreen.gov.database.presentation.SecurityConfig.SecurityUtils;
import com.desgreen.gov.database.data.source.local.jpa_dao.TbDesaRepository;
import com.desgreen.gov.database.data.source.local.jpa_dao.TbKecamatanRepository;
import com.desgreen.gov.database.data.source.entity.TbDesa;
import com.desgreen.gov.database.data.source.entity.TbKecamatan;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * The TodoController  Class
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 4/27/2018.
 */
@Controller
@ComponentScan
public class DesaController {

    private static final Logger logger = LoggerFactory.getLogger(DesaController.class);

    @Autowired
    private TbKecamatanRepository kecamatanRepository;

    @Autowired
    private TbDesaRepository desaRepository;
    

    @Autowired
    private SecurityUtils securityUtils;

    private List<TbKecamatan> listKecamatan = new ArrayList<>();
    

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping("/master/desa")
    public String listIndex(final Model viewModel) {

        // final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

  
        final TbDesa newDomain =new TbDesa();
        viewModel.addAttribute("newDomain", newDomain);
        // viewModel.addAttribute("allTask", usersRepository.findByUserIdStatus(securityUtils.getLoginUser().getId(), Status.ACTIVE.getValue()));
        viewModel.addAttribute("allData", desaRepository.findAll());

        logger.info("# Form Task");

        listKecamatan = kecamatanRepository.findAll();

        return "master/desa/desa_list"; 
    }


    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = {"/master/desa_form/save_process"}, method = RequestMethod.POST)
    public String saveProcess(@ModelAttribute("domain") final TbDesa domain, @ModelAttribute("domainDetil1") final TbDesa domainDetil1, final RedirectAttributes redirectAttributes) {
        if (domain.getTempInt1()==0) {
            TbDesa newDomain = new TbDesa();

            newDomain.setTempInt1(domain.getTempInt1()); //0.New Form, 1.Edit Form, 3.Delete

            newDomain.setKode1(domain.getKode1());
            newDomain.setDescription(domain.getDescription());
            newDomain.setKecamatanBean(domain.getKecamatanBean());

    
            if (desaRepository.save(newDomain) != null) {                
                redirectAttributes.addFlashAttribute("saveUser", "success");
                newDomain.setTempInt1(1);
            } else {
                redirectAttributes.addFlashAttribute("saveUser", "fail");
            }

    
            return "redirect:/master/desa/edit_form/" + newDomain.getId();

        }else if (domain.getTempInt1()==1) {
            TbDesa domainUpdate = desaRepository.findById(domain.getId() );
            domainUpdate.setTempInt1(domain.getTempInt1()); //0.New Form, 1.Edit Form, 3.Delete

            domainUpdate.setKode1(domain.getKode1());
            domainUpdate.setDescription(domain.getDescription());
            domainUpdate.setKecamatanBean(domain.getKecamatanBean());

            desaRepository.save(domainUpdate);

            redirectAttributes.addFlashAttribute("saveUser", "success");
            
            return "redirect:/master/desa/edit_form/" + domainUpdate.getId();
        }
       
        
        return "redirect:/master/desa/";
    }


    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = "/master/desa/{operation}/{id}", method = RequestMethod.GET)
    public String toFormOperation(@PathVariable("operation") final String operation,
                                @PathVariable("id") final int id, final RedirectAttributes redirectAttributes,
                                final Model model) {

        logger.info("/master/desa/operation: {} ", operation);

       if (operation.equals("delete")) {
            try {
                TbDesa domainToDelete = desaRepository.findById(id);        
                // hati-hati kecamatanRepository.deleteAll(domainToDelete.getListDesa() );
                desaRepository.delete(domainToDelete);               
                redirectAttributes.addFlashAttribute("msg", "del");
                redirectAttributes.addFlashAttribute("msgText", " Deleted permanently");
            } catch (Exception e) {
                    redirectAttributes.addFlashAttribute("msg", "del_fail");
                    redirectAttributes.addFlashAttribute("msgText", " Task could not deleted. Please try later");
                e.printStackTrace();
            }
           
        } else if (operation.equals("new_form")) {
            TbDesa newDomain = new TbDesa();
            if (newDomain != null) {
                newDomain.setTempInt1(0); //0.New Form, 1.Edit Form, 3.Delete
                model.addAttribute("domain", newDomain);      
                
                listKecamatan = kecamatanRepository.findAll();
                model.addAttribute("listKecamatan", listKecamatan);
                
                // Map<Integer, String> mapCountries = countryDAO.getMapCountries();
                Map<Integer, String> mapKecamatan = new HashMap<>();
                for (TbKecamatan kecamatanBean: listKecamatan) {
                    mapKecamatan.put(kecamatanBean.getId(), kecamatanBean.getDescription());
                }
                 model.addAttribute("mapKecamatan", mapKecamatan);          

                return "master/desa/desa_form";
            }

        } else if (operation.equals("edit_form")) {

            TbDesa domain = desaRepository.findById(id);
            if (domain != null) {
                domain.setTempInt1(1); //0.New Form, 1.Edit Form, 3.Delete
                //Password tidak bisa di decode ya?


                // TbDesaRoles domainDetil1 = new TbDesaRoles();
                // TbDesaRoles domainDetil2 = new TbDesaRoles();
                // // for (TbDesaRoles domainDetil: domain.getFuserRoles()) {
                // //     domainDetil1 = domainDetil; 
                // // }

                listKecamatan = kecamatanRepository.findAll();

                model.addAttribute("domain", domain);           
                model.addAttribute("listKecamatan", listKecamatan);
                
                // Map<Integer, String> mapCountries = countryDAO.getMapCountries();
                Map<Integer, String> mapKecamatan = new HashMap<>();
                for (TbKecamatan kecamatanBean: listKecamatan) {
                    mapKecamatan.put(kecamatanBean.getId(), kecamatanBean.getDescription());
                }
                 model.addAttribute("mapKecamatan", mapKecamatan);

                // model.addAttribute("domainDetil1", domainDetil1);                
                // model.addAttribute("domainDetil2", domainDetil2);                

                // model.addAttribute("allDataDetil", domain.getFuserRoles());                

                return "master/desa/desa_form";
            } else {
                redirectAttributes.addFlashAttribute("msg", "notfound");
            }
        }

        return "redirect:/master/desa/";
    }

    

    @RequestMapping("/master/desa/perbaikan")
    @ResponseBody
    public String perbaikan(){
        String result = "Perbaikan desa error";

        List<TbKecamatan> listKecamatan = kecamatanRepository.findAll();
        
        List<TbDesa> listDesa = desaRepository.findAll();
        int counter = 0;
        for (TbDesa domain: listDesa) {
            result = " ada Kecamatan " + listKecamatan.size();
            List<TbKecamatan> listKecamatan_Found = listKecamatan.stream().filter(x -> x.getKode1().equals(domain.getKecamatanBean_old())).collect(Collectors.toList());

            // for (TbKecamatan kecamatan: listKecamatan) {
            //     if (kecamatan.getKode1().equals(domain.getKecamatanBean_old())) {
            //         result = "ada yang ketemu";
            //     }
            // }

            if (listKecamatan_Found.size() >0 ) {

            //     result = "MASUK KETEMU ";
                
                domain.setKecamatanBean(listKecamatan_Found.get(0)) ;
                desaRepository.save(domain);
                counter++;
            }
        }

        if (counter>0) {
            result = "perbaikan sejumlah: " + counter;
        }

        return result;
    }

}
