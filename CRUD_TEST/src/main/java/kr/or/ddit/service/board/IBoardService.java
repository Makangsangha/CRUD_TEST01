package kr.or.ddit.service.board;

import java.util.List;

import kr.or.ddit.StateCheck;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PageVO;

public interface IBoardService {

	public StateCheck insertBoard(BoardVO board);

	public BoardVO detailBoard(String bono);

	public StateCheck deleteBoard(String bono);

	public int selectBoardCount(PageVO<BoardVO> pageVO);

	public List<BoardVO> selectBoardList(PageVO<BoardVO> pageVO);

	public StateCheck updateBoard(BoardVO board);

}
