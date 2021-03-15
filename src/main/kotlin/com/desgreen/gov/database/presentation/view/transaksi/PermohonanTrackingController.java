package com.desgreen.gov.database.presentation.view.transaksi;

import com.desgreen.gov.database.presentation.SecurityConfig.SecurityUtils;
import com.desgreen.gov.database.utils.Constants;
import com.desgreen.gov.database.data.source.local.jpa_dao.*;
import com.desgreen.gov.database.data.source.entity.TbKecamatan;
import com.desgreen.gov.database.data.source.entity.TbPermohonan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


/**
 * The TodoController  Class
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 4/27/2018.
 */
@Controller
@ComponentScan
public class PermohonanTrackingController {

    private static final Logger logger = LoggerFactory.getLogger(PermohonanRekomController.class);

    @Autowired
    private TbTipeLayananRepository tipeLayananRepository;
    @Autowired
    private TbPelakuUsahaRepository pelakuUsahaRepository;
    @Autowired
    private TbRencanaPemanfaatanRepository kegiatanDimohonRepository;
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
    private TbPermohonanRepository desaRepository;


    @Autowired
    private TbPermohonanRepository permohonanRepository;



    @Autowired
    private SecurityUtils securityUtils;


    private List<TbPermohonan> listDesa = new ArrayList<>();
    private List<TbKecamatan> listKecamatan = new ArrayList<>();

    
    String pageTitle = "";


    // @PreAuthorize("hasAnyRole({'" + Role.ADMIN + "', '" + Role.ADMIN + "'})") //Perhatikan hasRole dan hasAnyRole
    @RequestMapping("/transaksi/permohonan_tracking/{id}")
    public String listIndex(@PathVariable("id") final long id,  Model viewModel) {

    

        TbPermohonan domainToTrack = permohonanRepository.findById(id);      
        // final TbPermohonan newDomain =new TbPermohonan();

        viewModel.addAttribute("domain", domainToTrack);
        // viewModel.addAttribute("allData", desaRepository.findAll());

        logger.info("# Form Task");

        pageTitle = Constants.APP_BRAND_NAME;
        viewModel.addAttribute("pageTitle", pageTitle);

        return "transaksi/permohonan_tracking/permohonan_tracking"; 
    }



 
    @RequestMapping("/transaksi/permohonan_tracking/perbaikan")
    @ResponseBody
    public String perbaikan(){
        String result = "Perbaikan desa error";        
        return result;
    }

}
