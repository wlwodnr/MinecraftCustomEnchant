import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.LivingEntity;

import java.util.List;

public class CustomEnchantEffectListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player player)) return;

        ItemStack item = player.getInventory().getItemInMainHand();
        if (!item.hasItemMeta() || !item.getItemMeta().hasLore()) return;

        List<String> lore = item.getItemMeta().getLore();
        if (lore == null) return;

        for (String line : lore) {
            CustomEnchant.fromLore(line).ifPresent(enchant -> {
                if (EnchantUtils.rollChance(enchant.getChance())) {
                    applyEnchantEffect(enchant, event);
                }
            });
        }
    }

    private void applyEnchantEffect(CustomEnchant enchant, EntityDamageByEntityEvent event) {
        switch (enchant) {
            case DESTROYER -> {
                event.setDamage(event.getDamage() + 5.0);
                event.getDamager().sendMessage("§c[파괴자] 추가 피해!");
            }
            case FROST -> {
                if (event.getEntity() instanceof LivingEntity target) {
                    target.setFreezeTicks(60);
                    event.getDamager().sendMessage("§b[서리의 손길] 상대가 느려집니다!");
                }
            }
        }
    }

}
