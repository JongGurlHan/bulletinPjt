package project.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import project.demo.beans.BoardInfoBean;

import java.util.List;

@Mapper
public interface TopMenuMapper {

    @Select("SELECT board_info_idx, board_info_name FROM board_info_table")
    List<BoardInfoBean> getTopMenuList();

}
