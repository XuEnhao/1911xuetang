package com.yijiuyiyiedu.xuetang.alipay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class OrderInfoUtil2_0 {

    /**
     * ������Ȩ�����б�
     *
     * @param pid
     * @param app_id
     * @param target_id
     * @return
     */
    public static Map<String, String> buildAuthInfoMap(String pid,
                                                       String app_id, String target_id, boolean rsa2) {
        Map<String, String> keyValues = new HashMap<String, String>();

        // �̻�ǩԼ�õ���app_id���磺2013081700024223
        keyValues.put("app_id", app_id);

        // �̻�ǩԼ�õ���pid���磺2088102123816631
        keyValues.put("pid", pid);

        // ����ӿ����ƣ� �̶�ֵ
        keyValues.put("apiname", "com.alipay.account.auth");

        // �̻����ͱ�ʶ�� �̶�ֵ
        keyValues.put("app_name", "mc");

        // ҵ�����ͣ� �̶�ֵ
        keyValues.put("biz_type", "openservice");

        // ��Ʒ�룬 �̶�ֵ
        keyValues.put("product_id", "APP_FAST_LOGIN");

        // ��Ȩ��Χ�� �̶�ֵ
        keyValues.put("scope", "kuaijie");

        // �̻�Ψһ��ʶ���磺kkkkk091125
        keyValues.put("target_id", target_id);

        // ��Ȩ���ͣ� �̶�ֵ
        keyValues.put("auth_type", "AUTHACCOUNT");

        // ǩ������
        keyValues.put("sign_type", rsa2 ? "RSA2" : "RSA");

        return keyValues;
    }

    /**
     * ����֧�����������б�
     *
     * @param pid
     * @param app_id
     * @param target_id
     * @return
     */
    public static Map<String, String> buildOrderParamMap(String app_id,
                                                         boolean rsa2, String StrOrderInfo) {
        Map<String, String> keyValues = new HashMap<String, String>();

        keyValues.put("app_id", app_id);

        keyValues.put("biz_content", StrOrderInfo);

        keyValues.put("charset", "utf-8");
        keyValues.put("notify_url",
                "http://mpos.sx-xpay.com/water/AlipayOperate");
        keyValues.put("method", "alipay.trade.app.pay");

        keyValues.put("sign_type", rsa2 ? "RSA2" : "RSA");

        keyValues.put("timestamp", "2016-07-29 16:55:53");
        keyValues.put("version", "1.0");

        return keyValues;
    }

    /**
     * ���ݷŽ�ȥ��
     *
     * @param subject ������Ϣ
     * @param body    ���ݡ�
     * @param price   ���
     * @return
     */
    public static String getOrderInfo(String subject, String body, String price, String out_trade_no) {

        String orderInfo = "{\"timeout_express\":\"30m\",\"product_code\":\"QUICK_MSECURITY_PAY\",\"total_amount\":\""
                + price
                + "\",\"subject\":\""
                + subject
                + "\",\"body\":\""
                + body + "\",\"out_trade_no\":\"" + out_trade_no + "\"}";

        return orderInfo;
    }

    /**
     * ����֧������������Ϣ
     *
     * @param map ֧����������
     * @return
     */
    public static String buildOrderParam(Map<String, String> map) {
        List<String> keys = new ArrayList<String>(map.keySet());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keys.size() - 1; i++) {
            String key = keys.get(i);
            String value = map.get(key);
            sb.append(buildKeyValue(key, value, true));
            sb.append("&");
        }

        String tailKey = keys.get(keys.size() - 1);
        String tailValue = map.get(tailKey);
        sb.append(buildKeyValue(tailKey, tailValue, true));

        return sb.toString();
    }

    /**
     * ƴ�Ӽ�ֵ��
     *
     * @param key
     * @param value
     * @param isEncode
     * @return
     */
    private static String buildKeyValue(String key, String value,
                                        boolean isEncode) {
        StringBuilder sb = new StringBuilder();
        sb.append(key);
        sb.append("=");
        if (isEncode) {
            try {
                sb.append(URLEncoder.encode(value, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                sb.append(value);
            }
        } else {
            sb.append(value);
        }
        return sb.toString();
    }

    /**
     * ��֧��������Ϣ����ǩ��
     *
     * @param map ��ǩ����Ȩ��Ϣ
     * @return
     */
    public static String getSign(Map<String, String> map, String rsaKey,
                                 boolean rsa2) {
        List<String> keys = new ArrayList<String>(map.keySet());
        // key����
        Collections.sort(keys);

        StringBuilder authInfo = new StringBuilder();
        for (int i = 0; i < keys.size() - 1; i++) {
            String key = keys.get(i);
            String value = map.get(key);
            authInfo.append(buildKeyValue(key, value, false));
            authInfo.append("&");
        }

        String tailKey = keys.get(keys.size() - 1);
        String tailValue = map.get(tailKey);
        authInfo.append(buildKeyValue(tailKey, tailValue, false));

        String oriSign = SignUtils.sign(authInfo.toString(), rsaKey, rsa2);
        String encodedSign = "";

        try {
            encodedSign = URLEncoder.encode(oriSign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "sign=" + encodedSign;
    }

    /**
     * Ҫ���ⲿ�����ű���Ψһ��
     *
     * @return
     */
    private static String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss",
                Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }

}
