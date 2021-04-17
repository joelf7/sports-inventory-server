package com.freire.services;

import com.freire.entity.Inventory;
import com.freire.entity.Store;

import java.util.List;

public interface MainService {
    List<Inventory> getInventoryList();
    List<Store> getStoreList();

    void addInventory(Inventory inventory);
    void updInventory(Inventory inventory);
    void removeInventory(Inventory inventory);
    void addStore(Store store);
    void removeStore(Store store);
    void addInventoryToStore(Store store, Inventory inventory);
    void delInventoryFromStore(Store store, Inventory inventory);
}
