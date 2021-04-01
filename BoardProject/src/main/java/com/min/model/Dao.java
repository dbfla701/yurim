package com.min.model;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.dto.BoardDto;

// DB를 통해 데이터를 조회하거나 수정 삭제 하는 역할
@Repository
public class Dao implements IDao {

	private Logger log = LoggerFactory.getLogger(Dao.class);
	
	private final String NS = "boardMapper.";

	@Autowired
	private SqlSessionTemplate sqlsession;

	@Override
	public boolean write(BoardDto dto) {
		log.info("게시글 등록 : write");
		int n = sqlsession.insert(NS + "write", dto);
		return (n > 0) ? true : false;
	}
	
	@Override
	public boolean login(BoardDto dto) {
		log.info("로그인 : login");
		int n = sqlsession.selectOne(NS + "login", dto);
		return (n > 0) ? true : false;
	}

	@Override
	public boolean joinUser(BoardDto dto) {
		log.info("회원가입 : login");
		int n = sqlsession.insert(NS + "joinUser", dto);
		return (n > 0) ? true : false;
	}

	@Override
	public List<BoardDto> All_Board() {
		log.info("게시글 전체 조회: All_Board");
		return sqlsession.selectList(NS + "All_Board");
	}

	@Override
	public BoardDto Detail_Board(int seq) {
		log.info("게시글 상세 조회: Detail_Board");
		return sqlsession.selectOne(NS + "Detail_Board", seq);
	}

	@Override
	public boolean update(BoardDto dto) {
		log.info("게시글 수정 : update");
		int n = sqlsession.update(NS + "update", dto);
		return (n > 0) ? true : false;
	}

	@Override
	public boolean delete(int seq) {
		log.info("게시글 삭제: delete");
		int n = sqlsession.delete(NS + "delete", seq);
		return (n > 0) ? true : false;
	}

	@Override
	public int count() {
		return sqlsession.selectOne(NS + "count");
	}

	@Override
	public List<BoardDto> listPage(int displayPost, int postNum) {

		// DAO와 매퍼에서는 데이터를 하나만 전송할 수 있기 때문에,
//		2개 이상의 데이터를 다룰 때는 VO(Value Object)를 사용하거나 해시맵을 이용함
		HashMap<String, Object> data = new HashMap<>();

		data.put("displayPost", displayPost);
		data.put("postNum", postNum);
		return sqlsession.selectList(NS + "listPage", data);
	}

	@Override
	public List<BoardDto> regdateRow(int displayPost, int postNum) {
		log.info("정렬 날짜 최신순 : regdateRow");
		HashMap<String, Object> data = new HashMap<>();

		data.put("displayPost", displayPost);
		data.put("postNum", postNum);
		return sqlsession.selectList(NS+"regdateRow", data);
	}

	@Override
	public List<BoardDto> titleRow(int displayPost, int postNum) {
		log.info("페이징 정렬 제목순: titleRow");
		HashMap<String, Object> data = new HashMap<>();

		data.put("displayPost", displayPost);
		data.put("postNum", postNum);
		return sqlsession.selectList(NS+"titleRow", data);
	}

	@Override
	public List<BoardDto> seqRow(int displayPost, int postNum) {
		log.info("페이징 seq순: seqRow");
		HashMap<String, Object> data = new HashMap<>();

		data.put("displayPost", displayPost);
		data.put("postNum", postNum);
		return sqlsession.selectList(NS+"seqRow", data);
	}

	@Override
	public int upload(BoardDto dto) {
		log.info("파일 업로드: upload");
		System.out.println("12356666daodao");
		int n = sqlsession.insert(NS + "upload", dto);
		return n;
	}

	@Override
	public BoardDto down(int id) {
		log.info("파일 다운로드 : down");
		return sqlsession.selectOne(NS+"down", id);
	}

	
	

}
