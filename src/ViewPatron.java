
import java.awt.Window;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class ViewPatron extends javax.swing.JPanel {
DefaultTableModel borrowmodel;
    DefaultTableModel holdmodel;
    DefaultTableModel punishmodel;
    int user = 0;
    /**
     * Creates new form ViewPatron
     */
    public ViewPatron() {
        initComponents();
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
                    PatronCombo.removeAllItems();;
                    PatronCombo.addItem(n);
                }
            }
            br.close();
        } catch (Exception e) {

        }
        }
        
        
        SerialCombo.setVisible(false);
        SerialCombo.setEnabled(false);
        Membership.setEditable(false);
        String pat = "";
        try {
            
                ArrayList<String>restoreList = new ArrayList<String>();
                
            FileReader cu = new FileReader("CurrentUser.txt");
            BufferedReader br = new BufferedReader(cu);
            String r = "";
            while ((r = br.readLine()) != null) {
                StringTokenizer tk = new StringTokenizer(r, ",");
                pat = tk.nextToken();
                }
        }
        catch(Exception e)
        {
        }
             borrowmodel = (DefaultTableModel) BorrowTable.getModel();
        borrowmodel.setRowCount(0);
        holdmodel = (DefaultTableModel) HoldTable.getModel();
        holdmodel.setRowCount(0);
        punishmodel = (DefaultTableModel) PunishTable.getModel();
        punishmodel.setRowCount(0);
            
        BorrowQuota.setEditable(false);
        HoldQuota.setEditable(false);
        
        String namee = "";
        String member = "";
        
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
                String ser = tk.nextToken();
                if (pat.equals(ser)) {
                    namee = n;
                    Title.setText(n);
                    member=mem;
                }
            }
            br.close();
        } catch (Exception e) {

        }
        
        try {
            // Borrow/Hold Quota
            FileReader ResReader = new FileReader("Membership.txt");
            BufferedReader ResBuffer = new BufferedReader(ResReader);
            String oneSetData = "";
            while ((oneSetData = ResBuffer.readLine()) != null) {
                StringTokenizer tk = new StringTokenizer(oneSetData, ",");
                String title = tk.nextToken();
                String price = tk.nextToken();
                String borrowQ = tk.nextToken();
                String holdQ = tk.nextToken();
                String duration = tk.nextToken();
                String memberQ  = tk.nextToken();
                String del  = tk.nextToken();
                String ser = tk.nextToken();
                if(member.equals(ser))
                {
                    BorrowQuota.setText(borrowQ);
                    HoldQuota.setText(holdQ);
                    Membership.setText(title);
                }
            }
        } catch (Exception e) {
        }
        
        try{
            
        
        ArrayList<String>Borrow = new ArrayList<String>();
        ArrayList<String>BorrowName = new ArrayList<String>();
        ArrayList<String>Due = new ArrayList<String>();
        try {
            //Borrow
            FileReader ResReader = new FileReader("Borrow.txt");
            BufferedReader ResBuffer = new BufferedReader(ResReader);
            String oneSetData = "";
            while ((oneSetData = ResBuffer.readLine()) != null) {
                StringTokenizer tk = new StringTokenizer(oneSetData, ",");
                String patron = tk.nextToken();
                    int bookse = Integer.parseInt(tk.nextToken());
                    String se = tk.nextToken();
                    int renew = Integer.parseInt(tk.nextToken());
                    String due = tk.nextToken();
                if(pat.equals(patron))
                {
                    Borrow.add(bookse+"");
                    Due.add(due);
                }
            }
            } catch (Exception e) {
        }
            for(int i=0; i<Borrow.size(); i++)
            {
                int b = Integer.parseInt(Borrow.get(i));
                try {

            FileReader ResReader = new FileReader("MainBook.txt");
            BufferedReader ResBuffer = new BufferedReader(ResReader);
            String oneSetData = "";
            while ((oneSetData = ResBuffer.readLine()) != null) {
                StringTokenizer tk = new StringTokenizer(oneSetData, ",");
                String name = tk.nextToken();
                String auth = tk.nextToken();
                String gen = tk.nextToken();
                String pr = tk.nextToken();
                int copies = Integer.parseInt(tk.nextToken());
                String av = tk.nextToken();
                String del = tk.nextToken();
                int bookse = Integer.parseInt(tk.nextToken());
                if(b==bookse)
                {
                    BorrowName.add(name);
                }
            }
            } catch (Exception e) {
        }
                
            }
            for(int i =0; i < Borrow.size(); i++)
            {
                String b = BorrowName.get(i);
                String d = Due.get(i);
                borrowmodel.insertRow(borrowmodel.getRowCount(), new Object[]{b,d});
            }
        }
        catch(Exception e)
        {
            
        }
            
        ArrayList<String>Hold = new ArrayList<String>();
        ArrayList<String>HoldName = new ArrayList<String>();
        ArrayList<String>HoldAv = new ArrayList<String>();
        try {
            //Hold
            FileReader ResReader = new FileReader("Hold.txt");
            BufferedReader ResBuffer = new BufferedReader(ResReader);
            String oneSetData = "";
            while ((oneSetData = ResBuffer.readLine()) != null) {
                StringTokenizer tk = new StringTokenizer(oneSetData, ",");
                String patron = tk.nextToken();
                    int bookse = Integer.parseInt(tk.nextToken());
                if(pat.equals(patron))
                {
                    Hold.add(bookse+"");;
                }
            }
            } catch (Exception e) {
        }
            for(int i=0; i<Hold.size(); i++)
            {
                int h = Integer.parseInt(Hold.get(i));
                try {

            FileReader ResReader = new FileReader("MainBook.txt");
            BufferedReader ResBuffer = new BufferedReader(ResReader);
            String oneSetData = "";
            while ((oneSetData = ResBuffer.readLine()) != null) {
                StringTokenizer tk = new StringTokenizer(oneSetData, ",");
                String name = tk.nextToken();
                String auth = tk.nextToken();
                String gen = tk.nextToken();
                String pr = tk.nextToken();
                int copies = Integer.parseInt(tk.nextToken());
                String av = tk.nextToken();
                String del = tk.nextToken();
                int bookse = Integer.parseInt(tk.nextToken());
                if(h==bookse)
                {
                    HoldName.add(name);
                    if(av.equals("Y"))
                    {
                        HoldAv.add("Yes");
                    }
                    else if(av.equals("N"))
                    {
                        HoldAv.add("No");
                    }
                }
            }
            } catch (Exception e) {
        }
                
            }
            for(int i =0; i < HoldName.size(); i++)
            {
                String ha = HoldAv.get(i);
                String h = HoldName.get(i);
                holdmodel.insertRow(holdmodel.getRowCount(), new Object[]{h,ha});
            }
        
            //Punishment
                 
                ArrayList<String>Punish = new ArrayList<String>();
                ArrayList<String>MemberName = new ArrayList<String>();
                ArrayList<String>MemberSerial = new ArrayList<String>();
                ArrayList<String>Bookse = new ArrayList<String>();
                ArrayList<String>Price = new ArrayList<String>();
                ArrayList<String>BookSerial = new ArrayList<String>();
                ArrayList<String>BookName = new ArrayList<String>();
                ArrayList<String>PatronSerial = new ArrayList<String>();
                ArrayList<String>DueDate = new ArrayList<String>();
                
            try
            {
                FileReader bcr = new FileReader("Payment.txt");
                BufferedReader bur = new BufferedReader(bcr);
                String re ="";
                while((re=bur.readLine())!=null)
                {
                    StringTokenizer tk = new StringTokenizer(re,",");
                    String name = tk.nextToken();
                    String patron = tk.nextToken();
                    int bookse = Integer.parseInt(tk.nextToken());
                    String date = tk.nextToken();
                    
                    if(pat.equals(patron))
                    {
                        Punish.add(name);
                        PatronSerial.add(patron);
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
                System.out.println(Price.get(0));
                boolean err=false;
                for(int i=0; i<Price.size(); i++)
                {
                    String punish = Punish.get(i);
                    int price = Integer.parseInt(Price.get(i));
                    String book = BookName.get(i);
                    String bookser = BookSerial.get(i);
                    String patronser = PatronSerial.get(i);
                    String due = DueDate.get(i);
                    
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        Date Today = sdf.parse(getTodayDate());
                        Date PunDate = sdf.parse(due);
                        
                        long diffInMillies = Math.abs(Today.getTime() - PunDate.getTime());
                        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                        if(punish.equals("Damage"))
                        {
                            punishmodel.insertRow(punishmodel.getRowCount(), new Object[]{punish,book,price});
                        }
                        if(punish.equals("Overdue"))
                        {
                            punishmodel.insertRow(punishmodel.getRowCount(), new Object[]{punish,book,diff});
                        }
                        if(punish.equals("Member"))
                        {
                            punishmodel.insertRow(punishmodel.getRowCount(), new Object[]{punish,book,price});
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

        jPanel3 = new javax.swing.JPanel();
        PatronCombo = new javax.swing.JComboBox<>();
        Select = new javax.swing.JButton();
        Select1 = new javax.swing.JButton();
        PatSearch = new javax.swing.JLabel();
        SearchBar = new javax.swing.JTextField();
        Search = new javax.swing.JButton();
        Title = new javax.swing.JLabel();
        Title1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        HoldQuota = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        BorrowQuota = new javax.swing.JTextField();
        Membership = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        BorrowTable = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        HoldTable = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        PunishTable = new javax.swing.JTable();
        SerialCombo = new javax.swing.JComboBox<>();
        BackToAdminoHomePage = new javax.swing.JButton();

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
                        .addContainerGap()
                        .addComponent(PatSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(SearchBar)
                            .addComponent(PatronCombo, 0, 175, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(Select, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(Select1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
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
                    .addComponent(Select1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Select, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        Title.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        Title.setText("[Patron Name]");

        Title1.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        Title1.setText("View Patron");

        jPanel1.setBackground(new java.awt.Color(243, 196, 192));

        HoldQuota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HoldQuotaActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel6.setText("Book Holding Quota");

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel3.setText("Book Borrowing Quota");

        BorrowQuota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BorrowQuotaActionPerformed(evt);
            }
        });

        Membership.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MembershipActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel8.setText("Membership");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(HoldQuota)
                        .addComponent(Membership, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(BorrowQuota, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(Membership, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(BorrowQuota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(HoldQuota))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(242, 227, 213));

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel4.setText("Books Borrowing");

        BorrowTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title", "Due Date"
            }
        ));
        jScrollPane1.setViewportView(BorrowTable);

        jLabel9.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel9.setText("Books Holding");

        HoldTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title", "Available?"
            }
        ));
        jScrollPane2.setViewportView(HoldTable);

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel5.setText("Payments");

        PunishTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Reason for Payment", "Item", "Fee"
            }
        ));
        jScrollPane3.setViewportView(PunishTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(200, 200, 200))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(168, 168, 168))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(185, 185, 185))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(111, 111, 111)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(28, 28, 28)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(264, Short.MAX_VALUE)))
        );

        SerialCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Serial" }));

        BackToAdminoHomePage.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        BackToAdminoHomePage.setText("Back to Home Page");
        BackToAdminoHomePage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackToAdminoHomePageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BackToAdminoHomePage)
                        .addGap(18, 18, 18)
                        .addComponent(Title1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(SerialCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(79, 79, 79))
            .addGroup(layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(Title)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(SerialCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(BackToAdminoHomePage, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Title1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Title)
                .addGap(9, 9, 9)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

            borrowmodel = (DefaultTableModel) BorrowTable.getModel();
            borrowmodel.setRowCount(0);
            holdmodel = (DefaultTableModel) HoldTable.getModel();
            holdmodel.setRowCount(0);
            punishmodel = (DefaultTableModel) PunishTable.getModel();
            punishmodel.setRowCount(0);

            BorrowQuota.setEditable(false);
            HoldQuota.setEditable(false);
            String serial = SerialCombo.getSelectedItem().toString();

            String namee = "";
            String member = "";

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
                    String ser = tk.nextToken();
                    if (serial.equals(ser)) {
                        namee = n;
                        Title.setText(n);
                        member=mem;
                    }
                }
                br.close();
            } catch (Exception e) {

            }

            try {
                // Borrow/Hold Quota
                FileReader ResReader = new FileReader("Membership.txt");
                BufferedReader ResBuffer = new BufferedReader(ResReader);
                String oneSetData = "";
                while ((oneSetData = ResBuffer.readLine()) != null) {
                    StringTokenizer tk = new StringTokenizer(oneSetData, ",");
                    String title = tk.nextToken();
                    String price = tk.nextToken();
                    String borrowQ = tk.nextToken();
                    String holdQ = tk.nextToken();
                    String duration = tk.nextToken();
                    String memberQ  = tk.nextToken();
                    String del  = tk.nextToken();
                    String ser = tk.nextToken();
                    if(member.equals(ser))
                    {
                        BorrowQuota.setText(borrowQ);
                        HoldQuota.setText(holdQ);
                        Membership.setText(title);
                    }
                }
            } catch (Exception e) {
            }

            ArrayList<String>Borrow = new ArrayList<String>();
            ArrayList<String>BorrowName = new ArrayList<String>();
            ArrayList<String>Due = new ArrayList<String>();
            try {
                //Borrow
                FileReader ResReader = new FileReader("Borrow.txt");
                BufferedReader ResBuffer = new BufferedReader(ResReader);
                String oneSetData = "";
                while ((oneSetData = ResBuffer.readLine()) != null) {
                    StringTokenizer tk = new StringTokenizer(oneSetData, ",");
                    String patron = tk.nextToken();
                    int bookse = Integer.parseInt(tk.nextToken());
                    String se = tk.nextToken();
                    int renew = Integer.parseInt(tk.nextToken());
                    String due = tk.nextToken();
                    if(serial.equals(patron))
                    {
                        Borrow.add(bookse+"");
                        Due.add(due);
                    }
                }
            } catch (Exception e) {
            }
            for(int i=0; i<Borrow.size(); i++)
            {
                int b = Integer.parseInt(Borrow.get(i));
                try {

                    FileReader ResReader = new FileReader("MainBook.txt");
                    BufferedReader ResBuffer = new BufferedReader(ResReader);
                    String oneSetData = "";
                    while ((oneSetData = ResBuffer.readLine()) != null) {
                        StringTokenizer tk = new StringTokenizer(oneSetData, ",");
                        String name = tk.nextToken();
                        String auth = tk.nextToken();
                        String gen = tk.nextToken();
                        String pr = tk.nextToken();
                        int copies = Integer.parseInt(tk.nextToken());
                        String av = tk.nextToken();
                        String del = tk.nextToken();
                        int bookse = Integer.parseInt(tk.nextToken());
                        if(b==bookse)
                        {
                            BorrowName.add(name);
                        }
                    }
                } catch (Exception e) {
                }

            }
            for(int i =0; i < Borrow.size(); i++)
            {
                String b = BorrowName.get(i);
                String d = Due.get(i);
                borrowmodel.insertRow(borrowmodel.getRowCount(), new Object[]{b,d});
            }

            ArrayList<String>Hold = new ArrayList<String>();
            ArrayList<String>HoldName = new ArrayList<String>();
            ArrayList<String>HoldAv = new ArrayList<String>();
            try {
                //Hold
                FileReader ResReader = new FileReader("Hold.txt");
                BufferedReader ResBuffer = new BufferedReader(ResReader);
                String oneSetData = "";
                while ((oneSetData = ResBuffer.readLine()) != null) {
                    StringTokenizer tk = new StringTokenizer(oneSetData, ",");
                    String patron = tk.nextToken();
                    int bookse = Integer.parseInt(tk.nextToken());
                    if(serial.equals(patron))
                    {
                        Hold.add(bookse+"");;
                    }
                }
            } catch (Exception e) {
            }
            for(int i=0; i<Hold.size(); i++)
            {
                int h = Integer.parseInt(Hold.get(i));
                try {

                    FileReader ResReader = new FileReader("MainBook.txt");
                    BufferedReader ResBuffer = new BufferedReader(ResReader);
                    String oneSetData = "";
                    while ((oneSetData = ResBuffer.readLine()) != null) {
                        StringTokenizer tk = new StringTokenizer(oneSetData, ",");
                        String name = tk.nextToken();
                        String auth = tk.nextToken();
                        String gen = tk.nextToken();
                        String pr = tk.nextToken();
                        int copies = Integer.parseInt(tk.nextToken());
                        String av = tk.nextToken();
                        String del = tk.nextToken();
                        int bookse = Integer.parseInt(tk.nextToken());
                        if(h==bookse)
                        {
                            HoldName.add(name);
                            if(av.equals("Y"))
                            {
                                HoldAv.add("Yes");
                            }
                            else if(av.equals("N"))
                            {
                                HoldAv.add("No");
                            }
                        }
                    }
                } catch (Exception e) {
                }

            }
            for(int i =0; i < HoldName.size(); i++)
            {
                String ha = HoldAv.get(i);
                String h = HoldName.get(i);
                holdmodel.insertRow(holdmodel.getRowCount(), new Object[]{h,ha});
            }

            //Punishment

            ArrayList<String>Punish = new ArrayList<String>();
            ArrayList<String>MemberName = new ArrayList<String>();
            ArrayList<String>MemberSerial = new ArrayList<String>();
            ArrayList<String>Bookse = new ArrayList<String>();
            ArrayList<String>Price = new ArrayList<String>();
            ArrayList<String>BookSerial = new ArrayList<String>();
            ArrayList<String>BookName = new ArrayList<String>();
            ArrayList<String>PatronSerial = new ArrayList<String>();
            ArrayList<String>DueDate = new ArrayList<String>();

            try
            {
                FileReader br = new FileReader("Payment.txt");
                BufferedReader bur = new BufferedReader(br);
                String re ="";
                while((re=bur.readLine())!=null)
                {
                    StringTokenizer tk = new StringTokenizer(re,",");
                    String name = tk.nextToken();
                    String patron = tk.nextToken();
                    int bookse = Integer.parseInt(tk.nextToken());
                    String date = tk.nextToken();

                    if(serial.equals(patron))
                    {
                        Punish.add(name);
                        PatronSerial.add(patron);
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
                System.out.println(Price.get(0));
                boolean err=false;
                for(int i=0; i<Price.size(); i++)
                {
                    String punish = Punish.get(i);
                    int price = Integer.parseInt(Price.get(i));
                    String book = BookName.get(i);
                    String bookser = BookSerial.get(i);
                    String patronser = PatronSerial.get(i);
                    String due = DueDate.get(i);

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date Today = sdf.parse(getTodayDate());
                    Date PunDate = sdf.parse(due);

                    long diffInMillies = Math.abs(Today.getTime() - PunDate.getTime());
                    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                    if(punish.equals("Damage"))
                    {
                        punishmodel.insertRow(punishmodel.getRowCount(), new Object[]{punish,book,price});
                    }
                    if(punish.equals("Overdue"))
                    {
                        punishmodel.insertRow(punishmodel.getRowCount(), new Object[]{punish,book,diff});
                    }
                    if(punish.equals("Member"))
                    {
                        punishmodel.insertRow(punishmodel.getRowCount(), new Object[]{punish,book,price});
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

    private void MembershipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MembershipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MembershipActionPerformed

    private void BorrowQuotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BorrowQuotaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BorrowQuotaActionPerformed

    private void HoldQuotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HoldQuotaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HoldQuotaActionPerformed

    private void BackToAdminoHomePageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackToAdminoHomePageActionPerformed
        AdminHomePage f1 = new AdminHomePage();

        f1.show();

        ((Window)getRootPane().getParent()).dispose();
    }//GEN-LAST:event_BackToAdminoHomePageActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackToAdminoHomePage;
    private javax.swing.JTextField BorrowQuota;
    private javax.swing.JTable BorrowTable;
    private javax.swing.JTextField HoldQuota;
    private javax.swing.JTable HoldTable;
    private javax.swing.JTextField Membership;
    private javax.swing.JLabel PatSearch;
    private javax.swing.JComboBox<String> PatronCombo;
    private javax.swing.JTable PunishTable;
    private javax.swing.JButton Search;
    private javax.swing.JTextField SearchBar;
    private javax.swing.JButton Select;
    private javax.swing.JButton Select1;
    private javax.swing.JComboBox<String> SerialCombo;
    private javax.swing.JLabel Title;
    private javax.swing.JLabel Title1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
