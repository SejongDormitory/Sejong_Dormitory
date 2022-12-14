package sd_back.demo.repository.notice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sd_back.demo.domain.Notice;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;


public interface JpaNoticeRepository extends JpaRepository<Notice, Integer> {

    List<Notice> findByMemberId(long memberId);
    List<Notice> findByTitle(String title);

    @Transactional //데이터 수정/삭제 도중 에러 발생 시 롤백되도록 함
    @Modifying(clearAutomatically = true) //update 쿼리로 인식되도록 함
    @Query(value = "UPDATE Notice b set b.title = :title, b.content = :content, b.date_time = :date_time where b.id= :id")
    void updateNotice(@Param("title")String title, @Param("content")String content
            , @Param("date_time") LocalDateTime date_time, @Param("id") int id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from Notice b where b.id = :id")
    void deleteById(@Param("id") int id);
}
