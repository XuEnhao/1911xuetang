package com.yijiuyiyiedu.xuetang.module.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.UriConstant;
import com.yijiuyiyiedu.xuetang.module.base.BaseFragment;
import com.yijiuyiyiedu.xuetang.module.entity.NewCategoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.SearchEntity;
import com.yijiuyiyiedu.xuetang.module.persenter.NewCategoryPresenter;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.CategoryActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.SearchActivity;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.CategoryAdapter;
import com.yijiuyiyiedu.xuetang.module.view.NewCategoryView;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xuenhao on 2018/3/13.
 * 分类页面
 */

public class NewCategoryFragment extends BaseFragment implements NewCategoryView {

    Unbinder unbinder;
    @BindView(R.id.fragment_newCategory_recyclerView)
    RecyclerView recyclerView;//一级分类
    @BindView(R.id.fragment_newCategory_flowLayout)
    TagFlowLayout flowLayout;//二级分类
    @BindView(R.id.fragment_newCategory_search)
    ImageView searchLinear;//二级分类
    private String categoryTitle;
    protected NewCategoryPresenter mPresenter;
    private CategoryAdapter mAdapter;//一级adapter
    private boolean isLoad = false;//是否加载过数据
    private ArrayList<NewCategoryEntity.DataBean.CategoryListBean> list;
    private ArrayList<NewCategoryEntity.DataBean.CategoryListBean.ChildListBean> childList;


    @Override
    protected int getLayout() {
        return R.layout.fragment_new_category;
    }


    /**
     * 初始化数据
     *
     * @param inflater
     */
    @Override
    protected void initView(LayoutInflater inflater) {
        super.initView(inflater);


    }

    @Override
    protected void initEvent() {
        super.initEvent();
    }

    /**
     * 懒加载
     */
    @Override
    protected void lazyFetchData() {
        super.lazyFetchData();
        if (!isLoad) {
            isLoad = true;
            list = new ArrayList<>();
            childList = new ArrayList<>();
            mPresenter = new NewCategoryPresenter(this);
            mPresenter.getNewCategoryList();
            mAdapter = new CategoryAdapter(R.layout.item_category_title,list);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(mAdapter);
            flowLayout.setAdapter(new TagAdapter<NewCategoryEntity.DataBean.CategoryListBean.ChildListBean>(childList) {
                @Override
                public View getView(FlowLayout parent, int position, NewCategoryEntity.DataBean.CategoryListBean.ChildListBean bean) {
                    View view = LayoutInflater.from(mContext).inflate(R.layout.item_evaluate_check, parent, false);
                    TextView textView = view.findViewById(R.id.item_evaluate_text);
                    textView.setTextSize(14);
                    textView.setText(bean.getCategory_name());
                    return view;
                }
            });


            flowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, FlowLayout parent) {
                    NewCategoryEntity.DataBean.CategoryListBean.ChildListBean item = (NewCategoryEntity.DataBean.CategoryListBean.ChildListBean)
                            flowLayout.getAdapter().getItem(position);
                    Gson gson = new Gson();
                    Intent it = new Intent(mContext, CategoryActivity.class);
                    it.putExtra("category_id",item.getParent_id());
                    it.putExtra("category_idb",item.getId());
                    it.putExtra("categoryTitle",categoryTitle);
                    it.putExtra("childList",gson.toJson(childList));
                    it.putExtra("position",position);
                    startActivity(it);
                    return true;
                }
            });
            mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    List<NewCategoryEntity.DataBean.CategoryListBean> data = adapter.getData();
                    for (int i = 0;i<data.size();i++){
                        data.get(i).setCheck(false);
                    }
                    data.get(position).setCheck(true);
                    categoryTitle = data.get(position).getCategory_name();
                    childList.clear();
                    childList.addAll(data.get(position).getChildList());
                    flowLayout.getAdapter().notifyDataChanged();
                    mAdapter.notifyDataSetChanged();
                }
            });
            searchLinear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JumpUtil.jump(mContext, SearchActivity.class);
                }
            });
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
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showData(NewCategoryEntity data) {
        if (data.getStatus()==0){
            if (data.getData().getCategoryList()!=null){
                data.getData().getCategoryList().get(0).setCheck(true);//默认第一个选中
                categoryTitle = data.getData().getCategoryList().get(0).getCategory_name();
                childList.addAll(data.getData().getCategoryList().get(0).getChildList());
            }
            mAdapter.setNewData(data.getData().getCategoryList());
            flowLayout.getAdapter().notifyDataChanged();
        }
    }

    @Override
    public void showFailureMessage(String msg) {

    }

    @Override
    public void showErrorMessage() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
