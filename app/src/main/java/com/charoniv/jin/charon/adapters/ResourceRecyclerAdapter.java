package com.charoniv.jin.charon.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.charoniv.jin.charon.BuildConfig;
import com.charoniv.jin.charon.Downloader;
import com.charoniv.jin.charon.R;
import com.charoniv.jin.charon.datasource.Resource;

import java.io.File;
import java.util.List;

public class ResourceRecyclerAdapter extends RecyclerView.Adapter<ResourceRecyclerAdapter.ResourceViewHolder> {

    private static final String TAG = "ResourceRecyclerAdapter";
    private LayoutInflater layoutInflater;
    private List<Resource> resources;
    private Resources resourcesInstance;
    private final Context context;

    public ResourceRecyclerAdapter(Context context, List<Resource> resources) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.resources = resources;
        resourcesInstance = context.getResources();
    }

    @NonNull
    @Override
    public ResourceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.loaded_item, viewGroup, false);
        return new ResourceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ResourceViewHolder resourceViewHolder, int i) {
        final Resource resource = resources.get(i);
        String title = resource.getSn() + ". " + resource.getTitle();
        resourceViewHolder.title.setText(title);
        resourceViewHolder.date.setText(resource.getDate());
        resourceViewHolder.progressBar.setVisibility(View.INVISIBLE);

        if(resource.getType().equals("L")){
            resourceViewHolder.title.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
        }

        /*final File file = new File(Environment.getExternalStorageDirectory() + "/Download/" + resource.getFilename() + "." + resource.getExtension());*/
        final File path = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "");
        final File file = new File(path, resource.getFilename() + "." + resource.getExtension() );
        Log.d(TAG, "onBindViewHolder: " + Uri.fromFile(file));

        if(file.exists()){
            resourceViewHolder.download.setText(resourcesInstance.getText(R.string.action_open));
            resourceViewHolder.download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileprovider", file);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(uri, "application/pdf");
                    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    context.startActivity(intent);

                    /*Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.fromFile(file), "application/pdf");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    context.startActivity(intent);
                    */
                }
            });
        }else{
            resourceViewHolder.download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Downloader downloader = new Downloader(context, resourceViewHolder.progressBar, resourceViewHolder.download, resource.getFilename(), resource.getExtension());
                    downloader.execute(resource.getUrl());
                }
            });
        }
    }

    public void onRequest(List<Resource> resources){
        this.resources = resources;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (resources != null && resources.size()>0) ? resources.size() : 0;
    }

    static class ResourceViewHolder extends RecyclerView.ViewHolder{

        TextView title, date;
        ProgressBar progressBar;
        Button download;
        ConstraintLayout constraintLayout;

        ResourceViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            date = itemView.findViewById(R.id.date);
            progressBar = itemView.findViewById(R.id.progressBar);
            download = itemView.findViewById(R.id.load);
            constraintLayout = itemView.findViewById(R.id.resource_item);
        }
    }
}
