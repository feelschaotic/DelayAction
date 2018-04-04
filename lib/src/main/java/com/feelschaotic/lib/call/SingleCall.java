package com.feelschaotic.lib.call;

import com.feelschaotic.lib.Action;
import com.feelschaotic.lib.Valid;

/**
 * 如果CallUnit验证模型中没有嵌套的验证模型，则可以直接使用SingleCall即可
 *
 */


public class SingleCall {

    private Call call = new Call();

    public SingleCall addAction(Action action) {
        clear();
        call.setAction(action);
        return this;
    }


    public SingleCall addValid(Valid valid) {
        //只添加无效的，验证不通过的
        if (valid.preCheck()) {
            return this;
        }
        call.addValid(valid);
        return this;
    }

    public void doCall() {
        //如果上一条valid没有通过，是不允许再发起call的
        if (call.getLastValid() != null && !call.getLastValid().preCheck()) {
            return;
        }

        //执行action
        if (call.getValidQueue().size() == 0) {
            if (call.getAction() != null) {
                call.getAction().call();
                clear();
            }
        } else {
            //执行验证
            Valid valid = call.getValidQueue().poll();
            call.setLastValid(valid);
            valid.doValid();
        }

    }

    private void clear() {
        call.getValidQueue().clear();
        call.setAction(null);
        call.setLastValid(null);
    }


    public static SingleCall getInstance() {
        return SingletonHolder.mInstance;
    }

    private static class SingletonHolder {
        private static SingleCall mInstance = new SingleCall();
    }
}
