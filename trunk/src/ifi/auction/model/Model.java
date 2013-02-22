package ifi.auction.model;


import ifi.auction.AuctionDescription;

import jade.core.AID;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class Model extends AbstractTableModel{
	private List<AuctionDescription> auctionDescriptions = new ArrayList<AuctionDescription>();
	public Model(List<AuctionDescription> auctionDescriptions){
		this.auctionDescriptions = auctionDescriptions;
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
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
			return auctionDescription.getCurrentPrice();
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
}
