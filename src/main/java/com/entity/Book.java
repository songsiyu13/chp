package com.entity;

/**
 * Created by Song on 2017/3/17.
 */
public class Book {
    private int bookID;
    private String bookName;
    private double price;
    private String category;

    public void setBookID(int bookID){this.bookID = bookID;}

    public void setBookName(String bookName){this.bookName = bookName;}

    public void setPrice(double price) {this.price = price;}

    public void setCategory(String category){this.category = category;}

    public int getBookID(){return this.bookID;}

    public String getBookName() { return this.bookName;}

    public double getPrice(){return this.price;}

    public String getCategory(){return this.category;}
}
