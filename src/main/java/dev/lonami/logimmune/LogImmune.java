package dev.lonami.logimmune;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.MovementInput;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Logger;

@Mod(modid = LogImmune.MODID, name = LogImmune.NAME, version = LogImmune.VERSION)
public class LogImmune
{
    public static final String MODID = "logimmune";
    public static final String NAME = "Log Immune";
    public static final String VERSION = "0.1";

    public static Logger logger;

    // used only client-side (each client knows if its player is buffed or not)
    // the server does not really need to know who is buffed or not so there is
    // no need to store that
    public static boolean isBuffed;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        isBuffed = false;
        MinecraftForge.EVENT_BUS.register(this);

        // The client needs to know when a player gains the buffs
        PacketHandler.INSTANCE.registerMessage(BuffMessageHandler.class, BuffMessage.class, 0, Side.CLIENT);

        // The server needs to know when a player should have the buffs removed
        PacketHandler.INSTANCE.registerMessage(BuffMessageHandler.class, BuffMessage.class, 0, Side.SERVER);
        logger.info("Successfully initialized");
    }

    // Logical Server

    @SubscribeEvent
    public void onLogin(PlayerEvent.PlayerLoggedInEvent event) {
        buffPlayer(event.player);
    }

    @SubscribeEvent
    public void onDimensionChange(PlayerEvent.PlayerChangedDimensionEvent event) {
        buffPlayer(event.player);
    }

    @SubscribeEvent
    public void onRespawn(PlayerEvent.PlayerRespawnEvent event) {
        buffPlayer(event.player);
    }

    private void buffPlayer(EntityPlayer player) {
        if (!player.getEntityWorld().isRemote) {
            Buffs.buff(player);
            PacketHandler.INSTANCE.sendTo(new BuffMessage(true), (EntityPlayerMP) player);
        }
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(MODID)) {
            ConfigManager.sync(MODID, Config.Type.INSTANCE);
        }
    }

    // Physical Client

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onMoved(InputUpdateEvent event) {
        if (!isBuffed) {
            return;
        }

        MovementInput mi = event.getMovementInput();
        if (mi.forwardKeyDown || mi.backKeyDown || mi.leftKeyDown || mi.rightKeyDown) {
            isBuffed = false;
            PacketHandler.INSTANCE.sendToServer(new BuffMessage(false));
        }
    }
}
