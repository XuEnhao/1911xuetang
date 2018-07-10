package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.api.constant.UriConstant;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.module.base.BaseNewActivity;
import com.yijiuyiyiedu.xuetang.module.base.SwipeBackActivity;
import com.yijiuyiyiedu.xuetang.module.entity.CityInfoEntity;
import com.yijiuyiyiedu.xuetang.module.entity.EditMyselfInfoBus;
import com.yijiuyiyiedu.xuetang.module.entity.FillRegisterDataEntity;
import com.yijiuyiyiedu.xuetang.module.entity.LoginEntity;
import com.yijiuyiyiedu.xuetang.module.entity.MineEntity;
import com.yijiuyiyiedu.xuetang.module.persenter.EditMyselfInfoPresenter;
import com.yijiuyiyiedu.xuetang.module.persenter.FillRegisterDataPresenter;
import com.yijiuyiyiedu.xuetang.module.ui.custom.CircleImageView;
import com.yijiuyiyiedu.xuetang.module.ui.custom.SketchLengthFilter;
import com.yijiuyiyiedu.xuetang.module.ui.custom.SketchTextWatcher;
import com.yijiuyiyiedu.xuetang.module.view.EditMyselfInfoView;
import com.yijiuyiyiedu.xuetang.module.view.FillRegisterDataView;
import com.yijiuyiyiedu.xuetang.utils.GlideUtils;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.LogUtil;
import com.yijiuyiyiedu.xuetang.utils.SharedPreferencesUtil;
import com.yijiuyiyiedu.xuetang.utils.UtilToast;
import com.yijiuyiyiedu.xuetang.utils.UtilsShowPopup;
import com.yijiuyiyiedu.xuetang.utils.titlebar.StatusBarUtil;
import com.yijiuyiyiedu.xuetang.widget.StateButton;


import org.greenrobot.eventbus.EventBus;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xuenhao on 2018/3/21.
 * 编辑个人信息页面
 */

public class EditMyselfInfoActivity extends BaseActivity implements FillRegisterDataView, EditMyselfInfoView {
    @BindView(R.id.buyRecord_back)
    ImageView back;//返回
    @BindView(R.id.edit_userAvatar)
    CircleImageView userAvatar;//用户头像
    @BindView(R.id.edit_updateAvatar)
    TextView updateAvatar;//更换头像
    @BindView(R.id.edit_userName)
    EditText userName;//用户名 默认手机号
    @BindView(R.id.edit_sex)
    TextView sex;//性别
    @BindView(R.id.edit_phoneNumber)
    EditText phoneNumber;//手机号
    @BindView(R.id.edit_companyName)
    EditText companyName;//公司名字
    @BindView(R.id.edit_save)
    StateButton save;//保存按钮
    Context mContext;
    EditMyselfInfoPresenter editPresenter;
    private File file;
    FillRegisterDataPresenter mPresenter;
    //拍照
    private final int REQUEST_CAMERA = 0;
    //调用系统相册-选择图片
    private static final int IMAGE = 1;
    private String fullPath;//回调成功后的图片路径
    //            性别 1男 2女 3保密
    private String isMan = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_myself_info);
        ButterKnife.bind(this);
        StatusBarUtil.setStatusBar(this,R.id.edit_statusBar);
        mContext = this;
//        setStatusBar();
        initData();
        userName.addTextChangedListener(new SketchTextWatcher(userName));
        InputFilter[] filters = {new SketchLengthFilter()};
        userName.setFilters(filters);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mPresenter = new FillRegisterDataPresenter(this);
        editPresenter = new EditMyselfInfoPresenter(this);
        Glide.with(mContext).load(Constant.BASE_URL + UriConstant.userAvatar).into(userAvatar);
        userName.setText(UriConstant.userNickName + "");//姓名默认手机号
        userName.setSelection(UriConstant.userNickName.length());//游标位置
        phoneNumber.setText(UriConstant.userPhone + "");//手机号
        if (!TextUtils.isEmpty(UriConstant.userRealName)) {
            companyName.setText(UriConstant.userRealName + "");//真实姓名
        } else {
            companyName.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(UriConstant.userSex)) {
//            男1 女2 保密3
            if (UriConstant.userSex.equals("1")) {
                sex.setText("男");//用户性别
            } else if (UriConstant.userSex.equals("2")) {
                sex.setText("女");//用户性别
            } else if (UriConstant.userSex.equals("3")) {
                sex.setText("保密");//用户性别
            } else {

            }
        }
        //性别
        final View view = View.inflate(mContext, R.layout.popupwindow_sex, null);
        sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                UtilsShowPopup.showBottomPopup(mContext, getWindow(), view, findViewById(R.id.fill_scrollView));
            }
        });
        view.findViewById(R.id.man).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sex.setText("男");
                isMan = "1";
//                UtilsShowPopup.popupWindow.dismiss();
            }
        });

        view.findViewById(R.id.woman).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sex.setText("女");
                isMan = "2";
//                UtilsShowPopup.popupWindow.dismiss();
            }
        });

        view.findViewById(R.id.privary).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sex.setText("保密");
                isMan = "3";
//                UtilsShowPopup.popupWindow.dismiss();
            }
        });

        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                UtilsShowPopup.popupWindow.dismiss();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.overiderAnimsition((Activity) mContext);
            }
        });
        //点击更换头像
        final View imgView = View.inflate(mContext, R.layout.fill_register_data_dialog, null);
        userAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                UtilsShowPopup.showBottomPopup(mContext, getWindow(), imgView, findViewById(R.id.fill_scrollView));
            }
        });
        //拍照
        imgView.findViewById(R.id.dialog_picker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                UtilsShowPopup.popupWindow.dismiss();
                applyWritePermission();
            }
        });
        imgView.findViewById(R.id.dialog_pickerImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                UtilsShowPopup.popupWindow.dismiss();
                //调用相册
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMAGE);
            }
        });
        imgView.findViewById(R.id.dialog_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                UtilsShowPopup.popupWindow.dismiss();
            }
        });
    }


//    //导航栏
    private void setStatusBar() {
//         设置透明导航栏
//        StatusBarUtil.setTransparent(this);
        StatusBarUtil.setTranslucentForImageView(this, 0, null);
//        StatusBarUtil.setStatusBar(this);
//        StatusBarUtil.createStatusBarView(this,Color.parseColor("#161616"),100);
//        int statusBarHeight = StatusBarUtil.getStatusBarHeight(this);
//        View viewById = findViewById(R.id.edit_statusBar);
//        ViewGroup.LayoutParams layoutParams = viewById.getLayoutParams();
//        layoutParams.height = statusBarHeight;
    }



    //获取写入权限
    public void applyWritePermission() {

        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

        if (Build.VERSION.SDK_INT >= 23) {
            int check = ContextCompat.checkSelfPermission(this, permissions[0]);
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (check == PackageManager.PERMISSION_GRANTED) {
                //调用相机
                useCamera();
            } else {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        } else {
            useCamera();
        }
    }

    /**
     * 动态权限
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            useCamera();
        } else {
            // 没有获取 到权限，从新请求，或者关闭app
            Toast.makeText(this, "需要存储权限", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 使用相机
     */
    private void useCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/test/" + System.currentTimeMillis() + ".jpg");
        file.getParentFile().mkdirs();

        //改变Uri  com.xykj.customview.fileprovider注意和xml中的一致
        Uri uri = FileProvider.getUriForFile(this, "com.onenineoneone._1911school", file);
        //添加权限
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    /**
     * 回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
            //拍照回调
            LogUtil.LogE("TAG", "---------" + FileProvider.getUriForFile(this, "com.xykj.customview.fileprovider", file));
            compressPhoto(file.getAbsolutePath());
        } else if (requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            //相册回调
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            compressPhoto(imagePath);
            c.close();
        }
    }

    @Override
    public void showData(LoginEntity data) {

    }

    /**
     * 上传图片回调
     *
     * @param data
     */
    @Override
    public void uploadData(FillRegisterDataEntity data) {
        if (data.getStatus() == 1) {
            UtilToast.showToast(mContext, data.getMsg());
            fullPath = data.getData().getFull_path();
            GlideUtils.loadImage(mContext, data.getData().getFull_path(), userAvatar);
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showFailureMessage(String msg) {

    }

    @Override
    public void showErrorMessage() {

    }

    /**
     * 压缩图片
     *
     * @param absolutePath
     */
    private void compressPhoto(String absolutePath) {
//        Biscuit.with(this)
//                .path(absolutePath)
//                .ignoreLessThan(500)//小于500kb的图片不压缩
//                .listener(this)//压缩监听
//                .build()
//                .asyncCompress();//异步压缩
    }

    /**
     * 压缩监听回调
     *
//     * @param compressResult
     */
//    @Override
//    public void onCompressCompleted(CompressResult compressResult) {
//        mPresenter.uploadImage("logo", compressResult.mSuccessPaths.get(0));//p层上传图片
//    }

//    @Override
//    public void showSaveData(FillRegisterDataEntity data) {
//        if (data.getStatus() == 1) {
//            UtilToast.showToast(mContext, data.getMsg());
//            //保存用户信息
//            SharedPreferencesUtil.put(mContext, "userNickName", userName.getText().toString());
//            if (!TextUtils.isEmpty(fullPath)) {//如果选择图片了
//                SharedPreferencesUtil.put(mContext, "userAvatar", fullPath);
//                UriConstant.userAvatar = fullPath;
//            }
//            SharedPreferencesUtil.put(mContext, "userSex", sex.getText().toString());
//            UriConstant.userNickName = userName.getText().toString();
//            UriConstant.userSex = isMan;
//            EventBus.getDefault().postSticky(new EditMyselfInfoBus("1"));
//            finish();
//        }
//    }

    @OnClick(R.id.edit_save)
    public void onViewClicked() {
        if (TextUtils.isEmpty(sex.getText().toString())) {
            UtilToast.showToast(mContext, "请选择性别");
        } else {

            if (sex.getText().toString().equals("男")) {
                isMan = "1";
            } else if (sex.getText().toString().equals("女")) {
                isMan = "2";
            } else if (sex.getText().toString().equals("保密")) {
                isMan = "3";
            }
            if (TextUtils.isEmpty(fullPath)) {
//                editPresenter.getSaveData(UriConstant.userAvatar, "", isMan, userName.getText().toString(), UriConstant.userId);
//            } else {
//                editPresenter.getSaveData(fullPath, "", isMan, userName.getText().toString(), UriConstant.userId);
            }
        }
    }

    @Override
    public void showSaveData(MineEntity data) {

    }

    @Override
    public void showCityData(CityInfoEntity data) {

    }
}
