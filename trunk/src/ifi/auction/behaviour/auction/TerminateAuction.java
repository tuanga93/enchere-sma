package ifi.auction.behaviour.auction;

import java.io.IOException;
import java.security.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ifi.auction.agent.Auction;
import ifi.auction.behaviour.auction.*;
import ifi.auction.AuctionDescription;
import ifi.auction.Constant;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class TerminateAuction extends TickerBehaviour {

	private AuctionDescription auctionDescription = null;
	
	private List<AID> bidders;
	
	private long expireTime = 0;
	
	public TerminateAuction(Agent a, List<AID> b, AuctionDescription auctionDes) {		
		super(a, 1);
		bidders = b;
		auctionDescription= auctionDes;
		
		SimpleDateFormat datetimeFormatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		Date expireDate;
		try {
			expireDate = datetimeFormatter.parse(auctionDescription.getExpire());
			expireTime = expireDate.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}


	@Override
	protected void onTick() {
		if(expireTime >= System.currentTimeMillis()){
			DFAgentDescription template = new DFAgentDescription();
			ServiceDescription serviceDescription = new ServiceDescription();
			serviceDescription.setType(Constant.MAIN_TYPE);		
			template.addServices(serviceDescription);		
			DFAgentDescription[] results = null;
			AID mainAgent = null;
			try {
				results = DFService.search(myAgent, template);
			} catch (FIPAException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			if(results != null && results.length > 0){
				//mainAgent = new AID();
				mainAgent = results[0].getName();
			}			
			ACLMessage inform = new ACLMessage(ACLMessage.INFORM);
			try {
				inform.setContentObject(auctionDescription);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			inform.addReceiver(auctionDescription.getAuctionner());
			myAgent.send(inform);
			
			if(mainAgent != null){
				inform.addReceiver(mainAgent);
				myAgent.send(inform);
			}
			//notify all the bidders
			for (AID bidder : bidders) {																						
				inform.addReceiver(bidder);							
				myAgent.send(inform);				
			}				
		}
	}

}
