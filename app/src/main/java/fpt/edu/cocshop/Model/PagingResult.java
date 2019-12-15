package fpt.edu.cocshop.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PagingResult<T> implements Serializable {

    @SerializedName("totalRecords")
    public int totalRecords;
    @SerializedName("pageIndex")
    public Integer pageIndex;
    @SerializedName("pageSize")
    public Integer pageSize;
    @SerializedName("results")
    public List<T> results;

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}