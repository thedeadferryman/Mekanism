package mekanism.common.block.basic;

import mekanism.api.block.IHasTileEntity;
import mekanism.common.block.interfaces.IHasGui;
import mekanism.common.inventory.container.ContainerProvider;
import mekanism.common.inventory.container.tile.InductionMatrixContainer;
import mekanism.common.tile.TileEntityInductionCasing;
import mekanism.common.tile.base.MekanismTileEntityTypes;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntityType;

public class BlockInductionCasing extends BlockBasicMultiblock implements IHasTileEntity<TileEntityInductionCasing>, IHasGui<TileEntityInductionCasing> {

    @Override
    public INamedContainerProvider getProvider(TileEntityInductionCasing tile) {
        return new ContainerProvider("mekanism.container.induction_matrix", (i, inv, player) -> new InductionMatrixContainer(i, inv, tile));
    }

    @Override
    public TileEntityType<TileEntityInductionCasing> getTileType() {
        return MekanismTileEntityTypes.INDUCTION_CASING.getTileEntityType();
    }
}