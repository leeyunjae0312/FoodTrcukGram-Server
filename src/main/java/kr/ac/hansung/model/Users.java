package kr.ac.hansung.model;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
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

}
