package kr.ac.hansung.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.hansung.model.Users;
import kr.ac.hansung.service.UsersService;


@Controller
public class AndroidReqeustRegisterAndLoginController {
	
	@Autowired
	private UsersService usersService;

	@RequestMapping("/Android_register")
	@ResponseBody
	public Map<String, String> android_register(HttpServletRequest request) {
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
}
