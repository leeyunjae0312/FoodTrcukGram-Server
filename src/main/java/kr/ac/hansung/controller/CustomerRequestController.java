package kr.ac.hansung.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.hansung.model.FoodTruckInfo;
import kr.ac.hansung.model.MenuInfo;
import kr.ac.hansung.service.FoodTruckService;
import kr.ac.hansung.service.MenuService;

@RestController
@RequestMapping("/foodtruckgram/c")
public class CustomerRequestController {

	@Autowired
	private FoodTruckService foodTruckService;
	
	@Autowired
	private MenuService menuService;
	
	@RequestMapping("/getFoodTruckInfoList")
	public ResponseEntity<List<FoodTruckInfo>> getFoodTruckInfoByStoreName(HttpServletRequest request) {
		
		List<FoodTruckInfo> foodTruckInfos;
		
		foodTruckInfos = foodTruckService.getFoodTruckInfoList();
		
		for(FoodTruckInfo info : foodTruckInfos) {
			ArrayList<MenuInfo> menuList;
			menuList = (ArrayList<MenuInfo>) menuService.getMenuList(info.getStoreName());
			info.setMenuList(menuList);
		}
		
		return new ResponseEntity<List<FoodTruckInfo>>(foodTruckInfos, HttpStatus.OK);

		
	}
}
