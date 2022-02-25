package com.nowcoder;

import com.nowcoder.model.*;
import com.nowcoder.util.JedisAdapter;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ToutiaoApplication.class)
public class JedisTests {
    @Autowired
    JedisAdapter jedisAdapter;

    @Test
    public void testJedis() {
        jedisAdapter.set("hello", "world");
        Assert.assertEquals("world", jedisAdapter.get("hello"));
    }
    @Test
    public void testObject() {
        User user = new User();
        user.setSalt("salt");
        user.setHeadUrl("http://images.nowcoder.com/head/100t.png");
        user.setPassword("pwd");
        user.setName("user1");
        jedisAdapter.setObject("u", user);

        User user1 = jedisAdapter.getObject("u", User.class);
        System.out.println(ToStringBuilder.reflectionToString(user1));
    }
}
