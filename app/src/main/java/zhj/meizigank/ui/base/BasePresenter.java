package zhj.meizigank.ui.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import zhj.meizigank.api.ApiFactory;
import zhj.meizigank.api.GankApi;

public abstract class BasePresenter<V> {

    protected Reference<V> mViewRef;

    public static final GankApi gankApi = ApiFactory.getGankApiSingleton();

    public void attachView(V view){
        mViewRef = new WeakReference<V>(view);
    }

    protected V getView(){
        return mViewRef.get();
    }

    public boolean isViewAttached(){
        return mViewRef != null&&mViewRef.get()!=null;
    }

    public void detachView(){
        if(mViewRef!=null){
            mViewRef.clear();
            mViewRef = null;
        }
    }

}