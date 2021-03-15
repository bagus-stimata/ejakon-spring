package com.desgreen.gov.database.data.source.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_rekomendasi_template")
public class TbRekomendasiTemplate {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id = 0;
	
	@Column(name = "kode1", length = 20)
	private String kode1 = "";
	@Column(name = "kode2", length = 20)
	private String kode2 = "";
	@Column(name = "description" , length = 75)
	private String description = "";

	// @Lob	
	@Column(columnDefinition="TEXT")
	private String isiRekom = "";



	@Transient
	private int tempInt1 = 0;
	
	@Column(name = "created")
	private LocalDateTime created = LocalDateTime.now();
	@Column(name = "lastmodified")
	private LocalDateTime lastModified = LocalDateTime.now();
	@Column(name = "modified_by")
	private String modifiedBy = "";	


}