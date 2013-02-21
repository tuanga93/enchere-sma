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
		//DFAgentDescription[] results = DFService.search(, dfd);
		
		try {
			DFService.register(this, dfd);		
			DFAgentDescription template = new DFAgentDescription();
			DFAgentDescription[] results = DFService.search(this, template);
			ServiceDescription serviceDescription = new ServiceDescription();
			serviceDescription.setType("Recepteur");
			template.addServices(serviceDescription);			
//			recepteurAgents = new AID[results.length];
//			for (int i = 0; i < results.length; ++i) {
//				recepteurAgents[i] = results[i].getName();
//			}			
		} catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		addBehaviour(new RequestPerformer());
//		addBehaviour(new Reponse());
	}
	public void addAuction(Auction au){
		addBehaviour(new SendAuctionRequest(au));		
	}

}
