package kr.or.ddit.vo;

import java.util.List;

public class PageVO<T> {
	private int totalRecord;		
	private int totalPage;			
	private int currentPage;		
	private int screenSize = 10;	
	private int blockSize = 5;		
	private int startRow;			
	private int endRow;				
	private int startPage;			
	private int endPage;			
	private List<T> dataList;			
	private String searchType;		
	private String searchWord;	
	
	public PageVO() {
		
	}
	
	public PageVO(int screenSize, int blockSize) {
		super();
		this.screenSize = screenSize;
		this.blockSize = blockSize;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		// 총 페이지 수 구하기
		// ceil 올림
		totalPage = (int)Math.ceil(totalRecord / (double)screenSize);  
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		      this.currentPage = currentPage;         //현재 페이지
		      endRow = currentPage * screenSize;      // 끝 row = 현재 페이지 * 한 페이지당 게시글 수
		      startRow = endRow - (screenSize - 1);   // 시작 row = 끝 row - (한 페이지당 게시글 수 - 1)
		      // 마지막 페이지 = (현재 페이지 + (페이지 블록 사이즈 - 1)) / 페이지 블록 사이즈 * 페이지 블록 사이즈
		      endPage = (currentPage + (blockSize - 1)) / blockSize * blockSize;
		      startPage = endPage - (blockSize - 1);      // 시작 페이지 = 마지막 페이지 - (페이지 블록 사이즈 - 1)
		  
	}

	public int getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(int screenSize) {
		this.screenSize = screenSize;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public String getPagingHTML() {
		StringBuffer html = new StringBuffer();
		
		// 부투스트랩을 이용하던 여러 css요소를 이용해서 페이징 UI를 구현하는 곳입니다!
		html.append("<ul class='pagination justify-content-center'>");
		
		if(startPage > 1) {			
			html.append("<li class='page-item'><a href='' class='page-link' data-page='"		
					+ (startPage - blockSize) + "'>Prev</a></li>");
		}
		
		for(int i = startPage; i <= (endPage < totalPage ? endPage : totalPage); i++) {
			if(i == currentPage) {
				html.append("<li class='page-item active'><span class='page-link'>"
						+ i + "</span></li>");
			}else {
				html.append("<li class='page-item'><a class='page-link' data-page='"
						+ i + "'>" + i + "</a></li>");
			}
		}
		
		if(endPage < totalPage) {
			html.append("<li class='page-item'><a href='' class='page-link' data-page='"
					+ (endPage + 1) + "'>Next</a></li>");
		}
		
		
		html.append("</ul>");
		return html.toString();
	}
	
}

