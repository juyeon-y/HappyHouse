package com.example.demo.board.dto;

import java.util.Date;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class Board {
	private int code, like, comment;
	private String title, content, writer;
	private Date reg_datetime;

	int getCode() {
		return code;
	}

	void setCode(int code) {
		this.code = code;
	}

	public int getComment() {
		return comment;
	}

	public void setComment(int comment) {
		this.comment = comment;
	}

	int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	String getTitle() {
		return title;
	}

	void setTitle(String title) {
		this.title = title;
	}

	String getContent() {
		return content;
	}

	void setContent(String content) {
		this.content = content;
	}

	String getWriter() {
		return writer;
	}

	void setWriter(String writer) {
		this.writer = writer;
	}

	Date getReg_datetime() {
		return reg_datetime;
	}

	void setReg_datetime(Date reg_datetime) {
		this.reg_datetime = reg_datetime;
	}

	@Override
	public String toString() {
		return "Board [code=" + code + ", like=" + like + ", title=" + title + ", content=" + content + ", writer="
				+ writer + ", reg_datetime=" + reg_datetime;
	}

}
