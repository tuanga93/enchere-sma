package ifi.auction.behaviour.main;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class ReceiveRequest extends CyclicBehaviour{

	@Override
	public void action() {
		// TODO Auto-generated method stub
		// Receive request from Client(bidder or auctionner)
		ACLMessage msg = myAgent.receive();
		if (msg != null) {
			System.out.println("receive message from seller");
		}
	}

}
