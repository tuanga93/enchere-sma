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
	
	private long expireTime = 0;
	private Auction auctionAgent = null;
	
	public TerminateAuction(Auction a) {		
		super(a, 1000);
		auctionAgent = a;
	}


	@Override
	protected void onTick() {
		SimpleDateFormat datetimeFormatter = new SimpleDateFormat(Constant.DATE_FORMAT);
		Date expireDate;		
		try {
			expireDate = datetimeFormatter.parse(auctionAgent.getAuctionDescription().getExpire());
			expireTime = expireDate.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
System.out.println("TerminateAuction: Expiretime "+ expireTime);
System.out.println("TerminateAuction: Currenttime"+ System.currentTimeMillis());
		if(expireTime <= System.currentTimeMillis()){
		//if(true){
System.out.println("Sending terminate inform");			
			DFAgentDescription template = new DFAgentDescription();
			ServiceDescription serviceDescription = new ServiceDescription();
			serviceDescription.setType(Constant.MAIN_TYPE);		
			template.addServices(serviceDescription);	
			DFAgentDescription[] results = null;
			AID mainAgent = null;
			ACLMessage inform = new ACLMessage(ACLMessage.INFORM);
			try {
				inform.setContentObject(auctionAgent.getAuctionDescription());
				results = DFService.search(myAgent, template);
				if(results != null && results.length > 0){
					//mainAgent = new AID();
					mainAgent = results[0].getName();
				}	
				if(mainAgent != null){
					inform.addReceiver(mainAgent);
					myAgent.send(inform);
				}
				inform.addReceiver(auctionAgent.getAuctionDescription().getAuctionner());
				myAgent.send(inform);
				
				//notify all the bidders
				for (AID bidder : auctionAgent.getBidders()) {																						
					inform.addReceiver(bidder);							
					myAgent.send(inform);				
				}				
				
			} catch (FIPAException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
