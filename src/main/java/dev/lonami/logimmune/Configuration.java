package dev.lonami.logimmune;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Type;

@Config(modid = LogImmune.MODID, type = Type.INSTANCE)
public class Configuration {
    @Config.Comment({
            "The buff duration, in seconds"
    })
    @Config.Name("Buff Duration")
    @Config.RangeDouble(min = 1.0, max = 1000000.0)
    public static double buffDuration = 30.0f;

    @Config.Comment({
            "The buff amplifier as a strictly positive integer"
    })
    @Config.Name("Buff Amplifier")
    @Config.RangeInt(min = 1, max = 256)
    public static int buffAmplifier = 256;

    @Config.Comment({
            "The name of the potion effect buff to grant on login",
            "Some valid examples are: resistance, absorption, regeneration, etc.",
            "Invalid values will be interpreted as \"resistance\" instead"
    })
    @Config.Name("Buff Identifier")
    public static String buffIdentifier = "resistance";
}
