package artifacts.item.wearable.necklace;

import artifacts.registry.ModGameRules;
import net.minecraft.world.entity.LivingEntity;

public class ThornPendantItem extends PendantItem {

    public ThornPendantItem() {
        super(ModGameRules.THORN_PENDANT_STRIKE_CHANCE, ModGameRules.THORN_PENDANT_COOLDOWN);
    }

    @Override
    public boolean hasNonCosmeticEffects() {
        return super.hasNonCosmeticEffects() && ModGameRules.THORN_PENDANT_MAX_DAMAGE.get() > 0;
    }

    @Override
    protected void applyEffect(LivingEntity target, LivingEntity attacker) {
        if (attacker.attackable()) {
            int minDamage = ModGameRules.THORN_PENDANT_MIN_DAMAGE.get();
            int maxDamage = ModGameRules.THORN_PENDANT_MAX_DAMAGE.get();
            if (maxDamage < minDamage) {
                minDamage = maxDamage;
            }
            int damage = minDamage + target.getRandom().nextInt(maxDamage - minDamage + 1);
            if (damage > 0) {
                attacker.hurt(target.damageSources().thorns(target), damage);
            }
        }
    }
}
