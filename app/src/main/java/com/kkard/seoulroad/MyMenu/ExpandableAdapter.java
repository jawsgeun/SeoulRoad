package com.kkard.seoulroad.MyMenu;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kkard.seoulroad.R;

import java.util.ArrayList;

/**
 * Created by SuGeun on 2017-09-04.
 */

public class ExpandableAdapter extends BaseExpandableListAdapter {
    ArrayList<NoticeParentData> noticeParentDatas;
    ArrayList<ArrayList<NoticeChildData>> noticeChildDatas;
    LayoutInflater layoutInflater;

    public ExpandableAdapter(Context c, ArrayList<NoticeParentData> noticeParentDatas,
                             ArrayList<ArrayList<NoticeChildData>> noticeChildDatas ) {
        this.noticeChildDatas = noticeChildDatas;
        this.noticeParentDatas = noticeParentDatas;
        layoutInflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return noticeParentDatas.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return noticeChildDatas.get(groupPosition).size();
    }

    @Override
    public NoticeParentData getGroup(int groupPosition) {
        return noticeParentDatas.get(groupPosition);
    }

    @Override
    public NoticeChildData getChild(int groupPosition, int childPosition) {
        return noticeChildDatas.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.layout_listview_parent,null);
        }
        Log.e("갱신","실행");
        if(groupPosition%2==0){
            convertView.setBackgroundColor(Color.rgb(189,189,189));
            Log.e("벌집","실행");
        }
        ImageView image_parent_iv = (ImageView)convertView.findViewById(R.id.image_parent);
        TextView content_parent_tv = (TextView)convertView.findViewById(R.id.content_parent);
        TextView date_parent_tv = (TextView)convertView.findViewById(R.id.date_parent);
        if(isExpanded){
            image_parent_iv.setImageResource(R.drawable.listview_on);
        } else{
            image_parent_iv.setImageResource(R.drawable.listview_off);
        }
        content_parent_tv.setText(noticeParentDatas.get(groupPosition).getTitle());
        date_parent_tv.setText(noticeParentDatas.get(groupPosition).getDate());

        return convertView;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        View parentV = layoutInflater.inflate(R.layout.layout_listview_parent,null);
        parentV.setBackgroundColor(Color.rgb(206,242,121));
        ImageView image_parent_iv = (ImageView)parentV.findViewById(R.id.image_parent);
        image_parent_iv.setImageResource(R.drawable.listview_on);
        Log.e("확장","실행");

        super.onGroupExpanded(groupPosition);
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        View parentV = layoutInflater.inflate(R.layout.layout_listview_parent,null);
        ImageView image_parent_iv = (ImageView)parentV.findViewById(R.id.image_parent);
        image_parent_iv.setImageResource(R.drawable.listview_off);
        Log.e("축소","실행");
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.layout_listview_child,null);
        }
        TextView content_child_tv = (TextView)convertView.findViewById(R.id.content_child);
        content_child_tv.setText(noticeChildDatas.get(groupPosition).get(childPosition).getContent());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
