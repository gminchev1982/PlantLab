package com.minchev.plantlab.components;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class SortPage {
    private  String sort;
    private Sort sortBy;

    public SortPage() {
    }


    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
        setSortANDSortBy();

    }

    public Sort getSortBy() {
        return sortBy;
    }

    public void setSortBy(Sort sortBy) {
        this.sortBy = sortBy;
    }

    private void setSortANDSortBy (){

        if (this.sort.indexOf(',')==-1) this.sort+=",asc";

        String[]  sortType = this.sort.split(",");
       if (sortType.length>0 && sortType[1].equals("asc")){
           this.sortBy = new Sort(Sort.Direction.ASC, sortType[0]);
        }
        if (sortType.length>0 && sortType[1].equals("desc")){
            this.sortBy= new Sort(Sort.Direction.DESC, sortType[0]);
        }

    }


}
