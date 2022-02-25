package com.nowcoder;

import com.nowcoder.dao.CommentDAO;
import com.nowcoder.dao.LoginTicketDAO;
import com.nowcoder.dao.NewsDAO;
import com.nowcoder.dao.UserDAO;
import com.nowcoder.model.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ToutiaoApplication.class)
@Sql("/init-schema.sql")
public class InitDatabaseTests {
	@Autowired
	private UserDAO userDAO;

	@Autowired
	private NewsDAO newsDAO;

	@Autowired
	private LoginTicketDAO loginTicketDAO;

	@Autowired
	private CommentDAO commentDAO;

	@Test
	public void initData() {
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setName("user" + i);
			user.setPassword("111111");
			user.setSalt("aa");
			user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", random.nextInt(1000)));
			userDAO.addUser(user);

			News news = new News();
			news.setTitle(String.format("TITLE{%d}", i));
			news.setUserId(i + 1);
			news.setLikeCount(i + 1);
			news.setImage(String.format("http://images.nowcoder.com/head/%dm.png", random.nextInt(1000)));
			news.setLink(String.format("http://www.nowcoder.com/%d.html", i));
			Date date = new Date();
			date.setTime(date.getTime() + 1000 * 3600 * 5 * i);
			news.setCreatedDate(date);
			newsDAO.addNews(news);

			for (int i1 = 0; i1 < 3; i1++) {
				Comment comment = new Comment();
				comment.setUserId(i + 1);
				comment.setEntityId(news.getId());
				comment.setEntityType(EntityType.ENTITY_NEWS);
				comment.setCreatedDate(new Date());
				comment.setStatus(0);
				comment.setContent("Comment " + String.valueOf(i1));
				commentDAO.addComment(comment);
			}

			user.setPassword("newpassword");
			userDAO.updatePassword(user);

			LoginTicket ticket = new LoginTicket();
			ticket.setUserId(i + 1);
			ticket.setTicket(String.format("TICKET%d", i + 1));
			ticket.setExpired(new Date());
			ticket.setStatus(0);
			loginTicketDAO.addTicket(ticket);

			loginTicketDAO.updateStatus(ticket.getTicket(), 2);
		}

		Assert.assertEquals("newpassword", userDAO.selectById(1).getPassword());
		userDAO.deleteById(1);
		Assert.assertNull(userDAO.selectById(1));

		Assert.assertEquals(1, loginTicketDAO.selectByTicket("TICKET1").getUserId());
		Assert.assertEquals(2, loginTicketDAO.selectByTicket("TICKET1").getStatus());
	}
}
