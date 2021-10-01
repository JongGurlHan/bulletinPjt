package project.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.demo.beans.ContentBean;
import project.demo.beans.UserBean;
import project.demo.dao.BoardDao;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

@Service
@PropertySource("option.properties")
public class BoardService {

    @Value("${path.upload}")
    private String path_upload;

    @Autowired
    private BoardDao boardDao;

    //사용자 로그인정보 주입
    @Resource(name="loginUserBean")
    private UserBean loginUserBean;

    //파일 저장 메소드
    private String saveUploadFile(MultipartFile upload_file){
       //중복된 파일명이면 덮어지기때문에 현재시간을 구한뒤 파일명에 붙여준다.
        String file_name = System.currentTimeMillis() +"_"+ upload_file.getOriginalFilename();

        try{
            upload_file.transferTo(new File(path_upload + "/" + file_name));
        }catch (Exception e){
            e.printStackTrace();
        }
        return file_name;

    }

    public void addContentInfo(ContentBean writeContentBean){

        MultipartFile upload_file = writeContentBean.getUpload_file();
        if(upload_file.getSize() > 0){
            String file_name = saveUploadFile(upload_file);
            writeContentBean.setContent_file(file_name);
        }
        writeContentBean.setContent_writer_idx(loginUserBean.getUser_idx());

        boardDao.addContentInfo(writeContentBean);
    }

    //게시판 이름 가져오기
    public String getBoardInfoName(int board_info_idx){
        return boardDao.getBoardInfoName(board_info_idx);
    }

    //게시글 리스트 가져오기
    public List<ContentBean> getContentList(int board_info_idx){
        return boardDao.getContentList(board_info_idx);
    }

    //게시글 정보 가져오기
    public ContentBean getContentInfo(int content_idx){
        return boardDao.getContentInfo(content_idx);
    }

}
