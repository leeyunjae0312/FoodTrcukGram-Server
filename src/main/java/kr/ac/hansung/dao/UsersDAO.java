package kr.ac.hansung.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.ac.hansung.model.Users;

@Repository
public class UsersDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public boolean insert(Users user) {

		String userId = user.getUserId();
		String password = user.getPassword();
		String userName = user.getUserName();
		String role = user.getRole();
		String tel = user.getTel();
		
		String sqlStatement = "insert into user_tbl (userId, password, userName, role, tel) values(?,?,?,?,?)";

		return (jdbcTemplate.update(sqlStatement, new Object[] { userId, password, userName, role, tel }) == 1);
	}

	public List<Users> getUsers() {
		String sqlStatement = "select * from user_tbl";

		return jdbcTemplate.query(sqlStatement, new RowMapper<Users>() {

			@Override
			public Users mapRow(ResultSet rs, int rowNum) throws SQLException {

				Users users = new Users();

				users.setUserId(rs.getString("userId"));
				users.setPassword(rs.getString("password"));
				users.setUserName(rs.getString("userName"));
				users.setRole(rs.getString("role"));
				users.setTel(rs.getString("tel"));

				return users;
			}

		});
	}

	// 로그인 중복확인
	public List<Users> getIds() {
		String sqlStatement = "select userId from user_tbl";

		return jdbcTemplate.query(sqlStatement, new RowMapper<Users>() {

			@Override
			public Users mapRow(ResultSet rs, int rowNum) throws SQLException {

				Users users = new Users();

				users.setUserId(rs.getString("userId"));

				return users;
			}

		});
	}

	// 로그인
	public Users getUserInfo(String id, String password) {
		String sqlStatement = "select * from user_tbl where userId=? and password=?";
		return jdbcTemplate.queryForObject(sqlStatement, new Object[] { id, password }, new RowMapper<Users>() {

			@Override
			public Users mapRow(ResultSet rs, int rowNum) throws SQLException {

				Users user = new Users();

				user.setUserId(rs.getString("userId"));
				user.setPassword(rs.getString("password"));
				user.setUserName(rs.getString("userName"));
				user.setRole(rs.getString("role"));
				user.setTel(rs.getString("tel"));

				return user;

			}

		});
	}

	public Users getUserInfo(String id) {

		String sqlStatement = "select * from users where userid=?";
		return jdbcTemplate.queryForObject(sqlStatement, new Object[] { id}, new RowMapper<Users>() {

			@Override
			public Users mapRow(ResultSet rs, int rowNum) throws SQLException {

				Users user = new Users();

				user.setUserId(rs.getString("userId"));
				user.setPassword(rs.getString("password"));
				user.setUserName(rs.getString("userName"));
				user.setRole(rs.getString("role"));
				user.setTel(rs.getString("tel"));

				return user;

			}

		});
	}

	/*public boolean saveStamp(String id, int stampCnt) {

		String sqlStatement = "update users set stamp = ? where userid=?";

		System.out.println("stampCnt = " + stampCnt);

		return (jdbcTemplate.update(sqlStatement, new Object[] { stampCnt, id }) == 1);

	}*/

}
