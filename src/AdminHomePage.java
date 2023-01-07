
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
public class AdminHomePage extends javax.swing.JFrame {
DefaultTableModel borrowmodel;
    DefaultTableModel holdmodel;
    DefaultTableModel punishmodel;
    /**
     * Creates new form AdminHomePage
     */
    public AdminHomePage() {
        initComponents();
       
        try{
            //Add overdue
            ArrayList<String>bookser = new ArrayList<String>();
            ArrayList<String>patser = new ArrayList<String>();
            
            ArrayList<String>duedate = new ArrayList<String>();
            ArrayList<String>addduedate = new ArrayList<String>();
            
            ArrayList<String>addpatser = new ArrayList<String>();
            ArrayList<String>addbookser = new ArrayList<String>();
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        Date Today = sdf.parse(getTodayDate());
                        
            FileReader prr = new FileReader("Borrow.txt");
                BufferedReader purr = new BufferedReader(prr);
                String re ="";
                
                while((re=purr.readLine())!=null)
                {
                    StringTokenizer tk = new StringTokenizer(re,",");
                    int patron = Integer.parseInt(tk.nextToken());
                    int bookse = Integer.parseInt(tk.nextToken());
                    int se = Integer.parseInt(tk.nextToken());
                    int renew = Integer.parseInt(tk.nextToken());
                    String due = tk.nextToken();
                    
                    Date Duedate = sdf.parse(due);
                    long diffInMillies = Duedate.getTime() - Today.getTime();
                    if(diffInMillies<0)
                    {
                        bookser.add(se+"");
                        patser.add(patron+"");
                        duedate.add(due);
                    }
                    }
                
                
            for(int i = 0; i<bookser.size();i++)
            {
                
                int patr = Integer.parseInt(patser.get(i));
                int book = Integer.parseInt(bookser.get(i));
                String dat = duedate.get(i);
                boolean yes = false;
                
                FileReader crr = new FileReader("Payment.txt");
            BufferedReader bbr = new BufferedReader(crr);
            re ="";
            while((re=bbr.readLine())!=null)
            {
                StringTokenizer tk = new StringTokenizer(re,",");
                String name = tk.nextToken();
                int patron = Integer.parseInt(tk.nextToken());
                    int bookse = Integer.parseInt(tk.nextToken());
                    String date = tk.nextToken();
                
                    if(name.equals("Overdue") && patron==patr && book==bookse)
                {
                        yes = true;
                }
                    
                }
            
            if(yes==false)
            {
                System.out.println(book);
                addbookser.add(book+"");
                addduedate.add(dat);
            }
            }
                
            FileWriter fw = new FileWriter("Payment.txt", true);
            PrintWriter pr = new PrintWriter(fw);
            
            for(int i=0; i<addbookser.size(); i++)
            {
                String b = addbookser.get(i);
                String p = addpatser.get(i);
                String dat = addduedate.get(i);
                pr.println("Overdue," + p + "," + b + "," + dat);
            }
            pr.close();
                }

            catch(Exception e)
            {

            }
        
        
        //Delete Patron
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
                            if(tot>=100)
                            {
                                Hun.add(patronser);
                            }
                        }
                        if(punish.equals("Overdue"))
                        {
                            if(tot>=100)
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
                    String hun = hund.remove(i);
                    if (hun.equals(ser)) {
                    restoreList.add(block + "," + floor + "," + n + "," + hpn + "," + email + "," + mem + ",N," + ser);
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
        } catch (Exception e) {

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

        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        MenuBar = new javax.swing.JMenuBar();
        PatronBar = new javax.swing.JMenu();
        ApplyMembership = new javax.swing.JMenuItem();
        ViewPatrn = new javax.swing.JMenuItem();
        AdminBar = new javax.swing.JMenu();
        EditMembership = new javax.swing.JMenuItem();
        EditPatronData = new javax.swing.JMenuItem();
        RegisterEditDeleteBooks = new javax.swing.JMenuItem();
        ViewDeletePunish = new javax.swing.JMenuItem();
        BookBar = new javax.swing.JMenu();
        BorrowBook = new javax.swing.JMenuItem();
        ReturnBook = new javax.swing.JMenuItem();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(255, 0, 102));
        setForeground(java.awt.Color.gray);

        jPanel4.setBackground(new java.awt.Color(202, 214, 226));
        jPanel4.setToolTipText("");

        jPanel1.setBackground(new java.awt.Color(243, 196, 192));

        jLabel15.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        jLabel15.setText("Patron");

        jLabel7.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel7.setText("Apply Membership");

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel8.setText("View Patron");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel15))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel8)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(243, 196, 192));

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        jLabel5.setText("Admin");

        jLabel9.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel9.setText("Edit Membership");

        jLabel10.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel10.setText("Edit Patron Data");

        jLabel11.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel11.setText("Register/Edit/Delete Patron");

        jLabel12.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel12.setText("Payment");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(jLabel5))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel9)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addComponent(jLabel12)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 6, Short.MAX_VALUE)
                        .addComponent(jLabel11)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(12, 12, 12)
                .addComponent(jLabel10)
                .addGap(12, 12, 12)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(243, 196, 192));

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        jLabel6.setText("Books");

        jLabel13.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel13.setText("Borrow/Hold Book");

        jLabel14.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel14.setText("Return Book");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 28, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(22, 22, 22))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel6))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel14)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel3.setText("Developed by: E-Therng Lee");

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 30)); // NOI18N
        jLabel2.setText("Welcome to HoloCondo Library Management Program");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(261, 261, 261)
                                .addComponent(jLabel3))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(71, 71, 71)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel2)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26))
        );

        PatronBar.setText("Patron");

        ApplyMembership.setText("Apply Membership");
        ApplyMembership.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApplyMembershipActionPerformed(evt);
            }
        });
        PatronBar.add(ApplyMembership);

        ViewPatrn.setText("View Patron");
        ViewPatrn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewPatrnActionPerformed(evt);
            }
        });
        PatronBar.add(ViewPatrn);

        MenuBar.add(PatronBar);

        AdminBar.setText("Admin");

        EditMembership.setText("Edit Membership");
        EditMembership.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditMembershipActionPerformed(evt);
            }
        });
        AdminBar.add(EditMembership);

        EditPatronData.setText("Edit Patron Data");
        EditPatronData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditPatronDataActionPerformed(evt);
            }
        });
        AdminBar.add(EditPatronData);

        RegisterEditDeleteBooks.setText("Register/Edit/Delete Books");
        RegisterEditDeleteBooks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisterEditDeleteBooksActionPerformed(evt);
            }
        });
        AdminBar.add(RegisterEditDeleteBooks);

        ViewDeletePunish.setText("Payment");
        ViewDeletePunish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewDeletePunishActionPerformed(evt);
            }
        });
        AdminBar.add(ViewDeletePunish);

        MenuBar.add(AdminBar);

        BookBar.setText("Books");

        BorrowBook.setText("Borrow/Hold Book");
        BorrowBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BorrowBookActionPerformed(evt);
            }
        });
        BookBar.add(BorrowBook);

        ReturnBook.setText("Return/Renew Book");
        ReturnBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReturnBookActionPerformed(evt);
            }
        });
        BookBar.add(ReturnBook);

        MenuBar.add(BookBar);

        setJMenuBar(MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EditMembershipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditMembershipActionPerformed
        setContentPane(new EditMembership());
        pack();
    }//GEN-LAST:event_EditMembershipActionPerformed

    private void EditPatronDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditPatronDataActionPerformed
        setContentPane(new EditPatronData());
        pack();
    }//GEN-LAST:event_EditPatronDataActionPerformed

    private void RegisterEditDeleteBooksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegisterEditDeleteBooksActionPerformed
        setContentPane(new RegisterEditDeleteBooks());
        pack();
    }//GEN-LAST:event_RegisterEditDeleteBooksActionPerformed

    private void ApplyMembershipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ApplyMembershipActionPerformed
        setContentPane(new Membership());
        pack();
    }//GEN-LAST:event_ApplyMembershipActionPerformed

    private void BorrowBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BorrowBookActionPerformed
        BookSearch f1 = new BookSearch();

        f1.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_BorrowBookActionPerformed

    private void ReturnBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReturnBookActionPerformed
        setContentPane(new ReturnBook());
        pack();
    }//GEN-LAST:event_ReturnBookActionPerformed

    private void ViewDeletePunishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewDeletePunishActionPerformed
        setContentPane(new Payment());
        pack();
    }//GEN-LAST:event_ViewDeletePunishActionPerformed

    private void ViewPatrnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewPatrnActionPerformed
        setContentPane(new ViewPatron());
        pack();
    }//GEN-LAST:event_ViewPatrnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminHomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminHomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminHomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminHomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminHomePage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu AdminBar;
    private javax.swing.JMenuItem ApplyMembership;
    private javax.swing.JMenu BookBar;
    private javax.swing.JMenuItem BorrowBook;
    private javax.swing.JMenuItem EditMembership;
    private javax.swing.JMenuItem EditPatronData;
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JMenu PatronBar;
    private javax.swing.JMenuItem RegisterEditDeleteBooks;
    private javax.swing.JMenuItem ReturnBook;
    private javax.swing.JMenuItem ViewDeletePunish;
    private javax.swing.JMenuItem ViewPatrn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}
