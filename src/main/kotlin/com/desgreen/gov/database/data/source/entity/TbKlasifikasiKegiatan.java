package com.desgreen.gov.database.data.source.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "tb_klasifikasi_kegiatan")
public class TbKlasifikasiKegiatan {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	
	@Column(name = "kode1", length = 20)
	private String kode1 = "";
	@Column(name = "kode2", length = 20)
	private String kode2 = "";
	@Column(name = "description" , length = 75)
	private String description = "";


	@OneToMany(mappedBy = "klasifikasiKegiatanBean")
	private List<TbPermohonan> listPermohonan;

	@Transient
	private int tempInt1 = 0;
	
	@Column(name = "created")
	private LocalDateTime created = LocalDateTime.now();
	@Column(name = "lastmodified")
	private LocalDateTime lastModified = LocalDateTime.now();
	@Column(name = "modified_by")
	private String modifiedBy = "";	
	


    /**
     * @return int return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return String return the kode1
     */
    public String getKode1() {
        return kode1;
    }

    /**
     * @param kode1 the kode1 to set
     */
    public void setKode1(String kode1) {
        this.kode1 = kode1;
    }

    /**
     * @return String return the kode2
     */
    public String getKode2() {
        return kode2;
    }

    /**
     * @param kode2 the kode2 to set
     */
    public void setKode2(String kode2) {
        this.kode2 = kode2;
    }

    /**
     * @return String return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return List<TbPermohonan> return the listPermohonan
     */
    public List<TbPermohonan> getListPermohonan() {
        return listPermohonan;
    }

    /**
     * @param listPermohonan the listPermohonan to set
     */
    public void setListPermohonan(List<TbPermohonan> listPermohonan) {
        this.listPermohonan = listPermohonan;
    }

    /**
     * @return int return the tempInt1
     */
    public int getTempInt1() {
        return tempInt1;
    }

    /**
     * @param tempInt1 the tempInt1 to set
     */
    public void setTempInt1(int tempInt1) {
        this.tempInt1 = tempInt1;
    }

    /**
     * @return LocalDateTime return the created
     */
    public LocalDateTime getCreated() {
        return created;
    }

    /**
     * @param created the created to set
     */
    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    /**
     * @return LocalDateTime return the lastModified
     */
    public LocalDateTime getLastModified() {
        return lastModified;
    }

    /**
     * @param lastModified the lastModified to set
     */
    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * @return String return the modifiedBy
     */
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * @param modifiedBy the modifiedBy to set
     */
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
		TbKlasifikasiKegiatan other = (TbKlasifikasiKegiatan) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TbKlasifikasiKegiatan [id=" + id + "]";
	}

}