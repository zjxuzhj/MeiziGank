package zhj.meizigank.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import zhj.meizigank.R;
import zhj.meizigank.bean.gank.Gank;
import zhj.meizigank.contract.GankContract;
import zhj.meizigank.ui.adapter.GankActivityAdapter;


public class GankPresenter extends GankContract.Presenter {

    private Context context;
    private GankContract.IGankView gankView;
    private RecyclerView recyclerView;

    public GankPresenter(Context context) {
        this.context = context;
    }

    public void getGankList(int year, int month, int day) {
        gankView = getView();
        if (gankView != null) {
            recyclerView = gankView.getRecyclerView();

            gankApi.getGankData(year, month, day)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(gankData -> {
                        displayGankList(context, gankData.results.getAllResults(), gankView, recyclerView);
                    }, this::loadError);
        }
    }

    private void loadError(Throwable throwable) {
        throwable.printStackTrace();
        Toast.makeText(context, R.string.gank_load_error, Toast.LENGTH_SHORT).show();
    }

    private void displayGankList(Context context, List<Gank> gankList, GankContract.IGankView gankView, RecyclerView recyclerView) {
        GankActivityAdapter adapter = new GankActivityAdapter(context, gankList);
        recyclerView.setAdapter(adapter);
        gankView.setDataRefresh(false);
    }
}
