package com.example.recyleview1.dao;

import java.util.List;

public interface IDao<T> {
    boolean create (T o);
    boolean update (T o);
    boolean delete (T o);
    T findById(int d);
    List<T> findAll();


}
