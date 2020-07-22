package dto;

public class CategoryDto {
	private int id;
	private String name;
	
	public CategoryDto(Integer id, String name) {
		// goods用コンストラクタ
		if(id == 0) {
			this.id = 1;
			this.name = "未指定";
		}else {
			this.id = id;
			this.name = name;
		}
	}
	
	
	public CategoryDto() {
		// TODO 自動生成されたコンストラクター・スタブ
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
