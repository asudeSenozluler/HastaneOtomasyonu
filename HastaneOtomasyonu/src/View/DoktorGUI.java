package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.Doktor;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import com.toedter.calendar.JDateChooser;

import Helper.Helper;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class DoktorGUI extends JFrame {

	private JPanel w_PaneDoktor;
	private static Doktor doctor = new Doktor();
	private JTable table_whour;
	private DefaultTableModel whourModel;
	private Object[] whourData = null;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoktorGUI frame = new DoktorGUI(doctor);
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
	public DoktorGUI(Doktor doctor) throws SQLException {
		
		whourModel =new DefaultTableModel();
		Object[] colWhour = new Object[2];
		colWhour[0]="ID";
		colWhour[1]="Tarih";
		whourModel.setColumnIdentifiers(colWhour);
		whourData= new Object[2];
		
		for(int i=0; i< doctor.getWhourList(doctor.getId()).size(); i++) {
			whourData[0]= doctor.getWhourList(doctor.getId()).get(i).getId();
			whourData[1]= doctor.getWhourList(doctor.getId()).get(i).getW_date();
			whourModel.addRow(whourData);
		}
		
		setResizable(false);
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_PaneDoktor = new JPanel();
		w_PaneDoktor.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_PaneDoktor);
		w_PaneDoktor.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hoþgeldiniz, Sayýn " + doctor.getName());
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel.setBounds(10, 11, 457, 34);
		w_PaneDoktor.add(lblNewLabel);
		
		JButton btnBashekimCikis = new JButton("Çýkýþ Yap");
		btnBashekimCikis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login=new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btnBashekimCikis.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBashekimCikis.setBounds(635, 11, 89, 23);
		w_PaneDoktor.add(btnBashekimCikis);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(10, 56, 714, 394);
		w_PaneDoktor.add(w_tab);
		
		JPanel w_whour = new JPanel();
		w_whour.setBackground(UIManager.getColor("Button.background"));
		w_whour.setForeground(Color.BLACK);
		w_tab.addTab("Çalýþma Saatleri", null, w_whour, null);
		w_whour.setLayout(null);
		
		JDateChooser selectDate = new JDateChooser();
		selectDate.setBounds(10, 11, 243, 30);
		w_whour.add(selectDate);
		
		JComboBox selectTime = new JComboBox();
		selectTime.setBounds(263, 11, 85, 30);
		selectTime.setFont(new Font("Times New Roman", Font.BOLD, 14));
		selectTime.setModel(new DefaultComboBoxModel(new String[] {"10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:30", "14:00", "14:30", "15:00", "15:30"}));
		w_whour.add(selectTime);
		
		JButton btnSaatEkle = new JButton("Ekle");
		btnSaatEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				String date="";
				try {
					date=sdf.format(selectDate.getDate());
					
				} catch (Exception e2) {
					
				}
				if(date.length()==0) {
					Helper.showMsg("Lütfen geçerli bir tarih girin!");
				}
				else {
					String time=" "+selectTime.getSelectedItem().toString()+ ":00";
					String selectDate=date+time;
					try {
						boolean control=doctor.addWhour(doctor.getId(), doctor.getName(), selectDate);
						if(control) {
							Helper.showMsg("success");
							updateWhourModel(doctor);
						}
						else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				}
				
				
			}
		});
		btnSaatEkle.setBounds(472, 11, 111, 30);
		btnSaatEkle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		w_whour.add(btnSaatEkle);
		
		JScrollPane scrollwhour = new JScrollPane();
		scrollwhour.setBounds(20, 306, 679, -242);
		w_whour.add(scrollwhour);
		
		table_whour =new JTable(whourModel);
		table_whour.setBounds(10, 52, 687, 303);
		w_whour.add(table_whour);
		
		JButton btnWhourSil = new JButton("Sil");
		btnWhourSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_whour.getSelectedRow();
				if(selRow>=0) {
					String selectRow = table_whour.getModel().getValueAt(selRow,0).toString();
					int selID = Integer.parseInt(selectRow);
					try {
						boolean control= doctor.deleteWhour(selID);
						if(control) {
							Helper.showMsg("success");
							updateWhourModel(doctor);
						}
						else {
							Helper.showMsg("error");
							
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnWhourSil.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnWhourSil.setBounds(593, 11, 106, 30);
		w_whour.add(btnWhourSil);
	}
	public void updateWhourModel(Doktor doctor) throws SQLException  {
		DefaultTableModel clearModel=(DefaultTableModel) table_whour.getModel();
		clearModel.setRowCount(0);
		for(int i=0; i< doctor.getWhourList(doctor.getId()).size(); i++) {
			whourData[0]= doctor.getWhourList(doctor.getId()).get(i).getId();
			whourData[1]= doctor.getWhourList(doctor.getId()).get(i).getW_date();
			whourModel.addRow(whourData);
		}
		}
	}
