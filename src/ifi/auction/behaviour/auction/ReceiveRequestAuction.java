package ifi.auction.behaviour.auction;

import ifi.auction.AuctionDescription;
import ifi.auction.Constant;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class ReceiveRequestAuction extends CyclicBehaviour {

	private AuctionDescription auctionDescription = null;
	
	@Override
	public void action() {
		// TODO Auto-generated method stub
		// Receive request from Client(bidder or auctionner)
		System.out.println("Receiving...");
		ACLMessage msg = myAgent.receive();
		if (msg != null) {
			try {
				String content = msg.getContent();
				System.out.println(content);
				if (content.equals(Constant.GET_AUCTION_LIST)) {
					System.out.println("Receive list request, I will send list");
				} else {
					auctionDescription = (AuctionDescription) msg
							.getContentObject();
System.out.println("I'm new auction for: "
							+ auctionDescription.getProductName()+ auctionDescription.getCurrentBidder());
					try {
						System.out.println("hello");
					} catch (Exception any) {
						any.printStackTrace();
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
