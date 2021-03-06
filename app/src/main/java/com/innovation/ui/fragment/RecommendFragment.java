package com.innovation.ui.fragment;

import android.app.Application;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.innovation.AppApplication;
import com.innovation.R;
import com.innovation.bean.AppInfo;
import com.innovation.di.component.AppComponent;
import com.innovation.di.component.DaggerAppComponent;
import com.innovation.di.component.DaggerRecommendComponent;
import com.innovation.di.module.RecommendModule;
import com.innovation.presenter.RecommendPresenter;
import com.innovation.presenter.contract.RecommendContract;
import com.innovation.ui.adapter.RecommendAppAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Ivan on 16/9/26.
 */

public class RecommendFragment extends BaseFragment<RecommendPresenter> implements RecommendContract.View{


    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;

    private RecommendAppAdapter mAdapter;

    @Inject
    ProgressDialog mProgressDialog;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public int setLayout() {
        return R.layout.fragment_recomend;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerRecommendComponent.builder().appComponent(appComponent)
                .recommendModule(new RecommendModule(this)).build().inject(this);
    }

    @Override
    public void init() {
        mPresenter.requestDatas();
    }


    private void initRecyclerView(List<AppInfo> datas){
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new RecommendAppAdapter(getActivity(),datas);
        mRecycleView.setAdapter(mAdapter);

    }


    @Override
    public void showNodata() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showResult(List<AppInfo> datas) {
        initRecyclerView(datas);
    }

    @Override
    public void showLoading() {
        mProgressDialog.show();
    }

    @Override
    public void dismissLoading() {
        if(mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }
}
