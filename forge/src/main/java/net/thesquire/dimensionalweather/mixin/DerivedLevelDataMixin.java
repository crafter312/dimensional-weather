package net.thesquire.dimensionalweather.mixin;

import net.minecraft.world.level.storage.WorldData;
import net.minecraft.world.level.storage.ServerLevelData;
import net.minecraft.world.level.storage.DerivedLevelData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DerivedLevelData.class)
public abstract class DerivedLevelDataMixin {

    private int clearWeatherTime;
    private int rainTime;
    private int thunderTime;
    private boolean isRaining;
    private boolean isThundering;

    @Inject(method = "<init>(Lnet/minecraft/world/level/storage/WorldData;Lnet/minecraft/world/level/storage/ServerLevelData;)V", at = @At(value = "TAIL"))
    public void onInit(WorldData worldData, ServerLevelData pWrapped, CallbackInfo ci) {
        this.clearWeatherTime = pWrapped.getClearWeatherTime();
        this.rainTime = pWrapped.getRainTime();
        this.thunderTime = pWrapped.getThunderTime();
        this.isRaining = pWrapped.isRaining();
        this.isThundering = pWrapped.isThundering();
    }

    @Inject(method = "getClearWeatherTime()I", at = @At(value = "HEAD"), cancellable = true)
    public void onGetClearWeatherTime(CallbackInfoReturnable<Integer> ci) {
        ci.setReturnValue(this.clearWeatherTime);
    }

    @Inject(method = "getRainTime()I", at = @At(value = "HEAD"), cancellable = true)
    public void onGetRainTime(CallbackInfoReturnable<Integer> ci) {
        ci.setReturnValue(this.rainTime);
    }

    @Inject(method = "getThunderTime()I", at = @At(value = "HEAD"), cancellable = true)
    public void onGetThunderTime(CallbackInfoReturnable<Integer> ci) {
        ci.setReturnValue(this.thunderTime);
    }

    @Inject(method = "isRaining()Z", at = @At(value = "HEAD"), cancellable = true)
    public void onIsRaining(CallbackInfoReturnable<Boolean> ci) {
        ci.setReturnValue(this.isRaining);
    }

    @Inject(method = "isThundering()Z", at = @At(value = "HEAD"), cancellable = true)
    public void onIsThundering(CallbackInfoReturnable<Boolean> ci) {
        ci.setReturnValue(this.isThundering);
    }

    @Inject(method = "setClearWeatherTime(I)V", at = @At("HEAD"))
    public void onSetClearWeatherTime(int clearWeatherTime, CallbackInfo ci) {
        this.clearWeatherTime = clearWeatherTime;
    }

    @Inject(method = "setRainTime(I)V", at = @At("HEAD"))
    public void onSetRainTime(int rainTime, CallbackInfo ci) {
        this.rainTime = rainTime;
    }

    @Inject(method = "setThunderTime(I)V", at = @At("HEAD"))
    public void onSetThunderTime(int thunderTime, CallbackInfo ci) {
        this.thunderTime = thunderTime;
    }

    @Inject(method = "setRaining(Z)V", at = @At("HEAD"))
    public void onSetRaining(boolean isRaining, CallbackInfo ci) {
        this.isRaining = isRaining;
    }

    @Inject(method = "setThundering(Z)V", at = @At("HEAD"))
    public void onSetThundering(boolean isThundering, CallbackInfo ci) {
        this.isThundering = isThundering;
    }

}
