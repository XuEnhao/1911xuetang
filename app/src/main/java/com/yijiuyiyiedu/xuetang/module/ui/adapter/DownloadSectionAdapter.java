package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tencent.rtmp.downloader.ITXVodDownloadListener;
import com.tencent.rtmp.downloader.TXVodDownloadManager;
import com.tencent.rtmp.downloader.TXVodDownloadMediaInfo;
import com.yijiuyiyiedu.xuetang.DownloadService;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.entity.ClassContentEntity;
import com.yijiuyiyiedu.xuetang.module.entity.VipListEntity;
import com.yijiuyiyiedu.xuetang.module.ui.custom.DownloadProgressView;
import com.yijiuyiyiedu.xuetang.utils.DataCleanManager;
import com.yijiuyiyiedu.xuetang.utils.LogUtil;

import java.util.List;

/**
 * Created by xuenhao on 2018/4/19.
 * 下载详情
 */

public class DownloadSectionAdapter extends BaseQuickAdapter<ClassContentEntity.DataBean.CurriculumCatalogListBean.ChildListBean,BaseViewHolder> {
    TXVodDownloadManager downloader;
    private DownloadProgressView progressView;
    private DownloadService service;

    public DownloadSectionAdapter(int layoutResId, @Nullable List<ClassContentEntity.DataBean.CurriculumCatalogListBean.ChildListBean> data) {
        super(layoutResId, data);
        downloader = TXVodDownloadManager.getInstance();
    }

    @Override
    protected void convert(BaseViewHolder helper, ClassContentEntity.DataBean.CurriculumCatalogListBean.ChildListBean item) {
        helper.setText(R.id.item_download_details_number,item.getId()+"");
        helper.setText(R.id.item_download_details_title,item.getTitle());
        progressView = helper.getView(R.id.item_download_details_progress);
        downloader.setListener(listener);
//        Intent it =new Intent(mContext,DownloadService.class);
//        it.putExtra("url",item.getVideo_address());
//        it.putExtra("flag","start");
//        mContext.startService(it);

        String url = "http://1256678727.vod2.myqcloud.com/19d7e632vodgzp1256678727/18d723fe7447398156870567855/ahMfXSxJx4kA.mp4?t=5b39d81f&sign=a0be3c831608253a2eb6ee1ce2c2d691";
        String url1 = "http://1256678727.vod2.myqcloud.com/19d7e632vodgzp1256678727/18d723fe7447398156870567855/ahMfXSxJx4kA.mp4?t=5b39d81f&sign=a0be3c831608253a2eb6ee1ce2c2d691";

//        downloader.startDownloadUrl(url);
        String formatSize = DataCleanManager.getFormatSize(Double.parseDouble(item.getSize()));
        helper.setText(R.id.item_download_details_size,item.getSize());
    }

    private ITXVodDownloadListener listener = new ITXVodDownloadListener() {
        @Override
        public void onDownloadStart(TXVodDownloadMediaInfo txVodDownloadMediaInfo) {
            LogUtil.LogD("TXVodDownloadManager", "onDownloadStart: 开始下载");
        }

        @Override
        public void onDownloadProgress(TXVodDownloadMediaInfo txVodDownloadMediaInfo) {
            LogUtil.LogD("TXVodDownloadManager", "onDownloadProgress:"+txVodDownloadMediaInfo.getProgress());
            progressView.setDegree((int) txVodDownloadMediaInfo.getProgress());
        }

        @Override
        public void onDownloadStop(TXVodDownloadMediaInfo txVodDownloadMediaInfo) {
            LogUtil.LogD("TXVodDownloadManager", "onDownloadStop: 停止下载");
        }

        @Override
        public void onDownloadFinish(TXVodDownloadMediaInfo txVodDownloadMediaInfo) {
            txVodDownloadMediaInfo.getPlayPath();
            LogUtil.LogD("TXVodDownloadManager", "onDownloadFinish: 下载完成");
        }

        @Override
        public void onDownloadError(TXVodDownloadMediaInfo txVodDownloadMediaInfo, int i, String s) {
            LogUtil.LogD("TXVodDownloadManager", "onDownloadError: 下载错误"+txVodDownloadMediaInfo.getSize());
        }
    };
}
