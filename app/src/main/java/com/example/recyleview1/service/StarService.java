package com.example.recyleview1.service;

import com.example.recyleview1.beans.Star;
import com.example.recyleview1.dao.IDao;

import java.util.ArrayList;
import java.util.List;

public class StarService implements IDao<Star> {
    private List<Star> stars;
    private  static  StarService instance;

    public StarService() {

        this.stars = new ArrayList<>();
    }
    public static StarService getInstance()
    { if(instance == null)
        instance = new StarService();
        return instance;
    }

    @Override
    public boolean create(Star o) {
        return stars.add(o);
    }

    @Override
    public boolean update(Star o) {
        for(Star s:stars){
            if(s.getId()==o.getId()){
                s.setStar(o.getStar());
                s.setImg(o.getImg());
                s.setName(o.getName());
            }

        }
        return true;
    }

    @Override
    public boolean delete(Star o) {
        return stars.remove(o);
    }

    @Override
    public Star findById(int d) {
        for(Star s:stars){
            if(s.getId()==d)
                return s;
        }
        return null;
    }

    @Override
    public List<Star> findAll() {
        return stars;
    }}

