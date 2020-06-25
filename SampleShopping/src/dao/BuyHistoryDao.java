package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.BuyHistoryDto;
import dto.GoodsDto;



public class BuyHistoryDao extends DaoBase{
	
	
//	購入履歴一覧取得
	public List<BuyHistoryDto> getBuyHistoryList(int userId){
		List<BuyHistoryDto> buyHistoryDtoList = new ArrayList<BuyHistoryDto>();
		try {
			con = super.dbOpen();
			stmt = this.con.prepareStatement("SELECT history.id, history.buy_date, goods.id as goods_id, goods.goods_name FROM buy_historys AS history"
					+ " INNER JOIN buy_history_details AS detail ON history.id = detail.buy_history_id"
					+ "INNER JOIN goods AS goods ON detail.id = goods.id WHERE user_id=?");
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			buyHistoryDtoList = buyHistoryToDto(rs);
		}catch(Exception e) {
			buyHistoryDtoList = null;
			e.printStackTrace();
		}
		return buyHistoryDtoList;
	}
	
	
//	getBuyHistoryList用dto詰め替えメソッド
	private List<BuyHistoryDto> buyHistoryToDto(ResultSet rs) {
		List<BuyHistoryDto> buyHistoryDtoList = new ArrayList<BuyHistoryDto>();
		try {
			boolean flag = rs.next();
			while(flag) {
				List<GoodsDto> goodsDtoList = new ArrayList<GoodsDto>();
				BuyHistoryDto buyHistoryDto =new BuyHistoryDto();
				buyHistoryDto.setId(rs.getInt("id"));
				buyHistoryDto.setBuyDate(rs.getDate("buy_date"));
				while(flag) {
//					1回目は必ずtrue
					if(buyHistoryDto.getId() == rs.getInt("Id")) {
						GoodsDto goodsDto = new GoodsDto();
						goodsDto.setId(rs.getInt("goods_id"));
						goodsDto.setGoodsName(rs.getString("goods_name"));
						goodsDtoList.add(goodsDto);
//						rs.next()をするのはここのみ（idが同じ限りrs.next()を、異なればdtoListに追加してbreakさせる。)
//						データがなくならない限りtrue
						flag = rs.next();
					}else {
						buyHistoryDto.setGoodsDtoList(goodsDtoList);
						buyHistoryDtoList.add(buyHistoryDto);
						break;
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			buyHistoryDtoList = null;
		}
		return buyHistoryDtoList;
	}
}
