package com.desgreen.gov.database.presentation.view.master_data;

import com.desgreen.gov.database.presentation.SecurityConfig.SecurityUtils;
import com.desgreen.gov.database.data.source.local.jpa_dao.TbKabupatenRepository;
import com.desgreen.gov.database.data.source.local.jpa_dao.TbProvinsiRepository;
import com.desgreen.gov.database.data.source.entity.TbKabupaten;
import com.desgreen.gov.database.data.source.entity.TbProvinsi;
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
public class KabupatenController {

    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private TbProvinsiRepository provinsiRepository;

    @Autowired
    private TbKabupatenRepository kabupatenRepository;
    

    @Autowired
    private SecurityUtils securityUtils;

    private List<TbProvinsi> listProvinsi = new ArrayList<>();
    

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping("/master/kabupaten")
    public String listIndex(final Model viewModel) {

        // final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        List<TbKabupaten> list = kabupatenRepository.findAll();
        List<TbKabupaten> allData = new ArrayList<>();
        for (TbKabupaten domain: list) {
            if (domain.getProvinsiBean()!=null) {
                allData.add(domain);
            }
        }
  
        final TbKabupaten newDomain =new TbKabupaten();
        viewModel.addAttribute("newDomain", newDomain);
        // viewModel.addAttribute("allTask", usersRepository.findByUserIdStatus(securityUtils.getLoginUser().getId(), Status.ACTIVE.getValue()));
        viewModel.addAttribute("allData", kabupatenRepository.findAll());

        logger.info("# Form Task");

       

        return "master/kabupaten/kabupaten_list"; 
    }


    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = {"/master/kabupaten_form/save_process"}, method = RequestMethod.POST)
    public String saveProcess(@ModelAttribute("domain") final TbKabupaten domain, @ModelAttribute("domainDetil1") final TbKabupaten domainDetil1, final RedirectAttributes redirectAttributes) {
        if (domain.getTempInt1()==0) {
            TbKabupaten newDomain = new TbKabupaten();

            newDomain.setTempInt1(domain.getTempInt1()); //0.New Form, 1.Edit Form, 3.Delete

            newDomain.setKode1(domain.getKode1());
            newDomain.setDescription(domain.getDescription());


            // //Sementara otomatis
            // List<TbKabupatenRoles> listTbKabupatenLRoles = new ArrayList<>();
            // TbKabupatenRoles userRole1 = new TbKabupatenRoles();
            // userRole1.setFuserBean(newDomain);
            // userRole1.setRoleID(Role.USER);
            // TbKabupatenRoles userRole2 = new TbKabupatenRoles();
            // userRole2.setFuserBean(newDomain);
            // userRole2.setRoleID(Role.ADMIN);
    
            // listTbKabupatenLRoles.add(userRole1);
            // listTbKabupatenLRoles.add(userRole2);
    
            // newDomain.setFuserRoles(listTbKabupatenLRoles);
    
            if (kabupatenRepository.save(newDomain) != null) {
                
                // if (provinsiRepository.save(userRole1) !=null &&  
                // provinsiRepository.save(userRole2) !=null) {
                //         redirectAttributes.addFlashAttribute("saveUser", "success");
                // }
                redirectAttributes.addFlashAttribute("saveUser", "success");
    
    
            } else {
                redirectAttributes.addFlashAttribute("saveUser", "fail");
            }

    
            return "redirect:/master/kabupaten/edit_form/" + newDomain.getId();

        }else if (domain.getTempInt1()==1) {
            TbKabupaten domainUpdate = kabupatenRepository.findById(domain.getId() );
            domainUpdate.setTempInt1(domain.getTempInt1()); //0.New Form, 1.Edit Form, 3.Delete

            domainUpdate.setKode1(domain.getKode1());
            domainUpdate.setDescription(domain.getDescription());
            domainUpdate.setProvinsiBean(domain.getProvinsiBean());

            kabupatenRepository.save(domainUpdate);

            redirectAttributes.addFlashAttribute("saveUser", "success");
            return "redirect:/master/kabupaten/edit_form/" + domain.getId();
        }
       
        
        return "redirect:/master/kabupaten/";
    }


    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = "/master/kabupaten/{operation}/{id}", method = RequestMethod.GET)
    public String toFormOperation(@PathVariable("operation") final String operation,
                                @PathVariable("id") final int id, final RedirectAttributes redirectAttributes,
                                final Model model) {

        logger.info("/master/kabupaten/operation: {} ", operation);

       if (operation.equals("delete")) {
            try {
                TbKabupaten domainToDelete = kabupatenRepository.findById(id);        
                // hati-hati provinsiRepository.deleteAll(domainToDelete.getListDesa() );
                kabupatenRepository.delete(domainToDelete);               
                redirectAttributes.addFlashAttribute("msg", "del");
                redirectAttributes.addFlashAttribute("msgText", " Deleted permanently");
            } catch (Exception e) {
                    redirectAttributes.addFlashAttribute("msg", "del_fail");
                    redirectAttributes.addFlashAttribute("msgText", " Task could not deleted. Please try later");
                e.printStackTrace();
            }
           
        } else if (operation.equals("new_form")) {
            TbKabupaten newDomain = new TbKabupaten();
            if (newDomain != null) {
                newDomain.setTempInt1(0); //0.New Form, 1.Edit Form, 3.Delete
                model.addAttribute("domain", newDomain);                
                return "master/kabupaten/kabupaten_form";
            }

        } else if (operation.equals("edit_form")) {

            TbKabupaten domain = kabupatenRepository.findById(id);
            if (domain != null) {
                domain.setTempInt1(1); //0.New Form, 1.Edit Form, 3.Delete
                //Password tidak bisa di decode ya?


                // TbKabupatenRoles domainDetil1 = new TbKabupatenRoles();
                // TbKabupatenRoles domainDetil2 = new TbKabupatenRoles();
                // // for (TbKabupatenRoles domainDetil: domain.getFuserRoles()) {
                // //     domainDetil1 = domainDetil; 
                // // }

                listProvinsi = provinsiRepository.findAll();

                model.addAttribute("domain", domain);           
                model.addAttribute("listProvinsi", listProvinsi);
                
                // Map<Integer, String> mapCountries = countryDAO.getMapCountries();
                Map<Integer, String> mapProvinsi = new HashMap<>();
                for (TbProvinsi provinsiBean: listProvinsi) {
                    mapProvinsi.put(provinsiBean.getId(), provinsiBean.getDescription());
                }
                 model.addAttribute("mapProvinsi", mapProvinsi);

                // model.addAttribute("domainDetil1", domainDetil1);                
                // model.addAttribute("domainDetil2", domainDetil2);                

                // model.addAttribute("allDataDetil", domain.getFuserRoles());                

                return "master/kabupaten/kabupaten_form";
            } else {
                redirectAttributes.addFlashAttribute("msg", "notfound");
            }
        }

        return "redirect:/master/kabupaten/";
    }

    

    @RequestMapping("/master/kabupaten/perbaikan")
    @ResponseBody
    public String perbaikan(){
        String result = "Perbaikan desa error";

        List<TbProvinsi> listProvinsi = provinsiRepository.findAll();
        
        List<TbKabupaten> listDesa = kabupatenRepository.findAll();
        int counter = 0;
        for (TbKabupaten domain: listDesa) {
            result = " ada Kecamatan " + listProvinsi.size();
            List<TbProvinsi> listProvinsi_Found = listProvinsi.stream().filter(x -> x.getKode1().equals(domain.getProvinsiBean())).collect(Collectors.toList());

            // for (TbProvinsi kecamatan: listProvinsi) {
            //     if (kecamatan.getKode1().equals(domain.getKecamatanBean_old())) {
            //         result = "ada yang ketemu";
            //     }
            // }

            if (listProvinsi_Found.size() >0 ) {

            //     result = "MASUK KETEMU ";
                
                domain.setProvinsiBean(listProvinsi_Found.get(0)) ;
                kabupatenRepository.save(domain);
                counter++;
            }
        }

        if (counter>0) {
            result = "perbaikan sejumlah: " + counter;
        }

        return result;
    }

}
