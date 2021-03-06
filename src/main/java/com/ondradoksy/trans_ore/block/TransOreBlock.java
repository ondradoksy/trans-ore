package com.ondradoksy.trans_ore.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import java.util.List;
import java.util.Random;

public class TransOreBlock extends Block {
    public TransOreBlock(Settings settings) {
        super(settings.ticksRandomly()); // enable random ticks

    }

    @Override
    public void appendTooltip(ItemStack itemStack, BlockView world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add( new TranslatableText("block.trans_ore.trans_ore_block.tooltip").formatted(Formatting.GRAY));
    }

    // Still deprecated but it uses the same thing as saplings
    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 10.0F, false) != null  && random.nextDouble() < 0.41F) world.breakBlock(pos, true);
    }

}
