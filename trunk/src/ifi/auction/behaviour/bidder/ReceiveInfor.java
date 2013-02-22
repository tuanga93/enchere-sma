package ifi.auction.behaviour.bidder;

import javax.swing.JOptionPane;

import ifi.auction.AuctionDescription;
import ifi.auction.Constant;
import ifi.auction.agent.Bidder;
import ifi.auction.behaviour.auction.NotifyBidders;
import ifi.auction.behaviour.auction.SendAuctionInfor;
import ifi.auction.gui.BidGui;
import ifi.auction.gui.MyAuctionListGUI;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class ReceiveInfor extends CyclicBehaviour{

	private AuctionDescription auctionDescription;
	private Bidder bidder;
	
	public ReceiveInfor(Bidder bidder) {
		super();
		this.bidder = bidder;
	}
	@Override
	public void action() {
		ACLMessage msg = myAgent.receive();
		if (msg != null) {
			try {
				//add auction
				auctionDescription = (AuctionDescription) msg.getContentObject();
				BidGui bidGui = new BidGui(auctionDescription);
				int result = JOptionPane.showConfirmDialog(null, bidGui, 
			               "Faire un enchère", JOptionPane.OK_CANCEL_OPTION);
			      if (result == JOptionPane.OK_OPTION) {
					try {					
						double biddingPrice = Double.parseDouble(bidGui.txtBiddingPrice.getText());
						auctionDescription.setCurrentPrice(biddingPrice);
						auctionDescription.setCurrentBidder(bidder.getAID());
						bidder.bid(auctionDescription);			    	  
					}
					catch (Exception e) {
						//JOptionPane.showMessageDialog(MyAuctionListGUI.this, "Invalid values. "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
					}			     
				}
				
			} catch (UnreadableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			block();
		}
	}

}
