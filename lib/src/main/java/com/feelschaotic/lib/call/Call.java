package com.feelschaotic.lib.call;

import com.feelschaotic.lib.Action;
import com.feelschaotic.lib.Valid;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 一个执行单元。
 * 包括一个执行目标体和一个检验队列。检验队列用来保证所有的前置条件。当所有的前置条件都通过后，才能进行执行单元。
 *
 */

public class Call {

    /**
     * 目标行为
     */
    private Action mAction;
    /**
     * 先进先出的验证模型
     */
    private Queue<Valid> mValidQueue = new ArrayDeque();
    /**
     * 上一个执行的valid
     */
    private Valid mLastValid;

    public Call() {
    }

    public Call(Action action) {
        this.mAction = action;
    }

    public Call addValid(Valid valid) {
        mValidQueue.add(valid);
        return this;
    }

    public void check() {
        for (Valid valid : mValidQueue) {
            if (valid.preCheck()) {
                mValidQueue.remove(valid);
            }
        }
    }

    public void doCall() {
        MultipleCall.getInstance().postCall(this);
    }


    public Action getAction() {
        return mAction;
    }

    public void setAction(Action mAction) {
        this.mAction = mAction;
    }

    public Queue<Valid> getValidQueue() {
        return mValidQueue;
    }

    public void setValidQueue(Queue<Valid> mValidQueue) {
        this.mValidQueue = mValidQueue;
    }

    public Valid getLastValid() {
        return mLastValid;
    }

    public void setLastValid(Valid mLastValid) {
        this.mLastValid = mLastValid;
    }
}
