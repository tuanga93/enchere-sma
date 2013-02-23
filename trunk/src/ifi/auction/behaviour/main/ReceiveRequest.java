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
		System.out.println("Main ReceiveRequest: Wait for message...");
		ACLMessage msg = myAgent.receive();
		if (msg != null) {
			try {
				String content = msg.getContent();
				
				if (content.equals(Constant.GET_AUCTION_LIST)) {
					// Message received. Process it
					System.out.println("Main ReceiveRequest:"+content +"Receive getAuction list");
					System.out.println("Main ReceiveRequest:Get sendername: ----"+msg.getSender().getName());

//					String title = msg.getContent();
					ACLMessage reply = msg.createReply();
//					reply.addReceiver(msg.getSender());
					// The requested book is NOT available for sale.
					//reply.setContent("Seller: " + title);
					reply.setContentObject(mainAgent.getAuctionDescriptions());
					System.out.println("$$$$$$$$$"+mainAgent.getAuctionDescriptions().keys());
					//System.out.println(reply.getContent());
					myAgent.send(reply);
					
				}else if (content.equals(Constant.GET_AUCTION_LIST_AUCTIONNER)){
					// Message received. Process it
					System.out.println("Main ReceiveRequest:"+content +"Receive getAuction list");
					System.out.println("Main ReceiveRequest: Get sendername: ----"+msg.getSender().getName());

					ACLMessage reply = msg.createReply();
					reply.setContentObject(mainAgent.getAuctionDescriptions(msg.getSender()));
					myAgent.send(reply);
					System.out.println("Main ReceiveRequest: Send to " + msg.getSender().getName());
				}
				else {
					AuctionDescription auctionDescription = (AuctionDescription) msg.getContentObject();
					AID auctioneer = msg.getSender();
					if(mainAgent.getAuctionDescriptions().get(auctioneer) != null){
						mainAgent.getAuctionDescriptions().put(auctioneer, auctionDescription);
						mainAgent.addBehaviour(new NotifyChange(mainAgent, auctionDescription.getAuctionner()));
					}else{
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
								auctionDescription.setAuction(newAuction);
								cfp.setContentObject(auctionDescription);
	//							cfp.setConversationId(Constant.ADD_AUCTION);	
								cfp.addReceiver(newAuction);
								mainAgent.getAuctionDescriptions().put(newAuction, auctionDescription);
	//							System.out.println();				
								System.out.println("Main ReceiveRequest:"+newAuction.getName());
								myAgent.send(cfp);
								System.out.println("Main ReceiveRequest:"+auctionDescription.getAuctionner());
								System.out.println("Main ReceiveRequest: Send to Auction cree");
								mainAgent.addBehaviour(new NotifyChange(mainAgent, auctionDescription.getAuctionner()));
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}			
							System.out.println(myAgent.getLocalName()+" CREATED AND STARTED NEW Agent:" + auctionName + " ON CONTAINER "+container.getContainerName());
						} catch (Exception any) {
							any.printStackTrace();
						}
					}
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
