package com.kkard.seoulroad.Recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kkard.seoulroad.Circleindicator_C.IndicatorAdapter;
import com.kkard.seoulroad.R;
import com.kkard.seoulroad.ViewHolder.CourseItemHolder;
import com.kkard.seoulroad.ViewHolder.ImageItemHolder;
import com.kkard.seoulroad.ViewHolder.MainCourseItemHolder;
import com.kkard.seoulroad.ViewHolder.MainTextItemHolder;
import com.kkard.seoulroad.ViewHolder.MypostItemHolder;
import com.kkard.seoulroad.ViewHolder.PagerItemHolder;
import com.kkard.seoulroad.ViewHolder.TextItemHolder;
import com.kkard.seoulroad.utils.Check;
import com.kkard.seoulroad.utils.DialogView_C;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by SuGeun on 2017-10-03.
 */

public class ViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int VIEW_TYPE_TEXT_MAIN = 50;
    public static final int VIEW_TYPE_TEXT = 51;
    public final static int VIEW_TYPE_PAGER = 52;
    public static final int VIEW_TYPE_IMAGE = 53;
    public static final int VIEW_TYPE_POST = 54;
    public static final int VIEW_TYPE_COURSE = 55;
    public static final int VIEW_TYPE_MAIN_COURSE = 56;

    private DialogView_C mDialog;
    private Context mcontext;
    private List<Data> mDataList = new ArrayList<>();

    public ViewAdapter(List<Data> list, Context context) {

        if (list != null && list.size() > 0)
            mDataList.addAll(list);
        mcontext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case VIEW_TYPE_MAIN_COURSE:
                View mainCourseView = inflater.inflate(R.layout.layout_recycle_course, parent, false);
                viewHolder = new MainCourseItemHolder(mainCourseView);
                break;
            case VIEW_TYPE_COURSE:
                View courseView = inflater.inflate(R.layout.layout_recycle_course_detail, parent, false);
                viewHolder = new CourseItemHolder(courseView);
                break;
            case VIEW_TYPE_TEXT_MAIN:
                View mainUserView = inflater.inflate(R.layout.layout_recycle_maintext, parent, false);
                viewHolder = new MainTextItemHolder(mainUserView);
                break;
            case VIEW_TYPE_TEXT:
                View userView = inflater.inflate(R.layout.layout_recycle_text, parent, false);
                viewHolder = new TextItemHolder(userView);
                break;
            case VIEW_TYPE_PAGER:
                View blockbusterView = inflater.inflate(R.layout.layout_recycle_pager, parent, false);
                viewHolder = new PagerItemHolder(blockbusterView);
                break;
            case VIEW_TYPE_IMAGE:
                View userImageView = inflater.inflate(R.layout.layout_recycle_image, parent, false);
                viewHolder = new ImageItemHolder(userImageView);
                break;
            case VIEW_TYPE_POST:
                View userPostView = inflater.inflate(R.layout.layout_recycle_mypost, parent, false);
                viewHolder = new MypostItemHolder(userPostView);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MAIN_COURSE:
                MainCourseItemHolder mainCourseItemHolder = (MainCourseItemHolder) holder;
                configureMaincourseHolder(mainCourseItemHolder,position);
                break;
            case VIEW_TYPE_COURSE:
                CourseItemHolder courseItemHolder = (CourseItemHolder) holder;
                configureCourseHolder(courseItemHolder,position);
                break;
            case VIEW_TYPE_TEXT_MAIN:
                MainTextItemHolder mainTextHolder = (MainTextItemHolder) holder;
                configureMainTextItem(mainTextHolder, position);
                break;

            case VIEW_TYPE_TEXT:
                TextItemHolder textHolder = (TextItemHolder) holder;
                configureTextItem(textHolder, position);
                break;

            case VIEW_TYPE_PAGER:
                PagerItemHolder pagerHolder = (PagerItemHolder) holder;
                configurePagerHolder(pagerHolder, position);
                break;

            case VIEW_TYPE_IMAGE:
                ImageItemHolder imageHolder = (ImageItemHolder) holder;
                configureImageHolder(imageHolder, position);
                break;

            case VIEW_TYPE_POST:
                MypostItemHolder postHolder = (MypostItemHolder) holder;
                configurePostHolder(postHolder, position);
                break;
        }
    }
    private void configureMaincourseHolder(MainCourseItemHolder holder, int position){
        Data data = mDataList.get(position);

        if(data.getmImageId() != -1){
            holder.mainCourseImage.setImageResource(data.getmImageId());
        }
    }
    private void configureCourseHolder(CourseItemHolder holder, int position){
        Data data = mDataList.get(position);

        if(data.getmCourseContent().size()==3) { //사진, 제목, 내용 까지 총 3개가 들어왔는지
            holder.courseImage.setImageResource(R.drawable.test_pink); // 이미지 저장 디비 구축 후 수정
            holder.courseTitle.setText(data.getmCourseContent().get(1));
            holder.courseContent.setText(data.getmCourseContent().get(2));
        }
    }
    private void configurePostHolder(MypostItemHolder holder, int position){
        Data data = mDataList.get(position);

        if (data.getmPostContent().size()==5){ // 아이디, 이미지, 좋아요 수, 날짜, 코멘트 까지 총 5개가 들어왔는지
            holder.mypostUserid.setText(data.getmPostContent().get(0));
            holder.mypostImg.setImageResource(R.drawable.testimage_mypost); // 이미지 저장 디비 구축 후 수정
            holder.mypostLike.setText("좋아요 "+data.getmPostContent().get(2)+"명");
            holder.mypostDate.setText(data.getmPostContent().get(3));
            holder.mypostCom.setText(data.getmPostContent().get(4));
        }
    }
    private void configureMainTextItem(MainTextItemHolder holder, int position) {

        Data data = mDataList.get(position);

        if (!Check.isEmpty(data.getTextTile()))
            holder.mainTvTitle.setText(data.getTextTile());
            holder.mainTvContent1.setText("-"+data.getTextContent().get(0));
            holder.mainTvContent2.setText("-"+data.getTextContent().get(1));
            holder.mainTvContent3.setText("-"+data.getTextContent().get(2));
            holder.mainTvContent4.setText("-"+data.getTextContent().get(3));
            holder.mainTvContent5.setText("-"+data.getTextContent().get(4));
    }
    private void configureTextItem(TextItemHolder holder, int position) {

        Data data = mDataList.get(position);

        if (!Check.isEmpty(data.getTextTile()))
            holder.tvTitle.setText(data.getTextTile());
            holder.tvContent.setText(data.getTextSingle());
    }

    private void configurePagerHolder(PagerItemHolder holder, int position) {

        IndicatorAdapter mPageAdapter = new IndicatorAdapter(mcontext);
        holder.viewPager.setAdapter(mPageAdapter);
        holder.indicator.setViewPager(holder.viewPager);

}
    private void configureImageHolder(ImageItemHolder holder, int position){
        final Data data = mDataList.get(position);

        if(!data.getListImageItemList().isEmpty()){
            holder.imageView1.setImageResource(data.getListImageItemList().get(0).getItemImage());
            holder.imageView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialog = new DialogView_C(v.getContext(),data.getListImageItemList().get(0).getItemImage(),"몇번째 방문자입니다.","아이디@이메일.com","xxx개","주저리주저리");
                    mDialog.show();
                    mDialog.setCanceledOnTouchOutside(false);
                }
            });
            holder.imageView2.setImageResource(data.getListImageItemList().get(1).getItemImage());
            holder.imageView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialog = new DialogView_C(v.getContext(),data.getListImageItemList().get(1).getItemImage(),"몇번째 방문자입니다.","아이디@이메일.com","xxx개","주저리주저리");
                    mDialog.show();
                    mDialog.setCanceledOnTouchOutside(false);
                }
            });
            holder.imageView3.setImageResource(data.getListImageItemList().get(2).getItemImage());
            holder.imageView3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialog = new DialogView_C(v.getContext(),data.getListImageItemList().get(2).getItemImage(),"몇번째 방문자입니다.","아이디@이메일.com","xxx개","주저리주저리");
                    mDialog.show();
                    mDialog.setCanceledOnTouchOutside(false);
                }
            });
        }

    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {

    }

    @Override
    public int getItemViewType(int position) {

        return mDataList.get(position).getViewType();
    }

    @Override
    public int getItemCount() {

        return mDataList.size();
    }
}
