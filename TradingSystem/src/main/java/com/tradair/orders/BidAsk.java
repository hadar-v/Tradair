package com.tradair.orders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Collectors;

import com.tradair.orders.exception.ModifyOrderException;
import com.tradair.orders.exception.OrderExistException;

public class BidAsk {
	private BidAskEnum bidAsk;
	
	public enum BidAskEnum{
		BID,ASK
	}
	private ArrayList<Order> orderList = new ArrayList<Order>();
	private ArrayList<Order> sortOrderList = new ArrayList<Order>();
	private HashMap<Order,Integer> mapOrder = new HashMap<Order,Integer>();
	private boolean needToSort=false;
	
	public BidAsk(BidAskEnum bidAsk) {
		this.bidAsk = bidAsk;
	}
	
	public void addOrder(Order order) throws OrderExistException {
		if(!mapOrder.containsKey(order)) {
			orderList.add(order);
			mapOrder.put(order,orderList.size()-1);
			needToSort=true;
		}else {
			throw new OrderExistException("Order exists in the system");
		}
	}
	
	public void modifyOrder(Order order) throws OrderExistException {
		if(mapOrder.containsKey(order)) {
			int orderIndex = mapOrder.get(order);
			Order sourceOrder = orderList.get(orderIndex);
			if(!sourceOrder.isOrderChanged(order)) {
				sourceOrder.setQuantity(order.getQuantity());
				needToSort=true;
			}else {
				throw new ModifyOrderException("The order cannot be modified");
			}
			
		}else {
			throw new OrderExistException("Order not exist in the system");
		}
	}
	
	public void deleteOrder(Order order) throws OrderExistException {
		if(mapOrder.containsKey(order)) {
			int orderIndex = mapOrder.get(order);			//
			if(orderIndex<orderList.size()-1) {
				Collections.swap(orderList, orderIndex, orderList.size()-1);
				mapOrder.remove(order);
				orderList.remove(orderList.size()-1);
				Order replacedOrder = orderList.get(orderIndex);
				mapOrder.put(replacedOrder, orderIndex);
			}else {
				mapOrder.remove(order);
				orderList.remove(orderIndex);
			}
			needToSort=true;
		}else {
			throw new OrderExistException("Order not exist in the system");
		}		
	}
	
	private void sortOrders() {
		if(needToSort) {
			sortOrderList = (ArrayList<Order>) orderList.stream().collect(Collectors.toList());
			if(bidAsk.equals(BidAskEnum.BID)) {
				sortOrderList.sort(Comparator.comparingDouble(Order::getPrice).reversed());
			}else if(bidAsk.equals(BidAskEnum.ASK)) {
				sortOrderList.sort(Comparator.comparingDouble(Order::getPrice));
			}
			needToSort = false;
		}
	}
	
	public ArrayList<Order> getOrders() {
		sortOrders();
		return sortOrderList;
	}
	
	public Order getOrder(int index) {
		sortOrders();
		return sortOrderList.get(index);
	}
	
	public ArrayList<Order> getOrdersByPrice(double price) {
		ArrayList<Order> result = (ArrayList<Order>) orderList.stream().filter((order  -> price == order.getPrice())).
				collect(Collectors.toList());
		return result;
	}
}