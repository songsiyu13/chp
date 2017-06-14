package com.dao;

import com.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Song on 2017/3/17.
 */
@Repository(value="bookDao")
public interface BookDao {
    public Book findBookByID(int bookID);
    public List<Book> findAll();
}
