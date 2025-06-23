package net.gaelfe18.extrasmelterythings.component;

import com.mojang.serialization.Codec;
import net.gaelfe18.extrasmelterythings.ExtraSmelteryThings;
import net.minecraft.core.component.DataComponentType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.UnaryOperator;

public class ModDataComponents {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
            DeferredRegister.createDataComponents(ExtraSmelteryThings.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<String>> MOLDING_TYPE = register("molding_type",
            stringBuilder -> stringBuilder.persistent(Codec.STRING));



    private static <T>DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name, UnaryOperator<DataComponentType.Builder<T>> builderUnaryOperator){
        return DATA_COMPONENT_TYPES.register(name, () -> builderUnaryOperator.apply(DataComponentType.builder()).build());
    }

    public static void register(IEventBus eventBus){
        DATA_COMPONENT_TYPES.register(eventBus);
    }
}
