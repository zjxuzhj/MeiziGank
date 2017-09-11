package zhj.meizigank.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Calendar;

import butterknife.BindView;
import zhj.meizigank.R;
import zhj.meizigank.presenter.GankPresenter;
import zhj.meizigank.ui.base.MVPBaseActivity;
import zhj.meizigank.ui.view.IGankView;


public class GankActivity extends MVPBaseActivity<IGankView,GankPresenter> implements IGankView {

    private static final String DATE = "date";

    private int year;
    private int month;
    private int day;

    @BindView(R.id.gank_list)
    RecyclerView gank_list;

    @Override
    protected GankPresenter createPresenter() {
        return new GankPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_gank;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        gank_list.setLayoutManager(layoutManager);

        setTitle("Gank の 今日特供");
        parseIntent();
        setDataRefresh(true);
        mPresenter.getGankList(year,month,day);
    }

    public static Intent newIntent(Context context, long date){
        Intent intent = new Intent(context,GankActivity.class);
        intent.putExtra(GankActivity.DATE,date);
        return intent;
    }

    private void parseIntent(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(getIntent().getLongExtra(DATE,0));
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH)+1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public Boolean isSetRefresh() {
        return true;
    }

    @Override
    public boolean canBack() {
        return true;
    }

    @Override
    public void setDataRefresh(boolean refresh) {
        setRefresh(refresh);
    }

    @Override
    public void requestDataRefresh() {
        super.requestDataRefresh();
        setDataRefresh(true);
        mPresenter.getGankList(year,month,day);
    }

    @Override
    public RecyclerView getRecyclerView() {
        return gank_list;
    }
}
