package com.ty.java.jmockit.mockedexpectations;

import com.ty.java.jmockit.entity.Dog;
import mockit.Expectations;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExpectationsReturnManyResultsInOrderTest {
    @Test
    void test_mock_a_instance(){
        String expectedStr1 = "ha ha ha";
        String expectedStr2 = "he he he";

        //将要mock的对象传入Expectations的构造函数，可以mock实例
        Dog dog = new Dog("AHuang", 2);
        new Expectations(dog){{
            dog.shut();
            returns(expectedStr1, expectedStr2);
        }};

        Assertions.assertEquals(expectedStr1, dog.shut());
        Assertions.assertEquals(expectedStr2, dog.shut());
    }

}
