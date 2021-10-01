package project.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import project.demo.beans.ContentBean;

import java.util.List;

@Mapper
public interface BoardMapper {

    @Insert("INSERT INTO content_table(content_subject, content_text, content_file, content_writer_idx, content_board_idx, content_date) VALUES(#{content_subject}, #{content_text}, #{content_file}, #{content_writer_idx}, #{content_board_idx}, SYSDATE() )")
    void addContentInfo(ContentBean writeContentBean);

    @Select("SELECT board_info_name\n" +
            "FROM board_info_table\n" +
            "WHERE board_info_idx = #{board_info_idx}")
    String getBoardInfoName(int board_info_idx);

    @Select("SELECT a1.content_idx, a1.content_subject, a2.user_name AS content_writer_name, DATE_FORMAT(a1.content_date,'%Y-%M-%d') AS content_date\n" +
            "FROM content_table a1, user_table a2 \n" +
            "WHERE a1.content_writer_idx = a2.user_idx \n" +
            "AND a1.content_board_idx = #{board_info_idx} \n" +
            "ORDER BY a1.content_idx DESC")
    List<ContentBean> getContentList(int board_info_idx);

    @Select("SELECT a2.user_name as content_writer_name, DATE_FORMAT(a1.content_date,'%Y-%M-%d') AS content_date,\n" +
            "a1.content_subject, a1.content_text, a1.content_file\n" +
            "FROM content_table a1, user_table a2\n" +
            "WHERE a1.content_writer_idx = a2.user_idx\n" +
            "AND content_idx= #{content_idx}\n")
    ContentBean getContentInfo(int content_idx);



}



