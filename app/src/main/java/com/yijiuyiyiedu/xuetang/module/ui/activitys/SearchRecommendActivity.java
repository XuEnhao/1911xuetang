package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.UriConstant;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.module.entity.CategoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.SearchCurriCulumEntity;
import com.yijiuyiyiedu.xuetang.module.entity.SearchRecommendEntity;
import com.yijiuyiyiedu.xuetang.module.persenter.SearchCurriculumPresenter;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.NewSearchCurriculumAdapter;
import com.yijiuyiyiedu.xuetang.module.view.SearchCurriculumView;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.UtilToast;
import com.yijiuyiyiedu.xuetang.utils.titlebar.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xuenhao on 2018/3/30.
 * 搜索推荐页面
 */

public class SearchRecommendActivity extends BaseActivity implements SearchCurriculumView {

    @BindView(R.id.search_curriculum_searchView)
    EditText searchEdit;//搜索框
    @BindView(R.id.search_curriculum_cancel)
    TextView cancel;//取消
    @BindView(R.id.search_recommend_recycler)
    RecyclerView recycler;//条目
    //    @BindView(R.id.search_recommend_title)
//    TextView title;
    @BindView(R.id.search_recommend_refreshLayout)
    TwinklingRefreshLayout refreshLayout;
    private SearchCurriculumPresenter mPresenter;
    private List<SearchRecommendEntity> list;
    private NewSearchCurriculumAdapter adapter;
    private String searchWord;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_recommend);
        ButterKnife.bind(this);
        isSupportSwipeBack();
        mContext = this;
        StatusBarUtil.setStatusBar(this,R.id.search_recommend_statusBar);
        initView();
    }

    private void initView() {
        mPresenter = new SearchCurriculumPresenter(this);
        list = new ArrayList<>();
        adapter = new NewSearchCurriculumAdapter(R.layout.item_category, list);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
        adapter.addFooterView(View.inflate(mContext, R.layout.fragment_category_foot, null));
        final int is_recommend = getIntent().getIntExtra("is_recommend", 0);
//        if (is_recommend == 1) {
//            title.setText("名师推荐");
//        } else {
//            title.setText("最新课程");
//        }
        mPresenter.getRemodListData("", "", "", "", String.valueOf(is_recommend), "");
        //软键盘回车监听
        searchEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //当actionId == XX_SEND 或者 XX_DONE时都触发
                //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
                //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    mPresenter.getSearchCurriculumData("", "", searchEdit.getText().toString(), "", UriConstant.userId);
//                    //处理事件
//                    Intent it = new Intent(SearchActivity.this, SearchCurriculumActivity.class);
//                    it.putExtra("searchWord", searchView.getText().toString() + "");
//                    startActivity(it);
//                    finish();
                    return true;
                }
                return false;
            }
        });
//        //软键盘回车监听
//        searchEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                //当actionId == XX_SEND 或者 XX_DONE时都触发
//                //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
//                //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
//                if (actionId == EditorInfo.IME_ACTION_SEND
//                        || actionId == EditorInfo.IME_ACTION_DONE
//                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
//                    searchWord = searchEdit.getText().toString();
//                    mPresenter.getSearchCurriculumData("", "", searchWord, "", UriConstant.userId);
//                }
//                return true;
//            }
//        });
        searchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    cancel.setText("取消");
                } else {
                    cancel.setText("清除");
                }
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cancel.getText().toString().equals("清除")){
                    searchEdit.setText("");
                }else{
                    JumpUtil.overiderAnimsition((Activity) mContext);
                }
            }
        });

        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setOverScrollBottomShow(false);//越界回弹不显示加载更多布局
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                if (mPresenter!=null){
                    mPresenter.getRemodListData("", "", "", "", String.valueOf(is_recommend), "");
                }

            }
        });


    }

//    private void setStatusBar() {
//        // 设置透明导航栏
//        StatusBarUtil.setTranslucentForImageView(this, 50, null);
//        int statusBarHeight = StatusBarUtil.getStatusBarHeight(this);
//        View viewById = findViewById(R.id.search_recommend_statusBar);
//        ViewGroup.LayoutParams layoutParams = viewById.getLayoutParams();
//        layoutParams.height = statusBarHeight;
//    }



    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showData(final SearchCurriCulumEntity data) {
        if (refreshLayout!=null){
            refreshLayout.finishRefreshing();
        }
        if (data.getStatus()==0) {
            List<SearchRecommendEntity> recommendList = new ArrayList<>();
            List<SearchCurriCulumEntity.DataBean.SearchCurriculumListBean> curriculumList = data.getData().getSearchCurriculumList();
            for (int i = 0; i < curriculumList.size(); i++) {
                SearchRecommendEntity bean = new SearchRecommendEntity();
                bean.setId(curriculumList.get(i).getId());
                bean.setTitle(curriculumList.get(i).getTitle());
//                bean.setCategory_id(curriculumList.get(i).getCategory_id());
//                bean.setIntroduction(curriculumList.get(i).getIntroduction());
//                bean.setStatus_serial(curriculumList.get(i).getStatus_serial());
                bean.setStudy_number(curriculumList.get(i).getStudy_number());
//                bean.setClass_number(curriculumList.get(i).getClass_number());
//                bean.setOriginal_price(curriculumList.get(i).getOriginal_price());
                bean.setPresent_price(curriculumList.get(i).getPresent_price());
//                bean.setScore(curriculumList.get(i).getScore());
//                bean.setLecturer(curriculumList.get(i).getLecturer());
//                bean.setContent(curriculumList.get(i).getContent());
                bean.setPicture(curriculumList.get(i).getPicture());
//                bean.setIs_recommend(curriculumList.get(i).getIs_recommend());
//                bean.setStatus(curriculumList.get(i).getStatus());
//                bean.setCreate_time(curriculumList.get(i).getCreate_time());
                recommendList.add(bean);
            }
            list.clear();
            list.addAll(recommendList);
            adapter.notifyDataSetChanged();
        } else {
            UtilToast.showToast(mContext, data.getMsg());
        }
    }

    @Override
    public void showRemodData(final CategoryEntity data) {
        if (refreshLayout!=null){
            refreshLayout.finishRefreshing();
        }
//        if (data.getStatus().equals("1")) {
//            List<SearchRecommendEntity> recommendList = new ArrayList<>();
//            List<CategoryEntity.DataBean.CurriculumListBean> curriculumList = data.getData().getCurriculumList();
//            for (int i = 0; i < curriculumList.size(); i++) {
//                SearchRecommendEntity bean = new SearchRecommendEntity();
//                bean.setId(curriculumList.get(i).getId());
//                bean.setTitle(curriculumList.get(i).getTitle());
//                bean.setCategory_id(curriculumList.get(i).getCategory_id());
//                bean.setLecturer(curriculumList.get(i).getLecturer());
////                bean.setIntroduction(curriculumList.get(i).getIntroduction());
//                bean.setStatus_serial(curriculumList.get(i).getStatus_serial());
//                bean.setStudy_number(curriculumList.get(i).getStudy_number());
//                bean.setClass_number(curriculumList.get(i).getStudy_number());
//                bean.setOriginal_price(curriculumList.get(i).getOriginal_price());
//                bean.setPresent_price(curriculumList.get(i).getPresent_price());
//                bean.setScore(curriculumList.get(i).getScore());
//                bean.setContent(curriculumList.get(i).getContent());
//                bean.setPicture(curriculumList.get(i).getPicture());
//                bean.setIs_recommend(curriculumList.get(i).getIs_recommend());
//                bean.setStatus(curriculumList.get(i).getStatus());
//                bean.setCreate_time(curriculumList.get(i).getCreate_time());
//                recommendList.add(bean);
//            }
//            list.clear();
//            list.addAll(recommendList);
//            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//                @Override
//                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                    Intent it = new Intent(mContext, CourseDetailsActivity.class);
//                    it.putExtra("courseId", data.getData().getCurriculumList().get(position).getId());
//                    startActivity(it);
//                }
//            });
//            adapter.notifyDataSetChanged();
//        }

    }

    @Override
    public void showFailureMessage(String msg) {

    }

    @Override
    public void showErrorMessage() {

    }

//    @OnClick(R.id.applyCompanyNumber_back)
//    public void onViewClicked() {
//        JumpUtil.overiderAnimsition((Activity) mContext);
//    }
}
