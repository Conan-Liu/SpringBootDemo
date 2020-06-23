package com.conan.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * SpringRunner类是SpringJUnit4ClassRunner的一个别名，两者都可以用
 *
 * SpringBoot单元测试主要是测试Service业务逻辑是否正确，Dao层也可以
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public abstract class SpringBaseTest {
}
