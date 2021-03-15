package com.desgreen.gov.database.data.source.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_rekomendasi_docs")
public class TbRekomendasiDocs {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id = 0;
	private String description = "";
	private String filePath = "";

    @ManyToOne
    @JoinColumn(name = "rekomendasi_bean", referencedColumnName = "id")
	private TbRekomendasi rekomendasiBean;


	@Transient
	private int tempInt1 = 0;
	
	@Column(name = "created")
	private LocalDateTime created = LocalDateTime.now();
	@Column(name = "lastmodified")
	private LocalDateTime lastModified = LocalDateTime.now();
	@Column(name = "modified_by")
	private String modifiedBy = "";	

	
}