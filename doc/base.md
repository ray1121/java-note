
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
* `println` 与 `print` 不同点在于前者会在结束打印之后插入换行符。
* 定义数组 `String[] arr = {'1','12'}` 
* 必须声明所有变量的值。
* **java 通过拷贝传递，变量不会**
* **实例变量**在声明之后没有初始化就直接get就会得到他们的默认值，但是声明在方法中的 **局部变量** 只声明不报错就会导致编译报错；
* JS:`parseInt` ; java : `Integer.parseInt`不同点在于JS会把不是表示数字的字符串转化为`NAN`,java面对这种情况直接回抛出异常。
* java中的`for(:)`用法相当于JS中的`for(in)`


### Access Level java语言中的权限

`public` 类型的成员会被子类继承 相较于 `private` 定义的成员就不会被子类继承

* **访问修饰符**

修饰符 | 当前类 | 子孙类 | 同包 | 其他包 | 其他包子孙类
--- | --- | ---- | --- | --- | --- | ---- |
public | Y | Y | Y | Y | Y |
private | Y | N | N | N | N |

### 继承原则

所能形成（设计）继承的应该是可以被描述成“IS-A”的关系，比如三角形是形状

1. 子类比父类更加具有特定意义（猫科动物继承动物是合理的）
2. 行为程序（类）应该被多个基本类型的类所继承（比如“发出声音”，要被动物，乐器所运用`HAS-A`比较合理）