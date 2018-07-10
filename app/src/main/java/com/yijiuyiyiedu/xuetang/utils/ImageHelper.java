package com.yijiuyiyiedu.xuetang.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.DrawableRes;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yijiuyiyiedu.xuetang.module.ui.custom.MyClearEditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * <p>图片加载帮助类</p>
 */
public class ImageHelper {
    private static String TAG = "ImageHelper";

    /**
     * Glide 加载图片
     * Priority 展示团片优先级
     *
     * @param mContext
     * @param imageView
     */
    public static void loadImageBannerGlide(Context mContext, String url, ImageView imageView) {
//        Glide.with(mContext).load(url).priority(Priority.HIGH).into(imageView);
    }

    /**
     * Glide 清除内存
     *
     * @param mContext
     */
    public static void clearMoneryCache(Context mContext) {
        if (mContext != null) {
            LogUtil.LogE(TAG, "清理图片缓存");
            Glide.get(mContext).clearMemory();
            System.gc();
        }
    }

    /**
     * 利用BitmapShader绘制圆角图片
     *
     * @param bitmap    待处理图片
     * @param outWidth  结果图片宽度，一般为控件的宽度
     * @param outHeight 结果图片高度，一般为控件的高度
     * @param radius    圆角半径大小
     * @return 结果图片
     */
    public static Bitmap roundBitmapByShader(Bitmap bitmap, int outWidth, int outHeight, int radius) {
        if (bitmap == null) {
            throw new NullPointerException("Bitmap can't be null");
        }
        // 初始化缩放比
        float widthScale = outWidth * 1.0f / bitmap.getWidth();
        float heightScale = outHeight * 1.0f / bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.setScale(widthScale, heightScale);

        // 初始化绘制纹理图
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        // 根据控件大小对纹理图进行拉伸缩放处理
        bitmapShader.setLocalMatrix(matrix);

        // 初始化目标bitmap
        Bitmap targetBitmap = Bitmap.createBitmap(outWidth, outHeight, Bitmap.Config.ARGB_8888);

        // 初始化目标画布
        Canvas targetCanvas = new Canvas(targetBitmap);

        // 初始化画笔
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(bitmapShader);

        // 利用画笔将纹理图绘制到画布上面
        targetCanvas.drawRoundRect(new RectF(0, 0, outWidth, outWidth), radius, radius, paint);

        return targetBitmap;
    }

    public static String getResourcesUri(Context mContext, @DrawableRes int id) {
        Resources resources = mContext.getResources();
        String uriPath = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                resources.getResourcePackageName(id) + "/" +
                resources.getResourceTypeName(id) + "/" +
                resources.getResourceEntryName(id);
        return uriPath;
    }

    /**
     * @param bmp     获取的bitmap数据
     * @param picName 自定义的图片名
     */
    public static boolean saveBmp2Gallery(Bitmap bmp, String picName, Context mContext) {

        String fileName = null;
        //系统相册目录
        String galleryPath = Environment.getExternalStorageDirectory()
                + File.separator + Environment.DIRECTORY_DCIM
                + File.separator + "Camera" + File.separator;


        // 声明文件对象
        File file = null;
        // 声明输出流
        FileOutputStream outStream = null;

        try {
            // 如果有目标文件，直接获得文件对象，否则创建一个以filename为名称的文件
            file = new File(galleryPath, picName + ".jpg");

            // 获得文件相对路径
            fileName = file.toString();
            // 获得输出流，如果文件中有内容，追加内容
            outStream = new FileOutputStream(fileName);
            if (null != outStream) {
                bmp.compress(Bitmap.CompressFormat.JPEG, 90, outStream);
            }

        } catch (Exception e) {
            e.getStackTrace();
            Log.d("Exception", "Exception: "+e.getMessage());
            return  false;
        } finally {
            try {
                if (outStream != null) {
                    outStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
//　　　　　　　　}
//        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//        Uri uri = Uri.fromFile(file);
//        intent.setData(uri);
//        mContext.sendBroadcast(intent);
        }
        MediaStore.Images.Media.insertImage(mContext.getContentResolver(),
                bmp, fileName, null);
        return true;
    }

}
