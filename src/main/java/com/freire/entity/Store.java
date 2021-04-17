package com.freire.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@NamedQuery(name = "Store.findAll", query = "SELECT s FROM Store s")
@NamedQuery(name = "Store.getByName", query = "SELECT s FROM Store s where s.storeName = :storeName")
public class Store implements Comparable<Store>, Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Lob
    @Column(length = 50, unique = true)
    private String storeName;

    @Lob
    @Column(length = 50)
    private String location;

    @OneToMany(mappedBy = "store", fetch = FetchType.EAGER)
    private List<Inventory> inventoryList;

    public Store(String storeName, String location){
        this.storeName = storeName;
        this.location = location;
    }

    @Override
    public int compareTo(Store o) {
        return location.compareTo(o.location);
    }
}
