package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.CartDto;

public class CartDao extends DaoBase{

	
//	カート商品削除
	public boolean deleteCart(int id) {
		boolean isSuccess = false;
		try {
			con = super.dbOpen();
			stmt = con.prepareStatement("DELETE FROM cart WHERE id=?");
			stmt.setInt(1, id);
			stmt.executeUpdate();
			isSuccess = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return isSuccess;
	}
	
	
//	カート削除（ユーザ削除に伴う削除)
	public boolean delteteCarts(int userId) {
		boolean isSuccess = false;
		try {
			con = super.dbOpen();
			stmt = con.prepareStatement("DELETE FROM carts WHERE user_id=?");
			stmt.setInt(1, userId);
			stmt.executeUpdate();
			isSuccess = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return isSuccess;
	}
	
	
//	カート情報更新（数量
	public boolean updateCart(CartDto cartDto) {
		boolean isSuccess = false;
		try {
			con = super.dbOpen();
			stmt = con.prepareStatement("UPDATE carts SET volume=? WHERE id=?");
			stmt.setInt(1, cartDto.getVolume());
			stmt.setInt(2, cartDto.getId());
			stmt.executeUpdate();
			isSuccess = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return isSuccess;
	}
	
	
//	カート情報追加
	public boolean insertCart(CartDto cartDto) {
		boolean isSuccess = false;
		try {
			con = super.dbOpen();
			stmt = con.prepareStatement("INSERT INTO carts(user_id,goods_id,volume) VALUES(?,?,?)");
			stmt.setInt(1, cartDto.getUserId());
			stmt.setInt(2, cartDto.getGoodsId());
			stmt.setInt(3, cartDto.getVolume());
			stmt.executeUpdate();
			isSuccess = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return isSuccess;
	}
	
	
//	カート情報取得
	public List<CartDto> getCart(int userId){
		List<CartDto> cartDtoList = new ArrayList<CartDto>();
		try {
			con = super.dbOpen();
			stmt = con.prepareStatement("SELECT * FROM carts WHERE userId=?");
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				CartDto cartDto = cartToDto(rs);
				cartDtoList.add(cartDto);
			}
		}catch(Exception e) {
			cartDtoList = null;
			e.printStackTrace();
		}
		return cartDtoList;
	}
	
//	cartsから取得したカラムをdtoへ詰め替え
	private CartDto cartToDto(ResultSet rs) {
		CartDto cartDto = new CartDto();
		try {
			cartDto.setId(rs.getInt("id"));
			cartDto.setUserId(rs.getInt("user_id"));
			cartDto.setGoodsId(rs.getInt("goods_id"));
			cartDto.setVolume(rs.getInt("volume"));
		}catch(Exception e ) {
			e.printStackTrace();
		}
		return cartDto;
	}
}
