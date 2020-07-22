package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.BrandDto;

public class BrandDao extends DaoBase{
	
	public ArrayList<BrandDto> getBrandList() {
		ArrayList<BrandDto> brandDtoList = new ArrayList<BrandDto>();
		try {
			con = super.dbOpen();
			stmt = this.con.prepareStatement("SELECT * FROM brands");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				BrandDto brandDto = new BrandDto();
				brandDto.setId(rs.getInt("id"));
				brandDto.setName(rs.getString("brand_name"));
				brandDtoList.add(brandDto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			super.dbClose();
		}
		return brandDtoList;
	}
}
