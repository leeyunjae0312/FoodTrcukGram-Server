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

import kr.ac.hansung.model.OrderInfo;

@Repository
@Transactional
public class OrderDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<OrderInfo> getOrderListByStoreName(Map<String, Object> param) {
		String sqlStatement = "select * from order_tbl where storeName = ? order by date asc";

		return jdbcTemplate.query(sqlStatement, new Object[] { param.get("storeName") }, new RowMapper<OrderInfo>() {

			@Override
			public OrderInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

				OrderInfo orderInfo = new OrderInfo();

				orderInfo.setStoreName(rs.getString("storeName"));
				orderInfo.setMenuName(rs.getString("menuName"));
				orderInfo.setTel(rs.getString("tel"));
				orderInfo.setPrice(rs.getInt("price"));
				orderInfo.setDate(rs.getDate("date"));
				orderInfo.setUserId(rs.getString("userId"));

				return orderInfo;
			}

		});
	}

	public boolean deleteOrder(Map<String, Object> param) {
		String sqlStatement = "delete from order_tbl where userId = ? and storeName = ? and menuName = ?";

		return (jdbcTemplate.update(sqlStatement, new Object[] { param.get("userId"), param.get("storeName"), param.get("menuName") }) == 1);
	}

	public boolean insertOrder(Map<String, Object> param) {
		String sqlStatement = "insert into order_tbl (storeName, menuName, tel, date, price, userId) values(?,?,?,?,?,?)";

		return (jdbcTemplate.update(sqlStatement, new Object[] { param.get("storeName"), param.get("menuName"), param.get("tel"),  param.get("date"), param.get("price"), param.get("userId") }) == 1);
	}

	public List<OrderInfo> getOrderListByUserId(Map<String, Object> param) {
		String sqlStatement = "select * from order_tbl where userId = ? order by date asc";

		return jdbcTemplate.query(sqlStatement, new Object[] { param.get("userId") }, new RowMapper<OrderInfo>() {

			@Override
			public OrderInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

				OrderInfo orderInfo = new OrderInfo();

				orderInfo.setStoreName(rs.getString("storeName"));
				orderInfo.setMenuName(rs.getString("menuName"));
				orderInfo.setTel(rs.getString("tel"));
				orderInfo.setPrice(rs.getInt("price"));
				orderInfo.setDate(rs.getDate("date"));
				orderInfo.setUserId(rs.getString("userId"));

				return orderInfo;
			}

		});
	}
}
