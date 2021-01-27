package com.ty.java.jmockit.testedinjectable;

import com.ty.java.jmockit.service.MailService;
import com.ty.java.jmockit.service.PetShopServiceImpl;
import com.ty.java.jmockit.service.UserService;
import mockit.Injectable;
import mockit.MockUp;
import mockit.Tested;
import org.junit.jupiter.api.Assertions;

/**
 * 1.37移除了MockUp类的getMockInstance（），所以不再使用MockUp来mock接口
 * Injectable和MockUp搭配来mock类也不能生效
 */
public class NotSupportTestedInjectableMockUpTest {
    //需要被测试的类
    @Tested
    PetShopServiceImpl petShopService;

    //外部依赖，需要被mock
    @Injectable
    MailService mailService;
    @Injectable
    UserService userService;

    void test_tested_injectable_mock_mailService() throws Exception {
        new MockUp<MailService>(){
            public Boolean sendMail(){
                return true;
            }
        };
        new MockUp<UserService>(){
            public Boolean check(){
                return true;
            }
        };

        Assertions.assertEquals(false, petShopService.check());
    }
}
