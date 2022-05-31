package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Whour {
	private int id, doktor_id;
	private String doktor_name, w_date, status;
	
DBConnection conn = new DBConnection();
	
	Statement st = null;
	ResultSet rs= null;
	Connection con= conn.connDb();
	PreparedStatement preparedStatement=null;
	
	public Whour(int id, int doktor_id, String doktor_name, String w_date, String status) {
		this.id = id;
		this.doktor_id = doktor_id;
		this.doktor_name = doktor_name;
		this.w_date = w_date;
		this.status = status;
	}
	
	public Whour() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDoktor_id() {
		return doktor_id;
	}

	public void setDoktor_id(int doktor_id) {
		this.doktor_id = doktor_id;
	}

	public String getDoktor_name() {
		return doktor_name;
	}

	public void setDoktor_name(String doktor_name) {
		this.doktor_name = doktor_name;
	}

	public String getW_date() {
		return w_date;
	}

	public void setW_date(String w_date) {
		this.w_date = w_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public ArrayList<Whour> getWhourList(int doktor_id) throws SQLException{
		ArrayList<Whour> list = new ArrayList<>();
		
		Whour obj;
		try {
			Connection con=conn.connDb();
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
	


}
