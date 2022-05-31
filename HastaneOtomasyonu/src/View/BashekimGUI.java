package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Model.Bashekim;
import Model.Klinik;

import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;

import Helper.*;
import javax.swing.JComboBox;

public class BashekimGUI extends JFrame {
	static Bashekim bashekim=new Bashekim();
	Klinik clinic= new Klinik();

	private JPanel w_paneBashekim;
	private JTextField textField;
	private JLabel BashekimAdSoyad;
	private JLabel BashekimTc;
	private JLabel BashekimSifre;
	private JLabel BashekimID;
	private JTextField txtDoktorAd;
	private JTextField txtDoktorTC;
	private JTextField txtDoktorSifre;
	private JTextField txtDoktorID;
	private JTable table_doktor;
	private DefaultTableModel doctorModel=null;
	private Object[] doctorData=null;
	private JTable tableKlinik;
	private JTextField fld_klinikname;
	private DefaultTableModel clinicModel = null;
	private Object[] clinicData = null;
	private JPopupMenu clinicMenu;
	private JTable tableWorker;
		
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BashekimGUI frame = new BashekimGUI(bashekim);
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
	public BashekimGUI(Bashekim bashekim) throws SQLException {
		//Doctor Model
		doctorModel=new DefaultTableModel();
		Object[] colDoctorName=new Object[4];
		colDoctorName[0]="ID";
		colDoctorName[1]="Ad Soyad";
		colDoctorName[2]="TC No";
		colDoctorName[3]="Þifre";
		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData=new Object[4];
		for(int i=0;i<bashekim.getDoctorList().size();i++) {
			doctorData[0]=bashekim.getDoctorList().get(i).getId();
			doctorData[1]=bashekim.getDoctorList().get(i).getName();
			doctorData[2]=bashekim.getDoctorList().get(i).getTcno();
			doctorData[3]=bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}
		
		// Clinic Model
		clinicModel=new DefaultTableModel();
		Object[] colClinic=new Object[2];
		colClinic[0]="ID";
		colClinic[1]="Poliklinik Adý";
		clinicModel.setColumnIdentifiers(colClinic);
		clinicData=new Object[2];
		
		for(int i=0;i<clinic.getList().size();i++) {
			clinicData[0]=clinic.getList().get(i).getId();
			clinicData[1]=clinic.getList().get(i).getName();
			clinicModel.addRow(clinicData);
		}
		
		// Worker Model
		DefaultTableModel workerModel=new DefaultTableModel();
		Object[] colWorker=new Object[2];
		colClinic[0]="ID";
		colClinic[1]="Ad Soyad";
		workerModel.setColumnIdentifiers(colWorker);
		Object[] workerData =new Object[2];
		
		
		
		
		setTitle("Hastane Yönetim Sistemi");
		 setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_paneBashekim = new JPanel();
		w_paneBashekim.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_paneBashekim);
		w_paneBashekim.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hoþgeldiniz, Sayýn "+ bashekim.getName());
		lblNewLabel.setBounds(10, 11, 391, 34);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));
		w_paneBashekim.add(lblNewLabel);
		
		JButton btnBashekimCikis = new JButton("Çýkýþ Yap");
		btnBashekimCikis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login=new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btnBashekimCikis.setBounds(635, 11, 89, 23);
		btnBashekimCikis.setFont(new Font("Tahoma", Font.PLAIN, 13));
		w_paneBashekim.add(btnBashekimCikis);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 56, 714, 394);
		w_paneBashekim.add(tabbedPane);
		JPanel w_hastaLogin = new JPanel();
		tabbedPane.addTab("Doktor Yönetimi", null, w_hastaLogin, null);
		
		JLabel lblTcNumaras;
		w_hastaLogin.setLayout(null);
		BashekimTc = new JLabel("T.C. Numarasý:");
		BashekimTc.setBounds(549, 69, 123, 17);
		BashekimTc.setForeground(Color.BLACK);
		BashekimTc.setFont(new Font("Times New Roman", Font.BOLD, 18));
		w_hastaLogin.add(BashekimTc);
		
		JLabel lblifre;
		BashekimSifre = new JLabel("Þifre:");
		BashekimSifre.setBounds(549, 132, 50, 17);
		BashekimSifre.setForeground(Color.BLACK);
		BashekimSifre.setFont(new Font("Times New Roman", Font.BOLD, 19));
		w_hastaLogin.add(BashekimSifre);
		
		
		JLabel lblTcNumaras_1;
		BashekimAdSoyad = new JLabel("Ad Soyad:");
		BashekimAdSoyad.setBounds(549, 11, 90, 17);
		BashekimAdSoyad.setForeground(Color.BLACK);
		BashekimAdSoyad.setFont(new Font("Times New Roman", Font.BOLD, 18));
		w_hastaLogin.add(BashekimAdSoyad);
		
		
		BashekimID = new JLabel("Kullanýcý ID:");
		BashekimID.setBounds(549, 273, 104, 17);
		BashekimID.setForeground(Color.BLACK);
		BashekimID.setFont(new Font("Times New Roman", Font.BOLD, 19));
		w_hastaLogin.add(BashekimID);
		
		
		JButton BashekimSil = new JButton("Sil");
		BashekimSil.setFont(new Font("Tahoma", Font.PLAIN, 13));
		BashekimSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtDoktorID.getText().length()==0) {
					Helper.showMsg("Lütfen Geçerli Bir ID Giriniz!");
				}
				else {
					if(Helper.confirm("sure")){
						int selectID=Integer.parseInt(txtDoktorID.getText());
						try {
							boolean control=bashekim.deleteDoktor(selectID);
							if(control) {
								Helper.showMsg("success");
								txtDoktorID.setText(null);
								updateDoctorModel();
								
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		BashekimSil.setBounds(549, 332, 123, 23);
		w_hastaLogin.add(BashekimSil);
		
		JButton BashekimEkle = new JButton("Ekle");
		BashekimEkle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		BashekimEkle.setBounds(549, 200, 123, 23);
		BashekimEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtDoktorAd.getText().length()==0||txtDoktorSifre.getText().length()==0||txtDoktorTC.getText().length()==0) {
					Helper.showMsg("fill");
					
				}
				else {
					try {
						
						boolean control=bashekim.addDoktor(txtDoktorTC.getText(),txtDoktorSifre.getText(),txtDoktorAd.getText() );
						
						if(control) {
							Helper.showMsg("success");
							txtDoktorAd.setText(null);
							txtDoktorTC.setText(null);
							txtDoktorSifre.setText(null);
							updateDoctorModel();
						}
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		w_hastaLogin.add(BashekimEkle);
		
		txtDoktorAd = new JTextField();
		txtDoktorAd.setBounds(549, 38, 123, 20);
		w_hastaLogin.add(txtDoktorAd);
		txtDoktorAd.setColumns(10);
		
		txtDoktorTC = new JTextField();
		txtDoktorTC.setBounds(549, 97, 123, 20);
		w_hastaLogin.add(txtDoktorTC);
		txtDoktorTC.setColumns(10);
		
		txtDoktorSifre = new JTextField();
		txtDoktorSifre.setBounds(549, 160, 123, 20);
		w_hastaLogin.add(txtDoktorSifre);
		txtDoktorSifre.setColumns(10);
		
		txtDoktorID = new JTextField();
		txtDoktorID.setBounds(549, 301, 123, 20);
		w_hastaLogin.add(txtDoktorID);
		txtDoktorID.setColumns(10);
		
		JScrollPane w_scrollDoktor = new JScrollPane();
		w_scrollDoktor.setBounds(10, 11, 529, 344);
		w_hastaLogin.add(w_scrollDoktor);
		
		table_doktor = new JTable(doctorModel);
		w_scrollDoktor.setViewportView(table_doktor);
		
		table_doktor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					txtDoktorID.setText(table_doktor.getValueAt(table_doktor.getSelectedRow(), 0).toString());
				} catch (Exception ex) {
					
				}
				
				
			}
		});
		
		
		
		table_doktor.getModel().addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				if(e.getType()==TableModelEvent.UPDATE) {
					int selectID=Integer.parseInt(table_doktor.getValueAt(table_doktor.getSelectedRow(), 0).toString());
					String selectName=table_doktor.getValueAt(table_doktor.getSelectedRow(), 1).toString();
					String selecttcno=table_doktor.getValueAt(table_doktor.getSelectedRow(), 2).toString();
					String selectPass=table_doktor.getValueAt(table_doktor.getSelectedRow(), 3).toString();
					
					
					try {
						
						 boolean  control=bashekim.updateDoktor(selectID, selecttcno, selectPass, selectName);
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		
		JPanel w_klinik = new JPanel();
		tabbedPane.addTab("Poliklinikler", null, w_klinik, null);
		w_klinik.setLayout(null);
		
		JScrollPane scrollKlinik = new JScrollPane();
		scrollKlinik.setBounds(10, 11, 277, 344);
		w_klinik.add(scrollKlinik);
		
		
		
		clinicMenu=new JPopupMenu();
		JMenuItem updateMenu=new JMenuItem("Güncelle");
		JMenuItem deleteMenu=new JMenuItem("Sil");
		clinicMenu.add(updateMenu);
		clinicMenu.add(deleteMenu);
		 
		updateMenu.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int selID=Integer.parseInt(tableKlinik.getValueAt(tableKlinik.getSelectedRow(), 0).toString());
				Klinik seleKlinik=clinic.getFetch(selID);
				updateClinic updateClinicGUI=new updateClinic(seleKlinik);
				updateClinicGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				updateClinicGUI.setVisible(true);
				updateClinicGUI.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						try {
							updateClinicModel();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						}
				});
				
				}
			});
		deleteMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Helper.confirm("sure")) {
					int selID=Integer.parseInt(tableKlinik.getValueAt(tableKlinik.getSelectedRow(), 0).toString());
					try {
						if(clinic.deleteKlinik(selID)) {
							Helper.showMsg("success");
							updateClinicModel();
						}
						else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				// TODO Auto-generated method stub
				
			}
		} );
		
		tableKlinik = new JTable(clinicModel);
		tableKlinik.setComponentPopupMenu(clinicMenu);
		tableKlinik.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("unused")
			public void moussePressed(final MouseEvent e) {
				Point point=e.getPoint();
				int selectedRow=tableKlinik.rowAtPoint(point);
				tableKlinik.setRowSelectionInterval(selectedRow, selectedRow);
			}
	
		
		});
	
		
		
		scrollKlinik.setViewportView(tableKlinik);
		
		JLabel lblPoliklinikad = new JLabel("Poliklinik Adý:");
		lblPoliklinikad.setBounds(297, 42, 125, 17);
		lblPoliklinikad.setForeground(Color.BLACK);
		lblPoliklinikad.setFont(new Font("Times New Roman", Font.BOLD, 18));
		w_klinik.add(lblPoliklinikad);
		
		fld_klinikname = new JTextField();
		fld_klinikname.setBounds(297, 70, 125, 20);
		fld_klinikname.setColumns(10);
		w_klinik.add(fld_klinikname);
		
		JButton btn_klinikEkle = new JButton("Ekle");
		btn_klinikEkle.setBounds(297, 101, 125, 23);
		btn_klinikEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_klinikname.getText().length()==0) {
					Helper.showMsg("fill");
				}
				else {
					try {
						if(clinic.addKlinik(fld_klinikname.getText())) {
							Helper.showMsg("success");
							fld_klinikname.setText(null);
							updateClinicModel();
							
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
				
			}
		});
		w_klinik.add(btn_klinikEkle);
		
		JScrollPane w_scrollWorker = new JScrollPane();
		w_scrollWorker.setBounds(432, 11, 267, 344);
		w_klinik.add(w_scrollWorker);
		
		tableWorker = new JTable();
		w_scrollWorker.setViewportView(tableWorker);
		
		JComboBox SelectDoktor = new JComboBox();
		SelectDoktor.setBounds(297, 271, 125, 23);
		for (int i=0; i< bashekim.getDoctorList().size(); i++) {
			SelectDoktor.addItem(new Item(bashekim.getDoctorList().get(i).getId(), bashekim.getDoctorList().get(i).getName()));
		}
		
		SelectDoktor.addActionListener(e -> {
			JComboBox c= (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			System.out.println(item.getKey() + ":" + item.getValue());
		});
		
		w_klinik.add(SelectDoktor);
		
		JButton btn_workerEkle = new JButton("Ekle");
		btn_workerEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = tableKlinik.getSelectedRow();
				if(selRow >=0 ) {
					String selClinic = tableKlinik.getModel().getValueAt(selRow, 0).toString();
					int selClinicID= Integer.parseInt(selClinic);
					Item doctorItem = (Item) SelectDoktor.getSelectedItem();
					try {
						boolean control = bashekim.addWorker(doctorItem.getKey(), selClinicID);
						if(control) {
							Helper.showMsg("success");
							DefaultTableModel clearModel =(DefaultTableModel) tableWorker.getModel();
							clearModel.setRowCount(0);
							for(int i=0; i<bashekim.getClinicDoctorList(selClinicID).size(); i++) {
								workerData[0]= bashekim.getClinicDoctorList(selClinicID).get(i).getId();
								workerData[1]= bashekim.getClinicDoctorList(selClinicID).get(i).getName();
								workerModel.addRow(workerData);
						}
					tableWorker.setModel(workerModel);

						}else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else {
					Helper.showMsg("Lütfen bir poliklinik seçiniz!");
				}
			}
		});
		btn_workerEkle.setBounds(297, 305, 125, 23);
		w_klinik.add(btn_workerEkle);
		
		JLabel lblPoliklinikad_1 = new JLabel("Poliklinik Adý:");
		lblPoliklinikad_1.setForeground(Color.BLACK);
		lblPoliklinikad_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblPoliklinikad_1.setBounds(299, 173, 123, 17);
		w_klinik.add(lblPoliklinikad_1);
		
		JButton btn_workerSelect = new JButton("Se\u00E7");
		btn_workerSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow= tableKlinik.getSelectedRow();
				if(selRow >=0) {
					String selClinic = tableKlinik.getModel().getValueAt(selRow, 0).toString();
					int selClinicID= Integer.parseInt(selClinic);
					DefaultTableModel clearModel =(DefaultTableModel) tableWorker.getModel();
					clearModel.setRowCount(0);
					try {
						for(int i=0; i<bashekim.getClinicDoctorList(selClinicID).size(); i++) {
							workerData[0]= bashekim.getClinicDoctorList(selClinicID).get(i).getId();
							workerData[1]= bashekim.getClinicDoctorList(selClinicID).get(i).getName();
							workerModel.addRow(workerData);
							
							
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					tableWorker.setModel(workerModel);
				}
				else {
					Helper.showMsg("Lütfen bir poliklinik seçiniz!");
				}
			}
		});
		btn_workerSelect.setBounds(297, 201, 123, 23);
		w_klinik.add(btn_workerSelect);
		
		
	
	}
	public void updateDoctorModel() throws SQLException  {
		DefaultTableModel clearModel=(DefaultTableModel) table_doktor.getModel();
		clearModel.setRowCount(0);
		for(int i=0;i<bashekim.getDoctorList().size();i++) {
			doctorData[0]=bashekim.getDoctorList().get(i).getId();
			doctorData[1]=bashekim.getDoctorList().get(i).getName();
			doctorData[2]=bashekim.getDoctorList().get(i).getTcno();
			doctorData[3]=bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}
	}
	
	public void updateClinicModel() throws SQLException {
		DefaultTableModel clearModel= (DefaultTableModel) tableKlinik.getModel();
		clearModel.setRowCount(0);
		for(int i=0;i<clinic.getList().size();i++) {
			clinicData[0]=clinic.getList().get(i).getId();
			clinicData[1]=clinic.getList().get(i).getName();
			clinicModel.addRow(clinicData);
		}
	}
}

