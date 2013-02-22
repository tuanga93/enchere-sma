package ifi.auction.behaviour.bidder;

import ifi.auction.AuctionDescription;
import ifi.auction.Constant;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class SendBid extends Behaviour{
	
	private AuctionDescription auctionDescription;
	
	public SendBid(AuctionDescription auctionDescription) {
		this.auctionDescription = auctionDescription;
	}
	
	@Override
	public void action() {
		AID	auctionAgent = auctionDescription.getAuction();
		ACLMessage cfp = new ACLMessage(ACLMessage.REQUEST);
		try {
			cfp.setContentObject(auctionDescription);								
			cfp.addReceiver(auctionAgent);
			
			myAgent.send(cfp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return true;
	}

}
