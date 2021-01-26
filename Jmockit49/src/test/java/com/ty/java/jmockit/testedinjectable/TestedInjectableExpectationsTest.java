package com.ty.java.jmockit.testedinjectable;

import com.ty.java.jmockit.service.MailService;
import com.ty.java.jmockit.service.PetShopServiceImpl;
import com.ty.java.jmockit.service.UserService;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *通过@Injectable，mock被依赖的类（接口），然后通过@Tested注解被测类，就可以实现mock依赖注入
 */
public class TestedInjectableExpectationsTest {
    //需要被测试的类
    @Tested
    PetShopServiceImpl petShopService;

    //外部依赖，需要被mock
    @Injectable
    MailService mailService;
    @Injectable
    UserService userService;

    @Test
    void test_tested_injectable_mock_mailService() throws Exception {
        new Expectations(){{
            mailService.sendMail();
            result = false;
            //需要被显示mock，因为默认是mock了所有的方法，boolean返回值为false.
            userService.check();
            result = true;
        }};

        Assertions.assertEquals(false, petShopService.check());
    }
    @Test
    void test_tested_injectable_mock_userService() throws Exception {
        new Expectations(){{
            //需要被显示mock，因为默认是mock了所有的方法，boolean返回值为false.
            mailService.sendMail();
            result = true;
            userService.check();
            result = false;
        }};

        Assertions.assertEquals(false, petShopService.check());
    }
}
