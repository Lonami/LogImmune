package dev.lonami.logimmune;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class Buffs {
    private static Potion getBuff() {
        Potion potion = Potion.getPotionFromResourceLocation(Configuration.buffIdentifier);
        return potion == null ? MobEffects.RESISTANCE : potion;
    }

    public static void buff(EntityLivingBase living) {
        living.addPotionEffect(new PotionEffect(
                getBuff(),
                (int) (Configuration.buffDuration * 20.0),
                Configuration.buffAmplifier - 1
        ));
    }

    public static void debuff(EntityLivingBase living) {
        living.removePotionEffect(getBuff());
    }
}
