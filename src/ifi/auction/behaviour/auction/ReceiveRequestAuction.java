package ifi.auction.behaviour.auction;

import ifi.auction.agent.Auction;
import ifi.auction.behaviour.auction.*;
import ifi.auction.AuctionDescription;
import ifi.auction.Constant;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class ReceiveRequestAuction extends CyclicBehaviour {

	private AuctionDescription auctionDescription = null;
	
	private Auction auctionAgent;
	
	public ReceiveRequestAuction(Auction a) {
		// TODO Auto-generated constructor stub
		auctionAgent = a;		
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		// Receive request from Client(bidder or auctionner)
		System.out.println("Auction ReceiveRequestAuction: Receiving...");
		ACLMessage msg = myAgent.receive();
		if (msg != null) {
			try {
				String content = msg.getContent();
				System.out.println(content);
				if (content.equals(Constant.GET_AUCTION_LIST)) {
					System.out.println("Auction ReceiveRequestAuction: Receive list request, I will send information");
					// Send auction infor to Main agent
					myAgent.addBehaviour(new SendAuctionInfor(auctionDescription));
					
				} else {
					auctionDescription = (AuctionDescription) msg.getContentObject();
					System.out.println("Price :" + auctionDescription.getCurrentPrice());
					//add auction
					if(msg.getPerformative() == ACLMessage.REQUEST){						
						auctionAgent.setAuctionDescription(auctionDescription);
						auctionAgent.getBidders().add(msg.getSender());
						//notification
						auctionAgent.addBehaviour(new NotifyBidders(auctionAgent.getBidders(), auctionDescription));
						auctionAgent.addBehaviour(new NotifyMain(auctionDescription));
					}else if(msg.getPerformative() == ACLMessage.CFP){
						auctionAgent.setAuctionDescription(auctionDescription);
						auctionAgent.addBehaviour(new TerminateAuction(auctionAgent));
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
