package project.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.demo.beans.ContentBean;
import project.demo.beans.UserBean;
import project.demo.service.BoardService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;

	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;
	
	@GetMapping("/main")
	public String main(@RequestParam("board_info_idx") int board_info_idx,
					   Model model) {
		model.addAttribute("board_info_idx", board_info_idx);

		//게시판 이름 가져오기
		String boardInfoName = boardService.getBoardInfoName(board_info_idx);
		model.addAttribute("boardInfoName", boardInfoName);

		//게시글 정보 가져오기
		List<ContentBean> contentList = boardService.getContentList(board_info_idx);
		model.addAttribute("contentList", contentList);

		return "board/main";
	}

	@GetMapping("/read")
	public String read(@RequestParam("board_info_idx")int board_info_idx,
					   @RequestParam("content_idx") int content_idx,
					   Model model){

		model.addAttribute("board_info_idx", board_info_idx);
		//수정하기나 삭제하기 눌렀을때 어떤 글을 수정하고 삭제할지 알아내기 위해
		model.addAttribute("content_idx", content_idx);

		ContentBean readContentBean = boardService.getContentInfo(content_idx);
		model.addAttribute("readContentBean", readContentBean);

		model.addAttribute("loginUserBean", loginUserBean);

		return "board/read";
	}

	@GetMapping("/write")
	public String write(@ModelAttribute("writeContentBean")ContentBean writeContentBean,
						@RequestParam("board_info_idx") int board_info_idx,
						Model model){
		//어느 게시판에서 쓰는지 board_info_idx를 가져온다.

		writeContentBean.setContent_board_idx(board_info_idx);

		model.addAttribute("writeContentBean", writeContentBean);

		System.out.println(writeContentBean.getContent_board_idx());

		return "board/write";
	}

	@PostMapping("/write")
	public String write(@Valid @ModelAttribute("writeContentBean")ContentBean writeContentBean, BindingResult result,
						Model model){
		if(result.hasErrors()){
			return "board/write";
		}
		boardService.addContentInfo(writeContentBean);
		model.addAttribute("writeContentBean", writeContentBean);
		return "board/write_success";

	}


	@GetMapping("/modify")
	public String modify(@RequestParam("board_info_idx") int board_info_idx,
						 @RequestParam("content_idx") int content_idx,
						 @ModelAttribute("modifyContentBean") ContentBean modifyContentBean,
						 Model model) {

		model.addAttribute("board_info_idx", board_info_idx);
		model.addAttribute("content_idx", content_idx);

		//게시글 정보 보여주기 위해서 임시 빈 세팅
		ContentBean tempContentBean = boardService.getContentInfo(content_idx);

		modifyContentBean.setContent_writer_name(tempContentBean.getContent_writer_name());
		modifyContentBean.setContent_date(tempContentBean.getContent_date());
		modifyContentBean.setContent_subject(tempContentBean.getContent_subject());
		modifyContentBean.setContent_text(tempContentBean.getContent_text());
		modifyContentBean.setContent_file(tempContentBean.getContent_file());
		modifyContentBean.setContent_writer_idx(tempContentBean.getContent_writer_idx());
		modifyContentBean.setContent_board_idx(board_info_idx);
		modifyContentBean.setContent_idx(content_idx);

		return "board/modify";
	}



	@PostMapping("/modify")
	public String modify(@Valid @ModelAttribute("modifyContentBean") ContentBean modifyContentBean,
							 BindingResult result) {
		if(result.hasErrors()) {
			return "board/modify";
		}
		boardService.modifyContentInfo(modifyContentBean);

		return "board/modify_success";
	}


	@GetMapping("/delete")
	public String delete(@RequestParam("board_info_idx") int board_info_idx,
						 @RequestParam("content_idx") int content_idx,
						 Model model){

		boardService.deleteContentInfo(content_idx);

		//삭제 메시지를 보여주기 위해
		model.addAttribute("board_info_idx", board_info_idx);

		return "board/delete";
	}

	@GetMapping("/not_writer")
	public String not_writer(){
		return "board/not_writer";
	}
}
