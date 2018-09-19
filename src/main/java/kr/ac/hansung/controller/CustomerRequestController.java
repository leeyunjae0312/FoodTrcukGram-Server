package kr.ac.hansung.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.hansung.model.FavoriteInfo;
import kr.ac.hansung.model.FoodTruckInfo;
import kr.ac.hansung.model.MenuInfo;
import kr.ac.hansung.model.OrderInfo;
import kr.ac.hansung.model.ReviewInfo;
import kr.ac.hansung.service.FavoriteService;
import kr.ac.hansung.service.FoodTruckService;
import kr.ac.hansung.service.MenuService;
import kr.ac.hansung.service.OrderService;
import kr.ac.hansung.service.ReviewService;

@RestController
@RequestMapping("/foodtruckgram/c")
public class CustomerRequestController {

	@Autowired
	private FoodTruckService foodTruckService;

	@Autowired
	private MenuService menuService;
	
	@Autowired
	private ReviewService reviewService;

	@Autowired
	private FavoriteService favoriteService;
	
	@Autowired
	private OrderService orderService;

	@RequestMapping("/getFoodTruckInfoList")
	public ResponseEntity<List<FoodTruckInfo>> getFoodTruckInfoByStoreName(HttpServletRequest request) {

		List<FoodTruckInfo> foodTruckInfos;

		foodTruckInfos = foodTruckService.getFoodTruckInfoList();

		for (FoodTruckInfo info : foodTruckInfos) {
			ArrayList<MenuInfo> menuList;
			menuList = (ArrayList<MenuInfo>) menuService.getMenuList(info.getStoreName());
			info.setMenuList(menuList);
		}

		return new ResponseEntity<List<FoodTruckInfo>>(foodTruckInfos, HttpStatus.OK);

	}

	@RequestMapping("/insertFavoriteStore")
	public Map<String, String> insertFavoriateStore(HttpServletRequest request) {

		String userId = request.getParameter("userId");
		String storeName = request.getParameter("storeName");
		boolean check = false;
		boolean chick = false;
		Map<String, String> result = new HashMap<String, String>();
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", userId);
		param.put("storeName", storeName);
		
		System.out.println("userId = " + userId + ", storeName = " + storeName);
		
		try {
			FavoriteInfo favoriteInfo = favoriteService.getFavoriateStoreByUserIdAndStoreName(param);
			
			if(favoriteInfo != null) {
				if(favoriteService.deleteFavorite(param)) {
					chick = true;
					result.put("favorite", "NO");
					System.out.println("delete favorite");
				}
				else {
					System.out.println("valid delete favorite");
				}
			}
			
		}catch(EmptyResultDataAccessException e) {
			
		}
		if(!chick) {
			System.out.println("insert favorite");
			check = favoriteService.insertFavoriate(param);
			result.put("favorite", "YES");
		}
		

		return result;

	}
	
	@RequestMapping("/insertReview")
	public Object insertReview(HttpServletRequest request) {

		String storeName = request.getParameter("storeName");
		String userName = request.getParameter("userName");
		String date = request.getParameter("date");
		String review = request.getParameter("review");

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("storeName", storeName);
		param.put("userName", userName);
		param.put("date", date);
		param.put("review", review);

		boolean check = reviewService.insertReview(param);

		if (check) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}

	}

	@RequestMapping("/getFavoriteStoreByUserId")
	public ResponseEntity<List<FoodTruckInfo>> getFavoriateStoreByUserId(HttpServletRequest request) {
		
		String userId = request.getParameter("userId");

		List<FavoriteInfo> favoriateInfos;
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", userId);

		favoriateInfos = favoriteService.getFavoriateStoreByUserId(param); //storeName list로 가져오기
		
		List<FoodTruckInfo> foodTruckInfos = new ArrayList<>();
		for(FavoriteInfo info : favoriateInfos) {
			FoodTruckInfo foodTruckInfo = foodTruckService.getFoodTruckInfoByStoreName(info.getStoreName());
			ArrayList<MenuInfo> menuList;
			menuList = (ArrayList<MenuInfo>) menuService.getMenuList(info.getStoreName());
			foodTruckInfo.setMenuList(menuList);
			foodTruckInfos.add(foodTruckInfo);
		}

		return new ResponseEntity<List<FoodTruckInfo>>(foodTruckInfos, HttpStatus.OK);

	}
	
	@RequestMapping("/insertOrder")
	public Object insertOrder(HttpServletRequest request) {

		String storeName = request.getParameter("storeName");
		String menuName = request.getParameter("menuName");
		String tel = request.getParameter("tel");
		String date = request.getParameter("date");
		String price = request.getParameter("price");
		String userId = request.getParameter("userId");

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("storeName", storeName);
		param.put("menuName", menuName);
		param.put("date", date);
		param.put("tel", tel);
		param.put("price", price);
		param.put("userId", userId);

		boolean check = orderService.insertOrder(param);

		if (check) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}

	}
	
	@RequestMapping("/getOrderListByUserId") //내 주문내역 가져오기
	public ResponseEntity<List<OrderInfo>> getOrderListByUserId(HttpServletRequest request) {
		
		String userId = request.getParameter("userId");
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", userId);

		List<OrderInfo> orderInfos = orderService.getOrderListByUserId(param);
		

		return new ResponseEntity<List<OrderInfo>>(orderInfos, HttpStatus.OK);

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
}
