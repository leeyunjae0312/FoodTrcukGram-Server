package kr.ac.hansung.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.dao.OrderDAO;
import kr.ac.hansung.model.OrderInfo;

@Service
public class OrderService {

	@Autowired
	private OrderDAO orderDao;

	public List<OrderInfo> getOrderListByStoreName(Map<String, Object> param) {
		return orderDao.getOrderListByStoreName(param);
	}

	public boolean deleteOrder(Map<String, Object> param) {
		return orderDao.deleteOrder(param);
	}

	public boolean insertOrder(Map<String, Object> param) {
		return orderDao.insertOrder(param);
	}

	public List<OrderInfo> getOrderListByUserId(Map<String, Object> param) {
		return orderDao.getOrderListByUserId(param);
	}
}
