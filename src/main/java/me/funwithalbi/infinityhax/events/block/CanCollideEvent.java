package me.funwithalbi.infinityhax.events.block;

import me.funwithalbi.infinityhax.events.bus.Cancellable;
import net.minecraft.block.state.IBlockState;

public class CanCollideEvent extends Cancellable {
	public IBlockState state;
	
	public CanCollideEvent(IBlockState state) {
		this.state = state;
	}
