package project.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import project.demo.beans.ContentBean;

@Mapper
public interface BoardMapper {
    @Insert("INSERT INTO content_table(content_subject, content_text, content_file, content_writer_idx, content_board_idx, content_date) VALUES(#{content_subject}, #{content_text}, #{content_file}, #{content_writer_idx}, #{content_board_idx}, SYSDATE() )")
    void addContentInfo(ContentBean writeContentBean);
}



