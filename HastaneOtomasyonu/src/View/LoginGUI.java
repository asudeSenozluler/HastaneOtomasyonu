package View;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import Helper.*;
import Model.Bashekim;
import Model.Doktor;
import Model.Hasta;

public class LoginGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_hastaTc;
	private JTextField fld_DoktorTc;
	private JPasswordField passwordFieldDoktor;
	private JPasswordField passwordFieldHasta;
	private DBConnection conn=new DBConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		setResizable(false);
		setTitle("Hastane Y\u00F6netim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100, 500, 400);
		w_pane = new JPanel();
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource("login.png")));
		lbl_logo.setBounds(192, 10, 100, 75);
		w_pane.add(lbl_logo);
		
		JLabel lblNewLabel = new JLabel(" Hastane Y�netim Sistemine Ho�geldiniz");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(68, 62, 347, 47);
		w_pane.add(lblNewLabel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 120, 464, 230);
		w_pane.add(tabbedPane);
		
		JPanel w_hastaLogin = new JPanel();
		tabbedPane.addTab("Hasta Giri�i", null, w_hastaLogin, null);
		w_hastaLogin.setLayout(null);
		
		JLabel lblTcNumaras = new JLabel("T.C. Numaras\u0131:");
		lblTcNumaras.setForeground(Color.BLACK);
		lblTcNumaras.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTcNumaras.setBounds(10, 50, 123, 37);
		w_hastaLogin.add(lblTcNumaras);
		
		JLabel lblifre = new JLabel("\u015Eifre:");
		lblifre.setForeground(Color.BLACK);
		lblifre.setFont(new Font("Times New Roman", Font.BOLD, 19));
		lblifre.setBounds(84, 98, 49, 37);
		w_hastaLogin.add(lblifre);
		
		
		fld_hastaTc = new JTextField();
		fld_hastaTc.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		fld_hastaTc.setBounds(132, 60, 123, 20);
		w_hastaLogin.add(fld_hastaTc);
		fld_hastaTc.setColumns(10);
		
		JButton btnHastaKay�tOl = new JButton("Kay�t Ol");
		btnHastaKay�tOl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnHastaKay�tOl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI rGUI=new RegisterGUI();
				rGUI.setVisible(true);
				dispose();
			}
		});
		btnHastaKay�tOl.setBounds(318, 108, 90, 23);
		w_hastaLogin.add(btnHastaKay�tOl);
		
		JButton btnHastaLogin = new JButton("Giri� yap");
		btnHastaLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnHastaLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_hastaTc.getText().length()==0 || passwordFieldHasta.getText().length()==0) {
					Helper.showMsg("fill");
				}
				else {
					boolean key=true;
					try {
						Connection con=conn.connDb();
						Statement st=con.createStatement();
						ResultSet rs=st.executeQuery("SELECT * FROM user" );
						
						
						
						
						while(rs.next()) {
							if(fld_hastaTc.getText().equals(rs.getString("tcno")) && passwordFieldHasta.getText().equals(rs.getString("password"))) {
							
							if(rs.getString("type").equals("hasta\r\n")) {
								Hasta hasta=new Hasta();
								hasta.setId(rs.getInt("id"));
								hasta.setPassword("password");
								hasta.setTcno(rs.getString("tcno"));
								hasta.setName(rs.getString("name"));
								hasta.setType(rs.getString("type"));
								HastaGUI hGUI=new HastaGUI(hasta);
								hGUI.setVisible(true);
								dispose();
								key=false;
							 	}
							}
						}
						}catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					if(key) {
						Helper.showMsg("B�yle bir kullan�c� bul�namad�. L�tfen kay�t olunuz!"); 
					}
					
					
				}
			}
		});
		btnHastaLogin.setBounds(318, 60, 90, 25);
		w_hastaLogin.add(btnHastaLogin);
		
		passwordFieldHasta = new JPasswordField();
		passwordFieldHasta.setBounds(132, 108, 123, 20);
		w_hastaLogin.add(passwordFieldHasta);
		
		JPanel w_doctorLogin = new JPanel();
		tabbedPane.addTab("Doktor Giri�i", null, w_doctorLogin, null);
		w_doctorLogin.setLayout(null);
		
		JLabel lblifre_1 = new JLabel("�ifre:");
		lblifre_1.setBounds(84, 98, 49, 37);
		lblifre_1.setForeground(Color.BLACK);
		lblifre_1.setFont(new Font("Times New Roman", Font.BOLD, 19));
		w_doctorLogin.add(lblifre_1);
		
		JLabel lblTcNumaras_1 = new JLabel("T.C. Numaras�:");
		lblTcNumaras_1.setBounds(10, 50, 123, 37);
		lblTcNumaras_1.setForeground(Color.BLACK);
		lblTcNumaras_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		w_doctorLogin.add(lblTcNumaras_1);
		
		fld_DoktorTc = new JTextField();
		fld_DoktorTc.setBounds(132, 60, 123, 20);
		fld_DoktorTc.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		fld_DoktorTc.setColumns(10);
		w_doctorLogin.add(fld_DoktorTc);
		
		JButton btnDoctorLogin = new JButton("Giri� yap");
		btnDoctorLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDoctorLogin.addActionListener(new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent e) {
				if(fld_DoktorTc.getText().length()==0||passwordFieldDoktor.getText().length()==0) {
					Helper.showMsg("fill");
				}
				else {
					
					try {
						Connection con=conn.connDb();
						Statement st=con.createStatement();
						ResultSet rs=st.executeQuery("SELECT * FROM user" );
						while(rs.next()) {
							if(fld_DoktorTc.getText().equals(rs.getString("tcno")) && passwordFieldDoktor.getText().equals(rs.getString("password"))) {
							
							if(rs.getString("type").equals("bashekim")) {
								Bashekim bhekim=new Bashekim();
								bhekim.setId(rs.getInt("id"));
								bhekim.setPassword("password");
								bhekim.setTcno(rs.getString("tcno"));
								bhekim.setName(rs.getString("name"));
								bhekim.setType(rs.getString("type"));
								BashekimGUI bGUI=new BashekimGUI(bhekim);
								bGUI.setVisible(true);
								dispose();
							 	}
							
							if(rs.getString("type").equals("doktor")) {
								Doktor doctor=new Doktor();
								doctor.setId(rs.getInt("id"));
								doctor.setPassword("password");
								doctor.setTcno(rs.getString("tcno"));
								doctor.setName(rs.getString("name"));
								doctor.setType(rs.getString("type"));
								DoktorGUI dGUI=new DoktorGUI(doctor);
								dGUI.setVisible(true);
								dispose();
							}
							}
						}
						}
								
							
					 catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					}
				}
			}
			);
		btnDoctorLogin.setBounds(318, 78, 100, 32);
		w_doctorLogin.add(btnDoctorLogin);
		
		passwordFieldDoktor = new JPasswordField();
		passwordFieldDoktor.setBounds(132, 108, 123, 20);
		w_doctorLogin.add(passwordFieldDoktor);
	}
}