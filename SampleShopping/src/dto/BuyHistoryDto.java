package dto;

import java.util.Date;
import java.util.List;

public class BuyHistoryDto {
	
	private int id;
	private int userId;
	private List<GoodsDto> goodsDtoList;
	private Date buyDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public List<GoodsDto> getGoodsDtoList() {
		return goodsDtoList;
	}
	public void setGoodsDtoList(List<GoodsDto> goodsDtoList) {
		this.goodsDtoList = goodsDtoList;
	}
	public Date getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	
}
