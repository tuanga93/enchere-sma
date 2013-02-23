package ifi.auction.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ifi.auction.AuctionDescription;
import ifi.auction.Good;
import ifi.auction.agent.Auctioneer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

public class AddAuctioneerGui extends JPanel {
	
	private static final int WIDTH_OF_TEXT = 50;
	
	private static final String LBL_NAME = "Nom de produit";
	private static final String LBL_UNIT_PRICE = "Prix du produit";
	private static final String LBL_MIN_STEP = "Pas minimum";
	private static final String LBL_EXPIRE = "Temps expirant";
	private static final String LBL_DESCRIPTION = "Description";
	
	
	private static final String LBL_ADD = "Ajouter";
	
	public JTextField txtName = new JTextField(WIDTH_OF_TEXT);
	public JTextField txtPrice = new JTextField(WIDTH_OF_TEXT);
	public JTextField txtMinStep = new JTextField(WIDTH_OF_TEXT);
	public JTextField txtExpire = new JTextField(WIDTH_OF_TEXT);
	public JTextField txtDescription = new JTextField(WIDTH_OF_TEXT);
	
	private JLabel lblName = new JLabel(LBL_NAME);
	private JLabel lblPrice = new JLabel(LBL_UNIT_PRICE);
	private JLabel lblMinStep = new JLabel(LBL_MIN_STEP);
	private JLabel lblExpire = new JLabel(LBL_EXPIRE);
	private JLabel lblDescription = new JLabel(LBL_DESCRIPTION);
		
	public AddAuctioneerGui(){		
		
		JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());	
        LayoutHelper layoutHelper = new LayoutHelper();
        
        layoutHelper.addLabel(lblName, p);
        layoutHelper.addTextField(txtName, p);
        layoutHelper.addLabel(lblPrice, p);
        layoutHelper.addTextField(txtPrice, p);
        layoutHelper.addLabel(lblMinStep, p);
        layoutHelper.addTextField(txtMinStep, p);
        layoutHelper.addLabel(lblExpire, p);
		SimpleDateFormat datetimeFormatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		Date date = new Date(System.currentTimeMillis() + 180000);
		String defaultDate = datetimeFormatter.format(date);
        txtExpire.setText(defaultDate);
        layoutHelper.addTextField(txtExpire, p);
        
        final JXDatePicker datePicker = new JXDatePicker(Calendar.getInstance().getTime());        
        datePicker.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Date date = datePicker.getDate();
        		SimpleDateFormat datetimeFormatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        		String pickedDate = datetimeFormatter.format(date);       		
        		txtExpire.setText(pickedDate);
        	}
        });        
        
        layoutHelper.addLabel(lblDescription, p);
        layoutHelper.addTextField(txtDescription, p);

		add(p, BorderLayout.CENTER);
		add(datePicker);
	}
}
