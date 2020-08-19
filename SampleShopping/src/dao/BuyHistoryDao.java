package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.BuyHistoryDetailDto;
import dto.BuyHistoryDto;
import dto.GoodsDto;


public class BuyHistoryDao extends DaoBase{
	
//	購入履歴一覧取得
	public List<BuyHistoryDto> getBuyHistoryList(int userId){
		List<BuyHistoryDto> buyHistoryDtoList = new ArrayList<BuyHistoryDto>();
		try {
			con = super.dbOpen();
			stmt = this.con.prepareStatement("SELECT history.id, history.buy_date, goods.id as goods_id, goods.goods_name, detail.volume, detail.unit_price FROM buy_historys AS history"
					+ " INNER JOIN buy_history_details AS detail ON history.id = detail.buy_history_id"
					+ " LEFT JOIN goods AS goods ON detail.goods_id = goods.id WHERE user_id=?");
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			buyHistoryDtoList = buyHistoryToDto(rs);
		}catch(Exception e) {
			buyHistoryDtoList = null;
			e.printStackTrace();
		}finally {
			super.dbClose();
		}
		return buyHistoryDtoList;
	}
	
//	購入履歴登録
	public boolean insertBuyHistory(BuyHistoryDto buyHistoryDto) {
		boolean isSuccess = false;
		try {
			con = super.dbOpen();
			stmt = this.con.prepareStatement("INSERT INTO buy_historys(user_id, buy_date) VALUES(?, ?)");
			stmt.setInt(1, buyHistoryDto.getUserId());
			stmt.setDate(2, new java.sql.Date(buyHistoryDto.getBuyDate().getTime()));
			stmt.executeUpdate();
			isSuccess = true;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			super.dbClose();
		}
		return isSuccess;
	}
	
//  購入履歴詳細登録
	public boolean insertBuyHistoryDetails(List<BuyHistoryDetailDto> buyHistoryDetailDtoList) {
		boolean isSuccess = false;
		try {
			con = super.dbOpen();
			for(BuyHistoryDetailDto buyHistoryDetailDto : buyHistoryDetailDtoList) {
				stmt = this.con.prepareStatement("INSERT INTO buy_history_details(buy_history_id, goods_id, volume, unit_price) VALUES(?,?,?,?);");
				stmt.setInt(1, buyHistoryDetailDto.getBuyHistoryId());
				stmt.setInt(2, buyHistoryDetailDto.getGoodsId());
				stmt.setInt(3, buyHistoryDetailDto.getVolume());
				stmt.setInt(4, buyHistoryDetailDto.getUnitPrice());
				stmt.executeUpdate();
			}
			isSuccess = true;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			super.dbClose();
		}
		return isSuccess;
	}
	
//	購入履歴id取得
	public Integer findBuyHistoryId(BuyHistoryDto buyHistoryDto) {
		Integer id = null;
		try {
			con = super.dbOpen();
			stmt = this.con.prepareStatement("SELECT id FROM buy_historys WHERE user_id = ? AND buy_date = ? ORDER BY id DESC Limit 1");
			stmt.setInt(1, buyHistoryDto.getUserId());
			stmt.setDate(2, new java.sql.Date(buyHistoryDto.getBuyDate().getTime()));
			ResultSet rs = stmt.executeQuery();
			rs.next();
			id = rs.getInt("id");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			super.dbClose();
		}
		return id;
	}
	
//	非表示機能を有効化
	public boolean hiddenBuyHistory(int id) {
		boolean isSuccess = false;
		try {
			con = super.dbOpen();
			stmt = this.con.prepareStatement("UPDATE buy_history_details SET is_hidden = true WHERE id = ?");
			stmt.setInt(1, id);
			stmt.executeUpdate();
			isSuccess = true;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			super.dbClose();
		}
		return isSuccess;
	}
	
//	getBuyHistoryList用dto詰め替えメソッド
	private List<BuyHistoryDto> buyHistoryToDto(ResultSet rs) {
		List<BuyHistoryDto> buyHistoryDtoList = new ArrayList<BuyHistoryDto>();
		try {
			boolean flag = rs.next();
			while(flag) {
				List<GoodsDto> goodsDtoList = new ArrayList<GoodsDto>();
				List<BuyHistoryDetailDto> buyHistoryDetailDtoList = new ArrayList<BuyHistoryDetailDto>();
				BuyHistoryDto buyHistoryDto =new BuyHistoryDto();
				buyHistoryDto.setId(rs.getInt("id"));
				buyHistoryDto.setBuyDate(rs.getDate("buy_date"));
				int totalPrice = 0;
				while(flag) {
//					1回目は必ずtrue
					if(buyHistoryDto.getId() == rs.getInt("Id")) {
						GoodsDto goodsDto = new GoodsDto();
						int goodsId = rs.getInt("goods_id");
						String goodsName = rs.getString("goods_name");
						if(goodsId == 0) {
							goodsName="削除済み商品";
						}
	
						goodsDto.setId(goodsId);
						goodsDto.setGoodsName(goodsName);
						
						BuyHistoryDetailDto buyHistoryDetailDto = new BuyHistoryDetailDto();
						buyHistoryDetailDto.setUnitPrice(rs.getInt("unit_price"));
						buyHistoryDetailDto.setVolume(rs.getInt("volume"));
						
						totalPrice+= buyHistoryDetailDto.getUnitPrice() * buyHistoryDetailDto.getVolume();

						goodsDtoList.add(goodsDto);
						buyHistoryDetailDtoList.add(buyHistoryDetailDto);
//						rs.next()をするのはここのみ（idが同じ限りrs.next()を、異なればdtoListに追加してbreakさせる。)
//						データがなくならない限りtrue
						flag = rs.next();
						if(flag == false) {
							buyHistoryDto.setGoodsDtoList(goodsDtoList);
							buyHistoryDto.setDetailDtoList(buyHistoryDetailDtoList);
							buyHistoryDto.setTotalPrice(totalPrice);
							buyHistoryDtoList.add(buyHistoryDto);
						}
					}else {
						buyHistoryDto.setGoodsDtoList(goodsDtoList);
						buyHistoryDto.setDetailDtoList(buyHistoryDetailDtoList);
						buyHistoryDto.setTotalPrice(totalPrice);
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
