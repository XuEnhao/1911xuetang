package com.yijiuyiyiedu.xuetang.module.entity;

import java.util.List;

/**
 * Created by xuenhao on 2018/4/16.
 */

public class AliPayEntity {

    /**
     * status : 1
     * msg : 调取成功
     * data : ["alipay_sdk=alipay-sdk-php-20161101&app_id=2018032702456869&biz_content=%7B%22body%22%3A%22%5Cu8d2d%5Cu4e70%5Cu5546%5Cu54c12%5Cu4ef6%5Cu517115.00%5Cu5143%22%2C%22subject%22%3A%221911%5Cu5b66%5Cu5802%5Cu8d2d%5Cu4e70%5Cu8bfe%5Cu7a0b%22%2C%22out_trade_no%22%3A%22RCM2018041683954343%22%2C%22timeout_express%22%3A%2210%22%2C%22total_amount%22%3A%22100.00%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay¬ify_url=http%3A%2F%2Fwww.1911edu.com%2FPay%2FWechat%2FaliPayNotify&sign_type=RSA2×tamp=2018-04-16+16%3A35%3A09&version=1.0&sign=RR9k1GCEb80Loxy5SMEOI7g%2BomL3nw2YEVZC90F9Otgl7QnmEtsqFeH7pyZXeSq8qKqbPOJHhcTWFX%2F91MOcwEhMX0POat4w0b87FLA9xvXweTctREk9Al8WQKDVfdfQ2q9vqa%2FDaMoqq6th%2BNT%2FljDOIkDgpVcFB2Vk2uCSYOnkVzP6ac3Mo%2B9Hbc8QbH8kv1Qd4n9F9MRROZhI6gp%2BeEyttkShnOSnaJJMzIrpYjELp7QKfiKOLkDMJMsG9rx%2FEjqr2YzytnVrxY7K%2B3YsAiGTPofMxUKpS0B939n96jqC2jH8KehcIiDKmwVIknu4IhYq3lXI5RKa4Uyvsg9KXQ%3D%3D"]
     */

    private String status;
    private String msg;
    private List<String> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
