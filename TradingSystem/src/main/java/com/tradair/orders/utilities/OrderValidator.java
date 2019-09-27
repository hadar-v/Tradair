package com.tradair.orders.utilities;

import com.tradair.orders.Order;
import com.tradair.orders.Order.OrderType;

public class OrderValidator {

	public OrderValidator() {
	}
	
	public boolean isValidForAmend(Order source,Order target) {
		boolean valid=true;
		if(!source.getType().equals(OrderType.LIMIT) || source.isOrderChanged(target)) {
			valid=false;
		}
		return valid;
	}

}
