package kr.ac.hansung.model;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ReviewInfo {

	 private String userName;
	 private Date date;
	 private String review;
}
