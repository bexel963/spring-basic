package com.my.spring3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.spring3.dao.BoardDao;
import com.my.spring3.dao.CommentDao;
import com.my.spring3.domain.CommentDto;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
    BoardDao boardDao;
    @Autowired
    CommentDao commentDao;
    @Autowired
    BoardService boardService;

//    @Autowired
//    public CommentServiceImpl(CommentDao commentDao, BoardDao boardDao) {
//        this.commentDao = commentDao;
//        this.boardDao = boardDao;
//    }

    @Override
	public int getCount(Integer bno) throws Exception{
        return commentDao.count(bno);
    }


    @Override
	@Transactional(rollbackFor = Exception.class)
    public int remove(Integer cno, Integer bno, String commenter) throws Exception{
    	
        int rowCnt = boardService.updateCommentCnt(bno, -1);
//        throw new Exception("test");
        rowCnt = commentDao.delete(cno, commenter);
        return rowCnt;
    }

    @Override
	@Transactional(rollbackFor = Exception.class) 
    public int write(CommentDto commentDto) throws Exception{
    	boardService.updateCommentCnt(commentDto.getBno(), 1);
//                throw new Exception("test");
        return commentDao.insert(commentDto);
    }

    @Override
	public List<CommentDto> getList(Integer bno) throws Exception{
//        throw new Exception("test");
        return commentDao.selectAll(bno);
    }

    @Override
	public CommentDto read(Integer cno) throws Exception{
        return commentDao.select(cno);
    }

    @Override
	public int modify(CommentDto commentDto) throws Exception{
        return commentDao.update(commentDto);
    }
}
