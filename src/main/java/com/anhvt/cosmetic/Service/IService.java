package com.anhvt.cosmetic.Service;

import com.anhvt.cosmetic.Entity.Order;

public interface IService<T>{

//    public Iterable<T> findAll();
//
//    public T findByID(Long id);

    public Order save(T t);

//    public void update(Long id, T t);
//
//    public boolean delete(Long id);


}
