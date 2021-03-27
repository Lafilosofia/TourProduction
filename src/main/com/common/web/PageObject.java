package cn.ryan.ttms.common.web;

import java.io.Serializable;

public class PageObject implements Serializable {
	private static final long serialVersionUID = -8753809986545361268L;

	/**当前页*/
	private int currentPage=1;
	/**总页数*/
	private int countPage;
	/**总行数*/
	private int countRows;
	/**当前页数量*/
	private int pageSize=3;
	/**取下一页起始页记录*/
	private int indexPage;
	
	public int getCountPage(){
		int pages=countRows/pageSize;
		if(countRows%pageSize!=0){
			pages+=1;
		}
		return pages;
	}
	
	public int getIndexPage(){
		return (currentPage-1)*pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getCountRows() {
		return countRows;
	}

	public void setCountRows(int countRows) {
		this.countRows = countRows;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public void setIndexPage(int indexPage) {
		this.indexPage = indexPage;
	}

	public void setCountPage(int countPage) {
		this.countPage = countPage;
	}

	public PageObject(int currentPage, int countPage, int countRows, int pageSize, int indexPage) {
		super();
		this.currentPage = currentPage;
		this.countPage = countPage;
		this.countRows = countRows;
		this.pageSize = pageSize;
		this.indexPage = indexPage;
	}

	public PageObject() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "PageObject [currentPage=" + currentPage + ", countPage=" + countPage + ", countRows=" + countRows
				+ ", pageSize=" + pageSize + ", indexPage=" + indexPage + "]";
	}
	
	
	
}
