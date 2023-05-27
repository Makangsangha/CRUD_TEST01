package kr.or.ddit.controller.board;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.StateCheck;
import kr.or.ddit.service.board.IBoardService;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PageVO;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/board")
@Slf4j
public class boardController {
	
	@Inject
	IBoardService service;
	
	@RequestMapping(value = "/boardList", method  = RequestMethod.GET)
	public String boardList(
			@RequestParam(name="page",required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false, defaultValue = "title") String searchType,
			@RequestParam(required = false) String searchWord,
			Model model) {
		log.info("boardList() 실행...!");
		log.info(currentPage+"");
		PageVO<BoardVO> pageVO = new PageVO<BoardVO>();
		if(StringUtils.isNoneBlank(searchWord)) {
			pageVO.setSearchType(searchType);
			pageVO.setSearchWord(searchWord.trim());
			model.addAttribute("searchType", searchType);
			model.addAttribute("searchWord", searchWord);
		}
		
		pageVO.setCurrentPage(currentPage);
		int totalRecord = service.selectBoardCount(pageVO);
		pageVO.setTotalRecord(totalRecord);
		List<BoardVO> detaList = service.selectBoardList(pageVO);
		pageVO.setDataList(detaList);
		model.addAttribute("pageVO", pageVO);

		return "pages/ddit_list";
	}
	
	@RequestMapping(value = "/boardDetail", method = RequestMethod.GET)
	public String boardDetail(String bono, Model model) {
		BoardVO board = service.detailBoard(bono);
		String url ="";
		if(board != null) {
			model.addAttribute("board", board);
			url = "pages/ddit_detail";
		}else {
			model.addAttribute("massage", "게시판 불러오기가 실패하였습니다.");
			url = "pages/ddit_error";
		}
		return url;
	}
	
	@RequestMapping(value = "/boardInsertForm", method  = RequestMethod.GET)
	public String boardInsertForm(Model model) {
		log.info("boardInsertForm() 실행...!");

		
		model.addAttribute("status", "I");
		
		return "pages/ddit_form";
	}
	
	@RequestMapping(value = "/boardUpdateForm", method  = RequestMethod.GET)
	public String boardUpdateForm(String bono, Model model) {
		log.info("boardUpdateForm() 실행...!");
		
		BoardVO board = service.detailBoard(bono);
		String url = "";
		if(board!=null) {
			model.addAttribute("board", board);
			model.addAttribute("status", "U");
			url = "pages/ddit_form";
		}else {
			model.addAttribute("massage", "불러오기 실패!");
			url = "pages/ddit_error";			
		}
		
		return url;
	}
	
	@RequestMapping(value = "/insertBoard", method = RequestMethod.POST)
	public String insertBoard(BoardVO board, Model model) {
		String url = "";
		if(board.getBotitle().trim()=="" && board.getBotitle()==null &&
			board.getBocontent().trim()=="" && board.getBocontent()==null &&
			board.getBowriter().trim()=="" && board.getBowriter()==null) {
			model.addAttribute("massage", "누락된 정보가 있습니다.");
			url = "pages/ddit_form";
		}else {
			StateCheck check = service.insertBoard(board);
			if(check == StateCheck.OK) {
				url = "redirect:/board/boardDetail?bono=" + board.getBono();
			}else {
				model.addAttribute("board", board);
				url = "pages/ddit_form";				
			}
		}
		
		return url;
	}
	
	@RequestMapping(value = "/deleteBoard", method = RequestMethod.POST)
	public String deleteBoard(String bono, Model model) {
		String url = "";
		StateCheck check = service.deleteBoard(bono);
		if(check == StateCheck.OK) {
			url = "redirect:/board/boardList";
		}else {
			model.addAttribute("massage", "삭제 실패! 다시 시도해주세요");
			url = "pages/ddit_error";
		}
		return url;
	}
	
	@RequestMapping(value = "/updateBoard", method = RequestMethod.POST)
	public String updateBoard(BoardVO board, Model model) {
		String url = "";
		if(board.getBotitle().trim()=="" && board.getBotitle()==null &&
			board.getBocontent().trim()=="" && board.getBocontent()==null &&
			board.getBowriter().trim()=="" && board.getBowriter()==null) {
			model.addAttribute("massage", "누락된 정보가 있습니다.");
			url = "pages/ddit_form";
		}else {
			StateCheck check = service.updateBoard(board);
			if(check == StateCheck.OK) {
				url = "redirect:/board/boardDetail?bono=" + board.getBono();
			}else {
				model.addAttribute("board", board);
				url = "pages/ddit_form";				
			}
		}
		
		return url;
	}
	
}
