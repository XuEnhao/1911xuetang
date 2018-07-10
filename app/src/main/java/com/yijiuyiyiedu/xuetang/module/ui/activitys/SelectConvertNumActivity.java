package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.api.constant.UriConstant;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.SelectConvertNumEntity;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.SelectConvertNumAdapter;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.UtilToast;
import com.yijiuyiyiedu.xuetang.utils.titlebar.StatusBarUtil;
import com.yijiuyiyiedu.xuetang.widget.StateButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xuenhao on 2018/4/20.
 * 查看兑换码
 */

public class SelectConvertNumActivity extends BaseActivity {
    @BindView(R.id.addEvaluate_back)
    ImageView Back;
    @BindView(R.id.convertNum_buyNumber)
    TextView buyNumber;
    @BindView(R.id.convertNum_useNumber)
    TextView useNumber;
    @BindView(R.id.convertNum_buyRecyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.convertNum_copy)
    StateButton copy;
    private String orderId;
    private String status;
    private LinearLayoutManager manager;
    private List<SelectConvertNumEntity.DataBean.OrderRandomListBean> list;
    private SelectConvertNumAdapter adapter;
    private Context mContext;
    private String payNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_convert_num);
        ButterKnife.bind(this);
        StatusBarUtil.setStatusBar(this,R.id.addEvaluate_statusBar);
        mContext = this;
        payNumber = getIntent().getStringExtra("payNumber");
        //购买份数
        buyNumber.setText(payNumber + "");
        initView();
        loadData();

    }

    private void initView() {
        orderId = getIntent().getStringExtra("orderId");
        status = getIntent().getStringExtra("status");
        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        list = new ArrayList<>();
        adapter = new SelectConvertNumAdapter(R.layout.item_select_convert_num, list);
        recyclerView.setAdapter(adapter);
    }

    /**
     * 加载数据
     */
    private void loadData() {
        OkGo.<SelectConvertNumEntity>post(Constant.CONVERT_LIST)
                .tag(this)
                .params("user_id", UriConstant.userId)
                .params("order_id", orderId)
                .params("status", "1")
                .execute(new JsonCallback<SelectConvertNumEntity>() {
                    @Override
                    public void onSuccess(Response<SelectConvertNumEntity> response) {
                        SelectConvertNumEntity data = response.body();
                        if (data.getStatus().equals("1")) {
                            //可用兑换码
                            useNumber.setText(data.getData().getTotal() + "");
                            list.clear();
                            list.addAll(response.body().getData().getOrderRandomList());
                            adapter.notifyDataSetChanged();
                            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    //获取剪切板内容
                                    ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                                    cm.setText(list.get(position).getCode());
                                    UtilToast.showToast(mContext,"复制成功，可以发送给朋友们了");
                                }
                            });
                        }
                    }
                });
    }

    //导航栏
//    private void setStatusBar() {
//        // 设置透明导航栏
//        StatusBarUtil.setTranslucentForImageView(this, 50, null);
//        int statusBarHeight = StatusBarUtil.getStatusBarHeight(this);
//        View viewById = findViewById(R.id.addEvaluate_statusBar);
//        ViewGroup.LayoutParams layoutParams = viewById.getLayoutParams();
//        layoutParams.height = statusBarHeight;
//    }



    @OnClick({R.id.addEvaluate_back, R.id.convertNum_copy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.addEvaluate_back://
                JumpUtil.overiderAnimsition(this);
                break;
            case R.id.convertNum_copy://复制全部
                //获取剪切板内容
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                StringBuffer sb = new StringBuffer();
                for (int i=0;i<list.size();i++){
                    sb.append(list.get(i).getCode()+";");
                }
                cm.setText(sb);
                UtilToast.showToast(this,"复制成功，可以发送给朋友们了");
                break;
        }
    }
}
