package democretes.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import democretes.Magitek;
import democretes.api.block.BlockInfo;
import democretes.api.block.IBlockDebug;
import democretes.api.macht.IMachtStorage;
import democretes.api.purity.IPurityHandler;

public class BlockMTBase extends BlockContainer implements IBlockDebug {

	public BlockMTBase() {
		super(Material.iron);
		setCreativeTab(Magitek.tabMT);
		setHardness(2F);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return null;
	}

	@Override
	public BlockInfo getInfo(EntityPlayer player, int x, int y, int z) {
		BlockInfo info = new BlockInfo(player, x, y, z);
		TileEntity tile = player.worldObj.getTileEntity(x, y, z);
		if(tile instanceof IPurityHandler) {
			info.setPurity(((IPurityHandler)tile).getPurity());
		}
		if(tile instanceof IMachtStorage) {
			info.setMacht(((IMachtStorage)tile).getMachtStored());
		}
		return info;
	}

	

}
