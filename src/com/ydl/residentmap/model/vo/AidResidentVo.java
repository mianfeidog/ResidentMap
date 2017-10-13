package com.ydl.residentmap.model.vo;

import com.ydl.residentmap.model.AidResident;

/**
 * Created by 小强 on 2017/7/21.
 */
public class AidResidentVo extends AidResident {
    private Integer page;
    private Integer size;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
