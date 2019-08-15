package dev.lonami.logimmune;


import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class BuffMessageHandler implements IMessageHandler<BuffMessage, IMessage> {
    @Override public IMessage onMessage(BuffMessage message, MessageContext ctx) {
        if (ctx.side == Side.CLIENT) {
            LogImmune.isBuffed = message.buffed;
        } else if (ctx.side == Side.SERVER) {
            EntityPlayerMP player = ctx.getServerHandler().player;
            player.getServerWorld().addScheduledTask(() -> {
                if (message.buffed) {
                    Buffs.buff(player);
                } else {
                    Buffs.debuff(player);
                }
            });
        }
        return null;
    }
}
