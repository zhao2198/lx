
package com.lx.core.common.paging;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.io.Serializable;

//import com.github.pagehelper.Page;
//import com.github.pagehelper.PageHelper;

@Data
public class PagingAttribute implements Serializable {

    private static final long serialVersionUID = -8550189961954908201L;

    private int pageNo = 1;

    private int startIndex = 0;
    private int currentPage = 1;

    private int pageSize = 10;

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        setPageNo(currentPage);
    }

    public PagingAttribute() {
    }

    public PagingAttribute(int pageNo) {
        this.pageNo = pageNo > 0 ? pageNo : 1;
    }

    public PagingAttribute(int pageNo, int pageSize) {
        this(pageNo);
        this.pageSize = pageSize;
    }

    public Page toPage() {
        //return PageHelper.startPage(this.pageNo, this.pageSize, true);
        return null;

    }

}
