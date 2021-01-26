package com.ty.java.jmockit.mockedexpectations;

import com.ty.java.jmockit.entity.Animal;
import com.ty.java.jmockit.entity.Dog;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MockedExpectationRangeInMethodTest {
    /**
     * 使用Mocked Dog 作为测试方法参数，作用本方法
     */
    @Test
    public void testMockedExpectationsInMethodParameter(@Mocked Dog dog){
        String mockedInfomation = "mocked information";
        new Expectations(){
            {
                dog.toString();
                this.result = mockedInfomation;
            }
        };

        Dog dogLocal = new Dog("", 2);
        Assertions.assertEquals(mockedInfomation, dogLocal.toString());
        Assertions.assertEquals(mockedInfomation, dog.toString());
    }
}
