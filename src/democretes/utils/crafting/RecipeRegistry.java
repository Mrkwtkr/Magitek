package democretes.utils.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import democretes.item.ItemsMT;
import democretes.utils.crafting.AltarRecipes.AltarRecipe;
import democretes.utils.crafting.RunicRecipes.RuneRecipe;

public final class RecipeRegistry {

	public static RuneRecipe fire;
	public static RuneRecipe water;
	public static RuneRecipe earth;
	public static RuneRecipe air;
	public static RuneRecipe energy;
	public static RuneRecipe balance;
	public static RuneRecipe control;

	public static void initRunicRecipes() {
		fire = new RuneRecipe(new ItemStack(Items.blaze_powder), new ItemStack(ItemsMT.rune, 2, 0), 600, 0);
		water = new RuneRecipe(new ItemStack(Items.dye, 1, 4), new ItemStack(ItemsMT.rune, 2, 1), 600, 0);
		earth = new RuneRecipe(new ItemStack(Items.clay_ball), new ItemStack(ItemsMT.rune, 2, 2), 600, 0);
		air = new RuneRecipe(new ItemStack(Items.feather), new ItemStack(ItemsMT.rune, 2, 3), 600, 0);
		energy = new RuneRecipe(new ItemStack(Items.blaze_rod), new ItemStack(ItemsMT.rune, 1, 4), 2400, 0);
		balance = new RuneRecipe(new ItemStack(Items.magma_cream), new ItemStack(ItemsMT.rune, 1, 5), 1800, 0);
		control = new RuneRecipe(new ItemStack(Items.ender_eye), new ItemStack(ItemsMT.rune, 1, 6), 2400, 0);
		for(int i = 0; i < RunicRecipes.runeRecipes.size(); i++) {
			System.out.println(i + "recipes" + RunicRecipes.runeRecipes.get(i).getEnergyRequired());
		}
	}
	
	public static AltarRecipe rune;
	
	public static void initAltarRecipes() {
		rune = AltarRecipes.addRecipe(new ItemStack(Blocks.stone), new ItemStack(ItemsMT.material, 1, 0), 500);
	}	

	public static void initRitualRecipes() {
		
	}
	
}
