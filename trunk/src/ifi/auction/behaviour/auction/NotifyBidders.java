package ifi.auction.behaviour.auction;

import java.util.List;

import ifi.auction.agent.Auction;
import ifi.auction.behaviour.auction.*;
import ifi.auction.AuctionDescription;
import ifi.auction.Constant;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class NotifyBidders extends Behaviour {

	private AuctionDescription auctionDescription = null;
	
	private List<AID> bidders;
	
	public NotifyBidders(List<AID> b, AuctionDescription auctionDes) {
		// TODO Auto-generated constructor stub
		bidders = b;
		auctionDescription= auctionDes;
	}

	@Override
	public void action() {
		
		for (AID bidder : bidders) {
			ACLMessage cfp = new ACLMessage(ACLMessage.REQUEST);
			try {
				cfp.setContentObject(auctionDescription);								
				cfp.addReceiver(bidder);							
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
