package sd_back.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sd_back.demo.domain.Member;
import sd_back.demo.domain.Notice;
import sd_back.demo.repository.notice.JpaNoticeRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final JpaNoticeRepository noticeRepository;

    public boolean isManager(int memberid){
        if (memberid == 1) {
            return true;
        }
        else{
            return false;
        }
    }

    public Notice createNotice(String title, String content, LocalDateTime dateTime, Member member) { //공지사항 생성
        Notice notice = new Notice(title, content, dateTime, member);
        noticeRepository.save(notice);
        return notice;
    }

    public List<Notice> findAllNotice(){ //모든 공지사항 조회 (전체 공지사항 보기)
        return noticeRepository.findAll();
    }

    public List<Notice> findNoticeByTitle(){ //공지사항 제목으로 조회
        return null;
    }

    public boolean deleteNotice(int id) { //공지사항 삭제
        Optional<Notice> notice = noticeRepository.findById(id);
        if (notice.isEmpty()) {
            return false;
        }
        noticeRepository.deleteById(id);
        return true;
    }

    public Optional<Notice> updateNotice(int id, String title, String content, LocalDateTime dateTime) { //공지사항 업데이트
        Optional<Notice> notice = noticeRepository.findById(id);
        if (notice.isEmpty()) {
            return null;
        }
        notice.get().setTitle(title);
        notice.get().setContent(content);
        notice.get().setDate_time(dateTime);
        noticeRepository.updateNotice(title, content, dateTime, id);

        return notice;
    }

    public Optional<Notice> findNoticeById(int id) {
        Optional<Notice> notice = noticeRepository.findById(id);

        if (notice.isEmpty()) {
            return null;
        }
        else return notice;
    }
}
