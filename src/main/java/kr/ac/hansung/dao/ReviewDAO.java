package kr.ac.hansung.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.ac.hansung.model.MenuInfo;
import kr.ac.hansung.model.ReviewInfo;

@Repository
public class ReviewDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<ReviewInfo> getReview(Map<String, Object> param) {
		String sqlStatement = "select * from review_tbl where storeName = ?";

		return jdbcTemplate.query(sqlStatement, new Object[] { param.get("storeName") }, new RowMapper<ReviewInfo>() {

			@Override
			public ReviewInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

				ReviewInfo review = new ReviewInfo();

				review.setUserName(rs.getString("userName"));
				review.setReview(rs.getString("review"));
				review.setDate(rs.getDate("date"));

				return review;
			}

		});
	}

}
