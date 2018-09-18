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
import org.springframework.transaction.annotation.Transactional;

import kr.ac.hansung.model.MenuInfo;
import kr.ac.hansung.model.ReviewInfo;

@Repository
@Transactional
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
				System.out.println("//////////////" + rs.getDate("date"));

				return review;
			}

		});
	}

	public boolean insertReview(Map<String, Object> param) {
		String sqlStatement = "insert into review_tbl (storeName, userName, date, review) values(?,?,?,?)";

		return (jdbcTemplate.update(sqlStatement, new Object[] { param.get("storeName"), param.get("userName"), param.get("date"),  param.get("review") }) == 1);
	}

}
