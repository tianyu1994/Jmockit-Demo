package com.ty.java.jmockit.mockedexpectations;

import com.ty.java.jmockit.entity.Animal;
import com.ty.java.jmockit.entity.Dog;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 使用Mocked Dog 作为测试类属性，作用整个类
 *
 */
public class MockedExpectationsRangeInClassTest {
    @Mocked
    Dog dogGlobal;

    @Test
    public void testMockedExpectationsInGlobal(){
        String mockedInfomation = "mocked information";
        new Expectations(){
            {
                dogGlobal.toString();
                this.result = mockedInfomation;
            }
        };

        Dog dogLocal = new Dog("", 2);
        Assertions.assertEquals(mockedInfomation, dogGlobal.toString());
        Assertions.assertEquals(mockedInfomation, dogLocal.toString());
    }


}
