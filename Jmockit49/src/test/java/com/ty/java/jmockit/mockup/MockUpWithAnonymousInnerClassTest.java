package com.ty.java.jmockit.mockup;

import com.ty.java.jmockit.entity.Animal;
import com.ty.java.jmockit.entity.Dog;
import com.ty.java.jmockit.entity.PetShop;
import mockit.Mock;
import mockit.MockUp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * 下将要mock的对象传入MockUp模板中，可以mock整个类的方法
 */
public class MockUpWithAnonymousInnerClassTest {
    Integer expectedAnimalSize = 10000;
    String expectedShopName = "maimai kaixinguo";
    Dog expectedDog = new Dog("AHuang", 2);

    public class MockUpPetShop extends MockUp<PetShop>{
        //只给要想mock的方法加上@Mock，为@Mock的方法不受影响
        @Mock
        public void $init(String shopName){}

        @Mock
        public Integer getMaxAnimalSize() {
            return expectedAnimalSize;
        }

        @Mock
        public Animal getAnimal(String id){
            return expectedDog;
        }

        @Mock
        public Boolean add(Animal animal){
            return true;
        }

        @Mock
        public Boolean getIsOpen(){
            return true;
        }
    }

    @Test
    void test_mock_various_method_with_different_parameter(){
        new MockUpPetShop();

        PetShop petShop = new PetShop("maimain leyuan");
        Assertions.assertEquals(expectedAnimalSize, PetShop.getMaxAnimalSize());
        Assertions.assertEquals(null, petShop.getShopName());
        Assertions.assertEquals(expectedDog, petShop.getAnimal("aHuang"));
        Assertions.assertEquals(true, petShop.add(new Dog("", 2)));
        Assertions.assertEquals(true, petShop.getIsOpen());
    }

}
