package com.tomato.framework.demo.bean;

/**
 * Created by zhenfeng on 17/3/24.
 */
public class PageResult {


    private long page;
    private long total;
    private long records;
    private Object rows;


    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getRecords() {
        return records;
    }

    public void setRecords(long records) {
        this.records = records;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }
}
