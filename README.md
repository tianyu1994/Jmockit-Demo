# JmockitDemo 1.49
Demo for using Jmockit


### 一、支持：

1、部分mock；

2、接口mock；

3、无需@runwith注解；

4、mock 构造方法、final方法、静态方法、（不支持私有方法）；

5、对实例化对象mock；

### 二、POM依赖
1、jmockit依赖

    <dependency>
        <groupId>org.jmockit</groupId>
        <artifactId>jmockit</artifactId>
        <version>${jmockit.version}</version>
        <scope>test</scope>
    </dependency>
        
2、junit5依赖

    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.6.2</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.junit.vintage</groupId>
        <artifactId>junit-vintage-engine</artifactId>
        <version>5.6.2</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.junit.platform</groupId>
        <artifactId>junit-platform-launcher</artifactId>
        <version>1.6.2</version>
        <scope>test</scope>
    </dependency>
        
注意：确保jmockit依赖在junit依赖 之前

### 三、注解说明
**@Mocked**：注解类的所有实例、所有方法都被mock（见测试类：MockedInitDefaultValueTest）

    用于修饰一个类、抽象类、接口,生成一个其对应的Mocked对象，并初始化该对象生成默认值，其依赖的对象递归初始化。
    
**@Tested**：注解对象为**被测试类**实例对象（见测试类：TestedInjectableExpectationsTest）

**@Injectable**：常和@Tested连用，用于注入被测试类依赖的对象（见测试类：TestedInjectableExpectationsTest）

**@Capturing**：注解的类或接口的子类或实例都将被mock（见测试类：CapturingExpectationsMock4SubClassTest）

**@Mock**：用于MockUp内部，重写方法逻辑（见测试类：MockUpWithAnonymousInnerClassTest）

### 四、API说明
#### 1、Jmockit使用代码结构
    1、声明
    2、录制
    3、调用
    4、验证（非必须）

#### 2.1、Expectations语法
    new Expectations(){//Expectations的匿名内部类
    {//匿名内部的初始化代码块
    
        //重写方法
        animal.isHealthy();
        //放回值赋值，紧跟重写方法后面
        result = true;
        
        //以此重复...
        
        
        //native, private方法不能Mock
    }};
    
    //在一个测试 方法 中可以多个new Expectations(){};
    
#### 2.2 Expectations用法 
    1、通过引用外部类Mock对象（@Injectable、 @Mocked、@Capturring 注解的对象)来录制，此方法将会mock类的所有实例化对象的所有方法，
    被录制的方法返回预设值，未被录制的方法返回此方法返回类型的默认值。
    2、通过传入一个实例化对象到Expectations构造函数，此方法将会只mock该实例化对象的被录制方法，未被录制的以及其他实例化对象方法均不会被mock
    
    使用Expectations录制的方法必须被调用，不然会报 missing invocation错误。
    
#### 3.1 MockUp & @Mock 语法
    1、版本1.37移除了MockUp类的getMockInstance（），所以不再使用MockUp来mock接口
    
    2、使用语法一(见：MockUpWithInheritClassTest)
    //只给要想mock的方法加上@Mock，为@Mock的方法不受影响
    new MockUp<PetShop>(){
        @Mock
        public void $init(String shopName){}

        @Mock
        public Integer getMaxAnimalSize() {
            return expectedAnimalSize;
        }
        ...
        //private方法不能Mock
    };
    
    3、使用语法二(见：MockUpWithAnonymousInnerClassTest)
    public class MockUpPetShop extends MockUp<PetShop>{
        //只给要想mock的方法加上@Mock，为@Mock的方法不受影响
        @Mock
        public void $init(String shopName){}

        @Mock
        public Integer getMaxAnimalSize() {
            return expectedAnimalSize;
        }

        //...
        //private方法不能Mock
    }
    
#### 3.2 MockUp & @Mock 用法
    MockUp & @Mock可以解决大部分的Mock场景，除：
        1、一个类有多个实例，只对其中一个某个实例进行mock
        2、AOP动态生成的mock
        3、对类的所有方法都要mock时，书写MockUp工作量大
    使用场景：项目中通用类的mock，可避免大量的new Expectations{}出现。
    
#### 4. Verifications(见测试类：VerificationsExpectationsTest)
    Verifications用于做验证。验证mock对象（即@Mocked, @Injectable，@Capturing修饰、或者传入Expectations构造函数的对象）有没有调用过某方法，调用了多少次。
    new Verifications(){{
        dogGlobal.shut();
        times = 1;

        dogGlobal.isHealthy();
        minTimes = 1;

        dogGlobal.isHealthy();
        maxTimes = 5;
    }};
    
    times=1代表该方法调用了2次，不能多也不能少。除此之外minTimes和maxTimes用来验证最少和最多次数。
    new VerificationInOrder(){{}};验证调用方法的次序。
    
### 五、小结
Jockit 支持mock一类多实例、mock泛型、同一方法调用返回时序结果（见：ExpectationsReturnManyResultsInOrderTest）、定制返回结果、在mock中做AOP、级联编程、mock spring bean、mock dubbo消费bean、mock MQ消息生产者。