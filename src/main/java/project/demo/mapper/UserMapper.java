package project.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import project.demo.beans.UserBean;

@Mapper
public interface UserMapper {

    @Select("SELECT user_name FROM user_table WHERE user_id = #{user_id}")
    String checkUserIdExist(String user_id);

    @Insert("INSERT INTO user_table(user_name, user_id, user_pw) VALUES (#{user_name}, #{user_id}, #{user_pw})")
    void addUserInfo(UserBean userBean);
}
