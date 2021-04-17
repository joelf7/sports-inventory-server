package com.freire.services;

import com.freire.entity.Inventory;
import com.freire.entity.Store;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Remote(MainService.class)
public class MainServiceImpl implements MainService{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Inventory> getInventoryList() {
        List<Inventory> inventoryList =  em.createNamedQuery("Inventory.findAll", Inventory.class)
                .getResultList();
        return inventoryList.stream().sorted()
                .collect(Collectors.toList());
    }

    @Override
    public List<Store> getStoreList() {
        List<Store> storeList =  em.createNamedQuery("Store.findAll", Store.class)
                .getResultList();
        return storeList.stream().sorted()
                .collect(Collectors.toList());
    }

    @Override
    public void addInventory(Inventory inventory) {
        em.persist(inventory);
    }

    @Override
    public void updInventory(Inventory inventory) {
        em.merge(inventory);
    }

    @Override
    public void removeInventory(Inventory inventory) {
        Inventory inventoryWithId = em.find(Inventory.class, inventory.getId());
        em.remove(inventoryWithId);
    }

    @Override
    public void addStore(Store store) {
        em.persist(store);
    }

    @Override
    public void removeStore(Store store) {
        Store storeWithId = em.find(Store.class, store.getId());
        em.remove(storeWithId);
    }

    @Override
    public void addInventoryToStore(Store store, Inventory inventory){
        store.getInventoryList().add(inventory);
        inventory.setStore(store);
        em.merge(store);
        em.merge(inventory);
    }

    @Override
    public void delInventoryFromStore(Store store, Inventory inventory){
        store.getInventoryList().remove(inventory);
        inventory.setStore(null);
        em.merge(store);
        em.merge(inventory);
    }
}
