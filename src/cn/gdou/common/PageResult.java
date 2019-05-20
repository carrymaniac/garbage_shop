package cn.gdou.common;

import java.util.List;


/**
 * 分页信息封装
 * @author ljgui
 *
 */
public class PageResult {
	
	private List<?> list;
	private Long totalRecord;
	private Integer pageSize;
	private Integer currentPage;
	
	private Integer totalPages;
	/**
	 * 开始页码  
	 */
	private Integer beginIndex;
	/**
	 * 结束页码
	 */
	private Integer endIndex;
	
	
	public PageResult(List<?> list, Long totalRecord, Integer pageSize, Integer currentPage) {
		this.list = list;
		this.totalRecord = totalRecord;
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		
		//总页数计算
		if(totalRecord.intValue() % pageSize==0) {
			this.totalPages = totalRecord.intValue() / pageSize;
		}else {
			this.totalPages = totalRecord.intValue() / pageSize +1;
		}
		
		// 约定中间只显示3个页码
		if(totalPages<=3) {
			this.beginIndex=1;
			this.endIndex = totalPages;
		}else {
			this.beginIndex = currentPage-1;
			this.endIndex = currentPage + 1;
			if(this.beginIndex<1) {
				this.beginIndex=1;
				this.endIndex = 3;
			}
			if(this.endIndex>totalPages) {
				this.endIndex=totalPages;
				this.beginIndex= totalPages-3;
			}
		}
	}


	public List<?> getList() {
		return list;
	}


	public void setList(List<?> list) {
		this.list = list;
	}


	public Long getTotalRecord() {
		return totalRecord;
	}


	public void setTotalRecord(Long totalRecord) {
		this.totalRecord = totalRecord;
	}


	public Integer getPageSize() {
		return pageSize;
	}


	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}


	public Integer getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}


	public Integer getTotalPages() {
		return totalPages;
	}


	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}


	public Integer getBeginIndex() {
		return beginIndex;
	}


	public void setBeginIndex(Integer beginIndex) {
		this.beginIndex = beginIndex;
	}


	public Integer getEndIndex() {
		return endIndex;
	}


	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}
	
	
	

}
