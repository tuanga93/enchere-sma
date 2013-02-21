package ifi.auction;

import java.util.Set;

import jade.content.Concept;

public class Auction implements Concept{
	private int minStep;
	private Set<Good> goods = null;
	public Auction(Set<Good> goods){
		this.goods = goods;
	}
	
	public Auction(Set<Good> goods, int minStep){
		this.goods = goods;
		this.minStep = minStep;
	}

	public int getMinStep() {
		return minStep;
	}

	public void setMinStep(int minStep) {
		this.minStep = minStep;
	}

	public Set<Good> getGoods() {
		return goods;
	}

	public void setGoods(Set<Good> goods) {
		this.goods = goods;
	}	
}
