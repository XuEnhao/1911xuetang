package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.module.entity.CategoryBus;
import com.yijiuyiyiedu.xuetang.module.entity.CategoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.CategorySortBus;
import com.yijiuyiyiedu.xuetang.module.entity.CategorySortEntity;
import com.yijiuyiyiedu.xuetang.module.entity.NewCategoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.NewChildCategoryEntity;
import com.yijiuyiyiedu.xuetang.module.persenter.CategoryPresenter;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.CategorySortAdapter;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.channerl.ContentListPagerAdapter;
import com.yijiuyiyiedu.xuetang.module.ui.fragments.CategoryFragment;
import com.yijiuyiyiedu.xuetang.module.view.CategoryView;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.LogUtil;
import com.yijiuyiyiedu.xuetang.utils.UtilDpOrPx;
import com.yijiuyiyiedu.xuetang.utils.UtilsShowPopup;
import com.yijiuyiyiedu.xuetang.utils.WindowUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xuenhao on 2018/3/20.
 */

public class CategoryActivity extends BaseActivity implements CategoryView {
    @BindView(R.id.collect_back)
    ImageView back;
    @BindView(R.id.category_tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.category_viewPager)
    ViewPager viewPager;
    @BindView(R.id.category_search)
    ImageView searchImg;
    @BindView(R.id.course_category_hide)
    Switch switchImg;
    @BindView(R.id.category_sort)
    LinearLayout sortLinear;
    Context mContext;
    CategoryPresenter mPresenter;
    @BindView(R.id.category_title)
    TextView categoryTitle;
    @BindView(R.id.category_searchText)
    TextView sortText;
    @BindView(R.id.category_searchImg)
    ImageView sortImg;
    private ArrayList<NewCategoryEntity.DataBean.CategoryListBean.ChildListBean> categoryList;//分类列表
    private List<String> titles = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    public String category_id = "";
    private String title;
    private String category_idb = "";
    private int isHide = 0;
    private RecyclerView recyclerView;
    private CategorySortAdapter adapter;
    private String childTiltes;
    ArrayList<CategorySortEntity> list = new ArrayList<>();
    private View view;
    private Gson gson;
    private int position;
    private ViewHolder holder;
    private LinearLayout popLinear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_category);
        unbinder = ButterKnife.bind(this);
        mContext = this;
        Intent it = getIntent();
        gson = new Gson();
        category_id = it.getStringExtra("category_id");//一级分类id
        title = it.getStringExtra("categoryTitle");//标题
        category_idb = it.getStringExtra("category_idb");//二级分类id
        childTiltes = it.getStringExtra("childList");//二级分类id
        position = it.getIntExtra("position",0);//位置
        initChildList();
        categoryTitle.setText(title);
        mPresenter = new CategoryPresenter(this);
//        if (mPresenter != null) {//获取二级分类名字 用于生成碎片
//            mPresenter.getData("", "", "", "", "", "", "", category_id, "", 0);
//
//        }
        searchImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.jump(mContext, SearchActivity.class);
            }
        });
        switchImg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switchImg.isChecked()) {
                    isHide = 1;
                    EventBus.getDefault().post(new CategoryBus(isHide));
                } else {
                    isHide = 0;
                    EventBus.getDefault().post(new CategoryBus(isHide));
                }
            }
        });
        initpopView();
        sortLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPop();
                sortText.setText("筛选");
                sortText.setTextColor(Color.parseColor("#6317A5"));
                sortImg.setImageResource(R.mipmap.category_sort);

            }


        });
    }

    private void initChildList() {
//        int position = 0;
        titles.add("全部");
        categoryList = gson.fromJson(childTiltes,new TypeToken<ArrayList<NewCategoryEntity.DataBean.CategoryListBean.ChildListBean>>(){}.getType());
        fragments.add(CategoryFragment.getInstance(category_id));//全部的时候 传的是一级id
        for (int i = 0; i < categoryList.size(); i++) {
//            if (category_idb.equals(categoryList.get(i).getId())) {
//                position = i;
//            }
            titles.add(categoryList.get(i).getCategory_name());
            fragments.add(CategoryFragment.getInstance(categoryList.get(i).getId()));
        }
        viewPager.setAdapter(new ContentListPagerAdapter(getSupportFragmentManager(), fragments, titles));
        viewPager.setOffscreenPageLimit(4);
        tabLayout.setupWithViewPager(viewPager);
        setTabView();//设置选中字体变大 加粗

        position+=1;
        viewPager.setCurrentItem(position);//1是全部
        LogUtil.LogD("tag","tabLayout.getSelectedTabPosition():"+position);
    }
    /**
     * 设置Tab的样式
     */
    private void setTabView() {
        holder = null;
        for (int i = 0; i < titles.size(); i++) {
            //依次获取标签
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            //为每个标签设置布局
            tab.setCustomView(R.layout.tab_item);
            holder = new ViewHolder(tab.getCustomView());
            //为标签填充数据
            holder.tvTabName.setText(titles.get(i));
            //默认选择第一项
            if (i == 0){
                holder.tvTabName.setSelected(true);
                holder.tvTabName.setTextSize(19);
                holder.tvTabName.setTextColor(Color.parseColor("#6317A5"));
            }
        }

        //tab选中的监听事件
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                holder = new ViewHolder(tab.getCustomView());
                holder.tvTabName.setSelected(true);
                //选中后字体变大
                holder.tvTabName.setTextSize(19);
                TextPaint tp = holder.tvTabName.getPaint();
                tp.setFakeBoldText(true);
                //让Viewpager跟随TabLayout的标签切换
                viewPager.setCurrentItem(tab.getPosition());
                holder.tvTabName.setTextColor(Color.parseColor("#6317A5"));

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                holder = new ViewHolder(tab.getCustomView());
                holder.tvTabName.setSelected(false);
                TextPaint tp = holder.tvTabName.getPaint();
                tp.setFakeBoldText(false);
                //恢复为默认字体大小
                holder.tvTabName.setTextSize(16);
                holder.tvTabName.setTextColor(Color.parseColor("#787993"));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initpopView() {
        view = View.inflate(mContext, R.layout.pop_category_sort, null);
        recyclerView = view.findViewById(R.id.category_sort_recyclerView);
        popLinear = view.findViewById(R.id.category_sort_linear);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        list.add(new CategorySortEntity("全部", true));
        list.add(new CategorySortEntity("最新", false));
        list.add(new CategorySortEntity("最热", false));
//        排序 1 时间倒序（最新课程） 2 学习人数排序（热门课程）3 评分排序
        adapter = new CategorySortAdapter(R.layout.item_category_sort, list);
        recyclerView.setAdapter(adapter);
    }

    private void showPop() {
        final UtilsShowPopup popup = new UtilsShowPopup();

        popup.showViewBottomPopup(mContext, ((Activity) mContext).getWindow(), view, sortLinear, WindowUtils.getScreenHeight(mContext), 1f);

        final PopupWindow popupWindow = popup.getPopupWindow();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CategorySortEntity item = (CategorySortEntity) adapter.getItem(position);
                ArrayList<CategorySortEntity> data = (ArrayList<CategorySortEntity>) adapter.getData();
                for (int i = 0; i < data.size(); i++) {
                    data.get(i).setCheck(false);
                }
                item.setCheck(true);
                adapter.notifyDataSetChanged();
                popupWindow.dismiss();
                EventBus.getDefault().post(new CategorySortBus(String.valueOf(position + 1)));

            }
        });
        popLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                sortText.setText("排序");
                sortText.setTextColor(Color.parseColor("#25265E"));
                sortImg.setImageResource(R.mipmap.category_unsort);
            }
        });
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showData(CategoryEntity data) {
    }

    @Override
    public void showChildData(NewChildCategoryEntity data) {

    }

    @Override
    public void showFailureMessage(String msg) {

    }

    @Override
    public void showErrorMessage() {

    }

    class ViewHolder{
        TextView tvTabName;

        public ViewHolder(View tabView) {
            tvTabName = (TextView) tabView.findViewById(R.id.tv_tab_name);
        }
    }
    @OnClick(R.id.collect_back)
    public void onViewClicked() {
        JumpUtil.overiderAnimsition((Activity) mContext);
    }
}
