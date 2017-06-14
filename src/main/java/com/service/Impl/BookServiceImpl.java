package com.service.Impl;

import com.dao.BookDao;
import com.entity.Book;
import com.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 滩涂上的芦苇 on 2017/4/6.
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired

    private BookDao bookDao;

    @PostFilter("hasPermission('read','READ_SECRET') or !filterObject.category.equals('secret')")
    public List<Book>queryBooks()
    {
        return bookDao.findAll();
    }
}
