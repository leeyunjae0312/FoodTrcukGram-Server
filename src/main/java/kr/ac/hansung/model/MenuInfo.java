package kr.ac.hansung.model;

//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;


//@Getter
//@Setter
//@NoArgsConstructor
//@ToString
public class MenuInfo {

	private String menuImage;
    private String menuName;
    private String menuPrice;
    private String menuIntroduce;
	public String getMenuImage() {
		return menuImage;
	}
	public void setMenuImage(String menuImage) {
		this.menuImage = menuImage;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuPrice() {
		return menuPrice;
	}
	public void setMenuPrice(String menuPrice) {
		this.menuPrice = menuPrice;
	}
	public String getMenuIntroduce() {
		return menuIntroduce;
	}
	public void setMenuIntroduce(String menuIntroduce) {
		this.menuIntroduce = menuIntroduce;
	}
    
    
    
    
}
