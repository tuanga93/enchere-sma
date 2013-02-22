package ifi.auction.behaviour.bidder;

import ifi.auction.AuctionDescription;
import ifi.auction.Constant;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class RequestAuctionList extends OneShotBehaviour {
		private static final int REPLY_TIMEOUT = 1000;
		private AID mainAgent;
		
//AuctionDescription au = new AuctionDescription("13", 100, 50, "df", "description");		
		
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
				ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
				try {
					cfp.setContent(Constant.GET_AUCTION_LIST);
					cfp.setConversationId(Constant.GET_AUCTION_LIST);				
					cfp.addReceiver(mainAgent);
	System.out.println(mainAgent);				
					myAgent.send(cfp);
					System.out.println("Send to main agent...");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
			}
		}

//		@Override
//		public boolean done() {
//			// TODO Auto-generated method stub
//			return true;
//		}
	}
