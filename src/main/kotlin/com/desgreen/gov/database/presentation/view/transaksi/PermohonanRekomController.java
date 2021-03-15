package com.desgreen.gov.database.presentation.view.transaksi;

import com.desgreen.gov.database.presentation.SecurityConfig.SecurityUtils;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
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
public class PermohonanRekomController {

    private static final Logger logger = LoggerFactory.getLogger(PermohonanRekomController.class);

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

    @ModelAttribute("appImagePath")
    public String appImagePath(){
        return Constants.APP_IMAGE_PATH;
    }
   
    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.USER1 + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping("/transaksi/permohonanrekom")
    public String listIndex(final Model viewModel) {

  
        final TbPermohonan newDomain =new TbPermohonan();
        viewModel.addAttribute("newDomain", newDomain);

        List<TbPermohonan> list = new ArrayList<>();

        boolean isAdmin = false;        
        for (FUserRoles test: securityUtils.getLoginUser().getFuserRoles()) {
            if (test.getRoleID().equals(Role.ADMIN) || test.getRoleID().equals(Role.ADMIN_1) || test.getRoleID().equals(Role.ADMIN_2) || test.getRoleID().equals(Role.PEJABAT1)) {
                isAdmin = true;
                break;
            }
        }
        if (isAdmin) {
            list = permohonanRepository.findAll();
        }else {
            list = permohonanRepository.findByUserId(securityUtils.getLoginUser().getId());
        }

        List<TbPermohonan> newList = new ArrayList<>();
        for (TbPermohonan domain: list) {          
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

            newList.add(domain);
        }
       
        viewModel.addAttribute("allData",  newList.stream().sorted(Comparator.comparing(TbPermohonan::getTanggalPermohonan).reversed()).collect(Collectors.toList()));

        logger.info("# Form Task");

        pageTitle = Constants.APP_BRAND_NAME;
        viewModel.addAttribute("pageTitle", pageTitle);

        return "transaksi/permohonanrekom/permohonanrekom_list"; 
    }


    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.USER1 + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = {"/transaksi/permohonanrekom/save_process"}, method = RequestMethod.POST)
    public String saveProcess(@ModelAttribute("domain") final TbPermohonan domain,
             @RequestParam("file_1") MultipartFile file_1, @RequestParam("file_2") MultipartFile file_2, 
             @RequestParam("file_3") MultipartFile file_3, @RequestParam("file_4") MultipartFile file_4, 
             @RequestParam("file_5") MultipartFile file_5, @RequestParam("file_6") MultipartFile file_6, 
             @RequestParam("file_7") MultipartFile file_7, @RequestParam("file_8") MultipartFile file_8, 
                final RedirectAttributes redirectAttributes) {

        if (domain.getTempInt1()==0) {
            TbPermohonan newDomain = new TbPermohonan();
            newDomain = getNewDomainFromView(newDomain, domain);
        
            newDomain.setTempInt1(domain.getTempInt1()); //0.New Form, 1.Edit Form, 3.Delete
            newDomain.setFuserBean(securityUtils.getLoginUser()); //Untuk NEW SAJA (edit tidak boleh di edit)

             //Upload Image
             if (! file_1.isEmpty()) {
                String newImageName = System.currentTimeMillis() + "__" + file_1.getOriginalFilename();
                try {
                    byte[] bytes = file_1.getBytes();
                    // Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                    Path path = Paths.get(Constants.APP_IMAGE_PATH + newImageName);
                    Files.write(path, bytes);

                    newDomain.setImageSuratPermohonan(newImageName);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (! file_2.isEmpty()) {
                String newImageName = System.currentTimeMillis() + "__" + file_2.getOriginalFilename();
                try {
                    byte[] bytes = file_2.getBytes();
                    Path path = Paths.get(Constants.APP_IMAGE_PATH + newImageName);
                    Files.write(path, bytes);
                    newDomain.setImageKtp(newImageName);
                } catch (IOException e) {e.printStackTrace(); }
            }
            if (! file_3.isEmpty()) {
                String newImageName = System.currentTimeMillis() + "__" + file_3.getOriginalFilename();
                try {
                    byte[] bytes = file_3.getBytes();
                    Path path = Paths.get(Constants.APP_IMAGE_PATH + newImageName);
                    Files.write(path, bytes);
                    newDomain.setImageKk(newImageName);
                } catch (IOException e) {e.printStackTrace(); }
            }
            if (! file_4.isEmpty()) {
                String newImageName = System.currentTimeMillis() + "__" + file_4.getOriginalFilename();
                try {
                    byte[] bytes = file_4.getBytes();
                    Path path = Paths.get(Constants.APP_IMAGE_PATH + newImageName);
                    Files.write(path, bytes);
                    newDomain.setImageBuktiKepemilikan(newImageName);
                } catch (IOException e) {e.printStackTrace(); }
            }
            if (! file_5.isEmpty()) {
                String newImageName = System.currentTimeMillis() + "__" + file_5.getOriginalFilename();
                try {
                    byte[] bytes = file_5.getBytes();
                    Path path = Paths.get(Constants.APP_IMAGE_PATH + newImageName);
                    Files.write(path, bytes);
                    newDomain.setImageSuratKeterangan(newImageName);
                } catch (IOException e) {e.printStackTrace(); }
            }
            if (! file_6.isEmpty()) {
                String newImageName = System.currentTimeMillis() + "__" + file_6.getOriginalFilename();
                try {
                    byte[] bytes = file_6.getBytes();
                    Path path = Paths.get(Constants.APP_IMAGE_PATH + newImageName);
                    Files.write(path, bytes);
                    newDomain.setImageAktePendirianPerusahaan(newImageName);
                } catch (IOException e) {e.printStackTrace(); }
            }
            if (! file_7.isEmpty()) {
                String newImageName = System.currentTimeMillis() + "__" + file_7.getOriginalFilename();
                try {
                    byte[] bytes = file_7.getBytes();
                    Path path = Paths.get(Constants.APP_IMAGE_PATH + newImageName);
                    Files.write(path, bytes);
                    newDomain.setImageBuktiPelunasanPbb(newImageName);
                } catch (IOException e) {e.printStackTrace(); }
            }
            if (! file_8.isEmpty()) {
                String newImageName = System.currentTimeMillis() + "__" + file_8.getOriginalFilename();
                try {
                    byte[] bytes = file_8.getBytes();
                    Path path = Paths.get(Constants.APP_IMAGE_PATH + newImageName);
                    Files.write(path, bytes);
                    newDomain.setImageHandDrawLokasi(newImageName);
                } catch (IOException e) {e.printStackTrace(); }
            }


            if (permohonanRepository.save(newDomain) != null) {                
                newDomain.setTempInt1(1);
               

                redirectAttributes.addFlashAttribute("saveUser", "success");
            } else {
                redirectAttributes.addFlashAttribute("saveUser", "fail");
            }

    
            return "redirect:/transaksi/permohonanrekom/edit_form/" + newDomain.getId();

        } else if (domain.getTempInt1()==1) {
            TbPermohonan domainUpdate = permohonanRepository.findById(domain.getId() );
            domainUpdate = getNewDomainFromView(domainUpdate, domain);

            domainUpdate.setTempInt1(domain.getTempInt1()); //0.New Form, 1.Edit Form, 3.Delete

            permohonanRepository.save(domainUpdate);

            redirectAttributes.addFlashAttribute("saveUser", "success");
            
            return "redirect:/transaksi/permohonanrekom/edit_form/" + domainUpdate.getId();
        }
       
    
        return "redirect:/transaksi/permohonanrekom/";
    }


    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.USER1 + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = "/transaksi/permohonanrekom/{operation}/{id}", method = RequestMethod.GET)
    public String toFormOperation(@PathVariable("operation") final String operation,
                                @PathVariable("id") final int id, final RedirectAttributes redirectAttributes,
                                final Model model) {

        logger.info("/transaksi/permohonanrekom/operation: {} ", operation);
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
                
                return "transaksi/permohonanrekom/permohonanrekom_form";
            }

        } else if (operation.equals("edit_form")) {

            TbPermohonan domain = permohonanRepository.findById(id);
            if (domain != null) {
                domain.setTempInt1(1); //0.New Form, 1.Edit Form, 3.Delete
              
             
                model.addAttribute("domain", domain);           
                
                             

                return "transaksi/permohonanrekom/permohonanrekom_form";
            } else {
                redirectAttributes.addFlashAttribute("msg", "notfound");
            }
        }

        return "redirect:/transaksi/permohonanrekom/";
    }

    

    @RequestMapping("/transaksi/permohonanrekom/perbaikan")
    @ResponseBody
    public String perbaikan(){
        String result = "Perbaikan desa error";        
        return result;
    }


    public TbPermohonan getNewDomainFromView(TbPermohonan newDomain, TbPermohonan domain) {
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
        // newDomain.setFuserBean(domain.getFuserBean()); //Hanya untuk Save New Saja Warning
        newDomain.setNoKtpPemohon(domain.getNoKtpPemohon());

        newDomain.setNamaPemohon(domain.getNamaPemohon());
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

}
