package ifi.auction;

import jade.content.Concept;

public class Good implements Concept {
    private int initialPrice;
    private int availableCount;
    private int reservationPrize;
    
    public Good(){}
    
    public Good(int initialPrice, int availableCount, int reservationPrize) {
        this.initialPrice = initialPrice;
        this.availableCount = availableCount;
        this.reservationPrize = reservationPrize;
    }
    public Good(int initialPrice){
    	this.initialPrice = initialPrice;
    	this.availableCount = 1;
    	this.reservationPrize = 0;
    }
    
    public int getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(int availableCount) {
        this.availableCount = availableCount;
    }

    public int getReservationPrize() {
        return reservationPrize;
    }

    public void setReservationPrize(int reservationPrize) {
        this.reservationPrize = reservationPrize;
    }

    public int getInitialPrize() {
        return initialPrice;
    }

    public void setInitialPrize(int initialPrize) {
        this.initialPrice = initialPrize;
    }

}
