package com.tradair.orders;

import static org.junit.Assert.*;

import org.junit.Test;

import com.tradair.orders.BidAsk.BidAskEnum;
import com.tradair.orders.Order.OrderType;
import com.tradair.orders.exception.OrderExistException;

public class OrderBookTest {

	@Test
	public void test() throws OrderExistException {
		OrderBook ob = new OrderBook();
		Order o1 = new Order(1,"APPL",10,100,"DONALD1",OrderType.LIMIT,BidAskEnum.BID);
		Order o2 = new Order(2,"APPL",11,100,"DONALD1",OrderType.LIMIT,BidAskEnum.BID);
		Order o3 = new Order(3,"APPL",9,100,"DONALD1",OrderType.LIMIT,BidAskEnum.BID);
		
		
		Order o4 = new Order(4,"APPL",12,100,"DONALD1",OrderType.LIMIT,BidAskEnum.ASK);
		Order o5 = new Order(5,"APPL",13,100,"DONALD1",OrderType.LIMIT,BidAskEnum.ASK);
		Order o6 = new Order(6,"APPL",15,100,"DONALD1",OrderType.LIMIT,BidAskEnum.ASK);
		Order o7 = new Order(7,"APPL",17,100,"DONALD1",OrderType.LIMIT,BidAskEnum.ASK);
		
		ob.add_order(o1);
		ob.add_order(o2);
		ob.add_order(o3);
		ob.add_order(o4);
		ob.add_order(o5);
		ob.add_order(o6);
		ob.add_order(o7);
		
		assertEquals(11,ob.getBid().getOrder(0).getPrice(),0);
		
		assertEquals(10,ob.getBid().getOrder(1).getPrice(),0);
		assertEquals(9,ob.getBid().getOrder(2).getPrice(),0);
		
		assertEquals(12,ob.getAsk().getOrder(0).getPrice(),0);
		assertEquals(13,ob.getAsk().getOrder(1).getPrice(),0);
		assertEquals(15,ob.getAsk().getOrder(2).getPrice(),0);
		assertEquals(17,ob.getAsk().getOrder(3).getPrice(),0);
	}

}
