package com.desgreen.gov.database.data.source.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_rapat")
public class TbRapat {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

    @ManyToOne
    @JoinColumn(name = "permohonan_bean", referencedColumnName = "id")
	private TbPermohonan permohonanBean;

	private LocalDate jadwalRapat = LocalDate.now();
	private LocalDate realisasiRapat = LocalDate.now();
	private String pesertaRapat = "";
	private String yangHadirRapat = "";
	private String hasilRapat = "";


	@OneToMany(mappedBy = "rapatBean")
	private List<TbRapatDocs> listRapatDocs;



}