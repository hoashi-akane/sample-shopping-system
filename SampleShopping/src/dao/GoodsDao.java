package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dto.BrandDto;
import dto.CategoryDto;
import dto.GoodsDto;

public class GoodsDao extends DaoBase{

//	商品一覧取得
		public List<GoodsDto> getGoodsList(){
		List<GoodsDto> goodsDtoList= new ArrayList<GoodsDto>();
		try {
			con = super.dbOpen();
			stmt = this.con.prepareStatement("SELECT * FROM goods");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				GoodsDto goodsDto = goodsToDto(rs);
				goodsDtoList.add(goodsDto);
			}
		}catch(Exception e) {
			goodsDtoList = null;
			e.printStackTrace();
		}
		super.dbClose();
		return goodsDtoList;
	}

//	検索結果の商品一覧取得
	public List<GoodsDto> getGoodsList(String search){
		List<GoodsDto> goodsDtoList= new ArrayList<GoodsDto>();
		try {
			con = super.dbOpen();
			stmt = this.con.prepareStatement("SELECT * FROM goods WHERE goods_name LIKE ?;");
			stmt.setString(1, "%"+search+"%");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				GoodsDto goodsDto = goodsToDto(rs);
				goodsDtoList.add(goodsDto);
			}
		}catch(Exception e) {
			goodsDtoList = null;
			e.printStackTrace();
		}
		super.dbClose();
		return goodsDtoList;
	}

//	商品取得（ひとつ）
	public GoodsDto getGoods(int id) {
		GoodsDto goodsDto = null;
		try {
			con = super.dbOpen();

			stmt = this.con.prepareStatement("SELECT goods.id, goods.goods_name, goods.price, goods.description, goods.stock, goods.image_dir, goods.sales_quantity, goods.is_sale, goods.category_id,"
					+ " goods.brand_id, brands.brand_name, categorys.category_name FROM goods LEFT OUTER JOIN brands ON goods.brand_id = brands.id "
					+ "LEFT OUTER JOIN goods_category AS categorys ON goods.category_id = categorys.id WHERE goods.id = ?;");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				goodsDto = goodsToDto(rs);
				goodsDto.setBrandDto(new BrandDto(rs.getInt("brand_id"),rs.getString("brand_name")));
				goodsDto.setCategoryDto(new CategoryDto(rs.getInt("category_id"), rs.getString("category_name")));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		super.dbClose();
		return goodsDto;
	}
	
//	 商品id取得（商品登録用）
	public int findGoods(GoodsDto goodsDto) {
		int id = 0;
		try {
			con = super.dbOpen();
			stmt = this.con.prepareStatement("SELECT id FROM goods "
					+ "WHERE goods_name=? AND description=?;");
			stmt.setString(1, goodsDto.getGoodsName());
			stmt.setString(2, goodsDto.getDescription());
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
	
//	商品登録
	public boolean insertGoods(GoodsDto goodsDto) {
		boolean isSuccess = false;
		try {
			con = super.dbOpen();
			stmt = this.con.prepareStatement("INSERT INTO goods("
					+ "goods_name,price,stock,description,image_dir,sales_quantity,is_sale,category_id,brand_id "
					+ ")VALUES(?,?,?,?,?,?,?,?,?);");
			stmt.setString(1, goodsDto.getGoodsName());
			stmt.setInt(2, goodsDto.getPrice());
			stmt.setInt(3, goodsDto.getStock());
			stmt.setString(4, goodsDto.getDescription());
			stmt.setString(5, goodsDto.getImageDir());
			stmt.setInt(6, goodsDto.getSalesQuantity());
			stmt.setBoolean(7, goodsDto.isSale());
			stmt.setInt(8, goodsDto.getCategoryId());
			stmt.setInt(9, goodsDto.getBrandId());
			stmt.executeUpdate();
			isSuccess = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		super.dbClose();
		return isSuccess;
	}
	
//	商品の画像パスを登録
	public boolean insertImagePath(int id, String imagePath) {
		boolean isSuccess = false;
		try {
			con = super.dbOpen();
			stmt = this.con.prepareStatement("UPDATE goods SET image_dir =? WHERE id =?");
			stmt.setString(1, imagePath);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			isSuccess = true;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			super.dbClose();
		}
		return isSuccess;
	}

//	商品削除
	public boolean deleteGoods(int id){
		boolean isSuccess = false;
		try {
			con = super.dbOpen();
			stmt = this.con.prepareStatement("DELETE FROM goods WHERE id = ?");
			stmt.setInt(1, id);
			stmt.executeUpdate();
			isSuccess = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		super.dbClose();
		return isSuccess;
	}

//	商品更新
	public boolean updateGoods(GoodsDto goodsDto) {
		boolean isSuccess = false;
		try {
			con = super.dbOpen();
			stmt = this.con.prepareStatement("UPDATE goods SET goods_name=?,price=?,stock=?,description=?,"
					+ "is_sale=?,category_id=?,brand_id=? WHERE id=?;");
			stmt.setString(1, goodsDto.getGoodsName());
			stmt.setInt(2, goodsDto.getPrice());
			stmt.setInt(3, goodsDto.getStock());
			stmt.setString(4, goodsDto.getDescription());
			stmt.setBoolean(5, goodsDto.isSale());
			stmt.setInt(6, goodsDto.getCategoryId());
			stmt.setInt(7, goodsDto.getBrandId());
			stmt.setInt(8, goodsDto.getId());
			stmt.executeUpdate();
			isSuccess = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		super.dbClose();
		return isSuccess;
	}

//	goodsから取得したカラムをDtoに詰め替え
	private GoodsDto goodsToDto(ResultSet rs) {
		GoodsDto goodsDto = new GoodsDto();
		try {
			goodsDto.setId(rs.getInt("id"));
			goodsDto.setGoodsName(rs.getString("goods_name"));
			goodsDto.setPrice(rs.getInt("price"));
			goodsDto.setStock(rs.getInt("stock"));
			goodsDto.setDescription(rs.getString("description"));
			goodsDto.setImageDir(rs.getString("image_dir"));
			goodsDto.setSale(rs.getBoolean("is_sale"));
			goodsDto.setSalesQuantity(rs.getInt("sales_quantity"));
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return goodsDto;
	}
}
