package ifi.auction.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import ifi.auction.Auction;
import ifi.auction.Good;
import ifi.auction.agent.Auctioneer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AuctioneerGui extends JFrame {
	
	private static final int WIDTH_OF_TEXT = 50;
	
	private static final String LBL_NAME = "Nom de produit";
	private static final String LBL_UNIT_PRICE = "Prix du produit";
	private static final String LBL_MIN_STEP = "Pas minimum";
	private static final String LBL_EXPIRE = "Temps expirant";
	private static final String LBL_DESCRIPTION = "Description";
	
	
	private static final String LBL_ADD = "Ajouter";
	
	private JTextField txtName = new JTextField(WIDTH_OF_TEXT);
	private JTextField txtPrice = new JTextField(WIDTH_OF_TEXT);
	private JTextField txtMinStep = new JTextField(WIDTH_OF_TEXT);
	private JTextField txtExpire = new JTextField(WIDTH_OF_TEXT);
	private JTextField txtDescription = new JTextField(WIDTH_OF_TEXT);
	
	private JLabel lblName = new JLabel(LBL_NAME);
	private JLabel lblPrice = new JLabel(LBL_UNIT_PRICE);
	private JLabel lblMinStep = new JLabel(LBL_MIN_STEP);
	private JLabel lblExpire = new JLabel(LBL_EXPIRE);
	private JLabel lblDescription = new JLabel(LBL_DESCRIPTION);
	
	private Auctioneer auctioneer;
	public AuctioneerGui(Auctioneer a){
		super(a.getLocalName());
		
		auctioneer = a;
		
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
        layoutHelper.addTextField(txtExpire, p);
        layoutHelper.addLabel(lblDescription, p);
        layoutHelper.addTextField(txtDescription, p);

		getContentPane().add(p, BorderLayout.CENTER);
		
		JButton addButton = new JButton(LBL_ADD);
		addButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {					
					double minStep = Double.parseDouble(txtMinStep.getText());
					String productName = txtName.getText();
					double initialPrice = Double.parseDouble(txtPrice.getText());
					String expire = txtExpire.getText();
					String description = txtDescription.getText();
					Auction auction = new Auction(productName, initialPrice, minStep, expire, description);
//					String title = titleField.getText().trim();
//					String price = priceField.getText().trim();
//					//auctioneer.updateCatalogue(title, Integer.parseInt(price));
//					titleField.setText("");
//					priceField.setText("");
					auctioneer.addAuction(auction);
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(AuctioneerGui.this, "Invalid values. "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		} );
		p = new JPanel();
		p.add(addButton);
		getContentPane().add(p, BorderLayout.SOUTH);
		
		// Make the agent terminate when the user closes 
		// the GUI using the button on the upper right corner	
		addWindowListener(new	WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				auctioneer.doDelete();
			}
		} );
		
		setResizable(false);		
	}
	public void showGui() {
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int)screenSize.getWidth() / 2;
		int centerY = (int)screenSize.getHeight() / 2;
		setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
		super.setVisible(true);
	}		
}
