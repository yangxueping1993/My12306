package com.neuedu.util;

public class PageBean {
	
	//记录总数
	private int recordCnt;
	//每页记录数
	private int pageSize;
	//分页数
	private int pageCnt;
	//当前页码（从1开始）
	private int curPage;
	
	/**
	 * 
	 * @param recordCnt 记录总数
	 * @param pageSize  每页大小
	 * @param curPage   当前页码（从1开始）
	 */
	public PageBean(int pageSize, int curPage) {
		super();
		this.pageSize = pageSize;
		this.curPage = curPage;
	}
	
	public int getStartRowNum(){
		return (curPage-1)*pageSize+1;
	}
	
	public int getEndRowNum(){
		return curPage*pageSize;
	}

	public int getRecordCnt() {
		return recordCnt;
	}

	public void setRecordCnt(int recordCnt) {
		this.recordCnt = recordCnt;
		if(this.recordCnt%this.pageSize==0){
			this.pageCnt=this.recordCnt/this.pageSize;
		}else{
			this.pageCnt=this.recordCnt/this.pageSize+1;
		}
		//this.pageCnt=(int)Math.ceil(this.recordCnt/this.pageSize);
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCnt() {
		return pageCnt;
	}

	public void setPageCnt(int pageCnt) {
		this.pageCnt = pageCnt;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	
	

}
