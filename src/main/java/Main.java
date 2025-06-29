import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("CE").setExecutor(new CustomEnchantCommand(this));
        getServer().getPluginManager().registerEvents(new AnvilEnchantListener(this), this);
        getServer().getPluginManager().registerEvents(new CustomEnchantEffectListener(), this);
    }
}