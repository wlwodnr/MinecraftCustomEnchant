import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class AnvilEnchantListener implements Listener {

    private final JavaPlugin plugin;

    public AnvilEnchantListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent event) {
        ItemStack base = event.getInventory().getItem(0);
        ItemStack addition = event.getInventory().getItem(1);
        if (base == null || addition == null) return;

        ItemMeta addMeta = addition.getItemMeta();
        if (addMeta == null) return;

        NamespacedKey key = new NamespacedKey(plugin, "custom_enchant");
        PersistentDataContainer container = addMeta.getPersistentDataContainer();

        if (container.has(key, PersistentDataType.STRING)) {
            String enchantKey = container.get(key, PersistentDataType.STRING);
            CustomEnchant enchant = CustomEnchant.valueOf(enchantKey.toUpperCase());

            // ✅ 아이템 종류 허용 여부 검사
            if (!enchant.isAllowedOn(base.getType())) {
                event.setResult(null); // 모루 결과 없음
                return;
            }

            ItemStack result = base.clone();
            ItemMeta resultMeta = result.getItemMeta();
            resultMeta.setLore(List.of("§6커스텀 인첸트: " + enchant.getDisplayName()));
            result.setItemMeta(resultMeta);
            event.setResult(result);
        }
    }

}
