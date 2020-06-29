package dto;

public class CartDto {

	private int id;
	private int userId;
	private int goodsId;
	private int volume;
	private GoodsDto goodsDto;
	
	
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
	public GoodsDto getGoodsDto() {
		return goodsDto;
	}
	public void setGoodsDto(GoodsDto goodsDto) {
		this.goodsDto = goodsDto;
	}
	
}
