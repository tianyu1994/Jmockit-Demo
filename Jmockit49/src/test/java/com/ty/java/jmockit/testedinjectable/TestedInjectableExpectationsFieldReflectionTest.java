package com.ty.java.jmockit.testedinjectable;

import com.ty.java.jmockit.entity.Dog;
import com.ty.java.jmockit.entity.PetShop;
import com.ty.java.jmockit.service.MailService;
import com.ty.java.jmockit.service.PetShopServiceImpl;
import com.ty.java.jmockit.service.UserService;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.internal.reflection.FieldReflection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * 通过@Injectable，mock被依赖的类（接口），然后通过@Tested注解被测类，就可以实现mock依赖注入，
 * mock被测试类的成员引用字段
 */
public class TestedInjectableExpectationsFieldReflectionTest {
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
        new Expectations() {{
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
        new Expectations() {{
            //需要被显示mock，因为默认是mock了所有的方法，boolean返回值为false.
            mailService.sendMail();
            result = true;
            userService.check();
            result = false;
        }};

        Assertions.assertEquals(false, petShopService.check());
    }

    /**
     * mock被测试类的成员引用字段
     * @throws Exception
     */
    @Test
    void test_tested_injectable_mock_petShopList() throws Exception {
        new Expectations() {{
            //需要被显示mock，因为默认是mock了所有的方法，boolean返回值为false.
            mailService.sendMail();
            result = true;
            userService.check();
            result = true;
        }};
        ArrayList<PetShop> petShopList = new ArrayList<>();
        PetShop petShop = new PetShop("MM Leyuan");
        petShop.add(new Dog("AHuang", 2));
        petShopList.add(petShop);
        //mock被测试类的引用字段
        FieldReflection.setFieldValue(PetShopServiceImpl.class.getDeclaredField("petShopList"), petShopService, petShopList);

        Assertions.assertEquals(true, petShopService.check());
    }
}
