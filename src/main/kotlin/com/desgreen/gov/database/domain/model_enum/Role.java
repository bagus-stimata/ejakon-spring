package com.desgreen.gov.database.domain.model_enum;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class Role {
	/**
	 * boleh tidak ditambahkan ROLE_ 
	 * misal ADMIN menjadi ROLE_ADMIN
	 * penambahan pada:
	 *  	authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
	 * 	oleh karena untuk menyamakan dengan konsep @Secure("ROLE_ADMIN") 
	 *  vaadin maka sebaiknya ditambahkan
	 */
	public static final String USER 	= "ROLE_USER"; //semuanya harus user

	public static final String ADMIN 	= "ROLE_ADMIN";
	public static final String ADMIN_1 	= "ROLE_ADMIN_1";
	public static final String ADMIN_2 	= "ROLE_ADMIN_2";

	public static final String GUEST 	= "ROLE_GUEST"; 
	public static final String USER1 	= "ROLE_USER_1"; //Masyarakat

	public static final String PEJABAT1 	= "ROLE_PEJABAT_1"; 
	public static final String PEJABAT2 	= "ROLE_PEJABAT_2"; 
	public static final String PEJABAT3 	= "ROLE_PEJABAT_3"; 
	public static final String PEJABAT4 	= "ROLE_PEJABAT_4"; 

	//Menu digunakan suatu penanda MNU
	// public static final String MNU_ADMIN_1 	= "ROLE_MNU_BAPPEDA";
	// public static final String MNU_PROFILE_1 = "ROLE_MNU_SOSIAL";
	public static final String MNU_PERSON_1 = "ROLE_MNU_PERSON_1";

	private Role() {
		// Static methods and fields only
	}

	public static String[] getAllRoles() {
		// return new String[] { USER, ADMIN, MNU_ADMIN_1 };
		String allRoles[] = ArrayUtils.addAll(getAuthRoles(), getMenuRoles());

		return allRoles;
	}

	public static List getAllRolesList() {
		List<String> list = new ArrayList<>(); 
  
        // Iterate through the array 
        for (String t : getAllRoles()) { 
            list.add(t); 
		} 
		return list;
	}
	public static String[] getAuthRoles() {
		return new String[] { USER, ADMIN, GUEST, ADMIN_1, ADMIN_2, USER1, PEJABAT1, PEJABAT2, PEJABAT3, PEJABAT4 };
	}

	public static String[] getMenuRoles() {
		return new String[] { MNU_PERSON_1};
	}

}
