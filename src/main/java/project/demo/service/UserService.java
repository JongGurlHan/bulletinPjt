package project.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.demo.dao.UserDao;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public boolean checkUserIdExist(String user_id){
        String user_name = userDao.checkUserIdExist(user_id);
      //아이디에 해당하는 유저명이 없으면 true, 있으면 false
        if(user_name == null){
            return true;
        }else {
            return false;
        }
    }

}
