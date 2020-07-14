package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import dto.UserDto;

public class UserDaoBase extends DaoBase{
//	ログイン可能か調べユーザ情報を返す。
	public UserDto login(String loginId, String password) {
		UserDto userDto = null;
		try {
			con = super.dbOpen();
			stmt = this.con.prepareStatement("SELECT * FROM users WHERE login_id=? AND user_password=?");
			this.stmt.setString(1, loginId);
			this.stmt.setString(2, password);
			ResultSet rs = this.stmt.executeQuery();
			if(rs.next()) {
				userDto = userToDto(rs);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		super.dbClose();
		return userDto;
	}
	
//	ユーザ削除を行う。
	public boolean deleteUser(int id) {
		boolean isSuccess =false;
		try {
			con = super.dbOpen();
			stmt = this.con.prepareStatement("DELETE FROM users WHERE id = ?");
			stmt.setInt(1, id);
			stmt.executeUpdate();
			isSuccess = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return isSuccess;
	}
	
//	ユーザの更新を行う。
	public boolean updateUser(UserDto userDto) {
		boolean isSuccess = false;
		try {
			con = super.dbOpen();
			stmt = this.con.prepareStatement(
					"UPDATE users SET login_id=?, user_password=?, user_name=?,"
					+ " address=?, zip_code=?, address_sub=?, zip_code_sub=?, tel=?,"
					+ " gernder=?, mail_address=? WHERE id = ?");
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
			stmt.setInt(11, userDto.getId());
			stmt.executeUpdate();
			isSuccess = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return isSuccess;
	}
	
//	dbのカラムから取得したデータをdtoに詰め替えるメソッド
	protected UserDto userToDto(ResultSet rs) {
		UserDto userDto = new UserDto();
		try {
			userDto.setId(rs.getInt("id"));
			userDto.setLoginId(rs.getString("login_id"));
			userDto.setUserName(rs.getString("user_name"));
			userDto.setZipCode(rs.getString("zip_code"));
			userDto.setAddress(rs.getString("address"));
			userDto.setAddressSub(rs.getString("address_sub"));
			userDto.setTel(rs.getString("tel"));
			userDto.setGender(rs.getByte("gender"));
			userDto.setAdmin(rs.getBoolean("is_admin"));
		} catch (SQLException e) {
			userDto = null;
		}
		return userDto;
	}
}
