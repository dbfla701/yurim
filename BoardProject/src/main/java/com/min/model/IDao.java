package com.min.model;

import java.util.List;
import java.util.Map;

import com.min.dto.BoardDto;

public interface IDao {
	
	// 게시판 목록 전체 보기
		public List<BoardDto> All_Board();
		
		// 로그인
		public boolean login(BoardDto dto);
		
		// 회원가입
		public boolean joinUser(BoardDto dto);
		
		// 게시글 상세 보기
		public BoardDto Detail_Board(int seq);
		
		// 게시글 작성
		public boolean write(BoardDto dto);
		
		// 게시글 수정
		public boolean update(BoardDto dto);
		
		// 게시글 삭제
		public boolean delete(int seq);
		
		// 게시물 총 갯수
		public int count();
		
		// 게시물 목록 + 페이징
		public List<BoardDto> listPage(int displayPost, int postNum);
		
		// 페이지 정렬 날짜 최신순
		public List<BoardDto> regdateRow(int displayPost, int postNum);
		
		// 페이징 정렬 제목순
		public List<BoardDto> titleRow(int displayPost, int postNum);
		
		// 페이징 seq순
		public List<BoardDto> seqRow(int displayPost, int postNum);
		
		// 파일 업로드
		public int upload(BoardDto dto);
		
		// 파일 다운로드
		public BoardDto down(int id);

}
