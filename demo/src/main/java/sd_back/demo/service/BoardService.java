package sd_back.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sd_back.demo.domain.Board;
import sd_back.demo.domain.Member;
import sd_back.demo.domain.Notice;
import sd_back.demo.repository.board.JpaBoardRepository;
import sd_back.demo.repository.notice.JpaNoticeRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final JpaBoardRepository boardRepository;

    public Board createBoard(String title, String content, LocalDateTime date_time, Member member) { //공지사항 생성
        Board board = new Board(title, content, date_time, member);
        boardRepository.save(board);
        return board;
    }

    public List<Board> findAllBoard(){ //모든 공지사항 조회 (전체 공지사항 보기)
        return boardRepository.findAll();
    }

    public boolean deleteBoard(int id) { //공지사항 삭제
        Optional<Board> board = boardRepository.findById(id);
        if (board.isEmpty()) {
            return false;
        }
        boardRepository.deleteById(id);
        return true;
    }

    public Optional<Board> updateBoard(int id, String title, String content, LocalDateTime date_time) { //공지사항 업데이트
        Optional<Board> board = boardRepository.findById(id);
        if (board.isEmpty()) {
            return null;
        }
        boardRepository.updateBoard(title, content, date_time, id);
        return board;
    }

    public Optional<Board> findBoardById(int id) {
        Optional<Board> board = boardRepository.findById(id);
        if (board.isEmpty()) {
            return null;
        }
        else return board;
    }
}
