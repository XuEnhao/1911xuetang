package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.moos.library.HorizontalProgressView;
import com.tencent.rtmp.downloader.TXVodDownloadMediaInfo;
import com.yijiuyiyiedu.xuetang.DownloadService;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.ClassContentEntity;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.DownloadSectionAdapter;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.LogUtil;
import com.yijiuyiyiedu.xuetang.utils.ToastUtils;
import com.yijiuyiyiedu.xuetang.utils.WindowUtils;
import com.yijiuyiyiedu.xuetang.utils.encryption.Base64Utils;
import com.yijiuyiyiedu.xuetang.utils.encryption.RSAUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xuenhao on 2018/6/19.
 * 下载详情
 */

public class DownloadDetailsActivity extends BaseActivity{
    @BindView(R.id.addCourseId_details_back)
    ImageView back;//返回
    @BindView(R.id.download_details_space)
    TextView space;//剩余空间
    @BindView(R.id.download_details_progress)
    HorizontalProgressView progress;//剩余空间
    @BindView(R.id.download_details_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.download_details_allCheck)
    TextView allCheck;//全选
    @BindView(R.id.download_details_confirm)
    TextView pause;//全部暂停
    private Context mContext;
    private DownloadSectionAdapter adapter;
    private DownloadService myService = null;
    private MyReceiver myReceiver;
    private List<ClassContentEntity.DataBean.CurriculumCatalogListBean.ChildListBean> downloadList;
    private String encode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_details);
        ButterKnife.bind(this);
        mContext = this;
        downloadList = new ArrayList<>();
        //获取要下载的视频
        downloadList = (List<ClassContentEntity.DataBean.CurriculumCatalogListBean.ChildListBean>) getIntent().getSerializableExtra("listObj");
        myReceiver = new MyReceiver();
        IntentFilter filter = new IntentFilter("com.yijiuyiyiedu.xuetang");
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(myReceiver, filter);
        initView();
        //------------------测试RSA加密-----------------------
        String string = "123456";
        try {
            //获取公钥
            PublicKey publicKey = RSAUtils.keyStrToPublicKey(RSAUtils.PublicKey);
            //公钥加密结果
            encode = RSAUtils.encryptDataByPublicKey(string.getBytes(),publicKey);
            Log.d("encryptData", "encryptData:"+ encode);
        } catch (Exception e) {
            e.printStackTrace();
        }

        OkGo.<String>post("http://ceshi.1911edu.com/Web/Test/rsaWord")
                .params("str",encode)
                .execute(new JsonCallback<String>() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.d("encryptData", "onSuccess: "+response.body());
                    }
                });
        //---------------------RSA------------------------

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(myReceiver);
    }

    private void initView() {
        adapter = new DownloadSectionAdapter(R.layout.item_download_details,downloadList);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter);
        String sdTotalSize = WindowUtils.getSDTotalSize(mContext);//总内存 单位GB
        String gb = sdTotalSize.substring(0, sdTotalSize.indexOf("GB"));//把GB去除
        String sdAvailableSize = WindowUtils.getSDAvailableSize(mContext);//剩余空间 单位GB
        String gb1 = sdAvailableSize.substring(0, sdAvailableSize.indexOf("GB"));//把GB去除
        float v = (float)((Double.parseDouble(gb)-Double.parseDouble(gb1))*100 / Double.parseDouble(gb));//进度
        LogUtil.LogD("tag", "Double.parseDouble(gb1):"+Double.parseDouble(gb1)+"Double.parseDouble(gb):"+Double.parseDouble(gb)+"v:"+v);
        progress.setProgress(v);//进度条
        space.setText("剩余"+WindowUtils.getSDAvailableSize(mContext));
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showShort("点击了"+position);
            }
        });
        //返回
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.overiderAnimsition((Activity) mContext);
            }
        });
        //全部暂停
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, DownloadService.class);
                i.putExtra("flag","pause");
                startService(i);
            }
        });
//        onBindService(progress);
        Intent i = new Intent(this, DownloadService.class);
        i.putExtra("flag","start");
        startService(i);
    }

    /**
     * 广播接收Service消息
     */
    class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String value = intent.getStringExtra("extra_data");
            LogUtil.LogD("downloadService","value via broadcast: " + value);
        }
    }

}
