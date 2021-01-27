package com.ty.java.jmockit.capturingexpectations;

import com.ty.java.jmockit.entity.Animal;
import com.ty.java.jmockit.entity.Dog;
import mockit.Capturing;
import mockit.Expectations;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CapturingExpectationsMock4SubClassTest {
    @Test
    void test_capturing_mock_subclass_by_father_class(@Capturing final Animal animal){
        new Expectations(){{
            animal.isHealthy();
            result = true;
        }};

        Dog dog = new Dog("", 2);
        Assertions.assertEquals(true, dog.isHealthy());
    }
}
