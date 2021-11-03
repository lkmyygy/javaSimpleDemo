package com.example.entity;

import java.util.List;

public class Page {
    private int cpage;//当前页
    private int totalpage;//总页数
    private int totalcount;//总条数
    private int pagesize;//每页多少条
    private List<User> users;

    private String title;


    public Page(int cpage, int pagesize, int totalpage, int totalcount, List<User> users){
        this.cpage = cpage;
        this.pagesize = pagesize;
        this.totalpage = totalpage;
        this.totalcount = totalcount;
        this.users = users;

    }


    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }


}

