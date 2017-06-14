package com.service;

import com.entity.Book;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 * Created by 滩涂上的芦苇 on 2017/4/6.
 */

public interface BookService {
    List<Book> queryBooks();
}