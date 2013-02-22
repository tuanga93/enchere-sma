package ifi.auction.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import ifi.auction.AuctionDescription;
import ifi.auction.agent.Auctioneer;
import ifi.auction.model.Model;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AuctionListGUI extends JFrame implements ActionListener{
	
	private static final String LBL_ADD = "Ajouter";
		
	private Auctioneer auctioneer;	
	
	private	JTable		table;
	private Model model;
	private	JScrollPane scrollPane;

	public AuctionListGUI(Auctioneer a){
		super(a.getLocalName());
		
		auctioneer = a;
		model = new Model(a.getAuctionList());
        table = new JTable(model);
        
        
        table.setRowHeight(24);
        scrollPane = new JScrollPane(table);
        //add(scrollPane);       
//        this.add(panelButton, BorderLayout.SOUTH);
        //table.set
        
        
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel rowSM = table.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                //Ignore extra messages.
                    if (e.getValueIsAdjusting()) return;
 
                    ListSelectionModel lsm = (ListSelectionModel)e.getSource();
                    if (lsm.isSelectionEmpty()) {
                        System.out.println("No rows are selected.");
                } else {
                    int selectedRow = lsm.getMinSelectionIndex();
                    AuctionDescription auctionDescription = AuctionListGUI.this.getModel().getValueAt(selectedRow);
                    System.out.println(auctionDescription.getAuction());
//                    Control control =  ViewListPerson.this.getController();
//                    Person person = control.getValueAt(selectedRow);
//                    ViewListPerson.this.setFormData(person);
//                    ViewListPerson.this.setCurrentPerson(selectedRow);
//                    System.out.println("Row " + selectedRow
//                                       + " is now selected.");
                }
            }
        });
		
		
		JPanel p = new JPanel();
		
        JPanel panelListAuction = new JPanel(new GridLayout());
        panelListAuction.add(scrollPane);
		//getContentPane().add(p, BorderLayout.CENTER);
        getContentPane().add(panelListAuction, BorderLayout.CENTER);
		
		JButton addButton = new JButton(LBL_ADD);
		addButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					AddAuctioneerGui addAuctioneerGui = new AddAuctioneerGui();
					 int result = JOptionPane.showConfirmDialog(null, addAuctioneerGui, 
				               "Ajouter un enchère", JOptionPane.OK_CANCEL_OPTION);
				      if (result == JOptionPane.OK_OPTION) {
							try {					
								double minStep = Double.parseDouble(addAuctioneerGui.txtMinStep.getText());
								String productName = addAuctioneerGui.txtName.getText();
								double initialPrice = Double.parseDouble(addAuctioneerGui.txtPrice.getText());
								String expire = addAuctioneerGui.txtExpire.getText();
								String description = addAuctioneerGui.txtDescription.getText();
								AuctionDescription auction = new AuctionDescription(productName, initialPrice, minStep, expire, description);
//								String title = titleField.getText().trim();
//								String price = priceField.getText().trim();
//								//auctioneer.updateCatalogue(title, Integer.parseInt(price));
//								titleField.setText("");
//								priceField.setText("");
								auctioneer.addAuction(auction);				    	  
							}
							catch (Exception e) {
								JOptionPane.showMessageDialog(AuctionListGUI.this, "Invalid values. "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
							}
				      }
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(AuctionListGUI.this, "Invalid values. "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
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
		setSize((int)Math.round(screenSize.width*0.8), (int)Math.round(screenSize.height*0.8));
		int centerX = (int)screenSize.getWidth() / 2;
		int centerY = (int)screenSize.getHeight() / 2;
		setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);		
		super.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}	
	
}
