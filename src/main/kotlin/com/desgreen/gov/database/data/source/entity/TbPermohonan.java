package com.desgreen.gov.database.data.source.entity;

import com.desgreen.gov.database.domain.model_enum.StatusPersetujuan;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_permohonan")
public class TbPermohonan {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	//Nomor Permohonan
	@Column(name = "no_rek", length = 25)
	private String  noRek = "";
	// @Column(name = "nomor_permohonan", length = 25)
	// private String noPermohonan = "";

    @ManyToOne
    @JoinColumn(name = "tipe_layanan_bean", referencedColumnName = "id")
	private TbTipeLayanan tipeLayananBean;
	
    @ManyToOne
    @JoinColumn(name = "status_wilayah_bean", referencedColumnName = "id")
	private TbStatusWilayah statusWilayahBean;
	
	@ManyToOne
    @JoinColumn(name = "rencana_pemanfaatan_bean", referencedColumnName = "id")
	private TbRencanaPemanfaatan rencanaPemanfaatanBean;
	
	@ManyToOne
    @JoinColumn(name = "arahan_fungsi_lahan_bean", referencedColumnName = "id")
	private TbArahanFungsiLahan arahanFungsiLahanBean;
    @ManyToOne
    @JoinColumn(name = "fungsi_lahan_sekitar_bean", referencedColumnName = "id")
	private TbFungsiLahanSekitar fungsiLahanSekitarBean;
    @ManyToOne
    @JoinColumn(name = "fungsi_jalan_bean", referencedColumnName = "id")
	private TbFungsiJalan fungsiJalanBean;
    @ManyToOne
    @JoinColumn(name = "klasifikasi_kegiatan_bean", referencedColumnName = "id")
	private TbKlasifikasiKegiatan klasifikasiKegiatanBean;

    @ManyToOne
    @JoinColumn(name = "jenis_bukti_kepemilikan_bean", referencedColumnName = "id")
	private TbJenisBuktiKepemilikan jenisBuktiKepemilikanBean;

    @ManyToOne
    @JoinColumn(name = "pekerjaan_bean", referencedColumnName = "id")
	private TbPekerjaan pekerjaanBean;


    @ManyToOne
    @JoinColumn(name = "pelaku_usaha_bean", referencedColumnName = "id")
	private TbPelakuUsaha pelakuUsahaBean;


	@Column(name = "alamat_dimohon", length = 20)
	private String alamatDimohon = "";
	
	@ManyToOne
    @JoinColumn(name = "desa_dimohon_bean", referencedColumnName = "id")
	private TbDesa desaDimohonBean;
	@ManyToOne
    @JoinColumn(name = "desa_bean", referencedColumnName = "id")
	private TbDesa desaBean;


    @ManyToOne
    @JoinColumn(name = "fuser_bean", referencedColumnName = "id")
	private FUser fuserBean;

	@Column(name = "nomor_ktp_pemohon", length = 25)
	private String noKtpPemohon = "";
	@Column(name = "namaPemohon", length = 55)
	private String namaPemohon = "";
	@Column(name = "alamatPemohon", length = 75)
	private String alamatPemohon = "";
	@Column(name = "telpPemohon", length = 25)
	private String telpPemohon = "";
	@Column(name = "emailPemohon", length = 45)
	private String emailPemohon = "";



	@Column(name = "nomor_kepemilikan", length = 50)
	private String nomorKepemilikan = "";


	private LocalDate tanggalPermohonan = LocalDate.now();


	private double luasTanahBuktiKepemilikan = 0;
	private double luasTanahDimohon = 0;
	private double latDimohon = 0;
	private double lonDimohon = 0;

	private String imageSuratPermohonan = "";
	private String imageKtp = "";
	private String imageKk = "";
	private String imageBuktiKepemilikan = "";
	private String imageSuratKeterangan = "";
	private String imageAktePendirianPerusahaan = "";
	private String imageBuktiPelunasanPbb = "";
	private String imageHandDrawLokasi = "";

	private String keterangan = "";
	@Column(name = "batas_timur", length = 100)
	private String batasTimur = "";
	@Column(name = "batas_selatan", length = 100)
	private String batasSelatan = "";
	@Column(name = "batas_barat", length = 100)
	private String batasBarat = "";
	@Column(name = "batas_utara", length = 100)
	private String batasUtara = "";
	private Integer jarakFasumPendidikan = 0;
	private Integer jarakFasumPerdagangan = 0;
	private Integer jarakFasumPerkantoran = 0;
	private Integer jarakFasumLain1 = 0;
	private Integer jarakFasumLain2 = 0;

	private boolean konflikSosial = false;
	@Enumerated(EnumType.ORDINAL)
	private StatusPersetujuan statusVerifikasi1 =  StatusPersetujuan.HOLD;
	private String statusVerifikasi1Notes = "";
	@Enumerated(EnumType.ORDINAL)
	private StatusPersetujuan statusVerifikasi2 =  StatusPersetujuan.HOLD;
	private String statusVerifikasi2Notes = "";

	@Column(name = "status_harus_rapat")
	private boolean statusHarusRapat;


	@Enumerated(EnumType.ORDINAL)
	private StatusPersetujuan statusPersetujuan1 = StatusPersetujuan.HOLD;
	private String statusPersetujuan1Notes = "";
	@Enumerated(EnumType.ORDINAL)
	private StatusPersetujuan statusPersetujuan2= StatusPersetujuan.HOLD;
	private String statusPersetujuan2Notes = "";
	@Enumerated(EnumType.ORDINAL)
	private StatusPersetujuan statusPersetujuan3= StatusPersetujuan.HOLD;
	private String statusPersetujuan3Notes = "";
	@Enumerated(EnumType.ORDINAL)
	private StatusPersetujuan statusPersetujuan4= StatusPersetujuan.HOLD;
	private String statusPersetujuan4Notes = "";

	@Transient
	private StatusPersetujuan statusPersetujuanAll = StatusPersetujuan.HOLD;

	@OneToMany(mappedBy = "permohonanBean")
	private List<TbRapat> listRapat;

	@OneToMany(mappedBy = "permohonanBean")
	private List<Chats> listChats;

	@OneToMany(mappedBy = "permohonanBean")
	private List<TbRekomendasi> listRekomendasi;	

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
		TbPermohonan other = (TbPermohonan) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TbPermohonan [id=" + id + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNoRek() {
		return noRek;
	}

	public void setNoRek(String noRek) {
		this.noRek = noRek;
	}

	public TbTipeLayanan getTipeLayananBean() {
		return tipeLayananBean;
	}

	public void setTipeLayananBean(TbTipeLayanan tipeLayananBean) {
		this.tipeLayananBean = tipeLayananBean;
	}

	public TbStatusWilayah getStatusWilayahBean() {
		return statusWilayahBean;
	}

	public void setStatusWilayahBean(TbStatusWilayah statusWilayahBean) {
		this.statusWilayahBean = statusWilayahBean;
	}

	public TbRencanaPemanfaatan getRencanaPemanfaatanBean() {
		return rencanaPemanfaatanBean;
	}

	public void setRencanaPemanfaatanBean(TbRencanaPemanfaatan rencanaPemanfaatanBean) {
		this.rencanaPemanfaatanBean = rencanaPemanfaatanBean;
	}

	public TbArahanFungsiLahan getArahanFungsiLahanBean() {
		return arahanFungsiLahanBean;
	}

	public void setArahanFungsiLahanBean(TbArahanFungsiLahan arahanFungsiLahanBean) {
		this.arahanFungsiLahanBean = arahanFungsiLahanBean;
	}

	public TbFungsiLahanSekitar getFungsiLahanSekitarBean() {
		return fungsiLahanSekitarBean;
	}

	public void setFungsiLahanSekitarBean(TbFungsiLahanSekitar fungsiLahanSekitarBean) {
		this.fungsiLahanSekitarBean = fungsiLahanSekitarBean;
	}

	public TbFungsiJalan getFungsiJalanBean() {
		return fungsiJalanBean;
	}

	public void setFungsiJalanBean(TbFungsiJalan fungsiJalanBean) {
		this.fungsiJalanBean = fungsiJalanBean;
	}

	public TbKlasifikasiKegiatan getKlasifikasiKegiatanBean() {
		return klasifikasiKegiatanBean;
	}

	public void setKlasifikasiKegiatanBean(TbKlasifikasiKegiatan klasifikasiKegiatanBean) {
		this.klasifikasiKegiatanBean = klasifikasiKegiatanBean;
	}

	public TbJenisBuktiKepemilikan getJenisBuktiKepemilikanBean() {
		return jenisBuktiKepemilikanBean;
	}

	public void setJenisBuktiKepemilikanBean(TbJenisBuktiKepemilikan jenisBuktiKepemilikanBean) {
		this.jenisBuktiKepemilikanBean = jenisBuktiKepemilikanBean;
	}

	public TbPekerjaan getPekerjaanBean() {
		return pekerjaanBean;
	}

	public void setPekerjaanBean(TbPekerjaan pekerjaanBean) {
		this.pekerjaanBean = pekerjaanBean;
	}

	public TbPelakuUsaha getPelakuUsahaBean() {
		return pelakuUsahaBean;
	}

	public void setPelakuUsahaBean(TbPelakuUsaha pelakuUsahaBean) {
		this.pelakuUsahaBean = pelakuUsahaBean;
	}

	public String getAlamatDimohon() {
		return alamatDimohon;
	}

	public void setAlamatDimohon(String alamatDimohon) {
		this.alamatDimohon = alamatDimohon;
	}

	public TbDesa getDesaDimohonBean() {
		return desaDimohonBean;
	}

	public void setDesaDimohonBean(TbDesa desaDimohonBean) {
		this.desaDimohonBean = desaDimohonBean;
	}

	public TbDesa getDesaBean() {
		return desaBean;
	}

	public void setDesaBean(TbDesa desaBean) {
		this.desaBean = desaBean;
	}

	public FUser getFuserBean() {
		return fuserBean;
	}

	public void setFuserBean(FUser fuserBean) {
		this.fuserBean = fuserBean;
	}

	public String getNoKtpPemohon() {
		return noKtpPemohon;
	}

	public void setNoKtpPemohon(String noKtpPemohon) {
		this.noKtpPemohon = noKtpPemohon;
	}

	public String getAlamatPemohon() {
		return alamatPemohon;
	}

	public void setAlamatPemohon(String alamatPemohon) {
		this.alamatPemohon = alamatPemohon;
	}

	public String getTelpPemohon() {
		return telpPemohon;
	}

	public void setTelpPemohon(String telpPemohon) {
		this.telpPemohon = telpPemohon;
	}

	public String getNomorKepemilikan() {
		return nomorKepemilikan;
	}

	public void setNomorKepemilikan(String nomorKepemilikan) {
		this.nomorKepemilikan = nomorKepemilikan;
	}

	public LocalDate getTanggalPermohonan() {
		return tanggalPermohonan;
	}

	public void setTanggalPermohonan(LocalDate tanggalPermohonan) {
		this.tanggalPermohonan = tanggalPermohonan;
	}

	public double getLuasTanahBuktiKepemilikan() {
		return luasTanahBuktiKepemilikan;
	}

	public void setLuasTanahBuktiKepemilikan(double luasTanahBuktiKepemilikan) {
		this.luasTanahBuktiKepemilikan = luasTanahBuktiKepemilikan;
	}

	public double getLuasTanahDimohon() {
		return luasTanahDimohon;
	}

	public void setLuasTanahDimohon(double luasTanahDimohon) {
		this.luasTanahDimohon = luasTanahDimohon;
	}

	public double getLatDimohon() {
		return latDimohon;
	}

	public void setLatDimohon(double latDimohon) {
		this.latDimohon = latDimohon;
	}

	public double getLonDimohon() {
		return lonDimohon;
	}

	public void setLonDimohon(double lonDimohon) {
		this.lonDimohon = lonDimohon;
	}

	public String getImageSuratPermohonan() {
		return imageSuratPermohonan;
	}

	public void setImageSuratPermohonan(String imageSuratPermohonan) {
		this.imageSuratPermohonan = imageSuratPermohonan;
	}

	public String getImageKtp() {
		return imageKtp;
	}

	public void setImageKtp(String imageKtp) {
		this.imageKtp = imageKtp;
	}

	public String getImageKk() {
		return imageKk;
	}

	public void setImageKk(String imageKk) {
		this.imageKk = imageKk;
	}

	public String getImageBuktiKepemilikan() {
		return imageBuktiKepemilikan;
	}

	public void setImageBuktiKepemilikan(String imageBuktiKepemilikan) {
		this.imageBuktiKepemilikan = imageBuktiKepemilikan;
	}

	public String getImageSuratKeterangan() {
		return imageSuratKeterangan;
	}

	public void setImageSuratKeterangan(String imageSuratKeterangan) {
		this.imageSuratKeterangan = imageSuratKeterangan;
	}

	public String getImageAktePendirianPerusahaan() {
		return imageAktePendirianPerusahaan;
	}

	public void setImageAktePendirianPerusahaan(String imageAktePendirianPerusahaan) {
		this.imageAktePendirianPerusahaan = imageAktePendirianPerusahaan;
	}

	public String getImageBuktiPelunasanPbb() {
		return imageBuktiPelunasanPbb;
	}

	public void setImageBuktiPelunasanPbb(String imageBuktiPelunasanPbb) {
		this.imageBuktiPelunasanPbb = imageBuktiPelunasanPbb;
	}

	public String getImageHandDrawLokasi() {
		return imageHandDrawLokasi;
	}

	public void setImageHandDrawLokasi(String imageHandDrawLokasi) {
		this.imageHandDrawLokasi = imageHandDrawLokasi;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public String getBatasTimur() {
		return batasTimur;
	}

	public void setBatasTimur(String batasTimur) {
		this.batasTimur = batasTimur;
	}

	public String getBatasSelatan() {
		return batasSelatan;
	}

	public void setBatasSelatan(String batasSelatan) {
		this.batasSelatan = batasSelatan;
	}

	public String getBatasBarat() {
		return batasBarat;
	}

	public void setBatasBarat(String batasBarat) {
		this.batasBarat = batasBarat;
	}

	public String getBatasUtara() {
		return batasUtara;
	}

	public void setBatasUtara(String batasUtara) {
		this.batasUtara = batasUtara;
	}

	public Integer getJarakFasumPendidikan() {
		return jarakFasumPendidikan;
	}

	public void setJarakFasumPendidikan(Integer jarakFasumPendidikan) {
		this.jarakFasumPendidikan = jarakFasumPendidikan;
	}

	public Integer getJarakFasumPerdagangan() {
		return jarakFasumPerdagangan;
	}

	public void setJarakFasumPerdagangan(Integer jarakFasumPerdagangan) {
		this.jarakFasumPerdagangan = jarakFasumPerdagangan;
	}

	public Integer getJarakFasumPerkantoran() {
		return jarakFasumPerkantoran;
	}

	public void setJarakFasumPerkantoran(Integer jarakFasumPerkantoran) {
		this.jarakFasumPerkantoran = jarakFasumPerkantoran;
	}

	public Integer getJarakFasumLain1() {
		return jarakFasumLain1;
	}

	public void setJarakFasumLain1(Integer jarakFasumLain1) {
		this.jarakFasumLain1 = jarakFasumLain1;
	}

	public Integer getJarakFasumLain2() {
		return jarakFasumLain2;
	}

	public void setJarakFasumLain2(Integer jarakFasumLain2) {
		this.jarakFasumLain2 = jarakFasumLain2;
	}

	public boolean isKonflikSosial() {
		return konflikSosial;
	}

	public void setKonflikSosial(boolean konflikSosial) {
		this.konflikSosial = konflikSosial;
	}

	public StatusPersetujuan getStatusVerifikasi1() {
		return statusVerifikasi1;
	}

	public void setStatusVerifikasi1(StatusPersetujuan statusVerifikasi1) {
		this.statusVerifikasi1 = statusVerifikasi1;
	}

	public String getStatusVerifikasi1Notes() {
		return statusVerifikasi1Notes;
	}

	public void setStatusVerifikasi1Notes(String statusVerifikasi1Notes) {
		this.statusVerifikasi1Notes = statusVerifikasi1Notes;
	}

	public StatusPersetujuan getStatusVerifikasi2() {
		return statusVerifikasi2;
	}

	public void setStatusVerifikasi2(StatusPersetujuan statusVerifikasi2) {
		this.statusVerifikasi2 = statusVerifikasi2;
	}

	public String getStatusVerifikasi2Notes() {
		return statusVerifikasi2Notes;
	}

	public void setStatusVerifikasi2Notes(String statusVerifikasi2Notes) {
		this.statusVerifikasi2Notes = statusVerifikasi2Notes;
	}

	

	public StatusPersetujuan getStatusPersetujuan1() {
		return statusPersetujuan1;
	}

	public void setStatusPersetujuan1(StatusPersetujuan statusPersetujuan1) {
		this.statusPersetujuan1 = statusPersetujuan1;
	}

	public String getStatusPersetujuan1Notes() {
		return statusPersetujuan1Notes;
	}

	public void setStatusPersetujuan1Notes(String statusPersetujuan1Notes) {
		this.statusPersetujuan1Notes = statusPersetujuan1Notes;
	}

	public StatusPersetujuan getStatusPersetujuan2() {
		return statusPersetujuan2;
	}

	public void setStatusPersetujuan2(StatusPersetujuan statusPersetujuan2) {
		this.statusPersetujuan2 = statusPersetujuan2;
	}

	public String getStatusPersetujuan2Notes() {
		return statusPersetujuan2Notes;
	}

	public void setStatusPersetujuan2Notes(String statusPersetujuan2Notes) {
		this.statusPersetujuan2Notes = statusPersetujuan2Notes;
	}

	public StatusPersetujuan getStatusPersetujuan3() {
		return statusPersetujuan3;
	}

	public void setStatusPersetujuan3(StatusPersetujuan statusPersetujuan3) {
		this.statusPersetujuan3 = statusPersetujuan3;
	}

	public String getStatusPersetujuan3Notes() {
		return statusPersetujuan3Notes;
	}

	public void setStatusPersetujuan3Notes(String statusPersetujuan3Notes) {
		this.statusPersetujuan3Notes = statusPersetujuan3Notes;
	}

	public StatusPersetujuan getStatusPersetujuan4() {
		return statusPersetujuan4;
	}

	public void setStatusPersetujuan4(StatusPersetujuan statusPersetujuan4) {
		this.statusPersetujuan4 = statusPersetujuan4;
	}

	public String getStatusPersetujuan4Notes() {
		return statusPersetujuan4Notes;
	}

	public void setStatusPersetujuan4Notes(String statusPersetujuan4Notes) {
		this.statusPersetujuan4Notes = statusPersetujuan4Notes;
	}

	public List<TbRapat> getListRapat() {
		return listRapat;
	}

	public void setListRapat(List<TbRapat> listRapat) {
		this.listRapat = listRapat;
	}

	public List<Chats> getListChats() {
		return listChats;
	}

	public void setListChats(List<Chats> listChats) {
		this.listChats = listChats;
	}

	public List<TbRekomendasi> getListRekomendasi() {
		return listRekomendasi;
	}

	public void setListRekomendasi(List<TbRekomendasi> listRekomendasi) {
		this.listRekomendasi = listRekomendasi;
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

	public StatusPersetujuan getStatusPersetujuanAll() {
		return statusPersetujuanAll;
	}

	public void setStatusPersetujuanAll(StatusPersetujuan statusPersetujuanAll) {
		this.statusPersetujuanAll = statusPersetujuanAll;
	}

	public boolean isStatusHarusRapat() {
		return statusHarusRapat;
	}

	public void setStatusHarusRapat(boolean statusHarusRapat) {
		this.statusHarusRapat = statusHarusRapat;
	}

	public String getEmailPemohon() {
		return emailPemohon;
	}

	public void setEmailPemohon(String emailPemohon) {
		this.emailPemohon = emailPemohon;
	}

	public String getNamaPemohon() {
		return namaPemohon;
	}

	public void setNamaPemohon(String namaPemohon) {
		this.namaPemohon = namaPemohon;
	}

	


	



}