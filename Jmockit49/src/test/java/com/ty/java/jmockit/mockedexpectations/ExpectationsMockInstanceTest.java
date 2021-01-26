package com.ty.java.jmockit.mockedexpectations;

import com.ty.java.jmockit.entity.Dog;
import mockit.Expectations;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 1.48移除了传入类名到Expectations构造函数的mock使用方法
 * 下面是将要mock的对象传入Expectations的构造函数
 */
public class ExpectationsMockInstanceTest {
    Dog dogGlobal = new Dog("DaHuang", 2);
    @Test
    void test_mock_a_instance(){
        //将要mock的对象传入Expectations的构造函数，可以mock实例
        Dog dog = new Dog("AHuang", 2);
        String expectedStr = "ha ha ha";
        new Expectations(dog){{
            dog.shut();
            result = expectedStr;
        }};

        Assertions.assertEquals(expectedStr, dog.shut());
        Assertions.assertNotEquals(expectedStr, dogGlobal.shut());//只满足mock实例
    }

}
