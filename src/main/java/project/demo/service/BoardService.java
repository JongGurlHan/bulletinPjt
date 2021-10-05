package project.demo.service;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.demo.beans.ContentBean;
import project.demo.beans.PageBean;
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

    @Value("${page.listcnt}")
    private int page_listcnt;

    @Value("${page.paginationcnt}")
    private int page_paginationcnt;

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
    public List<ContentBean> getContentList(int board_info_idx, int page){

        //rowBounds는 인덱스 번호가 0 부터 시작된다. (0,10) ->처음부터 10개 (10,10) ->11번째부터 10개
        int start = (page - 1) * page_listcnt;
        //만약 페이지에 1이 들어오면 start는 0(1번째) -> 1에서부터 10개
        //페이지에 2가 들어가면 start는 10(11번째) -> 11에서부터 10개
        RowBounds rowBounds = new RowBounds(start, page_listcnt);


        return boardDao.getContentList(board_info_idx, rowBounds);
    }

    //게시글 정보 가져오기
    public ContentBean getContentInfo(int content_idx){
        return boardDao.getContentInfo(content_idx);
    }

    //게시글 수정하기
    public void modifyContentInfo(ContentBean modifyContentBean){

        MultipartFile upload_file = modifyContentBean.getUpload_file();

        //기존에 업로드한 파일이 있다면
        if(upload_file.getSize() > 0){
            String file_name = saveUploadFile(upload_file);
            modifyContentBean.setContent_file(file_name);
        }
        //기존에 업로드한 파일이 없다면
        boardDao.modifyContentInfo(modifyContentBean);
    }

    //게시글 삭제하기
    public void deleteContentInfo(int content_idx){
        boardDao.deleteContentInfo(content_idx);
    }

    public PageBean getContentCnt(int content_board_idx, int currentPage){

        int content_cnt = boardDao.getContentCnt(content_board_idx);

        PageBean pageBean = new PageBean(content_cnt, currentPage, page_listcnt, page_paginationcnt );

        return pageBean;
    }


}
