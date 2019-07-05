package com.revature.pojos;

public class Users 
{
	private String username;
	private String password;
	private String position;
	private int userID;
	private double availablebalance = 1000;
	private double pendingbalance =0;
	private double totalbalance=1000;
	private double awarded=0;
	
	public double getAwarded() {
		return awarded;
	}
	public void setAwarded(double d) {
		this.awarded = d;
	}
	public Users(String username, String password) {
		super();
		this.username = username;
		this.password = password;
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
	public void setuserID(int ID)
	{
		this.userID = ID;
	}
	public int getuserID()
	{
		return userID;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public double getAvailablebalance() {
		return availablebalance;
	}
	public void setAvailablebalance(double d) {
		this.availablebalance = d;
	}
	public double getPendingbalance() {
		return pendingbalance;
	}
	public void setPendingbalance(double d) {
		this.pendingbalance = d;
	}
	public double getTotalbalance() {
		return totalbalance;
	}
	public void setTotalbalance(double d) {
		this.totalbalance = d;
	}

}
