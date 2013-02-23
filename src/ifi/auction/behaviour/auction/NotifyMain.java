package ifi.auction.behaviour.auction;

import java.util.List;

import ifi.auction.agent.Auction;
import ifi.auction.agent.Main;
import ifi.auction.behaviour.auction.*;
import ifi.auction.AuctionDescription;
import ifi.auction.Constant;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class NotifyMain extends Behaviour {

	private AuctionDescription auctionDescription = null;
	private AID mainAgent;
	public NotifyMain(AuctionDescription auctionDes) {
		// TODO Auto-generated constructor stub
		auctionDescription= auctionDes;
	}

	@Override
	public void action() {
		DFAgentDescription template = new DFAgentDescription();
		ServiceDescription serviceDescription = new ServiceDescription();
		serviceDescription.setType(Constant.MAIN_TYPE);		
		template.addServices(serviceDescription);		
		DFAgentDescription[] results = null;
		try {
			results = DFService.search(myAgent, template);
		} catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		if(results != null && results.length > 0){
			mainAgent = results[0].getName();
		
			ACLMessage cfp = new ACLMessage(ACLMessage.REQUEST);
			try {
				cfp.setContentObject(auctionDescription);								
				cfp.addReceiver(mainAgent);		
				myAgent.send(cfp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return true;
	}

}
