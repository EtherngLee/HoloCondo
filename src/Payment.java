
import java.awt.Window;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author E-Therng
 */
public class Payment extends javax.swing.JPanel {
    int user = 0;
DefaultTableModel model;
    /**
     * Creates new form Punishments
     */
    public Payment() {
        initComponents();
        SerialCombo.setEnabled(false);
        SerialCombo.setVisible(false);
               boolean x = true;
        try {
            FileReader fr = new FileReader("CurrentUser.txt");
            BufferedReader br = new BufferedReader(fr);
            String r = "";
            while ((r = br.readLine()) != null) {
                StringTokenizer tk = new StringTokenizer(r, ",");
                String pat = tk.nextToken();
                user = Integer.parseInt(pat);
                }
        } catch (Exception e) {
            x=false;
        }
        
        if(x==true)
        {
            try {
            FileReader fr = new FileReader("Patron.txt");
            BufferedReader br = new BufferedReader(fr);
            String r = "";
            while ((r = br.readLine()) != null) {
                StringTokenizer tk = new StringTokenizer(r, ",");
                String block = tk.nextToken();
                String floor = tk.nextToken();
                String n = tk.nextToken();
                String hpn = tk.nextToken();
                String email = tk.nextToken();
                String mem = tk.nextToken();
                String del = tk.nextToken();
                int ser = Integer.parseInt(tk.nextToken());
                if (user==ser) {
                    PatronCombo.removeAllItems();
                    PatronCombo.addItem(n);
                }
            }
            br.close();
        } catch (Exception e) {

        }
        }
        
        model = (DefaultTableModel) Table.getModel();
            PayPunishment.setEnabled(false);
            Confirm.setEnabled(false);
            model.setRowCount(0);
                ArrayList<String>Boookse = new ArrayList<String>();
                ArrayList<String>Punish = new ArrayList<String>();
                ArrayList<String>Price = new ArrayList<String>();
                ArrayList<String>BookSerial = new ArrayList<String>();
                ArrayList<String>BookName = new ArrayList<String>();
                ArrayList<String>PatronSerial = new ArrayList<String>();
                ArrayList<String>PatronName = new ArrayList<String>();
                ArrayList<String>DueDate = new ArrayList<String>();
                ArrayList<String>Apar = new ArrayList<String>();
                
            try
            {
                FileReader br = new FileReader("Penalty.txt");
                BufferedReader bur = new BufferedReader(br);
                String re ="";
                while((re=bur.readLine())!=null)
                {
                    StringTokenizer tk = new StringTokenizer(re,",");
                    String name = tk.nextToken();
                    int patron = Integer.parseInt(tk.nextToken());
                    int bookse = Integer.parseInt(tk.nextToken());
                    String date = tk.nextToken();
                    
                        Punish.add(name);
                        PatronSerial.add(patron+"");
                        BookSerial.add(bookse+"");
                        DueDate.add(date);
                }
                
                }

            catch(Exception e)
            {

            }
            try
            {
                for(int i=0; i<BookSerial.size(); i++)
                    {
                        String n = Punish.get(i);
                        String s = BookSerial.get(i);
                FileReader brr = new FileReader("Book.txt");
                BufferedReader burr = new BufferedReader(brr);
                String re ="";
                while((re=burr.readLine())!=null)
                {
                    StringTokenizer tk = new StringTokenizer(re,",");
                    String name = tk.nextToken();
                    String auth = tk.nextToken();
                    String gen = tk.nextToken();
                    String pr = tk.nextToken();
                    String av = tk.nextToken();
                    String del = tk.nextToken();
                    int bookse = Integer.parseInt(tk.nextToken());
                    String se = tk.nextToken();
                    
                    
                    
                        if(s.equals(se))
                        {
                            BookName.add(name);
                            Boookse.add(bookse+"");
                            if(n.equals("Overdue"))
                    {
                        Price.add("0");
                    }
                    if(n.equals("Damage"))
                    {
                        Price.add(pr);
                    }
                        }
                    }
                    
                    
                }
            }
            catch(Exception e)
            {

            }
            
            try
            {
                for(int i=0; i<PatronSerial.size(); i++)
                    {
                FileReader prr = new FileReader("Patron.txt");
                BufferedReader purr = new BufferedReader(prr);
                String re ="";
                
                        String s = PatronSerial.get(i);
                while((re=purr.readLine())!=null)
                {
                    StringTokenizer tk = new StringTokenizer(re,",");
                    String block = tk.nextToken();
                    String floor = tk.nextToken();
                    String n = tk.nextToken();
                    String hpn = tk.nextToken();
                    String email = tk.nextToken();
                    String mem = tk.nextToken();
                    String del = tk.nextToken();
                    String serial = tk.nextToken();
                    
                        if(s.equals(serial))
                        {
                            PatronName.add(n);
                            
                            Apar.add(block + "," + floor);
                        }
                    }
                }
                }

            catch(Exception e)
            {

            }
            try
            {
                for(int i=0; i<BookName.size(); i++)
                {
                    String punish = Punish.get(i);
                    int price = Integer.parseInt(Price.get(i));
                    String book = BookName.get(i);
                    String patron = PatronName.get(i);
                    String bookser = BookSerial.get(i);
                    String patronser = PatronSerial.get(i);
                    String apar = Apar.get(i);
                    String due = DueDate.get(i);
                    
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        Date Today = sdf.parse(getTodayDate());
                        Date PunDate = sdf.parse(due);
                        
                        long diffInMillies = Math.abs(Today.getTime() - PunDate.getTime());
                        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                        if(punish.equals("Damage"))
                        {
                            model.insertRow(model.getRowCount(), new Object[]{patron,apar,book,punish,price,patronser,bookser});
                        }
                        if(punish.equals("Overdue"))
                        {
                            model.insertRow(model.getRowCount(), new Object[]{patron,apar,book,punish,diff,patronser,bookser});
                        }
                }
            }
            catch(Exception e)
            {

            }
    }
    private String getTodayDate()
    {
        
        //defines the format
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        
        //gets the system datee
        Date todayD = new Date();
        
        //formats today's date as per our format defined in the first line
        String m = df.format(todayD);
        
        return m;
        //will return string m which contains the data.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Violation = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        PatronCombo = new javax.swing.JComboBox<>();
        Select = new javax.swing.JButton();
        Select1 = new javax.swing.JButton();
        PatSearch = new javax.swing.JLabel();
        SearchBar = new javax.swing.JTextField();
        Search = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        SerialCombo = new javax.swing.JComboBox<>();
        Search1 = new javax.swing.JButton();
        ShowAll = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        BackToAdminHomePage = new javax.swing.JButton();
        SelectPayment = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        PayPunishment = new javax.swing.JButton();
        Confirm = new javax.swing.JRadioButton();

        setBackground(new java.awt.Color(202, 214, 226));

        jPanel3.setBackground(new java.awt.Color(162, 249, 211));

        PatronCombo.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        PatronCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Patron Name" }));
        PatronCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PatronComboActionPerformed(evt);
            }
        });

        Select.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        Select.setText("Sign In");
        Select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectActionPerformed(evt);
            }
        });

        Select1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        Select1.setText("Sign Out");
        Select1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Select1ActionPerformed(evt);
            }
        });

        PatSearch.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        PatSearch.setText("Patron Search");

        SearchBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchBarActionPerformed(evt);
            }
        });

        Search.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        Search.setText("Search");
        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(Select, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)
                        .addComponent(Select1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(PatSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(PatronCombo, 0, 175, Short.MAX_VALUE)
                            .addComponent(SearchBar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PatSearch)
                    .addComponent(SearchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PatronCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Select, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Select1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        jLabel1.setText("Payment");

        SerialCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Serial" }));
        SerialCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SerialComboActionPerformed(evt);
            }
        });

        Search1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        Search1.setText("Patron Specific");
        Search1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Search1ActionPerformed(evt);
            }
        });

        ShowAll.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        ShowAll.setText("Show All");
        ShowAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowAllActionPerformed(evt);
            }
        });

        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Patron Name", "Apartment", "Payment Name", "Reason for Payment", "Fees", "Patron Serial", "BookSerial"
            }
        ));
        Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Table);
        if (Table.getColumnModel().getColumnCount() > 0) {
            Table.getColumnModel().getColumn(5).setMinWidth(0);
            Table.getColumnModel().getColumn(5).setPreferredWidth(0);
            Table.getColumnModel().getColumn(5).setMaxWidth(0);
            Table.getColumnModel().getColumn(6).setMinWidth(0);
            Table.getColumnModel().getColumn(6).setPreferredWidth(0);
            Table.getColumnModel().getColumn(6).setMaxWidth(0);
        }

        BackToAdminHomePage.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        BackToAdminHomePage.setText("Back to Admin Home Page");
        BackToAdminHomePage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackToAdminHomePageActionPerformed(evt);
            }
        });

        SelectPayment.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        SelectPayment.setText("Select Payment");
        SelectPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectPaymentActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(243, 196, 192));

        PayPunishment.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        PayPunishment.setText("Pay");
        PayPunishment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PayPunishmentActionPerformed(evt);
            }
        });

        Confirm.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        Confirm.setText("Confirm Payment");
        Confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PayPunishment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Confirm, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(PayPunishment, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Confirm)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(BackToAdminHomePage, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(jLabel1)
                        .addGap(33, 33, 33)
                        .addComponent(SerialCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 858, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Search1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ShowAll, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(266, 266, 266)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SelectPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(SerialCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(BackToAdminHomePage, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Search1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ShowAll, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SelectPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void PatronComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PatronComboActionPerformed
        if(PatronCombo.getSelectedIndex()>0)
        {
            SerialCombo.setSelectedIndex(PatronCombo.getSelectedIndex());
        }
    }//GEN-LAST:event_PatronComboActionPerformed

    private void SelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectActionPerformed
        if(SerialCombo.getSelectedIndex()==0)
        {
            javax.swing.JOptionPane.showMessageDialog(null, "Please Select Patron!", "Message: ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        else
        {

            try{
                user = Integer.parseInt(SerialCombo.getSelectedItem().toString());
                FileWriter PatInfo = new FileWriter("CurrentUser.txt", false);
                PrintWriter PatWriter = new PrintWriter(PatInfo);
                String x = getTodayDate();
                PatWriter.println(SerialCombo.getSelectedItem().toString());
                PatWriter.close();
            }
            catch(Exception e)
            {

            }
        }
    }//GEN-LAST:event_SelectActionPerformed

    private void Select1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Select1ActionPerformed
        try{

            FileWriter PatInfo = new FileWriter("CurrentUser.txt", false);
            PrintWriter PatWriter = new PrintWriter(PatInfo);
            String x = getTodayDate();
            PatWriter.println("");
            PatWriter.close();
            PatronCombo.removeAllItems();
            PatronCombo.addItem("Select Patron");
            SearchBar.setText("");
        }
        catch(Exception e)
        {

        }
    }//GEN-LAST:event_Select1ActionPerformed

    private void SearchBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchBarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchBarActionPerformed

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        if(SearchBar.getText().equals(""))
        {
            javax.swing.JOptionPane.showMessageDialog(null, "Enter Patron Name!", "Message: ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        else{
            try{
                PatronCombo.removeAllItems();
                PatronCombo.addItem("Patron Name");
                SerialCombo.removeAllItems();
                SerialCombo.addItem("Serial");
                String na = SearchBar.getText();
                FileReader fr = new FileReader("Patron.txt");
                BufferedReader br = new BufferedReader(fr);
                String r ="";

                while((r=br.readLine())!=null)
                {
                    StringTokenizer tk = new StringTokenizer(r,",");
                    String block = tk.nextToken();
                    String floor = tk.nextToken();
                    String n = tk.nextToken();
                    String hpn = tk.nextToken();
                    String email = tk.nextToken();
                    String mem = tk.nextToken();
                    String del = tk.nextToken();
                    String ser = tk.nextToken();
                    if(n.contains(na))
                    {
                        PatronCombo.addItem(n + ": " + block + "," + floor);
                        SerialCombo.addItem(ser);
                    }
                }
                br.close();
            }
            catch(Exception e)
            {
            }
        }
    }//GEN-LAST:event_SearchActionPerformed

    private void SerialComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SerialComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SerialComboActionPerformed

    private void Search1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Search1ActionPerformed
        PayPunishment.setEnabled(false);
        Confirm.setEnabled(false);
        model.setRowCount(0);

        ArrayList<String>Punish = new ArrayList<String>();
        ArrayList<String>MemberName = new ArrayList<String>();
        ArrayList<String>MemberSerial = new ArrayList<String>();
        ArrayList<String>Bookse = new ArrayList<String>();
        ArrayList<String>Price = new ArrayList<String>();
        ArrayList<String>BookSerial = new ArrayList<String>();
        ArrayList<String>BookName = new ArrayList<String>();
        ArrayList<String>PatronSerial = new ArrayList<String>();
        ArrayList<String>PatronName = new ArrayList<String>();
        ArrayList<String>DueDate = new ArrayList<String>();
        ArrayList<String>Apar = new ArrayList<String>();

        try
        {
            FileReader br = new FileReader("Payment.txt");
            BufferedReader bur = new BufferedReader(br);
            String re ="";
            while((re=bur.readLine())!=null)
            {
                StringTokenizer tk = new StringTokenizer(re,",");
                String name = tk.nextToken();
                int patron = Integer.parseInt(tk.nextToken());
                int bookse = Integer.parseInt(tk.nextToken());
                String date = tk.nextToken();

                if(user==patron)
                {
                    Punish.add(name);
                    PatronSerial.add(patron+"");
                    BookSerial.add(bookse+"");
                    DueDate.add(date);
                }
            }

        }

        catch(Exception e)
        {

        }
        try
        {
            for(int i=0; i<BookSerial.size(); i++)
            {
                String n = Punish.get(i);
                String s = BookSerial.get(i);
                if((n.equals("Overdue") || n.equals("Damage")))
                {
                    FileReader brr = new FileReader("Book.txt");
                    BufferedReader burr = new BufferedReader(brr);
                    String re ="";
                    while((re=burr.readLine())!=null)
                    {
                        StringTokenizer tk = new StringTokenizer(re,",");
                        String name = tk.nextToken();
                        String auth = tk.nextToken();
                        String gen = tk.nextToken();
                        String pr = tk.nextToken();
                        String av = tk.nextToken();
                        String del = tk.nextToken();
                        int bookse = Integer.parseInt(tk.nextToken());
                        String se = tk.nextToken();

                        if(s.equals(se))
                        {
                            BookName.add(name);
                            Bookse.add(bookse+"");
                            if(n.equals("Overdue"))
                            {
                                Price.add("0");
                            }
                            if(n.equals("Damage"))
                            {
                                Price.add(pr);
                            }
                        }
                    }
                }
                else if(n.equals("Member"))
                {

                    FileReader brr = new FileReader("Membership.txt");
                    BufferedReader burr = new BufferedReader(brr);
                    String re ="";
                    while((re=burr.readLine())!=null)
                    {
                        StringTokenizer tk = new StringTokenizer(re,",");
                        String title = tk.nextToken();
                        String price = tk.nextToken();
                        String borrowQ = tk.nextToken();
                        String holdQ = tk.nextToken();
                        String duration = tk.nextToken();
                        String memberQ  = tk.nextToken();
                        String del = tk.nextToken();
                        String ser = tk.nextToken();

                        if(s.equals(ser))
                        {
                            BookName.add(title);
                            BookSerial.add(ser);
                            Price.add(price);
                        }

                    }
                }
            }
        }
        catch(Exception e)
        {

        }
        try
        {
            for(int i=0; i<PatronSerial.size(); i++)
            {
                FileReader prr = new FileReader("Patron.txt");
                BufferedReader purr = new BufferedReader(prr);
                String re ="";

                String s = PatronSerial.get(i);
                while((re=purr.readLine())!=null)
                {
                    StringTokenizer tk = new StringTokenizer(re,",");
                    String block = tk.nextToken();
                    String floor = tk.nextToken();
                    String n = tk.nextToken();
                    String hpn = tk.nextToken();
                    String email = tk.nextToken();
                    String mem = tk.nextToken();
                    String del = tk.nextToken();
                    String serial = tk.nextToken();

                    if(s.equals(serial))
                    {
                        PatronName.add(n);

                        Apar.add(block + "," + floor);
                    }
                }
            }
        }

        catch(Exception e)
        {

        }
        System.out.println(PatronName.size());
        try
        {
            boolean err=false;
            for(int i=0; i<PatronName.size(); i++)
            {
                String punish = Punish.get(i);
                int price = Integer.parseInt(Price.get(i));
                String book = BookName.get(i);
                String patron = PatronName.get(i);
                String bookser = BookSerial.get(i);
                String patronser = PatronSerial.get(i);
                String apar = Apar.get(i);
                String due = DueDate.get(i);

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date Today = sdf.parse(getTodayDate());
                Date PunDate = sdf.parse(due);

                long diffInMillies = Math.abs(Today.getTime() - PunDate.getTime());
                long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                if(punish.equals("Damage"))
                {
                    model.insertRow(model.getRowCount(), new Object[]{patron,apar,book,punish,price,patronser,bookser});
                }
                if(punish.equals("Overdue"))
                {
                    model.insertRow(model.getRowCount(), new Object[]{patron,apar,book,punish,diff,patronser,bookser});
                }
                if(punish.equals("Member"))
                {
                    model.insertRow(model.getRowCount(), new Object[]{patron,apar,book,punish,price,patronser,bookser});
                }
                err=true;
            }

            if(err==false)
            {
                javax.swing.JOptionPane.showMessageDialog(null, "No Items Found!", "Message: ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(Exception e)
        {

        }
    }//GEN-LAST:event_Search1ActionPerformed

    private void ShowAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowAllActionPerformed
        PayPunishment.setEnabled(false);
        Confirm.setEnabled(false);
        model.setRowCount(0);

        ArrayList<String>Punish = new ArrayList<String>();
        ArrayList<String>MemberName = new ArrayList<String>();
        ArrayList<String>MemberSerial = new ArrayList<String>();
        ArrayList<String>Bookse = new ArrayList<String>();
        ArrayList<String>Price = new ArrayList<String>();
        ArrayList<String>BookSerial = new ArrayList<String>();
        ArrayList<String>BookName = new ArrayList<String>();
        ArrayList<String>PatronSerial = new ArrayList<String>();
        ArrayList<String>PatronName = new ArrayList<String>();
        ArrayList<String>DueDate = new ArrayList<String>();
        ArrayList<String>Apar = new ArrayList<String>();

        try
        {
            FileReader br = new FileReader("Payment.txt");
            BufferedReader bur = new BufferedReader(br);
            String re ="";
            while((re=bur.readLine())!=null)
            {
                StringTokenizer tk = new StringTokenizer(re,",");
                String name = tk.nextToken();
                int patron = Integer.parseInt(tk.nextToken());
                int bookse = Integer.parseInt(tk.nextToken());
                String date = tk.nextToken();

                Punish.add(name);
                PatronSerial.add(patron+"");
                BookSerial.add(bookse+"");
                DueDate.add(date);
            }

        }

        catch(Exception e)
        {

        }
        try
        {
            for(int i=0; i<BookSerial.size(); i++)
            {
                String n = Punish.get(i);
                String s = BookSerial.get(i);
                if((n.equals("Overdue") || n.equals("Damage")))
                {
                    FileReader brr = new FileReader("Book.txt");
                    BufferedReader burr = new BufferedReader(brr);
                    String re ="";
                    while((re=burr.readLine())!=null)
                    {
                        StringTokenizer tk = new StringTokenizer(re,",");
                        String name = tk.nextToken();
                        String auth = tk.nextToken();
                        String gen = tk.nextToken();
                        String pr = tk.nextToken();
                        String av = tk.nextToken();
                        String del = tk.nextToken();
                        int bookse = Integer.parseInt(tk.nextToken());
                        String se = tk.nextToken();

                        if(s.equals(se))
                        {
                            BookName.add(name);
                            Bookse.add(bookse+"");
                            if(n.equals("Overdue"))
                            {
                                Price.add("0");
                            }
                            if(n.equals("Damage"))
                            {
                                Price.add(pr);
                            }
                        }
                    }
                }
                else if(n.equals("Member"))
                {

                    FileReader brr = new FileReader("Membership.txt");
                    BufferedReader burr = new BufferedReader(brr);
                    String re ="";
                    while((re=burr.readLine())!=null)
                    {
                        StringTokenizer tk = new StringTokenizer(re,",");
                        String title = tk.nextToken();
                        String price = tk.nextToken();
                        String borrowQ = tk.nextToken();
                        String holdQ = tk.nextToken();
                        String duration = tk.nextToken();
                        String memberQ  = tk.nextToken();
                        String del = tk.nextToken();
                        String ser = tk.nextToken();

                        if(s.equals(ser))
                        {
                            BookName.add(title);
                            BookSerial.add(ser);
                            Price.add(price);
                        }

                    }
                }
            }
        }
        catch(Exception e)
        {

        }
        try
        {
            for(int i=0; i<PatronSerial.size(); i++)
            {
                FileReader prr = new FileReader("Patron.txt");
                BufferedReader purr = new BufferedReader(prr);
                String re ="";

                String s = PatronSerial.get(i);
                while((re=purr.readLine())!=null)
                {
                    StringTokenizer tk = new StringTokenizer(re,",");
                    String block = tk.nextToken();
                    String floor = tk.nextToken();
                    String n = tk.nextToken();
                    String hpn = tk.nextToken();
                    String email = tk.nextToken();
                    String mem = tk.nextToken();
                    String del = tk.nextToken();
                    String serial = tk.nextToken();

                    if(s.equals(serial))
                    {
                        PatronName.add(n);

                        Apar.add(block + "," + floor);
                    }
                }
            }
        }

        catch(Exception e)
        {

        }
        try
        {
            boolean err=false;
            for(int i=0; i<PatronName.size(); i++)
            {
                String punish = Punish.get(i);
                int price = Integer.parseInt(Price.get(i));
                String book = BookName.get(i);
                String patron = PatronName.get(i);
                String bookser = BookSerial.get(i);
                String patronser = PatronSerial.get(i);
                String apar = Apar.get(i);
                String due = DueDate.get(i);

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date Today = sdf.parse(getTodayDate());
                Date PunDate = sdf.parse(due);

                long diffInMillies = Math.abs(Today.getTime() - PunDate.getTime());
                long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                if(punish.equals("Damage"))
                {
                    model.insertRow(model.getRowCount(), new Object[]{patron,apar,book,punish,price,patronser,bookser});
                }
                if(punish.equals("Overdue"))
                {
                    model.insertRow(model.getRowCount(), new Object[]{patron,apar,book,punish,diff,patronser,bookser});
                }
                if(punish.equals("Member"))
                {
                    model.insertRow(model.getRowCount(), new Object[]{patron,apar,book,punish,price,patronser,bookser});
                }
                err=true;
            }

            if(err==false)
            {
                javax.swing.JOptionPane.showMessageDialog(null, "No Items Found!", "Message: ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(Exception e)
        {

        }
    }//GEN-LAST:event_ShowAllActionPerformed

    private void TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableMouseClicked

    }//GEN-LAST:event_TableMouseClicked

    private void BackToAdminHomePageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackToAdminHomePageActionPerformed
        AdminHomePage f1 = new AdminHomePage();

        f1.show();

        ((Window)getRootPane().getParent()).dispose();
    }//GEN-LAST:event_BackToAdminHomePageActionPerformed

    private void SelectPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectPaymentActionPerformed
        if(Table.getSelectionModel().isSelectionEmpty())
        {
            javax.swing.JOptionPane.showMessageDialog(null, "Select Book!", "Message: ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            PayPunishment.setEnabled(true);
            Confirm.setEnabled(true);
            int row = Table.getSelectedRow();

            pun = Table.getModel().getValueAt(row, 3).toString();

            pat = Integer.parseInt(Table.getModel().getValueAt(row, 5)+"");

            book = Integer.parseInt(Table.getModel().getValueAt(row, 6)+"");

            String patn = Table.getModel().getValueAt(row, 0).toString();

            String bookn = Table.getModel().getValueAt(row, 2).toString();

            String price = Table.getModel().getValueAt(row, 4).toString();

            javax.swing.JOptionPane.showMessageDialog(null, "You Selected Reason of Payment: " + pun + "\n" + "Patron: " + patn + "\n" + "Item: " + bookn + "\n" + "Price: " + price, "Message: Selected!", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_SelectPaymentActionPerformed

    private void PayPunishmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PayPunishmentActionPerformed
        boolean err=false;
        if(Table.getSelectionModel().isSelectionEmpty())
        {
            javax.swing.JOptionPane.showMessageDialog(null, "Select Item!", "Message: ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
            err=true;
        }
        if(Confirm.isSelected()==false && err==false)
        {
            javax.swing.JOptionPane.showMessageDialog(null, "Click Confirm Payment!", "Message: ERROR", javax.swing.JOptionPane.ERROR_MESSAGE);
            err=true;
        }
        if(err==false)
        {
            try
            {
                int row = 0;
                row = Table.getSelectedRow();
                String rfp = Table.getModel().getValueAt(row, 3)+"";
                ArrayList<String>restoreList = new ArrayList<String>();
                FileReader crr = new FileReader("Payment.txt");
                BufferedReader bbr = new BufferedReader(crr);
                String re ="";
                while((re=bbr.readLine())!=null)
                {
                    StringTokenizer tk = new StringTokenizer(re,",");
                    String name = tk.nextToken();
                    int patron = Integer.parseInt(tk.nextToken());
                    int bookse = Integer.parseInt(tk.nextToken());
                    String date = tk.nextToken();
                    if(patron==pat && book==bookse && name.equals(pun))
                    {
                    }
                    else
                    {
                        restoreList.add(re);
                    }
                }
                FileWriter fw = new FileWriter("Payment.txt",false);
                PrintWriter pr = new PrintWriter(fw);

                for(int i=0; i<restoreList.size(); i++)
                {
                    String s = restoreList.get(i);
                    pr.println(s);
                }
                pr.close();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String curdate = getTodayDate();
                FileWriter BookInfo = new FileWriter("Transactions.txt", true);
                PrintWriter BookWriter = new PrintWriter(BookInfo);
                if(rfp.equals("Overdue"))
                {
                    BookWriter.println("Fee Paid Overdue" + "," + pat + "," + book  + "," + curdate);
                    BookWriter.close();
                }
                else if(rfp.equals("Damage"))
                {
                    BookWriter.println("Fee Paid Damage" + "," + pat + "," + book  + "," + curdate);
                    BookWriter.close();
                }
                else if(rfp.equals("Member"))
                {
                    BookWriter.println("Fee Paid Member" + "," + pat + "," + book  + "," + curdate);
                    BookWriter.close();
                }
            }
            catch(Exception e)
            {

            }

            //restore patron
            ArrayList<String>Punish = new ArrayList<String>();
            ArrayList<String>Price = new ArrayList<String>();
            ArrayList<String>BookSerial = new ArrayList<String>();
            ArrayList<String>BookName = new ArrayList<String>();
            ArrayList<String>PatronSerial = new ArrayList<String>();
            ArrayList<String>PatronName = new ArrayList<String>();
            ArrayList<String>DueDate = new ArrayList<String>();
            LinkedHashSet<String> Hun = new LinkedHashSet<String>();

            try
            {
                FileReader br = new FileReader("Payment.txt");
                BufferedReader bur = new BufferedReader(br);
                String re ="";
                while((re=bur.readLine())!=null)
                {
                    StringTokenizer tk = new StringTokenizer(re,",");
                    String name = tk.nextToken();
                    int patron = Integer.parseInt(tk.nextToken());
                    int bookse = Integer.parseInt(tk.nextToken());
                    String date = tk.nextToken();

                    Punish.add(name);
                    PatronSerial.add(patron+"");
                    BookSerial.add(bookse+"");
                    DueDate.add(date);
                    System.out.println(name);
                }

            }

            catch(Exception e)
            {

            }
            try
            {
                for(int i=0; i<BookSerial.size(); i++)
                {
                    String n = Punish.get(i);
                    String s = BookSerial.get(i);
                    FileReader brr = new FileReader("Book.txt");
                    BufferedReader burr = new BufferedReader(brr);
                    String re ="";
                    while((re=burr.readLine())!=null)
                    {
                        StringTokenizer tk = new StringTokenizer(re,",");
                        String name = tk.nextToken();
                        String auth = tk.nextToken();
                        String gen = tk.nextToken();
                        String pr = tk.nextToken();
                        String av = tk.nextToken();
                        String del = tk.nextToken();
                        int bookse = Integer.parseInt(tk.nextToken());
                        String se = tk.nextToken();

                        if(s.equals(se))
                        {
                            BookName.add(name);
                            if(n.equals("Overdue"))
                            {
                                Price.add("0");
                            }
                            if(n.equals("Damage"))
                            {
                                Price.add(pr);
                            }
                        }
                    }

                }
            }
            catch(Exception e)
            {

            }

            try
            {
                for(int i=0; i<PatronSerial.size(); i++)
                {
                    FileReader prr = new FileReader("Patron.txt");
                    BufferedReader purr = new BufferedReader(prr);
                    String re ="";

                    String s = PatronSerial.get(i);
                    while((re=purr.readLine())!=null)
                    {
                        StringTokenizer tk = new StringTokenizer(re,",");
                        String block = tk.nextToken();
                        String floor = tk.nextToken();
                        String n = tk.nextToken();
                        String hpn = tk.nextToken();
                        String email = tk.nextToken();
                        String mem = tk.nextToken();
                        String del = tk.nextToken();
                        String serial = tk.nextToken();

                        if(s.equals(serial))
                        {
                            PatronName.add(n);
                        }
                    }
                }
            }

            catch(Exception e)
            {

            }
            try
            {

                for(int i=0; i<BookName.size(); i++)
                {
                    String punish = Punish.get(i);
                    int price = Integer.parseInt(Price.get(i));
                    String book = BookName.get(i);
                    String patron = PatronName.get(i);
                    String bookser = BookSerial.get(i);
                    String patronser = PatronSerial.get(i);
                    String due = DueDate.get(i);

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date Today = sdf.parse(getTodayDate());
                    Date PunDate = sdf.parse(due);

                    long diff = 0;
                    if(punish.equals("Overdue"))
                    {
                        long diffInMillies = Math.abs(Today.getTime() - PunDate.getTime());
                        diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                    }
                    long tot = diff + price;
                    System.out.println(tot);
                    if(punish.equals("Damage"))
                    {
                        if(tot<100)
                        {
                            Hun.add(patronser);
                        }
                    }
                    if(punish.equals("Overdue"))
                    {
                        if(tot<100)
                        {
                            Hun.add(patronser);
                        }
                    }
                }

            }
            catch(Exception e)
            {

            }

            ArrayList<String>hund = new ArrayList<>(Hun);

            try {
                ArrayList<String>restoreList = new ArrayList<String>();

                FileReader fr = new FileReader("Patron.txt");
                BufferedReader br = new BufferedReader(fr);
                String r = "";
                while ((r = br.readLine()) != null) {
                    StringTokenizer tk = new StringTokenizer(r, ",");
                    String block = tk.nextToken();
                    String floor = tk.nextToken();
                    String n = tk.nextToken();
                    String hpn = tk.nextToken();
                    String email = tk.nextToken();
                    String mem = tk.nextToken();
                    String del = tk.nextToken();
                    String ser = tk.nextToken();

                    boolean yes = true;
                    for(int i = 0; i<hund.size(); i++)
                    {
                        String hun = hund.get(i);
                        if (hun.equals(ser)) {
                            System.out.println(n);
                            restoreList.add(block + "," + floor + "," + n + "," + hpn + "," + email + "," + mem + ",Y," + ser);
                            yes = false;
                        }
                    }
                    if(yes ==true)
                    {
                        restoreList.add(r);
                    }
                }

                FileWriter fw = new FileWriter("Patron.txt",false);
                PrintWriter pr = new PrintWriter(fw);

                for(int i=0; i<restoreList.size(); i++)
                {
                    String s = restoreList.get(i);
                    pr.println(s);
                }
                pr.close();

                javax.swing.JOptionPane.showMessageDialog(null, "Fee Paid!", "Message: Saved!", javax.swing.JOptionPane.INFORMATION_MESSAGE);

                ShowAll.doClick();
            }
            catch(Exception e)
            {

            }
        }
    }//GEN-LAST:event_PayPunishmentActionPerformed

    private void ConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ConfirmActionPerformed
String pun = "";
int pat = 0;
int book = 0;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackToAdminHomePage;
    private javax.swing.JRadioButton Confirm;
    private javax.swing.JLabel PatSearch;
    private javax.swing.JComboBox<String> PatronCombo;
    private javax.swing.JButton PayPunishment;
    private javax.swing.JButton Search;
    private javax.swing.JButton Search1;
    private javax.swing.JTextField SearchBar;
    private javax.swing.JButton Select;
    private javax.swing.JButton Select1;
    private javax.swing.JButton SelectPayment;
    private javax.swing.JComboBox<String> SerialCombo;
    private javax.swing.JButton ShowAll;
    private javax.swing.JTable Table;
    private javax.swing.ButtonGroup Violation;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
