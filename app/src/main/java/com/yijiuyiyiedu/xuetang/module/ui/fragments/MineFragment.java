package com.yijiuyiyiedu.xuetang.module.ui.fragments;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.tencent.bugly.crashreport.CrashReport;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.api.constant.UriConstant;
import com.yijiuyiyiedu.xuetang.module.base.BaseFragment;
import com.yijiuyiyiedu.xuetang.module.entity.CourseEntity;
import com.yijiuyiyiedu.xuetang.module.entity.EditMyselfInfoBus;
import com.yijiuyiyiedu.xuetang.module.entity.MineEntity;
import com.yijiuyiyiedu.xuetang.module.entity.MyCourseBus;
import com.yijiuyiyiedu.xuetang.module.entity.NewsEntity;
import com.yijiuyiyiedu.xuetang.module.persenter.MinePresenter;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.BindPhoneActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.BuyRecordActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.CollectActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.CourseIdActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.CustomProjectContentActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.EditConvertNumberActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.LateStudyActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.LoginActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.MsgManagerActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.PassLoginActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.SettingsActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.SuggestActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.UserInfoActivity;
import com.yijiuyiyiedu.xuetang.module.ui.custom.CircleImageView;
import com.yijiuyiyiedu.xuetang.module.view.MineView;
import com.yijiuyiyiedu.xuetang.test.Test;
import com.yijiuyiyiedu.xuetang.utils.GlideUtils;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.LogUtil;
import com.yijiuyiyiedu.xuetang.utils.NetWorkUtil;
import com.yijiuyiyiedu.xuetang.utils.SharedPreferencesUtil;
import com.yijiuyiyiedu.xuetang.utils.ToastUtils;
import com.yijiuyiyiedu.xuetang.utils.UtilToast;
import com.yijiuyiyiedu.xuetang.utils.UtilsShowWindow;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by ${xeh} on 2017/4/21 0021.
 * 我的界面
 */

public class MineFragment extends BaseFragment implements MineView {

    @BindView(R.id.fragment_mine_userName)
    TextView userName; //用户昵称
    @BindView(R.id.fragment_mine_userCompany)
    TextView userPhone; //用户账号
    Unbinder unbinder;
    @BindView(R.id.fragment_mine_userAvatar)
    CircleImageView userAvatar;//用户头像
    MinePresenter mPresenter;
    @BindView(R.id.fragment_mine_settings)
    LinearLayout settings;//设置
    @BindView(R.id.fragment_mine_userInfoLinear)
    LinearLayout userInfoLinear;//用户信息
    @BindView(R.id.fragment_mine_collectCourse)
    LinearLayout collectCourse;//收藏的课程
    @BindView(R.id.fragment_mine_msgManager)
    LinearLayout msgManager;//消息管理
    @BindView(R.id.fragment_mine_applyCompanyNumber)
    LinearLayout applyCourse;//兑换课程
    @BindView(R.id.fragment_mine_callService)
    LinearLayout callService;//反馈建议
    @BindView(R.id.fragment_mine_userTime)
    TextView userTime;//使用时长
    boolean isLoad = false;
    @BindView(R.id.fragment_mine)
    LinearLayout fragmentMine;//根布局
    private final int REQUEST_CODE_ASK_CALL_PHONE = 0;
    private Gson gson;

    // 指定FramgnetView布局
    @Override
    protected int getLayout() {
        return R.layout.fragment_mine;
    }

    // 初始化View , presenter等
    @Override
    protected void initView(LayoutInflater inflater) {
        super.initView(inflater);
        mPresenter = new MinePresenter(this);
        if (!NetWorkUtil.judgeNetWork(mContext)){
            NotNetWork();
        }

    }

    /**
     * 无网络
     */
    private void NotNetWork(){
        LogUtil.LogD("tag", "mineFragment: 无网络 从数据库里那东西");
        String userInfo = SharedPreferencesUtil.getStringValue(mContext, "userInfo");
        gson = new Gson();
        MineEntity mineEntity = gson.fromJson(userInfo, MineEntity.class);
        initUserData(mineEntity);


    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void EditMyselfInfoBusBus(EditMyselfInfoBus event) {
        userPhone.setText("");
        userName.setText("登录/注册");
        userTime.setVisibility(View.GONE);
        GlideUtils.loadImage(mContext, R.mipmap.default_avatar, userAvatar);
        if (mPresenter != null) {
            if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringValue(mContext, "token"))) {
                mPresenter.getData(UriConstant.token);//请求用户的信息
                Log.d("tag", "EditMyselfInfoBusBus: "+UriConstant.token);
            } else {
            }

        } else {
            LogUtil.LogE("tag","mineFragment:presenter为空");
        }
    }


    @Override
    protected void initEvent() {
        super.initEvent();
        EventBus.getDefault().register(this);
    }

    // 懒加载
    @Override
    protected void lazyFetchData() {
        super.lazyFetchData();
        if (!isLoad) {//如果已登录
            if (SharedPreferencesUtil.selectUserInfo(mContext)) {
                UriConstant.token = SharedPreferencesUtil.getStringValue(mContext, "token");
                mPresenter.getData(UriConstant.token);//请求用户的信息
                isLoad = true;
                userTime.setVisibility(View.VISIBLE);
            }else {
                userPhone.setText("");
                userName.setText("登录/注册");
                userTime.setVisibility(View.GONE);
                GlideUtils.loadImage(mContext,R.mipmap.default_avatar, userAvatar);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.fragment_mine_settings, R.id.fragment_mine_collectCourse, R.id.fragment_mine_msgManager,R.id.fragment_mine_applyCompanyNumber, R.id.fragment_mine_callService,R.id.fragment_mine_userInfoLinear, R.id.fragment_mine_userAvatar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_mine_settings://设置
                if (SharedPreferencesUtil.selectUserInfo(mContext)) {
                    JumpUtil.jump(mContext, SettingsActivity.class);
//                    JumpUtil.jump(mContext, Test.class);
                } else {
                    JumpUtil.jump(mContext, PassLoginActivity.class);
                }
                break;
            case R.id.fragment_mine_collectCourse://最近学习
                if (SharedPreferencesUtil.selectUserInfo(mContext)) {
                    JumpUtil.jump(mContext, LateStudyActivity.class);
                } else {
                    JumpUtil.jump(mContext, PassLoginActivity.class);
                }
                break;
//            case R.id.fragment_mine_order://我的订单
//                if (SharedPreferencesUtil.selectUserInfo(mContext)) {
//                    JumpUtil.jump(mContext, BuyRecordActivity.class);
//                } else {
//                    JumpUtil.jump(mContext, LoginActivity.class);
//                }
//                break;
            case R.id.fragment_mine_msgManager://消息管理
                if (SharedPreferencesUtil.selectUserInfo(mContext)) {
                    JumpUtil.jump(mContext, MsgManagerActivity.class);
                } else {
                    JumpUtil.jump(mContext, PassLoginActivity.class);
                }
                break;
            case R.id.fragment_mine_applyCompanyNumber://课程id
                if (SharedPreferencesUtil.selectUserInfo(mContext)) {
                    JumpUtil.jump(mContext, CourseIdActivity.class);
                } else {
                    JumpUtil.jump(mContext, PassLoginActivity.class);
//                    ((Activity) mContext).finish();
                }
                break;
            case R.id.fragment_mine_callService://联系客服
                if (SharedPreferencesUtil.selectUserInfo(mContext)) {
////
//                    UtilsShowWindow.showDialog(mContext, "拨打电话", "是否拨打客服电话", "取消", "确定", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            //取消
//                            dialog.dismiss();
//                        }
//                    }, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            //确定
//                            requestPermission();
//                        }
//                    });
                    JumpUtil.jump(mContext, SuggestActivity.class);
                } else {
                    JumpUtil.jump(mContext, PassLoginActivity.class);
//                    ((Activity) mContext).finish();
                }
                break;
            case R.id.fragment_mine_userInfoLinear:
                if (!SharedPreferencesUtil.selectUserInfo(mContext)) {
                    JumpUtil.jump(mContext, PassLoginActivity.class);
                }else{
                    JumpUtil.jump(mContext, UserInfoActivity.class);
                }
                break;
            case R.id.fragment_mine_userAvatar:
                if (!SharedPreferencesUtil.selectUserInfo(mContext)) {
                    JumpUtil.jump(mContext, PassLoginActivity.class);
                }else{
                    JumpUtil.jump(mContext, UserInfoActivity.class);
                }
                break;
        }
    }

    /**
     * 申请权限
     */
    private void requestPermission() {
        //判断Android版本是否大于23
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE);

            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.CALL_PHONE},
//                        REQUEST_CODE_ASK_CALL_PHONE);
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE_ASK_CALL_PHONE);
            } else {
                diallPhone();
            }
        } else {
            diallPhone();
        }
    }
    /**
     * 拨打电话
     */
    public void diallPhone() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + "123456");
        intent.setData(data);
        startActivity(intent);
    }


    /**
     * 注册权限申请回调
     *
     * @param requestCode  申请码
     * @param permissions  申请的权限
     * @param grantResults 结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_CALL_PHONE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    diallPhone();
                } else {
                    // Permission Denied
                    Toast.makeText(mContext, "CALL_PHONE Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {

    }


    /**
     * 获取用户信息
     *
     * @param data
     */
    @Override
    public void showData(MineEntity data) {
        if (data.getStatus()==0) {
            gson = new Gson();
            String s = gson.toJson(data);
            SharedPreferencesUtil.put(mContext,"userInfo",s);
            Log.d("tag", "userInfo存储数据成功:"+s);
            initUserData(data);
        } else if (data.getStatus()==100008){
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setMessage("您的账号已在另一台设备登录，您被迫下线");
            builder.setTitle("被迫下线");
            builder.setPositiveButton("去登录", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SharedPreferencesUtil.put(mContext,"token","");//清除用户数据
                    UriConstant.clear();
                    JumpUtil.jump(mContext,PassLoginActivity.class);
                    dialog.dismiss();
                    userPhone.setText("");
                    userName.setText("登录/注册");
                    userTime.setVisibility(View.GONE);
                    GlideUtils.loadImage(mContext,R.mipmap.default_avatar, userAvatar);
                }
            });
            //不关闭写法
            builder.setCancelable(false);
            builder.create();
            builder.show();
        }else {
            userPhone.setText("");
            userName.setText("登录/注册");
            userTime.setVisibility(View.GONE);
            GlideUtils.loadImage(mContext,R.mipmap.default_avatar, userAvatar);
            SharedPreferencesUtil.put(mContext,"token","");
            UriConstant.clear();
            UtilToast.showToast(mContext, data.getMsg());
        }
    }

    /**
     * 初始化用户数据
     * @param data
     */
    private void initUserData(MineEntity data) {
        if (SharedPreferencesUtil.selectUserInfo(mContext)){
            userTime.setVisibility(View.VISIBLE);
            userTime.setText("学习时长："+"\n"+data.getData().getUser().getStudy_curriculum_time());
            SharedPreferencesUtil.put(mContext,"token",SharedPreferencesUtil.getStringValue(mContext,"token"));
        }else{
            userTime.setVisibility(View.GONE);
            userPhone.setText("");
            userName.setText("登录/注册");
            GlideUtils.loadImage(mContext,R.mipmap.default_avatar, userAvatar);
            SharedPreferencesUtil.put(mContext,"token","");
            UriConstant.clear();
        }
        if (data.getData()!=null){
            MineEntity.DataBean dataBean = data.getData();
            UriConstant.userAvatar = dataBean.getUser().getHead_img();//将用户头像存储起来
            UriConstant.userNickName = dataBean.getUser().getNick_name();//用户昵称
            UriConstant.userPhone = dataBean.getUser().getUser_name();//用户手机号
            UriConstant.userSex = dataBean.getUser().getSex();//用户性别
            userName.setText(dataBean.getUser().getNick_name());//设置用户名
            userPhone.setText(""+dataBean.getUser().getCompany_name());//设置账号
            if (TextUtils.isEmpty(UriConstant.userAvatar)){
                GlideUtils.loadImage(mContext,R.mipmap.default_avatar, userAvatar);
            }else{
                Glide.with(mContext).load(dataBean.getUser().getHead_img()).into(userAvatar);
            }
            fragmentMine.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showFailureMessage(String msg) {

    }

    @Override
    public void showErrorMessage() {

    }
}
