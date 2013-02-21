package ifi.auction.agent;

import ifi.auction.Auction;
import ifi.auction.Constant;
import ifi.auction.behaviour.auctioneer.SendAuctionRequest;
import ifi.auction.gui.AuctioneerGui;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class Auctioneer extends Agent{
//	private AID[] recepteurAgents;
	private AuctioneerGui gui;
	protected void setup(){
		
		gui = new AuctioneerGui(this);
		gui.showGui();
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		
		ServiceDescription sd = new ServiceDescription();
		sd.setType(Constant.AUCTIONEER_TYPE);
		sd.setName(Constant.AUCTIONEER_NAME);
		dfd.addServices(sd);
		//DFAgentDescription[] results = DFService.search(, dfd);
		
		try {
			DFService.register(this, dfd);		
		} catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		addBehaviour(new RequestPerformer());
//		addBehaviour(new Reponse());
		
		//test
//		Auction au = new Auction("Bookk", 10000, 100, "123456", "XXX");
//		addAuction(au);
		
	}
	public void addAuction(Auction au){
		addBehaviour(new SendAuctionRequest(au));		
	}

}
