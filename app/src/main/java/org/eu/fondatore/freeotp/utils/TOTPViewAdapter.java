package org.eu.fondatore.freeotp.utils;

import org.eu.fondatore.freeotp.R;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.List;

public class TOTPViewAdapter extends ArrayAdapter<Pair<String, String>> {

    private final LayoutInflater inflater;

    public TOTPViewAdapter(Context context, List<Pair<String, String>> data) {
        super(context, android.R.layout.simple_list_item_1, data);
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.services_list_layout, parent, false);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.imageView);
            holder.serviceNameTextView = convertView.findViewById(R.id.serviceNameTextView);
            holder.totpCodeTextView = convertView.findViewById(R.id.totpCodeTextView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Pair<String, String> item = getItem(position);
        assert item != null;
        String serviceName = item.first;
        String totpCode = item.second;

        holder.serviceNameTextView.setText(serviceName);
        holder.totpCodeTextView.setText(totpCode);

        int drawableResourceId = getContext().getResources().getIdentifier(serviceName.toLowerCase(), "drawable", getContext().getPackageName());
        if (drawableResourceId != 0) {
            holder.imageView.setImageResource(drawableResourceId);
        } else {
            holder.imageView.setImageDrawable(null);
        }

        return convertView;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView serviceNameTextView;
        TextView totpCodeTextView;
    }
}
