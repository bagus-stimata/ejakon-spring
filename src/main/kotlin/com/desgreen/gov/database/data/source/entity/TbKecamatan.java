package com.desgreen.gov.database.data.source.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_kecamatan")
public class TbKecamatan {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id = 0;
	@Column(name = "kode1")
	private String kode1 = "";
	@Column(name = "description")
	private String description = "";

    @ManyToOne
    @JoinColumn(name = "kabupatenBean", referencedColumnName = "id")
	private TbKabupaten kabupatenBean;

    @OneToMany(mappedBy = "kecamatanBean")
	private List<TbDesa> listDesa;
	
	@OneToMany(mappedBy = "kecamatanBean")
	private List<TbKegiatanLokasi> listKegiatanLokasi;

    @OneToMany(mappedBy = "kecamatanBean")
	private List<TbPrasarana> listPrasarana;
    @OneToMany(mappedBy = "kecamatanBean")
	private List<TbSarana> listSarana;

	@Transient
	private int tempInt1 = 0;
	
	@Column(name = "created")
	private LocalDateTime created = LocalDateTime.now();
	@Column(name = "lastmodified")
	private LocalDateTime lastModified = LocalDateTime.now();
	@Column(name = "modified_by")
	private String modifiedBy = "";

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKode1() {
		return kode1;
	}

	public void setKode1(String kode1) {
		this.kode1 = kode1;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TbKabupaten getKabupatenBean() {
		return kabupatenBean;
	}

	public void setKabupatenBean(TbKabupaten kabupatenBean) {
		this.kabupatenBean = kabupatenBean;
	}

	public List<TbDesa> getListDesa() {
		return listDesa;
	}

	public void setListDesa(List<TbDesa> listDesa) {
		this.listDesa = listDesa;
	}


	public List<TbPrasarana> getListPrasarana() {
		return listPrasarana;
	}

	public void setListPrasarana(List<TbPrasarana> listPrasarana) {
		this.listPrasarana = listPrasarana;
	}

	public List<TbSarana> getListSarana() {
		return listSarana;
	}

	public void setListSarana(List<TbSarana> listSarana) {
		this.listSarana = listSarana;
	}

	public int getTempInt1() {
		return tempInt1;
	}

	public void setTempInt1(int tempInt1) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		TbKecamatan other = (TbKecamatan) obj;
		if (id != other.id)
			return false;
		return true;
	}


	
}