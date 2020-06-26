package dto;

public class BrandDto {
	private int id;
	private String name;
	
	public BrandDto(Integer id, String name) {
		// goods用コンストラクタ dbではnullだけどrs.getIntはintで返すため0となる
		if(id == 0) {
			this.id = 1;
			this.name = "未指定"; 
		}else {
			this.id = id;
			this.name = name;
		}
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
