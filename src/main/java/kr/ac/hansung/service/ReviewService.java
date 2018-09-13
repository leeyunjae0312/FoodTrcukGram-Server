package kr.ac.hansung.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.dao.ReviewDAO;
import kr.ac.hansung.model.ReviewInfo;

@Service
public class ReviewService {

	@Autowired
	private ReviewDAO reviewDao;

	public List<ReviewInfo> getReview(Map<String, Object> param) {
		return reviewDao.getReview(param);
	}
}
