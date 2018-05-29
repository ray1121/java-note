
## Java基础归纳总结

`javac` 将java源代码（.java）编译成可以在java虚拟上执行的字节码(.class)，`java` java虚拟机开始执行字节码。

对象=状态+行为（属性+方法）

**对象是一个高内聚的服务供应商**

1. **方法**是对对象内部状态的操作，并作为 **对象和对象之间通信的主要机制**。
2. 程序是一大堆对象的组合；通过 **消息的传递**，使得各个对象知道他们应该做什么，可以 **将消息看做是一次调用请求**
3. 每个对象都有自己的存储空间，也可以容纳其他的对象，通过封装现有的对象可以创建出新的对象。
4. 一个 **类** 最重要的特征就是 **可以将什么消息传递给他**
5. 同一个类的所有对象都能接受相同的消息。所以可以 **通过程序代码统一指挥一个类去控制所有满足这个类的描述的对象**

### 类

类就是创建单个对象的设计图纸

```java
class Bicycle {
    /*状态*/
    int cadence = 0;
    int speed = 0;
    int gear = 1;
    /*方法*/
    void changeCadence(int newValue) {
         cadence = newValue;
    }

    void changeGear(int newValue) {
         gear = newValue;
    }

    void speedUp(int increment) {
         speed = speed + increment;   
    }

    void applyBrakes(int decrement) {
         speed = speed - decrement;
    }

    void printStates() {
         System.out.println("cadence:" +
             cadence + " speed:" + 
             speed + " gear:" + gear);
    }
}
```

**完整的应用程序（可以直接运行的）类中必须要包含一个main方法** `public static void main(String[] args)`

### 开发类
 **伪码**、**测试码**、**真实码**

### 接口（Interface）

每一个对象只能够接收特定的请求，能够接收什么样的请求就是接口定义的。

### 语法要点（与JS语法习惯不同的地方）

* 条件判断 **只能用 `boolean` 类型进行条件测试**，不能使用JS中的整型等数据类型来作为条件判断。
* 定义数组 `String[] arr = {'1','12'}` 
* 必须声明所有变量的值。
* **java 通过拷贝传递，变量不会**
* **实例变量**在声明之后没有初始化就直接get就会得到他们的默认值，但是声明在方法中的 **局部变量** 只声明不报错就会导致编译报错；
* JS:`parseInt` ; java : `Integer.parseInt`不同点在于JS会把不是表示数字的字符串转化为`NAN`,java面对这种情况直接回抛出异常。
* java中的`for(:)`用法相当于JS中的`for(in)`


### 继承原则

所能形成（设计）继承的应该是可以被描述成“IS-A”的关系，比如三角形是形状

1. 子类比父类更加具有特定意义（猫科动物继承动物是合理的）
2. 行为程序（类）应该被多个基本类型的类所包含（比如“发出声音”，要被动物，乐器所运用`HAS-A`比较合理）



### 变量类型

Java 8种基本类型：整形4种，浮点2种，布尔，字符`char`

* 默认小数是 `double` 类型（长度64位）因此不能直接给float初始化赋值小数应该在小数后面加一个f  `float num = 1.22f`
* 字符char使用单引号，只能放一个字符

**字面值**

1. 整数字面值：十进制，十六进制（0x开头）0x121a，八进制（0开头）032，二进制（0b开头）0b1011
2. 浮点数字面值， **科学计数法e2表示10的二次方** 1.234e2 = 1.234 * 10^2

**精度转化**

1. 低精度向高精度转化：直接转
2. 高精度向低精度转化就会要使用 **强制转化** `b =(byte) i2;` **若i2超过127则会先转化成二进制然后按照byte的精度8位进行截取，然后转化**（i2的值是300，其对应的二进制数是 100101100，按照byte的长度8位进行截取后，其值为 00101100 即44）

### 访问修饰符

**private** 私有的 
**package/friendly/default** 不写 
**protected** 受保护的 
**public** 公共的 

* 不写修饰符的变量则代表 package/friendly/default

**明确类之间的关系**：

1. 类自身
2. 同包子类
3. 同包非子类
4. 不同包子类
5. 其他类（不同包也非子类）

**所以有了不同的访问控制符**

* **private**
    * 类自身：可以访问
    * 同包子类：**不能继承**
    * 同包非子类：**不能访问**
    * 不同包子类：**不能继承**
    * 其他类：**不能访问**

* **package/default/friendly 或者不写**
    * 类自身：可以访问
    * 同包子类：可以继承
    * 同包非子类：可以访问
    * 不同包子类：**不可以继承**
    * 其他类（不同包也非子类）：**可以访问**

* **protected**
    * 类自身：可以访问
    * 同包子类：可以继承
    * 同包非子类：可以访问
    * 不同包子类：可以继承
    * 其他类：**不可以访问**

* **public**
    * 都可以访问或者继承

**总结：**访问控制修饰词中 **protected**对 **package/default/friendly 或者不写**提升了一个 **不同包子类**的可以继承权限。 开放程度依次递增。

* **static** 
    * 使用 `static` 修饰的**属性**是类属性，**类属性代表着所有的实例对象都共享这一份属性**，**使用类名.的方式或者对象.的方式都能访问和改变他的值**。当所用的实例对象的某一个属性都是一样的时候，那么他们就可以使用static修饰。
    * 静态方法,与静态属性一样同样 **都可以使用类名调用或者对象名调用**

面对什么时候设计成类方法（属性）的时候都是看方法中是否涉及到对象的个性化属性的原则进行涉及，没有的就能够用static修饰成类方法。

```java
package charactor;
 
public class Hero {
    public String name;
    protected float hp;
 
    //实例方法,对象方法，非静态方法
    //必须有对象才能够调用
    public void die(){
        hp = 0;
    }
     
    //类方法，静态方法
    //通过类就可以直接调用
    public static void battleWin(){
        System.out.println("battle win");
    }
     
    public static void main(String[] args) {
           Hero garen =  new Hero();
           garen.name = "盖伦";
           //必须有一个对象才能调用
           garen.die();
            
           Hero teemo =  new Hero();
           teemo.name = "提莫";
            
           //无需对象，直接通过类调用
           Hero.battleWin();
         
    }
}
```




* `final`：当一个变量被final修饰的时候，**只有一次被赋值的机会**
* 

### 方法的重写与可变参数

```java
 // 可变数量的参数
    public void attack(Hero... heros) {
        for (int i = 0; i < heros.length; i++) {
            System.out.println(name + " 攻击了 " + heros[i].name);
 
        }
    }

```
可变参数是一个数组

### 构造方法

1. 方法名与类名相同
2. 没有返回类型
3. 不写构造方法就会默认有一个无参的构造方法

```java
public class Hero {
 
    String name;
    float hp;
    float armor;
    int moveSpeed;
 
    // 方法名和类名一样（包括大小写）
    // 没有返回类型
    public Hero() {
        System.out.println("实例化一个对象的时候，必然调用构造方法");
    }
     
    public static void main(String[] args) {
        //实例化一个对象的时候，必然调用构造方法
        Hero h = new Hero();
    }
 
}
```

**注意：**一旦提供了一个有参的构造方法，在创建对象的时候没有传入构造参数，因为默认的构造函数已经没有了，所以就会报错，“实际参数列表和形式参数列表长度不同”

**构造函数也可以重写**

```java
public class Hero {      
    String name; //姓名
       
    float hp; //血量
       
    float armor; //护甲
       
    int moveSpeed; //移动速度
       
    //带一个参数的构造方法
    public Hero(String heroname){ 
        name = heroname;
    }
     
    //带两个参数的构造方法
    public Hero(String heroname,float herohp){ 
        name = heroname;
        hp = herohp;
    }
       
    public static void main(String[] args) {
        Hero garen =  new Hero("盖伦"); 
        Hero teemo =  new Hero("提莫",383);
    }
     
}
```

### this

* this 指代当前对象这一点没有太大的区别
* **通过this调用其他构造函数使用this()**

```java
public class Hero {
        
    String name; //姓名
        
    float hp; //血量
        
    float armor; //护甲
        
    int moveSpeed; //移动速度
        
    //带一个参数的构造方法
    public Hero(String name){
        System.out.println("一个参数的构造方法");
        this.name = name;
    }
      
    //带两个参数的构造方法
    public Hero(String name,float hp){
        this(name);//调用只有一个参数的构造方法
        System.out.println("两个参数的构造方法");
        this.hp = hp;
    }
 
    public static void main(String[] args) {
        Hero teemo =  new Hero("提莫",383);       
        System.out.println(teemo.name);
         
    }
      
}
```

上述代码打印：

```
一个参数的构造方法
两个参数的构造方法
提莫
```

### 传参问题

Java中的基本类型传入参数是通过 **拷贝传递**，引用类型传入参数是通过共同指向引用的地址来进行传递。

### 包

* `package base;`**在最开始的地方声明该类所处的包名**
* 使用其他包下的类的时候必须使用 `import` 关键字： `import base.test;`

### 属性的初始化

注意一种：**初始化块** 和 **静态初始化块**

```java
package charactor;
 
public class Hero {
    public String name;
    protected float hp;
    float maxHP;
    //物品栏的容量
    public static int itemCapacity=8; //声明的时候 初始化

    static{
        itemCapacity = 6;//静态初始化块 初始化
    }
     
    {
        maxHP = 200; //初始化块
    }  
    public Hero(){
         
    }
     
    public static void main(String[] args) {
        System.out.println(Hero.itemCapacity);
    }
     
}
```


### 单例模式

**指的是在JVM中只有一个实例存在**分为：饿汉单例模式 和 懒汉单例模式

三要素：
* 构造函数私有化（private）
* 私有静态属性指向实例（private static）
* 公有静态属性返回第二部的实例

```java
package charactor;
//饿汉
public class GiantDragon {
 
    //私有化构造方法使得该类无法在外部通过new 进行实例化
    private GiantDragon(){
         
    }
 
    //准备一个类属性，指向一个实例化对象。 因为是类属性，所以只有一个
 
    private static GiantDragon instance = new GiantDragon();
     
    //public static 方法，提供给调用者获取12行定义的对象
    public static GiantDragon getInstance(){
        return instance;
    }
     
}
```

```java
package charactor;
//懒汉
public class GiantDragon { 
    //私有化构造方法使得该类无法在外部通过new 进行实例化
    private GiantDragon(){       
    } 
    //准备一个类属性，用于指向一个实例化对象，但是暂时指向null
    private static GiantDragon instance;     
    //public static 方法，返回实例对象
    public static GiantDragon getInstance(){
        //第一次访问的时候，发现instance没有指向任何对象，这时实例化一个对象
        if(null==instance){
            instance = new GiantDragon();
        }
        //返回 instance指向的对象
        return instance;
    }
      
}
```

### 枚举类型

枚举 `enum` 是一种特殊的类，方便于定义常量。使用`枚举类.values()`取值遍历。`for (Season s : Season.values())`.


```java
pubulic enum Season{
    SPRING,SUMMER,AUTUMN,WINTER
}

public class HelloWorld {
    public static void main(String[] args) {
        //遍历
        for (Season s : Season.values()) {
            System.out.println(s);
        }
    }
}
```

### 异常处理

```java
try{

}catch(FileNotFoundException | ParseException e){

}finally{
    System.out.println("一定会执行的方法。");  
}
```

**可以将方法的错误抛出值调用栈下层执行捕获**

```java
package exception;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
 
public class TestException {
 
    public static void main(String[] args) {
        method1();
 
    }
 
    private static void method1() {
        try {
            method2();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
 
    }
 
    private static void method2() throws FileNotFoundException {
        //方法2不对错误进行处理而是抛给上层调用他的method1处理
        File f = new File("d:/LOL.exe");
        System.out.println("试图打开 d:/LOL.exe");
        new FileInputStream(f);
        System.out.println("成功打开");
 
    }
}
```

* **异常类型：**
    * 可查异常，必须处理（try-catch，throws），如果不处理那么编译器就不会通过。
    * 运行时异常，不是必须处理的额，编译时也不会不通过。
        * 除数不能为零
        * 下标越界
        * 空指针异常

![异常错误分类](img/异常与错误.png)

**创建自定义异常**

* 步骤：
    * 继承 Exception 类
    * 提供**两个**构造函数方法：
        * 无参构造函数
        * 有参构造函数，并且**调用父类的构造函数**

```java
class EnemyHeroIsDeadException extends Exception{
     
    public EnemyHeroIsDeadException(){
         
    }
    public EnemyHeroIsDeadException(String msg){
        super(msg);
    }
}
```
**抛出自定义异常**

* 步骤：

1. 创建一个EnemyHeroIsDeadException实例
2. 通过throw 抛出该异常
3. **当前方法**通过 throws 抛出该异常

```java
package charactor;  
public class Hero {
    public String name;
    protected float hp;
 
    public void attackHero(Hero h) throws EnemyHeroIsDeadException{
        if(h.hp == 0){
            throw new EnemyHeroIsDeadException(h.name + " 已经挂了,不需要施放技能" );
        }
    }
 
    public String toString(){
        return name;
    }
     
    class EnemyHeroIsDeadException extends Exception{       
        public EnemyHeroIsDeadException(){
             
        }
        public EnemyHeroIsDeadException(String msg){
            super(msg);
        }
    }
      
    public static void main(String[] args) {
         
        Hero garen =  new Hero();
        garen.name = "盖伦";
        garen.hp = 616;
 
        Hero teemo =  new Hero();
        teemo.name = "提莫";
        teemo.hp = 0;
         
        try {
            garen.attackHero(teemo);
             
        } catch (EnemyHeroIsDeadException e) {
            // TODO Auto-generated catch block
            System.out.println("异常的具体原因:"+e.getMessage());
            e.printStackTrace();
        }
         
    }
}
```

在外部调用attack方法的时候，就需要进行捕捉，并且捕捉的时候，**可以通过e.getMessage() 获取当时出错的具体原因**

### I/O

* 文件对象
    * `import java.io.File`
    * `File f = new File("d:/test.txt");`
    * 常用方法：
        * f.exists()
        * f.isDirectory()判断是否是目录
        * f.isFile()
        * f.length()文件长度
        * f.lastModified()最后修改时间
        * 文件api参考[http://how2j.cn/k/io/io-file/345.html#nowhere](http://how2j.cn/k/io/io-file/345.html#nowhere)
    * 文件流：
        * `import java.io.FileInputStream;`
        * `FileInputStream fis = new FileInputStream(f);`输入输出（OutputStream）流