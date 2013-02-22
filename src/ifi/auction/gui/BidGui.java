package ifi.auction.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import ifi.auction.AuctionDescription;
import ifi.auction.Good;
import ifi.auction.agent.Auctioneer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BidGui extends JPanel {
	
	private static final int WIDTH_OF_TEXT = 50;
	
	private static final String LBL_NAME = "Nom de produit";
	private static final String LBL_UNIT_PRICE = "Prix actuel";
	private static final String LBL_MIN_STEP = "Pas minimum";
	private static final String LBL_EXPIRE = "Temps expirant";
	private static final String LBL_DESCRIPTION = "Description";
	
	private static final String LBL_BIDDING_PRICE = "Nouveau prix";
	
	
	private static final String LBL_ADD = "Ajouter";
	
	public JTextField txtName = new JTextField(WIDTH_OF_TEXT);
	public JTextField txtPrice = new JTextField(WIDTH_OF_TEXT);
	public JTextField txtMinStep = new JTextField(WIDTH_OF_TEXT);
	public JTextField txtExpire = new JTextField(WIDTH_OF_TEXT);	
	public JTextArea txtDescription = new JTextArea();
	
	public JTextField txtBiddingPrice = new JTextField(WIDTH_OF_TEXT);
	
	private JLabel lblName = new JLabel(LBL_NAME);
	private JLabel lblPrice = new JLabel(LBL_UNIT_PRICE);
	private JLabel lblMinStep = new JLabel(LBL_MIN_STEP);
	private JLabel lblExpire = new JLabel(LBL_EXPIRE);
	private JLabel lblDescription = new JLabel(LBL_DESCRIPTION);
	private JLabel lblBiddingPrice = new JLabel(LBL_BIDDING_PRICE);
	
	public BidGui(AuctionDescription auctionDescription){	
		
		JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());	
        LayoutHelper layoutHelper = new LayoutHelper();
        txtName.setText(auctionDescription.getProductName());
        txtName.setEditable(false);

        txtPrice.setText(String.valueOf(auctionDescription.getCurrentPrice()));
        txtPrice.setEditable(false);

        txtMinStep.setText(String.valueOf(auctionDescription.getMinStep()));
        txtMinStep.setEditable(false);

        txtExpire.setText(auctionDescription.getExpire());
        txtExpire.setEditable(false);

        txtDescription.setText(auctionDescription.getDescription());
        txtDescription.setEditable(false);

        txtName.setText(auctionDescription.getProductName());
        txtName.setEditable(false);
        
        layoutHelper.addLabel(lblName, p);
        layoutHelper.addTextField(txtName, p);
        layoutHelper.addLabel(lblPrice, p);
        layoutHelper.addTextField(txtPrice, p);
        layoutHelper.addLabel(lblMinStep, p);
        layoutHelper.addTextField(txtMinStep, p);
        layoutHelper.addLabel(lblExpire, p);
        layoutHelper.addTextField(txtExpire, p);
        layoutHelper.addLabel(lblDescription, p);
        layoutHelper.addTextField(txtDescription, p);
        layoutHelper.addLabel(lblBiddingPrice, p);
        layoutHelper.addTextField(txtBiddingPrice, p);

		add(p, BorderLayout.CENTER);
				
		// Make the agent terminate when the user closes 
		// the GUI using the button on the upper right corner	
//		addWindowListener(new	WindowAdapter() {
//			public void windowClosing(WindowEvent e) {
//				auctioneer.doDelete();
//			}
//		} );
//		
//		setResizable(false);		
	}
//	public void showGui() {
//		pack();
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		int centerX = (int)screenSize.getWidth() / 2;
//		int centerY = (int)screenSize.getHeight() / 2;
//		setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
//		super.setVisible(true);
//	}
}
