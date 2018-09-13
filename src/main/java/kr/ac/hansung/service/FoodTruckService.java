package kr.ac.hansung.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.dao.FoodTruckDAO;
import kr.ac.hansung.model.FoodTruckInfo;

@Service
public class FoodTruckService{

	@Autowired
	private FoodTruckDAO foodTruckDao;

	public FoodTruckInfo getFoodTruckInfoByStoreName(String ownerId) {
		return foodTruckDao.getFoodTruckInfoByStoreName(ownerId);
	}

	public List<FoodTruckInfo> getFoodTruckInfoList() {
		return foodTruckDao.getFoodTruckInfoList();
	}

	public boolean updateFoodTruckLocationAndOpen(Map<String, Object> param) {
		return foodTruckDao.updateFoodTruckLocationAndOpen(param);
	}

	public boolean updateFoodTruckClose(Map<String, Object> param) {
		return foodTruckDao.updateFoodTruckClose(param);
	}

}
