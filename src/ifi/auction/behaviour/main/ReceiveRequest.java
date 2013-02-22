package ifi.auction.behaviour.main;

import java.io.IOException;

import ifi.auction.AuctionDescription;
import ifi.auction.Constant;
import ifi.auction.agent.Main;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class ReceiveRequest extends CyclicBehaviour {
	private AgentContainer ac = null;
	private AgentController t1 = null;
	private Main mainAgent;
	
	public ReceiveRequest(Main mainAgent){
		this.mainAgent = mainAgent;
	}
	@Override
	public void action() {
		// TODO Auto-generated method stub
		// Receive request from Client(bidder or auctionner)
		System.out.println("Wait for message...");
		ACLMessage msg = myAgent.receive();
		if (msg != null) {
			try {
				String content = msg.getContent();
				System.out.println(content +"Get content message");
				if (content.equals(Constant.GET_AUCTION_LIST)) {
					System.out.println("receive message");
					// Message received. Process it
					String title = msg.getContent();
					ACLMessage reply = msg.createReply();
					// The requested book is NOT available for sale.
					reply.setContent("Seller: " + title);
					reply.setContentObject(mainAgent.getAuctionDescriptions());
					System.out.println(reply.getContent());
					myAgent.send(reply);
					
				} else {
					
					AuctionDescription auctionDescription = (AuctionDescription) msg
							.getContentObject();
					AID auctioneer = msg.getSender();
					String auctionName = "Auction1" + Math.random() ;//+ msg.getSender();
					try {
						System.out.println("Auction creation hello");
						// create agent t1 on the same container of the creator agent
						AgentContainer container = (AgentContainer)myAgent.getContainerController(); // get a container controller for creating new agents
						t1 = container.createNewAgent(auctionName, "ifi.auction.agent.Auction", null);
						t1.start();
												
						//send un message to t1
						ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
						try {
							AID newAuction = new AID(auctionName, AID.ISLOCALNAME);
							System.out.println(newAuction.getName());
							auctionDescription.setAuction(newAuction);
							cfp.setContentObject(auctionDescription);
//							cfp.setConversationId(Constant.ADD_AUCTION);	
							cfp.addReceiver(newAuction);
//							mainAgent.getAuctionDescriptions().put(newAuction, auctionDescription);
//							System.out.println();				
//							//cfp.setReplyWith("cfp" + System.currentTimeMillis());
//							myAgent.send(cfp);
//							System.out.println("Send ...asdfsadfd");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}			
						
						
						System.out.println(myAgent.getLocalName()+" CREATED AND STARTED NEW Agent:" + auctionName + " ON CONTAINER "+container.getContainerName());
					} catch (Exception any) {
						any.printStackTrace();
					}

					
					System.out.println("receive message about product : "
							+ auctionDescription.getProductName());
				}
			} catch (UnreadableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			block();
		}
	}

}
