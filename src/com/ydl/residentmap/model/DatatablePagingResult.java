package com.ydl.residentmap.model;

public class DatatablePagingResult {
	
	private String code;
	private Integer draw;
	private Long recordsTotal;
	private Long recordsFiltered;
	private Object data;

	public static DatatablePagingResult create(String code, Integer draw, Long recordsTotal, Long recordsFiltered, Object data
			) {
		return new DatatablePagingResult(code,draw,recordsTotal,recordsFiltered,data);
	}

	private DatatablePagingResult(String code,Integer draw, Long recordsTotal, Long recordsFiltered, Object data) {
		super();
		this.code = code;
		this.draw = draw;
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;
		this.data = data;
	}

	public DatatablePagingResult() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public Long getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(Long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public Long getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(Long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
