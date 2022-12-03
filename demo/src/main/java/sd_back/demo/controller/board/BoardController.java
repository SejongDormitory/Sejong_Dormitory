package sd_back.demo.controller.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sd_back.demo.controller.notice.NoticeForm;
import sd_back.demo.domain.Board;
import sd_back.demo.domain.Notice;
import sd_back.demo.service.BoardService;
import sd_back.demo.service.MemberService;
import sd_back.demo.service.NoticeService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/board")//게시판 전체 리스트(초기 화면)
    public ResponseEntity<List<Board>> boardList() {
        List<Board> boardList = boardService.findAllBoard();

        if (boardList.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(boardList, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/board")//게시글 생성
    public ResponseEntity saveBoard(@Valid @RequestBody BoardForm form, BindingResult bindingResult) {
        boardService.createBoard(form.title, form.content, form.date_time, form.member);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/board/{id}")
    public ResponseEntity getBoardById(@PathVariable int id) { //게시글 상세 내용
        Optional<Board> board = boardService.findBoardById(id);

        if (board.isEmpty()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(board.get(), HttpStatus.OK);
    }

    @ResponseBody
    @PutMapping("/board/{id}") //게시글 업데이트
    public ResponseEntity updateBoard(@PathVariable int id, @Valid @RequestBody BoardForm form, BindingResult bindingResult) {
        Optional<Board> board = boardService.updateBoard(id, form.title, form.content, form.date_time);

        if (board.isEmpty()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(board, HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping("/board/{id}")
    public ResponseEntity deleteBoard(@PathVariable int id) { //게시글 삭제
        if(!boardService.deleteBoard(id)){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
