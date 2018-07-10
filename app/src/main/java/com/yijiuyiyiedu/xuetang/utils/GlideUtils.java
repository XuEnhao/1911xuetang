package com.yijiuyiyiedu.xuetang.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yijiuyiyiedu.xuetang.module.ui.custom.GlideRoundTransform;

/**
 * Created by zhangyy on 2017/4/25.
 */

public class GlideUtils {

    /**
     * 因为都是七牛云上的图片 所以返回的路径就是最终路径
     * @param context
     * @param path
     * @param imageView
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void loadImage(Context context, String path, ImageView imageView) {
        if (TextUtils.isEmpty(path)) {
            imageView.setImageResource(R.mipmap.placeholder);
        } else {
            if (!((Activity)context).isDestroyed()){
                RequestOptions options = new RequestOptions();
                options.placeholder(R.mipmap.placeholder);
                options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                Glide.with(context).load(path).apply(options).into(imageView);
            }
        }

    }
    public static void loadImage(Context context, int path, ImageView imageView) {
        if (path==0) {
            imageView.setImageResource(R.mipmap.placeholder);
        } else {
        RequestOptions options = new RequestOptions();
        options.placeholder(R.mipmap.placeholder);
        options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(context).load(path).apply(options).into(imageView);
        }

    }

    public static void loadRoundImage(Context context, String path, ImageView imageView) {
        if (TextUtils.isEmpty(path)) {
            imageView.setImageResource(R.mipmap.placeholder);
        } else {
            RequestOptions options = new RequestOptions();
            options.placeholder(R.mipmap.placeholder);
            options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
            options.transform(new GlideRoundTransform(context,4));
            Glide.with(context).load(path).apply(options).into(imageView);
        }

    }

    public static void loadRoundImage(Context context, int path, ImageView imageView) {
        RequestOptions options = new RequestOptions();
        options.placeholder(R.mipmap.placeholder);
        options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        options.transform(new GlideRoundTransform(context,4));
        Glide.with(context).load(path).apply(options).into(imageView);


    }

    public static void loadImage(Context context, String path, ImageView imageView, int width, int height) {
        if (TextUtils.isEmpty(path)) {
            imageView.setImageResource(R.mipmap.placeholder);
        } else {
            RequestOptions options = new RequestOptions();
            options.placeholder(R.mipmap.placeholder);
            options.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
            Glide.with(context).load(path).apply(options).into(imageView);
        }

    }



//    //    加载圆形图片
//    public static void loadCircleImage(Context context, String path, ImageView imageView, String rgb, int width, int height) {
//        if (TextUtils.isEmpty(path)) {
//            imageView.setImageResource(R.drawable.ic_default);
//        } else {
//            if (context!=null){
//                Glide.with(context).load(path + "@" + height + "h_" + width + "w")
//                        .transform(new GlideCircleTransform(context))
//                        .diskCacheStrategy(DiskCacheStrategy.NONE)
////                    .placeholder(R.drawable.ic_default)
//                        .error(R.drawable.ic_default)
//                        .into(imageView);
//            }
//        }
//    }
//    //    加载圆形图片
//    public static void loadCircleImage(Context context,int resId, ImageView imageView, String rgb, int width, int height) {
//        if (resId!=0) {
//            imageView.setImageResource(R.drawable.ic_default);
//        } else {
//            if (context!=null){
//                Glide.with(context).load(resId + "@" + height + "h_" + width + "w")
//                        .transform(new GlideCircleTransform(context))
//                        .diskCacheStrategy(DiskCacheStrategy.NONE)
////                    .placeholder(R.drawable.ic_default)
//                        .error(R.drawable.ic_default)
//                        .into(imageView);
//            }
//        }
//    }
//
    /**
     * 加载带边圆形图片
     * @param context
     * @param path
     * @param imageView
     * @param rgb 图片色值
     * @param width 图片的宽
     * @param height 图片的高
     * @param borderWidth 边的宽度
     * @param borderColor 边的颜色
     */
    public static void loadBorderCircleImg(Context context, String path, ImageView imageView, String rgb, int width, int height,int borderWidth, String borderColor){
//        if (TextUtils.isEmpty(path)) {
//            imageView.setImageResource(R.mipmap.placeholder);
//        } else {
//            if (context!=null){
//                RequestOptions options = new RequestOptions();
//                options.placeholder(R.mipmap.placeholder);
//                options.circleCrop();
//                Glide.with(context).load(path + "@" + height + "h_" + width + "w")
//                        .apply(options)
//                        .into(imageView);
//            }
//        }
    }
//
//    //    加载圆角图片
//    public static void loadRoundImage(Context context, String path, ImageView imageView, String rgb, int width, int height,int radius) {
//        if (TextUtils.isEmpty(path)){
//            imageView.setImageResource(R.drawable.ic_default);
//        }else {
//            Glide.with(context).load(path+ "@" + height + "h_" + width + "w")
//                    .transform(new CenterCrop(context),new GlideRoundTransform(context,radius))
//                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                    .error(R.drawable.ic_default)
//                    .into(imageView);
//        }
//    }


    /**
     * 加载圆角带边图片
     * @param context
     * @param path
     * @param imageView
     * @param width
     * @param height
     * @param radius
     */
//    public static void loadRoundImage(Context context, String path, ImageView imageView,  int width, int height,int radius,int color,int line_wid) {
//        if (TextUtils.isEmpty(path)){
//            imageView.setImageResource(R.drawable.ic_default);
//        }else {
//            Glide.with(context).load(path+ "@" + height + "h_" + width + "w")
//                    .transform(new GlideRoundWithLineTransform(context,radius,color,line_wid))
//                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                    .error(R.drawable.ic_default)
//                    .into(imageView);
//        }
//    }

}
