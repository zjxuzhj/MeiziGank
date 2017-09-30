package zhj.meizigank.contract;

import android.webkit.WebView;
import android.widget.ProgressBar;

import zhj.meizigank.ui.base.BasePresenter;

/**
 * Created by HongJay on 2017/9/30.
 */

public interface GankWebContract {
    interface IGankWebView {

        ProgressBar getProgressBar();
        WebView getWebView();
    }


    abstract class Presenter extends BasePresenter<IGankWebView> {

    }
}
