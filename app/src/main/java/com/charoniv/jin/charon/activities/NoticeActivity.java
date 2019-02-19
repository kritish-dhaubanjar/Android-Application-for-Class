package com.charoniv.jin.charon.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.charoniv.jin.charon.R;
import com.charoniv.jin.charon.adapters.NoticeAdapter;
import com.charoniv.jin.charon.notice.NoticeHandler;

public class NoticeActivity extends AppCompatActivity {

    private ListView noticeListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        noticeListView = findViewById(R.id.noticeListView);

        NoticeHandler handler = new NoticeHandler(this);
        NoticeAdapter noticeAdapter = new NoticeAdapter(this, R.layout.notice, handler.getNoticeList());
        handler.setNoticeAdapter(noticeAdapter);
        noticeListView.setAdapter(noticeAdapter);
    }
}
