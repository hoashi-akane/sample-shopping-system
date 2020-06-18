package dao;

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
		}
		return isSuccess;
	}
}
