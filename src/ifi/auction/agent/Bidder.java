package ifi.auction.agent;

import java.util.ArrayList;
import java.util.List;

import ifi.auction.AuctionDescription;
import ifi.auction.behaviour.bidder.*;
import ifi.auction.gui.AuctioneerGui;
import ifi.auction.gui.AuctionListGUI;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class Bidder extends CommonAuctionAgent{
	private static final String BIDDER_TYPE = "Bidder";
	private static final String BIDDER_NAME = "BIDDER";
	
//	private AID[] recepteurAgents;
	private AuctionListGUI gui;
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
	public List<AuctionDescription> getAuctionList(){
		AuctionDescription a = new AuctionDescription("xxx", 1234, 124, "12346", "XXXXXXXXX");
		List<AuctionDescription> b = new ArrayList<AuctionDescription>();
		b.add(a);
		return b;
	}

}
