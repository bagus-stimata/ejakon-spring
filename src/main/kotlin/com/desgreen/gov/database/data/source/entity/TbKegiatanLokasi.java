package com.desgreen.gov.database.data.source.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_kegiatan_lokasi")
public class TbKegiatanLokasi {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id = 0;

	@Column(name = "tag_lat1")
	private double tagLat1 = 0;
	@Column(name = "tag_lon1")
	private double tagLon1 = 0;
	@Column(name = "tag_lat2")
	private double tagLat2 = 0;
	@Column(name = "tag_lon2")
	private double tagLon2 = 0;
	@Column(name = "image_name1")
	private String imageName1 = "";
	@Column(name = "image_name2")
	private String imageName2 = "";
	@Column(name = "image_name3")
	private String imageName3 = "";
	@Column(name = "kondisi1")
	private int kondisi1 = 0;
	@Column(name = "kondisi2")
	private int kondisi2 = 0;
	
    @ManyToOne
    @JoinColumn(name = "kegiatan_bean", referencedColumnName = "id")
	private TbKegiatan kegiatanBean;

    @ManyToOne
    @JoinColumn(name = "kecamatan_bean", referencedColumnName = "id")
	private TbKecamatan kecamatanBean;
    
    @ManyToOne
    @JoinColumn(name = "desa_bean", referencedColumnName = "id")
	private TbDesa desaBean;


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
     * @return double return the tagLat1
     */
    public double getTagLat1() {
        return tagLat1;
    }

    /**
     * @param tagLat1 the tagLat1 to set
     */
    public void setTagLat1(double tagLat1) {
        this.tagLat1 = tagLat1;
    }

    /**
     * @return double return the tagLon1
     */
    public double getTagLon1() {
        return tagLon1;
    }

    /**
     * @param tagLon1 the tagLon1 to set
     */
    public void setTagLon1(double tagLon1) {
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
     * @return String return the imageName1
     */
    public String getImageName1() {
        return imageName1;
    }

    /**
     * @param imageName1 the imageName1 to set
     */
    public void setImageName1(String imageName1) {
        this.imageName1 = imageName1;
    }

    /**
     * @return String return the imageName2
     */
    public String getImageName2() {
        return imageName2;
    }

    /**
     * @param imageName2 the imageName2 to set
     */
    public void setImageName2(String imageName2) {
        this.imageName2 = imageName2;
    }

    /**
     * @return String return the imageName3
     */
    public String getImageName3() {
        return imageName3;
    }

    /**
     * @param imageName3 the imageName3 to set
     */
    public void setImageName3(String imageName3) {
        this.imageName3 = imageName3;
    }

    /**
     * @return int return the kondisi1
     */
    public int getKondisi1() {
        return kondisi1;
    }

    /**
     * @param kondisi1 the kondisi1 to set
     */
    public void setKondisi1(int kondisi1) {
        this.kondisi1 = kondisi1;
    }

    /**
     * @return int return the kondisi2
     */
    public int getKondisi2() {
        return kondisi2;
    }

    /**
     * @param kondisi2 the kondisi2 to set
     */
    public void setKondisi2(int kondisi2) {
        this.kondisi2 = kondisi2;
    }

    /**
     * @return TbKegiatan return the kegiatanBean
     */
    public TbKegiatan getKegiatanBean() {
        return kegiatanBean;
    }

    /**
     * @param kegiatanBean the kegiatanBean to set
     */
    public void setKegiatanBean(TbKegiatan kegiatanBean) {
        this.kegiatanBean = kegiatanBean;
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
		TbKegiatanLokasi other = (TbKegiatanLokasi) obj;
		if (id != other.id)
			return false;
		return true;
	}

}