package ifi.auction.agent;

import ifi.auction.behaviour.bidder.*;
import ifi.auction.gui.AuctioneerGui;
import ifi.auction.gui.ProductList;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class Bidder extends Agent{
	private static final String BIDDER_TYPE = "Bidder";
	private static final String BIDDER_NAME = "BIDDER";
	
//	private AID[] recepteurAgents;
	private ProductList gui;
	protected void setup(){
		
		//gui = new ProductList(this);
		//gui.showGui();
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		
		ServiceDescription sd = new ServiceDescription();
		sd.setType(BIDDER_TYPE);
		sd.setName(BIDDER_NAME);
		//DFAgentDescription[] results = DFService.search(, dfd);
		
		try {
			DFService.register(this, dfd);		
		} catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		addBehaviour(new RequestAuctionList());
//		addBehaviour(new Reponse());
	}


}
