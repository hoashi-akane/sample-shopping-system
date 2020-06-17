package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;


public class DaoBase {

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int rsno=0;

		DataSource ds=null;

		public Connection DbOpen() {


		try{
			///////////////////////////////////
			//DBの接続:
			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample_db","root","root");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

		public void Dbclose() {

		try {
			if(rs!=null) {
				rs.close();
			}if(con !=null){
					con.close();
				}
		}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}

