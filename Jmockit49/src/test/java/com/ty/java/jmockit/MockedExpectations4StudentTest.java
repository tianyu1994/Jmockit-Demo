package com.ty.java.jmockit;

import com.ty.java.jmockit.entity.Student;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MockedExpectations4StudentTest {
    @Test
    public void testMockedExpectationsInMethodParameter(@Mocked Student student){
        String mockedInfomation = "mocked information";
        new Expectations(){
            {
                student.toString();
                this.result = mockedInfomation;
            }
        };
        Student student1 = new Student("maimai", "女", 18);
        Assertions.assertEquals(mockedInfomation, student1.toString());
    }
}
