package com.yijiuyiyiedu.xuetang.module.ui.custom;

/**
 * Created by xuenhao on 2018/4/23.
 */

import android.support.annotation.NonNull;
import android.text.InputFilter;
import android.text.Spanned;

/**
 * 纯英文 纯中文 可以实现 from : http://justwyy.iteye.com/blog/1543419
 * 中英混输入 :
 * 中文 占 2 位
 * 英文 占 1 位
 * 思路：计算 已有文字的长度，计算新增文字的长度，长度没有到达限制时，拼接
 * 小米 输入时，没有边输入边写入EditText的手机有问题，比如小米
 * 华为这种输入一个字符就写入的，没有问题
 */
public class SketchLengthFilter implements InputFilter {

    private final int LENGTH_ENGLISH;

    public SketchLengthFilter() {
        this(16);
    }

    SketchLengthFilter(int english) {
        LENGTH_ENGLISH = english;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

        int keep = LENGTH_ENGLISH - (calculateLength(dest.toString()) - (dend - dstart));
        end = calculateLength(source.toString());

        CharSequence result = "";
        if(keep >= 0) {
            if (keep >= end - start) {
                result = null; // keep original
            } else {
                result = subSequence(source.toString(), start, start + keep);
            }
        }
        return result;
    }

    private CharSequence subSequence(@NonNull String source, int start, int length){
        int size = calculateLength(source);
        if(size < length){
            return source;
        }
        char[] chars = source.toCharArray();
        if(chars.length < length){
            return source;
        }
        char[] result = new char[length - start];
        System.arraycopy(chars, start , result, 0, length);
        return new String(result);
    }

    private int calculateLength(String string){
        int length = 0;
        char[] chars = string.toCharArray();
        for (char c : chars) {
            if (isChinese(c)) {
                length += 2;
            } else {
                length++;
            }
        }
        return length;
    }

    private boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }
}
