package ifi.auction.agent;

import ifi.auction.Constant;
import ifi.auction.gui.ProductList;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

import ifi.auction.behaviour.main.*;

public class Main extends Agent {
	// private AID[] recepteurAgents;
	private ProductList gui;

	protected void setup() {

		// gui = new ProductList(this);
		// gui.showGui();
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());

		ServiceDescription sd = new ServiceDescription();
		sd.setType(Constant.MAIN_TYPE);
		sd.setName(Constant.MAIN_NAME);
		// DFAgentDescription[] results = DFService.search(, dfd);

		try {
			//register agent 
			DFService.register(this, dfd);
			DFAgentDescription template = new DFAgentDescription();
			DFAgentDescription[] results = DFService.search(this, template);
			ServiceDescription serviceDescription = new ServiceDescription();
			serviceDescription.setType(Constant.MAIN_TYPE);
			template.addServices(serviceDescription);
		} catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addBehaviour(new ReceiveRequest());
		addBehaviour(new AddAuction());
		addBehaviour(new GetAuction());
	}

}
