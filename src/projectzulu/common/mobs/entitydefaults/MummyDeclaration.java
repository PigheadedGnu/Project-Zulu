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
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.entitydeclaration.SpawnableDeclaration;
import projectzulu.common.mobs.entity.EntityMummy;
import projectzulu.common.mobs.models.ModelMammoth;
import projectzulu.common.mobs.models.ModelMummy;
import projectzulu.common.mobs.renders.RenderGenericLiving;

public class MummyDeclaration extends SpawnableDeclaration {

    public MummyDeclaration() {
        super("Mummy", EntityMummy.class, EnumCreatureType.monster);
        setSpawnProperties(5, 100, 1, 2);
        setRegistrationProperties(128, 3, true);
        setDropAmount(0, 2);

        eggColor1 = (255 << 16) + (255 << 8) + 255;
        eggColor2 = (255 << 16) + (255 << 8) + 255;

        defaultBiomesToSpawn.add(BiomeGenBase.desert.biomeName);
        defaultBiomesToSpawn.add(BiomeGenBase.desertHills.biomeName);
    }

    @Override
    public void outputDataToList(Configuration config, CustomMobData customMobData) {
        super.outputDataToList(config, customMobData);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public RenderLiving getEntityrender(Class<? extends EntityLivingBase> entityClass) {
        return new RenderGenericLiving(new ModelMummy(), 0.5f, new ResourceLocation(DefaultProps.mobKey, "mummy.png"));
    }
}
