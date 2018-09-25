package kr.ac.hansung.model;

import javax.validation.constraints.NotEmpty;

//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;


//@Getter
//@Setter
//@NoArgsConstructor
//@ToString
public class Users {

	@NotEmpty
	private String userId;
	
	@NotEmpty
	private String password;
	
	@NotEmpty
	private String userName;
	
	@NotEmpty
	private String role;
	
	@NotEmpty
	private String tel;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	

}
