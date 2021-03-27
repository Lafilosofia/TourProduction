package cn.ryan.ttms.common.web;

import java.io.Serializable;

public class ObjectPage implements Serializable {

	private static final long serialVersionUID = 8628014301768299544L;
	/**��ǰҳ��*/

	/**��ǰҳ*/
	private int currentPage=1;
	/**��ҳ��*/
	private int countPage;
	/**������*/
	private int countRows;
	/**��ǰҳ����*/
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
