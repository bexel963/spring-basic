package com.my.spring3.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.my.spring3.domain.BoardDto;
import com.my.spring3.domain.CommentDto;
import com.my.spring3.domain.PageHandler;
import com.my.spring3.domain.SearchCondition;
import com.my.spring3.service.BoardService;
import com.my.spring3.service.CommentService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	BoardService boardService;
	@Autowired
	CommentService commentService;
	
	@GetMapping("/list")
	public String list(SearchCondition sc, Model m, HttpServletRequest request) {
		
		System.out.println("sc = " + sc);
		// 1. 세션을 얻는다.
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		if(id==null) {
//			return "redirect:/login/login";	// 로그인을 안 했으면 로그인 화면으로 이동.
			return "redirect:/login/login?toURL="+request.getRequestURL();
		}
		
		int totalCnt = boardService.getSearchResultCnt(sc);
		
		PageHandler pageHandler = new PageHandler(totalCnt, sc);
		
		List<BoardDto> list = boardService.getSearchResultPage(sc);
		
		m.addAttribute("list", list);
		m.addAttribute("ph", pageHandler);
		
		return "boardList";
	}
	
	@GetMapping("/detail")
	public String detail(SearchCondition sc, Integer bno, Model m) {
		
		BoardDto dto = boardService.read(bno);
		
		try {
			List<CommentDto> clist = commentService.getList(bno);
			m.addAttribute("clist", clist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		m.addAttribute("dto", dto);
		m.addAttribute("page", sc.getPage());
		m.addAttribute("pageSize", sc.getPageSize());
		
		return "boardDetail";
	}
	
	@PostMapping("/remove")
	public String remove(SearchCondition sc, Integer bno, Model m, HttpSession session, RedirectAttributes rattr) {
		
		String writer = (String)session.getAttribute("id");
		
		Map map = new HashMap();
		
		map.put("bno", bno);
		map.put("writer", writer);
		
		try {
			m.addAttribute("page", sc.getPage());
			m.addAttribute("pageSize", sc.getPageSize());
			
			int rowCnt = boardService.remove(map);
			
			if(rowCnt!=1) {
				throw new Exception("board remove error");
			}
			
			rattr.addFlashAttribute("msg", "DEL_OK");	// 세션에 잠깐 저장했다가 한번 쓰고 지워버린다.
			
		} catch (Exception e) {
			e.printStackTrace();
			rattr.addFlashAttribute("msg", "DEL_ERR");
		}
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/write")
	public String write() {
		
		return "boardRegis";
	}
	
	@PostMapping("/write")
	public String regis(BoardDto dto, RedirectAttributes rattr, Model m) {
		
		try {
			int rowCnt = boardService.write(dto);
			
			if(rowCnt != 1)
				throw new Exception("Write failed");
			
			rattr.addFlashAttribute("msg", "WRT_OK");
			
		} catch (Exception e) {
			e.printStackTrace();
			m.addAttribute("dto", dto); 
			rattr.addFlashAttribute("msg", "WRT_ERROR ");
			return "boardRegis";
		}
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/amend")
	public String amend(int bno, Model m) {
		
		BoardDto dto = boardService.read(bno);
		
		m.addAttribute("dto", dto);
		
		return "boardModify";
	}
	
	@PostMapping("/amend")
	public String amendPost(BoardDto dto, RedirectAttributes rattr) {
		
		
		boardService.modify(dto);
		
		rattr.addFlashAttribute("msg", "AMEND_OK");
		
		return "redirect:/board/list";
	}
}
