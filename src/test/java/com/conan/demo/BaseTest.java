package com.conan.demo;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Conan on 2019/1/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public abstract class BaseTest {
    Logger log = LoggerFactory.getLogger(this.getClass());
}