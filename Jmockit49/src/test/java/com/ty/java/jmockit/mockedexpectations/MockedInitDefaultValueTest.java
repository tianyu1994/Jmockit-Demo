package com.ty.java.jmockit.mockedexpectations;

import com.ty.java.jmockit.entity.Animal;
import com.ty.java.jmockit.entity.Dog;
import com.ty.java.jmockit.entity.PetShop;
import mockit.Mocked;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MockedInitDefaultValueTest {
    @Mocked
    PetShop petShop;
    @Mocked
    Dog dog;

    @Test
    void test_mocked_class_init_default_value(){
        //mock对象的方法返回值为基本类型
        Assertions.assertEquals(0.0, dog.getPrice());
        Assertions.assertEquals(0, dog.getAge());
        Assertions.assertEquals(false, petShop.getIsOpen());

        //mock对象的方法返回值为String
        Assertions.assertEquals(null, petShop.getShopName());

        //mock对象的方法返回值为非String的引用类型
        Assertions.assertTrue(petShop.getAnimal("12344") instanceof Animal);
    }
}
