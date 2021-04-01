package com.min.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.min.dto.BoardDto;
import com.min.model.IService;
@Controller
public class BoardController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IService service;

	// 게시판 전체 목록 조회
	@RequestMapping(value = "/BoardMain.do", method = RequestMethod.GET)
	public String boardAll(Model model) throws Exception {
		log.info("게시판 상세 보기");
		List<BoardDto> lists = service.All_Board();
		log.info("게시판 상세 보기22");
		model.addAttribute("lists", lists);
		return "BoardMain";
	}
	
	// 로그인 후 보이는 게시판목록
	@RequestMapping(value ="/home.do", method = RequestMethod.POST)
	public String login(BoardDto dto, Model model) {
		boolean lists = service.login(dto);
		if(lists==false) {
			log.info("로그인 안됨");
			return "redirect:/home.do";
		}else {
			service.login(dto);
			model.addAttribute("dto", lists);
			log.info("로그인확인", dto.getUsername());
			return "listPage?num=1&row=seqRow";
		}
	}
	
	// 게시판 상세 보기
	@RequestMapping(value = "/BoardDetail.do", method = RequestMethod.GET)
	public String boardDetail(Model model, int seq) throws IOException {
		BoardDto lists = service.Detail_Board(seq);
		log.info("게시판 상세 보기", seq);
		model.addAttribute("dto", lists);
		return "BoardDetail";
	}

	// 게시판 수정뷰
	@RequestMapping(value = "/updateView.do", method = RequestMethod.GET)
	public String updateView(BoardDto dto, Model model) {
		log.info("updateView");
		model.addAttribute("update", service.Detail_Board(dto.getSeq()));

		return "updateView";
	}

	// 게시판 수정
	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public String update(BoardDto dto) {
		log.info("게시판 수정");
		service.update(dto);
		return "redirect:BoardMain.do";
	}

	// 게시판 삭제
	@RequestMapping(value = "/delete.do", method = RequestMethod.POST)
	public String boardDelete(Model model, int seq) {
		boolean listss = service.delete(seq);
		log.info("게시판 삭제");
		model.addAttribute("dto", listss);
		service.delete(seq);
		return "redirect:BoardMain.do";
	}

	// 게시판 글 작성 화면
	@RequestMapping(value = "/writeView.do", method = RequestMethod.GET)
	public String writeView() {
		log.info("writeView");
		return "writeView";
	}

	// 게시판 글 작성/////////////////////////////////////////////////
	@RequestMapping(value = "/write.do", method = RequestMethod.POST)
	public String write(BoardDto dto, MultipartHttpServletRequest req, Model model) {
		log.info("write dto : {}", dto);

		String fileTag = "file"; //
		MultipartFile file = req.getFile(fileTag);
		int filesize = (int) file.getSize();
		String filename = file.getOriginalFilename(); // 파일 전송
		String savepath = "C:\\fileupload\\"; // 업로드 파일이 저장될 경로
		log.info("성공성공성공!!!!!!!!!!!!!!");
		
		model.addAttribute("filename", filename);
		try {
			file.transferTo(new File(savepath + filename));
			log.info("file.transferTo");
			dto.setFilename(filename);
			log.info("filename");
			dto.setFilesize(filesize);
			log.info("filesize");
			dto.setSavepath(savepath);
			log.info("savepath");
			service.upload(dto);
			log.info("upload pk : {}", dto.getId());
			dto.setDownload(dto.getId());
			log.info("uploadPK : {}", dto);
			service.write(dto);
			// 다운로드 받을때 pk=id값 몇이다 라고 알수있게
		} catch (Exception e) {
			System.out.println("업로드 오류");
		}
		return "redirect:BoardMain.do";

	}

	// 파일 다운로드
	@RequestMapping(value = "/download.do", method = RequestMethod.GET)
	public void download(Model model, HttpServletRequest req, HttpServletResponse resp, int id) throws IOException {

		BoardDto dto = service.down(id);
		
		log.info("download dto : {}", dto);
		
		File file = new File(dto.getSavepath() + dto.getFilename());
		log.info("file : {}", file);
		
		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
		String mimeType = URLConnection.guessContentTypeFromStream(inputStream);

		// 파일의 종류가 없다면 기본으로 설정
		if (mimeType == null) {
		mimeType = "application/octec-stream";
		}
		resp.setContentType(mimeType);
		resp.setContentLength((int) file.length());
		//해당 파일이 첨부 파일임을 명시
		resp.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
		log.info(file.getName() + "@@@@@@@@@@@@@@@@@@@@@@@@@@");

		FileCopyUtils.copy(inputStream, resp.getOutputStream());
	}

	// 게시물 목록 + 페이징 추가
	@RequestMapping(value = "/listPage", method = {RequestMethod.GET, RequestMethod.POST})
	public String ListPage(Model model,
			@RequestParam("num") int number,  String row) {

		// 게시물 총 갯수
		int count = service.count();

		// 한 페이지에 출력할 게시물 갯수
		int postNum = 10;

		// 하단 페이징 갯수 ( '게시물 총갯수 / 한페이지에 출력할 갯수' 의 올림) // 나머지 페이지들도 보여야하니까
		int pageNum = (int) Math.ceil((double) count / postNum);

		// 페이지에서 첫번째 게시글의 번호
		int displayPost = (number - 1) * postNum;

		// 하단에 한페이지에 보여지는 페이지의 갯수
		int pageNum_cnt = 5;

		// 표시되는 하단 페이지 번호 중 마지막 번호
		int endPageNum = (int) (Math.ceil((double) number / (double) pageNum_cnt) * pageNum_cnt);

		// 표시되는 페이지 번호 중 첫번째 번호
		int startPageNum = endPageNum - (pageNum_cnt - 1);

		// 마지막 번호 재계산
		int endPageNum_tmp = (int) (Math.ceil((Math.ceil((double) count / (double) pageNum_cnt))/2));

		if (endPageNum > endPageNum_tmp) { // 빈페이지가 없게 하려고
			endPageNum = endPageNum_tmp;
		}

		boolean prev = startPageNum == 1 ? false : true;
		boolean next = endPageNum * pageNum_cnt >= count ? false : true;

		// 이전 링크는 시작페이지번호가 1일때를 제외하곤 무조건 출력 되어야 함
		// 시작 및 끝 번호
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);

		// 이전 및 다음
		model.addAttribute("prev", prev);
		model.addAttribute("next", next);

		// 현재 페이지
		model.addAttribute("select", number);

		List<BoardDto> list = null;
		
		if (row.equals("regdateRow")) {
			list = service.regdateRow(displayPost, postNum);
		}else if (row.equals("titleRow")) {
			list = service.titleRow(displayPost, postNum);
		}else if (row.equals("seqRow")) {
			list = service.seqRow(displayPost, postNum);
		}

		model.addAttribute("list", list);
		model.addAttribute("number", number);
		model.addAttribute("pageNum", pageNum); // 페이지 총 갯수
		model.addAttribute("row",row);

		return "listPage";
	}


}
