package com.example.chapter3.homework;

import java.io.Serializable;
import java.util.ArrayList;

public class Item implements Serializable {
    private static final long serialVersionUID = -6099312954099962806L;
    private String title;
    private String body;

    public Item(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public static ArrayList<Item> getItems() {
        ArrayList<Item> items = new ArrayList<Item>();
        items.add(new Item("Item 1", "This is the first item"));
        items.add(new Item("Item 2", "This is the second item"));
        items.add(new Item("Item 3", "This is the third item"));
        items.add(new Item("Item 4", "This is the forth item"));
        items.add(new Item("Item 5", "This is the fifth item"));
        items.add(new Item("Item 6", "This is the sixth item"));
        return items;
    }

    @Override
    public String toString() {
        return getTitle();
    }

}
