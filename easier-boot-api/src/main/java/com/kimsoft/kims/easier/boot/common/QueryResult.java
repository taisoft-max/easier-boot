package com.kimsoft.kims.easier.boot.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kimi
 * @date 2020/3/28
 */
public class QueryResult<T> {
    private long totalCount;
    private long pageIndex;
    private long pageSize;
    private List<T> records = new ArrayList<>();

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(long pageIndex) {
        this.pageIndex = pageIndex;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }
}
