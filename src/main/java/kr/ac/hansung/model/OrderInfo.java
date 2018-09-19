package kr.ac.hansung.model;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrderInfo {

	String storeName;
	String menuName;
	String tel;
	Date date;
	String price;
	String userId;
	List<MenuInfo> menuInfos;
}
