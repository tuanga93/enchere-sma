package ifi.auction.behaviour.auction;

import ifi.auction.AuctionDescription;
import ifi.auction.Constant;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

import java.io.IOException;

public class SendAuctionInfor extends Behaviour {
	private static final int REPLY_TIMEOUT = 1000;
	private AuctionDescription auction;
	private AID mainAgent;

	
	public SendAuctionInfor(AuctionDescription auctionDescription) {
		// TODO Auto-generated constructor stub
		auction = auctionDescription ; 
	}

	@Override
	public void action() {
		
		//Send Auction Description to MainAgent
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
			//mainAgent = new AID();
			mainAgent = results[0].getName();
			ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
			try {
				cfp.setContentObject(auction);
				cfp.setConversationId(Constant.AUCTION_INFOR);				
				cfp.addReceiver(mainAgent);
System.out.println(mainAgent);				
				//cfp.setReplyWith("cfp" + System.currentTimeMillis());
				myAgent.send(cfp);
				System.out.println("Send Info To MainAgent...");
			} catch (IOException e) {
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