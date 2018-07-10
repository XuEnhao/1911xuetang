package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.entity.CourseEntity;
import com.yijiuyiyiedu.xuetang.utils.GlideUtils;

import java.util.List;

public class ImagePagerAdapter extends PagerAdapter {

	private List<CourseEntity.DataBean.BannerListBean> ids;
	private Context context;
	private LayoutInflater inflater;
	private OnPagerItemClickListener onClicklistener;

	public ImagePagerAdapter(Context context, List<CourseEntity.DataBean.BannerListBean> url){
		this.ids=url;
		this.context=context;
		inflater = LayoutInflater.from(context);
		
	}
	public void setOnClickListener(OnPagerItemClickListener onClickListener){
		this.onClicklistener = onClickListener;
	}
	
	@Override
	public int getCount() {
		return Integer.MAX_VALUE;
	}
	
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;  
    }  
  
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public interface OnPagerItemClickListener{
		void onItemClick(int position);
	}
    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
    	
		ImageView imgview = new ImageView(context);
		imgview.setScaleType(ImageView.ScaleType.CENTER_CROP);
		if (ids.size()!=0){
			GlideUtils.loadRoundImage(context,ids.get(position%ids.size()).getPicture(),imgview);
		}
		imgview.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (onClicklistener!=null){
					onClicklistener.onItemClick(position%ids.size());
				}
			}
		});
		container.addView(imgview);
    	return imgview;
    }  
	
}
