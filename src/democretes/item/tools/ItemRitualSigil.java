package democretes.item.tools;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import democretes.api.altar.RitualType;
import democretes.block.altar.TileAltar;
import democretes.item.ItemMTBase;
import democretes.lib.Reference;
import democretes.utils.helper.StringHelper;

public class ItemRitualSigil extends ItemMTBase {
	
	public ItemRitualSigil() {
		setHasSubtypes(true);
		setMaxStackSize(1);
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if(world.getTileEntity(x, y, z) instanceof TileAltar) {
			TileAltar altar = (TileAltar)world.getTileEntity(x, y, z);
			RitualType type = null;
			switch(player.getHeldItem().getItemDamage()) {
			case 0:
				type = RitualType.BASIC;break;
			case 1:
				type = RitualType.ADVANCED;break;
			case 2:
				type = RitualType.COMPLEX;
			}
			altar.ritual = type;
			world.markBlockForUpdate(x, y, z);
		}
		return false;
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for(int i = 0; i < icons.length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	@Override
	public String getUnlocalizedNameInefficiently(ItemStack stack) {
		return "item." + Reference.MOD_PREFIX + ".ritual." + stack.getItemDamage();
	}
	
	public String getItemStackDisplayName(ItemStack stack) {
		String color = "";
		if(stack.getItemDamage() > 0) {
			color = StringHelper.ITALIC;
			if(stack.getItemDamage() > 1) {
				color = StringHelper.BRIGHT_BLUE + StringHelper.ITALIC;
			}
		}
		return color + super.getItemStackDisplayName(stack);
	}
	
	
	
	IIcon icons[] = new IIcon[3];
	@Override
	public void registerIcons(IIconRegister ir) {
		for(int i = 0; i < icons.length; i++) {
			String ritual = RitualType.values()[i].toString().toLowerCase();
			icons[i] = ir.registerIcon(Reference.TEXTURE_PREFIX + "sigil/sigil_" + ritual);
		}
	}

	@Override
	public IIcon getIconFromDamage(int meta) {
		return icons[meta];
	}
}
