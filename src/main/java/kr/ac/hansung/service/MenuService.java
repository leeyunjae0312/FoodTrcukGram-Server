package kr.ac.hansung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.dao.MenuDAO;
import kr.ac.hansung.model.MenuInfo;


@Service
public class MenuService {

	@Autowired
	private MenuDAO menuDao;

	public List<MenuInfo> getMenuList(String storeName) {
		return menuDao.getMenuList(storeName);
	}
}
