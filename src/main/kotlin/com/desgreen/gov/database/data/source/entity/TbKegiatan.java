package com.desgreen.gov.database.data.source.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_kegiatan")
public class TbKegiatan {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id = 0;
	@Column(name = "kode1")
	private String kode1 = "";
	@Column(name = "kode2")
	private String kode2 = "";
	@Column(name = "description1")
	private String description1 = "";
	@Column(name = "description2")
	private String description2 = "";
	@Column(name = "opd_bean")
	private int opdBean;
	@Column(name = "jenis_sarana_bean")
	private int jenisSaranaBean;
	@Column(name = "tahun_anggaran")
	private int tahunAnggaran = 0;
	@Column(name = "nilai_pagu")
	private double nilaiPagu = 0;

    @ManyToOne
    @JoinColumn(name = "opd_bidang_bean", referencedColumnName = "id")
	private TbOpdBidang opdBidangBean;
    @ManyToOne
    @JoinColumn(name = "sarana_bean", referencedColumnName = "id")
	private TbSarana saranaBean;
    @ManyToOne
    @JoinColumn(name = "prasarana_bean", referencedColumnName = "id")
	private TbPrasarana prasaranaBean;
    @ManyToOne
    @JoinColumn(name = "jenis_prasarana_bean", referencedColumnName = "id")
	private TbJenisPrasarana jenisPrasaranaBean;

    @ManyToOne
    @JoinColumn(name = "sumber_dana_bean", referencedColumnName = "id")
	private TbSumberDana sumberDanaBean;
    @ManyToOne
    @JoinColumn(name = "kegiatan_jenis_bean", referencedColumnName = "id")
	private TbKegiatanJenis kegiatanJenisBean;

	@OneToMany(mappedBy = "kegiatanBean")
	private List<TbKegiatanPics> listKegiatanPics;
	@OneToMany(mappedBy = "kegiatanBean")
	private List<TbKegiatanLokasi> listKegiatanLokasi;
	
	@Transient
	private int tempInt1 = 0;
	
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
     * @return String return the description1
     */
    public String getDescription1() {
        return description1;
    }

    /**
     * @param description1 the description1 to set
     */
    public void setDescription1(String description1) {
        this.description1 = description1;
    }

    /**
     * @return String return the description2
     */
    public String getDescription2() {
        return description2;
    }

    /**
     * @param description2 the description2 to set
     */
    public void setDescription2(String description2) {
        this.description2 = description2;
    }

    /**
     * @return int return the opdBean
     */
    public int getOpdBean() {
        return opdBean;
    }

    /**
     * @param opdBean the opdBean to set
     */
    public void setOpdBean(int opdBean) {
        this.opdBean = opdBean;
    }

    /**
     * @return int return the jenisSaranaBean
     */
    public int getJenisSaranaBean() {
        return jenisSaranaBean;
    }

    /**
     * @param jenisSaranaBean the jenisSaranaBean to set
     */
    public void setJenisSaranaBean(int jenisSaranaBean) {
        this.jenisSaranaBean = jenisSaranaBean;
    }

    /**
     * @return int return the tahunAnggaran
     */
    public int getTahunAnggaran() {
        return tahunAnggaran;
    }

    /**
     * @param tahunAnggaran the tahunAnggaran to set
     */
    public void setTahunAnggaran(int tahunAnggaran) {
        this.tahunAnggaran = tahunAnggaran;
    }

    /**
     * @return double return the nilaiPagu
     */
    public double getNilaiPagu() {
        return nilaiPagu;
    }

    /**
     * @param nilaiPagu the nilaiPagu to set
     */
    public void setNilaiPagu(double nilaiPagu) {
        this.nilaiPagu = nilaiPagu;
    }

    /**
     * @return TbOpdBidang return the opdBidangBean
     */
    public TbOpdBidang getOpdBidangBean() {
        return opdBidangBean;
    }

    /**
     * @param opdBidangBean the opdBidangBean to set
     */
    public void setOpdBidangBean(TbOpdBidang opdBidangBean) {
        this.opdBidangBean = opdBidangBean;
    }

    /**
     * @return TbSarana return the saranaBean
     */
    public TbSarana getSaranaBean() {
        return saranaBean;
    }

    /**
     * @param saranaBean the saranaBean to set
     */
    public void setSaranaBean(TbSarana saranaBean) {
        this.saranaBean = saranaBean;
    }

    /**
     * @return TbPrasarana return the prasaranaBean
     */
    public TbPrasarana getPrasaranaBean() {
        return prasaranaBean;
    }

    /**
     * @param prasaranaBean the prasaranaBean to set
     */
    public void setPrasaranaBean(TbPrasarana prasaranaBean) {
        this.prasaranaBean = prasaranaBean;
    }

    /**
     * @return TbJenisPrasarana return the jenisPrasaranaBean
     */
    public TbJenisPrasarana getJenisPrasaranaBean() {
        return jenisPrasaranaBean;
    }

    /**
     * @param jenisPrasaranaBean the jenisPrasaranaBean to set
     */
    public void setJenisPrasaranaBean(TbJenisPrasarana jenisPrasaranaBean) {
        this.jenisPrasaranaBean = jenisPrasaranaBean;
    }

    /**
     * @return TbSumberDana return the sumberDanaBean
     */
    public TbSumberDana getSumberDanaBean() {
        return sumberDanaBean;
    }

    /**
     * @param sumberDanaBean the sumberDanaBean to set
     */
    public void setSumberDanaBean(TbSumberDana sumberDanaBean) {
        this.sumberDanaBean = sumberDanaBean;
    }

    /**
     * @return TbKegiatanJenis return the kegiatanJenisBean
     */
    public TbKegiatanJenis getKegiatanJenisBean() {
        return kegiatanJenisBean;
    }

    /**
     * @param kegiatanJenisBean the kegiatanJenisBean to set
     */
    public void setKegiatanJenisBean(TbKegiatanJenis kegiatanJenisBean) {
        this.kegiatanJenisBean = kegiatanJenisBean;
    }

    /**
     * @return List<TbKegiatanPics> return the listKegiatanPics
     */
    public List<TbKegiatanPics> getListKegiatanPics() {
        return listKegiatanPics;
    }

    /**
     * @param listKegiatanPics the listKegiatanPics to set
     */
    public void setListKegiatanPics(List<TbKegiatanPics> listKegiatanPics) {
        this.listKegiatanPics = listKegiatanPics;
    }

    /**
     * @return List<TbKegiatanLokasi> return the listKegiatanLokasi
     */
    public List<TbKegiatanLokasi> getListKegiatanLokasi() {
        return listKegiatanLokasi;
    }

    /**
     * @param listKegiatanLokasi the listKegiatanLokasi to set
     */
    public void setListKegiatanLokasi(List<TbKegiatanLokasi> listKegiatanLokasi) {
        this.listKegiatanLokasi = listKegiatanLokasi;
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
		TbKegiatan other = (TbKegiatan) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
}