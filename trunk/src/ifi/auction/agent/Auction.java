package ifi.auction.agent;

import ifi.auction.behaviour.auction.*;
import ifi.auction.AuctionDescription;
import ifi.auction.Constant;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class Auction extends Agent {
	private AuctionDescription auctionDescription = null;
	
	protected void setup() {
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());

		ServiceDescription sd = new ServiceDescription();
		sd.setType(Constant.AUCTION_TYPE);
		sd.setName(Constant.AUCTION_NAME);
		dfd.addServices(sd);
		// DFAgentDescription[] results = DFService.search(, dfd);

		try {
			DFService.register(this, dfd);
		} catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addBehaviour(new ReceiveRequestAuction(auctionDescription));
//		addBehaviour(new SendAuctionInfor());
	}
}