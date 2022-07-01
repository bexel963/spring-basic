package com.my.spring3.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.my.spring3.domain.CommentDto;
import com.my.spring3.service.CommentService;

//@ResponseBody
//@Controller
@RestController
public class CommentController {

	@Autowired
	CommentService commentService;
	
	@GetMapping("/comments")
	public ResponseEntity<List<CommentDto>> list(Integer bno){
		
		List<CommentDto> list = null;
		
		try {
			list = commentService.getList(bno);
			System.out.println("list = " + list);
			return new ResponseEntity<List<CommentDto>>(list, HttpStatus.OK);
		} catch (Exception e) {			
			e.printStackTrace();
			return new ResponseEntity<List<CommentDto>>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
				// {cno}�� uri�� ���Ե� ���̱� ������ �� ���� �޴� �Ű����� �տ� @PathVariable�� �ٿ�����Ѵ�.
	@DeleteMapping("/comments/{cno}")		// /comments/cno?bno=2331   <-- ������ ��� ��ȣ(cno)
	public ResponseEntity<String> remove(@PathVariable Integer cno, Integer bno, HttpSession session){
		
//		String commenter = (String)session.getAttribute("id");
		
		String commenter = "qwer";
		
		try {
			int rowCnt = commentService.remove(cno, bno, commenter);
			
			if(rowCnt != 1) {
				throw new Exception("Delete Failed");
			}
			return new ResponseEntity<>("DEL_OK", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("DEL_ERR", HttpStatus.BAD_REQUEST);
		}	
	}
	
	@PostMapping("/comments")
	public ResponseEntity<String> write(@RequestBody CommentDto dto, Integer bno, HttpSession session){
		
//		postman -> Hearders�� Ŭ�� �� Content-type-application/json �Է� -> raw �� ���� �� �Ʒ� �ڵ� �Է� (json���� ��û�ϴ� ��)
//			{
//			"pcno" : 0,
//			"comment" : "hihihihi",
//			}
		
		
//		String commenter = (String)session.getAttribute("id");
		String commenter = "qwer";
		
		dto.setCommenter(commenter);
		dto.setBno(bno);
		
		try {
			int rowCnt = commentService.write(dto);
			
			return new ResponseEntity<>("WRT_OK", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("WRT_ERR", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PatchMapping("/comments/{cno}")
	public ResponseEntity<String> modify(@PathVariable Integer cno, @RequestBody CommentDto dto ,HttpSession session){

//		postman -> Hearders�� Ŭ�� �� Content-type-application/json �Է� -> raw �� ���� �� �Ʒ� �ڵ� �Է� (json���� ��û�ϴ� ��)
//			{
//			"pcno" : 0,
//			"comment" : "hihihihi",
//			"commenter" : "qwer"
//			}
//		String commenter = (String)session.getAttribute("id");
		String commenter = "qwer";
		
		dto.setCommenter(commenter);
		dto.setCno(cno);
		
		try {
			int rowCnt = commentService.modify(dto);
			
			System.out.println("dto = " + dto);
			return new ResponseEntity<>("MOD_OK", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("MOD_ERR", HttpStatus.BAD_REQUEST);
		}
	}
}
