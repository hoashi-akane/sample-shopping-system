package dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import dto.BrandDto;
import dto.CategoryDto;

public class CategoryDao extends DaoBase{
	public ArrayList<CategoryDto> getCategoryList() {
		ArrayList<CategoryDto> categoryDtoList = new ArrayList<CategoryDto>();
		try {
			con = super.dbOpen();
			stmt = this.con.prepareStatement("SELECT * FROM goods_category");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				CategoryDto categoryDto = new CategoryDto();
				categoryDto.setId(rs.getInt("id"));
				categoryDto.setName(rs.getString("category_name"));
				categoryDtoList.add(categoryDto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			super.dbClose();
		}
		return categoryDtoList;
	}
}
