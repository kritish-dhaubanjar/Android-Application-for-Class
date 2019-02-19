package com.charoniv.jin.charon.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.charoniv.jin.charon.R;
import com.charoniv.jin.charon.notice.Notice;

import java.util.List;

public class NoticeAdapter extends ArrayAdapter {

    private LayoutInflater layoutInflater;
    private int resId;
    private List<Notice> notices;

    public NoticeAdapter(Context context, int resId, List<Notice> notices){
        super(context, resId, notices);
        this.layoutInflater = LayoutInflater.from(context);
        this.resId = resId;
        this.notices = notices;
    }

    @Override
    public int getCount() {
        return (notices != null && notices.size()>0) ? notices.size() : 0;
    }

    public void updateAndNotify(List<Notice> notices){
        this.notices = notices;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = layoutInflater.inflate(resId, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        Notice notice = notices.get(position);

        viewHolder.sn.setText(notice.get_id() + ".");
        viewHolder.info.setText(notice.getInfo());
        viewHolder.date.setText(notice.getDate());
        return convertView;
    }

    class ViewHolder{

        TextView sn, info, date;

        public ViewHolder(View view){
            this.sn = view.findViewById(R.id.sn);
            this.info = view.findViewById(R.id.info);
            this.date = view.findViewById(R.id.date);
        }
    }
}
