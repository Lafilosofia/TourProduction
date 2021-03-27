package cn.ryan.ttms.common.web;

import java.io.Serializable;

public class PageObject implements Serializable {
	private static final long serialVersionUID = -8753809986545361268L;

	/**��ǰҳ*/
	private int currentPage=1;
	/**��ҳ��*/
	private int countPage;
	/**������*/
	private int countRows;
	/**��ǰҳ����*/
	private int pageSize=3;
	/**ȡ��һҳ��ʼҳ��¼*/
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
