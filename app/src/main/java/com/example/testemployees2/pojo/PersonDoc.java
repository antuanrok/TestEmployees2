package com.example.testemployees2.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PersonDoc {
    @SerializedName("docs")
    @Expose
    private List<Person> docs;
    @SerializedName("total")
    @Expose
    private int total;
    @SerializedName("limit")
    @Expose
    private int limit;
    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("pages")
    @Expose
    private int pages;

    public List<Person> getDocs() {
        return docs;
    }

    public void setDocs(List<Person> docs) {
        this.docs = docs;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
