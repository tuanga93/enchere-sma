package ifi.auction;


import jade.content.Concept;
import jade.util.leap.Serializable;

public class AuctionDescription implements Concept, Serializable{
	private double minStep;
	private String productName;
	private double initialPrice;
	private String expire;
	private String description;
	//private Set<Good> goods = null;
	public AuctionDescription(String productName, double initialPrice, double minStep, 
			String expire, String description) {
		super();
		this.minStep = minStep;
		this.productName = productName;
		this.initialPrice = initialPrice;
		this.expire = expire;
		this.description = description;
	}
	public double getMinStep() {
		return minStep;
	}
	public void setMinStep(double minStep) {
		this.minStep = minStep;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getInitialPrice() {
		return initialPrice;
	}
	public void setInitialPrice(double initialPrice) {
		this.initialPrice = initialPrice;
	}
	public String getExpire() {
		return expire;
	}
	public void setExpire(String expire) {
		this.expire = expire;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
