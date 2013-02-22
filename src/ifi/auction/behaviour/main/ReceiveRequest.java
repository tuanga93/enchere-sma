package ifi.auction.behaviour.main;

import java.io.IOException;

import sun.security.action.GetLongAction;
import ifi.auction.AuctionDescription;
import ifi.auction.Constant;
import ifi.auction.agent.Auctioneer;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
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
					
					///---------------------------------
					System.out.println("reques list of auction");
					ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
					try {
						cfp.setContent(Constant.GET_AUCTION_LIST);
						cfp.setConversationId(Constant.ADD_AUCTION);				
						cfp.addReceiver(new AID("Auction1", AID.ISLOCALNAME));
						//System.out.println();				
						//cfp.setReplyWith("cfp" + System.currentTimeMillis());
						myAgent.send(cfp);
						System.out.println("Send to auction");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}		
					//------------------------------------
					
				} else {
					
					AuctionDescription auction = (AuctionDescription) msg
							.getContentObject();
					AID auctioneer = msg.getSender();
					String auctionName = "Auction1" ;//+ msg.getSender();
					try {
						System.out.println("hello");
						// create agent t1 on the same container of the creator agent
						AgentContainer container = (AgentContainer)myAgent.getContainerController(); // get a container controller for creating new agents
						t1 = container.createNewAgent(auctionName, "ifi.auction.agent.Auction", null);
						t1.start();
						
						//send un message to t1
						ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
						try {
							cfp.setContentObject(auction);
							cfp.setConversationId(Constant.ADD_AUCTION);				
							cfp.addReceiver(new AID(auctionName, AID.ISLOCALNAME));
							System.out.println();				
							//cfp.setReplyWith("cfp" + System.currentTimeMillis());
							myAgent.send(cfp);
							System.out.println("Send ...asdfsadfd");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}			
						
						
						System.out.println(myAgent.getLocalName()+" CREATED AND STARTED NEW Agent:" + auctionName + " ON CONTAINER "+container.getContainerName());
					} catch (Exception any) {
						any.printStackTrace();
					}

					
					System.out.println("receive message about product : "
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
