/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Viewer;

import Controller.db_Customer;
import Controller.db_Inventory;
import Controller.db_Order;
import Model.Customer;
import Model.Inventory;
import Model.Order;
import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import static javax.swing.BorderFactory.createEmptyBorder;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aggelos
 */
public class viewer extends JFrame {

    public static Connection conn = null;
    static String dbURL = "jdbc:mysql://127.0.0.1:3306/eshop?useSSL=false";
    static String user = "root";
    static String pass = "admin";
    int chooseAction = -1;
    JLabel id = new JLabel("ID:", JLabel.RIGHT);
    JLabel lastname = new JLabel("LastName:", JLabel.RIGHT);
    JLabel firstname = new JLabel("FirstName:", JLabel.RIGHT);
    JLabel afm = new JLabel("AFM:", JLabel.RIGHT);
    JLabel telephone = new JLabel("Telephone:", JLabel.RIGHT);
    JTextField ttelephone = new JTextField();
    JTextField tid = new JTextField();
    JTextField tlastname = new JTextField();
    JTextField tfirstname = new JTextField();
    JTextField tafm = new JTextField();
    JPanel panel = new JPanel();
    JButton[] buttons = new JButton[9];
    JFrame frame = new JFrame("Customer");
    JMenuBar mb = new JMenuBar();

    public viewer() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        initComponents();
        connect2db();

    }

    private void initComponents() {
        JMenu files, order, report, help;
        JMenuItem files_inventory, files_customers, files_exit, order_place_order,
                reports_customers, reports_inventory, reports_invoice, about;
        JFrame f = new JFrame("Invoice Application 2021");
        JMenuBar mb = new JMenuBar();
        files = new JMenu("Files");
        order = new JMenu("Order");
        report = new JMenu("Reports");
        help = new JMenu("Help");
        files_inventory = new JMenuItem(new AbstractAction("Inventory") {
            public void actionPerformed(ActionEvent e) {
                try {
                    open_InventoriesFile_Panel();
                } catch (SQLException ex) {
                    Logger.getLogger(viewer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        files_customers = new JMenuItem(new AbstractAction("Customers") {
            public void actionPerformed(ActionEvent e) {
                try {
                    open_CusotmersFile_Panel();
                } catch (SQLException ex) {
                    Logger.getLogger(viewer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        files_exit = new JMenuItem(new AbstractAction("Exit") {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                System.exit(0);
            }
        });
        order_place_order = new JMenuItem(new AbstractAction("Place Order") {
            public void actionPerformed(ActionEvent e) {
                try {
                    open_Place_Order();
                } catch (SQLException ex) {
                    Logger.getLogger(viewer.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        reports_customers = new JMenuItem(new AbstractAction("Customers") {
            public void actionPerformed(ActionEvent e) {
                open_customers_report();
            }
        });
        reports_inventory = new JMenuItem(new AbstractAction("Inventory") {
            public void actionPerformed(ActionEvent e) {
                open_inventories_report();
            }
        });
        reports_invoice = new JMenuItem(new AbstractAction("INVOICE") {
            public void actionPerformed(ActionEvent e) {
                try {
                    open_invoice_report();
                } catch (SQLException ex) {
                    Logger.getLogger(viewer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        about = new JMenuItem(new AbstractAction("About") {
            public void actionPerformed(ActionEvent e) {
                open_about_frame();
            }
        });

        files.add(files_inventory);
        files.add(files_customers);
        files.add(files_exit);
        order.add(order_place_order);
        report.add(reports_customers);
        report.add(reports_inventory);
        report.add(reports_invoice);
        help.add(about);
        mb.add(files);
        mb.add(order);
        mb.add(report);
        mb.add(help);
        f.setJMenuBar(mb);
        f.setSize(400, 400);
        f.setLayout(null);
        f.setVisible(true);
    }

    private void open_about_frame() {
        JFrame about_frame = new JFrame("About");
        JPanel panel = new JPanel();
        panel.setSize(new Dimension(80, 50));
        panel.setLayout(new GridLayout(3, 2));

        JTextField progect = new JTextField("Invetory Shop");
        JTextField version = new JTextField("0.0.");
        JTextField author = new JTextField("@Author");
        JTextField name = new JTextField("Aggelos Pirpiris");
        JTextField am1 = new JTextField("AM");
        JTextField am2 = new JTextField("4006");

        am2.setSize(new Dimension(50, 20));
        version.setSize(new Dimension(50, 20));
        author.setSize(new Dimension(50, 20));
        progect.setSize(new Dimension(50, 20));
        name.setSize(new Dimension(50, 20));
        am1.setSize(new Dimension(50, 20));

        progect.setEnabled(false);
        version.setEnabled(false);
        name.setEnabled(false);
        am1.setEnabled(false);
        am2.setEnabled(false);
        author.setEnabled(false);

        about_frame.setSize(new Dimension(150, 150));
        panel.add(progect);
        panel.add(version);
        panel.add(author);
        panel.add(name);
        panel.add(am1);
        panel.add(am2);
        about_frame.add(panel);
        about_frame.setLocationRelativeTo(null);
        about_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        about_frame.setVisible(true);
        about_frame.show();
    }

    private void open_invoice_report() throws SQLException {
        JFrame invoice_report_frame = new JFrame("Invoice Report");
        invoice_report_frame.setLayout(new GridLayout(6, 2));
        ArrayList<Customer> customers = db_Customer.selectCustomers();
        Vector<Customer> cust_items = new Vector();
        for (Customer tmp : customers) {
            cust_items.add(tmp);
        }
        JComboBox cust_cb = new JComboBox(cust_items);
        cust_cb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    print_invoice(cust_cb.getSelectedIndex());

                } catch (SQLException ex) {
                    Logger.getLogger(viewer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        cust_cb.setBounds(50, 50, 600, 20);
        invoice_report_frame.add(cust_cb);
        invoice_report_frame.setLayout(null);
        invoice_report_frame.setSize(800, 300);
        invoice_report_frame.setLocationRelativeTo(null);
        invoice_report_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        invoice_report_frame.setVisible(true);
        invoice_report_frame.show();
//        System.out.println("  -> " + cust_cb.getItemAt(cust_cb.getSelectedIndex()));
    }

    private void print_invoice(int index) throws SQLException {
        ArrayList<Customer> customers = db_Customer.selectCustomers();
        Customer c = customers.get(index);
        System.out.println("CUSTOMER REPORT\n\n\n");
        System.out.println("Code\tLastname\t\t\tFirstName\t\t\tAFM\t\t\tTelephone");
        System.out.println("======================================================================================================");
        System.out.println(c.getIdcustomer() + "\t" + c.getLastName() + "\t\t\t" + c.getFirstName() + "\t\t\t" + c.getAfm() + "\t\t\t" + c.getTelephone());
        System.out.println("======================================================================================================");

    }

    private void open_inventories_report() {
        JFrame c_report = new JFrame("Print Inventories");
        JButton button = new JButton("Print Inventories");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    print_inventories();
                } catch (SQLException ex) {
                    Logger.getLogger(viewer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        button.setVisible(true);
        button.setLocation(50, 50);
        button.setSize(150, 20);
        c_report.add(button);
        c_report.setLayout(null);
        c_report.setSize(300, 150);
        c_report.setLocationRelativeTo(null);
        c_report.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        c_report.setVisible(true);
        c_report.show();
    }

    private void print_inventories() throws SQLException {
        ArrayList<Inventory> inventories = db_Inventory.selectInventories();
        System.out.println("INVENTORY REPORT\n\n\n");
        System.out.println("Code\tCategory\t\t\tDiscription\t\t\tQuantity\t\t\tPrice");
        System.out.println("======================================================================================================");
        for (Inventory c : inventories) {
            System.out.println(c.getIdinv() + "\t" + c.getCategory() + "\t\t\t" + c.getProductName() + "\t\t\t" + c.getQuantity() + "\t\t\t" + c.getPrice());
        }
        System.out.println("======================================================================================================");

    }

    private void open_customers_report() {
        JFrame c_report = new JFrame("Print Customers");
        JButton button = new JButton("Print Customers");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    print_customers();
                } catch (SQLException ex) {
                    Logger.getLogger(viewer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        button.setVisible(true);
        button.setLocation(50, 50);
        button.setSize(150, 20);
        c_report.add(button);
        c_report.setLayout(null);
        c_report.setSize(300, 150);
        c_report.setLocationRelativeTo(null);
        c_report.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        c_report.setVisible(true);
        c_report.show();
    }

    private void print_customers() throws SQLException {
        ArrayList<Customer> customers = db_Customer.selectCustomers();
        System.out.println("CUSTOMER REPORT\n\n\n");
        System.out.println("Code\tLastname\t\t\tFirstName\t\t\tAFM\t\t\tTelephone");
        System.out.println("======================================================================================================");
        for (Customer c : customers) {
            System.out.println(c.getIdcustomer() + "\t" + c.getLastName() + "\t\t\t" + c.getFirstName() + "\t\t\t" + c.getAfm() + "\t\t\t" + c.getTelephone());
        }
        System.out.println("======================================================================================================");

    }

    private void open_Place_Order() throws SQLException {
        JFrame order_frame = new JFrame("Place Order");

        JPanel buttonSet = new JPanel();
        JButton addLineButton = new JButton("Add Line");
        JButton deleteLineButon = new JButton("Delete Line");
        deleteLineButon.setEnabled(false);
        buttonSet.add(addLineButton);
        buttonSet.add(deleteLineButon);

        JPanel down_content_panel = new JPanel();
        setLayout(new BorderLayout());
        JPanel up_content_panel = new JPanel();
        add(up_content_panel, BorderLayout.CENTER);
        up_content_panel.setLayout(new GridLayout(6, 2));

        JLabel cust_text = new JLabel("Customer:");
        cust_text.setBounds(145, 30, 50, 30);
        up_content_panel.add(cust_text);

        ArrayList<Customer> customers = db_Customer.selectCustomers();
        Vector<Customer> cust_items = new Vector();
        for (Customer tmp : customers) {
            cust_items.add(tmp);
        }
        JComboBox cust_cb = new JComboBox(cust_items);
        cust_cb.setBounds(200, 30, 200, 30);

        JLabel item_text = new JLabel("Item Price");
        JTextField item_Label = new JTextField();
        JLabel quantity_text = new JLabel("Quantity");
        JTextField quantity_Label = new JTextField("1");

        JLabel total_price_text = new JLabel("Total Price");
        JTextField total_price_Label = new JTextField();

        JLabel inv_text = new JLabel("Inventory Item:");
        up_content_panel.add(cust_text);

        ArrayList<Inventory> inv = db_Inventory.selectInventories();
        Vector<Inventory> inv_items = new Vector();
        for (Inventory tmp : inv) {
            inv_items.add(tmp);
        }
        JComboBox inv_cb = new JComboBox(inv_items);
        inv_cb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    addLineButton.setEnabled(true);
                    ArrayList<Inventory> inv_list = db_Inventory.selectInventories();
                    Inventory inv = inv_list.get(inv_cb.getSelectedIndex());
                    item_Label.setText(Integer.toString(inv.getPrice()));
                    total_price_Label.setText(Integer.toString(inv.getPrice()));
                    System.out.println("Inevntory " + inv.toString());
                    up_content_panel.repaint();
                } catch (SQLException ex) {
                    Logger.getLogger(viewer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        JLabel dummy1 = new JLabel();
        JLabel dummy2 = new JLabel();

        up_content_panel.add(cust_text);
        up_content_panel.add(cust_cb);
        up_content_panel.add(inv_text);
        up_content_panel.add(inv_cb);
        up_content_panel.add(item_text);
        up_content_panel.add(item_Label);
        up_content_panel.add(quantity_text);
        up_content_panel.add(quantity_Label);
        up_content_panel.add(total_price_text);
        up_content_panel.add(total_price_Label);
        up_content_panel.add(dummy1);
        up_content_panel.add(dummy2);

//        JPanel parentPanel=new JPanel();
//        parentPanel.add(up_content_panel);
//        parentPanel.add(buttonSet);
        JSplitPane splitter = new JSplitPane(JSplitPane.VERTICAL_SPLIT, up_content_panel, buttonSet);
        splitter.setBackground(Color.red);

        String[] tblHead = {"OrderID", "Customer", "Category", "Description", "Price"};
        DefaultTableModel dtm = new DefaultTableModel();

        dtm.setColumnIdentifiers(tblHead);
        ArrayList<Order> orderslist = new ArrayList();
        orderslist = db_Order.selectOrders();
        for (Order o : orderslist) {
            Customer customer = db_Customer.selectCustomer(o.getCustomerID());
            Inventory inventory = db_Inventory.selectInventory(o.getInvid());
            String[] item = {Integer.toString(o.getOrderID()), customer.getLastName(), inventory.getCategory(), inventory.getProductName(), Integer.toString(inventory.getPrice())};
            dtm.addRow(item);

        }

        JTable table = new JTable();

        table.setModel(dtm);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        table.setFillsViewportHeight(true);
        table.setCellSelectionEnabled(true);
        ListSelectionModel cellSelectionModel = table.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        cellSelectionModel.addListSelectionListener((ListSelectionEvent e) -> {
            deleteLineButon.setEnabled(true);

//                selectedRow = table.getSelectedRow();
//                
//                orderID= Integer.parseInt((String) table.getValueAt(selectedRow,0));
//                 try {
//                     db_Order.deleteOrder(orderID);
//                 } catch (SQLException ex) {
//                     Logger.getLogger(viewer.class.getName()).log(Level.SEVERE, null, ex);
//                 }
//                 dtm.removeRow(i);
//                 table.repaint();
        });

        JScrollPane scroll = new JScrollPane(table);

        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        down_content_panel.add(scroll);
        JSplitPane splitPane_main = new JSplitPane(JSplitPane.VERTICAL_SPLIT, splitter, down_content_panel);

        addLineButton.setEnabled(false);
        addLineButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int customerIndex = cust_cb.getSelectedIndex();
                    int inventoryIndex = inv_cb.getSelectedIndex();

                    ArrayList<Customer> listofCustomers = db_Customer.selectCustomers();
                    Customer selectedCustomer = listofCustomers.get(customerIndex);

                    ArrayList<Inventory> listofInventories = db_Inventory.selectInventories();
                    Inventory selectedInventory = listofInventories.get(inventoryIndex);

                    Order newOrder = new Order(selectedCustomer.getIdcustomer(), selectedInventory.getIdinv(), 1, selectedInventory.getPrice());
                    insertOrder(newOrder);

                    ArrayList<Order> listofOrders = db_Order.selectOrders();
                    Order selectedOrder = listofOrders.get(listofOrders.size() - 1);
                    System.out.println(selectedOrder.toString());

                    String[] newitem = {Integer.toString(selectedOrder.getOrderID()), selectedCustomer.getLastName(), selectedInventory.getCategory(), selectedInventory.getProductName(), Integer.toString(selectedInventory.getPrice())};
                    dtm.addRow(newitem);
                    table.repaint();

                } catch (SQLException ex) {
                    Logger.getLogger(viewer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        deleteLineButon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {

                    int selectedRow = table.getSelectedRow();
                    int orderID = Integer.parseInt((String) table.getValueAt(selectedRow, 0));
                    db_Order.deleteOrder(orderID);
                    dtm.removeRow(selectedRow);
                    table.repaint();
                    deleteLineButon.setEnabled(false);
                } catch (SQLException ex) {
                    Logger.getLogger(viewer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        splitPane_main.setVisible(true);
        order_frame.add(splitPane_main);
//        order_frame.setLayout(null);
        order_frame.setSize(800, 600);
        order_frame.setLocationRelativeTo(null);
        order_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        order_frame.setVisible(true);
        order_frame.show();
    }

    private static void insertOrder(Order newOrder) throws SQLException {
        db_Order.InsertOrder(newOrder);
    }

    private void open_CusotmersFile_Panel() throws SQLException {
        Customer firstcustomer = db_Customer.getfirstCustomer();
        mb.removeAll();
        chooseAction = -1;
        buttons[0] = new JButton("first");
        buttons[1] = new JButton("previous");
        buttons[2] = new JButton("next");
        buttons[3] = new JButton("last");
        buttons[4] = new JButton("add");
        buttons[5] = new JButton("modify");
        buttons[6] = new JButton("delete");
        buttons[7] = new JButton("ok");
        buttons[8] = new JButton("cancel");
        for (int i = 0; i < buttons.length; i++) {
            mb.add(buttons[i]);
        }
        tid.setEditable(false);
        tlastname.setEditable(false);
        tfirstname.setEditable(false);
        tafm.setEditable(false);
        ttelephone.setEditable(false);
        tid.setText(String.valueOf(firstcustomer.getIdcustomer()));
        tlastname.setText(firstcustomer.getLastName());
        tfirstname.setText(firstcustomer.getFirstName());
        tafm.setText(firstcustomer.getAfm());
        tafm.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c)
                        || (c == KeyEvent.VK_BACK_SPACE)
                        || (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }
        });
        ttelephone.setText(firstcustomer.getTelephone());
        ttelephone.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c)
                        || (c == KeyEvent.VK_BACK_SPACE)
                        || (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }
        });
//        tid
//        tlastname
//        tfirstname
//        tafm
//        ttelephone
        buttons[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doFirst();
            }
        });
        buttons[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    doPrevious(Integer.parseInt(tid.getText()));
                } catch (SQLException ex) {
                    Logger.getLogger(viewer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        buttons[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    doNext(Integer.parseInt(tid.getText()));
                } catch (SQLException ex) {
                    Logger.getLogger(viewer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        buttons[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    doLast();
                } catch (SQLException ex) {
                    Logger.getLogger(viewer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        buttons[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
//                    String lastName, String firstName, String afm, String telephone)

                    doAdd();
                } catch (SQLException ex) {
                    Logger.getLogger(viewer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        buttons[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    doModify();
                } catch (SQLException ex) {
                    Logger.getLogger(viewer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        buttons[6].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    doDelete(Integer.parseInt(tid.getText()));
                } catch (SQLException ex) {
                    Logger.getLogger(viewer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        buttons[7].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    doOk(chooseAction);
                } catch (SQLException ex) {
                    Logger.getLogger(viewer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        buttons[8].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doCancel();
            }
        });
        buttons[7].setEnabled(false);
        buttons[8].setEnabled(false);
        JButton close_b = new JButton("Close");
        JPanel panel2 = new JPanel();
        panel2.add(close_b);
        close_b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        panel.add(mb);

        JPanel n_panel = new JPanel(new GridLayout(5, 2));

        n_panel.setPreferredSize(new Dimension(580, 85));
        n_panel.repaint();

        n_panel.add(id);
        n_panel.add(tid);

        n_panel.add(lastname);
        n_panel.add(tlastname);

        n_panel.add(firstname);
        n_panel.add(tfirstname);

        n_panel.add(afm);
        n_panel.add(tafm);

        n_panel.add(telephone);
        n_panel.add(ttelephone);
        panel.add(n_panel);

        panel.add(panel2);

        frame.add(panel);
        buttons[5].setEnabled(true);
        buttons[6].setEnabled(true);
        frame.setSize(580, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.show();
        frame.revalidate();
        frame.repaint();
        panel.setVisible(true);

    }

    public void doFirst() {
        buttons[5].setEnabled(true);
        buttons[6].setEnabled(true);
        buttons[7].setEnabled(false);
        buttons[8].setEnabled(false);
        chooseAction = -1;
        Customer customer = new Customer();
        try {
            customer = Controller.db_Customer.getfirstCustomer();

        } catch (SQLException ex) {
            Logger.getLogger(viewer.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (customer != null) {
            tid.setEditable(false);
            tlastname.setEditable(false);
            tfirstname.setEditable(false);
            tafm.setEditable(false);
            ttelephone.setEditable(false);
            tid.setText(String.valueOf(customer.getIdcustomer()));
            tlastname.setText(customer.getLastName());
            tfirstname.setText(customer.getFirstName());
            tafm.setText(customer.getAfm());
            ttelephone.setText(customer.getTelephone());
            panel.updateUI();
            panel.revalidate();
            frame.show();
            frame.revalidate();
            frame.repaint();
        } else {

            tid.setEditable(false);
            tlastname.setEditable(false);
            tfirstname.setEditable(false);
            tafm.setEditable(false);
            ttelephone.setEditable(false);
            tid.setText("empty");
            tlastname.setText("empty");
            tfirstname.setText("empty");
            tafm.setText("empty");
            ttelephone.setText("empty");
            panel.updateUI();
            panel.revalidate();
            frame.show();
            frame.revalidate();
            frame.repaint();
        }
    }

    public void doPrevious(int customer_id) throws SQLException {
        buttons[5].setEnabled(true);
        buttons[6].setEnabled(true);
        buttons[7].setEnabled(false);
        buttons[8].setEnabled(false);
        chooseAction = -1;
        System.out.println("mphke sto doprevious");
        ArrayList<Customer> customers = db_Customer.selectCustomers();
        Customer previous = null;
        if (customers.size() == 1) {
            previous = customers.get(0);
        } else {
            previous = customers.get(0);
            for (int i = 0; i < customers.size(); i++) {
                if (customers.get(i).getIdcustomer() == customer_id) {
                    if (i != 0) {
                        previous = customers.get(i - 1);
                        break;
                    } else {
                        previous = customers.get(customers.size() - 1);
                        break;
                    }
                }
            }
        }
        tid.setEditable(false);
        tlastname.setEditable(false);
        tfirstname.setEditable(false);
        tafm.setEditable(false);
        ttelephone.setEditable(false);
        tid.setText(String.valueOf(previous.getIdcustomer()));
        tlastname.setText(previous.getLastName());
        tfirstname.setText(previous.getFirstName());
        tafm.setText(previous.getAfm());
        ttelephone.setText(previous.getTelephone());
        panel.updateUI();
        panel.revalidate();
        frame.show();
        frame.revalidate();
        frame.repaint();

    }

    public void doNext(int customer_id) throws SQLException {
        buttons[5].setEnabled(true);
        buttons[6].setEnabled(true);
        buttons[7].setEnabled(false);
        buttons[8].setEnabled(false);
        chooseAction = -1;
        System.out.println("mphke sto donext");
        ArrayList<Customer> customers = db_Customer.selectCustomers();
        Customer next = null;
        System.out.println(customers.toString());
        if (customers.size() == 1) {
            customers.get(0);
            next = customers.get(0);
            System.out.println("1");

        } else {
            next = customers.get(0);
            for (int i = 0; i < customers.size(); i++) {
                if (customers.get(i).getIdcustomer() == customer_id) {
                    if (i != (customers.size() - 1)) {
                        next = customers.get(i + 1);
                        System.out.println("2");
                        break;
                    } else {
                        next = customers.get(0);
                        System.out.println("3");
                        break;

                    }
                }
            }
        }
        tid.setEditable(false);
        tlastname.setEditable(false);
        tfirstname.setEditable(false);
        tafm.setEditable(false);
        ttelephone.setEditable(false);
        tid.setText(String.valueOf(next.getIdcustomer()));
        tlastname.setText(next.getLastName());
        tfirstname.setText(next.getFirstName());
        tafm.setText(next.getAfm());
        ttelephone.setText(next.getTelephone());
        panel.updateUI();
        panel.revalidate();
        frame.show();
        frame.revalidate();
        frame.repaint();

    }

    public void doLast() throws SQLException {
        buttons[5].setEnabled(true);
        buttons[6].setEnabled(true);
        buttons[7].setEnabled(false);
        buttons[8].setEnabled(false);
        chooseAction = -1;
        ArrayList<Customer> customers = db_Customer.selectCustomers();
        Customer customer = new Customer();
        if (customers != null) {
            customer = customers.get(customers.size() - 1);
        } else {
            customer = new Customer("empty", "empty", "empty", "empty", 0);
        }
        System.out.println("last customer" + customer);
        tid.setEditable(false);
        tlastname.setEditable(false);
        tfirstname.setEditable(false);
        tafm.setEditable(false);
        ttelephone.setEditable(false);
        tid.setText(String.valueOf(customer.getIdcustomer()));
        tlastname.setText(customer.getLastName());
        tfirstname.setText(customer.getFirstName());
        tafm.setText(customer.getAfm());
        ttelephone.setText(customer.getTelephone());
        panel.updateUI();
        panel.revalidate();
        frame.show();
        frame.revalidate();
        frame.repaint();
    }

    public void doAdd() throws SQLException {

        buttons[5].setEnabled(false);
        buttons[6].setEnabled(false);
        buttons[7].setEnabled(true);
        buttons[8].setEnabled(true);
        chooseAction = 0;
        buttons[4].setEnabled(true);
        tid.setEditable(false);
        tlastname.setEditable(true);
        tfirstname.setEditable(true);
        tafm.setEditable(true);
        ttelephone.setEditable(true);
        tid.setText("");
        tlastname.setText("");
        tfirstname.setText("");
        tafm.setText("");
        ttelephone.setText("");
        panel.updateUI();
        panel.revalidate();
        frame.show();
        frame.revalidate();
        frame.repaint();

    }

    public void doModify() throws SQLException {
        buttons[7].setEnabled(true);
        buttons[8].setEnabled(true);
        Customer customer = new Customer(tlastname.getText(), tfirstname.getText(), tafm.getText(), ttelephone.getText(), Integer.parseInt(tid.getText()));
        chooseAction = 1;
        buttons[4].setEnabled(true);
        tid.setEditable(false);
        tlastname.setEditable(true);
        tfirstname.setEditable(true);
        tafm.setEditable(true);
        ttelephone.setEditable(true);
        tid.setText(String.valueOf(customer.getIdcustomer()));
        tlastname.setText(customer.getLastName());
        tfirstname.setText(customer.getFirstName());
        tafm.setText(customer.getAfm());
        ttelephone.setText(customer.getTelephone());
        panel.updateUI();
        panel.revalidate();
        frame.show();
        frame.revalidate();
        frame.repaint();

    }

    public void doDelete(int customer_id) throws SQLException {
        buttons[7].setEnabled(false);
        buttons[8].setEnabled(false);
        chooseAction = 2;
        buttons[4].setEnabled(true);
        System.out.println("customer_id " + customer_id);
        db_Order.deleteOrderWithCustomerID(customer_id);
        db_Customer.deleteCustomer(customer_id);
        doNext(customer_id);
    }

    public void doOk(int i) throws SQLException {
        buttons[5].setEnabled(true);
        buttons[6].setEnabled(true);
        buttons[7].setEnabled(false);
        buttons[8].setEnabled(false);

        if (i == 0) {
            System.out.println("mpainei edw?");
            Customer cust_tmp = new Customer(tlastname.getText(), tfirstname.getText(), tafm.getText(), ttelephone.getText());
            db_Customer.InsertCustomer(cust_tmp);
        } else {
//            String category, String productName, int price, int quantity, int idinv

            Customer cust_tmp = new Customer(tlastname.getText(), tfirstname.getText(), tafm.getText(), ttelephone.getText(), Integer.parseInt(tid.getText()));
            System.out.println("customer -> " + cust_tmp.toString());
            db_Customer.updateCustomer(cust_tmp);
        }
        doLast();
    }

    public void doCancel() {
        buttons[5].setEnabled(true);
        buttons[6].setEnabled(true);
        buttons[7].setEnabled(false);
        buttons[8].setEnabled(false);
        doFirst();

    }

    private void connect2db() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        try {
            conn = DriverManager.getConnection(dbURL, user, pass);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    JPanel panel_inv = new JPanel();
    JMenuBar mb_inv = new JMenuBar();
    JButton[] buttons_inv = new JButton[9];
    JLabel id_inv = new JLabel("ID:", JLabel.RIGHT);
    JLabel category = new JLabel("Category:", JLabel.RIGHT);
    JLabel description = new JLabel("Description:", JLabel.RIGHT);
    JLabel price = new JLabel("Price:", JLabel.RIGHT);
    JLabel quantity = new JLabel("Quantity:", JLabel.RIGHT);
    JTextField tcategory = new JTextField();
    JTextField tid_inv = new JTextField();
    JTextField tdescription = new JTextField();
    JTextField tprice = new JTextField();
    JTextField tquantity = new JTextField(25);
    JFrame frame_inv = new JFrame("Inventory");
    int chooseAction_inv = -1;

    private void open_InventoriesFile_Panel() throws SQLException {
        Inventory firstInventory = db_Inventory.getfirstInventory();
        mb_inv.removeAll();
        chooseAction_inv = -1;
        buttons_inv[0] = new JButton("first");
        buttons_inv[1] = new JButton("previous");
        buttons_inv[2] = new JButton("next");
        buttons_inv[3] = new JButton("last");
        buttons_inv[4] = new JButton("add");
        buttons_inv[5] = new JButton("modify");
        buttons_inv[6] = new JButton("delete");
        buttons_inv[7] = new JButton("ok");
        buttons_inv[8] = new JButton("cancel");
        for (int i = 0; i < buttons.length; i++) {
            mb_inv.add(buttons_inv[i]);
        }
        tid_inv.setEditable(false);
        tcategory.setEditable(false);
        tdescription.setEditable(false);
        tprice.setEditable(false);
        tquantity.setEditable(false);
        tid_inv.setText(String.valueOf(firstInventory.getIdinv()));
        tcategory.setText(firstInventory.getCategory());
        tdescription.setText(firstInventory.getProductName());
        tprice.setText(String.valueOf(firstInventory.getPrice()));
        tquantity.setText(String.valueOf(firstInventory.getQuantity()));
        tprice.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c)
                        || (c == KeyEvent.VK_BACK_SPACE)
                        || (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }
        });
        tquantity.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c)
                        || (c == KeyEvent.VK_BACK_SPACE)
                        || (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }
        });
        buttons_inv[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doFirst_inv();
            }
        });
        buttons_inv[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    doPrevious_inv(Integer.parseInt(tid_inv.getText()));
                } catch (SQLException ex) {
                    Logger.getLogger(viewer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        buttons_inv[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    doNext_inv(Integer.parseInt(tid_inv.getText()));
                } catch (SQLException ex) {
                    Logger.getLogger(viewer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        buttons_inv[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    doLast_inv();
                } catch (SQLException ex) {
                    Logger.getLogger(viewer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        buttons_inv[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
//                    String lastName, String firstName, String afm, String telephone)

                    doAdd_inv();
                } catch (SQLException ex) {
                    Logger.getLogger(viewer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        buttons_inv[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    doModify_inv();
                } catch (SQLException ex) {
                    Logger.getLogger(viewer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        buttons_inv[6].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    doDelete_inv(Integer.parseInt(tid_inv.getText()));
                } catch (SQLException ex) {
                    Logger.getLogger(viewer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        buttons_inv[7].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    doOk_inv(chooseAction_inv);
                } catch (SQLException ex) {
                    Logger.getLogger(viewer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        buttons_inv[8].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doCancel_inv();
            }
        });
        buttons_inv[7].setEnabled(false);
        buttons_inv[8].setEnabled(false);
        JButton close_b = new JButton("Close");
        JPanel panel_inv2 = new JPanel();
        panel_inv2.add(close_b);
        close_b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame_inv.dispose();
            }
        });

        panel_inv.add(mb_inv);

        JPanel n_panel_inv = new JPanel(new GridLayout(5, 2));

        n_panel_inv.setPreferredSize(new Dimension(580, 85));
        n_panel_inv.repaint();

        n_panel_inv.add(id_inv);
        n_panel_inv.add(tid_inv);

        n_panel_inv.add(category);
        n_panel_inv.add(tcategory);

        n_panel_inv.add(description);
        n_panel_inv.add(tdescription);

        n_panel_inv.add(price);
        n_panel_inv.add(tprice);

        n_panel_inv.add(quantity);
        n_panel_inv.add(tquantity);
        panel_inv.add(n_panel_inv);

        panel_inv.add(panel_inv2);

        frame_inv.add(panel_inv);
        buttons_inv[5].setEnabled(true);
        buttons_inv[6].setEnabled(true);
        frame_inv.setSize(580, 300);
        frame_inv.setLocationRelativeTo(null);
        frame_inv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame_inv.setVisible(true);
        frame_inv.show();
        frame_inv.revalidate();
        frame_inv.repaint();
        panel_inv.setVisible(true);

    }

    public void doFirst_inv() {
        buttons_inv[5].setEnabled(true);
        buttons_inv[6].setEnabled(true);
        buttons_inv[7].setEnabled(false);
        buttons_inv[8].setEnabled(false);
        chooseAction_inv = -1;
        Inventory inventory = new Inventory();
        try {
            inventory = Controller.db_Inventory.getfirstInventory();

        } catch (SQLException ex) {
            Logger.getLogger(viewer.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (inventory != null) {
            tid_inv.setEditable(false);
            tcategory.setEditable(false);
            tdescription.setEditable(false);
            tprice.setEditable(false);
            tquantity.setEditable(false);
            tid_inv.setText(String.valueOf(inventory.getIdinv()));
            tcategory.setText(inventory.getCategory());
            tdescription.setText(inventory.getProductName());
            tprice.setText(String.valueOf(inventory.getPrice()));
            tquantity.setText(String.valueOf(inventory.getQuantity()));

            panel_inv.updateUI();
            panel_inv.revalidate();
            frame_inv.show();
            frame_inv.revalidate();
            frame_inv.repaint();
        } else {

            tid_inv.setEditable(false);
            tcategory.setEditable(false);
            tdescription.setEditable(false);
            tprice.setEditable(false);
            tquantity.setEditable(false);
            tid_inv.setText("empty");
            tcategory.setText("empty");
            tdescription.setText("empty");
            tprice.setText("empty");
            tquantity.setText("empty");
            panel_inv.updateUI();
            panel_inv.revalidate();
            frame_inv.show();
            frame_inv.revalidate();
            frame_inv.repaint();
        }
    }

    public void doPrevious_inv(int Inventory_id) throws SQLException {
        buttons_inv[5].setEnabled(true);
        buttons_inv[6].setEnabled(true);
        buttons_inv[7].setEnabled(false);
        buttons_inv[8].setEnabled(false);
        chooseAction_inv = -1;
        System.out.println("mphke sto doprevious_inv");
        ArrayList<Inventory> Inventories = db_Inventory.selectInventories();
        Inventory previous = null;
        if (Inventories.size() == 1) {
            previous = Inventories.get(0);
        } else {
            previous = Inventories.get(0);
            for (int i = 0; i < Inventories.size(); i++) {
                if (Inventories.get(i).getIdinv() == Inventory_id) {
                    if (i != 0) {
                        previous = Inventories.get(i - 1);
                        break;
                    } else {
                        previous = Inventories.get(Inventories.size() - 1);
                        break;
                    }
                }
            }
        }
        tid_inv.setEditable(false);
        tcategory.setEditable(false);
        tdescription.setEditable(false);
        tprice.setEditable(false);
        tquantity.setEditable(false);
        tid_inv.setText(String.valueOf(previous.getIdinv()));
        tcategory.setText(previous.getCategory());
        tdescription.setText(previous.getProductName());
        tprice.setText(String.valueOf(previous.getPrice()));
        tquantity.setText(String.valueOf(previous.getQuantity()));
        panel_inv.updateUI();
        panel_inv.revalidate();
        frame_inv.show();
        frame_inv.revalidate();
        frame_inv.repaint();

    }

    public void doNext_inv(int Inventory_id) throws SQLException {
        buttons_inv[5].setEnabled(true);
        buttons_inv[6].setEnabled(true);
        buttons_inv[7].setEnabled(false);
        buttons_inv[8].setEnabled(false);
        chooseAction_inv = -1;
        System.out.println("mphke sto donext_inv");
        ArrayList<Inventory> Inventories = db_Inventory.selectInventories();
        Inventory next = null;
        System.out.println(Inventories.toString());
        if (Inventories.size() == 1) {
            Inventories.get(0);
            next = Inventories.get(0);
            System.out.println("1");

        } else {
            next = Inventories.get(0);
            for (int i = 0; i < Inventories.size(); i++) {
                if (Inventories.get(i).getIdinv() == Inventory_id) {
                    if (i != (Inventories.size() - 1)) {
                        next = Inventories.get(i + 1);
                        System.out.println("2");
                        break;
                    } else {
                        next = Inventories.get(0);
                        System.out.println("3");
                        break;

                    }
                }
            }
        }
        tid_inv.setEditable(false);
        tcategory.setEditable(false);
        tdescription.setEditable(false);
        tprice.setEditable(false);
        tquantity.setEditable(false);
        tid_inv.setText(String.valueOf(next.getIdinv()));
        tcategory.setText(next.getCategory());
        tdescription.setText(next.getProductName());
        tprice.setText(String.valueOf(next.getPrice()));
        tquantity.setText(String.valueOf(next.getQuantity()));
        panel_inv.updateUI();
        panel_inv.revalidate();
        frame_inv.show();
        frame_inv.revalidate();
        frame_inv.repaint();

    }

    public void doLast_inv() throws SQLException {
        buttons_inv[5].setEnabled(true);
        buttons_inv[6].setEnabled(true);
        buttons_inv[7].setEnabled(false);
        buttons_inv[8].setEnabled(false);
        chooseAction_inv = -1;
        ArrayList<Inventory> Inventories = db_Inventory.selectInventories();
        Inventory inventory = new Inventory();
        if (Inventories != null) {
            inventory = Inventories.get(Inventories.size() - 1);
        } else {
            inventory = new Inventory("empty", "empty", 0, 0, 0);
        }
        System.out.println("last Inventory" + inventory);
        tid_inv.setEditable(false);
        tcategory.setEditable(false);
        tdescription.setEditable(false);
        tprice.setEditable(false);
        tquantity.setEditable(false);
        tid_inv.setText(String.valueOf(inventory.getIdinv()));
        tcategory.setText(inventory.getCategory());
        tdescription.setText(inventory.getProductName());
        tprice.setText(String.valueOf(inventory.getPrice()));
        tquantity.setText(String.valueOf(inventory.getQuantity()));
        panel_inv.updateUI();
        panel_inv.revalidate();
        frame_inv.show();
        frame_inv.revalidate();
        frame_inv.repaint();
    }

    public void doAdd_inv() throws SQLException {

        buttons_inv[5].setEnabled(false);
        buttons_inv[6].setEnabled(false);
        buttons_inv[7].setEnabled(true);
        buttons_inv[8].setEnabled(true);
        chooseAction_inv = 0;
        buttons_inv[4].setEnabled(true);
        tid_inv.setEditable(false);
        tcategory.setEditable(true);
        tdescription.setEditable(true);
        tprice.setEditable(true);
        tquantity.setEditable(true);
        tid_inv.setText("");
        tcategory.setText("");
        tdescription.setText("");
        tprice.setText("");
        tquantity.setText("");
        panel_inv.updateUI();
        panel_inv.revalidate();
        frame_inv.show();
        frame_inv.revalidate();
        frame_inv.repaint();

    }

    public void doModify_inv() throws SQLException {
        buttons_inv[7].setEnabled(true);
        buttons_inv[8].setEnabled(true);
        Inventory inventory = new Inventory(tcategory.getText(), tdescription.getText(), Integer.parseInt(tprice.getText()), Integer.parseInt(tquantity.getText()), Integer.parseInt(tid_inv.getText()));
        chooseAction_inv = 1;
        buttons_inv[4].setEnabled(true);
        tid_inv.setEditable(false);
        tcategory.setEditable(true);
        tdescription.setEditable(true);
        tprice.setEditable(true);
        tquantity.setEditable(true);
        tid_inv.setText(String.valueOf(inventory.getIdinv()));
        tcategory.setText(inventory.getCategory());
        tdescription.setText(inventory.getProductName());
        tprice.setText(String.valueOf(inventory.getPrice()));
        tquantity.setText(String.valueOf(inventory.getQuantity()));
        panel_inv.updateUI();
        panel_inv.revalidate();
        frame_inv.show();
        frame_inv.revalidate();
        frame_inv.repaint();

    }

    public void doDelete_inv(int Inventory_id) throws SQLException {
        buttons_inv[7].setEnabled(false);
        buttons_inv[8].setEnabled(false);
        chooseAction_inv = 2;
        buttons_inv[4].setEnabled(true);
        System.out.println("Inventory_id " + Inventory_id);
        db_Order.deleteOrderWithInventoryID(Inventory_id);
        db_Inventory.deleteInventory(Inventory_id);
        doNext_inv(Inventory_id);
    }

    public void doOk_inv(int i) throws SQLException {
        buttons_inv[5].setEnabled(true);
        buttons_inv[6].setEnabled(true);
        buttons_inv[7].setEnabled(false);
        buttons_inv[8].setEnabled(false);

        if (i == 0) {
            System.out.println("mpainei edw?");
            Inventory inv_tmp = new Inventory(tcategory.getText(), tdescription.getText(), Integer.parseInt(tprice.getText()), Integer.parseInt(tquantity.getText()));
            db_Inventory.InsertInventory(inv_tmp);
        } else {
//            String category, String productName, int price, int quantity, int idinv

            Inventory inv_tmp = new Inventory(tcategory.getText(), tdescription.getText(), Integer.parseInt(tprice.getText()), Integer.parseInt(tquantity.getText()), Integer.parseInt(tid_inv.getText()));
            System.out.println("Inventory -> " + inv_tmp.toString());
            db_Inventory.updateInventory(inv_tmp);
        }
        doLast_inv();
    }

    public void doCancel_inv() {
        buttons_inv[5].setEnabled(true);
        buttons_inv[6].setEnabled(true);
        buttons_inv[7].setEnabled(false);
        buttons_inv[8].setEnabled(false);
        doFirst_inv();

    }

}
