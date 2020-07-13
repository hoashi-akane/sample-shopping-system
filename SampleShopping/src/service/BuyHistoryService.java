package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.BuyHistoryDao;
import dao.CartDao;
import dto.BuyHistoryDetailDto;
import dto.BuyHistoryDto;
import dto.CartDto;
import dto.UserDto;

public class BuyHistoryService {
	
	public boolean insertBuyHistoryFasade(UserDto userDto) {
		boolean isSuccess = false;
		CartDao cartDao = new CartDao();
		BuyHistoryDao buyHistoryDao = new BuyHistoryDao();
		List<CartDto> cartDtoList = cartDao.getCart(userDto.getId());
		BuyHistoryDto buyHistoryDto = createBuyHistoryDto(userDto.getId());
		isSuccess = buyHistoryDao.insertBuyHistory(buyHistoryDto);
		if(!isSuccess) {return false;}
		Integer buyHistoryId = buyHistoryDao.findBuyHistoryId(buyHistoryDto);
		if(buyHistoryId == null) {return false;}
		List<BuyHistoryDetailDto> buyHistoryDetailDtoList = createBuyHistoryDetailDtoList(cartDtoList, buyHistoryId.intValue());
		isSuccess = buyHistoryDao.insertBuyHistoryDetails(buyHistoryDetailDtoList);
		return isSuccess;
	}
	
//	Dtoを生成する(userId,現在時刻)
	private BuyHistoryDto createBuyHistoryDto(int userId) {
		BuyHistoryDto buyHistoryDto = new BuyHistoryDto();
		buyHistoryDto.setUserId(userId);
		buyHistoryDto.setBuyDate(new Date());
		return buyHistoryDto;
	}
	
//	List<BuyHistoryDetailDto>を生成
	private List<BuyHistoryDetailDto> createBuyHistoryDetailDtoList(List<CartDto> cartDtoList, int buyHistoryId){
		List<BuyHistoryDetailDto> buyHistoryDetailDtoList = new ArrayList<BuyHistoryDetailDto>();
		for(CartDto cartDto : cartDtoList) {
			buyHistoryDetailDtoList.add(createBuyHistoryDetailDto(cartDto, buyHistoryId));
		}
		return buyHistoryDetailDtoList;
	}
	
//	BuyHistoryDetailDtoを生成する
	private BuyHistoryDetailDto createBuyHistoryDetailDto(CartDto cartDto, int buyHistoryId) {
		BuyHistoryDetailDto buyHistoryDetailDto = new BuyHistoryDetailDto();
		buyHistoryDetailDto.setBuyHistoryId(buyHistoryId);
		buyHistoryDetailDto.setGoodsId(cartDto.getGoodsDto().getId());
		buyHistoryDetailDto.setVolume(cartDto.getVolume());
		buyHistoryDetailDto.setUnitPrice(cartDto.getGoodsDto().getPrice());
		return buyHistoryDetailDto;
	}
	
}
