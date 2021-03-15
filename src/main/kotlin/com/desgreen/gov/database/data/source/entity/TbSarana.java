package com.desgreen.gov.database.data.source.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_sarana")
public class TbSarana {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id = 0;
	@Column(name = "kode1")
	private String kode1 = "";
	@Column(name = "kode2")
	private String kode2 = "";
	@Column(name = "description")
	private String description = "";
	@Column(name = "tag_lat1")
	private String tagLat1 = "";
	@Column(name = "tagLon1")
	private String tagLon1 = "";
	@Column(name = "tagLat2")
	private double tagLat2 = 0;
	@Column(name = "tagLon2")
	private double tagLon2 = 0;
	@Column(name = "kecamatan2Bean")
	private int kecamatan2Bean;
	@Column(name = "desa2Bean")
	private int desa2Bean = 0;
	@Column(name = "perumahanBean")
	private int perumahanBean = 0;
	@Column(name = "kondisiBean")
	private int kondisiBean;
	@Column(name = "sumberDanaBean")
	private int sumberDanaBean;
	@Column(name = "tahunAnggaran")
	private int tahunAnggaran = 0;
	@Column(name = "imageName")
	private String imageName = "";

	@ManyToOne
    @JoinColumn(name = "jenis_sarana_bean", referencedColumnName = "id")
	private TbJenisSarana jenisSaranaBean;
	@ManyToOne
    @JoinColumn(name = "kecamatan_bean", referencedColumnName = "id")
	private TbKecamatan kecamatanBean;
	@ManyToOne
    @JoinColumn(name = "desa_bean", referencedColumnName = "id")
	private TbDesa desaBean;

	@OneToMany(mappedBy = "saranaBean")
	private List<TbKegiatan> listKegiatan;
	@OneToMany(mappedBy = "saranaBean")
	private List<TbSaranaPics> listSaranaPics;

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
     * @return String return the tagLat1
     */
    public String getTagLat1() {
        return tagLat1;
    }

    /**
     * @param tagLat1 the tagLat1 to set
     */
    public void setTagLat1(String tagLat1) {
        this.tagLat1 = tagLat1;
    }

    /**
     * @return String return the tagLon1
     */
    public String getTagLon1() {
        return tagLon1;
    }

    /**
     * @param tagLon1 the tagLon1 to set
     */
    public void setTagLon1(String tagLon1) {
        this.tagLon1 = tagLon1;
    }

    /**
     * @return double return the tagLat2
     */
    public double getTagLat2() {
        return tagLat2;
    }

    /**
     * @param tagLat2 the tagLat2 to set
     */
    public void setTagLat2(double tagLat2) {
        this.tagLat2 = tagLat2;
    }

    /**
     * @return double return the tagLon2
     */
    public double getTagLon2() {
        return tagLon2;
    }

    /**
     * @param tagLon2 the tagLon2 to set
     */
    public void setTagLon2(double tagLon2) {
        this.tagLon2 = tagLon2;
    }

    /**
     * @return int return the kecamatan2Bean
     */
    public int getKecamatan2Bean() {
        return kecamatan2Bean;
    }

    /**
     * @param kecamatan2Bean the kecamatan2Bean to set
     */
    public void setKecamatan2Bean(int kecamatan2Bean) {
        this.kecamatan2Bean = kecamatan2Bean;
    }

    /**
     * @return int return the desa2Bean
     */
    public int getDesa2Bean() {
        return desa2Bean;
    }

    /**
     * @param desa2Bean the desa2Bean to set
     */
    public void setDesa2Bean(int desa2Bean) {
        this.desa2Bean = desa2Bean;
    }

    /**
     * @return int return the perumahanBean
     */
    public int getPerumahanBean() {
        return perumahanBean;
    }

    /**
     * @param perumahanBean the perumahanBean to set
     */
    public void setPerumahanBean(int perumahanBean) {
        this.perumahanBean = perumahanBean;
    }

    /**
     * @return int return the kondisiBean
     */
    public int getKondisiBean() {
        return kondisiBean;
    }

    /**
     * @param kondisiBean the kondisiBean to set
     */
    public void setKondisiBean(int kondisiBean) {
        this.kondisiBean = kondisiBean;
    }

    /**
     * @return int return the sumberDanaBean
     */
    public int getSumberDanaBean() {
        return sumberDanaBean;
    }

    /**
     * @param sumberDanaBean the sumberDanaBean to set
     */
    public void setSumberDanaBean(int sumberDanaBean) {
        this.sumberDanaBean = sumberDanaBean;
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
     * @return String return the imageName
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * @param imageName the imageName to set
     */
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    /**
     * @return TbJenisSarana return the jenisSaranaBean
     */
    public TbJenisSarana getJenisSaranaBean() {
        return jenisSaranaBean;
    }

    /**
     * @param jenisSaranaBean the jenisSaranaBean to set
     */
    public void setJenisSaranaBean(TbJenisSarana jenisSaranaBean) {
        this.jenisSaranaBean = jenisSaranaBean;
    }

    /**
     * @return TbKecamatan return the kecamatanBean
     */
    public TbKecamatan getKecamatanBean() {
        return kecamatanBean;
    }

    /**
     * @param kecamatanBean the kecamatanBean to set
     */
    public void setKecamatanBean(TbKecamatan kecamatanBean) {
        this.kecamatanBean = kecamatanBean;
    }

    /**
     * @return TbDesa return the desaBean
     */
    public TbDesa getDesaBean() {
        return desaBean;
    }

    /**
     * @param desaBean the desaBean to set
     */
    public void setDesaBean(TbDesa desaBean) {
        this.desaBean = desaBean;
    }

    /**
     * @return List<TbKegiatan> return the listKegiatan
     */
    public List<TbKegiatan> getListKegiatan() {
        return listKegiatan;
    }

    /**
     * @param listKegiatan the listKegiatan to set
     */
    public void setListKegiatan(List<TbKegiatan> listKegiatan) {
        this.listKegiatan = listKegiatan;
    }

    /**
     * @return List<TbSaranaPics> return the listSaranaPics
     */
    public List<TbSaranaPics> getListSaranaPics() {
        return listSaranaPics;
    }

    /**
     * @param listSaranaPics the listSaranaPics to set
     */
    public void setListSaranaPics(List<TbSaranaPics> listSaranaPics) {
        this.listSaranaPics = listSaranaPics;
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
		TbSarana other = (TbSarana) obj;
		if (id != other.id)
			return false;
		return true;
	}

}