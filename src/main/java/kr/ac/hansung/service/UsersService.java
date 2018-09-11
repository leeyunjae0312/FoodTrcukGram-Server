package kr.ac.hansung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.dao.UsersDAO;
import kr.ac.hansung.model.Users;

@Service
public class UsersService {

	@Autowired
	private UsersDAO usersDao;
	
	public List<Users> getCurrent() {
		return usersDao.getUsers();
	}
	
	public boolean insert(Users user) {
		return usersDao.insert(user);
	}
	
	/* id로 password, name, role, tel 찾기 */
	public Users getUserInfo(String id, String password) {
		return usersDao.getUserInfo(id, password);
	}
	
	/* 아이디 중복 확인 */
	public List<Users> getIds() {
		return usersDao.getIds();
	}

	/*public void saveStamp(String id, int stampCnt) {
		usersDao.saveStamp(id, stampCnt);
	}*/

	public Users getUserInfo(String id) {
		return usersDao.getUserInfo(id);
	}
	
}
