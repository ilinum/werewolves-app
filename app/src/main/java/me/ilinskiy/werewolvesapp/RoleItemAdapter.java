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
        }
        bindView(convertView, role);
        return convertView;
    }

    private void bindView(View convertView, Role role) {
        ((TextView) convertView.findViewById(R.id.name)).setText(role.name);
        ((TextView) convertView.findViewById(R.id.description)).setText(role.description);
    }
}
