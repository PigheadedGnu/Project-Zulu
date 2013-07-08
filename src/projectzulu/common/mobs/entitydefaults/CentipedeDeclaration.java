package projectzulu.common.mobs.entitydefaults;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.resources.ResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ConfigHelper;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ItemGenerics;
import projectzulu.common.core.entitydeclaration.SpawnableDeclaration;
import projectzulu.common.mobs.entity.EntityCentipede;
import projectzulu.common.mobs.models.ModelCentipede;
import projectzulu.common.mobs.models.ModelFinch;
import projectzulu.common.mobs.renders.RenderGenericLiving;

public class CentipedeDeclaration extends SpawnableDeclaration{
	
	public CentipedeDeclaration(){
		super("Centipede", EntityCentipede.class, EnumCreatureType.monster);		
		setSpawnProperties(1, 35, 1, 1);
		setRegistrationProperties(128, 3, true);
        setDropAmount(0, 2);

		eggColor1 = (77 << 16) + (22 << 8) + 17;						eggColor2 = (212 << 16) + (97 << 8) + 38;
		defaultBiomesToSpawn.add(BiomeGenBase.plains.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.extremeHills.biomeName);
		defaultBiomesToSpawn.add(BiomeGenBase.forest.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.taiga.biomeName);
		defaultBiomesToSpawn.add(BiomeGenBase.swampland.biomeName); 	defaultBiomesToSpawn.add(BiomeGenBase.forestHills.biomeName);
		defaultBiomesToSpawn.add("Autumn Woods");						defaultBiomesToSpawn.add("Birch Forest");
		defaultBiomesToSpawn.add("Forested Hills");						defaultBiomesToSpawn.add("Forested Island");
		defaultBiomesToSpawn.add("Green Hills");						defaultBiomesToSpawn.add("Redwood Forest");
		defaultBiomesToSpawn.add("Lush Redwoods");						defaultBiomesToSpawn.add("Temperate Rainforest");
		defaultBiomesToSpawn.add("Woodlands");
	}
	
	@Override
	public void outputDataToList(Configuration config, CustomMobData customMobData) {
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.scrapMeat, 0, 15);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems,
				ItemGenerics.Properties.Antennae.meta(), 1);
		super.outputDataToList(config, customMobData);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public RenderLiving getEntityrender(Class<? extends EntityLivingBase> entityClass) {
        return new RenderGenericLiving(new ModelCentipede(), 0.5f, new ResourceLocation(DefaultProps.mobKey, "serpent.png"));
    }
}
