package cn.ryan.ttms.common.web;

import java.io.Serializable;

public class ObjectPage implements Serializable {

	private static final long serialVersionUID = 8628014301768299544L;
	/**当前页面*/

	/**当前页*/
	private int currentPage=1;
	/**总页数*/
	private int countPage;
	/**总行数*/
	private int countRows;
	/**当前页数量*/
	private int pageSize=3;
	public int getCurrentPage() {
		return (currentPage-1)*pageSize;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getCountPage() {
		int pages=countRows/pageSize;
		if(countRows%pageSize!=0){
			pages+=1;
		}
		return pages;
	}
	public void setCountPage(int countPage) {
		this.countPage = countPage;
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
	@Override
	public String toString() {
		return "ObjectPage [currentPage=" + currentPage + ", countPage=" + countPage + ", countRows=" + countRows
				+ ", pageSize=" + pageSize + "]";
	}
	
	
	
	
}
