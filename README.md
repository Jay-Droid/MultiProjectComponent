# MultiProjectComponent
多个项目实现组件化（项目内组件和项目之间组件）
### Demo架构
![Demo Architecture](https://github.com/Jay-Droid/MultiProjectComponent/blob/master/res/demo_architecture.png)

### 各组件层介绍
#### APP壳组件
壳工程依赖了需要集成的业务组件，它可能只有一些配置文件，没有任何代码逻辑。根据你的需要选择集成你的业务组件，不同的业务组件就组成了不同的APP，注意包名以及其它项目信息要和原来保持一致等。

#### 常规业务组件
该层的组件就是我们真正的业务组件了。我们通常按照功能模块来划分业务组件，
例如注册登录、用户协议、消息模块等。这里的每个业务组件都是一个小的APP，它必须可以单独编译，单独打包成APK在手机上运行。

#### 基础业务组件
该层组件是对一些系统通用的业务能力进行封装的组件。
例如公共业务组件，BossApplication、BaseActivity、BaseFragment、mvp、mvvm 基类等；
例如分享能力组件，其他业务只要集成该组件就能进行相关分享；
例如共享公共数据，可以将用户登录信息缓存在这里等；
例如共享公共资源，value、drawable、style等；
例如组件间数据通信的接口，可以将Arouter服务所需的IProvider 以及path 放在这里。

#### 基础功能组件
这个层的组件都是最基础的功能，通常它不包含任何业务逻辑，也可以说这些组件是一些通用的工具类。
例如日志记录组件，它只是提供了日志记录的能力，你要记录什么样的日志，它并不关心；
例如基础UI组件，它是一个全局通用的UI资源库；
例如图片加载组件，它是一个全局通用图片加载框架；
例如网络服务组件，它封装了网络的请求能力。

> 组件之间必须遵循以下规则：
> 1，只有上层的组件才能依赖下层组件，不能反向依赖，否则可能会出现循环依赖的情况；
> 2，同一层之间的组件不能相互依赖，这也是为了组件之间的彻底解耦

### 文件描述
- Projects/ProjectA:项目A
- Projects/ProjectB:项目B
- Components: 项目A与项目B的公共组件
