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

import kr.ac.hansung.model.FoodTruckInfo;
import kr.ac.hansung.model.MenuInfo;
import kr.ac.hansung.service.FoodTruckService;
import kr.ac.hansung.service.MenuService;

@Controller
@RequestMapping("/foodtruckgram/s")
public class SellerRequestController {

	@Autowired
	private FoodTruckService foodTruckService;
	
	@Autowired
	private MenuService menuService;

	@RequestMapping("/getFoodTruckInfoByStoreName")
	@ResponseBody
	public Object getFoodTruckInfoByStoreName(HttpServletRequest request) {
		
		String ownerId = request.getParameter("userId");
		
		FoodTruckInfo foodTruckInfo = new FoodTruckInfo();
		
		foodTruckInfo = foodTruckService.getFoodTruckInfoByStoreName(ownerId);
		
		ArrayList<MenuInfo> menuList;
		menuList = (ArrayList<MenuInfo>) menuService.getMenuList(foodTruckInfo.getStoreName());
		foodTruckInfo.setMenuList(menuList);
		
		//Map<String, String> result = new HashMap<String, String>();
		//return result;
		
		return new ResponseEntity<FoodTruckInfo>(foodTruckInfo, HttpStatus.OK);

		
	}
}
