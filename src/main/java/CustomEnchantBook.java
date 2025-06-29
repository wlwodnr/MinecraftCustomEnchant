import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;


import java.util.List;

public class CustomEnchantBook {

    public static ItemStack create(String enchantKey, String displayName, JavaPlugin plugin) {
        ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta meta = book.getItemMeta();

        meta.setDisplayName(displayName);
        meta.setLore(List.of("§7이 책을 모루에 사용하면", "§6" + displayName + "§7 인첸트를 부여합니다."));
        NamespacedKey key = new NamespacedKey(plugin, "custom_enchant");
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, enchantKey);

        book.setItemMeta(meta);
        return book;
    }
}
