package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import Helper.Helper;
import Model.Klinik;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class updateClinic extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField fldclinicDuznle;
	private static Klinik clinic;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					updateClinic frame = new updateClinic(clinic);
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
	public updateClinic(Klinik clinic) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 225, 196);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable((TableModel) null);
		table.setBounds(-197, 41, 275, 0);
		contentPane.add(table);
		
		JLabel lblPoliklinikad = new JLabel("Poliklinik Ad\u0131:");
		lblPoliklinikad.setForeground(Color.BLACK);
		lblPoliklinikad.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblPoliklinikad.setBounds(10, 25, 167, 17);
		contentPane.add(lblPoliklinikad);
		
		fldclinicDuznle = new JTextField();
		fldclinicDuznle.setColumns(10);
		fldclinicDuznle.setBounds(10, 52, 189, 29);
		fldclinicDuznle.setText(clinic.getName());
		contentPane.add(fldclinicDuznle);
		
		JButton btn_klinikupdate = new JButton("D\u00FCzenle");
		btn_klinikupdate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_klinikupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Helper.confirm("sure")) {
					try {
						clinic.updateKlinik(clinic.getId(),fldclinicDuznle.getText());
						Helper.showMsg("success");
						dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		btn_klinikupdate.setBounds(10, 92, 189, 36);
		contentPane.add(btn_klinikupdate);
	}
}
