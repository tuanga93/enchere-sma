package ifi.auction.behaviour.main;

import java.io.IOException;

import ifi.auction.AuctionDescription;
import ifi.auction.Constant;
import ifi.auction.agent.Main;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class NotifyChange extends OneShotBehaviour{
	private Main mainAgent;
	private AID auctioneer;
	public NotifyChange(Main mainAgent, AID auctioneer){
		this.mainAgent = mainAgent;
		this.auctioneer = auctioneer;
	}
	@Override
	public void action() {
		// TODO Auto-generated method stub
		DFAgentDescription template = new DFAgentDescription();
		ServiceDescription serviceDescription = new ServiceDescription();
		serviceDescription.setType(Constant.BIDDER_TYPE);		
		template.addServices(serviceDescription);	
		DFAgentDescription[] results = null;
		ACLMessage msg = new ACLMessage(ACLMessage.CFP);
		
		try {
			msg.setContentObject(mainAgent.getAuctionDescriptions());		
			//notify bidders
			results = DFService.search(myAgent, template);
System.out.println("Bidder &&&&&&&" + results.length);			
			if(results != null && results.length > 0){
				for(int i = 0; i < results.length; i++){
					msg.addReceiver(results[i].getName());
					myAgent.send(msg);
				}
			}					
		} catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		
		
		//notify auctioneer				
		try {
			msg.setContentObject(mainAgent.getAuctionDescriptions(auctioneer));
			msg.addReceiver(auctioneer);
			myAgent.send(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(results != null && results.length > 0){
			
		}		
	}

}
