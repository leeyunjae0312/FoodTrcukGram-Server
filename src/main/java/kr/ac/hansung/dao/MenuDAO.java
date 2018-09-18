package kr.ac.hansung.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.hansung.model.MenuInfo;

@Repository
@Transactional
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

				return menu;
			}

		});
	}

	public boolean updateMenu(Map<String, Object> param) {
		String sqlStatement = "update menuinfo_tbl set menuImage = ?, menuPrice = ?, menuIntroduce = ? where storeName = ? and menuName = ?";

		return (jdbcTemplate.update(sqlStatement, new Object[] { param.get("menuImage"), param.get("menuPrice"),  param.get("menuIntroduce"), param.get("storeName"),  param.get("menuName") }) == 1);
	}

	public boolean insertMenu(Map<String, Object> param) {
		String sqlStatement = "insert into menuinfo_tbl (menuName, menuImage, menuPrice, menuIntroduce, storeName) values(?,?,?,?,?)";

		return (jdbcTemplate.update(sqlStatement, new Object[] { param.get("menuName"), param.get("menuImage"), param.get("menuPrice"),  param.get("menuIntroduce"), param.get("storeName") }) == 1);
	}

	public boolean deleteMenu(Map<String, Object> param) {
		String sqlStatement = "delete from menuinfo_tbl where menuName = ? and storeName = ?";

		return (jdbcTemplate.update(sqlStatement, new Object[] { param.get("menuName"), param.get("storeName") }) == 1);
	}
	
	
}
