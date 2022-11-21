package com.example.demo.board.dto;

public class BoardLike {
	private int id, board_code, member_id;

	public BoardLike() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BoardLike(int id, int board_code, int member_id) {
		super();
		this.id = id;
		this.board_code = board_code;
		this.member_id = member_id;
	}

	int getId() {
		return id;
	}

	void setId(int id) {
		this.id = id;
	}

	int getBoard_code() {
		return board_code;
	}

	void setBoard_code(int board_code) {
		this.board_code = board_code;
	}

	int getMember_id() {
		return member_id;
	}

	void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	@Override
	public String toString() {
		return "BoardLike [id=" + id + ", board_code=" + board_code + ", member_id=" + member_id + "]";
	}

}
