package kr.ac.hansung.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.ac.hansung.model.MenuInfo;

@Repository
public class MenuDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<MenuInfo> getMenuList(String storeName) {
		String sqlStatement = "select * from menuinfo_tbl where storeName = ?";

		return jdbcTemplate.query(sqlStatement, new Object[] { storeName }, new RowMapper<MenuInfo>() {

			@Override
			public MenuInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

				MenuInfo menu = new MenuInfo();

				menu.setMenuImage(rs.getString("menuImage"));
				menu.setMenuIntroduce(rs.getString("menuIntroduce"));
				menu.setMenuName(rs.getString("menuName"));
				menu.setMenuPrice(rs.getString("menuPrice"));
				menu.setSoldOut(rs.getBoolean("soldOut"));

				return menu;
			}

		});
	}
	
	
}
