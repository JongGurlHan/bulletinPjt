package project.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.demo.beans.BoardInfoBean;
import project.demo.mapper.BoardMapper;
import project.demo.mapper.TopMenuMapper;

import java.util.List;

@Repository
public class TopMenuDao {

    @Autowired
    private TopMenuMapper topMenuMapper;

    public List<BoardInfoBean> getTopMenuList(){
        List<BoardInfoBean> topMenuList = topMenuMapper.getTopMenuList();
        return topMenuList;
    }


}
