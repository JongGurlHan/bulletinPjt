package project.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("SELECT user_name FROM user_table WHERE user_id = #{user_id}")
    String checkUserIdExist(String user_id);
}
