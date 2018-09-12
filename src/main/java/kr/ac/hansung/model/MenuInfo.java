package kr.ac.hansung.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MenuInfo {

	private String menuImage;
    private String menuName;
    private String menuPrice;
    private String menuIntroduce;
    private boolean soldOut;
    
}
