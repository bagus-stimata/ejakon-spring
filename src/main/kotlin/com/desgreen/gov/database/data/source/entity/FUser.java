package com.desgreen.gov.database.data.source.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name="fuser")
public class FUser {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="fuser_id_seq")
    // @SequenceGenerator(name="fuser_id_seq", sequenceName="fuser_id_seq", allocationSize=1)
	@Column(name = "id")
	private int id=0;

	@Column(length = 100, name = "email")
	private String email="";

	@Column(name = "username", length = 100)
	private String username="";


	@Column(name = "password")
	private String password="";

	@Transient
	private String passwordConfirm="";


	@Column(name = "full_name", length = 100)
	private String fullName = "";

    
	@Column(name = "phone", length = 30)
	private String phone = "";

	@Column(name = "notes")
	private String notes = "";

	// @NotBlank
	// @Size(max = 255)
	// private String role;

	@Column(name = "locked")
	private boolean locked = true; //Untuk pertama kali buat/default harus lock biar aman

    /**
     * Sebetulnya bisa menggunakan FUserRoles
     */
    @Column(name="tipe_user")
    private Integer tipeUser = 0;


    @OneToMany(mappedBy = "fuserBean")
	private List<FUserRoles> fuserRoles;
    @Transient
    private List<String> tempRoles;


    

    @Transient
	private Integer tempInt1 = 0;
	@Transient
	private boolean tempBol1 = false;
	@Transient
	private boolean tempBol2 = false;

	
	@Column(name = "created")
	private LocalDateTime created = LocalDateTime.now();
	@Column(name = "lastmodified")
	private LocalDateTime lastModified = LocalDateTime.now();
	@Column(name = "modified_by")
	private String modifiedBy = "";

	@PrePersist
	@PreUpdate
	private void prepareData(){
		this.email = email == null ? null : email.toLowerCase();
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
		FUser other = (FUser) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FUser [username=" + username + "]";
	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public List<FUserRoles> getFuserRoles() {
        return fuserRoles;
    }

    public void setFuserRoles(List<FUserRoles> fuserRoles) {
        this.fuserRoles = fuserRoles;
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

    public Integer getTipeUser() {
        return tipeUser;
    }

    public void setTipeUser(Integer tipeUser) {
        this.tipeUser = tipeUser;
    }

   

    

    /**
     * @return List<String> return the tempRoles
     */
    public List<String> getTempRoles() {
        return tempRoles;
    }

    /**
     * @param tempRoles the tempRoles to set
     */
    public void setTempRoles(List<String> tempRoles) {
        this.tempRoles = tempRoles;
    }

    public Integer getTempInt1() {
        return tempInt1;
    }

    public void setTempInt1(Integer tempInt1) {
        this.tempInt1 = tempInt1;
    }

    public boolean isTempBol1() {
        return tempBol1;
    }

    public void setTempBol1(boolean tempBol1) {
        this.tempBol1 = tempBol1;
    }

    public boolean isTempBol2() {
        return tempBol2;
    }

    public void setTempBol2(boolean tempBol2) {
        this.tempBol2 = tempBol2;
    }

}
