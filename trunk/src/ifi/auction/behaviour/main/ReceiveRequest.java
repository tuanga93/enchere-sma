package ifi.auction.behaviour.main;

import ifi.auction.Auction;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class ReceiveRequest extends CyclicBehaviour{

	@Override
	public void action() {
		// TODO Auto-generated method stub
		// Receive request from Client(bidder or auctionner)		
System.out.println("Receiving...");		
		ACLMessage msg = myAgent.receive();
		if (msg != null) {
			try {
				Auction auction = (Auction) msg.getContentObject();
				System.out.println("receive message from seller : " + auction.getProductName());
			} catch (UnreadableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}else{
			block();
		}
	}

}
