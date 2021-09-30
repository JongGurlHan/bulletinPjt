package project.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.demo.beans.ContentBean;
import project.demo.service.BoardService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
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
	public String read(){
		return "board/read";
	}

	@GetMapping("/write")
	public String write(@ModelAttribute("writeContentBean")ContentBean writeContentBean,
						@RequestParam("board_info_idx") int board_info_idx ){
		//어느 게시판에서 쓰는지 board_info_idx를 가져온다.

		writeContentBean.setContent_board_idx(board_info_idx);
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
	public String modify(){
		return "board/modify";
	}

	@GetMapping("/delete")
	public String delete(){
		return "board/delete";
	}
}
