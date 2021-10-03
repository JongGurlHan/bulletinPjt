package project.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.demo.beans.ContentBean;
import project.demo.mapper.BoardMapper;

import java.util.List;


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

    public List<ContentBean> getContentList(int board_info_idx){
        return boardMapper.getContentList(board_info_idx);
    }

    public ContentBean getContentInfo(int content_idx){
        return boardMapper.getContentInfo(content_idx);
    }

    public void modifyContentInfo(ContentBean modifyContentInfo){
        boardMapper.modifyContentInfo(modifyContentInfo);
    }

    public void deleteContentInfo(int content_idx){
        boardMapper.deleteContentInfo(content_idx);
    }



}
