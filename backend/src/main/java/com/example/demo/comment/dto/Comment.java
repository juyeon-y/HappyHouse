package com.example.demo.comment.dto;

public class Comment {
	private int id, board_code, member_id, likes, liked;
	private String comment, writer;

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(int id, int board_code, int member_id, int likes, int liked, String comment, String writer) {
		super();
		this.id = id;
		this.board_code = board_code;
		this.member_id = member_id;
		this.likes = likes;
		this.liked = liked;
		this.comment = comment;
		this.writer = writer;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getLiked() {
		return liked;
	}

	public void setLiked(int liked) {
		this.liked = liked;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBoard_code() {
		return board_code;
	}

	public void setBoard_code(int board_code) {
		this.board_code = board_code;
	}

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", board_code=" + board_code + ", member_id=" + member_id + ", comment=" + comment
				+ ", writer=" + writer + "]";
	}

}
