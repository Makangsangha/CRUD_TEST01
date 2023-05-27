package kr.or.ddit.dao.baord;

import java.util.List;

import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PageVO;

public interface IBoardDAO {

	public int insertBoard(BoardVO board);

	public BoardVO detailBoard(String bono);

	public void upHit(String bono);

	public int deleteBoard(String bono);

	public int selectBoardCount(PageVO<BoardVO> pageVO);

	public List<BoardVO> selectBoardList(PageVO<BoardVO> pageVO);

	public int updateBoard(BoardVO board);

}
