package com.my.spring3;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.my.spring3.dao.CommentDao;
import com.my.spring3.domain.CommentDto;

@RunWith(SpringJUnit4ClassRunner.class)		
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class CommentDaoTest {

	@Autowired
    CommentDao commentDao;

	@Test
	public void sampleInsert() {
		
		for(int i=0 ; i<5 ; i++) {			
			CommentDto commentDto = new CommentDto(2332, 0, "comment"+i, "qwer");
			commentDao.insert(commentDto);
		}
	}
    @Test
    public void count() {
        commentDao.deleteAll(1);
        assertTrue(commentDao.count(1)==0);
    }

    @Test
    public void delete() {
        commentDao.deleteAll(1);
        CommentDto commentDto = new CommentDto(1, 0, "comment", "asdf");
        assertTrue(commentDao.insert(commentDto)==1);
        assertTrue(commentDao.count(1)==1);
    }

    @Test
    public void insert()  {
        commentDao.deleteAll(1);
        CommentDto commentDto = new CommentDto(1, 0, "comment", "asdf");
        assertTrue(commentDao.insert(commentDto)==1);
        assertTrue(commentDao.count(1)==1);

        commentDto = new CommentDto(1, 0, "comment", "asdf");
        assertTrue(commentDao.insert(commentDto)==1);
        assertTrue(commentDao.count(1)==2);
    }

    @Test
    public void selectAll()  {
        commentDao.deleteAll(1);
        CommentDto commentDto = new CommentDto(1, 0, "comment", "asdf");
        assertTrue(commentDao.insert(commentDto)==1);
        assertTrue(commentDao.count(1)==1);

        List<CommentDto> list = commentDao.selectAll(1);
        assertTrue(list.size()==1);

        commentDto = new CommentDto(1, 0, "comment", "asdf");
        assertTrue(commentDao.insert(commentDto)==1);
        assertTrue(commentDao.count(1)==2);

        list = commentDao.selectAll(1);
        assertTrue(list.size()==2);
    }

    @Test
    public void select(){
        commentDao.deleteAll(1);
        CommentDto commentDto = new CommentDto(1, 0, "comment", "asdf");
        assertTrue(commentDao.insert(commentDto)==1);
        assertTrue(commentDao.count(1)==1);

        List<CommentDto> list = commentDao.selectAll(1);
        String comment = list.get(0).getComment();
        String commenter = list.get(0).getCommenter();
        assertTrue(comment.equals(commentDto.getComment()));
        assertTrue(commenter.equals(commentDto.getCommenter()));
    }

    @Test
    public void update()  {
        commentDao.deleteAll(1);
        CommentDto commentDto = new CommentDto(1, 0, "comment", "asdf");
        assertTrue(commentDao.insert(commentDto)==1);
        assertTrue(commentDao.count(1)==1);

        List<CommentDto> list = commentDao.selectAll(1);
        commentDto.setCno(list.get(0).getCno());
        commentDto.setComment("comment2");
        assertTrue(commentDao.update(commentDto)==1);

        list = commentDao.selectAll(1);
        String comment = list.get(0).getComment();
        String commenter = list.get(0).getCommenter();
        assertTrue(comment.equals(commentDto.getComment()));
        assertTrue(commenter.equals(commentDto.getCommenter()));
    }

}
