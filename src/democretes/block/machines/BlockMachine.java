package democretes.block.machines;

import java.util.List;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import democretes.block.BlockMTBase;
import democretes.block.generators.TileRunicGenerator;
import democretes.item.ItemsMT;
import democretes.lib.Reference;
import democretes.utils.crafting.RunicHelper;

public class BlockMachine extends BlockMTBase {
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess access, int x, int y, int z) {
		if(access.getBlockMetadata(x, y, z) == 1) {
			setBlockBounds(0F, 0F, 0F, 1F, 0.75F, 1F);
		}else{
			setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
		}
	}
	
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for(int i = 0; i < 2; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if(world.getBlockMetadata(x, y, z) == 1) {
			if(world.getTileEntity(x, y, z) instanceof TileRuneConstructor) {
				TileRuneConstructor construct = (TileRuneConstructor)world.getTileEntity(x, y, z);
				if(player.getHeldItem() != null) {
					int i = player.getHeldItem().getItem() == ItemsMT.material && player.getHeldItem().getItemDamage() == 0? 0 : 1;
					ItemStack stack = player.getHeldItem();
					if(i == 1) {
						if(!RunicHelper.recipeExists(stack)) {
							return false;
						}
					}
					int size = player.isSneaking() ? stack.stackSize : 1;
					if(construct.inventory[i] == null) {
						construct.inventory[i] = stack.copy();	
						construct.inventory[i].stackSize = size;
					}else{
						construct.inventory[i].stackSize += size;
						if(construct.inventory[i].stackSize > construct.inventory[i].getMaxStackSize()) {
							size = construct.inventory[i].stackSize - construct.inventory[i].getMaxStackSize();
							construct.inventory[i].stackSize = construct.inventory[i].getMaxStackSize();
						}
					}
					player.inventory.decrStackSize(player.inventory.currentItem, size);
					return true;
				}else if((construct.inventory[0] != null || construct.inventory[1] != null) && player.isSneaking()) {
						int i = construct.inventory[0] != null ? 0 : construct.inventory[1] != null ? 1 : -1;
						if(i == -1) {
							return false;
						}
						if(!player.inventory.addItemStackToInventory(construct.inventory[i])) {
							ForgeDirection fd = ForgeDirection.getOrientation(side);
							world.spawnEntityInWorld(new EntityItem(world, x + 0.5F + fd.offsetX / 3.0F, y + 0.5F, z + 0.5F + fd.offsetZ / 3.0F, construct.inventory[i]));
						}
						construct.inventory[i] = null;
						
				}
				return true;
			}
		}
		return false;
	}
	
	
	IIcon[] constructor = new IIcon[3];
	@Override
	public void registerBlockIcons(IIconRegister ir) {
		constructor[0] = ir.registerIcon(Reference.TEXTURE_PREFIX + "constructor_bottom");
		constructor[1] = ir.registerIcon(Reference.TEXTURE_PREFIX + "constructor_top");
		constructor[2] = ir.registerIcon(Reference.TEXTURE_PREFIX + "constructor_side");
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		if(meta == 1) {
			if(side > 1) {
				return constructor[2];
			}else{
				return constructor[side];
			}
		}
		return null;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		switch(meta) {
		case 0:
			return new TilePurityInverter();
		case 1:
			return new TileRuneConstructor();
		}
		return null;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}
}
