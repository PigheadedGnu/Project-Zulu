package projectzulu.common.blocks.itemblockdeclarations;

import net.minecraft.item.Item;
import projectzulu.common.ProjectZulu_Core;
import projectzulu.common.api.ItemList;
import projectzulu.common.blocks.ItemZuluArmor;
import projectzulu.common.core.itemblockdeclaration.ItemSetDeclaration;

import com.google.common.base.Optional;

public class WhiteClothArmor extends ItemSetDeclaration {

    public final int renderIndex;

    public WhiteClothArmor(int renderIndex) {
        super(new String[] { "WhiteClothHelmet", "WhiteClothChest", "WhiteClothLegs", "WhiteClothBoots" });
        this.renderIndex = renderIndex;
    }

    @Override
    protected boolean createItem(int iD, int partIndex) {
        Item item = new ItemZuluArmor(iD, ProjectZulu_Core.desertClothMaterial, renderIndex, partIndex,
                name[partIndex].toLowerCase());

        switch (partIndex) {
        case 0:
            ItemList.whiteClothHead = Optional.of(item);
            return true;
        case 1:
            ItemList.whiteClothChest = Optional.of(item);
            return true;
        case 2:
            ItemList.whiteClothLeg = Optional.of(item);
            return true;
        case 3:
            ItemList.whiteClothBoots = Optional.of(item);
            return true;
        }
        return false;
    }

    @Override
    protected void registerItem(int partIndex) {
    }
}