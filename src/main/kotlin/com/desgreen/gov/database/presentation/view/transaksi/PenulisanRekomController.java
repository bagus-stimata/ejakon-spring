package com.desgreen.gov.database.presentation.view.transaksi;

import com.desgreen.gov.database.presentation.SecurityConfig.SecurityUtils;
import com.desgreen.gov.database.data.source.entity.*;
import com.desgreen.gov.database.utils.Constants;
import com.desgreen.gov.database.data.source.local.jpa_dao.*;
import com.desgreen.gov.database.domain.model_enum.Role;
import com.desgreen.gov.database.domain.model_enum.StatusPersetujuan;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
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

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The TodoController Class
 *
 * @author ibrahim KARAYEL
 * @version 1.0 Date 4/27/2018.
 */
@Controller
@ComponentScan
public class PenulisanRekomController {

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
    private TbKlasifikasiKegiatanRepository klasifikasiKegiatanRepository;
    @Autowired
    private TbPekerjaanRepository pekerjaanRepository;
    @Autowired
    private ChatsRepository chatsRepository;
    @Autowired
    private TbKecamatanRepository kecamatanRepository;
    @Autowired
    private TbDesaRepository desaRepository;
    @Autowired
    private TbJenisBuktiKepemilikanRepository jenisBuktiKepemilikanRepository;

    @Autowired
    private TbPermohonanRepository permohonanRepository;
    @Autowired
    private TbRekomendasiRepository rekomendasiRepository;

    private TbPermohonan currentPermohonan = new TbPermohonan();

    @Autowired
    private SecurityUtils securityUtils;

    private List<TbPermohonan> listDesa = new ArrayList<>();
    private List<TbKecamatan> listKecamatan = new ArrayList<>();

    String pageTitle = "";

    @ModelAttribute("getListTipeLayanan")
    public List<TbTipeLayanan> getListTipeLayanan() {
        List<TbTipeLayanan> list = new ArrayList<>();
        for (TbTipeLayanan domain : tipeLayananRepository.findAll()) {
            list.add(domain);
        }
        return list;
    }

    @ModelAttribute("getListRencanaPemanfaatan")
    public List<TbRencanaPemanfaatan> getListRencanaPemanfaatan() {
        List<TbRencanaPemanfaatan> list = new ArrayList<>();
        for (TbRencanaPemanfaatan domain : rencanaPemanfaatanRepository.findAll()) {
            list.add(domain);
        }
        return list;
    }

    @ModelAttribute("getListKecamatan")
    public List<TbKecamatan> getListKecamatan() {
        List<TbKecamatan> list = new ArrayList<>();
        for (TbKecamatan domain : kecamatanRepository.findAll()) {
            list.add(domain);
        }
        return list;
    }

    @ModelAttribute("getListDesa")
    public List<TbDesa> getListDesa() {
        List<TbDesa> list = new ArrayList<>();
        for (TbDesa domain : desaRepository.findAll()) {
            list.add(domain);
        }
        return list;
    }

    @ModelAttribute("getListJenisBuktiKepemilikan")
    public List<TbJenisBuktiKepemilikan> getListJenisBuktiKepemilikan() {
        List<TbJenisBuktiKepemilikan> list = new ArrayList<>();
        for (TbJenisBuktiKepemilikan domain : jenisBuktiKepemilikanRepository.findAll()) {
            list.add(domain);
        }
        return list;
    }

    @ModelAttribute("allStatusPersetujuan")
    public StatusPersetujuan[] getAllStatusPersetujuan() {
        return StatusPersetujuan.values();
    }

    @ModelAttribute("appImagePath")
    public String appImagePath() {
        return Constants.APP_IMAGE_PATH;
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.PEJABAT1 + "', '" + Role.ADMIN + "', '" + Role.ADMIN
            + "', '" + Role.ADMIN + "'})") // Perhatikan hasRole dan hasAnyRole
    @RequestMapping("/transaksi/penulisanrekom")
    public String listIndex(final Model viewModel) {

        final TbPermohonan newDomain = new TbPermohonan();
        viewModel.addAttribute("newDomain", newDomain);

        List<TbPermohonan> list = new ArrayList<>();
        for (TbPermohonan domain : permohonanRepository.findAll()) {
            try {
                if (domain.getStatusPersetujuan1().equals(StatusPersetujuan.VALID)
                        && domain.getStatusPersetujuan2().equals(StatusPersetujuan.VALID)
                        && domain.getStatusPersetujuan3().equals(StatusPersetujuan.VALID)
                        && domain.getStatusPersetujuan4().equals(StatusPersetujuan.VALID)) {

                    domain.setStatusPersetujuanAll(StatusPersetujuan.VALID);

                } else if (domain.getStatusPersetujuan1().equals(StatusPersetujuan.INVALID)
                        || domain.getStatusPersetujuan2().equals(StatusPersetujuan.INVALID)
                        || domain.getStatusPersetujuan3().equals(StatusPersetujuan.INVALID)
                        || domain.getStatusPersetujuan4().equals(StatusPersetujuan.INVALID)) {

                    domain.setStatusPersetujuanAll(StatusPersetujuan.INVALID);

                } else {
                    domain.setStatusPersetujuanAll(StatusPersetujuan.HOLD);
                }
            } catch (Exception e) {
            }

            list.add(domain);
        }

        viewModel.addAttribute("allData",
                list.stream().sorted(Comparator.comparing(TbPermohonan::getTanggalPermohonan).reversed())
                        .collect(Collectors.toList()));

        logger.info("# Form Task");

        pageTitle = Constants.APP_BRAND_NAME;
        viewModel.addAttribute("pageTitle", pageTitle);

        return "transaksi/penulisanrekom/penulisanrekom_list";
    }

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.PEJABAT1 + "', '" + Role.ADMIN + "', '" + Role.ADMIN
            + "', '" + Role.ADMIN + "'})") // Perhatikan hasRole dan hasAnyRole
    @RequestMapping("/transaksi/penulisanrekom/print1/{id}")
    public String print1(HttpServletResponse response, @PathVariable("id") final int id) {
        NumberFormat nf = NumberFormat.getInstance(); nf.setMaximumFractionDigits(0);
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd MMMM yyyy");

        List<ZLapTemplate2> listLapTemplate = new ArrayList<>();

        TbPermohonan domain = permohonanRepository.findById(id);

        ZLapTemplate2 lapTemplate = new ZLapTemplate2();
        lapTemplate.setId((long) 1);
        lapTemplate.setString1(domain.getNamaPemohon());
        lapTemplate.setString2(domain.getAlamatPemohon() + " " + domain.getDesaBean().getDescription() + " " + domain.getDesaBean().getKecamatanBean().getDescription());
        lapTemplate.setString3(domain.getNoRek());

        lapTemplate.setString4(domain.getNomorKepemilikan());

        lapTemplate.setString11("");
        lapTemplate.setString12(domain.getDesaDimohonBean().getDescription() + " " + domain.getDesaDimohonBean().getKecamatanBean().getDescription());
        lapTemplate.setString13(nf.format(domain.getLuasTanahBuktiKepemilikan()) + " M2");
        lapTemplate.setString14(domain.getStatusWilayahBean()!=null? domain.getStatusWilayahBean().getDescription(): "");
        lapTemplate.setString15(domain.getArahanFungsiLahanBean()!=null? domain.getArahanFungsiLahanBean().getDescription(): "");
        lapTemplate.setString16(domain.getFungsiLahanSekitarBean()!=null? domain.getFungsiLahanSekitarBean().getDescription(): "");
        lapTemplate.setString17(domain.getFungsiJalanBean()!=null? domain.getFungsiJalanBean().getDescription(): "");

        lapTemplate.setString18("");
        lapTemplate.setString19(domain.getKlasifikasiKegiatanBean()!=null? domain.getKlasifikasiKegiatanBean().getDescription(): "");
        lapTemplate.setString20("Permohonan harus segera melaporkan/mengajukan/memperbaharui permohonan Rekomendasi Teknis Tata Ruang " + 
            "apabila terjadi perubhan rencana terkait fungsi dan luasan lahan");
 
        lapTemplate.setString21("Proboliggo");
        lapTemplate.setString22(sdf2.format(new Date()));

        listLapTemplate.add(lapTemplate);

        String inputFilePath = "input";
        try{
			final Map parameters=new HashMap();
			
			parameters.put("paramJudulHeader", "PEMERINTAH KABUPATEN PROBOLINGGO");
			parameters.put("paramJudulHeaderSub1", "DINAS PEKERJAAN UMUM DAN PENATAAN RUANG");
			parameters.put("paramJudulHeaderSub2", "Jl. Raya Panglima Sudirman No. 45. Kraksaan");
			parameters.put("paramString1",  "Telp. (0335) 841202 Kraksaan - Probolinggo 67282");
			parameters.put("paramString2",  "REKOMENDASI TEKNIS PEMANFAATAN TATA RUANG");
			parameters.put("paramString3",  "DIBERIKAN ATAS PERMOHONAN DARI");
			parameters.put("paramString4",  "Peraturan Daerah Kabupaten Probolinggo No. 3 Tahun 20111 tentang Rencana Tata Ruang Wilayah (RTRW) Kabupaten Probolinggo");
			
		
            // Fetching the .jrxml file from the resources folder.
//            final InputStream stream = this.getClass().getResourceAsStream("/Users/yhawin/jasper_report/RekomendasiPage1.jrxml");
// ?            InputStream stream = new FileInputStream(new File("/Users/yhawin/jasper_report/RekomendasiPage1.jrxml"));
            InputStream stream = new FileInputStream(new File(Constants.APP_IMAGE_PATH + "/jasper_report/RekomendasiPage1.jrxml"));

            // // Compile the Jasper report from .jrxml to .japser
            final JasperReport report = JasperCompileManager.compileReport(stream);
    
            // // Fetching the employees from the data source.
            final JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(listLapTemplate);
    
    
            // Filling the report with the employee data and additional parameters information.
            final JasperPrint print = JasperFillManager.fillReport(report, parameters, source);
    
            // Users can change as per their project requrirements or can take it as request input requirement.
            // For simplicity, this tutorial will automatically place the file under the "c:" drive.
            // If users want to download the pdf file on the browser, then they need to use the "Content-Disposition" technique.
            // final String filePath = "\\";
            // final String filePath = "/Users/yhawin/";
            final String filePath = Constants.APP_IMAGE_PATH + "/jasper_report/";
            // Export the report to a PDF file.
            JasperExportManager.exportReportToPdfFile(print, filePath + "rekomendasi_report.pdf");	
        
            
	
		} catch(Exception ex){
			ex.printStackTrace();
        }
        
        // ModelAndView model = new ModelAndView();
        // model.setViewName("transaksi/penulisanrekom/penulisanrekom_list");
        // return model;
        return "redirect:/images/rekomendasi_report.pdf";
    }
    
    

    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.PEJABAT1 + "', '" + Role.ADMIN + "', '" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = {"/transaksi/penulisanrekom/save_process"}, method = RequestMethod.POST)
    public String saveProcess(@ModelAttribute("domain") final TbRekomendasi domain, @RequestParam("file_1") MultipartFile file_1,
                              final RedirectAttributes redirectAttributes) {

        if (domain.getTempInt1()==0) {

        }else if (domain.getTempInt1()==1) {
            TbPermohonan domainParent = permohonanRepository.findById(currentPermohonan.getId());            
            if (domainParent != null) {
                domain.setTempInt1(1); //0.New Form, 1.Edit Form, 3.Delete
                TbRekomendasi domainUpdate = new TbRekomendasi();
                if (domainParent.getListRekomendasi().size() >0) {
                    domainUpdate =domainParent.getListRekomendasi().get(0);
                }              
                    
                domainUpdate.setDeskripsi(domain.getDeskripsi());
                domainUpdate.setIsiRekom(domain.getIsiRekom());
                

                //Upload Image
                if (! file_1.isEmpty()) {
                    /**
                     * 1. Hapus dahulu
                     * 2. Ganti yang baru    
                     */
                    try {
                        Path path = Paths.get(Constants.APP_IMAGE_PATH + domainUpdate.getImageDigitasi());
                        Files.deleteIfExists(path);                         
                     } catch (Exception e) {}

                    String newImageName = System.currentTimeMillis() + "__" + file_1.getOriginalFilename();
                    try {
                        byte[] bytes = file_1.getBytes();
                        // Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                        Path path = Paths.get(Constants.APP_IMAGE_PATH + newImageName);
                        Files.write(path, bytes);

                        domainUpdate.setImageDigitasi(newImageName);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


                if (domain.getPermohonanBean()==null) domainUpdate.setPermohonanBean(currentPermohonan);
                rekomendasiRepository.save(domainUpdate);

                redirectAttributes.addFlashAttribute("saveUser", "success");
                redirectAttributes.addFlashAttribute("saveUserInfo", domain.getIsiRekom());

                return "redirect:/transaksi/penulisanrekom/edit_form/" + currentPermohonan.getId();
            }
        }
       
        
       

        return "redirect:/transaksi/penulisanrekom/";
    }


    @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.PEJABAT1 + "', '" + Role.ADMIN + "', '" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping(value = "/transaksi/penulisanrekom/{operation}/{id}", method = RequestMethod.GET)
    public String toFormOperation(@PathVariable("operation") final String operation,
                                @PathVariable("id") final int id, final RedirectAttributes redirectAttributes,
                                final Model model) {

        logger.info("/transaksi/penulisanrekom/operation: {} ", operation);
        pageTitle = Constants.APP_BRAND_NAME;
        model.addAttribute("pageTitle", pageTitle);

       if (operation.equals("delete")) {
            try {
                TbPermohonan domainToDelete = permohonanRepository.findById(id);        
                // hati-hati kecamatanRepository.deleteAll(domainToDelete.getListDesa() );

                //  try {
                //     Path path = Paths.get(AppSetting.APP_IMAGE_PATH + domainToDelete.getImageSuratPermohonan());                    
                //     Files.deleteIfExists(path);                         
                //  } catch (Exception e) {}
                //  try {
                //     Path path = Paths.get(AppSetting.APP_IMAGE_PATH + domainToDelete.getImageKtp());
                //     Files.deleteIfExists(path); 
                //  } catch (Exception e) {}

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
                
                return "transaksi/penulisanrekom/penulisanrekom_form";
            }

        } else if (operation.equals("edit_form")) {

            TbPermohonan domainParent = permohonanRepository.findById(id);
            if (domainParent != null) {
                // domain.setTempInt1(1); //0.New Form, 1.Edit Form, 3.Delete
                TbRekomendasi domain = new TbRekomendasi();
                if (domainParent.getListRekomendasi().size() >0) {
                    domain =domainParent.getListRekomendasi().get(0);
                }
                
                domain.setTempInt1(1);

                domain.setDeskripsi("#" + domainParent.getId() + "#" + domainParent.getNoRek() + "#" + domainParent.getNamaPemohon());
                // domain.setIsiRekom("asssafa fafafa");
                
                currentPermohonan =  domainParent;
                // model.addAttribute("parentId", domain.getPermohonanBean().getId() );                
                model.addAttribute("domain", domain);                

                return "transaksi/penulisanrekom/penulisanrekom_form";    
        } else {
                redirectAttributes.addFlashAttribute("msg", "notfound");
            }
        }

        return "redirect:/transaksi/penulisanrekom/";
    }

    

    @RequestMapping("/transaksi/penulisanrekom/perbaikan")
    @ResponseBody
    public String perbaikan(){
        String result = "Perbaikan desa error";        
        return result;
    }


   
}
