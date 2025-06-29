import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomEnchantCommand implements CommandExecutor {

    private final JavaPlugin plugin;

    public CustomEnchantCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 2) {
            sender.sendMessage("§c사용법: /CE [플레이어] [인첸트 번호]");
            return true;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null) {
            sender.sendMessage("§c해당 플레이어를 찾을 수 없습니다.");
            return true;
        }

        int id;
        try {
            id = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            sender.sendMessage("§c인첸트 번호는 숫자여야 합니다.");
            return true;
        }

        CustomEnchant.fromId(id).ifPresentOrElse(enchant -> {
            target.getInventory().addItem(CustomEnchantBook.create(
                    enchant.name().toLowerCase(), enchant.getDisplayName(), plugin
            ));
            sender.sendMessage("§a" + target.getName() + "님에게 " + enchant.getDisplayName() + " 책을 지급했습니다.");
        }, () -> sender.sendMessage("§c해당 번호에 맞는 인첸트가 없습니다."));

        return true;
    }
}
