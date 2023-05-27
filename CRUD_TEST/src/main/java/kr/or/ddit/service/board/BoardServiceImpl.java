package kr.or.ddit.service.board;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.StateCheck;
import kr.or.ddit.dao.baord.IBoardDAO;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PageVO;

@Service
public class BoardServiceImpl implements IBoardService{
	
	@Inject
	private IBoardDAO dao;
	
	@Override
	public StateCheck insertBoard(BoardVO board) {
		StateCheck check;
		int result = dao.insertBoard(board);
		if(result > 0) {
			check = StateCheck.OK;
		}else {
			check = StateCheck.FAIL;
		}
		return check;
	}

	@Override
	public BoardVO detailBoard(String bono) {
		dao.upHit(bono);
		return dao.detailBoard(bono);
	}

	@Override
	public StateCheck deleteBoard(String bono) {
		StateCheck check;
		int result = dao.deleteBoard(bono);
		if(result > 0) {
			check = StateCheck.OK;
		}else {
			check = StateCheck.FAIL;			
		}
		return check;
	}

	@Override
	public int selectBoardCount(PageVO<BoardVO> pageVO) {
		return dao.selectBoardCount(pageVO);
	}

	@Override
	public List<BoardVO> selectBoardList(PageVO<BoardVO> pageVO) {
		return dao.selectBoardList(pageVO);
	}

	@Override
	public StateCheck updateBoard(BoardVO board) {
		StateCheck check;
		int result = dao.updateBoard(board);
		if(result > 0) {
			check = StateCheck.OK;
		}else {
			check = StateCheck.FAIL;
		}
		return check;
	}


}
