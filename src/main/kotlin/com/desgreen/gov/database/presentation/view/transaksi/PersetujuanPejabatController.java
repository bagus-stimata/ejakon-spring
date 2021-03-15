package com.desgreen.gov.database.presentation.view.transaksi;

import com.desgreen.gov.database.presentation.SecurityConfig.SecurityUtils;
import com.desgreen.gov.database.presentation.utils.EmailServiceImpl;
import com.desgreen.gov.database.data.source.entity.*;
import com.desgreen.gov.database.utils.Constants;
import com.desgreen.gov.database.data.source.local.jpa_dao.*;
import com.desgreen.gov.database.domain.model_enum.Role;
import com.desgreen.gov.database.domain.model_enum.StatusPersetujuan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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
public class PersetujuanPejabatController {

    private static final Logger logger = LoggerFactory.getLogger(PermohonanRekomController.class);

    @Autowired
    EmailServiceImpl emailService;

    @Autowired
    private TbTipeLayananRepository tipeLayananRepository;
    @Autowired
    private TbPelakuUsahaRepository pelakuUsahaRepository;
    @Autowired
    private TbRencanaPemanfaatanRepository rencanaPemanfaatanRepository;
    @Autowired
    private TbStatusWilayahRepository statusWilayahRepository;
    @Autowired
    private TbArahanFungsiLahanRepository arahanFungsiLahanRepository;
    @Autowired
    private TbFungsiLahanSekitarRepository fungsiLahanSekitarRepository;
    @Autowired
    private TbFungsiJalanRepository fungsiJalanRepository;
    @Autowired
    private TbKlasifikasiKegiatanRepository  klasifikasiKegiatanRepository;
    @Autowired
    private TbPekerjaanRepository  pekerjaanRepository;
    @Autowired
    private ChatsRepository  chatsRepository;
    @Autowired
    private TbKecamatanRepository kecamatanRepository;
    @Autowired
    private TbDesaRepository desaRepository;
    @Autowired
    private TbJenisBuktiKepemilikanRepository jenisBuktiKepemilikanRepository;

    @Autowired
    private TbPermohonanRepository permohonanRepository;


    @Autowired
    private SecurityUtils securityUtils;


    private List<TbPermohonan> listDesa = new ArrayList<>();
    private List<TbKecamatan> listKecamatan = new ArrayList<>();

    
    String pageTitle = "";
    
    @ModelAttribute("getListTipeLayanan")
    public List<TbTipeLayanan> getListTipeLayanan() {
        List<TbTipeLayanan> list = new ArrayList<>();
        for (TbTipeLayanan domain: tipeLayananRepository.findAll() ) {
            list.add(domain); 
        }        
        return list;
    }
    @ModelAttribute("getListRencanaPemanfaatan")
    public List<TbRencanaPemanfaatan> getListRencanaPemanfaatan() {
        List<TbRencanaPemanfaatan> list = new ArrayList<>();
        for (TbRencanaPemanfaatan domain: rencanaPemanfaatanRepository.findAll() ) {
            list.add(domain); 
        }        
        return list;
    }
    @ModelAttribute("getListKecamatan")
    public List<TbKecamatan> getListKecamatan() {
        List<TbKecamatan> list = new ArrayList<>();
        for (TbKecamatan domain: kecamatanRepository.findAll() ) {
            list.add(domain); 
        }        
        return list;
    }
    @ModelAttribute("getListDesa")
    public List<TbDesa> getListDesa() {
        List<TbDesa> list = new ArrayList<>();
        for (TbDesa domain: desaRepository.findAll() ) {
            list.add(domain); 
        }        
        return list;
    }
    @ModelAttribute("getListJenisBuktiKepemilikan")
    public List<TbJenisBuktiKepemilikan> getListJenisBuktiKepemilikan() {
        List<TbJenisBuktiKepemilikan> list = new ArrayList<>();
        for (TbJenisBuktiKepemilikan domain: jenisBuktiKepemilikanRepository.findAll() ) {
            list.add(domain); 
        }        
        return list;
    }

    @ModelAttribute("allStatusPersetujuan")
    public StatusPersetujuan[] getAllStatusPersetujuan() {
        return StatusPersetujuan.values();
    }
    @ModelAttribute("appImagePath")
    public String appImagePath(){
        return Constants.APP_IMAGE_PATH;
    }
   
    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.PEJABAT1 + "', '" + Role.PEJABAT2 + "', '" + Role.PEJABAT3 + "', '" + Role.PEJABAT4 + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping("/transaksi/persetujuanpejabat")
    public String listIndex(final Model model) {

        // try {
        //     Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //     String namaUser = auth.getName(); //jika tidak ada akan memberikan nilai null
        //     currentUserName = namaUser;
        //     currenPrincipal = auth.getPrincipal().toString();            
        // } catch (Exception e) {            
        // }

        
  
        final TbPermohonan newDomain =new TbPermohonan();
        model.addAttribute("newDomain", newDomain);

        List<TbPermohonan> list = new ArrayList<>();
        for (TbPermohonan domain: permohonanRepository.findAll()) {          
            try {
                if (domain.getStatusPersetujuan1().equals(StatusPersetujuan.VALID) && 
                    domain.getStatusPersetujuan2().equals(StatusPersetujuan.VALID)  && 
                    domain.getStatusPersetujuan3().equals(StatusPersetujuan.VALID) && 
                    domain.getStatusPersetujuan4().equals(StatusPersetujuan.VALID) ) {

                    domain.setStatusPersetujuanAll(StatusPersetujuan.VALID);

                } else if (domain.getStatusPersetujuan1().equals(StatusPersetujuan.INVALID) || 
                    domain.getStatusPersetujuan2().equals(StatusPersetujuan.INVALID)  || 
                    domain.getStatusPersetujuan3().equals(StatusPersetujuan.INVALID) || 
                    domain.getStatusPersetujuan4().equals(StatusPersetujuan.INVALID) ) {

                    domain.setStatusPersetujuanAll(StatusPersetujuan.INVALID);

                }else {
                    domain.setStatusPersetujuanAll(StatusPersetujuan.HOLD);
                }
            } catch (Exception e) {
            }

            list.add(domain);
        }
       
        model.addAttribute("allData",  list.stream().sorted(Comparator.comparing(TbPermohonan::getTanggalPermohonan).reversed()).collect(Collectors.toList()));

        String pejabatKe = "";
        for (FUserRoles test: securityUtils.getLoginUser().getFuserRoles()) {
            if (test.getRoleID().equals(Role.PEJABAT1)) {
                model.addAttribute("pejabatKe1", 1);
            }
            if (test.getRoleID().equals(Role.PEJABAT2)) {
                model.addAttribute("pejabatKe2", 1);
            }
            if (test.getRoleID().equals(Role.PEJABAT3)) {
                model.addAttribute("pejabatKe3", 1);
            }
            if (test.getRoleID().equals(Role.PEJABAT4)) {
                model.addAttribute("pejabatKe4", 1);
            }
            pejabatKe += test.getRoleID() + " >> ";
        }
        model.addAttribute("pejabatKe", pejabatKe); //Untuk Teset

        logger.info("# Form Task");

        pageTitle = Constants.APP_BRAND_NAME;
        model.addAttribute("pageTitle", pageTitle);

        return "transaksi/persetujuanpejabat/persetujuanpejabat_list"; 
    }


    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.PEJABAT1 + "', '" + Role.PEJABAT2 + "', '" + Role.PEJABAT3 + "', '" + Role.PEJABAT4 + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = {"/transaksi/persetujuanpejabat/save_process"}, method = RequestMethod.POST)
    public String saveProcess(@ModelAttribute("domain") final TbPermohonan domain,
                final RedirectAttributes redirectAttributes) {
    // public String saveProcess(@ModelAttribute("domain") final TbPermohonan domain,
    //          @RequestParam("file_1") MultipartFile file_1, @RequestParam("file_2") MultipartFile file_2, 
    //          @RequestParam("file_3") MultipartFile file_3, @RequestParam("file_4") MultipartFile file_4, 
    //          @RequestParam("file_5") MultipartFile file_5, @RequestParam("file_6") MultipartFile file_6, 
    //          @RequestParam("file_7") MultipartFile file_7, @RequestParam("file_8") MultipartFile file_8, 
    //             final RedirectAttributes redirectAttributes) {

        if (domain.getTempInt1()==0) {
            // TbPermohonan newDomain = new TbPermohonan();
            // // newDomain = getNewDomainFromView(newDomain, domain);
        
            // newDomain.setTempInt1(domain.getTempInt1()); //0.New Form, 1.Edit Form, 3.Delete

            // if (permohonanRepository.save(newDomain) != null) {                
            //     newDomain.setTempInt1(1);
               
            //     redirectAttributes.addFlashAttribute("saveUser", "success");
            // } else {
            //     redirectAttributes.addFlashAttribute("saveUser", "fail");
            // }
    
            // return "redirect:/transaksi/persetujuanpejabat/edit_form/" + newDomain.getId();

        }else if (domain.getTempInt1()==1) {
            TbPermohonan domainUpdate = permohonanRepository.findById(domain.getId() );
            // domainUpdate = getNewDomainFromView(domainUpdate, domain);

            if (domainUpdate !=null) {
                domainUpdate.setTempInt1(domain.getTempInt1()); //0.New Form, 1.Edit Form, 3.Delete
                domainUpdate.setTempInt1(1);

                domainUpdate.setStatusPersetujuan1(domain.getStatusPersetujuan1());
                domainUpdate.setStatusPersetujuan1Notes(domain.getStatusPersetujuan1Notes());
                domainUpdate.setStatusPersetujuan2(domain.getStatusPersetujuan2());
                domainUpdate.setStatusPersetujuan2Notes(domain.getStatusPersetujuan2Notes());
                domainUpdate.setStatusPersetujuan3(domain.getStatusPersetujuan3());
                domainUpdate.setStatusPersetujuan3Notes(domain.getStatusPersetujuan3Notes());
                domainUpdate.setStatusPersetujuan4(domain.getStatusPersetujuan4());
                domainUpdate.setStatusPersetujuan4Notes(domain.getStatusPersetujuan4Notes());


                if (domainUpdate.getStatusPersetujuan1().equals(StatusPersetujuan.VALID) 
                    && domainUpdate.getStatusPersetujuan2().equals(StatusPersetujuan.VALID) 
                    && domainUpdate.getStatusPersetujuan3().equals(StatusPersetujuan.VALID) 
                    && domainUpdate.getStatusPersetujuan4().equals(StatusPersetujuan.VALID) 
                    ) {

                    //SEND EMAIL SELESAI
                    kirimEmailNotifikasi(domainUpdate);
                }

                int jumlahTidakSetuju = 0;
                if (domainUpdate.getStatusPersetujuan1().equals(StatusPersetujuan.INVALID)) jumlahTidakSetuju++;
                if (domainUpdate.getStatusPersetujuan2().equals(StatusPersetujuan.INVALID)) jumlahTidakSetuju++;
                if (domainUpdate.getStatusPersetujuan3().equals(StatusPersetujuan.INVALID)) jumlahTidakSetuju++;
                if (domainUpdate.getStatusPersetujuan4().equals(StatusPersetujuan.INVALID)) jumlahTidakSetuju++;

                if (jumlahTidakSetuju >1) {
                    kirimEmailNotifikasi(domainUpdate);
                }



                permohonanRepository.save(domainUpdate);



                redirectAttributes.addFlashAttribute("saveUser", "success");
                redirectAttributes.addFlashAttribute("saveUserInfo", domain.getStatusVerifikasi1());
                
                return "redirect:/transaksi/persetujuanpejabat/edit_form/" + domainUpdate.getId();
            }
        }
       
        
       

        return "redirect:/transaksi/persetujuanpejabat/";
    }


    // @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.PEJABAT1 + "', '" + Role.PEJABAT2 + "', '" + Role.PEJABAT3 + "', '" + Role.PEJABAT4 + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = "/transaksi/persetujuanpejabat/{operation}/{id}", method = RequestMethod.GET)
    public String toFormOperation(@PathVariable("operation") final String operation,
                                @PathVariable("id") final int id, final RedirectAttributes redirectAttributes,
                                final Model model) {

        logger.info("/transaksi/persetujuanpejabat/operation: {} ", operation);
        pageTitle = Constants.APP_BRAND_NAME;
        model.addAttribute("pageTitle", pageTitle);

       if (operation.equals("delete")) {
            try {
                TbPermohonan domainToDelete = permohonanRepository.findById(id);        
                // hati-hati kecamatanRepository.deleteAll(domainToDelete.getListDesa() );

                 try {
                    Path path = Paths.get(Constants.APP_IMAGE_PATH + domainToDelete.getImageSuratPermohonan());
                    Files.deleteIfExists(path);                         
                 } catch (Exception e) {}
                 try {
                    Path path = Paths.get(Constants.APP_IMAGE_PATH + domainToDelete.getImageKtp());
                    Files.deleteIfExists(path); 
                 } catch (Exception e) {}
                 try {
                    Path path = Paths.get(Constants.APP_IMAGE_PATH + domainToDelete.getImageKk());
                    Files.deleteIfExists(path);                         
                 } catch (Exception e) {}
                 try {
                    Path path = Paths.get(Constants.APP_IMAGE_PATH + domainToDelete.getImageBuktiKepemilikan());
                    Files.deleteIfExists(path);                         
                 } catch (Exception e) {}
                 try {
                    Path path = Paths.get(Constants.APP_IMAGE_PATH + domainToDelete.getImageSuratKeterangan());
                    Files.deleteIfExists(path);                         
                 } catch (Exception e) {}
                 try {
                    Path path = Paths.get(Constants.APP_IMAGE_PATH + domainToDelete.getImageAktePendirianPerusahaan());
                    Files.deleteIfExists(path);                         
                 } catch (Exception e) {}
                 try {
                    Path path = Paths.get(Constants.APP_IMAGE_PATH + domainToDelete.getImageBuktiPelunasanPbb());
                    Files.deleteIfExists(path);                         
                 } catch (Exception e) {}
                 try {
                    Path path = Paths.get(Constants.APP_IMAGE_PATH + domainToDelete.getImageHandDrawLokasi());
                    Files.deleteIfExists(path);                         
                 } catch (Exception e) {}

                permohonanRepository.delete(domainToDelete);       
                
                redirectAttributes.addFlashAttribute("msg", "del");
                redirectAttributes.addFlashAttribute("msgText", " Deleted permanently");
            } catch (Exception e) {
                    redirectAttributes.addFlashAttribute("msg", "del_fail");
                    redirectAttributes.addFlashAttribute("msgText", " Task could not deleted. Please try later");
                e.printStackTrace();
            }
           
        } else if (operation.equals("new_form")) {
            TbPermohonan newDomain = new TbPermohonan();
            if (newDomain != null) {
                newDomain.setTempInt1(0); //0.New Form, 1.Edit Form, 3.Delete


                model.addAttribute("domain", newDomain);      
                
                return "transaksi/persetujuanpejabat/persetujuanpejabat_form";
            }

        } else if (operation.equals("edit_form")) {

            TbPermohonan domain = permohonanRepository.findById(id);
            if (domain != null) {
                domain.setTempInt1(1); //0.New Form, 1.Edit Form, 3.Delete
              
             
                model.addAttribute("domain", domain);           
                
                String pejabatKe = "";
                for (FUserRoles test: securityUtils.getLoginUser().getFuserRoles()) {
                    if (test.getRoleID().equals(Role.PEJABAT1)) {
                        model.addAttribute("pejabatKe1", 1);
                    }
                    if (test.getRoleID().equals(Role.PEJABAT2)) {
                        model.addAttribute("pejabatKe2", 1);
                    }
                    if (test.getRoleID().equals(Role.PEJABAT3)) {
                        model.addAttribute("pejabatKe3", 1);
                    }
                    if (test.getRoleID().equals(Role.PEJABAT4)) {
                        model.addAttribute("pejabatKe4", 1);
                    }
                    pejabatKe += test.getRoleID() + " >> ";
                }

                // model.addAttribute("pejabatKe", pejabatKe); //Untuk Teset
 
                return "transaksi/persetujuanpejabat/persetujuanpejabat_form";
            } else {
                redirectAttributes.addFlashAttribute("msg", "notfound");
            }
        }

        return "redirect:/transaksi/persetujuanpejabat/";
    }

    

    @RequestMapping("/transaksi/persetujuanpejabat/perbaikan")
    @ResponseBody
    public String perbaikan(){
        String result = "Perbaikan desa error";        
        return result;
    }


    public TbPermohonan getNewDomainFromViewXX(TbPermohonan newDomain, TbPermohonan domain) {
        // TbPermohonan newDomain = new TbPermohonan();

        newDomain.setNoRek("");

        newDomain.setTipeLayananBean(domain.getTipeLayananBean());
        // newDomain.setStatusWilayahBean(domain.getStatusWilayahBean());
        newDomain.setRencanaPemanfaatanBean(domain.getRencanaPemanfaatanBean());
        // newDomain.setArahanFungsiLahanBean(domain.getArahanFungsiLahanBean());
        // newDomain.setFungsiLahanSekitarBean(domain.getFungsiLahanSekitarBean());
        // newDomain.setFungsiJalanBean(domain.getFungsiJalanBean());
        // newDomain.setKlasifikasiKegiatanBean(domain.getKlasifikasiKegiatanBean());

        newDomain.setJenisBuktiKepemilikanBean(domain.getJenisBuktiKepemilikanBean());
        newDomain.setNomorKepemilikan(domain.getNomorKepemilikan());

        // newDomain.setPekerjaanBean(domain.getPekerjaanBean());
        // newDomain.setPelakuUsahaBean(domain.getPelakuUsahaBean());

        newDomain.setAlamatDimohon(domain.getAlamatDimohon());
        newDomain.setDesaDimohonBean(domain.getDesaDimohonBean());
        newDomain.setDesaBean(domain.getDesaBean());
        // newDomain.setFuserBean(domain.getFuserBean()); //user tidak dari inputan
        newDomain.setNoKtpPemohon(domain.getNoKtpPemohon());
        newDomain.setAlamatPemohon(domain.getAlamatPemohon());

        // newDomain.setTanggalPermohonan(tanggalPermohonan);
       
        if (domain.getTanggalPermohonan()==null) newDomain.setTanggalPermohonan(LocalDate.now());

        newDomain.setLuasTanahBuktiKepemilikan(domain.getLuasTanahBuktiKepemilikan());
        newDomain.setLuasTanahDimohon(domain.getLuasTanahDimohon());
        newDomain.setLatDimohon(domain.getLatDimohon());
        newDomain.setLonDimohon(domain.getLonDimohon());
        
        /**
         * Block Image File akan dilakukan Penambaan Ulang
         */

         newDomain.setKeterangan(domain.getKeterangan());
         newDomain.setBatasTimur(domain.getBatasTimur());
         newDomain.setBatasSelatan(domain.getBatasSelatan());
         newDomain.setBatasUtara(domain.getBatasUtara());
         newDomain.setBatasBarat(domain.getBatasBarat());

         newDomain.setJarakFasumPendidikan(domain.getJarakFasumPendidikan());
         newDomain.setJarakFasumPerdagangan(domain.getJarakFasumPerdagangan());
         newDomain.setJarakFasumPerkantoran(domain.getJarakFasumPerkantoran());
        //  newDomain.setJarakFasumLain1(domain.getJarakFasumLain1());
        //  newDomain.setJarakFasumLain2(domain.getJarakFasumLain2());
         
         newDomain.setTelpPemohon(domain.getTelpPemohon());


        //  newDomain.setKonflikSosial(domain.isKonflikSosial());
        //  newDomain.setStatusVerifikasi1(domain.getStatusVerifikasi1());
        //  newDomain.setStatusVerifikasi1Notes(domain.getStatusVerifikasi1Notes());
        //  newDomain.setStatusVerifikasi2(domain.getStatusVerifikasi2());
        //  newDomain.setStatusVerifikasi2Notes(domain.getStatusVerifikasi2Notes());
        //  newDomain.setStatusVerifikasi1(domain.getStatusVerifikasi1());

        //  newDomain.setStatusHarusRapat(domain.isStatusHarusRapat() );

        //  newDomain.setStatusPersetujuan1(domain.getStatusPersetujuan1());
        //  newDomain.setStatusPersetujuan1Notes(domain.getStatusPersetujuan1Notes());
        //  newDomain.setStatusPersetujuan2(domain.getStatusPersetujuan2());
        //  newDomain.setStatusPersetujuan2Notes(domain.getStatusPersetujuan2Notes());
        //  newDomain.setStatusPersetujuan3(domain.getStatusPersetujuan3());
        //  newDomain.setStatusPersetujuan3Notes(domain.getStatusPersetujuan3Notes());
        //  newDomain.setStatusPersetujuan4(domain.getStatusPersetujuan4());
        //  newDomain.setStatusPersetujuan4Notes(domain.getStatusPersetujuan4Notes());

        return newDomain;
    }


    public void kirimEmailNotifikasi(TbPermohonan domainUpdate){
         /**
         * SEND EMAIL HERE
         */
        // String emailPemohon = domainUpdate.get

        String userEmail_Validator = securityUtils.getLoginUser().getEmail();
        String pemohonEmail = domainUpdate.getFuserBean().getEmail();
        String mailMessage = "Informasi dari " + Constants.APP_BRAND_NAME + " Bahwa Permohonan Rekomendasi Pemanfaatan Tata Ruang Anda " +
        " Menadapat Response. Silahkan dilihat pada Aplikasi";

        try {
            emailService.sendSimpleMessage(userEmail_Validator, Constants.APP_BRAND_NAME,  mailMessage);
            
        } catch (Exception e) {}
        try {
            emailService.sendSimpleMessage(pemohonEmail, Constants.APP_BRAND_NAME,  mailMessage);
        } catch (Exception e) {}           
    }

    

}
