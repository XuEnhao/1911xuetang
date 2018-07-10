package com.yijiuyiyiedu.xuetang.module.base;

/******************************************
 * 类名称：BaseView
 ******************************************/
public interface BaseView<T> {

    /**
     * 显示正在加载进度框
     */
    void showLoading();

    /**
     * 隐藏正在加载进度框
     */
    void hideLoading();

    /**
     * 当数据请求成功后，调用此接口显示数据
     *
     * @param data 数据源
     */
    void showData(T data);

    /**
     * 当数据请求失败后，调用此接口提示
     *
     * @param msg 失败原因
     */
    void showFailureMessage(String msg);

    /**
     * 当数据请求异常，调用此接口提示
     */
    void showErrorMessage();
}
