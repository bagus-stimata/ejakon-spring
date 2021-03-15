package com.desgreen.gov.database.data.source.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_rekomendasi")
public class TbRekomendasi {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id = 0;

    @ManyToOne
    @JoinColumn(name = "permohonan_bean", referencedColumnName = "id")
	private TbPermohonan permohonanBean;

	private String deskripsi = "";
	
	// @Lob	
	@Column(columnDefinition="TEXT")
	private String isiRekom = "";

	private String dibuatOleh = "";
	private String imageDigitasi = "";


	@OneToMany(mappedBy = "rekomendasiBean")
	private List<TbRekomendasiDocs> listRekomendasiDocs;



	@Transient
	private Integer tempInt1 = 0;
	
	@Column(name = "created")
	private LocalDateTime created = LocalDateTime.now();
	@Column(name = "lastmodified")
	private LocalDateTime lastModified = LocalDateTime.now();
	@Column(name = "modified_by")
	private String modifiedBy = "";

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TbRekomendasi other = (TbRekomendasi) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TbRekomendasi [id=" + id + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TbPermohonan getPermohonanBean() {
		return permohonanBean;
	}

	public void setPermohonanBean(TbPermohonan permohonanBean) {
		this.permohonanBean = permohonanBean;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}

	public String getIsiRekom() {
		return isiRekom;
	}

	public void setIsiRekom(String isiRekom) {
		this.isiRekom = isiRekom;
	}

	public String getDibuatOleh() {
		return dibuatOleh;
	}

	public void setDibuatOleh(String dibuatOleh) {
		this.dibuatOleh = dibuatOleh;
	}

	public String getImageDigitasi() {
		return imageDigitasi;
	}

	public void setImageDigitasi(String imageDigitasi) {
		this.imageDigitasi = imageDigitasi;
	}

	public List<TbRekomendasiDocs> getListRekomendasiDocs() {
		return listRekomendasiDocs;
	}

	public void setListRekomendasiDocs(List<TbRekomendasiDocs> listRekomendasiDocs) {
		this.listRekomendasiDocs = listRekomendasiDocs;
	}

	public Integer getTempInt1() {
		return tempInt1;
	}

	public void setTempInt1(Integer tempInt1) {
		this.tempInt1 = tempInt1;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getLastModified() {
		return lastModified;
	}

	public void setLastModified(LocalDateTime lastModified) {
		this.lastModified = lastModified;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	


}