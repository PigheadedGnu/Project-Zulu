package projectzulu.common.mobs.entitydefaults;

import java.io.File;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.api.CustomMobData;
import projectzulu.common.core.ConfigHelper;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.DefaultSpawnable;
import projectzulu.common.mobs.entity.EntityMummy;

import com.google.common.base.Optional;

public class MummyDefault extends DefaultSpawnable{
	
	public MummyDefault(){
		super("Mummy", EntityMummy.class, EnumCreatureType.monster);		
		setSpawnProperties(5, 100, 1, 2);
		setRegistrationProperties(128, 3, true);
		setModelAndRender("projectzulu.common.mobs.models.ModelMummy", "projectzulu.common.mobs.renders.RenderGenericLiving");

		eggColor1 = (255 << 16) + (255 << 8) + 255;	
		eggColor2 = (255 << 16) + (255 << 8) + 255;
		
		defaultBiomesToSpawn.add(BiomeGenBase.desert.biomeName);
		defaultBiomesToSpawn.add(BiomeGenBase.desertHills.biomeName);
	}
	
	@Override
	public void outputDataToList(File configDirectory) {
		Configuration config = new Configuration(  new File(configDirectory, DefaultProps.configDirectory + DefaultProps.mobBiomeSpawnConfigFile) );
		config.load();
		CustomMobData customMobData = new CustomMobData(mobName, secondarySpawnRate, reportSpawningInLog);
		customMobData.creatureType = ConfigHelper.configCreatureType(config, "MOB CONTROLS."+mobName, enumCreatureType);
		ConfigHelper.userItemConfigRangeToMobData(config, "MOB CONTROLS."+mobName, customMobData);
		config.save();
		CustomEntityList.MUMMY.modData = Optional.of(customMobData);	
	}
}
