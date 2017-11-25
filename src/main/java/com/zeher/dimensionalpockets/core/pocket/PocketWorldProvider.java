package com.zeher.dimensionalpockets.core.pocket;

import java.util.Vector;

import com.zeher.dimensionalpockets.DimensionalPockets;
import com.zeher.dimensionalpockets.core.pocket.handlers.PocketBiomeHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class PocketWorldProvider extends WorldProvider {
	
	public PocketWorldProvider() {
		hasNoSky = true;
		if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
			setSkyRenderer(new IRenderHandler() {
				@Override
				@SideOnly(Side.CLIENT)
				public void render(float partialTicks, WorldClient world, Minecraft mc) {}
			});
		}
	}

	@Override
	public IChunkGenerator createChunkGenerator() {
		return new PocketChunkGenerator(world);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Vec3d getSkyColor(Entity cameraEntity, float partialTicks) {
		 return world.getSkyColorBody(cameraEntity, partialTicks);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Vec3d getFogColor(float par1, float par2) {
		return new Vec3d(0,0,0);
	}
	
	@Override
	public String getDepartMessage() {
		return "Leaving the Dimensional Pocket...";
	}

	@Override
	public String getWelcomeMessage() {
		return "Entering the Dimensional Pocket...";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getStarBrightness(float par1) {
		return 1F;
	}

	@Override
	public Biome getBiomeForCoords(BlockPos pos) {
		return PocketBiomeHandler.getPocketBiome();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getCloudHeight() {
		return 600000F;
	}

	@SideOnly(Side.CLIENT)
    public double getVoidFogYFactor() {
		return 0.0;
	}

	@Override
	public boolean canRespawnHere() {
		return false;
	}

	@Override
	public boolean canDoLightning(Chunk chunk) {
		return false;
	}

	@Override
	public boolean canDoRainSnowIce(Chunk chunk) {
		return false;
	}

	@Override
	public boolean canSnowAt(BlockPos pos, boolean checkLight) {
		return false;
	}

	@Override
	public boolean shouldMapSpin(String entity, double x, double y, double z) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float[] calcSunriseSunsetColors(float par1, float par2) {
		return new float[] { 0.0F, 0.0F, 0.0F, 0.0F };
	}
	
	@Override
	public DimensionType getDimensionType() {
		return DimensionType.getById(DimensionalPockets.dimension_id);
	}

}
