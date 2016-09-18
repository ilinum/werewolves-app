package me.ilinskiy.werewolvesapp;

import android.content.res.AssetManager;
import android.util.JsonReader;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Svyatoslav Ilinskiy on 17.09.16
 */
public class RoleLoader {
    public static final String ROLE_DESCRIPTION_FILE = "roles.json";
    public static final String NAME_PROPERTY = "name";
    public static final String DESCRIPTION_PROPERTY = "description";
    private static final String LOG_TAG = "RoleLoader";

    public static List<Role> loadRoles(AssetManager assetManager) {
        try (InputStream is = roleInputStream(assetManager)) {
            return readJSON(is);
        } catch (IOException e) {
            Log.e(LOG_TAG, "IOException");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static List<Role> readJSON(InputStream in) throws IOException {
        try (JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"))) {
            return readRolesIn(reader);
        }
    }

    private static List<Role> readRolesIn(JsonReader reader) throws IOException {
        List<Role> roles = new ArrayList<>();
        reader.beginArray();
        while (reader.hasNext()) {
            Role r = readRole(reader);
            if (r != null) {
                roles.add(r);
            }
        }
        reader.endArray();
        return roles;
    }

    private static Role readRole(JsonReader reader) throws IOException {
        String name = null;
        String description = null;
        reader.beginObject();
        while (reader.hasNext()) {
            String property = reader.nextName();
            switch (property) {
                case NAME_PROPERTY:
                    name = reader.nextString();
                    break;
                case DESCRIPTION_PROPERTY:
                    description = reader.nextString();
                    break;
                default:
                    Log.w(LOG_TAG, "Unknown property: " + property);
                    break;
            }
        }
        if (name == null || description == null) {
            Log.w(LOG_TAG, "name: " + name + ", description: " + description);
            return null;
        }
        reader.endObject();
        return new Role(name, description);
    }

    private static InputStream roleInputStream(AssetManager assetManager) throws IOException {
        return assetManager.open(ROLE_DESCRIPTION_FILE);
    }
}
