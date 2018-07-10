package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.UriConstant;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.module.entity.BuyCourseEntity;
import com.yijiuyiyiedu.xuetang.module.entity.OrderEntity;
import com.yijiuyiyiedu.xuetang.module.entity.ProjectDetailsEntity;
import com.yijiuyiyiedu.xuetang.module.persenter.BuyCoursePresenter;
import com.yijiuyiyiedu.xuetang.module.view.BuyCourseView;
import com.yijiuyiyiedu.xuetang.utils.GlideUtils;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.ProgressDialogUtils;
import com.yijiuyiyiedu.xuetang.utils.UtilToast;
import com.yijiuyiyiedu.xuetang.utils.titlebar.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xuenhao on 2018/3/23.
 * /购买页面
 */

public class BuyCourseActivity extends BaseActivity implements BuyCourseView {

    @BindView(R.id.applyCompanyNumber_back)
    ImageView back;//返回
    @BindView(R.id.buyCourse_buyUser)
    TextView buyUser;//购买用户
//    @BindView(R.id.buyCourse_accountType)
//    TextView accountType;//账户类型
    @BindView(R.id.buyCourse_phone)
    TextView phone;//手机号
    @BindView(R.id.buyCourse_courseImg)
    ImageView courseImg;//课程图片
    @BindView(R.id.buyCourse_courseName)
    TextView courseName;//课程名字
    @BindView(R.id.buyCourse_bought)
    TextView bought;//已购买
    @BindView(R.id.buyCourse_newAddNum)
    TextView newAddNum;//新增人数
    @BindView(R.id.buyCourse_onePrice)
    TextView onePrice;//单价
    @BindView(R.id.buyCourse_num)
    TextView courseNum;//课程人数
    @BindView(R.id.buyCourse_finalPrice)
    TextView finalPrice;//最终价格
    @BindView(R.id.buyCourse_buy)
    TextView buy;//去付款
    @BindView(R.id.buyCourse_number)
    EditText editTextnumber;
    private String curriculumId;
    private BuyCoursePresenter mPresenter;
    private String user_type;
    private Context mContext;
    private int orderId;
    private String price;
    private String className;
    private int number = 1;
    //    private Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            Intent it = new Intent(mContext,PayWebViewActivity.class);
//            it.putExtra("orderId",msg.what);
//            startActivity(it);
//            finish();
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_course);
        ButterKnife.bind(this);
        StatusBarUtil.setStatusBar(this,R.id.buyCourse_statusBar);
//        setStatusBars();
        mContext = this;
        getIntentData();
        editTextnumber.setCursorVisible(false);//取消光标
        editTextnumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextnumber.setCursorVisible(true);
            }
        });
        loadData();
    }


    private void initView() {
        editTextnumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(editTextnumber.getText().toString())) {
                    double v = Double.parseDouble(price) * Integer.parseInt(editTextnumber.getText().toString());
                    number = Integer.parseInt(editTextnumber.getText().toString());
                    finalPrice.setText(v + "");
                    editTextnumber.setSelection(editTextnumber.getText().length());
                } else {
                    number = 1;
                    double v = Double.parseDouble(price) * number;
                    editTextnumber.setText(1 + "");
                    finalPrice.setText(v + "");
                    editTextnumber.setSelection(editTextnumber.getText().length());
                }
            }
        });
    }

    /**
     * 加载数据
     */
    private void loadData() {
        mPresenter = new BuyCoursePresenter(this);
        mPresenter.getBuyCourseData(curriculumId, UriConstant.userId);
    }

    /**
     * 获取传过来的课程id
     */
    private void getIntentData() {
        Intent it = getIntent();
        curriculumId = it.getStringExtra("curriculumId");
    }

    /**
     * 设置沉浸式
     */
    private void setStatusBars() {
        // 设置透明导航栏
        StatusBarUtil.setTranslucentForImageView(this, 50, null);
        int statusBarHeight = StatusBarUtil.getStatusBarHeight(this);
        View viewById = findViewById(R.id.buyCourse_statusBar);
        ViewGroup.LayoutParams layoutParams = viewById.getLayoutParams();
        layoutParams.height = statusBarHeight;
    }

    @Override
    public void showLoading() {
//跳转选择付款
        ProgressDialogUtils.getInstance(mContext).showDialog();
    }

    @Override
    public void hideLoading() {
        ProgressDialogUtils.getInstance(mContext).dismissDialog();
    }

    /**
     * 确认支付信息
     *
     * @param data
     */
    @Override
    public void showData(BuyCourseEntity data) {
        if (data.getStatus().equals("1")) {
            buyUser.setText(data.getData().getInfo().getNick_name() + "");//用户名
            BuyCourseEntity.DataBean.InfoBean bean = data.getData().getInfo();
            user_type = bean.getUser_type();
            BuyCourseEntity.DataBean.CurridataBean courseBean = data.getData().getCurridata();
            courseNum.setText("永久有效");//份数
            bought.setVisibility(View.INVISIBLE);
            newAddNum.setVisibility(View.INVISIBLE);
            editTextnumber.setText(number + "");
            editTextnumber.setSelection(editTextnumber.getText().length());
            phone.setText(data.getData().getInfo().getUser_name());//手机号
            GlideUtils.loadImage(this, courseBean.getPicture(), courseImg);//图片
            className = courseBean.getTitle();
            courseName.setText(courseBean.getTitle());//课程名字
            onePrice.setText(courseBean.getPresent_price() + "");//现价价格
            price = (data.getData().getPicre());
            finalPrice.setText(Double.parseDouble(data.getData().getPicre()) * number + "");//最终价格
            initView();//添加监听
        } else {
            finish();
        }

    }

    @Override
    public void showOrderData(OrderEntity data) {
        if (data.getStatus().equals("1")) {
            orderId = data.getData().getId();
            Intent it = new Intent(mContext, PayActivity.class);
            it.putExtra("orderId", data.getData().getId());
            it.putExtra("price", price);
            it.putExtra("className", className);
            it.putExtra("type", 1);
            startActivity(it);
//            finish();
        } else {
            UtilToast.showToast(this, data.getMsg());
        }
    }

    @Override
    public void showProjectData(ProjectDetailsEntity data) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        handler.removeMessages();
    }

    @Override
    public void showFailureMessage(String msg) {

    }

    @Override
    public void showErrorMessage() {

    }

    @OnClick(R.id.buyCourse_buy)//点击去付款
    public void onViewClicked() {
//        pay_type 购买类型  1 课程  2 项目 3充值
        mPresenter.getOrderData(curriculumId, price, String.valueOf(editTextnumber.getText().toString()), user_type, UriConstant.userId, "1");
//        mPresenter.getOrderData(curriculumId, "0.01", courseName.getText().toString(), user_type, UriConstant.userId,"1");
    }

    @OnClick({R.id.buyCourse_del, R.id.buyCourse_number, R.id.buyCourse_add,R.id.applyCompanyNumber_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.buyCourse_del://删除
                if (number > 1) {
                    number--;
                } else {
                    UtilToast.showToast(mContext, "不能在减了");
                }
                double i = Double.parseDouble(price) * number;
                finalPrice.setText(i + "");//价钱
                editTextnumber.setText(number + "");//份数
                break;
            case R.id.buyCourse_add://添加
                if (number < 999) {
                    number++;
                } else {
                    UtilToast.showToast(mContext, "不能在加了");
                }
                double i1 = Double.parseDouble(price) * number;
                finalPrice.setText(i1 + "");//价钱
                editTextnumber.setText(number + "");//份数
                break;
            case R.id.applyCompanyNumber_back:
                JumpUtil.overiderAnimsition(this);
                break;
        }
    }

}
