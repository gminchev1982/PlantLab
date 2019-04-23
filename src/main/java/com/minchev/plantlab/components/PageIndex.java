package com.minchev.plantlab.components;

import org.springframework.stereotype.Component;

@Component
public class PageIndex {
    private int currentPage;
    public final static Integer PAGE_LIMIT = 2;
    public PageIndex() {

    }


    public int getCurrentPage() {
        if (this.currentPage-1<0) currentPage =0; else currentPage= currentPage-1;
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }


}
