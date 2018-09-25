package kr.ac.hansung.model;

import java.util.Date;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;



//@Getter
//@Setter
//@NoArgsConstructor
//@ToString
public class ReviewInfo {

	 private String userName;
	 private Date date;
	 private String review;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}

	 
	 
}
