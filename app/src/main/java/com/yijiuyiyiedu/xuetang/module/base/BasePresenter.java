package com.yijiuyiyiedu.xuetang.module.base;

/**
 * Description: BasePresenter
 */
public interface BasePresenter<T> {
    void attachView(T view);

    void detachView();
}
