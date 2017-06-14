package com.service;

import java.util.ArrayList;

public interface CartService {
    boolean put(int bookId, int number);

    boolean delete(int bookId);

    ArrayList<com.entity.Item> get();

    boolean toOrder(String username);
}
