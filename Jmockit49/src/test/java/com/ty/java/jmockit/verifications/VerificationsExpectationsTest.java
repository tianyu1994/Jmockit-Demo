package com.ty.java.jmockit.verifications;

import com.ty.java.jmockit.entity.Dog;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VerificationsExpectationsTest {
    @Mocked
    Dog dogGlobal;

    @Test
    public void testMockedExpectationsInGlobal(){
        String mockedInfomation = "mocked information";
        new Expectations(){
            {
                dogGlobal.shut();
                this.result = mockedInfomation;
            }
        };

        System.out.println(dogGlobal.shut());
        System.out.println(dogGlobal.isHealthy());
        System.out.println(dogGlobal.isHealthy());

        new Verifications(){{
            dogGlobal.shut();
            times = 1;

            dogGlobal.isHealthy();
            minTimes = 1;

            dogGlobal.isHealthy();
            maxTimes = 5;
        }};
    }
}
