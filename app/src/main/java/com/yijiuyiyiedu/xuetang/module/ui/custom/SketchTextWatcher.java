package com.yijiuyiyiedu.xuetang.module.ui.custom;

import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by xuenhao on 2018/4/23.
 */
public class SketchTextWatcher implements TextWatcher {
    private int editStart;
    private int editEnd;
    private int maxLen = 16; // 8 个汉字 18 个英文字符 (表情 2 个字符)

    private EditText editText;

    public SketchTextWatcher(EditText e) {
        editText = e;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        editStart = editText.getSelectionStart();
        editEnd = editText.getSelectionEnd();
        // 先去掉监听器，否则会出现栈溢出
        editText.removeTextChangedListener(this);
        if (!TextUtils.isEmpty(s.toString())) {
            while (calculateLength(s.toString()) > maxLen) {
                s.delete(--editStart, editEnd--);
            }
        }

        editText.setText(s);
        editText.setSelection(editStart);
        // 恢复监听器
        editText.addTextChangedListener(this);
    }

    /**
     * 英文一个字符  中文两个字符
     */
    private int calculateLength(String string) {
        char[] ch = string.toCharArray();

        int varlength = 0;
        for (char c : ch) {
            // changed by zyf 0825 , bug 6918，加入中文标点范围 ， TODO 标点范围有待具体化
            if ((c >= 0x2E80 && c <= 0xFE4F) || (c >= 0xA13F && c <= 0xAA40) || c >= 0x80) { // 中文字符范围0x4e00 0x9fbb
//                    if (c >= 0x4E00 && c <= 0x9FBB) { // 中文字符范围 0x4E00-0x9FA5 + 0x9FA6-0x9FBB
                varlength = varlength + 2;
            } else {
                varlength++;
            }
        }
        return varlength;
    }
}
