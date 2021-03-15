package com.desgreen.gov.database.domain.model_enum;


public enum StatusPersetujuan{
    HOLD(0, "HOLD", "BELUM", "Menunggu"),
    VALID(1, "VALID", "VALID", "SETUJU"),
    INVALID(2, "INVALID", "INVALID", "TIDAK SETUJU")
    ;
    
    private int intCode;
    private String strCode;
    private String description;
    private String descriptionOps;
    
    private StatusPersetujuan(int intCode, String strCode, String description, String descriptionOps){
        this.intCode = intCode;
        this.strCode = strCode;
        this.description = description;    
        this.descriptionOps = descriptionOps;    
    }
    

    

    /**
     * @return int return the intCode
     */
    public int getIntCode() {
        return intCode;
    }

    /**
     * @param intCode the intCode to set
     */
    public void setIntCode(int intCode) {
        this.intCode = intCode;
    }

    /**
     * @return String return the strCode
     */
    public String getStrCode() {
        return strCode;
    }

    /**
     * @param strCode the strCode to set
     */
    public void setStrCode(String strCode) {
        this.strCode = strCode;
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
     * @return String return the descriptionOps
     */
    public String getDescriptionOps() {
        return descriptionOps;
    }

    /**
     * @param descriptionOps the descriptionOps to set
     */
    public void setDescriptionOps(String descriptionOps) {
        this.descriptionOps = descriptionOps;
    }

}
