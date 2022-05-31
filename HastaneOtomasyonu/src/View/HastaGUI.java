package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Helper.Helper;
import Helper.Item;
import Model.Appointment;
import Model.Hasta;
import Model.Klinik;
import Model.Whour;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTable;

    public class HastaGUI extends JFrame {
    	
	private static Hasta hasta=new Hasta();
	private Klinik clinic=new Klinik();
	private JPanel w_pane;
	private JTable tableDoktor;
	private DefaultTableModel doctorModel;
	private Object[] doctorData = null;
	private JTable table_whour;
	private Whour whour =new Whour();
	private DefaultTableModel whourModel;
	private Object[] whourData = null;
	private int selectDoctorID=0;
	private String selectDoctorName=null;
	private JTable table_app;
	private DefaultTableModel appointModel;
	private Object[] appointData = null;
	private Appointment appoint=new Appointment();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HastaGUI frame = new HastaGUI(hasta);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public HastaGUI(Hasta hasta) throws SQLException {
		
		doctorModel=new DefaultTableModel();
		Object[] colDoctor=new Object[2];
		colDoctor[0]="ID";
		colDoctor[1]="Ad Soyad";
		doctorModel.setColumnIdentifiers(colDoctor);
		doctorData=new Object[2];
		
		whourModel=new DefaultTableModel();
		Object[] colWhour=new Object[2];
		colWhour[0]="ID";
		colWhour[1]="Tarih";
		whourModel.setColumnIdentifiers(colWhour);
		whourData=new Object[2];
		
		appointModel=new DefaultTableModel();
		Object[] colAppoint=new Object[3];
		colAppoint[0]="ID";
		colAppoint[1]="Doktor";
		colAppoint[2]="Tarih";
		appointModel.setColumnIdentifiers(colAppoint);
		appointData=new Object[3];
		for(int i=0;i<appoint.getHastaList(hasta.getId()).size();i++) {
			appointData[0]=appoint.getHastaList(hasta.getId()).get(i).getId();
			appointData[1]=appoint.getHastaList(hasta.getId()).get(i).getDoctorName();
			appointData[2]=appoint.getHastaList(hasta.getId()).get(i).getAppDate();
			appointModel.addRow(appointData);
			
		}
		
		setResizable(false);
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hoþgeldiniz, Sayýn "+hasta.getName());
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel.setBounds(10, 11, 448, 33);
		w_pane.add(lblNewLabel);
		
		JButton btnHastaCikis = new JButton("\u00C7\u0131k\u0131\u015F Yap");
		btnHastaCikis.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnHastaCikis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login=new LoginGUI();
				login.setVisible(true);
				dispose();
				
			}
		});
		btnHastaCikis.setBounds(635, 17, 89, 23);
		w_pane.add(btnHastaCikis);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(10, 67, 714, 383);
		w_pane.add(w_tab);
		
		JPanel w_appointment = new JPanel();
		w_tab.addTab("Randevu Sistemi", null, w_appointment, null);
		w_appointment.setLayout(null);
		
		JScrollPane scrollDoktor = new JScrollPane();
		scrollDoktor.setBounds(10, 35, 250, 309);
		w_appointment.add(scrollDoktor);
		
		tableDoktor = new JTable(doctorModel);
		scrollDoktor.setViewportView(tableDoktor);
		
		JLabel lblNewLabel_1 = new JLabel("Doktor Listesi");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 11, 99, 14);
		w_appointment.add(lblNewLabel_1);
		
		JLabel lblPoliklinikAd = new JLabel("Poliklinik Ad\u0131: ");
		lblPoliklinikAd.setForeground(Color.BLACK);
		lblPoliklinikAd.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblPoliklinikAd.setBounds(270, 35, 99, 19);
		w_appointment.add(lblPoliklinikAd);
		
		JComboBox select_klinik = new JComboBox();
		select_klinik.setFont(new Font("Tahoma", Font.PLAIN, 13));
		select_klinik.setBounds(269, 57, 170, 27);
		select_klinik.addItem("--Poliklinik Seç--");
		for(int i =0 ; i< clinic.getList().size(); i++) {
			
			select_klinik.addItem(new Item(clinic.getList().get(i).getId(),clinic.getList().get(i).getName()));
		}
		select_klinik.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(select_klinik.getSelectedIndex() != 0) {
					JComboBox c = (JComboBox) e.getSource();
					Item item = (Item) c.getSelectedItem();
					DefaultTableModel clearModel = (DefaultTableModel) tableDoktor.getModel();
					clearModel.setRowCount(0);
					try {
						for(int i=0; i< clinic.getClinicDoctorList(item.getKey()).size(); i++) {
							doctorData[0] =clinic.getClinicDoctorList(item.getKey()).get(i).getId();
							doctorData[1] =clinic.getClinicDoctorList(item.getKey()).get(i).getName();
							doctorModel.addRow(doctorData);
							
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					DefaultTableModel clearModel = (DefaultTableModel) tableDoktor.getModel();
					clearModel.setRowCount(0);
				}
			}
		});
		w_appointment.add(select_klinik);
		
		JLabel lblPoliklinikAd_1 = new JLabel("Doktor Seç:");
		lblPoliklinikAd_1.setForeground(Color.BLACK);
		lblPoliklinikAd_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblPoliklinikAd_1.setBounds(270, 100, 99, 14);
		w_appointment.add(lblPoliklinikAd_1);
		
		JButton btnDoktorSec = new JButton("Seç");
		btnDoktorSec.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDoktorSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row =tableDoktor.getSelectedRow();
				if(row>=0) {
					String value = tableDoktor.getModel().getValueAt(row, 0).toString();
					int id = Integer.parseInt(value);
					DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
					clearModel.setRowCount(0);
					try {
						for(int i=0; i< whour.getWhourList(id).size();i++) {
							whourData[0]= whour.getWhourList(id).get(i).getId();
							whourData[1]= whour.getWhourList(id).get(i).getW_date();
							whourModel.addRow(whourData);
							
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					table_whour.setModel(whourModel);
					selectDoctorID=id;
					selectDoctorName=tableDoktor.getModel().getValueAt(row, 1).toString();
					
				}
				else {
					Helper.showMsg("Lütfen bir doktor seçiniz!");
					
				}
			}
		});
		btnDoktorSec.setBounds(270, 119, 169, 33);
		w_appointment.add(btnDoktorSec);
		
		JScrollPane scrollWhour = new JScrollPane();
		scrollWhour.setBounds(449, 35, 250, 309);
		w_appointment.add(scrollWhour);
		
		table_whour = new JTable(whourModel);
		scrollWhour.setViewportView(table_whour);
		table_whour.getColumnModel().getColumn(0).setPreferredWidth(5);
		
		
		JLabel lblNewLabel_2 = new JLabel("Randevu Listesi");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblNewLabel_2.setBounds(449, 11, 99, 14);
		w_appointment.add(lblNewLabel_2);
		
		JButton btnRandevuAl = new JButton("Randevu Al");
		btnRandevuAl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow=table_whour.getSelectedRow();
				if(selRow>=0) {
					String date=table_whour.getModel().getValueAt(selRow, 1).toString();
					try {
						boolean control=hasta.addAppointmen(selectDoctorID, hasta.getId(), selectDoctorName, hasta.getName(), date);
						if(control) {
							Helper.showMsg("success");
							hasta.updateWhourStatus(selectDoctorID, date);
							updateWhour(selectDoctorID);
							updateAppointModel(hasta.getId());
						}
						else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else {
					Helper.showMsg("Lütfen geçerli bir tarih giriniz!");
				}
			}
		});
		btnRandevuAl.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRandevuAl.setBounds(270, 203, 169, 33);
		w_appointment.add(btnRandevuAl);
		
		JPanel w_appoint = new JPanel();
		w_tab.addTab("Randevularým", null, w_appoint, null);
		w_appoint.setLayout(null);
		
		JScrollPane w_scrollApp = new JScrollPane();
		w_scrollApp.setBounds(10, 11, 689, 333);
		w_appoint.add(w_scrollApp);
		
		table_app = new JTable(appointModel);
		w_scrollApp.setViewportView(table_app);
	}
	
	public void updateWhour(int doctor_id) throws SQLException {
		DefaultTableModel clearModel=(DefaultTableModel) table_whour.getModel();
		clearModel.setRowCount(0);
		for(int i=0; i< whour.getWhourList(doctor_id).size();i++) {
			whourData[0]= whour.getWhourList(doctor_id).get(i).getId();
			whourData[1]= whour.getWhourList(doctor_id).get(i).getW_date();
			whourModel.addRow(whourData);
			
		}
	}
	public void updateAppointModel(int hasta_id) throws SQLException {
		DefaultTableModel clearModel=(DefaultTableModel) table_app.getModel();
		clearModel.setRowCount(0);
		for(int i=0;i<appoint.getHastaList(hasta_id).size();i++) {
			appointData[0]=appoint.getHastaList(hasta_id).get(i).getId();
			appointData[1]=appoint.getHastaList(hasta_id).get(i).getDoctorName();
			appointData[2]=appoint.getHastaList(hasta_id).get(i).getAppDate();
			appointModel.addRow(appointData);
			
		}
	}
}
