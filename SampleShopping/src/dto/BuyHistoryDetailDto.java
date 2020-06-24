package dto;

public class BuyHistoryDetailDto {
	
	private int id;
	private int buyHistoryId;
	private int goodsId;
	private int volume;
	private int unitPrice;
	private boolean isHidden;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBuyHistoryId() {
		return buyHistoryId;
	}
	public void setBuyHistoryId(int buyHistoryId) {
		this.buyHistoryId = buyHistoryId;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public int getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
	public boolean isHidden() {
		return isHidden;
	}
	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}
}

