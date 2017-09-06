package zhj.meizigank.activity;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import zhj.meizigank.R;
import zhj.meizigank.adapter.GridAdapter;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mGridList;
    private GridAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mGridList = (RecyclerView) findViewById(R.id.grid_list);
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mGridList.setLayoutManager(layoutManager);
        mAdapter = new GridAdapter();
        mAdapter.notifyDataSetChanged();
        mGridList.setAdapter(mAdapter);

        mGridList.setItemAnimator(new DefaultItemAnimator());
        mGridList.setHasFixedSize(true);

        mGridList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    GridLayoutManager lm = (GridLayoutManager) recyclerView.getLayoutManager();
                    int lastVisiblePosition = lm.findLastVisibleItemPosition();
                    if (lastVisiblePosition >= lm.getItemCount() - 1) {
                        //System.out.println("Load more...");
                    }
                }
            }
        });

        mAdapter.setItemClick(new GridAdapter.IItemClick() {
            @Override
            public void onItemClick(final View view, final int pos) {
            }
        });

        loadData();
    }

    private void loadData() {
        mTask = new FindRootNodeTask();
        mTask.execute(Constant.MEI_URL);
    }
}
