package com.desgreen.gov.database.data.source.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_chats")
public class Chats {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

    @ManyToOne
    @JoinColumn(name = "user_bean", referencedColumnName = "id")
	private FUser userBean;
    @ManyToOne
    @JoinColumn(name = "permohonan_bean", referencedColumnName = "id")
	private TbPermohonan permohonanBean;

	private LocalDateTime waktu = LocalDateTime.now();
	private String message = "";
	private boolean statusRead = false;



	@Transient
	private int tempInt1 = 0;
	
	@Column(name = "created")
	private LocalDateTime created = LocalDateTime.now();
	@Column(name = "lastmodified")
	private LocalDateTime lastModified = LocalDateTime.now();
	@Column(name = "modified_by")
	private String modifiedBy = "";	
}