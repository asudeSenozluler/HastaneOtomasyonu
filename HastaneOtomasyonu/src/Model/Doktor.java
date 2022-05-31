package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Doktor extends User{
	Statement st = null;
	ResultSet rs= null;
	Connection con = conn.connDb();
	PreparedStatement preparedStatement=null;
	public Doktor() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Doktor(int id, String tcno, String name, String password, String type) {
		super(id, tcno, name, password, type);
		// TODO Auto-generated constructor stub
	}
	
	public boolean addWhour(int doktor_id, String doktor_name, String w_date) throws SQLException {
		int key=0;
		int count=0;
		
		String query = "INSERT INTO whour" + "(doktor_id, doktor_name, w_date) VALUES" + "(?,?,?)" ;
		
		try {
			st=con.createStatement();
			rs= st.executeQuery("SELECT * FROM whour WHERE status='a' AND doktor_id= " + doktor_id + " AND w_date='" + w_date + "'");
			
			while(rs.next()) {
				count++;
				break;
				
			}
			if(count==0) {
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, doktor_id);
			preparedStatement.setString(2, doktor_name);
			preparedStatement.setString(3, w_date);
			preparedStatement.executeUpdate();
			}
			key=1;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (key == 1)
			return true;
		else 
			return false;
	}
	public ArrayList<Whour> getWhourList(int doktor_id) throws SQLException{
		ArrayList<Whour> list = new ArrayList<>();
		
		Whour obj;
		try {
			st= con.createStatement();
			rs= st.executeQuery("SELECT * FROM whour WHERE status = 'a' AND doktor_id = " + doktor_id);
			while (rs.next()) {
				obj= new Whour();
				obj.setId(rs.getInt("id"));
				obj.setDoktor_name(rs.getString("status"));
				obj.setW_date(rs.getString("w_date"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
		
		}
	public boolean deleteWhour(int id) throws SQLException {
		String query="DELETE FROM whour WHERE id = ? ";
		boolean key=false;
		
		try {
			st=con.createStatement();
			preparedStatement=con.prepareStatement(query);
			preparedStatement.setInt(1,id);
			preparedStatement.executeUpdate();
			key=true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if(key)
			return true;
		else
			return false;
		
	}
}
