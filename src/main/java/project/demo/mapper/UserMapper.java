package project.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import project.demo.beans.UserBean;

@Mapper
public interface UserMapper {

    @Select("SELECT user_name FROM user_table WHERE user_id = #{user_id}")
    String checkUserIdExist(String user_id);

    @Insert("INSERT INTO user_table(user_name, user_id, user_pw) VALUES (#{user_name}, #{user_id}, #{user_pw})")
    void addUserInfo(UserBean userBean);

    @Select("SELECT user_idx, user_name\n" +
            "FROM user_table\n" +
            "WHERE user_id= #{user_id} and user_pw=#{user_pw};\n")
    UserBean getLoginUserInfo(UserBean tempLoginUserBean );

    //수정에 앞서 로그인된 유저 정보를 가져옴
    @Select("SELECT user_id, user_name " +
            "FROM user_table " +
            "WHERE user_idx = #{user_idx}")
    UserBean getModifyUserInfo(int user_idx);

    @Update("UPDATE user_table SET user_pw = #{user_pw} WHERE user_idx = #{user_idx}")
    void modifyUserInfo(UserBean modifyUserBean);


}
