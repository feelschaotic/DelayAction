#### 目标方法前置检验模型

##### 需求背景
在执行目标行为时，需要执行一些前置的行为。而这些前置行为，需要用户参与才能完成。
##### 应用场景举例
未登录情况下关注用户，跳转登陆，登陆成功后自动执行关注。

##### 调用
- 无嵌套调用（常用场景，单Action）：

`ActionActivity`实现`Action`接口，或 new `Action`实现类，实现 call 目标行为。

```
SingleCall.getInstance()
          .addAction(ActionActivity.this)
          .addValid(new LoginValid())//前置条件，可能有多个
          .addValid(new OtherValid()
          .doCall();
```
前置行为完成后，调用`SingleCall.getInstance().doCall();`启动验证模型

- 嵌套调用（多Action）：
```
Call call1 = new Call(new Action() {
    @Override
    public void call() {
    }
});
Call call2 = new Call(new Action() {
    @Override
    public void call() {
    }
});
callUnit1.addValid(new LoginValid());
callUnit1.addValid(new AnotherValid());
callUnit2.addValid(new OtherValid());

MultipleCall.getInstance()
        .postCall(call1)
        .postCall(call2);
```
前置行为完成后，调用`MultipleCall.getInstance().reCheckValid();`启动验证模型

##### 类关系
![](https://upload-images.jianshu.io/upload_images/3167794-af92e0f102172d59.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

>相比[参考](https://github.com/jinyb09017/delayActionDemo)：
>- 增加了容错处理
>- 补充了嵌套 Call 的情况

