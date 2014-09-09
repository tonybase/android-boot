package io.ganguo.app.basic.core.response;

import java.util.List;

/**
 * 分页接口数据模型
 * <p/>
 * Created by tony on 8/24/14.
 */
public class PageData<T> extends ApiResponse<T> {

    /**
     * 结果集中的条目数目。应该与items.length相等，并作为一个便利属性提供。
     */
    private int currentItemCount;

    /**
     * items结果的数目
     * 未必是data.items数组的大小；如果我们查看的是最后一页，data.items的大小可能小于itemsPerPage
     */
    private int itemsPerPage;

    /**
     * data.items中第一个条目的索引。为了一致，startIndex应从1开始。
     */
    private int startIndex;

    /**
     * 条目的当前页索引。为了一致，pageIndex应从1开始。
     * 分页而计算出来 pageIndex = floor(startIndex / itemsPerPage) + 1
     */
    private int pageIndex;

    /**
     * 当前结果集中的总页数
     * totalPages = ceiling(totalItems / itemsPerPage)
     */
    private int totalItems;

    /**
     * 当前结果集中的总页数
     * totalPages = ceiling(totalItems / itemsPerPage)
     */
    private int totalPages;

    /**
     * 分页数据
     */
    private List<T> items;

    public int getCurrentItemCount() {
        return currentItemCount;
    }

    public void setCurrentItemCount(int currentItemCount) {
        this.currentItemCount = currentItemCount;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "PageData{" +
                "currentItemCount=" + currentItemCount +
                ", itemsPerPage=" + itemsPerPage +
                ", startIndex=" + startIndex +
                ", pageIndex=" + pageIndex +
                ", totalItems=" + totalItems +
                ", totalPages=" + totalPages +
                ", items=" + items +
                '}';
    }
}
