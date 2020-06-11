package cn.edu.scujcc.startactivity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class CookbookRVAdapter extends RecyclerView.Adapter<CookbookRVAdapter.ChannelRowHolder> {
    private CookbookLab lab= CookbookLab.getInstance();
private Context context;
    private ChannelClikLisenter lisenter;
    private final static String TAG="DianDian";
    public CookbookRVAdapter(Context context, ChannelClikLisenter lisenter){
        this.lisenter=lisenter;
        this.context=context;
    }
    /**
     * 当需要时，此方法负责创建这一类的对象
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ChannelRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView= LayoutInflater.from(parent.getContext()).inflate(R.layout.channel_row,parent,false);
        ChannelRowHolder holder=new ChannelRowHolder(rowView);
        return holder;
    }

    /**
     * 用于确定一行的内容是什么
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ChannelRowHolder holder, int position) {

//        holder.bind("中央1台","1080p",R.drawable.cctv);
        Cookbook c=lab.getChannel(position);
        holder.bind(c);
        //    Glide.with(context).load(url).placeholder(R.mipmap.ic_launcher)
        //    .diskCacheStrategy(DiskCacheStrategy.RESULT)
//    //.bitmapTransform(new CropCircleTransformation(mContext))
// 如果想使用变换效果，这个注释可以打开
//        .into(imageview);
    }

    /**
     * 用于确定返回总共有几行
     * @return
     */
    @Override
    public int getItemCount() {

        return lab.getSize();
    }

    /**
     * 单行布局对应的java控制类
     */
    public class ChannelRowHolder extends RecyclerView.ViewHolder{
 private TextView title;
 private TextView maked;
 private TextView cstar;
 private ImageView cover;
        public ChannelRowHolder(@NonNull View row) {
            super(row);
            this.cstar=row.findViewById(R.id.cookbook_cstar);
            this.title=row.findViewById(R.id.cookbook_title);
            this.maked=row.findViewById(R.id.cookbook_maked);
            this.cover=row.findViewById(R.id.cookbook_img);
            row.setOnClickListener((v)->{
                int position=getAdapterPosition();
                Log.d(TAG,position+"行被点击了");
                lisenter.onChannelClick(position);
            });
        }

        /**
         * 修改title内容
         * @param
         * @param c
         */
        public  void bind(Cookbook c){
            this.title.setText(c.getTitle());
         this.cstar.setText(c.getCstar());
            this.maked.setText(c.getMaked());
//            this.cctv.setImageResource(c.getCover());
            Log.d(TAG,c.getTitle()+"准备从网络加载图片"+c.getCover());
            Glide.with(context)
                    .load(c.getCover())
                    .into(this.cover);
        }
    }
    public interface ChannelClikLisenter{

        public void onChannelClick(int position);//定义接口
    }

}
