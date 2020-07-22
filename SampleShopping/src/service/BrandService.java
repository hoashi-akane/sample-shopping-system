package service;

import java.util.ArrayList;

import dao.BrandDao;
import dto.BrandDto;

public class BrandService {
	
	public ArrayList<BrandDto> brandListService(){
		BrandDao brandDao = new BrandDao();
		ArrayList<BrandDto> brandDtoList = brandDao.getBrandList();
		return brandDtoList;
	}
}
