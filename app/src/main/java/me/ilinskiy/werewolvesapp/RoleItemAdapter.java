package me.ilinskiy.werewolvesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Svyatoslav Ilinskiy on 16.09.16
 */
public class RoleItemAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    private List<Role> roles;

    public RoleItemAdapter(Context context, List<Role> roles) {
        this.roles = roles;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return roles.size();
    }

    @Override
    public Object getItem(int i) {
        return roles.get(i);
    }

    @Override
    public long getItemId(int i) {
        return getItem(i).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Role role = (Role) getItem(position);
        if (role == null) {
            throw new IllegalStateException("getItem should not return null");
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.role_item, parent, false);
            TextView nameView = (TextView) convertView.findViewById(R.id.name);
            TextView descriptionView = (TextView) convertView.findViewById(R.id.description);
            convertView.setTag(new RoleViewHolder(nameView, descriptionView));
        }
        bindView(((RoleViewHolder) convertView.getTag()), role);
        return convertView;
    }

    private void bindView(RoleViewHolder vh, Role role) {
        vh.name.setText(role.name);
        vh.description.setText(role.description);
    }

    private static class RoleViewHolder {
        public TextView name;
        public TextView description;

        public RoleViewHolder(TextView name, TextView description) {
            this.name = name;
            this.description = description;
        }
    }
}
