package zhj.meizigank.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import zhj.meizigank.R;


/**
 * Created by panyi on 2017/8/9.
 */

public class GridViewHolder extends RecyclerView.ViewHolder {
    public ImageView mImage;
    public TextView mTitle;
    public GridViewHolder(View itemView) {
        super(itemView);
        mImage = (ImageView) itemView.findViewById(R.id.img);
        mTitle = (TextView) itemView.findViewById(R.id.title);
    }
}//end class
