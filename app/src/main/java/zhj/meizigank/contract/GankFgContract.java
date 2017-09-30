package zhj.meizigank.contract;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import zhj.meizigank.ui.base.BasePresenter;

/**
 * Created by HongJay on 2017/9/30.
 */

public interface GankFgContract {
     interface IGankFgView {

        void setDataRefresh(Boolean refresh);
        GridLayoutManager getLayoutManager();
        RecyclerView getRecyclerView();
    }


    abstract  class Presenter extends BasePresenter<IGankFgView> {

    }
}
