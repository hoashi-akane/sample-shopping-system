package dao;

import java.sql.ResultSet;

import dto.UserDto;

public class UserDao extends UserDaoBase{
	
	public boolean insertUser(UserDto userDto){
		boolean isSuccess = false;
		try {
			con = super.dbOpen();
			stmt = this.con.prepareStatement(
					"INSERT INTO users("
					+ "login_id,user_password,user_name,address,zip_code,"
					+ "address_sub,zip_code_sub,tel,gender,mail_address,is_admin"
					+ ") VALUES (?,?,?,?,?,?,?,?,?,?,false)");
			stmt.setString(1, userDto.getLoginId());
			stmt.setString(2, userDto.getPassword());
			stmt.setString(3, userDto.getUserName());
			stmt.setString(4, userDto.getAddress());
			stmt.setString(5, userDto.getZipCode());
			stmt.setString(6, userDto.getAddressSub());
			stmt.setString(7, userDto.getZipCodeSub());
			stmt.setString(8, userDto.getTel());
			stmt.setByte(9, userDto.getGender());
			stmt.setString(10, userDto.getMailAddress());
			stmt.executeUpdate();
			isSuccess = true;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			super.dbClose();
		}
		return isSuccess;
	}

//	カスタマーid取得
	public String getPaymentCustomerId(int userId) {
		String paymentCustomerId = null;
		try {
			con = super.dbOpen();
			stmt = this.con.prepareStatement("SELECT payment_customer_id FROM users WHERE id = ?");
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			paymentCustomerId = rs.getString("payment_customer_id");
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			super.dbClose();
		}
		
		return paymentCustomerId;
	}
	
//	カスタマーidを更新（登録する)
	public boolean updatePaymentCustomerId(int userId, String customerId) {
		boolean isSuccess = false;
		try {
			con = super.dbOpen();
			stmt = this.con.prepareStatement("UPDATE users SET payment_customer_id = ? WHERE id = ?");
			stmt.setString(1, customerId);
			stmt.setInt(2, userId);
			stmt.executeUpdate();
			isSuccess = true;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			super.dbClose();
		}
		return isSuccess;
	}
	
	public UserDto findUser(int id) {
		UserDto userDto = null;
		try {
			con = super.dbOpen();
			stmt = this.con.prepareStatement("SELECT * FROM users WHERE id=? AND is_admin=0");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			userDto = userToDto(rs);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return userDto;
	}
}
