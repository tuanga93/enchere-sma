package ifi.auction.agent;

import java.util.ArrayList;
import java.util.List;

import ifi.auction.AuctionDescription;
import ifi.auction.Constant;
import ifi.auction.behaviour.auctioneer.SendAuctionRequest;
import ifi.auction.gui.AuctionListGUI;
import ifi.auction.gui.AuctioneerGui;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class Auctioneer extends CommonAuctionAgent{
//	private AID[] recepteurAgents;
	private AuctionListGUI gui;
	protected void setup(){
		
		gui = new AuctionListGUI(this);
		gui.showGui();
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		
		ServiceDescription sd = new ServiceDescription();
		sd.setType(Constant.AUCTIONEER_TYPE);
		sd.setName(Constant.AUCTIONEER_NAME);
		dfd.addServices(sd);
		//DFAgentDescription[] results = DFService.search(, dfd);
		
		try {
			DFService.register(this, dfd);
		} catch (FIPAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		addBehaviour(new RequestPerformer());
//		addBehaviour(new Reponse());
		
		//test
//		Auction au = new Auction("Bookk", 10000, 100, "123456", "XXX");
//		addAuction(au);
		
	}
	public void addAuction(AuctionDescription au){
		addBehaviour(new SendAuctionRequest(au));		
	}
	public List<AuctionDescription> getAuctionList(){
		AuctionDescription a = new AuctionDescription("xxx", 1234, 124, "12346", "XXXXXXXXX");
		List<AuctionDescription> b = new ArrayList<AuctionDescription>();
		b.add(a);
		return b;
	}
}
