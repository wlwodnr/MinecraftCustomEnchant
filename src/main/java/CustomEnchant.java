import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum CustomEnchant {
    DESTROYER(1, "파괴자 I", 30.0, List.of(
            Material.WOODEN_SWORD, Material.STONE_SWORD,
            Material.IRON_SWORD, Material.GOLDEN_SWORD,
            Material.DIAMOND_SWORD, Material.NETHERITE_SWORD
    )),
    FROST(2, "서리의 손길 I", 20.0, List.of(
            Material.IRON_SWORD, Material.DIAMOND_SWORD
    ));

    private final int id;
    private final String displayName;
    private final double chance;
    private final List<Material> allowedItems;

    CustomEnchant(int id, String displayName, double chance, List<Material> allowedItems) {
        this.id = id;
        this.displayName = displayName;
        this.chance = chance;
        this.allowedItems = allowedItems;
    }

    public int getId() { return id; }

    public String getDisplayName() { return displayName; }

    public double getChance() { return chance; }

    public List<Material> getAllowedItems() { return allowedItems; }

    public static Optional<CustomEnchant> fromId(int id) {
        return Arrays.stream(values()).filter(e -> e.id == id).findFirst();
    }

    public static Optional<CustomEnchant> fromLore(String line) {
        return Arrays.stream(values()).filter(e -> line.contains(e.displayName)).findFirst();
    }

    public boolean isAllowedOn(Material material) {
        return allowedItems.contains(material);
    }
}
