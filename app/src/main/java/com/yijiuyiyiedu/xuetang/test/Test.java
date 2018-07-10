package com.yijiuyiyiedu.xuetang.test;

import android.Manifest;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.rtmp.TXPlayerAuthBuilder;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.MainActivity;
import com.yijiuyiyiedu.xuetang.play.TXPlayerAuthParam;
import com.yijiuyiyiedu.xuetang.play.wkvideoplayer.server.VideoInfo;
import com.yijiuyiyiedu.xuetang.play.wkvideoplayer.util.DensityUtil;
import com.yijiuyiyiedu.xuetang.play.wkvideoplayer.util.TCConstants;
import com.yijiuyiyiedu.xuetang.play.wkvideoplayer.server.GetVideoInfoListListener;
import com.yijiuyiyiedu.xuetang.play.wkvideoplayer.view.MediaController;
import com.yijiuyiyiedu.xuetang.play.wkvideoplayer.view.SuperVideoPlayer;
import com.yijiuyiyiedu.xuetang.play.wkvideoplayer.server.VideoDataMgr;
import com.yijiuyiyiedu.xuetang.play.wkvideoplayer.view.VodRspData;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by xuenhao on 2018/3/26.
 */

public class Test extends AppCompatActivity {
    Context mContext;
    volatile String url = "http://gcqq450f71eywn6bv7u.exp.bcevod.com/mda-hbqagik5sfq1jsai/mda-hbqagik5sfq1jsai.mp4";
    private SuperVideoPlayer mSuperVideoPlayer;
    private ImageView mPlayBtnView;
    private TXPlayerAuthParam mCurrentFileIDParam;
    private boolean isPlayDefaultVideo = true;
    private String videoIdFrom;
    private String titleFrom;
    private GetVideoInfoListListener mGetVideoInfoListListener;
    private String TAG = "Test";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mContext = this;
        getWindow().addFlags(WindowManager.LayoutParams.
                FLAG_KEEP_SCREEN_ON);
        checkPermission();
        initView();


        ZLoadingDialog dialog = new ZLoadingDialog(this);
        dialog.setLoadingBuilder(Z_TYPE.ROTATE_CIRCLE)//设置类型
                .setLoadingColor(Color.parseColor("#6317A5"))//颜色
                .setHintText("Loading...")
                .setHintTextSize(16) // 设置字体大小 dp
                .setHintTextColor(Color.GRAY)  // 设置字体颜色
                .setDurationTime(0.5) // 设置动画时间百分比 - 0.5倍
//                .setCancelable(false)//点击空白不关闭
                .setCanceledOnTouchOutside(false)
                .setDialogBackgroundColor(Color.parseColor("#ffffff"))// 设置背景色，默认白色
                .show();

    }

    private void initView() {
        mSuperVideoPlayer = (SuperVideoPlayer) findViewById(R.id.video_player_item_1);
        mSuperVideoPlayer.setVideoPlayCallback(mVideoPlayCallback);

        mPlayBtnView = (ImageView) findViewById(R.id.play_btn);
        mPlayBtnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentFileIDParam != null){
                    playVideoWithFileId(mCurrentFileIDParam);
                }
            }
        });

        getData();
        playVideo();
        initData();
    }

    private void initData() {
        if(isPlayDefaultVideo){
            TXPlayerAuthParam param2 = new TXPlayerAuthParam();
            param2.appId = "1252463788";
            param2.fileId = "4564972819219071568";
            mSuperVideoPlayer.addVodInfo(param2);

            TXPlayerAuthParam param3 = new TXPlayerAuthParam();
            param3.appId = "1252463788";
            param3.fileId = "4564972819219071668";
            mSuperVideoPlayer.addVodInfo(param3);

            TXPlayerAuthParam param4 = new TXPlayerAuthParam();
            param4.appId = "1252463788";
            param4.fileId = "4564972819219071679";
            mSuperVideoPlayer.addVodInfo(param4);

            TXPlayerAuthParam param5 = new TXPlayerAuthParam();
            param5.appId = "1252463788";
            param5.fileId = "4564972819219081699";
            mSuperVideoPlayer.addVodInfo(param5);

        }else{
            VideoDataMgr.getInstance().setGetVideoInfoListListener(mGetVideoInfoListListener);
            VideoDataMgr.getInstance().getVideoList();
        }
    }

    private void playVideo() {
        mPlayBtnView.setVisibility(View.GONE);
        mSuperVideoPlayer.setVisibility(View.VISIBLE);
        mSuperVideoPlayer.setAutoHideController(false);

        TXPlayerAuthParam param = new TXPlayerAuthParam();
        if(isPlayDefaultVideo){
            param.appId = "1252463788";
            param.fileId = "4564972819220421305";
            mSuperVideoPlayer.addVodInfo(param);
            playVideoWithFileId(param);
        }else if( !TextUtils.isEmpty(videoIdFrom) ){
            param.appId = TCConstants.VOD_APPID;
            param.fileId = videoIdFrom;
            mSuperVideoPlayer.addVodInfo(param);
            playVideoWithFileId(param);
        }
    }
    /**
     * 权限
     */
    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            List<String> permissions = new ArrayList<>();
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)) {
                permissions.add(Manifest.permission.CAMERA);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (permissions.size() != 0) {
                ActivityCompat.requestPermissions(this, permissions.toArray(new String[0]), 100);
            }
        }
    }

    private void playVideoWithFileId(TXPlayerAuthParam param) {
        TXPlayerAuthBuilder authBuilder = new TXPlayerAuthBuilder();
        try {
            if (!TextUtils.isEmpty(param.appId)) {
                authBuilder.setAppId(Integer.parseInt(param.appId));
            }
            String fileId = param.fileId;
            authBuilder.setFileId(fileId);

            if (mSuperVideoPlayer != null) {
                mSuperVideoPlayer.playFileID(authBuilder);
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mSuperVideoPlayer.loadVideo();
                }
            });
        } catch (NumberFormatException e) {
            Toast.makeText(this, "请输入正确的AppId和FileId", Toast.LENGTH_SHORT).show();
        }
    }

    private void getData() {
        isPlayDefaultVideo = getIntent().getBooleanExtra(TCConstants.PLAYER_DEFAULT_VIDEO, true);
        if(isPlayDefaultVideo){
            return;
        }
        mCurrentFileIDParam = new TXPlayerAuthParam();
        mCurrentFileIDParam.appId = TCConstants.VOD_APPID;

        videoIdFrom = getIntent().getStringExtra(TCConstants.PLAYER_VIDEO_ID);
        titleFrom = getIntent().getStringExtra(TCConstants.PLAYER_VIDEO_NAME);
        if( !TextUtils.isEmpty(videoIdFrom) ){
            mCurrentFileIDParam.fileId = videoIdFrom;
            mSuperVideoPlayer.updateUI(titleFrom);
            playVideoWithFileId(mCurrentFileIDParam);
        }
        initGetVideoInfoListListener();
        if( !isPlayDefaultVideo ){
//            mBtnScan.setVisibility(View.GONE);
//            mIvAdd.setVisibility(View.GONE);
        }
    }
    private void initGetVideoInfoListListener() {
        mGetVideoInfoListListener = new GetVideoInfoListListener() {


            @Override
            public void onGetVideoInfoList(List<VideoInfo> videoInfoList) {
                loadVideoInfoList(videoInfoList);
            }

            @Override
            public void onFail(int errCode) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mContext, "获取已上传的视频列表失败", Toast.LENGTH_SHORT).show();
//                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        };
    }
    private void loadVideoInfoList(List<VideoInfo> videoInfoList) {
        if (videoInfoList == null || videoInfoList.size() == 0)
            return;
//        mVodList.clear();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                mAdapter.clear();
            }
        });
        for (VideoInfo videoInfo : videoInfoList) {
            TXPlayerAuthParam param = new TXPlayerAuthParam();
            param.appId = TCConstants.VOD_APPID;
            param.fileId = videoInfo.fileId;
            TXCLog.i(TAG, " fileId:" + param.fileId);
            mSuperVideoPlayer.addVodInfo(param);
//            mVodList.add(param);
        }

//        mSuperVideoPlayer.setVideoListInfo(mVodList);
//        mSuperVideoPlayer.getNextInfo();

        if(TextUtils.isEmpty(mCurrentFileIDParam.fileId)){
            final VideoInfo videoInfo = videoInfoList.get(0);
            mCurrentFileIDParam.fileId = videoInfo.fileId;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mSuperVideoPlayer.updateUI(videoInfo.name);
                }
            });
            playVideoWithFileId(mCurrentFileIDParam);
        }
    }

    private SuperVideoPlayer.VideoPlayCallbackImpl mVideoPlayCallback = new SuperVideoPlayer.VideoPlayCallbackImpl() {
        @Override
        public void onCloseVideo() {
            mSuperVideoPlayer.onDestroy();
            mPlayBtnView.setVisibility(View.VISIBLE);
            mSuperVideoPlayer.setVisibility(View.GONE);
            resetPageToPortrait();
        }

        @Override
        public void onSwitchPageType() {
            if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//                mLyTop.setVisibility(View.VISIBLE);
                mSuperVideoPlayer.setPageType(MediaController.PageType.SHRINK);
            } else {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//                mLyTop.setVisibility(View.GONE);
                mSuperVideoPlayer.setPageType(MediaController.PageType.EXPAND);
            }
        }

        @Override
        public void onPlayFinish() {
            mPlayBtnView.setVisibility(View.VISIBLE);
        }

        @Override
        public void onBack() {
            if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//                mLyTop.setVisibility(View.VISIBLE);
                mSuperVideoPlayer.setPageType(MediaController.PageType.SHRINK);
            } else {
                finish();
            }
        }

        @Override
        public void onLoadVideoInfo(VodRspData data) {
//            mAdapter.addVideoInfo(data);
        }
    };

    /***
     * 恢复屏幕至竖屏
     */
    private void resetPageToPortrait() {
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            mSuperVideoPlayer.setPageType(MediaController.PageType.SHRINK);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSuperVideoPlayer != null) {
            mSuperVideoPlayer.onDestroy();
        }
        VideoDataMgr.getInstance().setGetVideoInfoListListener(null);
    }

    /***
     * 旋转屏幕之后回调
     *
     * @param newConfig newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (null == mSuperVideoPlayer) return;
        /***
         * 根据屏幕方向重新设置播放器的大小
         */
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().getDecorView().invalidate();
            float height = DensityUtil.getWidthInPx(this);
            float width = DensityUtil.getHeightInPx(this);
            mSuperVideoPlayer.getLayoutParams().height = (int) width;
            mSuperVideoPlayer.getLayoutParams().width = (int) height;
        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            final WindowManager.LayoutParams attrs = getWindow().getAttributes();
            attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(attrs);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            float width = DensityUtil.getWidthInPx(this);
            float height = DensityUtil.dip2px(this, 200.f);
            mSuperVideoPlayer.getLayoutParams().height = (int) height;
            mSuperVideoPlayer.getLayoutParams().width = (int) width;
        }
    }
}
