package ifi.auction.behaviour.main;

import sun.security.action.GetLongAction;
import ifi.auction.AuctionDescription;
import ifi.auction.Constant;
import ifi.auction.agent.Auctioneer;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class ReceiveRequest extends CyclicBehaviour {
	private AgentContainer ac = null;
	private AgentController t1 = null;
	@Override
	public void action() {
		// TODO Auto-generated method stub
		// Receive request from Client(bidder or auctionner)
		System.out.println("Receiving...");
		ACLMessage msg = myAgent.receive();
		if (msg != null) {
			try {
				String content = msg.getContent();
				System.out.println(content);
				if (content.equals(Constant.GET_AUCTION_LIST)) {
	
				} else {
				
					AuctionDescription auction = (AuctionDescription) msg
							.getContentObject();
					AID auctioneer = msg.getSender();
					String auctionName = "Auction" + msg.getSender();
					try {
						System.out.println("hello");
						// create agent t1 on the same container of the creator agent
						AgentContainer container = (AgentContainer)myAgent.getContainerController(); // get a container controller for creating new agents
						t1 = container.createNewAgent(auctionName, "ifi.auction.agent.Auction", null);
						t1.start();
						System.out.println(myAgent.getLocalName()+" CREATED AND STARTED NEW THANKSAGENT:" + auctionName + " ON CONTAINER "+container.getContainerName());
					} catch (Exception any) {
						any.printStackTrace();
					}

					
					System.out.println("receive message from seller : "
							+ auction.getProductName());
				}
			} catch (UnreadableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			block();
		}
	}

}
