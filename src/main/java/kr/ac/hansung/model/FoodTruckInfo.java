package kr.ac.hansung.model;

import java.io.Serializable;
import java.util.ArrayList;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class FoodTruckInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String storeName, ownerId, ownerName;
    private double longitude,latitude;
    private String isOpen;
    private ArrayList<MenuInfo> menuList;
}
