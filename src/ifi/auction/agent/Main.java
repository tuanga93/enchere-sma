package ifi.auction.agent;

import java.util.Hashtable;

import ifi.auction.AuctionDescription;
import ifi.auction.Constant;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

import ifi.auction.behaviour.main.*;

public class Main extends Agent {

	private Hashtable<AID, AuctionDescription> auctionDescriptions = new Hashtable<AID, AuctionDescription>();
	protected void setup() {

		// gui = new ProductList(this);
		// gui.showGui();
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());

		ServiceDescription sd = new ServiceDescription();
		sd.setType(Constant.MAIN_TYPE);
		sd.setName(Constant.MAIN_NAME);
		dfd.addServices(sd);
		try {
			DFService.register(this, dfd);	
		} catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		addBehaviour(new ReceiveRequest(this));
		addBehaviour(new AddAuction());
		addBehaviour(new GetAuction());
	}
	public Hashtable<AID, AuctionDescription> getAuctionDescriptions() {
		return auctionDescriptions;
	}
	public Hashtable<AID, AuctionDescription> getAuctionDescriptions(AID au) {
		Hashtable<AID, AuctionDescription> auctionDescriptionsAID = new Hashtable<AID, AuctionDescription>();
		auctionDescriptionsAID = auctionDescriptions;
		return auctionDescriptionsAID;
	}
	public void setAuctionDescriptions(
			Hashtable<AID, AuctionDescription> auctionDescriptions) {
		this.auctionDescriptions = auctionDescriptions;
	}

}
