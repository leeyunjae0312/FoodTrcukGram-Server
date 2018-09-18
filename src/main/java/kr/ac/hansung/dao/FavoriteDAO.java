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

import kr.ac.hansung.model.FavoriteInfo;
import kr.ac.hansung.model.FoodTruckInfo;

@Repository
@Transactional
public class FavoriteDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public boolean insertFavoriate(Map<String, Object> param) {
		String sqlStatement = "insert into favoriate_tbl (userId, storeName) values(?,?)";

		return (jdbcTemplate.update(sqlStatement, new Object[] { param.get("userId"), param.get("storeName")}) == 1);
	}

	public List<FavoriteInfo> getFavoriateStoreByUserId(Map<String, Object> param) {
		String sqlStatement = "select * from favoriate_tbl where userId = ?";

		return jdbcTemplate.query(sqlStatement, new Object[] { param.get("userId")}, new RowMapper<FavoriteInfo>() {

			@Override
			public FavoriteInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

				FavoriteInfo favoriateInfo = new FavoriteInfo();

				favoriateInfo.setUserId(rs.getString("userId"));
				favoriateInfo.setStoreName(rs.getString("storeName"));
				
				return favoriateInfo;
			}

		});
	}

	public FavoriteInfo getFavoriateStoreByUserIdAndStoreName(Map<String, Object> param) {
		String sqlStatement = "select * from favoriate_tbl where storeName=? and userId = ?";
		return jdbcTemplate.queryForObject(sqlStatement, new Object[] { param.get("storeName"), param.get("userId") }, new RowMapper<FavoriteInfo>() {

			@Override
			public FavoriteInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

				FavoriteInfo favoriteInfo = new FavoriteInfo();

				favoriteInfo.setUserId(rs.getString("userId"));
				favoriteInfo.setStoreName(rs.getString("storeName"));
				
				return favoriteInfo;

			}

		});
	}

	public boolean deleteFavorite(Map<String, Object> param) {
		String sqlStatement = "delete from favoriate_tbl where storeName=? and userId = ?";

		return (jdbcTemplate.update(sqlStatement, new Object[] { param.get("storeName"), param.get("userId") }) == 1);
	}
}
