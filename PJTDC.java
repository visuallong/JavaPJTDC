ReadWriteFile
//Đọc Danh Sách Nhân Viên
public static ArrayList<NhanVien> layNhanVienTuFile(String file){
ArrayList<NhanVien> listNV = new ArrayList<>();
try {
	BufferedReader br = new BufferedReader(new FileReader(file));
	String line;
	try {
		while((line = br.readLine()) != null){
			String [] temp = line.split(":");
			NhanVien nv = new NhanVien();
			nv.setUserName(temp[0]);
			nv.setHoTen(temp[1]);
			nv.setDiaChi(temp[2]);
			nv.setSoDT(temp[3]);
			String [] ngaySinh = temp[4].split("/");
			nv.setNgaySinh(new Date(Integer.parseInt(ngaySinh[0]), Integer.parseInt(ngaySinh[1]), Integer.parseInt(ngaySinh[2])));
			listNV.add(nv);
		}
		br.close();
	} catch (IOException ex) {
		Logger.getLogger(ReadWriteFile.class.getName()).log(Level.SEVERE, null, ex);
	}
} catch (FileNotFoundException ex) {
	Logger.getLogger(ReadWriteFile.class.getName()).log(Level.SEVERE, null, ex);
}return listNV; }

//Ghi Danh Sách Nhân Viên
public static void ghiNhanVienVaoFile(String file, ArrayList<NhanVien> listNV){
try {
	PrintWriter pw = new PrintWriter(new FileWriter(file));
	int i = 1;
	for(NhanVien nv : listNV){
		if(i == listNV.size()){
		pw.print(nv.getUserName() + ":" + nv.getHoTen() + ":" + nv.getDiaChi() + ":" + nv.getSoDT() + ":" + nv.getNgaySinh().toString());
		}else pw.print(nv.getUserName() + ":" + nv.getHoTen() + ":" + nv.getDiaChi() + ":" + nv.getSoDT() + ":" + nv.getNgaySinh().toString() + "\n");
		i++;
	}
	pw.close();
} catch (IOException ex) {
	Logger.getLogger(ReadWriteFile.class.getName()).log(Level.SEVERE, null, ex);
}
}

//Doc Danh Sach Hoa Don
public static ArrayList<HoaDon> layHoaDonTuFile(String file){
ArrayList<HoaDon> listHoaDon = new ArrayList<>();
try {
	BufferedReader br = new BufferedReader(new FileReader(file));
	String line;
	try {
		while((line = br.readLine()) != null){
			String [] temp = line.split(":");
			HoaDon hoaDon = new HoaDon();                    
			hoaDon.setMaHoaDon(temp[0]);     
			Thuoc thuoc = new Thuoc();  
			hoaDon.setMaHoaDon(temp[0]);
			hoaDon.setNgay(Integer.parseInt(temp[1]));
			hoaDon.setThang(Integer.parseInt(temp[2]));
			hoaDon.setNam(Integer.parseInt(temp[3]));
			thuoc.setMaThuoc(temp[4]);
			thuoc.setTenThuoc(temp[5]);
			thuoc.setDonViTinh(temp[6]);
			thuoc.setDonGia(Double.parseDouble(temp[7]));
			hoaDon.setThuoc(thuoc);
			listHoaDon.add(hoaDon);
		}
		br.close();
	} catch (IOException ex) {
		Logger.getLogger(ReadWriteFile.class.getName()).log(Level.SEVERE, null, ex);
	}
} catch (FileNotFoundException ex) {
	Logger.getLogger(ReadWriteFile.class.getName()).log(Level.SEVERE, null, ex);
}
return listHoaDon;
}
//Ghi Danh Sach Hoa Don
public static void ghiHoaDonVaoFile(String file, ArrayList<HoaDon> listHoaDon){
try {
	PrintWriter pw = new PrintWriter(new FileWriter(file));
	int i = 1;
	for(HoaDon hoaDon : listHoaDon){
		if(i == listHoaDon.size()){
		pw.print(hoaDon.getMaHoaDon() + ":" + 
				hoaDon.getNgay()+ ":" + hoaDon.getThang()+ ":" + hoaDon.getNam()+ ":"+
				hoaDon.getThuoc().getMaThuoc() + ":" + hoaDon.getThuoc().getTenThuoc() + ":" 
				+ hoaDon.getThuoc().getDonViTinh() + ":" + hoaDon.getThuoc().getDonGia());
		}else pw.print(hoaDon.getMaHoaDon() + ":" + 
				hoaDon.getNgay()+ ":" + hoaDon.getThang()+ ":" + hoaDon.getNam()+ ":"+
				hoaDon.getThuoc().getMaThuoc() + ":" + hoaDon.getThuoc().getTenThuoc() + ":" + hoaDon.getThuoc().getDonViTinh() + ":" + hoaDon.getThuoc().getDonGia() + "\n");
		i++;
	}
	pw.close();
} catch (IOException ex) {
	Logger.getLogger(ReadWriteFile.class.getName()).log(Level.SEVERE, null, ex);
}}
private ArrayList<Thuoc> arrThuoc;
private ArrayList<HoaDon> arrHoaDon;
private int selectedRowThuoc = 0;
private int selectedRowHoaDon = 0;
MainFarm(){
this.arrHoaDon = ReadWriteFile.layHoaDonTuFile("hoadon.txt");
this.arrThuoc = ReadWriteFile.layThuocTuFile("thuoc.txt");
hienThiListThuoc();
hienThiListHoaDon();
this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
this.addWindowListener(new WindowAdapter() {
	public void windowClosing(WindowEvent e) {
		int result = JOptionPane.showConfirmDialog(null, "Bạn muốn thoát chương trình?", "Xác nhận thoát" , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
if(result == 0){
	e.getWindow().dispose();}}}
private void hienThiListThuoc(){
DefaultTableModel model;
model = (DefaultTableModel) jTableThuoc.getModel();
model.setRowCount(0);
for(Thuoc t : arrThuoc){
	String [] data = new String[4];
	data [0] = t.getMaThuoc();
	data [1] = t.getTenThuoc();
	data [2] = t.getDonViTinh();
	data [3] = t.getDonGia() + "";
	model.addRow(data);}
jTableThuoc.setModel(model);}

private void hienThiListHoaDon() {
DefaultTableModel model;
model = (DefaultTableModel) jTableHoaDon.getModel();
model.setRowCount(0);
for(HoaDon hd : arrHoaDon) {
	String [] data = new String[8];
	data [0] = hd.getMaHoaDon();
	data [1] = hd.getNgay() + "";
	data [2] = hd.getThang() + "";
	data [3] = hd.getNam() + "";
	data [4] = hd.getThuoc().getMaThuoc();
	data [5] = hd.getThuoc().getTenThuoc();
	data [6] = hd.getThuoc().getDonViTinh();
	data [7] = hd.getThuoc().getDonGia() + "";
	 model.addRow(data);}
 jTableHoaDon.setModel(model);}
private void jTableThuocMouseClicked(java.awt.event.MouseEvent evt) {                                         
int row = jTableThuoc.getSelectedRow();
setSelectedRowThuoc(row);
txtMaThuoc.setText(jTableThuoc.getValueAt(row, 0) + "");
txtTenThuoc.setText(jTableThuoc.getValueAt(row, 1) + "");
txtDonViTinh.setText(jTableThuoc.getValueAt(row, 2) + "");
txtDonGia.setText(jTableThuoc.getValueAt(row, 3) + "");
btnThemThuoc.setEnabled(false);
txtMaThuoc.setEnabled(false);
btnSuaThuoc.setEnabled(true);
btnXoaThuoc.setEnabled(true);}
private void btnThemThuocActionPerformed(java.awt.event.ActionEvent evt) {                                             
if(!"".equals(txtMaThuoc.getText()) && !"".equals(txtTenThuoc.getText())){
	DefaultTableModel model = (DefaultTableModel) jTableThuoc.getModel();
	String [] data = new String [4];
	data [0] = txtMaThuoc.getText();
	data [1] = txtTenThuoc.getText();
	data [2] = txtDonViTinh.getText();
	data [3] = txtDonGia.getText();
	arrThuoc.add(new Thuoc (txtMaThuoc.getText(),txtTenThuoc.getText(),txtDonViTinh.getText(),Double.parseDouble(txtDonGia.getText())));
	model.addRow(data);
	jTableThuoc.setModel(model);
	hienThiListThuoc();	 clearAllForThuoc();
	ReadWriteFile.ghiThuocVaoFile("thuoc.txt", arrThuoc);
	JOptionPane.showMessageDialog(null,"Thêm thuốc thành công!!!");
}else JOptionPane.showMessageDialog(null,"Mã thuốc hoặc tên thuốc đang trống!!! Không thể thêm thuốc!!!"); 

private void btnXoaThuocActionPerformed(java.awt.event.ActionEvent evt) {                                            
int result = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa thuốc được chọn?", "Xác nhận Xóa" , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
if(result == 0){
	arrThuoc.remove(selectedRowThuoc);
ReadWriteFile.ghiThuocVaoFile("thuoc.txt", arrThuoc);
hienThiListThuoc();        clearAllForThuoc();
JOptionPane.showMessageDialog(null,"Xóa thuốc thành công!!!");}}

private void btnSuaThuocActionPerformed(java.awt.event.ActionEvent evt) {                                            
int result = JOptionPane.showConfirmDialog(null, "Bạn muốn sửa thuốc được chọn?", "Xác nhận Sửa" , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
if(result == 0){
	arrThuoc.get(selectedRowThuoc).setMaThuoc(txtMaThuoc.getText());
arrThuoc.get(selectedRowThuoc).setTenThuoc(txtTenThuoc.getText());
arrThuoc.get(selectedRowThuoc).setDonViTinh(txtDonViTinh.getText());
arrThuoc.get(selectedRowThuoc).setDonGia(Double.parseDouble(txtDonGia.getText()));
ReadWriteFile.ghiThuocVaoFile("thuoc.txt", arrThuoc);
hienThiListThuoc();
JOptionPane.showMessageDialog(null,"Sửa thuốc thành công!!!");}}

private void btnTimThuocActionPerformed(java.awt.event.ActionEvent evt) {                                            
boolean flag = false;
if(cboTimThuoc.getSelectedItem().equals("Mã Thuốc")){
	for(Thuoc t : arrThuoc){
		if(txtGiaTriTimThuoc.getText().equals(t.getMaThuoc())){
			btnThemThuoc.setEnabled(false);
			txtMaThuoc.setEnabled(false);
			btnSuaThuoc.setEnabled(true);
			btnXoaThuoc.setEnabled(true);
			txtMaThuoc.setText(t.getMaThuoc());
			txtTenThuoc.setText(t.getTenThuoc());
			txtDonViTinh.setText(t.getDonViTinh());
			txtDonGia.setText(t.getDonGia() + "");
			flag = true;
			break;}}
}else if (cboTimThuoc.getSelectedItem().equals("Tên Thuốc")){
	for(Thuoc t : arrThuoc){
		if(txtGiaTriTimThuoc.getText().equals(t.getTenThuoc())){
			btnThemThuoc.setEnabled(false);
			txtMaThuoc.setEnabled(false);
			btnSuaThuoc.setEnabled(true);
			btnXoaThuoc.setEnabled(true);
			txtMaThuoc.setText(t.getMaThuoc());
			txtTenThuoc.setText(t.getTenThuoc());
			txtDonViTinh.setText(t.getDonViTinh());
			txtDonGia.setText(t.getDonGia() + "");
			flag = true;
			break;}}}
if(!flag){
	JOptionPane.showMessageDialog(null,"Không tìm thấy thuốc!!!");}}

private void btnSapXepThuocActionPerformed(java.awt.event.ActionEvent evt) {                                               
if(cboSapXepThuoc.getSelectedItem().equals("Mã Thuốc")){
	Collections.sort(arrThuoc, new Comparator<Thuoc>(){
		public int compare(Thuoc t1, Thuoc t2) {
			return t1.getMaThuoc().compareTo(t2.getMaThuoc());
}});
}else if(cboSapXepThuoc.getSelectedItem().equals("Tên Thuốc")){
	Collections.sort(arrThuoc, new Comparator<Thuoc>(){
		public int compare(Thuoc t1, Thuoc t2) {
			return t1.getTenThuoc().compareTo(t2.getTenThuoc());
		}});
}else if(cboSapXepThuoc.getSelectedItem().equals("Đơn Vị Tính")){
	Collections.sort(arrThuoc, new Comparator<Thuoc>(){
	   public int compare(Thuoc t1, Thuoc t2) {
			return t1.getDonViTinh().compareTo(t2.getDonViTinh());
		}});
}else if(cboSapXepThuoc.getSelectedItem().equals("Đơn Giá")){
	Collections.sort(arrThuoc, new Comparator<Thuoc>(){
		public int compare(Thuoc t1, Thuoc t2) {
			return Double.compare(t1.getDonGia(), t2.getDonGia());
		}});}
hienThiListThuoc();}

private void txtMaThuocFocusLost(java.awt.event.FocusEvent evt) {                                     
for(Thuoc t : arrThuoc){
	if(txtMaThuoc.getText().equals(t.getMaThuoc())){
		JOptionPane.showMessageDialog(null,"Mã thuốc đã được sử dụng!!!");
		txtMaThuoc.setText("");
		txtMaThuoc.requestFocus();
		break;}}}

private void clearAllForThuoc(){
btnThemThuoc.setEnabled(true);
txtMaThuoc.setEnabled(true);
btnSuaThuoc.setEnabled(false);
btnXoaThuoc.setEnabled(false);
txtMaThuoc.setText("");
txtTenThuoc.setText("");
txtDonViTinh.setText("");
txtDonGia.setText("");}

private void btnThemHoaDonActionPerformed(java.awt.event.ActionEvent evt) {                                              
if(!"".equals(txtMaHoaDon.getText()) && !"".equals(txtNgay.getText()) && !"".equals(txtThang.getText()) && !"".equals(txtHoaDonMaThuoc.getText()) && !"".equals(txtHoaDonTenThuoc.getText())){
	DefaultTableModel model = (DefaultTableModel) jTableHoaDon.getModel();
	String [] data = new String [8];
	data [0] = txtMaHoaDon.getText();
	data [1] = txtNgay.getText();
	data [2] = txtThang.getText();
	data [3] = txtNam.getText();
	data [4] = txtHoaDonMaThuoc.getText();
	data [5] = txtHoaDonTenThuoc.getText();
	data [6] = txtHoaDonDonVi.getText();
	data [7] = txtHoaDonDonGia.getText();
	Thuoc thuoc = new Thuoc(txtHoaDonMaThuoc.getText(),txtHoaDonTenThuoc.getText(),
txtHoaDonDonVi.getText(),Double.parseDouble(txtHoaDonDonGia.getText()));
	arrHoaDon.add(new HoaDon (txtMaHoaDon.getText(), Integer.parseInt(txtNgay.getText()),Integer.parseInt(txtThang.getText()),Integer.parseInt(txtNam.getText()), thuoc));
	model.addRow(data);
	jTableHoaDon.setModel(model);
	hienThiListHoaDon();
	clearAllForHoaDon();
	ReadWriteFile.ghiHoaDonVaoFile("hoadon.txt", arrHoaDon);
	JOptionPane.showMessageDialog(null,"Thêm hóa đơn thành công!!!");
}else JOptionPane.showMessageDialog(null,"Bạn đang để trống ô!!! Không thể thêm hóa đơn!!!");}

private void btnXoaHoaDonActionPerformed(java.awt.event.ActionEvent evt) {                                             
int result = JOptionPane.showConfirmDialog(null, "Bạn muốn xóa hóa đơn được chọn?", "Xác nhận Xóa" , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
if(result == 0){
arrHoaDon.remove(getSelectedRowHoaDon());
ReadWriteFile.ghiHoaDonVaoFile("hoadon.txt", arrHoaDon);
hienThiListHoaDon();
clearAllForHoaDon();
JOptionPane.showMessageDialog(null,"Xóa hóa đơn thành công!!!");}}

private void btnSuaHoaDonActionPerformed(java.awt.event.ActionEvent evt) {                                             
int result = JOptionPane.showConfirmDialog(null, "Bạn muốn sửa hóa đơn được chọn?", "Xác nhận Sửa" , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
if(result == 0){
arrHoaDon.get(selectedRowHoaDon).setMaHoaDon(txtMaHoaDon.getText());
arrHoaDon.get(selectedRowHoaDon).setNgay(Integer.parseInt(txtNgay.getText()));
arrHoaDon.get(selectedRowHoaDon).setThang(Integer.parseInt(txtThang.getText()));
arrHoaDon.get(selectedRowHoaDon).setNam(Integer.parseInt(txtNam.getText()));
Thuoc thuoc = new Thuoc(txtHoaDonMaThuoc.getText(),txtHoaDonTenThuoc.getText(),txtHoaDonDonVi.getText(),Double.parseDouble(txtHoaDonDonGia.getText()));
arrHoaDon.get(selectedRowHoaDon).setThuoc(thuoc);
ReadWriteFile.ghiHoaDonVaoFile("hoadon.txt", arrHoaDon);
hienThiListHoaDon();
 JOptionPane.showMessageDialog(null,"Sửa hóa đơn thành công!!!");}}

private void jTableHoaDonMouseClicked(java.awt.event.MouseEvent evt) {                                          
int row = jTableHoaDon.getSelectedRow();
setSelectedRowHoaDon(row);
txtMaHoaDon.setText(jTableHoaDon.getValueAt(row, 0) + "");
txtNgay.setText(jTableHoaDon.getValueAt(row, 1) + "");
txtThang.setText(jTableHoaDon.getValueAt(row, 2) + "");
txtNam.setText(jTableHoaDon.getValueAt(row, 3) + "");
txtHoaDonMaThuoc.setText(jTableHoaDon.getValueAt(row, 4) + "");
txtHoaDonTenThuoc.setText(jTableHoaDon.getValueAt(row, 5) + "");
txtHoaDonDonVi.setText(jTableHoaDon.getValueAt(row, 6) + "");
txtHoaDonDonGia.setText(jTableHoaDon.getValueAt(row, 7) + "");
txtMaHoaDon.setEnabled(false);
txtHoaDonMaThuoc.setEnabled(false);
btnThemHoaDon.setEnabled(false);
btnXoaHoaDon.setEnabled(true);
btnSuaHoaDon.setEnabled(true);}
