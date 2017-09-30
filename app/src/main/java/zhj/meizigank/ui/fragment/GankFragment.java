package zhj.meizigank.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import zhj.meizigank.R;
import zhj.meizigank.contract.GankFgContract;
import zhj.meizigank.presenter.GankFgPresenter;
import zhj.meizigank.ui.base.MVPBaseFragment;


public class GankFragment extends MVPBaseFragment<GankFgContract.IGankFgView,GankFgPresenter> implements GankFgContract.IGankFgView {

    private GridLayoutManager gridLayoutManager;

    @BindView(R.id.content_list)
    RecyclerView mRv_content_list;

    @Override
    protected GankFgPresenter createPresenter() {
        return new GankFgPresenter(getActivity());
    }

    @Override
    protected int createViewLayoutId() {
        return R.layout.fragment_gank;
    }

    @Override
    protected void initView(View rootView) {
        gridLayoutManager = new GridLayoutManager(getContext(),2);
        mRv_content_list.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setDataRefresh(true);
        mPresenter.getGankData();
        mPresenter.scrollRecycleView();
    }

    @Override
    public void requestDataRefresh() {
        super.requestDataRefresh();
        setDataRefresh(true);
        mPresenter.getGankData();
    }

    @Override
    public void setDataRefresh(Boolean refresh) {
        setRefresh(refresh);
    }

    @Override
    public GridLayoutManager getLayoutManager() {
        return gridLayoutManager;
    }

    @Override
    public RecyclerView getRecyclerView() {
        return mRv_content_list;
    }
}
