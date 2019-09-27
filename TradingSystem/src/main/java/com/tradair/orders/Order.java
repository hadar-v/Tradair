package com.tradair.orders;

import com.tradair.orders.BidAsk.BidAskEnum;

public class Order {

	public enum OrderType{
		LIMIT,MARKET
	}
	
 
	private long timestamp;
	private boolean isBid;
	private int id;
	private String symbol;
	private double price;
	private long quantity;
	private String venue;
	private OrderType type;
	private BidAskEnum bidAsk;
	public Order(int id, String symbol, double price, long quantity, String venue, OrderType type, BidAskEnum bidAsk) {
		super();
		this.id = id;
		this.symbol = symbol;
		this.price = price;
		this.quantity = quantity;
		this.venue = venue;
		this.type = type;
		this.bidAsk = bidAsk;
		timestamp = System.currentTimeMillis();
		isBid =bidAsk.equals(BidAskEnum.BID); 
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public boolean isBid() {
		return isBid;
	}
	public int getId() {
		return id;
	}
	public String getSymbol() {
		return symbol;
	}
	public String getVenue() {
		return venue;
	}
	public OrderType getType() {
		return type;
	}
	public BidAskEnum getBidAsk() {
		return bidAsk;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Order){
			if(getId()== ((Order)obj).getId() && getSymbol().equals(((Order)obj).getSymbol())) {
				return true;
			}
		}
		return false;
	}
	@Override
	public int hashCode() {
		return getId()+getSymbol().hashCode();
	}
	
	public boolean isOrderChanged(Order order) {
		return !(isBid() == order.isBid() && getId()== order.getId() && getSymbol().equals(order.getSymbol()) && 
				getVenue().equals(order.getVenue()));
	}
}