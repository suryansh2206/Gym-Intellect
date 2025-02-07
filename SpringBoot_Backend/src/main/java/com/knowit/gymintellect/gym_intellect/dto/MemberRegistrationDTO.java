package com.knowit.gymintellect.gym_intellect.dto;

import java.util.Date;

public class MemberRegistrationDTO {
	 private String username;
	 private String email;
	 private String password;
	 private String contact;
	 private String aadhar;
	 private Date dob;
	 private String gender;
	 private String address;
	 private int height;
	 private Long gymProfileId;
	 private Long membershipPlanId;
	 private Long planId;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getAadhar() {
		return aadhar;
	}
	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public Long getGymProfileId() {
		return gymProfileId;
	}
	public void setGymProfileId(Long gymProfileId) {
		this.gymProfileId = gymProfileId;
	}
	public Long getMembershipPlanId() {
		return membershipPlanId;
	}
	public void setMembershipPlanId(Long membershipPlanId) {
		this.membershipPlanId = membershipPlanId;
	}
	public Long getPlanId() {
		return planId;
	}
	public void setPlanId(Long planId) {
		this.planId = planId;
	}
	 
	 
}
