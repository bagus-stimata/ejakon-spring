package com.desgreen.gov.database.data.source.entity;

import com.desgreen.gov.database.domain.model_enum.Role;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "fuser_roles")
public class FUserRoles {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="fuser_roles_id_seq")
    // @SequenceGenerator(name="fuser_roles_id_seq", sequenceName="fuser_roles_id_seq", allocationSize=1)
    @Column(name = "id")
    private int id=0;

    
    // @Column(name = "role_id")
    private String roleID = Role.GUEST; //as default

    @ManyToOne
    @JoinColumn(name = "fuserBean", referencedColumnName = "ID")
    private FUser fuserBean;

   
    @Column(name = "created")
	private LocalDateTime created = LocalDateTime.now();
	@Column(name = "lastmodified")
	private LocalDateTime lastModified = LocalDateTime.now();
	@Column(name = "modified_by")
	private String modifiedBy = "";


    @Override
    public String toString() {
        return "Todo [description=" + roleID + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((fuserBean == null) ? 0 : fuserBean.hashCode());
        result = prime * result + ((roleID == null) ? 0 : roleID.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        FUserRoles other = (FUserRoles) obj;
        if (fuserBean == null) {
            if (other.fuserBean != null)
                return false;
        } else if (!fuserBean.equals(other.fuserBean))
            return false;
        if (roleID == null) {
            if (other.roleID != null)
                return false;
        } else if (!roleID.equals(other.roleID))
            return false;
        return true;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public FUser getFuserBean() {
        return fuserBean;
    }

    public void setFuserBean(FUser fuserBean) {
        this.fuserBean = fuserBean;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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