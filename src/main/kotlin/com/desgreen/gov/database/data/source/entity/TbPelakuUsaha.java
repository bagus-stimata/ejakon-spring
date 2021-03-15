package com.desgreen.gov.database.data.source.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_pelaku_usaha")
public class TbPelakuUsaha {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	@Column(name = "kode1", length = 20)
	private String kode1 = "";

	@Column(name = "nama_perusahaan", length = 100)
	private String namaPerusahaan = "";
	@Column(name = "npwp", length = 100)
	private String npwp = "";
	@Column(name = "alamat_perusahaan", length =200)
	private String alamat_perusahaan = "";
	@Column(name = "email_perusahaan", length = 50)
	private String email_perusahaan = "";

	@Column(name = "telp_perusahaan", length = 35)
	private String telpPerusahaan = "";
	@Column(name = "hp_perusahaan", length = 35)
	private String hpPerusahaan = "";
	@Column(name = "nama_pemilik", length = 100)
	private String namaPemilik = "";
	@Column(name = "alamat_pemilik", length = 200)
	private String alamatPemilik = "";
	@Column(name = "tempat_lahir_pemilik", length = 35)
	private String tempatLahirPemilik = "";

	private LocalDate tanggalLahirPemilik = LocalDate.now();
	@Column(name = "whats_app", length = 20)
	private String whastApp = "";

	@OneToMany(mappedBy = "pelakuUsahaBean")
	private List<TbPermohonan> listPermohonan;

	@Transient
	private Integer tempInt1 = 0;

	@Column(name = "created")
	private LocalDateTime created = LocalDateTime.now();
	@Column(name = "lastmodified")
	private LocalDateTime lastModified = LocalDateTime.now();
	@Column(name = "modified_by")
	private String modifiedBy = "";	


	


    /**
     * @return long return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
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
     * @return String return the namaPerusahaan
     */
    public String getNamaPerusahaan() {
        return namaPerusahaan;
    }

    /**
     * @param namaPerusahaan the namaPerusahaan to set
     */
    public void setNamaPerusahaan(String namaPerusahaan) {
        this.namaPerusahaan = namaPerusahaan;
    }

    /**
     * @return String return the npwp
     */
    public String getNpwp() {
        return npwp;
    }

    /**
     * @param npwp the npwp to set
     */
    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    /**
     * @return String return the alamat_perusahaan
     */
    public String getAlamat_perusahaan() {
        return alamat_perusahaan;
    }

    /**
     * @param alamat_perusahaan the alamat_perusahaan to set
     */
    public void setAlamat_perusahaan(String alamat_perusahaan) {
        this.alamat_perusahaan = alamat_perusahaan;
    }

    /**
     * @return String return the email_perusahaan
     */
    public String getEmail_perusahaan() {
        return email_perusahaan;
    }

    /**
     * @param email_perusahaan the email_perusahaan to set
     */
    public void setEmail_perusahaan(String email_perusahaan) {
        this.email_perusahaan = email_perusahaan;
    }

    /**
     * @return String return the telpPerusahaan
     */
    public String getTelpPerusahaan() {
        return telpPerusahaan;
    }

    /**
     * @param telpPerusahaan the telpPerusahaan to set
     */
    public void setTelpPerusahaan(String telpPerusahaan) {
        this.telpPerusahaan = telpPerusahaan;
    }

    /**
     * @return String return the hpPerusahaan
     */
    public String getHpPerusahaan() {
        return hpPerusahaan;
    }

    /**
     * @param hpPerusahaan the hpPerusahaan to set
     */
    public void setHpPerusahaan(String hpPerusahaan) {
        this.hpPerusahaan = hpPerusahaan;
    }

    /**
     * @return String return the namaPemilik
     */
    public String getNamaPemilik() {
        return namaPemilik;
    }

    /**
     * @param namaPemilik the namaPemilik to set
     */
    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    /**
     * @return String return the alamatPemilik
     */
    public String getAlamatPemilik() {
        return alamatPemilik;
    }

    /**
     * @param alamatPemilik the alamatPemilik to set
     */
    public void setAlamatPemilik(String alamatPemilik) {
        this.alamatPemilik = alamatPemilik;
    }

    /**
     * @return String return the tempatLahirPemilik
     */
    public String getTempatLahirPemilik() {
        return tempatLahirPemilik;
    }

    /**
     * @param tempatLahirPemilik the tempatLahirPemilik to set
     */
    public void setTempatLahirPemilik(String tempatLahirPemilik) {
        this.tempatLahirPemilik = tempatLahirPemilik;
    }

    /**
     * @return LocalDate return the tanggalLahirPemilik
     */
    public LocalDate getTanggalLahirPemilik() {
        return tanggalLahirPemilik;
    }

    /**
     * @param tanggalLahirPemilik the tanggalLahirPemilik to set
     */
    public void setTanggalLahirPemilik(LocalDate tanggalLahirPemilik) {
        this.tanggalLahirPemilik = tanggalLahirPemilik;
    }

    /**
     * @return String return the whastApp
     */
    public String getWhastApp() {
        return whastApp;
    }

    /**
     * @param whastApp the whastApp to set
     */
    public void setWhastApp(String whastApp) {
        this.whastApp = whastApp;
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
     * @return Integer return the tempInt1
     */
    public Integer getTempInt1() {
        return tempInt1;
    }

    /**
     * @param tempInt1 the tempInt1 to set
     */
    public void setTempInt1(Integer tempInt1) {
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
		TbPelakuUsaha other = (TbPelakuUsaha) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TbPelakuUsaha [id=" + id + "]";
	}

}