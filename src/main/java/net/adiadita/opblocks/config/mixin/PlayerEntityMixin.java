package net.adiadita.opblocks.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin implements PlayerEntityCooldownAccess {

    @Unique
    private long ultimateopblocks_lastCosmicDropTime = 0L;

    @Override
    public long ultimateopblocks$getLastCosmicDropTime() {
        return ultimateopblocks_lastCosmicDropTime;
    }

    @Override
    public void ultimateopblocks$setLastCosmicDropTime(long time) {
        this.ultimateopblocks_lastCosmicDropTime = time;
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void onWriteCustomData(NbtCompound nbt, CallbackInfo ci) {
        nbt.putLong("UltimateOPBlocks_LastCosmicDropTime", ultimateopblocks_lastCosmicDropTime);
    }

    @Inject(method = "readCustomDataToNbt", at = @At("TAIL"))
    private void onReadCustomData(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("UltimateOPBlocks_LastCosmicDropTime")) {
            ultimateopblocks_lastCosmicDropTime = nbt.getLong("UltimateOPBlocks_LastCosmicDropTime");
        }
    }
}