/*
 * Copyright (c) 2014 아시아나IDT
 * 
 * 이 프로그램은 저작권 보호법에 의해 보호됩니다.
 * 이 프로그램의 일부나 전부를 무단으로 복제하거나 배포하는 경우에는
 * 저작권의 침해가 되므로 주의하시기 바랍니다.
 */
package com.spring.demo.model;

/**
 * 이 클래스는...
 *
 * @author user
 * Created on 2014. 8. 26.
 */
import java.io.Serializable;

/**
 * 이 클래스는... 
 * @author user
 *
 * Created on 2013. 7. 19.
 */
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7065340927220790631L;
	
	private String userAccount;
	private String userName;
	private String deptName;
	private String mailAddress;
	private String companyCode;
	private String rankName;
	private String companyName;
	private String dutyName;
	private String tdPassword;
	private int grade;
	/**
	 *
	 * @return 
	 */
	public String getUserAccount() {
		return userAccount;
	}
	/**
	 *
	 * @param userAccount
	 */
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	/**
	 *
	 * @return 
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 *
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 *
	 * @return 
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 *
	 * @param deptName
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 *
	 * @return 
	 */
	public String getMailAddress() {
		return mailAddress;
	}
	/**
	 *
	 * @param mailAddress
	 */
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	/**
	 *
	 * @return 
	 */
	public String getCompanyCode() {
		return companyCode;
	}
	/**
	 *
	 * @param companyCode
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	/**
	 *
	 * @return 
	 */
	public String getRankName() {
		return rankName;
	}
	/**
	 *
	 * @param rankName
	 */
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}
	/**
	 *
	 * @return 
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 *
	 * @param companyName
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 *
	 * @return 
	 */
	public String getDutyName() {
		return dutyName;
	}
	/**
	 *
	 * @param dutyName
	 */
	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}
	/**
	 *
	 * @return 
	 */
	public String getTdPassword() {
		return tdPassword;
	}
	/**
	 *
	 * @param tdPassword
	 */
	public void setTdPassword(String tdPassword) {
		this.tdPassword = tdPassword;
	}
	/**
	 *
	 * @return 
	 */
	public int getGrade() {
		return grade;
	}
	/**
	 *
	 * @param grade
	 */
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
}
