package kr.ac.hansung.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.hansung.model.FoodTruckInfo;
import kr.ac.hansung.model.MenuInfo;
import kr.ac.hansung.service.FoodTruckService;
import kr.ac.hansung.service.MenuService;

@RestController
@RequestMapping("/foodtruckgram/s")
public class SellerRequestController {

	@Autowired
	private FoodTruckService foodTruckService;
	
	@Autowired
	private MenuService menuService;

	@RequestMapping("/getFoodTruckInfoByStoreName")
	public Object getFoodTruckInfoByStoreName(HttpServletRequest request) {
		
		String ownerId = request.getParameter("userId");
		
		FoodTruckInfo foodTruckInfo = new FoodTruckInfo();
		
		foodTruckInfo = foodTruckService.getFoodTruckInfoByStoreName(ownerId);
		
		ArrayList<MenuInfo> menuList;
		menuList = (ArrayList<MenuInfo>) menuService.getMenuList(foodTruckInfo.getStoreName());
		foodTruckInfo.setMenuList(menuList);
		
		return new ResponseEntity<FoodTruckInfo>(foodTruckInfo, HttpStatus.OK);

		
	}
	
	@RequestMapping("/updateFoodTruckLocationAndOpen")
	public Object updateFoodTruckLocationAndOpen(HttpServletRequest request) {
		
		String storeName = request.getParameter("storeName");
		String longitude_s = request.getParameter("longitude");
		double longitude = Double.parseDouble(longitude_s);
		String latitude_s = request.getParameter("latitude");
		double latitude = Double.parseDouble(latitude_s);
		
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("storeName", storeName);
		param.put("longitude", longitude);
		param.put("latitude", latitude);
		param.put("isOpen", 1);
		
		boolean check = foodTruckService.updateFoodTruckLocationAndOpen(param);

		if(check) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
		
	}
	
	@RequestMapping("/updateFoodTruckClose")
	public Object updateFoodTruckClose(HttpServletRequest request) {
		
		String storeName = request.getParameter("storeName");
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("storeName", storeName);
		param.put("isOpen", 0);
		
		boolean check = foodTruckService.updateFoodTruckClose(param);

		if(check) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
		
	}
	
	@RequestMapping("/updateMenu")
	public Object updateMenu(HttpServletRequest request) {
		
		String menuName = request.getParameter("menuName");
		String menuImage = request.getParameter("menuImage");
		String menuPrice = request.getParameter("menuPrice");
		String soldOut_s = request.getParameter("soldOut");
		String storeName = request.getParameter("storeName");
		boolean soldOut = false;
		if(soldOut_s.equals("1")) {
			soldOut = true;
		}
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("menuName", menuName);
		param.put("menuImage", menuImage);
		param.put("menuPrice", menuPrice);
		param.put("soldOut", soldOut);
		param.put("storeName", storeName);
		
		boolean check = menuService.updateMenu(param);

		if(check) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
		
	}
	
	@RequestMapping("/insertMenu")
	public Object insertMenu(HttpServletRequest request) {
		
		String menuName = request.getParameter("menuName");
		String menuImage = request.getParameter("menuImage");
		String menuPrice = request.getParameter("menuPrice");
		String soldOut_s = request.getParameter("soldOut");
		String storeName = request.getParameter("storeName");
		boolean soldOut = false;
		if(soldOut_s.equals("1")) {
			soldOut = true;
		}
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("menuName", menuName);
		param.put("menuImage", menuImage);
		param.put("menuPrice", menuPrice);
		param.put("soldOut", soldOut);
		param.put("storeName", storeName);
		
		boolean check = menuService.insertMenu(param);

		if(check) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
		
	}
	
	@RequestMapping("/deleteMenu")
	public Object deleteMenu(HttpServletRequest request) {
		
		String menuName = request.getParameter("menuName");
		String storeName = request.getParameter("storeName");
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("menuName", menuName);
		param.put("storeName", storeName);
		
		boolean check = menuService.deleteMenu(param);

		if(check) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
		
	}
}
