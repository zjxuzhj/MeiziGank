package zhj.meizigank.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import zhj.meizigank.bean.gank.GankData;
import zhj.meizigank.bean.gank.Meizhi;
import zhj.meizigank.bean.gank.Video;

/**
 * Created by Werb on 2016/8/18.
 * Werb is Wanbo.
 * Contact Me : werbhelius@gmail.com
 * get Gank with retrofit
 */
public interface GankApi {

    @GET("data/福利/10/{page}")
    Observable<Meizhi> getMeizhiData(@Path("page") int page);

    @GET("day/{year}/{month}/{day}")
    Observable<GankData> getGankData(@Path("year") int year, @Path("month") int month, @Path("day") int day);

    @GET("data/休息视频/10/{page}")
    Observable<Video> getVideoData(@Path("page") int page);
}
