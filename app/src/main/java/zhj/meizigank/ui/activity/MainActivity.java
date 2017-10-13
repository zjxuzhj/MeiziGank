package zhj.meizigank.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import zhj.meizigank.R;
import zhj.meizigank.ui.adapter.ViewPagerFgAdapter;
import zhj.meizigank.ui.base.BasePresenter;
import zhj.meizigank.ui.base.MVPBaseActivity;
import zhj.meizigank.ui.base.MVPBaseFragment;
import zhj.meizigank.ui.fragment.GankFragment;

import static zhj.meizigank.R.id.titleText;


public class MainActivity extends MVPBaseActivity {
//    @BindView(R.id.tabLayout)
//    TabLayout tabLayout;
    @BindView(R.id.content_viewPager)
    ViewPager content_viewPager;
    @BindView(R.id.heder_pic)
    ImageView heder_pic;
    @BindView(R.id.appBarlayout)
    AppBarLayout appBarlayout;
    @BindView(R.id.titleText)
    TextView titleText;
    private List<MVPBaseFragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        appBarlayout.addOnOffsetChangedListener(new MyOffsetChangedListener());
    }
    private class MyOffsetChangedListener implements AppBarLayout.OnOffsetChangedListener{

        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            float progress = Math.abs(verticalOffset)*1.0f/appBarLayout.getTotalScrollRange();
            if (progress>=0.4){
                titleText.setAlpha(1-progress);
            }else {
                titleText.setAlpha(1);
                titleText.setText("aaa");
            }
        }
    }
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    private void initView() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new GankFragment());
        content_viewPager.setOffscreenPageLimit(3);//设置至少3个fragment，防止重复创建和销毁，造成内存溢出
        content_viewPager.setAdapter(new ViewPagerFgAdapter(getSupportFragmentManager(), fragmentList, "main_view_pager"));//给ViewPager设置适配器
//        tabLayout.setupWithViewPager(content_viewPager);//将TabLayout和ViewPager关联起来
    }

}
