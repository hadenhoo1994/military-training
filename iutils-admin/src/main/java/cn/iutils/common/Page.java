package cn.iutils.common;

import cn.iutils.common.config.JConfig;
import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.regex.Pattern;

/**
 * 分页工具
 *
 * @author cc
 */
public class Page<T> {

    private int pageNo = 0; // 当前页码
    private int pageSum = 0;// 总页数(从第0页算起)
    private int pageSize = Integer.valueOf(JConfig.getConfig("page.pageSize")); // 页面大小
    private int pageNumber = 1;// 总页数
    private int total = 0; // 总数量

    private int first;// 首页索引
    private int last;// 尾页索引
    private int prev;// 上一页索引
    private int next;// 下一页索引

    private boolean hasFirstPage;// 是否是第一页
    private boolean hasLastPage;// 是否是最后一页

    private T entity;
    private List<T> list = Lists.newArrayList();

    @JSONField(serialize = false)
    private boolean pour = true;// 是否注入
    @JSONField(serialize = false)
    private String key = "";// 查询关键字
    @JSONField(serialize = false)
    private String orderBy = "a.update_date desc"; // 排序字段

    /**
     * 空构造方法
     */
    public Page() {
        initialize();
    }

    /**
     * 构造方法
     *
     * @param pageNo   当前页码
     * @param pageSize 分页大小
     */
    public Page(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        initialize();
    }

    /**
     * 构造方法
     *
     * @param pageNo   当前页码
     * @param pageSize 分页大小
     * @param total    总数
     * @param list     列表
     */
    public Page(int pageNo, int pageSize, int total, List<T> list) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.total = total;
        this.list = list;
        initialize();
    }

    /**
     * 初始化参数
     */
    public void initialize() {
        this.first = 0;
        this.last = (int) (total / (this.pageSize < 1 ? 20 : this.pageSize) + first - 1);
        this.pageNumber = (total + this.pageSize - 1) / pageSize;
        if (this.total % this.pageSize != 0 || this.last == 0) {
            this.last++;
        }

        if (this.last < this.first) {
            this.last = this.first;
        }

        this.pageSum = last;

        if (this.pageNo <= 0) {
            this.pageNo = this.first;
            this.hasFirstPage = true;
        } else {
            this.hasFirstPage = false;
        }

        if (this.pageNo >= this.last) {
            this.pageNo = this.last;
            this.hasLastPage = true;
        } else {
            this.hasLastPage = false;
        }

        if (this.pageNo < this.last - 1) {
            this.next = this.pageNo + 1;
        } else {
            this.next = this.last;
        }

        if (this.pageNo > 1) {
            this.prev = this.pageNo - 1;
        } else {
            this.prev = this.first;
        }

        if (this.pageNo < this.first) {// 如果当前页小于首页
            this.pageNo = this.first;
        }

        if (this.pageNo > this.last) {// 如果当前页大于尾页
            this.pageNo = this.last;
        }
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageSum() {
        return pageSum;
    }

    public void setPageSum(int pageSum) {
        this.pageSum = pageSum;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public int getPrev() {
        return prev;
    }

    public void setPrev(int prev) {
        this.prev = prev;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public boolean isHasFirstPage() {
        return hasFirstPage;
    }

    public void setHasFirstPage(boolean hasFirstPage) {
        this.hasFirstPage = hasFirstPage;
    }

    public boolean isHasLastPage() {
        return hasLastPage;
    }

    public void setHasLastPage(boolean hasLastPage) {
        this.hasLastPage = hasLastPage;
    }

    public List<T> getList() {
        return list;
    }

    public Page<T> setList(List<T> list) {
        this.list = list;
        initialize();
        return this;
    }

    public boolean isPour() {
        return pour;
    }

    public void setPour(boolean pour) {
        this.pour = pour;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public String getOrderBy() {
        // SQL过滤，防止注入
        if (isPour()) {
            String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|" + "(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";
            Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
            if (sqlPattern.matcher(orderBy).find()) {
                return "";
            }
        }
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
