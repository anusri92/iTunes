
package com.altimetrik.itunes.model;

import java.util.List;

import com.altimetrik.itunes.dbController.entity.AlbumResultsEntity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Album {

    @SerializedName("resultCount")
    @Expose
    private Integer resultCount;
    @SerializedName("results")
    @Expose
    private List<AlbumResultsEntity> results = null;

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public List<AlbumResultsEntity> getResults() {
        return results;
    }

    public void setResults(List<AlbumResultsEntity> results) {
        this.results = results;
    }

}
