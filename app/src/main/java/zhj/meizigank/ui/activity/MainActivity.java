package zhj.meizigank.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import zhj.meizigank.R;
import zhj.meizigank.ui.adapter.ViewPagerFgAdapter;
import zhj.meizigank.ui.base.BasePresenter;
import zhj.meizigank.ui.base.MVPBaseActivity;
import zhj.meizigank.ui.base.MVPBaseFragment;
import zhj.meizigank.ui.fragment.GankFragment;


public class MainActivity extends MVPBaseActivity {
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.content_viewPager)
    ViewPager content_viewPager;
    private List<MVPBaseFragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
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
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.fragment, new GankFragment());
//        fragmentTransaction.commit();
//        content_viewPager.setOffscreenPageLimit(3);//设置至少3个fragment，防止重复创建和销毁，造成内存溢出
        content_viewPager.setAdapter(new ViewPagerFgAdapter(getSupportFragmentManager(), fragmentList, "main_view_pager"));//给ViewPager设置适配器
        tabLayout.setupWithViewPager(content_viewPager);//将TabLayout和ViewPager关联起来
    }

}
