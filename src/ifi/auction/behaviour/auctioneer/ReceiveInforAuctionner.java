package ifi.auction.behaviour.auctioneer;

import ifi.auction.agent.*;
import ifi.auction.AuctionDescription;
import ifi.auction.agent.Bidder;
import ifi.auction.gui.BidGui;
import ifi.auction.gui.MyAuctionListGUI;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JOptionPane;

public class ReceiveInforAuctionner extends CyclicBehaviour{

	private AuctionDescription auctionDescription;
	private Auctioneer auctioneer;
	
	public ReceiveInforAuctionner(Auctioneer auctionneer) {
		super();
		this.auctioneer = auctionneer;
	
	}
	@Override
	public void action() {
		ACLMessage msg = myAgent.receive();
		if (msg != null) {
			System.out.println("Ationneer ReceiveInforAuctionner: Receive information from "+ msg.getSender().getName());
		} else {
			block();
		}
	}
}
