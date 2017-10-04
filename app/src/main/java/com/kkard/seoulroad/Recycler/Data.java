package com.kkard.seoulroad.Recycler;

import java.io.Serializable;
import java.util.List;

/**
 * Created by SuGeun on 2017-10-03.
 */

public class Data implements Serializable {

    private int viewType;

    private String textItem;

    private List<ListImageItem> listImageItemList;

    /*
     * SETTER
     */
    public void setListImageItemList(List<ListImageItem> listImageItemList) {
        this.listImageItemList = listImageItemList;
    }

    public void setTextItem(String textItem) {
        this.textItem = textItem;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    /*
     * GETTER
     */
    public List<ListImageItem> getListImageItemList() {
        return listImageItemList;
    }

    public String getTextItem() {
        return textItem;
    }

    public int getViewType() {
        return viewType;
    }
}
