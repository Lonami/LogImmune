package dev.lonami.logimmune;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class BuffMessage implements IMessage {
    public boolean buffed;

    public BuffMessage() { /* required default constructor */ }

    public BuffMessage(boolean buffed) {
        this.buffed = buffed;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(buffed);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        buffed = buf.readBoolean();
    }
}
