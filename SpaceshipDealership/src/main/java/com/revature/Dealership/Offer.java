package com.revature.Dealership;

public class Offer 
{
	private int carid;
	private int customerid;
	private String status;
	private int offer_id;
	private int offer;
	
	public Offer(int o_id, int c_id, int car_id, int offer, String status)
	{
		this.carid = car_id;
		this.customerid = c_id;
		this.status = status;
		this.offer_id = o_id;
		this.offer = offer;
	}
	
	public String getStatus() 
	{
		return status;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}
	public int getCustomerid() 
	{
		return customerid;
	}
	public void setCustomerid(int customerid) 
	{
		this.customerid = customerid;
	}
	public int getCarid() 
	{
		return carid;
	}
	public void setCarid(int carid) 
	{
		this.carid = carid;
	}

	public int getOffer_id() {
		return offer_id;
	}

	public void setOffer_id(int offer_id) {
		this.offer_id = offer_id;
	}

	public int getOffer() {
		return offer;
	}

	public void setOffer(int offer) {
		this.offer = offer;
	}
}
