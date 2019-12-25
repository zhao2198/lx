
package com.lx.core.common.paging;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * Create Date: 2018年4月28日 下午3:40:42
 *
 * @version: V3.0.1
 * @author: zhao wei
 */
public final class Paging implements Serializable {

    private static final long serialVersionUID = -5648572423721834076L;
    @JsonIgnore
    private int firstPage;
    @JsonIgnore
    private int lastPage;
    @JsonIgnore
    private int nextPage;
    @JsonIgnore
    private int prevPage;

    private int currentPage;
    private int totalPage;
    private long rowCount;
    private int pageSize;

    @JsonIgnore
    private boolean hasNext;
    @JsonIgnore
    private boolean hasPrev;
    @JsonIgnore
    private boolean hasFirst;
    @JsonIgnore
    private boolean hasLast;
    @JsonIgnore
    private int startIndex;

    public Paging(int pageSize, int currentPage) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public Paging(long totalCount, PagingAttribute pageRequest) {
        this.rowCount = totalCount;
        this.pageSize = pageRequest.getPageSize();
        if (pageRequest.getStartIndex() > 0) {
            this.startIndex = pageRequest.getStartIndex();
            this.currentPage = pageRequest.getStartIndex() / this.pageSize + 1;
        } else {
            this.currentPage = pageRequest.getPageNo();
        }

        this.totalPage = (int) (this.rowCount % this.pageSize == 0 ? this.rowCount / this.pageSize : this.rowCount / this.pageSize + 1);
        if (this.currentPage > totalPage)
            this.currentPage = totalPage;
        if (this.currentPage < 1)
            this.currentPage = 1;
        if (this.totalPage > 0) {
            this.hasFirst = true;
            this.firstPage = 1;
        }
        if (this.currentPage > 1) {
            this.hasPrev = true;
            this.prevPage = this.currentPage - 1;
        }
        if (this.totalPage > 0 && this.currentPage < this.totalPage) {
            this.hasNext = true;
            this.nextPage = this.currentPage + 1;
        }
        if (this.totalPage > 0) {
            this.hasLast = true;
            this.lastPage = this.totalPage;
        }
    }

    public Paging(long rowCount, int pageSize, int currentPage) {
        this.rowCount = rowCount;

        this.pageSize = pageSize;
        this.totalPage = (int) (this.rowCount % pageSize == 0 ? this.rowCount / pageSize : this.rowCount / pageSize + 1);

        if (this.totalPage > 0) {
            this.hasFirst = true;
            this.firstPage = 1;
        }

        if (currentPage < 1) {
            currentPage = 1;
        } else if (currentPage > this.totalPage && this.totalPage > 0) {
            currentPage = this.totalPage;
        }

        this.currentPage = currentPage;
        if (this.currentPage > 1) {
            this.hasPrev = true;
            this.prevPage = this.currentPage - 1;
        }
        if (this.totalPage > 0 && this.currentPage < this.totalPage) {
            this.hasNext = true;
            this.nextPage = this.currentPage + 1;
        }
        if (this.totalPage > 0) {
            this.hasLast = true;
            this.lastPage = this.totalPage;
        }
    }

    /**
     * @return the firstPage
     */
    public int getFirstPage() {
        return firstPage;
    }

    /**
     * @param firstPage the firstPage to set
     */
    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    /**
     * @return the lastPage
     */
    public int getLastPage() {
        return lastPage;
    }

    /**
     * @param lastPage the lastPage to set
     */
    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    /**
     * @return the nextPage
     */
    public int getNextPage() {
        return nextPage;
    }

    /**
     * @param nextPage the nextPage to set
     */
    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    /**
     * @return the prevPage
     */
    public int getPrevPage() {
        return prevPage;
    }

    /**
     * @param prevPage the prevPage to set
     */
    public void setPrevPage(int prevPage) {
        this.prevPage = prevPage;
    }

    /**
     * @return the currentPage
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * @param currentPage the currentPage to set
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * @return the totalPage
     */
    public int getTotalPage() {
        return totalPage;
    }

    /**
     * @param totalPage the totalPage to set
     */
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    /**
     * @return the rowCount
     */
    public long getRowCount() {
        return rowCount;
    }

    /**
     * @param rowCount the rowCount to set
     */
    public void setRowCount(long rowCount) {
        this.rowCount = rowCount;
    }

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;

    }

    /**
     * @return the hasNext
     */
    public boolean isHasNext() {
        return hasNext;
    }

    /**
     * @param hasNext the hasNext to set
     */
    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    /**
     * @return the hasPrev
     */
    public boolean isHasPrev() {
        return hasPrev;
    }

    /**
     * @param hasPrev the hasPrev to set
     */
    public void setHasPrev(boolean hasPrev) {
        this.hasPrev = hasPrev;
    }

    /**
     * @return the hasFirst
     */
    public boolean isHasFirst() {
        return hasFirst;
    }

    /**
     * @param hasFirst the hasFirst to set
     */
    public void setHasFirst(boolean hasFirst) {
        this.hasFirst = hasFirst;
    }

    /**
     * @return the hasLast
     */
    public boolean isHasLast() {
        return hasLast;
    }

    /**
     * @param hasLast the hasLast to set
     */
    public void setHasLast(boolean hasLast) {
        this.hasLast = hasLast;
    }


}
