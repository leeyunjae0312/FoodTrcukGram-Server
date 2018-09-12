package kr.ac.hansung.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.ac.hansung.model.FoodTruckInfo;
import kr.ac.hansung.model.Users;

@Repository
public class FoodTruckDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public FoodTruckInfo getFoodTruckInfoByStoreName(String ownerId) {
		String sqlStatement = "select * from foodtruckinfo_tbl where ownerId=?";
		return jdbcTemplate.queryForObject(sqlStatement, new Object[] { ownerId }, new RowMapper<FoodTruckInfo>() {

			@Override
			public FoodTruckInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

				FoodTruckInfo foodtruck = new FoodTruckInfo();

				foodtruck.setStoreName(rs.getString("storeName"));
				foodtruck.setOwnerName(rs.getString("ownerName"));
				foodtruck.setOwnerId(rs.getString("ownerId"));
				foodtruck.setOpen(rs.getBoolean("isOpen"));
				foodtruck.setLongitude(rs.getDouble("longitude"));
				foodtruck.setLatitude(rs.getDouble("latitude"));
				
				return foodtruck;

			}

		});
	}

	public List<FoodTruckInfo> getFoodTruckInfoList() {
		String sqlStatement = "select * from foodtruckinfo_tbl";

		return jdbcTemplate.query(sqlStatement, new RowMapper<FoodTruckInfo>() {

			@Override
			public FoodTruckInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

				FoodTruckInfo foodtruck = new FoodTruckInfo();

				foodtruck.setStoreName(rs.getString("storeName"));
				foodtruck.setOwnerName(rs.getString("ownerName"));
				foodtruck.setOwnerId(rs.getString("ownerId"));
				foodtruck.setOpen(rs.getBoolean("isOpen"));
				foodtruck.setLongitude(rs.getDouble("longitude"));
				foodtruck.setLatitude(rs.getDouble("latitude"));
				
				return foodtruck;
			}

		});
	}
	
}
