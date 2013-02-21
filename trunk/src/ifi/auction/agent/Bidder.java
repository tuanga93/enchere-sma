package ifi.auction.agent;

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
		
		gui = new ProductList(this);
		gui.showGui();
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		
		ServiceDescription sd = new ServiceDescription();
		sd.setType(BIDDER_TYPE);
		sd.setName(BIDDER_NAME);
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


}
