package kr.or.ddit.dao.baord;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.StateCheck;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PageVO;


@Repository
public class BoardDAOImpl implements IBoardDAO {

	@Inject
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int insertBoard(BoardVO board) {
		return sqlSession.insert("Board.insertBoard", board);
	}

	@Override
	public BoardVO detailBoard(String bono) {
		return sqlSession.selectOne("Board.detailBoard", bono);
	}

	@Override
	public void upHit(String bono) {
		sqlSession.update("Board.upHit", bono);
	}

	@Override
	public int deleteBoard(String bono) {
		return sqlSession.delete("Board.deleteBoard", bono);
	}

	@Override
	public int selectBoardCount(PageVO<BoardVO> pageVO) {
		return sqlSession.selectOne("Board.selectBoardCount", pageVO);
	}

	@Override
	public List<BoardVO> selectBoardList(PageVO<BoardVO> pageVO) {
		return sqlSession.selectList("Board.selectBoardList", pageVO);
	}

	@Override
	public int updateBoard(BoardVO board) {
		return sqlSession.update("Board.updateBoard", board);
	}

}
