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
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.UriConstant;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.module.entity.CategoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.SearchCurriCulumEntity;
import com.yijiuyiyiedu.xuetang.module.persenter.SearchCurriculumPresenter;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.SearchCurriculumAdapter;
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
 * Created by xuenhao on 2018/3/23.
 * 搜索展示页面
 */

public class SearchCurriculumActivity extends BaseActivity implements SearchCurriculumView {

    @BindView(R.id.search_searchView)
    EditText searchEdit;
    @BindView(R.id.search_recycler)
    RecyclerView recycler;
    SearchCurriculumPresenter mPresenter;
    @BindView(R.id.search_cancel)
    TextView cancel;
    private String searchWord;
    private List<SearchCurriCulumEntity.DataBean.SearchCurriculumListBean> list;
    private SearchCurriculumAdapter adapter;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_curriculum);
        ButterKnife.bind(this);
        mContext = this;
        StatusBarUtil.setStatusBar(this,R.id.search_curriculum_statusBar);
        getIntentData();
        initView();
    }


    private void getIntentData() {
        Intent it = getIntent();
        searchWord = it.getStringExtra("searchWord");
    }

    private void initView() {
        mPresenter = new SearchCurriculumPresenter(this);
        list = new ArrayList<>();
        adapter = new SearchCurriculumAdapter(R.layout.item_category, list);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
        mPresenter.getSearchCurriculumData("", "", searchWord, "", UriConstant.userId);
        searchEdit.setText(searchWord + "");//接受传过来的搜索关键字 并设置
        searchEdit.setSelection(searchWord.length());
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
                    mPresenter.getSearchCurriculumData("", "", searchWord, "", UriConstant.userId);
                }
                return true;
            }
        });

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

    }

//    private void setStatusBar() {
//        // 设置透明导航栏
//        StatusBarUtil.setTranslucentForImageView(this, 50, null);
//        int statusBarHeight = StatusBarUtil.getStatusBarHeight(this);
//        View viewById = findViewById(R.id.search_curriculum_statusBar);
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
        if (data.getStatus()==0) {
            UtilToast.showToast(mContext, data.getMsg());
            list.clear();
            list.addAll(data.getData().getSearchCurriculumList());
            adapter.notifyDataSetChanged();
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent it = new Intent(mContext, CourseDetailsActivity.class);
                    it.putExtra("courseId", data.getData().getSearchCurriculumList().get(position).getId());
                    startActivity(it);
                }
            });
        }
    }

    @Override
    public void showRemodData(CategoryEntity data) {

    }

    @Override
    public void showFailureMessage(String msg) {

    }

    @Override
    public void showErrorMessage() {

    }

    @OnClick(R.id.search_cancel)
    public void onViewClicked() {
        JumpUtil.overiderAnimsition((Activity) mContext);
    }
}
