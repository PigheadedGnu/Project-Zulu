package projectzulu.common.potion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.minecraft.item.Item;
import projectzulu.common.api.ItemList;
import projectzulu.common.api.SubItemPotionList;
import projectzulu.common.core.itemblockdeclaration.ItemDeclaration;
import projectzulu.common.potion.subitem.SubItemPotion;
import projectzulu.common.potion.subitem.SubItemPotionDamageBoost;
import projectzulu.common.potion.subitem.SubItemPotionFireResistance;
import projectzulu.common.potion.subitem.SubItemPotionHarm;
import projectzulu.common.potion.subitem.SubItemPotionHeal;
import projectzulu.common.potion.subitem.SubItemPotionInvisibility;
import projectzulu.common.potion.subitem.SubItemPotionMoveSlowdown;
import projectzulu.common.potion.subitem.SubItemPotionMoveSpeed;
import projectzulu.common.potion.subitem.SubItemPotionNightVision;
import projectzulu.common.potion.subitem.SubItemPotionPoison;
import projectzulu.common.potion.subitem.SubItemPotionRegeneration;
import projectzulu.common.potion.subitem.SubItemPotionRegistry;
import projectzulu.common.potion.subitem.SubItemPotionWeakness;

import com.google.common.base.Optional;

public class PZVanillaPotionDeclaration extends ItemDeclaration {

    public PZVanillaPotionDeclaration() {
        super("PZVanillaPotion");
    }

    @Override
    protected boolean createItem() {
        Item item = new ItemPZPotion(iD, name);
        ItemList.vanillaPotions = Optional.of(item);
        int i = 0;
        List<SubItemPotion> list = new ArrayList<SubItemPotion>();

        addToLists(item.itemID, i++, SubItemPotionList.STRENGTH, list, SubItemPotionDamageBoost.class);
        addToLists(item.itemID, i++, SubItemPotionList.REGENERATION, list, SubItemPotionRegeneration.class);
        addToLists(item.itemID, i++, SubItemPotionList.POISON, list, SubItemPotionPoison.class);
        addToLists(item.itemID, i++, SubItemPotionList.WEAKNESS, list, SubItemPotionWeakness.class);
        addToLists(item.itemID, i++, SubItemPotionList.MOVE_SPEED, list, SubItemPotionMoveSpeed.class);
        addToLists(item.itemID, i++, SubItemPotionList.MOVE_SLOW, list, SubItemPotionMoveSlowdown.class);
        addToLists(item.itemID, i++, SubItemPotionList.FIRE_RESISTANCE, list, SubItemPotionFireResistance.class);
        addToLists(item.itemID, i++, SubItemPotionList.NIGHT_VISION, list, SubItemPotionNightVision.class);
        addToLists(item.itemID, i++, SubItemPotionList.INVISIBILITY, list, SubItemPotionInvisibility.class);
        addToLists(item.itemID, i++, SubItemPotionList.HEAL, list, SubItemPotionHeal.class);
        addToLists(item.itemID, i++, SubItemPotionList.HARM, list, SubItemPotionHarm.class);

        for (SubItemPotion subItemPotion : list) {
            SubItemPotionRegistry.INSTANCE.addSubPotions(subItemPotion);
        }
        return true;
    }

    @Override
    protected void registerItem() {
        Item item = ItemList.vanillaPotions.get();
        registerSubPotions(item.itemID);
    }

    private void registerSubPotions(int itemID) {
        Collection<SubItemPotion> potions = SubItemPotionRegistry.INSTANCE.getPotions(itemID);
        for (SubItemPotion subItemPotion : potions) {
            subItemPotion.register();
        }
    }

    private void addToLists(int itemID, int subID, SubItemPotionList entry, List<SubItemPotion> registryList,
            Class<? extends SubItemPotion> potionClass) {
        try {
            SubItemPotion subItemPotion = potionClass.getConstructor(new Class[] { int.class, int.class }).newInstance(
                    new Object[] { itemID, subID });
            entry.set(subItemPotion);
            registryList.add(subItemPotion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}