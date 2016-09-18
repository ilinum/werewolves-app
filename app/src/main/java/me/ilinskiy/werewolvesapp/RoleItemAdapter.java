package me.ilinskiy.werewolvesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Svyatoslav Ilinskiy on 16.09.16
 */
public class RoleItemAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    private List<Role> roles;
    private TextView totalPlayersView;
    private int totalPlayers = 0;

    public RoleItemAdapter(Context context, List<Role> roles, TextView totalPlayerNumberView) {
        this.roles = roles;
        inflater = LayoutInflater.from(context);
        this.totalPlayersView = totalPlayerNumberView;
        for (Role role : roles) {
            totalPlayers += role.getPlayers();
        }
        totalPlayerNumberView.setText(String.valueOf(totalPlayers));
    }

    public List<Role> getRoles() {
        return roles;
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
            TextView rolePlayersView = (TextView) convertView.findViewById(R.id.rolePlayers);
            Button minusButton = (Button) convertView.findViewById(R.id.minusButton);
            Button plusButton = (Button) convertView.findViewById(R.id.plusButton);
            TextView showDescription = (TextView) convertView.findViewById(R.id.showDescriptionTextView);
            RoleViewHolder holder = new RoleViewHolder(nameView, descriptionView, rolePlayersView,
                    minusButton, plusButton, showDescription);
            convertView.setTag(holder);
        }
        bindView(((RoleViewHolder) convertView.getTag()), role, convertView);
        return convertView;
    }

    private void bindView(final RoleViewHolder vh, final Role role, View view) {
        vh.name.setText(role.name);
        vh.description.setText(role.description);
        if (role.isCollapsed()) {
            vh.description.setVisibility(View.GONE);
        } else {
            vh.description.setVisibility(View.VISIBLE);
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                role.setCollapsed(!role.isCollapsed());
                if (role.isCollapsed()) {
                    vh.showDescriptionView.setText(R.string.show_description);
                    vh.description.setVisibility(View.GONE);
                } else {
                    vh.showDescriptionView.setText(R.string.hide_description);
                    vh.description.setVisibility(View.VISIBLE);
                }
            }
        });
        vh.numPlayers.setText(String.valueOf(role.getPlayers()));
        vh.minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int oldValue = role.getPlayers();
                role.removePlayer();
                int newValue = role.getPlayers();
                updateTotalPlayersView(oldValue, newValue);
                vh.numPlayers.setText(String.valueOf(newValue));
            }
        });
        vh.plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int oldValue = role.getPlayers();
                role.addPlayer();
                int newValue = role.getPlayers();
                updateTotalPlayersView(oldValue, newValue);
                vh.numPlayers.setText(String.valueOf(newValue));
            }
        });
    }

    private void updateTotalPlayersView(int oldValue, int newValue) {
        if (oldValue != newValue) {
            totalPlayers += newValue - oldValue;
            totalPlayersView.setText(String.valueOf(totalPlayers));
        }
    }

    public void reset() {
        for (Role role : roles) {
            role.resetNumPlayers();
        }
        totalPlayers = 0;
        totalPlayersView.setText(String.valueOf(totalPlayers));
        notifyDataSetChanged();
    }

    private static class RoleViewHolder {
        public final Button minusButton;
        public final Button plusButton;
        public final TextView showDescriptionView;
        public final TextView name;
        public final TextView description;
        public final TextView numPlayers;

        public RoleViewHolder(TextView name, TextView description, TextView numPlayers,
                              Button minusButton, Button plusButton, TextView showDescriptionView) {
            this.name = name;
            this.description = description;
            this.numPlayers = numPlayers;
            this.minusButton = minusButton;
            this.plusButton = plusButton;
            this.showDescriptionView = showDescriptionView;
        }
    }
}
