package ifi.auction.agent;

import ifi.auction.AuctionDescription;

import java.util.List;

import jade.core.Agent;

public abstract class CommonAuctionAgent extends Agent{
	public abstract List<AuctionDescription> getAuctionList();
}
