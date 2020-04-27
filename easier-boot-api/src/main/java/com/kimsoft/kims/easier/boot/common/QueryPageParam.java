package com.kimsoft.kims.easier.boot.common;

/**
 * @author Kimi
 * @date 2020/3/28
 */
public class QueryPageParam {
    private int pageSize = 20;
    private int pageIndex = 1;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
}
