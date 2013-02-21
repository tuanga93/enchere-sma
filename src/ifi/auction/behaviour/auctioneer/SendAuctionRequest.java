package ifi.auction.behaviour.auctioneer;

import java.io.IOException;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import ifi.auction.Auction;
import ifi.auction.Constant;
public class SendAuctionRequest extends OneShotBehaviour {
	private static final int REPLY_TIMEOUT = 1000;
	private Auction auction;
	
	private AID mainAgent;
	
	public SendAuctionRequest(Auction a){
		auction = a;
	}

	@Override
	public void action() {
		DFAgentDescription template = new DFAgentDescription();
		DFAgentDescription[] results = null;
		try {
			results = DFService.search(myAgent, template);
		} catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ServiceDescription serviceDescription = new ServiceDescription();
		serviceDescription.setType(Constant.MAIN_TYPE);
		template.addServices(serviceDescription);
		if(results != null && results.length == 1){
			//mainAgent = new AID();
			mainAgent = results[0].getName();
		}
		ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
		try {
			cfp.setContentObject(auction);
			cfp.setConversationId("");
			cfp.addReceiver(mainAgent);
			//cfp.setReplyWith("cfp" + System.currentTimeMillis());
			myAgent.send(cfp);
			System.out.println("Send ...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
