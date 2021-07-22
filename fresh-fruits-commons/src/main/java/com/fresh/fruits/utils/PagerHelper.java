package com.fresh.fruits.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: TravelDream
 * @BelongsPackage: com.travel.utils
 * @CreateTime: 2021-05-18 09:40
 * @Description: 分页工具类(封装数据)
 */
public class PagerHelper<T> {

    private Long pageNumber; //当前页码
    private Long pageSize; //页面大小
    private Long pageCount; //总页数
    private Long totalCount; //总条数
    private List<T> records;

    private Long numberStart=1L; //开始的页码
    private Long numberEnd; //结束的页码

    //要携带的额外数据
    private Object pagedata;

    //存值页码序号
    private List<Long> numbers=new ArrayList<Long>();

    public PagerHelper() {
    }

    public PagerHelper(Long pageNumber, Long pageSize, Long pageCount, Long totalCount, List<T> records) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.pageCount = pageCount;
        this.totalCount = totalCount;
        this.records = records;


        //给序号赋值
        if(this.pageCount<=10){
            this.numberStart=1L;
            this.numberEnd=this.pageCount;
        }else{
            this.numberStart=this.pageNumber-4;
            this.numberEnd=this.pageNumber+5;
            if(this.numberStart<1){
                this.numberStart=1L;
                this.numberEnd=10L;
            }
            if(this.numberEnd>this.pageCount){
                this.numberStart=this.pageCount-9;
                this.numberEnd=this.pageCount;
            }
        }

        //给numbers赋值
        for(long i=numberStart;i<=numberEnd;i++){
            numbers.add(i);
        }

    }

    public Long getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Long pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getPageCount() {
        return pageCount;
    }

    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public List<Long> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Long> numbers) {
        this.numbers = numbers;
    }

    public Object getPagedata() {
        return pagedata;
    }

    public void setPagedata(Object pagedata) {
        this.pagedata = pagedata;
    }
}
