package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.ClearHistoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.CourseIdBus;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.ToastUtils;
import com.yijiuyiyiedu.xuetang.utils.WindowUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xuenhao on 2018/6/13.
 * 添加绑定课程id
 */

public class AddCourseIdActivity extends BaseActivity {
    @BindView(R.id.addCourseId_back)
    ImageView back;
    @BindView(R.id.addCourseId_finish)
    TextView finish;
    @BindView(R.id.addCourseId_editext)
    EditText editext;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course_id);
        ButterKnife.bind(this);
        mContext = this;
        WindowUtils.showSoftInputFromWindow(this,editext);//主动弹出软键盘
        //回退
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WindowUtils.hintKeyboard((Activity) mContext);
                JumpUtil.overiderAnimsition((Activity) mContext);
            }
        });
        //完成
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editext.getText().toString())){
                    ToastUtils.showShort("请输入课程ID");
                }else{
                    bindCourseId();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        WindowUtils.hintKeyboard(this);
        super.onDestroy();
    }

    /**
     * 绑定课程id 的结果
     */
    private void bindCourseId() {
        OkGo.<ClearHistoryEntity>post(Constant.NEW_BASE_URL + "/MyInfo/bindingCurriculumPrivate")
                .params("invitation_code",editext.getText().toString())
                .execute(new JsonCallback<ClearHistoryEntity>() {
                    @Override
                    public void onSuccess(Response<ClearHistoryEntity> response) {
                        ClearHistoryEntity body = response.body();
                        if (body.getStatus()==0){
                            ToastUtils.showShort(body.getMsg());
                            EventBus.getDefault().post(new CourseIdBus("1"));
                            WindowUtils.hintKeyboard((Activity) mContext);
                            JumpUtil.overiderAnimsition((Activity) mContext);
                        }else{
                            ToastUtils.showShort(body.getMsg());
                        }
                    }
                });
    }


}
