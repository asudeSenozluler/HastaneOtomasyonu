package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Hasta;
import Model.User;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class RegisterGUI extends JFrame {
	private JTextField fld_name;
	private JTextField fld_tc;
	private JPasswordField passwordField;
	private Hasta hasta = new Hasta();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setResizable(false);
		setTitle("Hastane Y\u00F6netim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 330);
		JPanel w_pane = new JPanel();
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel BashekimAdSoyad = new JLabel("Ad Soyad:");
		BashekimAdSoyad.setForeground(Color.BLACK);
		BashekimAdSoyad.setFont(new Font("Times New Roman", Font.BOLD, 18));
		BashekimAdSoyad.setBounds(10, 11, 87, 17);
		w_pane.add(BashekimAdSoyad);
		
		fld_name = new JTextField();
		fld_name.setColumns(10);
		fld_name.setBounds(10, 31, 264, 27);
		w_pane.add(fld_name);
		
		fld_tc = new JTextField();
		fld_tc.setColumns(10);
		fld_tc.setBounds(10, 96, 264, 27);
		w_pane.add(fld_tc);
		
		JLabel lblTcNumaras = new JLabel("T.C. Numarasý:");
		lblTcNumaras.setForeground(Color.BLACK);
		lblTcNumaras.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTcNumaras.setBounds(10, 69, 127, 17);
		w_pane.add(lblTcNumaras);
		
		JLabel lblifre = new JLabel("Þifre:");
		lblifre.setForeground(Color.BLACK);
		lblifre.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblifre.setBounds(10, 127, 51, 17);
		w_pane.add(lblifre);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(10, 155, 264, 27);
		w_pane.add(passwordField);
		
		JButton btnRegister = new JButton("Kayýt Ol");
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_tc.getText().length()==0 || passwordField.getText().length()==0 || fld_name.getText().length()==0) {
					Helper.showMsg("fill");
				}
				else {
					try {
						boolean control = hasta.register(fld_tc.getText(),passwordField.getText(),fld_name.getText());
						if(control) {
							Helper.showMsg("success");
							LoginGUI login=new LoginGUI();
							login.setVisible(true);
							dispose();
						}
						else {
							Helper.showMsg("error");
						}
					}
					catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					
					
				}

			}
		});
		btnRegister.setBounds(10, 186, 264, 39);
		w_pane.add(btnRegister);
		
		JButton btnBack = new JButton("Geri Dön");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login=new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(10, 236, 264, 44);
		w_pane.add(btnBack);
	}
}
