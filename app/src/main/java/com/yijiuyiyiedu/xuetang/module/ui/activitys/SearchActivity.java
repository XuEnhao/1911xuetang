package com.yijiuyiyiedu.xuetang.module.ui.activitys;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.UriConstant;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.module.entity.ClearHistoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.SearchCurriCulumEntity;
import com.yijiuyiyiedu.xuetang.module.entity.SearchEntity;
import com.yijiuyiyiedu.xuetang.module.entity.SearchNoneEntity;
import com.yijiuyiyiedu.xuetang.module.persenter.SearchPresenter;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.SearchCurriculumAdapter;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.SearchNoneAdapter;
import com.yijiuyiyiedu.xuetang.module.view.SearchView;
import com.yijiuyiyiedu.xuetang.utils.ProgressDialogUtils;
import com.yijiuyiyiedu.xuetang.utils.ToastUtils;
import com.yijiuyiyiedu.xuetang.utils.titlebar.StatusBarUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;

/**
 * xeh
 * 搜索框
 */
public class SearchActivity extends BaseActivity implements SearchView {

    @BindView(R.id.search_curriculum_searchView)
    EditText searchView;
    @BindView(R.id.search_curriculum_cancel)
    TextView cancel;//取消
    @BindView(R.id.search_curriculum_clearHistory)
    TextView clearHistory;//清除历史记录
    @BindView(R.id.search_curriculum_hisRecycler)
    TagFlowLayout hisFlowLayout;//历史记录
    @BindView(R.id.search_curriculum_hotRecycler)
    TagFlowLayout hotFlowLayout;//热门搜索
    SearchPresenter mPresenter;
    @BindView(R.id.search_hothisLinear)
    LinearLayout searchLinear;//历史记录和热门搜索布局
    @BindView(R.id.search_recyclerView)
    RecyclerView recyclerView;//布局
    @BindView(R.id.search_refreshLayout)
    TwinklingRefreshLayout refreshLayout;//下拉
    @BindView(R.id.search_curriculum_History)
    LinearLayout historyLinear;
    private int page = 0;
    private List<SearchEntity.DataBean.UserSearchRecordBean> historyList;//历史记录集合
    private List<SearchEntity.DataBean.HotSearchRecordBean> hotList;//热门搜索集合
    //    private SearchHotAdapter hotAdapter;//热门适配器
//    private SearchHistoryAdapter historyAdapter;//历史记录适配器
    private Context mContext;
    private List<SearchCurriCulumEntity.DataBean.SearchCurriculumListBean> list;
    private SearchCurriculumAdapter adapter;
    private boolean loadMore = false;
    private TextView emptyName;//空布局 的暂无课程
    private RecyclerView emptyRecyclerView; //空布局 的猜你喜欢
    private LinearLayout emptyExchange;//空布局 换一批
    private ArrayList<SearchNoneEntity.DataBean> searchNoneList;
    private SearchNoneAdapter searchNoneAdapter;
    protected BGASwipeBackHelper mSwipeBackHelper;
    private TextView headNumber;//头部 共找到几门
    private TextView headName; //搜索的名字
    private View headView;
//    private String searchStr="";
    private ImageView emptyImg;
    private ProgressDialogUtils progressDialogUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);
        ButterKnife.bind(this);
        mContext = this;
        StatusBarUtil.setStatusBar(this, R.id.search_statusBar);
        initView();
    }


//    private void setStatusBar() {
//        // 设置透明导航栏
//        StatusBarUtil.setTranslucentForImageView(this, 50, null);
//        int statusBarHeight = StatusBarUtil.getStatusBarHeight(this);
//        View viewById = findViewById(R.id.search_statusBar);
//        ViewGroup.LayoutParams layoutParams = viewById.getLayoutParams();
//        layoutParams.height = statusBarHeight;
//    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    private void initView() {
        mSwipeBackHelper = new BGASwipeBackHelper(this, this);
        mSwipeBackHelper.setSwipeBackEnable(false);//设置不可侧滑
        historyList = new ArrayList<>();
        hotList = new ArrayList<>();
        mPresenter = new SearchPresenter(this);
        mPresenter.getHotHistory(UriConstant.token);
        list = new ArrayList<>();
        adapter = new SearchCurriculumAdapter(R.layout.item_new_course_new_course, list);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        recyclerView.setAdapter(adapter);
        View emptyView = View.inflate(mContext, R.layout.activity_search_empty, null);//空布局
        emptyName = emptyView.findViewById(R.id.nothing_buy);//暂无搜索到课程
        //暂无搜索到课程
        emptyImg = emptyView.findViewById(R.id.empty_img);
        emptyImg.setImageResource(R.mipmap.search_empty);
        emptyRecyclerView = emptyView.findViewById(R.id.empty_recyclerView);//猜你喜欢
        emptyExchange = emptyView.findViewById(R.id.empty_exchange);//换一批
        adapter.setEmptyView(emptyView);

        //头布局
        headView = View.inflate(mContext, R.layout.header_search_result, null);
        headNumber = headView.findViewById(R.id.searchResult_head_number);
        headName = headView.findViewById(R.id.searchResult_head_name);
        adapter.setHeaderView(headView);
//软键盘回车监听
        searchView.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //当actionId == XX_SEND 或者 XX_DONE时都触发
                //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
                //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    if (!TextUtils.isEmpty(searchView.getText().toString().trim())){

                        refreshLayout.setEnableLoadmore(true);
//                    searchStr = searchView.getText().toString();
                        mPresenter.getCurriculumData(String.valueOf(page++), "8", searchView.getText().toString(), "", UriConstant.token);
                    }else{
                        ToastUtils.showShort("请输入搜索内容");
                    }
                    return true;
                }
                return false;
            }
        });
        searchView.addTextChangedListener(new TextWatcher() {
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
                    recyclerView.setVisibility(View.GONE);
                    refreshLayout.setVisibility(View.GONE);
                    searchLinear.setVisibility(View.VISIBLE);
                } else {
                    cancel.setText("清除");
                }
            }
        });
        searchNoneList = new ArrayList<>();
        emptyRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        searchNoneAdapter = new SearchNoneAdapter(R.layout.item_new_course_new_course, searchNoneList);
        searchNoneAdapter.openLoadAnimation();//alpha动画
        emptyRecyclerView.setAdapter(searchNoneAdapter);
        refreshLayout.setEnableLoadmore(true);
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setOverScrollTopShow(false);
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                loadMore = true;
                mPresenter.getCurriculumData(String.valueOf(++page), "20", searchView.getText().toString(), "", UriConstant.token);
            }
        });
        //猜你喜欢布局
        searchNoneAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                SearchNoneEntity.DataBean item = (SearchNoneEntity.DataBean) adapter.getItem(position);
                Intent it = new Intent(mContext,CourseDetailsActivity.class);
                it.putExtra("courseId",item.getId());
                startActivity(it);
            }
        });
        //换一批
        emptyExchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPresenter != null) {
                    mPresenter.getSearchNoneData();
                }
            }
        });

        //历史搜索
        hotFlowLayout.setAdapter(new TagAdapter<SearchEntity.DataBean.HotSearchRecordBean>(hotList) {
            @Override
            public View getView(FlowLayout parent, int position, SearchEntity.DataBean.HotSearchRecordBean hotSearchRecordBean) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_search_history, parent, false);
                TextView textView = view.findViewById(R.id.item_search_history_text);
                textView.setText(hotSearchRecordBean.getSearch_word());
                return view;
            }
        });

        hotFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
//                    Intent it = new Intent(SearchActivity.this, SearchCurriculumActivity.class);
//                    it.putExtra("searchWord", hotList.get(position).getSearch_word() + "");
//                    startActivity(it);
//                    finish();
                searchView.setText(hotList.get(position).getSearch_word());
                searchView.setSelection(hotList.get(position).getSearch_word().length());
                refreshLayout.setEnableLoadmore(true);
//                searchStr = hotList.get(position).getSearch_word();
                mPresenter.getCurriculumData("", "",searchView.getText().toString() , "", UriConstant.token);

                return true;
            }
        });

        hisFlowLayout.setAdapter(new TagAdapter<SearchEntity.DataBean.UserSearchRecordBean>(historyList) {
            @Override
            public View getView(FlowLayout parent, int position, SearchEntity.DataBean.UserSearchRecordBean bean) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_search_history, parent, false);
                TextView textView = view.findViewById(R.id.item_search_history_text);
                textView.setText(bean.getSearch_word());
                return view;
            }
        });


        hisFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                searchView.setText(historyList.get(position).getSearch_word());
                searchView.setSelection(historyList.get(position).getSearch_word().length());
                refreshLayout.setEnableLoadmore(true);
//                searchStr = historyList.get(position).getSearch_word();
                mPresenter.getCurriculumData("", "", searchView.getText().toString(), "", UriConstant.token);
                return true;
            }
        });

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SearchCurriCulumEntity.DataBean.SearchCurriculumListBean item = (SearchCurriCulumEntity.DataBean.SearchCurriculumListBean) adapter.getItem(position);
                Intent it = new Intent(mContext, CourseDetailsActivity.class);
                it.putExtra("courseId", item.getId());
                startActivity(it);
            }
        });
    }

    @Override
    public void showLoading() {
        if (mContext != null) {
            progressDialogUtils = new ProgressDialogUtils(mContext);
            progressDialogUtils.showDialog();
        }
    }

    @Override
    public void hideLoading() {
        if (mContext != null) {
            progressDialogUtils.dismissDialog();
        }
    }

    @Override
    public void showData(SearchEntity data) {
        if (data.getStatus() == 0) {
//            //添加历史搜索
            hotList.clear();
            if (data.getData().getHotSearchRecord().size() > 10) {
                hotList.addAll(data.getData().getHotSearchRecord().subList(0, 9));
            } else {
                hotList.addAll(data.getData().getHotSearchRecord());
            }
//            //添加热门搜索
            historyList.clear();
            if (data.getData().getUserSearchRecord().size() > 10) {
                historyList.addAll(data.getData().getUserSearchRecord().subList(0, 9));
            } else {
                historyList.addAll(data.getData().getUserSearchRecord());
            }
            if (historyList.size()==0){
                historyLinear.setVisibility(View.GONE);
            }else{
                historyLinear.setVisibility(View.VISIBLE);
            }

            hotFlowLayout.getAdapter().notifyDataChanged();
            hisFlowLayout.getAdapter().notifyDataChanged();


        }
    }

    /**
     * 清除历史记录
     *
     * @param data
     */
    @Override
    public void showClearData(ClearHistoryEntity data) {
        if (data.getStatus() == 0) {
            historyList.clear();
            hisFlowLayout.getAdapter().notifyDataChanged();
        }
    }

    @Override
    public void showCurriculumData(final SearchCurriCulumEntity data) {
        if (data.getStatus() == 0) {
            refreshLayout.finishLoadmore();
            recyclerView.setVisibility(View.VISIBLE);//显示搜索列表
            refreshLayout.setVisibility(View.VISIBLE);//显示搜索列表
            searchLinear.setVisibility(View.GONE);//隐藏历史布局
            if (!loadMore) {
                list.clear();
            } else {
                loadMore = false;
            }
            list.addAll(data.getData().getSearchCurriculumList());
            if (list.size() == 0) {
                refreshLayout.setEnableLoadmore(false);
                mPresenter.getSearchNoneData();
                headView.setVisibility(View.GONE);
                emptyName.setText(searchView.getText().toString());//空布局
            } else {
                refreshLayout.setEnableLoadmore(false);
                headView.setVisibility(View.VISIBLE);
                headNumber.setText("共找到" + list.size() + "门“");
                headName.setText(searchView.getText().toString());
            }
            adapter.notifyDataSetChanged();
        } else {

        }
    }

    @Override
    public void showNoneData(SearchNoneEntity data) {
        if (data.getStatus() == 0) {
            searchNoneAdapter.setNewData(data.getData());
        }
    }

    @Override
    public void showFailureMessage(String msg) {

    }

    @Override
    public void showErrorMessage() {

    }

    @OnClick({R.id.search_curriculum_cancel, R.id.search_curriculum_clearHistory})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_curriculum_cancel:
                if (cancel.getText().toString().equals("清除")) {
                    searchView.setText("");
//                    searchStr = "";
                } else {
                    //收回软键盘
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),
                                0);
                    }
                    finish();
                }
                break;
            case R.id.search_curriculum_clearHistory:
                mPresenter.clearHistory(UriConstant.token);//请求接口 清除历史记录
                break;
        }
    }
}
