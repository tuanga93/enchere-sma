package ifi.auction.model;


import ifi.auction.AuctionDescription;

import jade.core.AID;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class Model extends AbstractTableModel{
	private List<AuctionDescription> auctionDescriptions = new ArrayList<AuctionDescription>();
	private static final String[] names = {"Nom du produit", 
											"Prix initalisé", 
											"Pas minimum,", 
											"Prix actuel", 
											"Enchérisseur actuel",
											"Date limitée",
											"Description"
											};
	public Model(){
		
	}
	public Model(List<AuctionDescription> auctionDescriptions){
		this.auctionDescriptions = auctionDescriptions;
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return names.length;
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return auctionDescriptions.size();
	}
	@Override
	public Object getValueAt(int row, int col) {
		
    	AuctionDescription auctionDescription = (AuctionDescription) auctionDescriptions.get(row);
    	switch (col) {
		case 0:
			return auctionDescription.getProductName();
		case 1:
			return auctionDescription.getInitialPrice();
		case 2:
			return auctionDescription.getMinStep();
		case 3:
			return auctionDescription.getCurrentPrice();
		case 4:
			return auctionDescription.getCurrentBidder();
		case 5:
			return auctionDescription.getExpire();
		case 6:
			return auctionDescription.getDescription();
			
		default:
			return "";
		}		
	}
	
    @Override
    public String getColumnName(int col) {
        return names[col];
    }

    public AuctionDescription getValueAt(int row){
    	return auctionDescriptions.get(row);
    }
    
	public List<AuctionDescription> getAuctionDescriptions() {
		return auctionDescriptions;
	}

	public void setAuctionDescriptions(List<AuctionDescription> auctionDescriptions) {
		System.out.println("Change data");
		this.auctionDescriptions = auctionDescriptions;
		fireTableDataChanged();
	}
    
    
}
