package com.min.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.dto.BoardDto;

// 데이터를 dao를 통해 넘겨주거나 받으면서 비즈니스 로직을 수행하는 역할
@Service
public class ControllerService implements IService {
	
	private Logger log = LoggerFactory.getLogger(ControllerService.class);
	
	@Autowired
	private IDao dao;

	@Override
	public boolean write(BoardDto dto) {
		log.info("게시글 등록 : write", dto);
		return dao.write(dto);
	}

	@Override
	public List<BoardDto> All_Board() {
		log.info("게시글 전체 조회: All_Board");
		return dao.All_Board();
	}

	@Override
	public BoardDto Detail_Board(int seq) {
		log.info("게시글 상세 조회: Detail_Board",seq);
		return dao.Detail_Board(seq);
	}

	@Override
	public boolean update(BoardDto dto) {
		log.info("게시글 수정: update",dto);
		return dao.update(dto);
	}

	@Override
	public boolean delete(int seq) {
		log.info("게시글 삭제: delete",seq);
		return dao.delete(seq);
	}

	@Override
	public int count() {
		log.info("게시물 총 갯수: count");
		return dao.count();
	}

	@Override
	public List<BoardDto> listPage(int displayPost, int postNum) {
		log.info("게시물 목록 + 페이징: listPage", displayPost, postNum);
		return dao.listPage(displayPost, postNum);
	}

	@Override
	public List<BoardDto> regdateRow(int displayPost, int postNum) {
		log.info("정렬 날짜 최신순", displayPost, postNum);
		return dao.regdateRow(displayPost, postNum);
	}

	@Override
	public List<BoardDto> titleRow(int displayPost, int postNum) {
		log.info("정렬 제목순", displayPost, postNum);
		return dao.titleRow(displayPost, postNum);
	}

	@Override
	public List<BoardDto> seqRow(int displayPost, int postNum) {
		log.info("seq순", displayPost, postNum);
		return dao.seqRow(displayPost, postNum);
	}

	@Override
	public int upload(BoardDto dto) {
		System.out.println("123");
		log.info("파일업로드 {}",dto);
		return dao.upload(dto);
	}

	@Override
	public BoardDto down(int id) {
		log.info("파일 다운로드",id);
		return dao.down(id);
	}

	@Override
	public boolean login(BoardDto dto) {
		log.info("로그인: login",dto);
		return dao.login(dto);
	}

	@Override
	public boolean joinUser(BoardDto dto) {
		log.info("회원가입: joinUser",dto);
		return dao.joinUser(dto);
	}


}
