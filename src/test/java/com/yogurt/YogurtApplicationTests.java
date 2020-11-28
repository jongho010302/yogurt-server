package com.yogurt;

import com.yogurt.api.staff.controller.admin.AdminStaffController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YogurtApplicationTests {

    @Autowired
    AdminStaffController userService;

    @Test
    public void contextLoads() {
        assertNotNull(userService);
    }
}
