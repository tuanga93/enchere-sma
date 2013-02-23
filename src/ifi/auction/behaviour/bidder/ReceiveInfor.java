package ifi.auction.behaviour.bidder;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import ifi.auction.AuctionDescription;
import ifi.auction.Constant;
import ifi.auction.agent.Bidder;
import ifi.auction.behaviour.auction.NotifyBidders;
import ifi.auction.behaviour.auction.SendAuctionInfor;
import ifi.auction.gui.BidGui;
import ifi.auction.gui.MyAuctionListGUI;
import jade.core.AID;
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
System.out.println("Bidder ReceiveInfor: Receive message from "+ msg.getSender().getName());
				
				Object content = msg.getContentObject();
				if(content instanceof AuctionDescription){
					auctionDescription = (AuctionDescription) content;
					if (msg.getPerformative()==ACLMessage.INFORM){
						if (auctionDescription.getCurrentBidder().equals(myAgent.getAID())){
							System.out.println("+++Bidder ReceiveInfor: You have wined this auction");
						}
					} else{
						//Auction
System.out.println("+++Bidder ReceiveInfor: Update AuctionDescription");
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
					}
				}else {
System.out.println("ReceiveInfor: receive Hashtable");					
					Hashtable<AID, AuctionDescription> mapAuctionDescriptions = (Hashtable<AID, AuctionDescription>)content;
					if(bidder.getGui() == null){
						bidder.setGui(new MyAuctionListGUI(bidder));					
					}
System.out.println("ReceiveInfor: receive Hashtable");		
System.out.println(mapAuctionDescriptions.values());	
System.out.println("ReceiveInfor: receive Hashtable");		
					List<AuctionDescription> auctionDescriptions = new ArrayList<AuctionDescription>(mapAuctionDescriptions.values()) ;
					bidder.getGui().getModel().setAuctionDescriptions(auctionDescriptions);
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
