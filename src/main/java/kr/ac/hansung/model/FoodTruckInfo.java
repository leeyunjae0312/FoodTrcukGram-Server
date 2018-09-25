package kr.ac.hansung.model;

import java.io.Serializable;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;

import java.util.ArrayList;


//@Getter
//@Setter
//@NoArgsConstructor
//@ToString
public class FoodTruckInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String storeName, ownerId, ownerName;
    private double longitude,latitude;
    private String isOpen;
    private ArrayList<MenuInfo> menuList;
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}
	public ArrayList<MenuInfo> getMenuList() {
		return menuList;
	}
	public void setMenuList(ArrayList<MenuInfo> menuList) {
		this.menuList = menuList;
	}
    
    
    
}
