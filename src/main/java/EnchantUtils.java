import java.util.concurrent.ThreadLocalRandom;

public class EnchantUtils {

    public static boolean rollChance(double chancePercent) {
        return ThreadLocalRandom.current().nextDouble(100) < chancePercent;
    }
}
