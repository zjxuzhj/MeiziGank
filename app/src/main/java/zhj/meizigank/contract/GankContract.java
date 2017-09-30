package zhj.meizigank.contract;

import android.support.v7.widget.RecyclerView;

import zhj.meizigank.ui.base.BasePresenter;

/**
 * Created by HongJay on 2017/9/30.
 */

public interface GankContract {
    interface IGankView {

        RecyclerView getRecyclerView();
        void setDataRefresh(boolean refresh);
    }


    abstract class Presenter extends BasePresenter<IGankView> {

    }
}
