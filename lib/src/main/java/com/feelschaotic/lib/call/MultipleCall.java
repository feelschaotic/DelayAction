package com.feelschaotic.lib.call;

import com.feelschaotic.lib.Valid;
import com.feelschaotic.lib.ValidException;

import java.util.Queue;
import java.util.Stack;


public class MultipleCall {
    private Stack<Call> mDelaysCallStack = new Stack<>();

    private MultipleCall() {
    }

    public static MultipleCall getInstance() {
        return MultipleHolder.mInstance;
    }

    private static class MultipleHolder {
        private static MultipleCall mInstance = new MultipleCall();
    }

    public MultipleCall postCall(Call call) {
        call.check();
        if (call.getValidQueue().size() == 0 && call.getAction() != null) {
            //如果全部满足，则直接跳转目标方法
            call.getAction().call();
        } else {
            //加入到延迟执行体中来
            mDelaysCallStack.push(call);
            Valid peekValid = call.getValidQueue().peek();
            call.setLastValid(peekValid);
            peekValid.doValid();
        }
        return this;
    }

    public void reCheckValid() {
        if (mDelaysCallStack.size() <= 0) {
            return;
        }
        Call call = mDelaysCallStack.peek();
        if (!call.getLastValid().preCheck()) {
            throw new ValidException(String.format("you must pass through the %s,and then reCall()", call.getLastValid().getClass().toString()));
        }
        Queue<Valid> validQueue = call.getValidQueue();
        validQueue.remove(call.getLastValid());
        if (validQueue.size() == 0) {
            if (call.getAction() != null) {
                call.getAction().call();
                mDelaysCallStack.remove(call);
            }
        } else {
            Valid peekValid = call.getValidQueue().peek();
            call.setLastValid(peekValid);
            peekValid.doValid();
        }
    }

}
