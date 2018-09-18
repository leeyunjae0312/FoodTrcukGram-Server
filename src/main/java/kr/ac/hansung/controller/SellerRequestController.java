package kr.ac.hansung.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.ac.hansung.model.FoodTruckInfo;
import kr.ac.hansung.model.MenuInfo;
import kr.ac.hansung.model.OrderInfo;
import kr.ac.hansung.model.ReviewInfo;
import kr.ac.hansung.service.FoodTruckService;
import kr.ac.hansung.service.MenuService;
import kr.ac.hansung.service.OrderService;
import kr.ac.hansung.service.ReviewService;

@RestController
@RequestMapping("/foodtruckgram/s")
public class SellerRequestController {

	@Autowired
	private FoodTruckService foodTruckService;
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private OrderService orderService;

	@RequestMapping("/getFoodTruckInfoByStoreName")
	public Object getFoodTruckInfoByStoreName(HttpServletRequest request) {
		
		String ownerId = request.getParameter("userId");
		
		FoodTruckInfo foodTruckInfo = new FoodTruckInfo();
		
		foodTruckInfo = foodTruckService.getFoodTruckInfoByOwnerId(ownerId);
		
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
		String menuIntroduce = request.getParameter("menuIntroduce");
		String menuPrice = request.getParameter("menuPrice");
		String storeName = request.getParameter("storeName");
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("menuName", menuName);
		param.put("menuImage", menuImage);
		param.put("menuIntroduce", menuIntroduce);
		param.put("menuPrice", menuPrice);
		param.put("storeName", storeName);
		
		boolean check = menuService.updateMenu(param);

		if(check) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
		
	}
	
	@RequestMapping(value="/insertMenu", method = RequestMethod.POST)
	public Object insertMenu(HttpServletRequest request, @RequestParam("menuImage") String menuImage) {
		
		String menuName = request.getParameter("menuName");
		//String menuImage = request.getParameter("menuImage");
		String menuIntroduce = request.getParameter("menuIntroduce");
		String menuPrice = request.getParameter("menuPrice");
		String storeName = request.getParameter("storeName");
		
		menuImage = menuImage.replaceAll(" ", "+");
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("menuName", menuName); 
		param.put("menuImage", menuImage);
		param.put("menuIntroduce", menuIntroduce);
		param.put("menuPrice", menuPrice);
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
	
	@RequestMapping("/getReview")
	public ResponseEntity<List<ReviewInfo>> getReview(HttpServletRequest request) {
		
		String storeName = request.getParameter("storeName");
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("storeName", storeName);
		
		List<ReviewInfo> infos = reviewService.getReview(param);

		if(infos != null) {
			return new ResponseEntity<List<ReviewInfo>>(infos, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<ReviewInfo>>(infos, HttpStatus.FORBIDDEN);
		}
		
	}
	
	@RequestMapping("/getOrderListByStoreName")
	public ResponseEntity<List<OrderInfo>> getOrderListByStoreName(HttpServletRequest request) {
		
		String storeName = request.getParameter("storeName");
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("storeName", storeName);
		
		List<OrderInfo> infos = orderService.getOrderListByStoreName(param);
		
		for(OrderInfo info : infos) {
			List<MenuInfo> menuInfos = menuService.getMenuList(storeName);
			info.setMenuInfos(menuInfos);
		}

		if(infos != null) {
			return new ResponseEntity<List<OrderInfo>>(infos, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<OrderInfo>>(infos, HttpStatus.FORBIDDEN);
		}
		
	}
	
	@RequestMapping("/deleteOrder")
	public Object deleteOrder(HttpServletRequest request) {
		
		String userId = request.getParameter("userId");
		String storeName = request.getParameter("storeName");
		String menuName = request.getParameter("menuName");
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", userId);
		param.put("storeName", storeName);
		param.put("menuName", menuName);
		
		boolean check = orderService.deleteOrder(param);

		if(check) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
		
	}
}
