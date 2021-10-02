package project.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	public String write(@Valid @ModelAttribute("writeContentBean")ContentBean writeContentBean, BindingResult result){
		if(result.hasErrors()){
			return "board/write";
		}
		boardService.addContentInfo(writeContentBean);
		System.out.println(writeContentBean.getContent_board_idx());

		return "board/write_success";

	}

	@GetMapping("/modify")
	public String modify(@RequestParam("board_info_idx") int board_info_idx,
						 @RequestParam("content_idx") int content_idx,
						 Model model){
		return "board/modify";
	}

	@GetMapping("/delete")
	public String delete(){
		return "board/delete";
	}

	@GetMapping("/not_writer")
	public String not_writer(){
		return "board/not_writer";
	}
}
