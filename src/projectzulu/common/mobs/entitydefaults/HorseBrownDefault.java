package projectzulu.common.mobs.entitydefaults;

import java.io.File;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.api.ItemList;
import projectzulu.common.core.ConfigHelper;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.DefaultSpawnable;
import projectzulu.common.core.ItemGenerics;
import net.minecraftforge.common.Configuration;
import projectzulu.common.mobs.entity.EntityHorseBrown;
import projectzulu.common.mobs.models.ModelHorse;

import com.google.common.base.Optional;

public class HorseBrownDefault extends DefaultSpawnable{
	
	public HorseBrownDefault(){
		super("Horse Brown", EntityHorseBrown.class);		
		setSpawnProperties(EnumCreatureType.creature, 5, 100, 1, 2);
		setRegistrationProperties(128, 3, true);
		setModelAndRender(ModelHorse.class, "projectzulu.common.mobs.renders.RenderGenericLiving");

		eggColor1 =  (137 << 16) + (78 << 8) + 0;						eggColor2 = (81 << 16) + (46 << 8) + 0;
		
		defaultBiomesToSpawn.add(BiomeGenBase.plains.biomeName); 		defaultBiomesToSpawn.add(BiomeGenBase.forest.biomeName); 		
		defaultBiomesToSpawn.add(BiomeGenBase.forestHills.biomeName); 		
		defaultBiomesToSpawn.add("Autumn Woods");						defaultBiomesToSpawn.add("Birch Forest");
		defaultBiomesToSpawn.add("Forested Hills");						defaultBiomesToSpawn.add("Forested Island");
		defaultBiomesToSpawn.add("Green Hills");						defaultBiomesToSpawn.add("Redwood Forest");
		defaultBiomesToSpawn.add("Lush Redwoods");						defaultBiomesToSpawn.add("Temperate Rainforest");
		defaultBiomesToSpawn.add("Woodlands");
	}
	
	@Override
	public void outputDataToList(File configDirectory) {
		Configuration config = new Configuration(  new File(configDirectory, DefaultProps.configDirectory + DefaultProps.mobBiomeSpawnConfigFile) );
		config.load();
		CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, Item.beefRaw, 0, 10);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.scrapMeat, 0, 10);
		ConfigHelper.configDropToMobData(config, "MOB CONTROLS."+mobName, customMobData, ItemList.genericCraftingItems1,
				ItemGenerics.Properties.LargeHeart.meta(), 4);
		config.save();
		CustomEntityList.horseBrown = Optional.of(customMobData);	
	}
}