package com.is4tech.inventory.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
@EnableBinding(SearchSink.class)
public class Receiver {

	@Autowired
	InventoryComponent inventoryComponent;

	@ServiceActivator(inputChannel = SearchSink.INVENTORY_Q)
	public void accept(Map<String,Object> map){
        inventoryComponent.updateInventory((String) map.get("EVENT"), (String) map.get("CATEGORY"), (Integer) map.get("QUANTITY"));
	}
}

interface SearchSink {

	String INVENTORY_Q = "inventoryQ";

	@Input("inventoryQ")
	MessageChannel inventoryQ();
}

	