/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class Cart {

    private List<Item> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public void add(Item it, int quantity) {
        for (Item x : items) {
            if (it.getId().equalsIgnoreCase(x.getId())) {
                x.setQuantity(x.getQuantity() + quantity);
                return;
            }
        }
        items.add(it);
    }

    public void remove(String id) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equalsIgnoreCase(id)) {
                items.remove(i);
                for (int j = i; j < items.size(); j++) {
                    items.get(j).setNumber(items.get(j).getNumber() - 1);
                }
                break;
            }
        }
    }

    public double getTotalAmount() {
        double total = 0;
        for (Item x : items) {
            total += x.getQuantity() * x.getPrice();
        }
        return total;
    }

    public List<Item> getItems() {
        return items;
    }

    public void update(String id, int quantity) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equalsIgnoreCase(id)) {
                items.get(i).setQuantity(quantity);
            }
        }
    }
}
