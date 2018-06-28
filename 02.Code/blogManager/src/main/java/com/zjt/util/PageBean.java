package com.zjt.util;

/**
 * Created by Mr.K on 2018/6/11.
 */
public class PageBean {
    public PageBean(int page ,int pageSize) {
        super();
        this.page=page;
        this.pagesize=pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getStart() {
        return (page - 1) * pagesize;
    }

    public void setStart(int start) {
        this.start = start;
    }

    private int page;
    private int pagesize;
    private int start;
}
