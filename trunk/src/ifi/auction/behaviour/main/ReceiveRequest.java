package ifi.auction.behaviour.main;

import ifi.auction.AuctionDescription;
import ifi.auction.Constant;
import ifi.auction.agent.Auctioneer;
import jade.core.AID;
import jade.core.Agent;
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
				String content = msg.getContent();
				if(content != null){
					
				}else{
					AuctionDescription auction = (AuctionDescription) msg.getContentObject();
					AID auctioneer = msg.getSender();
					String conversationId = msg.getConversationId();
					if(conversationId.equals(Constant.ADD_AUCTION)){
						//Agent newAuction = new Agent();
						//newAuction.
						Auctioneer newAuction = new Auctioneer();
					}
					System.out.println("receive message from seller : " + auction.getProductName());
				}
			} catch (UnreadableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}else{
			block();
		}
	}

}
