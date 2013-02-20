package projectzulu.common.mobs.entity;

import java.util.EnumSet;

import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import projectzulu.common.api.CustomEntityList;
import projectzulu.common.core.DefaultProps;
import projectzulu.common.core.ProjectZuluLog;
import projectzulu.common.mobs.entityai.EntityAIFollowParent;
import projectzulu.common.mobs.entityai.EntityAIHurtByTarget;
import projectzulu.common.mobs.entityai.EntityAIMate;
import projectzulu.common.mobs.entityai.EntityAINearestAttackableTarget;
import projectzulu.common.mobs.entityai.EntityAIPanic;
import projectzulu.common.mobs.entityai.EntityAITempt;
import projectzulu.common.mobs.entityai.EntityAIWander;

public class EntityFrog extends EntityGenericAnimal implements IAnimals {	
	
	public EntityFrog(World par1World){
		super(par1World);
		setSize(0.5f, 0.5f);
		
		this.moveSpeed = 0.3f;
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIPanic(this, this.moveSpeed, true));

//		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, this.moveSpeed, false));
//		this.tasks.addTask(4, new EntityAIFollowOwner(this, this.moveSpeed,	10.0F, 2.0F));

		this.tasks.addTask(5, new EntityAIMate(this, this.moveSpeed, true));
		this.tasks.addTask(6, new EntityAITempt(this, this.moveSpeed, Item.spiderEye.itemID, false, true));
		
		
		this.tasks.addTask(7, new EntityAIFollowParent(this, this.moveSpeed));		
		this.tasks.addTask(9, new EntityAIWander(this, this.moveSpeed, 40, true));

		this.targetTasks.addTask(3,	new EntityAIHurtByTarget(this, false, false));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EnumSet.of(EntityStates.attacking, EntityStates.looking), EntityPlayer.class, 16.0F, 0, true));
//		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityLiving.class, 16.0F, 0, false, true, IMob.mobSelector));

	}

	@Override
	protected int getAttackStrength(World par1World) {
		switch (par1World.difficultySetting) {
		case 0:
			return 2; 
		case 1:
			return 2; 
		case 2:
			return 3; 
		case 3:
			return 4; 
		default:
			return 3;
		}
	}

	@Override
	public String getTexture() {

		this.texture = DefaultProps.mobDiretory + "Frog.png";

		return super.getTexture();
	}


	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	@Override
	public boolean getCanSpawnHere() {
		int var1 = MathHelper.floor_double(this.posX);
		int var2 = MathHelper.floor_double(this.boundingBox.minY);
		int var3 = MathHelper.floor_double(this.posZ);
		boolean wasSuccesful = false;
		
		if (CustomEntityList.frog.get().secondarySpawnRate - rand.nextInt(100) >= 0 && super.getCanSpawnHere()
				&& worldObj.canBlockSeeTheSky(var1, var2, var3)){
			wasSuccesful = true;
		}
		
		if(CustomEntityList.frog.get().reportSpawningInLog){
			if(wasSuccesful){
				ProjectZuluLog.info("Successfully spawned %s at X:%s Y:%s Z:%s in %s",getEntityName(),var1,var2,var3,worldObj.getBiomeGenForCoords(var1, var3));
			}else{
				ProjectZuluLog.info("Failed to spawn %s at X:%s Y:%s Z:%s in %s, Spawning Location Inhospitable",getEntityName(),var1,var2,var3,worldObj.getBiomeGenForCoords(var1, var3));
			}
		}
		return wasSuccesful;
	}
	
	@Override
	public int getMaxHealth(){return 10;}

	/**
	 * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
	 */
	@Override
	public int getTotalArmorValue(){return 0;}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	@Override
	protected String getLivingSound(){return "sounds.frogliving";}
	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	@Override
	protected String getHurtSound(){ return "sounds.froghurt"; }

	@Override
	protected void updateAITick() {
		super.updateAITick();
	}
	
	@Override
	public boolean isValidBreedingItem(ItemStack itemStack) {
		if(itemStack != null && itemStack.getItem().itemID == Item.spiderEye.itemID){
			return true;
		}else{
			return super.isValidBreedingItem(itemStack);
		}
	}
	
	@Override
	protected boolean shouldPanic() {
		return true;
	}
	@Override
	public boolean shouldNotifySimilar(EntityPlayer attackingPlayer) {
		return true;
	}
	/**
	 * Plays step sound at given x, y, z for the entity
	 */
	@Override
	protected void playStepSound(int par1, int par2, int par3, int par4){ /* Does Not Play a Step Sound */ }

	/**
	 * Drop 0-2 items of this living's type
	 */
	protected void dropFewItems(boolean par1, int par2) {
		int var3 = rand.nextInt(1 + par2);
		for (int i = 0; i < var3; i++) {
			ItemStack loot = CustomEntityList.frog.get().getLootItem(rand);
			if(loot != null){
				entityDropItem(loot, 1);
			}
		}
	}

}