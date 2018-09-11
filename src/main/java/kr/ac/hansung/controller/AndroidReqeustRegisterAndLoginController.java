package kr.ac.hansung.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.hansung.model.Users;
import kr.ac.hansung.service.UsersService;


@Controller
@RequestMapping("/foodtruckgram")
public class AndroidReqeustRegisterAndLoginController {
	
	@Autowired
	private UsersService usersService;

	@RequestMapping("/Android_register")
	@ResponseBody
	public Map<String, String> android_register(HttpServletRequest request) {
		System.out.println("Register");
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String userName = request.getParameter("userName");
		String tel = request.getParameter("tel");
		String role = request.getParameter("role");
		
		Users user = new Users();
		user.setUserId(userId);
		user.setPassword(password);
		user.setUserName(userName);
		user.setRole(role);
		user.setTel(tel);
		
		boolean check = usersService.insert(user);
		Map<String, String> result = new HashMap<String, String>();
		
		if (check == true) {
			result.put("OK", "OK");
		} else
			result.put("NOK", "NOK");

		return result;
	}
	
	@RequestMapping("/Android_login_duplicate_check")
	@ResponseBody
	public Map<String, String> Android_register_login_duplicate_check(HttpServletRequest request) {
		System.out.println("Android_register_login_duplicate_check");

		String id = request.getParameter("userId");

		List<Users> users = usersService.getIds();
		Map<String, String> result = new HashMap<String, String>();
		for (int i = 0; i < users.size(); i++) {
			if (id.equals(users.get(i).getUserId())) {
				result.put("NOK", "NOK");
			} else {
				result.put("OK", "OK");
			}
		}

		return result;
	}
	
	@RequestMapping("/Android_login") //post 함수 쓰기
	@ResponseBody
	public Map<String, Object> Android_login(HttpServletRequest request) {

		String userId = request.getParameter("userId");
		String password = request.getParameter("password");


		try {
			Users user = usersService.getUserInfo(userId, password);
			
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("useriI", userId);
			result.put("password", user.getPassword());
			result.put("username", user.getUserName());
			result.put("role", user.getRole());
			result.put("tel", user.getTel());
			
			return result;

		} catch (EmptyResultDataAccessException e) {
			Map<String, Object> failresult = new HashMap<String, Object>();
			failresult.put("fail", "fail");
			System.out.println("fail");
			return failresult;
		}
	}
}
