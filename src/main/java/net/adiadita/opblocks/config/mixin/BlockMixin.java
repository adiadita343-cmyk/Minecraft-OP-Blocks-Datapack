package net.adiadita.opblocks.mixin;

import net.adiadita.opblocks.config.ModConfig;
import net.adiadita.opblocks.util.GodItemGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(Block.class)
public abstract class BlockMixin {

    private static final Random RANDOM = new Random();

    @Inject(method = "afterBreak", at = @At("RETURN"))
    private void onAfterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state,
                              BlockEntity blockEntity, ItemStack tool, CallbackInfo ci) {
        if (world.isClient) return;
        if (!(world instanceof ServerWorld serverWorld)) return;
        if (player == null || state.isAir()) return;

        ModConfig config = ModConfig.getInstance();

        // 1. Always drop full stack of diamond blocks
        ItemStack diamondBlocks = new ItemStack(Items.DIAMOND_BLOCK, config.diamondBlockStackCount);
        giveOrDrop(world, player, pos, diamondBlocks);

        // 2. Chance to drop one max‑enchanted netherite god item
        if (RANDOM.nextFloat() < config.godGearDropChance) {
            ItemStack godGear = GodItemGenerator.createRandomGodItem(serverWorld.getRegistryManager());
            if (godGear != null) {
                giveOrDrop(world, player, pos, godGear);
            }
        }

        // 3. Cosmic Drop (time‑gated, per player, persistent)
        if (config.cosmicDropEnabled && player instanceof ServerPlayerEntity) {
            if (player instanceof PlayerEntityCooldownAccess access) {
                long currentTime = serverWorld.getTime();
                long lastDrop = access.ultimateopblocks$getLastCosmicDropTime();
                long cooldownTicks = config.cosmicDropCooldownMinutes * 60L * 20L;

                if (currentTime - lastDrop >= cooldownTicks) {
                    access.ultimateopblocks$setLastCosmicDropTime(currentTime);

                    ItemStack netheriteIngots = new ItemStack(Items.NETHERITE_INGOT, 64);
                    ItemStack smithingTables = new ItemStack(Items.SMITHING_TABLE, 64);

                    giveOrDrop(world, player, pos, netheriteIngots);
                    giveOrDrop(world, player, pos, smithingTables);

                    String message = String.format(config.cosmicDropBroadcastMessage,
                            player.getGameProfile().getName());
                    serverWorld.getServer().getPlayerManager().broadcast(
                            Text.literal(message), false);
                }
            }
        }
    }

    private void giveOrDrop(World world, PlayerEntity player, BlockPos pos, ItemStack stack) {
        player.getInventory().insertStack(stack);
        if (!stack.isEmpty()) {
            ItemEntity itemEntity = new ItemEntity(
                    world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, stack);
            world.spawnEntity(itemEntity);
        }
    }
}