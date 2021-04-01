package com.min.dto;

import java.util.Date;

// DB에 있는 테이블 컬럼 값을 java에서 객체로 다루기 위해 사용
public class BoardDto {
	
	private int seq;
	private String title;
	private String content;
	private Date regdate;
	private String filename;
	private int filesize;
	private String savepath;
	private int download;
	private int id;
	private String username;
	private String password;
	
	
	public BoardDto() {
	}

	public BoardDto(String filename, String savepath) {
		this.filename = filename;
		this.savepath = savepath;
	}
	

	
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getSeq() {
		return seq;
	}




	public void setSeq(int seq) {
		this.seq = seq;
	}




	public String getTitle() {
		return title;
	}




	public void setTitle(String title) {
		this.title = title;
	}




	public String getContent() {
		return content;
	}




	public void setContent(String content) {
		this.content = content;
	}




	public Date getRegdate() {
		return regdate;
	}




	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}




	public String getFilename() {
		return filename;
	}




	public void setFilename(String filename) {
		this.filename = filename;
	}




	public int getFilesize() {
		return filesize;
	}




	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}




	public String getSavepath() {
		return savepath;
	}




	public void setSavepath(String savepath) {
		this.savepath = savepath;
	}




	public int getDownload() {
		return download;
	}




	public void setDownload(int download) {
		this.download = download;
	}




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}



	public BoardDto(int seq, String title, String content, Date regdate, String filename, int filesize, String savepath,
			int download, int id, String username, String password) {
		super();
		this.seq = seq;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.filename = filename;
		this.filesize = filesize;
		this.savepath = savepath;
		this.download = download;
		this.id = id;
		this.username = username;
		this.password = password;
	}

	@Override
	public String toString() {
		return "BoardDto [seq=" + seq + ", title=" + title + ", content=" + content + ", regdate=" + regdate
				+ ", filename=" + filename + ", filesize=" + filesize + ", savepath=" + savepath + ", download="
				+ download + ", id=" + id + "]";
	}






}
