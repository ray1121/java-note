### SOFA服务的发布与引用

SOFA（多模块）中的各个模块之间的 **Spring Context是隔离的**，不同的Spring Cotext导致A，B两个模块想要互相调用服务就没有办法直接通过Spring的依赖注入来完成调用了。

* **两种服务的发布与引用方式**：
    * JVM服务的发布与引用
        * 解决一个SOFA应用内部的不同模块之间的调用问题（**单机调用**）
    * RPC服务的发布与引用
        * 解决多个SOFA应用中的不同模块的调用问题（**跨机调用**）

#### JVM服务的发布与引用

* 方式：
    * XML配置方式
    * 注解方式

1. **XML配置方式实现**

**服务的发布**

1. 创建接口和实现类
2. 在 Spring xml配置文件中将 **实现类声明成一个bean**`<bean id="xmljvmService" class="com.ray.impl.XMLJVMServiveImpl">` **指明class**
3. 将bean注册为sofa服务，本工程其他模块就可以引用了。`<sofa:service ref="xmljvmService" interface="com.ray.XMLJVMService">` **指明发布的的bean的id，指明该类实现的接口**

* 接口和实现类
```java
//创建接口
public interface XMLJVMService{
    String get();
}

//实现接口
public class XMLJVMServiceImpl implements XMLJVMService{
    @Override
    public String get(){
        return "lal"
    }
}
```

**服务的引用**

* 在另一个模块的Spring xml中 **引用上述发布出来的Sofa Rpc服务,指明引用的接口**
`<sofa:reference id="xmljvmService" interface="com.ray.XMLJVMService">`

* 直接用Spring注解 `@Autowired` 自动注入就可以使用

```java
@Autowired
private XMLJVMService xmljvmService;
```

2. **注解方式**

```java
// 创建接口
public interface AnJVMService{
    String get();
}

// 实现类

@SofaService//注解服务实现类
public class AnJVMServiceImpl implements AnJVMService{
    @Override
    public String get(){
        return "lala"
    }
}
```

**将实现类注册成Spring Bean**
```xml
<bean id="anJVMService" class="com.ray.impl.AnJVMServiceImpl">
```
* **服务引用**

```java
@SofaReference
private AnJVMService anJVMService;
```

#### RPC服务的发布与引用

* **服务发布**
    * 接口的定义与实现
    * **XML方式将实现的类注册成RPC服务**

```xml
<sofa:service ref="xmlrpcService" interace="com.ray.XMLRPCService">
    <!-- 表示这个服务被注册为基于 taobao remoteing的RPC服务 -->
    <sofa:binding.tr/>
</sofe:service>
```

* **服务的引用**
    
* 在Spring xml 中将RPC服务引用过来

```xml
<sofa:reference id="xmlrpcService" interace="com.ray.XMLRPCService">
    <!-- 表示这个服务被注册为基于 taobao remoteing的RPC服务 -->
    <sofa:binding.tr/>
</sofe:reference>
```

* 在需要该服务的代码中直接用Spring注解 `@Autowrired` 自动注入就可以使用了

```java
@Autowired
private XMLRPCService xmlrpcService
```
#### 引入第三方的RPC服务的步骤

* 引入依赖
    * 首先要在主`pom.xml`的dependecyManagement中引入maven依赖
    * 在 `assembly/pom.xml`的 artifactItems 中增加 artifactItem 依赖
    * **在需要引入此服务的模块中**，引入maven依赖



### 如何将服务发出去的？

1. 在xml中引入sofa的命名空间
2. 通知Spring来加载sofa标签
3. 在服务的发布时，xml中使用了 `<sofa:service>`标签，则第一步需要 **解析这个标签**
4. 服务注册和发布
    1. 将初始化过程中生成的 `ServiceComponent`**服务组件对象注册到sofa上下文中** `sofaRuntimeContext`
    2. 实际上将服务对象注册到sofaRuntimeContext的过程就是讲服务组件对象 **塞到全局的registry对象中**。当有其他地方需要查找服务组件的时候就可以通过registry进行查找。
5. 在服务组件被注册到sofa上下文的同时，会依次调用服务组件的 register，resolve，activate方法。
    1. **activite方法会获取服务的binding，然后逐一调用BindingAdapter**的 **outBinding**对外暴露服务。
    2. **对于特殊的TrBindingAdaper来说，需要将服务信息推送到注册中心Confreg**