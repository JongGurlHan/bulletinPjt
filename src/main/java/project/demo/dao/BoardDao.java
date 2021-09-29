package project.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.demo.beans.ContentBean;
import project.demo.mapper.BoardMapper;


@Repository
public class BoardDao {

    @Autowired
    private BoardMapper boardMapper;

    public void addContentInfo(ContentBean writeContentBean){
        boardMapper.addContentInfo(writeContentBean);
    }

    public String getBoardInfoName(int board_info_idx){
        return boardMapper.getBoardInfoName(board_info_idx);
    }


}
