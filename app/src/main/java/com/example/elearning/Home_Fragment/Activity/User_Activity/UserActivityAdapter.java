package com.example.elearning.Home_Fragment.Activity.User_Activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.elearning.R;

import org.xutils.x;

import java.util.List;

public class UserActivityAdapter extends RecyclerView.Adapter <UserActivityAdapter.MyViewHolder>{

    private List<UserActivity> userActivities=null;

    public UserActivityAdapter(List<UserActivity> userActivities) {
        this.userActivities = userActivities;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.recycler_activity_item,viewGroup,false);
        final MyViewHolder myViewHolder=new MyViewHolder(view);
        final boolean[] judge = {false};
        myViewHolder.likeIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(judge[0] ==false){
                    judge[0] =true;
                    myViewHolder.likeIV.setSelected(judge[0]);
                    Toast.makeText(v.getContext(), "点赞成功", Toast.LENGTH_LONG).show();
                }else{
                    judge[0] =false;
                    myViewHolder.likeIV.setSelected(judge[0]);
                    Toast.makeText(v.getContext(), "取消点赞", Toast.LENGTH_LONG).show();
                }
            }
        });
        return myViewHolder ;
    }


    /**
     * 绑定
     * */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        UserActivity activity=userActivities.get(i);

        myViewHolder.headIV.setImageResource(activity.getHeadId());
        myViewHolder.userNameTV.setText(activity.getUserName());
        x.image().bind(myViewHolder.pictureIV,activity.getPictureId());
        myViewHolder.contentTV.setText(activity.getContent());
        myViewHolder.likeIV.setImageResource(activity.getLikeId());
    }

    @Override
    public int getItemCount() {
        return userActivities.size();
    }


    /**
     *自定义MyViewHolder
     * */
    static class MyViewHolder extends RecyclerView.ViewHolder{
        View userActivityView;
        private ImageView headIV;
        private ImageView pictureIV;
        private ImageView likeIV;
        private TextView userNameTV;
        private TextView contentTV;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userActivityView=itemView;
            headIV=(ImageView) itemView.findViewById(R.id.headIV);
            pictureIV=(ImageView) itemView.findViewById(R.id.pictureIV);
            likeIV=(ImageView) itemView.findViewById(R.id.likeIV);
            userNameTV=(TextView)itemView.findViewById(R.id.userNameTV);
            contentTV=(TextView)itemView.findViewById(R.id.contentTV);
        }
    }
}
