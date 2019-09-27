package com.tradair.orders;

import com.tradair.orders.BidAsk.BidAskEnum;
import com.tradair.orders.exception.OrderExistException;

public class OrderBook {
	
	BidAsk bid = new BidAsk(BidAskEnum.BID);
	BidAsk ask = new BidAsk(BidAskEnum.ASK);
	public OrderBook() {
	}
	
	private BidAsk getBidAsk(Order order) {
		if(order.getBidAsk().equals(BidAskEnum.BID)) {
			return bid;
		}else {
			return ask; 
		}
	}
	
	public void add_order(Order order) throws OrderExistException {
		BidAsk bidAsk= getBidAsk(order);
		bidAsk.addOrder(order);
	}

	public void modify_order(Order order) throws OrderExistException {
		BidAsk bidAsk= getBidAsk(order);
		bidAsk.modifyOrder(order);
	}	
	
	public void delete_order(Order order) throws OrderExistException   {
		BidAsk bidAsk= getBidAsk(order);
		bidAsk.deleteOrder(order);
	}	
	
	public void process_order(Order order) {
		
	}
	
	public Order get_best_bid() {
		return bid.getOrder(0);
	}
	
	public Order get_best_offer() {
		return ask.getOrder(0);
	}

	public BidAsk getBid() {
		return bid;
	}

	public BidAsk getAsk() {
		return ask;
	}
}
