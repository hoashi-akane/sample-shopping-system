package service;

import java.util.ArrayList;

import dao.CategoryDao;
import dto.CategoryDto;

public class CategoryService {
	
	public ArrayList<CategoryDto> categoryListService(){
		CategoryDao categoryDao = new CategoryDao();
		ArrayList<CategoryDto> categoryDtoList = categoryDao.getCategoryList();
		return categoryDtoList;
	}
}
