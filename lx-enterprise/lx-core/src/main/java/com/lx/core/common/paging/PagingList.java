
package com.lx.core.common.paging;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Create Date: 2018年4月28日 下午3:41:15
 *
 * @version: V3.0.1
 * @author: zhao wei
 */
public final class PagingList<T> implements Serializable {

    private static final long serialVersionUID = -2300609512648267589L;

    private List<T> data;
    private Paging paging;

    public PagingList(List<T> data, Paging paging) {
        this.data = data;
        this.paging = paging;
    }

    /**
     * @return the data
     */
    public List<T> getData() {
        if (data == null) {
            data = new ArrayList<T>();
        }
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(List<T> data) {
        this.data = data;
    }

    /**
     * @return the paging
     */
    public Paging getPaging() {
        return paging;
    }

    /**
     * @param paging the paging to set
     */
    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public static PagingList empty(PagingAttribute pageAttr) {
        return new PagingList(new ArrayList<>(), new Paging(0, pageAttr));
    }
}
