package net.adiadita.opblocks.util;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.component.type.UnbreakableComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GodItemGenerator {

    private static final List<Item> GOD_ITEMS = List.of(
            Items.NETHERITE_SWORD,
            Items.NETHERITE_PICKAXE,
            Items.NETHERITE_AXE,
            Items.NETHERITE_SHOVEL,
            Items.NETHERITE_HOE,
            Items.NETHERITE_HELMET,
            Items.NETHERITE_CHESTPLATE,
            Items.NETHERITE_LEGGINGS,
            Items.NETHERITE_BOOTS
    );

    public static ItemStack createRandomGodItem(RegistryWrapper.WrapperLookup registryLookup) {
        Item randomItem = GOD_ITEMS.get(ThreadLocalRandom.current().nextInt(GOD_ITEMS.size()));
        ItemStack stack = new ItemStack(randomItem);
        applyMaxEnchantments(stack, registryLookup);
        stack.set(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(true));
        return stack;
    }

    private static void applyMaxEnchantments(ItemStack stack, RegistryWrapper.WrapperLookup registryLookup) {
        RegistryWrapper.Impl<Enchantment> enchantmentRegistry =
                registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);

        Item item = stack.getItem();

        if (item == Items.NETHERITE_SWORD) {
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.SHARPNESS), 255);
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.LOOTING), 255);
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.UNBREAKING), 255);
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.FIRE_ASPECT), 255);
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.KNOCKBACK), 255);
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.SWEEPING_EDGE), 255);
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.MENDING), 1);
        } else if (item == Items.NETHERITE_AXE) {
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.SHARPNESS), 255);
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.EFFICIENCY), 255);
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.UNBREAKING), 255);
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.FORTUNE), 255);
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.MENDING), 1);
        } else if (item == Items.NETHERITE_PICKAXE) {
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.EFFICIENCY), 255);
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.FORTUNE), 255);
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.UNBREAKING), 255);
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.MENDING), 1);
        } else if (item == Items.NETHERITE_SHOVEL) {
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.EFFICIENCY), 255);
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.FORTUNE), 255);
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.UNBREAKING), 255);
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.MENDING), 1);
        } else if (item == Items.NETHERITE_HOE) {
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.EFFICIENCY), 255);
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.FORTUNE), 255);
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.UNBREAKING), 255);
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.MENDING), 1);
        } else if (item == Items.NETHERITE_HELMET) {
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.PROTECTION), 255);
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.UNBREAKING), 255);
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.MENDING), 1);
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.AQUA_AFFINITY), 1);
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.RESPIRATION), 255);
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.THORNS), 255);
        } else if (item == Items.NETHERITE_CHESTPLATE) {
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.PROTECTION), 255);
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.UNBREAKING), 255);
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.MENDING), 1);
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.THORNS), 255);
        } else if (item == Items.NETHERITE_LEGGINGS) {
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.PROTECTION), 255);
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.UNBREAKING), 255);
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.MENDING), 1);
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.THORNS), 255);
        } else if (item == Items.NETHERITE_BOOTS) {
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.PROTECTION), 255);
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.UNBREAKING), 255);
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.MENDING), 1);
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.DEPTH_STRIDER), 255);
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.FEATHER_FALLING), 255);
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.SOUL_SPEED), 255);
            addEnchantment(stack, enchantmentRegistry.getOrThrow(Enchantments.THORNS), 255);
        }
    }

    private static void addEnchantment(ItemStack stack, Enchantment enchantment, int level) {
        ItemEnchantmentsComponent existing = stack.getOrDefault(DataComponentTypes.ENCHANTMENTS,
                ItemEnchantmentsComponent.DEFAULT);
        ItemEnchantmentsComponent.Builder builder = new ItemEnchantmentsComponent.Builder(existing);
        builder.add(enchantment, level);
        stack.set(DataComponentTypes.ENCHANTMENTS, builder.build());
    }
}