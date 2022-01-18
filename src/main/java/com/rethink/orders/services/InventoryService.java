package com.rethink.orders.services;

import java.io.IOException;
import java.util.List;

public interface InventoryService {

    List<Integer> getLowStock() throws IOException;

}
