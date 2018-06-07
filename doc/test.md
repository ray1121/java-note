### SOFA模块

SOFA工程原型中每一层，比如`biz-shred`或者 `common-dal`这些都是一个sofa模块，这个sofa模块在普通的jar包的基础上加上一些sofa特有的配置，使得这个jar包让sofa框架能够识别

区别：

* META-INF目录下，的MANIFEST.MF定义了模块名称和模块之间的依赖关系。
	* 最后一行空行
	* 一行写不下换行，下一行行首必须是空格
	* **Module-name必须唯一，对于本地和其他应用** 
	* **Require-name被定义意味着当这个模块Spring上下文启动依赖于其他模块的Spring上下文先启动的时候**
	* **Spring-parent**只能定义一个，打通Spring上下文。Spring的每一个模块上下文都是隔离的（**Spring父上下文的作用？？**）

### 解决不同级别的服务发布与调用

**两种调用场景：**

* JVM服务的发布与调用：**解决一个SOFA应用内部模块之间的调用问题**。
* **RPC服务**的发布与调用：**解决多个SOFA应用之间的模块调用问题**。

Spring上下文隔离，调用方式：

1. XML方式
	1. 定义好一个Bean
	2. SOFA 扩展标签 `<sofa:service interface="",ref="">` 
	3. `<>sofa:reference>`
2. 注解方式
	1. `@SofaService` 作用是将一个Spring Bean发布成一个Sofa的JVM服务
	2. `@SofaReference`
3. 编程API方式

### sofa-config.properties 文件

该配置文件的主要作用：

* sofa框架的配置文件，配置特定的属性，可以改变框架行文
* 在Spring配置文件中使用，进行变量替换
* 在代码中获取配置信息

**服务器运行和本地运行的sofa-config.properties文件位置是不同的**

* 服务器运行时，sofa-config.properties 的位置是在 `conf/autoconfig/sofa-config.properties`
* 本地运行时，在'app/test/src/test/resources/sofaconfig/sofa-test/config.properties'


**代码中获取 `sofa-config.properties`中的配置的两种方法：**

* 实现 `AppConfigurationAware` 接口
* 使用 `AppConfig` 注解

此处需要注意的是：

在使用Sofa配置相关的API的时候，需要**确保使用的模块里面已经添加了如下的依赖**：

```
<dependency>
	<groupId>com.alipay.sofa</groupId>
	<artifactId>sofa-runtime-api</artifactId>
</dependency>
```

然后只需要在这个Bean上声明一个类型为 `AppConfiguration` 的字段，然后给**这个字段打上`@AppConfig("key")`的注解**，SOFA框架就可以将 sofa-config.properties里面的配置信息注入到该注解的字段。

**或者，使用 getPropertyValue**方法就可以拿到配置里面的东西了.

**小限制：被@AppConfig注解的必须是String类型**

例子：

```java
public class SampleConfigurationBean {
	@AppConfig("env")
	private String env;
}
```

### sofa添加依赖的问题

**如果某一个模块的编译需要依赖此依赖的话，要在该模块的 pom.xml中添加此依赖，并且在 assembly/pom.xml中也要增加这个依赖，因为这个pom.xml中决定了最后打包出来的Sofa应用中包含了哪些东西，在运行时，依赖就是这些东西，如果忘记添加，可能会出现 ClassNotFoundException**















