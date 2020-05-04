package thermos;

import org.bukkit.configuration.file.YamlConfiguration;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.cauldron.configuration.BoolSetting;
import net.minecraftforge.cauldron.configuration.ConfigBase;
import net.minecraftforge.cauldron.configuration.Setting;
import net.minecraftforge.cauldron.configuration.StringSetting;
import net.minecraftforge.cauldron.configuration.IntSetting;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import net.minecraft.block.Block;
import org.apache.commons.lang3.reflect.FieldUtils;

public class ThermosConfig extends ConfigBase {
    public BoolSetting commandEnable = new BoolSetting(this, "command.enable", true, "Enable Thermos command");
    public BoolSetting opConsoleOnly = new BoolSetting(this, "op.consoleonly", false, "Set the OP command to only be allowed to run in console");
    public BoolSetting updatecheckerEnable = new BoolSetting(this, "updatechecker.enable", true, "Enable Thermos update checker");
    public BoolSetting updatecheckerQuiet = new BoolSetting(this, "updatechecker.quiet", false, "Print less info during update");

    public BoolSetting loggingMaterialInjection = new BoolSetting(this, "logging.materialInjection", false, "Log material injection event");
    public BoolSetting loggingClientModList = new BoolSetting(this, "logging.clientModList", true, "Print client's mod list during attempt to join");
        
    public BoolSetting commonAllowNetherPortal = new BoolSetting(this, "common.allowNetherPortalBesidesOverworld", false, "Allow nether portals in dimensions besides overworld");

    public BoolSetting enable_oversizedchunk = new BoolSetting(this, "command.enable_oversizedchunk", true, "Enable Oversized Chunk to be saved");

    public IntSetting chunk_cache_size = new IntSetting(this,"command.chunk_cache_size", 256, "Size of cached chunk");
    
    public ThermosConfig() {
        super("thermos.yml", "thermos");
        register(commandEnable);
        register(updatecheckerEnable);
        register(updatecheckerQuiet);
        register(loggingMaterialInjection);
        register(loggingClientModList);
        register(commonAllowNetherPortal);
        register(opConsoleOnly);
        register(enable_oversizedchunk);
        register(chunk_cache_size);
        load();
    }


    

    private void register(Setting<?> setting) {
        settings.put(setting.path, setting);
    }

    @Override
    public void registerCommands() {
        if (commandEnable.getValue()) {
            super.registerCommands();
        }
    }

    @Override
    protected void addCommands() {
        commands.put(commandName, new ThermosCommand());
    }

    @Override
    protected void load() {
        try {
            config = YamlConfiguration.loadConfiguration(configFile);
            String header = "";
            for (Setting<?> toggle : settings.values()) {
                if (!toggle.description.equals(""))
                    header += "Setting: " + toggle.path + " Default: "
                            + toggle.def + " # " + toggle.description + "\n";

                config.addDefault(toggle.path, toggle.def);
                settings.get(toggle.path).setValue(
                        config.getString(toggle.path));
            }
            config.options().header(header);
            config.options().copyDefaults(true);
            save();
        } catch (Exception ex) {
            MinecraftServer.getServer().logSevere(
                    "Could not load " + this.configFile);
            ex.printStackTrace();
        }
    }
}