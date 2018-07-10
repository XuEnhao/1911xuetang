package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.Manifest;
import android.annotation.SuppressLint;
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
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.seek.biscuit.Biscuit;
import com.seek.biscuit.CompressResult;
import com.seek.biscuit.OnCompressCompletedListener;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.UriConstant;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.module.entity.CityEntity;
import com.yijiuyiyiedu.xuetang.module.entity.CityInfoEntity;
import com.yijiuyiyiedu.xuetang.module.entity.EditMyselfInfoBus;
import com.yijiuyiyiedu.xuetang.module.entity.FillRegisterDataEntity;
import com.yijiuyiyiedu.xuetang.module.entity.LoginEntity;
import com.yijiuyiyiedu.xuetang.module.entity.MineEntity;
import com.yijiuyiyiedu.xuetang.module.entity.PositionBus;
import com.yijiuyiyiedu.xuetang.module.persenter.EditMyselfInfoPresenter;
import com.yijiuyiyiedu.xuetang.module.persenter.FillRegisterDataPresenter;
import com.yijiuyiyiedu.xuetang.module.ui.custom.CircleImageView;
import com.yijiuyiyiedu.xuetang.module.ui.custom.MyClearEditText;
import com.yijiuyiyiedu.xuetang.module.view.EditMyselfInfoView;
import com.yijiuyiyiedu.xuetang.module.view.FillRegisterDataView;
import com.yijiuyiyiedu.xuetang.utils.GlideUtils;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.MobileUtils;
import com.yijiuyiyiedu.xuetang.utils.ProgressDialogUtils;
import com.yijiuyiyiedu.xuetang.utils.SharedPreferencesUtil;
import com.yijiuyiyiedu.xuetang.utils.SingleLoginUtils;
import com.yijiuyiyiedu.xuetang.utils.ToastUtils;
import com.yijiuyiyiedu.xuetang.utils.UtilToast;
import com.yijiuyiyiedu.xuetang.utils.UtilsShowPopup;
import com.yijiuyiyiedu.xuetang.utils.WindowUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xuenhao on 2018/6/4.
 * 个人信息设置
 */

public class UserInfoActivity extends BaseActivity implements EditMyselfInfoView, FillRegisterDataView {
    @BindView(R.id.userInfo_back)
    ImageView back;//返回
    @BindView(R.id.userInfo_save)
    TextView save;//保存
    @BindView(R.id.userInfo_avatar)
    LinearLayout avatarLinear;//头像
    @BindView(R.id.userInfo_sexText)
    TextView sexText;//性别
    @BindView(R.id.userInfo_sexLinear)
    LinearLayout sexLinear;//性别 linear
    @BindView(R.id.userInfo_birthText)
    TextView birthText;//生日
    @BindView(R.id.userInfo_birthLinear)
    LinearLayout birthLinear;//生日 linear
    @BindView(R.id.userInfo_areaText)
    TextView areaText;//地区
    @BindView(R.id.userInfo_areaLinear)
    LinearLayout areaLinear;//地区 linear
    @BindView(R.id.userInfo_jobText)
    TextView jobText;//职业
    @BindView(R.id.userInfo_jobLinear)
    LinearLayout jobLinear;//职业linear
    @BindView(R.id.userInfo_phoneText)
    TextView phoneText;//手机号
    @BindView(R.id.userInfo_companyText)
    TextView companyText;//公司
    @BindView(R.id.userInfo_name)
    EditText userName;//用户名字
    @BindView(R.id.userInfo_email)
    EditText email;//邮箱
    @BindView(R.id.userInfo_avatarImg)
    CircleImageView userInfoAvatarImg;
    private String getPositionId;
    private Context mContext;
    private List<CityInfoEntity.DataBean.RegionListBean> options1Items = new ArrayList<>();//省
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();//市
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();//区
    private Gson gson = new Gson();
    private OptionsPickerView pvOptions;
    private OptionsPickerView sexOptions;
    private TimePickerView pvTime;
    private EditMyselfInfoPresenter editPresenter;
    private File file;
    private FillRegisterDataPresenter mPresenter;
    //拍照
    private final int REQUEST_CAMERA = 0;
    //调用系统相册-选择图片
    private static final int IMAGE = 1;
    private String fullPath;//回调成功后的图片路径
    private CityInfoEntity.DataBean cityData;
    private String provinceId;
    private String cityId;
    private String areaId;
    private String positionId;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 6;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE2 = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        mContext = this;
        EventBus.getDefault().register(this);
        initView();
        initTimeView();
        userName.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        email.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getPositonData(PositionBus item) {
        positionId = item.getId();
        jobText.setText(item.getPositionName());
    }

    private void initData(CityInfoEntity.DataBean dataBean) {
        List<CityInfoEntity.DataBean.RegionListBean> jsonBean = dataBean.getRegionList();
        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
//        options1Items= (ArrayList<CityInfoEntity.DataBean.RegionlistBean>) jsonBean;
        options1Items = dataBean.getRegionList();

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCity().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCity().get(c).getName();
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCity().get(c).getCity() == null
                        || jsonBean.get(i).getCity().get(c).getCity().size() == 0) {
                    City_AreaList.add("");
                } else {

                    for (int d = 0; d < jsonBean.get(i).getCity().get(c).getCity().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCity().get(c).getCity().get(d).getName();

                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);

            pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                    //返回的分别是三个级别的选中位置
                    provinceId = cityData.getRegionList().get(options1).getRegion_code();
                    cityId = cityData.getRegionList().get(options1).getCity().get(option2).getRegion_code();
                    areaId = cityData.getRegionList().get(options1).getCity().get(option2).getCity().get(options3)
                            .getRegion_code();

                    String tx = options1Items.get(options1).getPickerViewText()
                            + options2Items.get(options1).get(option2)
                            + options3Items.get(options1).get(option2).get(options3);
                    areaText.setText(tx);
                }
            }).setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
                @Override
                public void onOptionsSelectChanged(int options1, int options2, int options3) {
                    String str = "options1: " + options1 + "\noptions2: " + options2 + "\noptions3: " + options3;
//                ToastUtils.showShort(str);
                }
            })
                    .setSubmitText("确定")//确定按钮文字
                    .setCancelText("")//取消按钮文字
                    .setTitleText("")//标题
                    .setSubCalSize(16)//确定和取消文字大小
                    .setTitleSize(20)//标题文字大小
                    .setTitleColor(Color.BLACK)//标题文字颜色
                    .setSubmitColor(Color.parseColor("#6317A5"))//确定按钮文字颜色
                    .setCancelColor(Color.parseColor("#333333"))//取消按钮文字颜色
                    .setTitleBgColor(Color.parseColor("#F8F9FB"))//标题背景颜色 Night mode
                    .setBgColor(0xFFffffff)//滚轮背景颜色 Night mode
//                    .setBackgroundId(Color.TRANSPARENT)
                    .setContentTextSize(18)//滚轮文字大小
//                .setLinkage(false)//设置是否联动，默认true
//                .setLabels("省", "市", "区")//设置选择的三级单位
                    .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                    .setCyclic(false, false, false)//循环与否
//                .setSelectOptions(1, 1, 1)  //设置默认选中项
                    .setOutSideCancelable(true)//点击外部dismiss default true
//                .isDialog(true)//是否显示为对话框样式
                    .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                    .build();
            pvOptions.setPicker(options1Items, options2Items, options3Items);//添加数据源
            //地区linear
            areaLinear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (pvOptions != null) {
                        pvOptions.show();
                    }
                }
            });
        }
    }

    /**
     * 初始化时间选择器
     */
    private void initTimeView() {
        Calendar selectedDate = Calendar.getInstance();
        selectedDate.setTime(new Date());
        Calendar startDate = Calendar.getInstance();
        startDate.set(1970, 0, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2020, 1, 1);

        //时间选择器
        if (pvTime == null) {
            pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
                @SuppressLint("SimpleDateFormat")
                @Override
                public void onTimeSelect(Date date, View v) {
                    //business
                    SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
                    String format = sm.format(date);
                    birthText.setText(format);
                }
            }).setRangDate(startDate, endDate)
                    .setSubmitText("确定")//确定按钮文字
                    .setCancelText("")//取消按钮文字
                    .setTitleText("")//标题
                    .setSubCalSize(16)//确定和取消文字大小
//                    .setBackgroundId(0x80000000)
//                    .setBackgroundId(Color.TRANSPARENT)
                    .setTitleSize(20)//标题文字大小
                    .setTitleColor(Color.BLACK)//标题文字颜色
                    .setSubmitColor(Color.parseColor("#6317A5"))//确定按钮文字颜色
                    .setCancelColor(Color.parseColor("#333333"))//取消按钮文字颜色
                    .setTitleBgColor(Color.parseColor("#F8F9FB"))//标题背景颜色 Night mode
                    .setType(new boolean[]{true, true, true, false, false, false})//数组布尔值分别代表 年 月 日 时 分 秒
                    .setDate(selectedDate)
                    .build();
        }
    }


    /**
     * 初始化布局
     */
    private void initView() {

        editPresenter = new EditMyselfInfoPresenter(this);
        editPresenter.getCityInfo();
        mPresenter = new FillRegisterDataPresenter(this);
        String userInfo = SharedPreferencesUtil.getStringValue(mContext, "userInfo");
        gson = new Gson();
        MineEntity mineEntity = gson.fromJson(userInfo, MineEntity.class);
        MineEntity.DataBean.UserBean user = mineEntity.getData().getUser();
        phoneText.setText(UriConstant.userPhone);
        fullPath = UriConstant.userAvatar;
        if (TextUtils.isEmpty(UriConstant.userAvatar)) {
            GlideUtils.loadImage(mContext, R.mipmap.default_avatar, userInfoAvatarImg);
        } else {
            GlideUtils.loadImage(mContext, UriConstant.userAvatar, userInfoAvatarImg);
        }
        areaText.setText(user.getProvince_name() + user.getCity_name() + user.getArea_name());
        provinceId = user.getProvince();
        cityId = user.getCity();
        areaId = user.getArea();
        birthText.setText(user.getBirthday());
        jobText.setText(user.getPosition_name());
        positionId = user.getPosition();
        phoneText.setText(user.getUser_name());
        companyText.setText(user.getCompany_name());
        userName.setText(UriConstant.userNickName);
        email.setText(user.getEmail());
        positionId = mineEntity.getData().getUser().getPosition();
        switch (UriConstant.userSex) {
            case "1":
                sexText.setText("男");
                break;
            case "2":
                sexText.setText("女");
                break;
            case "3":
                sexText.setText("保密");
                break;
        }

        //返回
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.overiderAnimsition((Activity) mContext);
            }
        });
        //保存
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                judgeInfo();//检查信息
            }
        });
        //性别linear
        final View view = View.inflate(mContext, R.layout.popupwindow_sex, null);
        final UtilsShowPopup popup = new UtilsShowPopup();
        sexLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.showBottomPopup(mContext, getWindow(), view, findViewById(R.id.userInfo_scrollView));
            }
        });
        view.findViewById(R.id.man).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sexText.setText("男");
                popup.popupWindow.dismiss();
            }
        });

        view.findViewById(R.id.woman).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sexText.setText("女");
                popup.popupWindow.dismiss();
            }
        });

        view.findViewById(R.id.privary).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sexText.setText("保密");
                popup.popupWindow.dismiss();
            }
        });

        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.popupWindow.dismiss();
            }
        });

        //生日linear
        birthLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvTime.show();
            }
        });
        //职业
        jobLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                JumpUtil.jump(mContext, PositionActivity.class);
                Intent it = new Intent(mContext,PositionActivity.class);
                it.putExtra("job",jobText.getText().toString());
                startActivity(it);
            }
        });
        //点击更换头像
        final View imgView = View.inflate(mContext, R.layout.fill_register_data_dialog, null);
        avatarLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.showBottomPopup(mContext, getWindow(), imgView, findViewById(R.id.userInfo_scrollView));
            }
        });
        //拍照
        imgView.findViewById(R.id.dialog_picker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.popupWindow.dismiss();
                takePhone();//拍照
            }
        });
        imgView.findViewById(R.id.dialog_pickerImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.popupWindow.dismiss();
                choosePhone();//选择相片

            }
        });
        imgView.findViewById(R.id.dialog_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.popupWindow.dismiss();
            }
        });
    }

    //检查信息
    private void judgeInfo() {
        if (TextUtils.isEmpty(userName.getText().toString())) {
            ToastUtils.showShort("请填写昵称");
        } else if (TextUtils.isEmpty(sexText.getText().toString())) {
            ToastUtils.showShort("请选择昵称");
        } else if (TextUtils.isEmpty(birthText.getText().toString())) {
            ToastUtils.showShort("请选择生日");
        } else if (TextUtils.isEmpty(areaText.getText().toString())) {
            ToastUtils.showShort("请选择地区");
        } else if (TextUtils.isEmpty(jobText.getText().toString())) {
            ToastUtils.showShort("请选择职位");
        } else if (TextUtils.isEmpty(email.getText().toString())) {
            ToastUtils.showShort("请填写邮箱");
        } else if (!MobileUtils.isEmail(email.getText().toString())){
            ToastUtils.showShort("请填写正确的邮箱");
        }else {
            int number = 0;
            switch (sexText.getText().toString()) {
                case "男":
                    number = 1;
                    break;
                case "女":
                    number = 2;
                    break;
                case "保密":
                    number = 3;
                    break;
            }
            editPresenter.getSaveData(fullPath, birthText.getText().toString(), String.valueOf(number),
                    userName.getText().toString(), Integer.valueOf(provinceId), Integer.valueOf(cityId),
                    Integer.valueOf(areaId), Integer.valueOf(positionId), email.getText().toString());
        }
    }

    @Override
    public void showSaveData(MineEntity data) {
        if (data.getStatus() == 0) {
            ToastUtils.showShort(data.getMsg());
            JumpUtil.overiderAnimsition(this);
            EventBus.getDefault().postSticky(new EditMyselfInfoBus("1"));
        } else if (data.getStatus() == 100008) {
            SingleLoginUtils.showDialog(mContext);
        } else {
            ToastUtils.showShort(data.getMsg());
        }
    }


    @Override
    public void showCityData(CityInfoEntity data) {
        if (data.getStatus() == 0) {
            cityData = data.getData();
            initData(data.getData());//省市县
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
            GlideUtils.loadImage(mContext, data.getData().getFull_path(), userInfoAvatarImg);
        } else {
            UtilToast.showToast(mContext, data.getMsg());
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

    public void takePhone() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE2);

        } else {
            //调用相机
            useCamera();
        }

    }

    public void choosePhone() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE2);

        } else {
            choosePhoto();//选择相册
        }
    }

    private void choosePhoto() {
        //调用相册
//        Intent intent = new Intent(Intent.ACTION_PICK,
//                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(intent, IMAGE);
        // 进入相册 以下是例子：用不到的api可以不写
        PictureSelector.create((Activity) mContext)
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                .minSelectNum(1)// 最小选择数量 int
                .imageSpanCount(4)// 每行显示个数 int
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
                .isCamera(false)// 是否显示拍照按钮 true or false
                .isZoomAnim(false)// 图片列表点击 缩放效果 默认true
                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .enableCrop(true)// 是否裁剪 true or false
                .compress(false)// 是否压缩 true or false
                .glideOverride(200, 200)// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .withAspectRatio(1, 1)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .isGif(false)// 是否显示gif图片 true or false
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                .circleDimmedLayer(false)// 是否圆形裁剪 true or false
                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                //  .selectionMedia(t)// 是否传入已选图片 List<LocalMedia> list
                .previewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
                .cropCompressQuality(90)// 裁剪压缩质量 默认90 int
                .rotateEnabled(false) // 裁剪是否可旋转图片 true or false
                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                .enableCrop(true)// 是否裁剪
                .previewImage(true)
                .compress(true)// 是否压缩
                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .minimumCompressSize(90)// 小于90kb的图片不压缩
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    @Override
    protected void onDestroy() {
        WindowUtils.hintKeyboard(this);
        super.onDestroy();
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
        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                useCamera();
            } else {
                // Permission Denied
                Toast.makeText(mContext, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }


        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE2) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                choosePhoto();
            } else {
                // Permission Denied
                Toast.makeText(mContext, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * 使用相机
     */
    private void useCamera() {
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
//                + "/test/" + System.currentTimeMillis() + ".jpg");
//        file.getParentFile().mkdirs();
//
//        //改变Uri  com.xykj.customview.fileprovider注意和xml中的一致
//        Uri uri = FileProvider.getUriForFile(this, "com.yijiuyiyiedu.xuetang", file);
//        //添加权限
//        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
//        startActivityForResult(intent, REQUEST_CAMERA);
        // 单独拍照
        PictureSelector.create((Activity) mContext)
                .openCamera(PictureMimeType.ofImage())// 单独拍照，也可录像或也可音频 看你传入的类型是图片or视频
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选
                .previewImage(true)// 是否可预览图片
                .previewVideo(false)// 是否可预览视频
                .enablePreviewAudio(false) // 是否可播放音频
                .isCamera(false)// 是否显示拍照按钮
                .enableCrop(true)// 是否裁剪
                .compress(false)// 是否压缩
//                .setOutputCameraPath("/idolmedia")// 自定义拍照保存路径,可不填
                .glideOverride(200, 200)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .isGif(false)// 是否显示gif图片
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                .circleDimmedLayer(false)// 是否圆形裁剪 true or false
                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                .previewEggs(false)//预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                .enableCrop(true)// 是否裁剪
                .previewImage(true)
                .compress(true)// 是否压缩
                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .minimumCompressSize(90)// 小于90kb的图片不压缩
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
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
        if (requestCode == PictureConfig.CHOOSE_REQUEST && resultCode == RESULT_OK) {
            //拍照回调
//            Log.e("TAG", "---------" + FileProvider.getUriForFile(this, "com.xykj.customview.fileprovider", file));
//            compressPhoto(file.getAbsolutePath());
            List<LocalMedia> localMedia = PictureSelector.obtainMultipleResult(data);
            mPresenter.uploadImage("logo", localMedia.get(0).getCompressPath());//p层上传图片
            //包括裁剪和压缩后的缓存，要在上传成功后调用，注意：需要系统sd卡权限
            PictureFileUtils.deleteCacheDirFile(mContext);
        } else if (requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            //相册回调
            try {
                Uri selectedImage = data.getData();
                String[] filePathColumns = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePathColumns[0]);
                String imagePath = c.getString(columnIndex);
                c.close();
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("tag", e.getMessage());
            }

        }
    }

}
