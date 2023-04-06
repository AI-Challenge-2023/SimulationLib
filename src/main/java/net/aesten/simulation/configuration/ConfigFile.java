package net.aesten.simulation.configuration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;
import org.ini4j.Ini;
import org.ini4j.IniPreferences;

public class ConfigFile {
    Preferences prefs;

    public ConfigFile(String name) throws IOException {
        this.prefs = new IniPreferences(new Ini(new File(name)));
    }

    public int getInt(String section, String prop) {
        return this.prefs.node(section).getInt(prop, 0);
    }

    public double getDouble(String section, String prop) {
        return this.prefs.node(section).getDouble(prop, 0.0);
    }

    public String getString(String section, String prop) {
        return this.prefs.node(section).get(prop, null);
    }

    public Color getColor(String section, String prop) {
        return Color.decode(this.prefs.node(section).get(prop, null));
    }
}
