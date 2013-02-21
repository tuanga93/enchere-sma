package ifi.auction.agent;

import ifi.auction.Constant;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

import ifi.auction.behaviour.main.*;

public class Main extends Agent {


	protected void setup() {

		// gui = new ProductList(this);
		// gui.showGui();
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());

		ServiceDescription sd = new ServiceDescription();
		sd.setType(Constant.MAIN_TYPE);
		sd.setName(Constant.MAIN_NAME);
		dfd.addServices(sd);
		try {
			DFService.register(this, dfd);	
		} catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		addBehaviour(new ReceiveRequest());
		addBehaviour(new AddAuction());
		addBehaviour(new GetAuction());
	}

}
