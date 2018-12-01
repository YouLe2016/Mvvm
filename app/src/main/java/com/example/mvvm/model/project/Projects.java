package com.example.mvvm.model.project;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.example.mvvm.model.gson.GsonFactory;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * 时间：2018/11/28 14:31
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author WangYoule
 * @qq 270628297
 */
@Entity(tableName = "projects")
public class Projects {


    /**
     * total_count : 1326
     * incomplete_results : false
     * items : []
     */

    @SerializedName("total_count")
    private int totalCount;
    @SerializedName("incomplete_results")
    private boolean incompleteResults;
    @SerializedName("items")

    @Ignore
    private List<ProjectItem> items;

    /* ******** DB ******** */
    @PrimaryKey
    private int page;

    @ColumnInfo(name = "items")
    private String itemsJson;

    public void itemsToJson() {
//        if (items == null) {
//            items = Collections.emptyList();
//        }
        itemsJson = GsonFactory.getGson().toJson(items);
    }

    public void itemsFromJson() {
        items = GsonFactory.getGson().fromJson(itemsJson,new TypeToken<List<ProjectItem>>(){}.getType());
    }

    public String getItemsJson() {
        return itemsJson;
    }

    public void setItemsJson(String itemsJson) {
        this.itemsJson = itemsJson;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isIncompleteResults() {
        return incompleteResults;
    }

    public void setIncompleteResults(boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    public List<ProjectItem> getItems() {
        return items;
    }

    public void setItems(List<ProjectItem> items) {
        this.items = items;
    }
}
