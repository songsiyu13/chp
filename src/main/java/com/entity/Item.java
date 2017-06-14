package com.entity;

import java.io.Serializable;

/**
 * Created by Song on 2017/3/17.
 */
public class Item implements Serializable {
    int bookId;
    int number;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Item() {

    }

    public Item(int bookId, int number) {

        this.bookId = bookId;
        this.number = number;
    }
}
