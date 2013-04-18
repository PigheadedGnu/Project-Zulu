package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.api.BlockList;
import projectzulu.common.blocks.BlockCampfire;
import projectzulu.common.blocks.ItemCampFire;
import projectzulu.common.blocks.RenderCampFire;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.core.itemblockdeclaration.BlockDeclaration;

import com.google.common.base.Optional;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class CampfireDeclaration extends BlockDeclaration {

    public CampfireDeclaration() {
        super("Campfire");
    }

    @Override
    protected boolean createBlock(int iD) {
        BlockList.campfire = Optional.of(new BlockCampfire(iD).setUnlocalizedName(DefaultProps.blockKey + ":"
                + name.toLowerCase()));
        return true;
    }

    @Override
    protected void registerBlock() {
        Block block = BlockList.campfire.get();
        GameRegistry.registerBlock(block, ItemCampFire.class, name.toLowerCase());
        LanguageRegistry.addName(block, "Campfire");
        for (BlockCampfire.Type type : BlockCampfire.Type.values()) {
            LanguageRegistry.addName(new ItemStack(block, 1, type.meta()), type.displayName());
        }
    }

    @Override
    protected void clientRegisterBlock() {
        ProjectZulu_Core.campFireRenderID = ProjectZulu_Core.campFireRenderID == -1 ? RenderingRegistry
                .getNextAvailableRenderId() : ProjectZulu_Core.campFireRenderID;
        RenderingRegistry.registerBlockHandler(ProjectZulu_Core.campFireRenderID, new RenderCampFire());
        ProjectZuluLog.info("Campfire Render ID Registed to %s", ProjectZulu_Core.campFireRenderID);
    }
}