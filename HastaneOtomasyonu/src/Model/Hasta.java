package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Helper.Helper;

public class Hasta extends User {

	Statement st = null;
	ResultSet rs = null;
	Connection con = conn.connDb();
	PreparedStatement preparedStatement = null;

	public Hasta() {

	}

	public Hasta(int id, String tcno, String name, String password, String type) {
		super(id, tcno, name, password, type);

	}

	public boolean register(String tcno, String password, String name) throws SQLException {
		int key = 0;
		boolean duplicate = false;

		String query = "INSERT INTO user" + "(tcno,password,name,type) VALUES" + "(?,?,?,?)";

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE tcno = '" + tcno + "'");

			while (rs.next()) {
				duplicate = true;
				Helper.showMsg("Bu TC Numaras�na ait bir kay�t bulunmaktad�r.");
				break;

			}
			if (!duplicate) {
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, tcno);
				preparedStatement.setString(2, password);
				preparedStatement.setString(3, name);
				preparedStatement.setString(4, "hasta\r\n");
				preparedStatement.executeUpdate();
				key = 1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (key == 1)
			return true;
		else
			return false;
	}

	public boolean addAppointmen(int doctor_id,int hasta_id,String doctor_name,String hasta_name,String app_date) throws SQLException {
		int key=0;
		
		String query = "INSERT INTO appointment" + "(doctor_id, doctor_name, hasta_id, hasta_name, app_date) VALUES" + "(?,?,?,?,?)";
		
		try {
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, doctor_id);
			preparedStatement.setString(2, doctor_name);
			preparedStatement.setInt(3, hasta_id);
			preparedStatement.setString(4,hasta_name);
			preparedStatement.setString(5, app_date);
			preparedStatement.executeUpdate();
			key=1;
			}
			catch(SQLException e){
			e.printStackTrace();
		}
		if(key==1)return true;
		else
			return false;
}
	
	public boolean updateWhourStatus(int doktor_id,String w_date) throws SQLException {
		int key=0;
		
		String query = "UPDATE whour SET status = ? WHERE doktor_id = ? AND w_date = ?";
		
		try {
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, "p");
			preparedStatement.setInt(2, doktor_id);
			preparedStatement.setString(3, w_date);
			preparedStatement.executeUpdate();
			key=1;
			}
			catch(SQLException e){
			e.printStackTrace();
		}
		if(key==1)return true;
		else
			return false;
}
	

}
