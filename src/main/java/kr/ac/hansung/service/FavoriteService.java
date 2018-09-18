package kr.ac.hansung.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.dao.FavoriteDAO;
import kr.ac.hansung.model.FavoriteInfo;


@Service
public class FavoriteService {

	@Autowired
	private FavoriteDAO favoriateDao;

	public boolean insertFavoriate(Map<String, Object> param) {
		return favoriateDao.insertFavoriate(param);
	}

	public List<FavoriteInfo> getFavoriateStoreByUserId(Map<String, Object> param) {
		return favoriateDao.getFavoriateStoreByUserId(param);
	}

	public FavoriteInfo getFavoriateStoreByUserIdAndStoreName(Map<String, Object> param) {
		return favoriateDao.getFavoriateStoreByUserIdAndStoreName(param);
	}

	public boolean deleteFavorite(Map<String, Object> param) {
		return favoriateDao.deleteFavorite(param);
	}
}
