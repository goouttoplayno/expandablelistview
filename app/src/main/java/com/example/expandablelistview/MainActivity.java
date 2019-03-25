package com.example.expandablelistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ExpandableListView expandableListView;
    private String[] groups = {"A", "B", "C"};
    private String[][] childs = {{"A1", "A2", "A3", "A4"}, {"A1", "A2", "A3", "A4"}, {"A1", "A2", "A3", "A4"}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expandableListView = (ExpandableListView)findViewById(R.id.expandableListView);
        expandableListView.setAdapter(new MyExpandableListView());
    }

    class MyExpandableListView extends BaseExpandableListAdapter {
        //返回一级列表的个数
        @Override
        public int getGroupCount() {
            return groups.length;
        }
        //返回二级列表的个数
        @Override
        public int getChildrenCount(int groupPosition) {//groupPosition表示第几个一级列表
            Log.d("smyhvae", "-->" + groupPosition);
            return childs[groupPosition].length;
        }

        //返回一级列表的单个item
        @Override
        public Object getGroup(int groupPosition) {
            return groups[groupPosition];
        }

        //返回二级列表中的单个item
        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return childs[groupPosition][childPosition];
        }


        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        //每个item的id是否是固定
        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.item_group, null);
            }
            TextView tv_group = (TextView)convertView.findViewById(R.id.tv_group);
            tv_group.setText(groups[groupPosition]);
            return convertView;
        }

        @Override
        public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.item_child, null);
            }
            ImageView iv_child = (ImageView)convertView.findViewById(R.id.iv_chile);
            TextView tv_child = (TextView)convertView.findViewById(R.id.tv_child);
            tv_child.setText(childs[groupPosition][childPosition]);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "父：" + groupPosition + ", 子：" + childPosition + ", " + childs[groupPosition][childPosition], Toast.LENGTH_LONG).show();
                }
            });
            return convertView;
        }

        //二级列表中的item是否能被选中，可以返回true
        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
}
