package mekanism.client.gui.element.window;

import com.mojang.blaze3d.matrix.MatrixStack;
import java.util.function.Consumer;
import mekanism.api.text.TextComponentUtil;
import mekanism.client.gui.IGuiWrapper;
import mekanism.client.gui.element.GuiRightArrow;
import mekanism.client.gui.element.slot.GuiSlot;
import mekanism.client.gui.element.slot.SlotType;
import mekanism.common.MekanismLang;

public class GuiCraftingWindow<DATA_SOURCE> extends GuiWindow {

    private final Consumer<GuiCraftingWindow<DATA_SOURCE>> onFocus;
    private final DATA_SOURCE dataSource;
    private final int index;

    public GuiCraftingWindow(IGuiWrapper gui, int x, int y, DATA_SOURCE dataSource, Consumer<GuiCraftingWindow<DATA_SOURCE>> onFocus, int index) {
        super(gui, x, y, 118, 80);
        this.dataSource = dataSource;
        this.onFocus = onFocus;
        this.index = index;
        interactionStrategy = InteractionStrategy.ALL;
        //TODO: Have this stuff sync, the container will need to always be syncing the slots just in case
        // the order of which one is open changes or stuff
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                addChild(new GuiSlot(SlotType.NORMAL, gui, relativeX + 8 + column * 18, relativeY + 18 + row * 18));
            }
        }
        addChild(new GuiRightArrow(gui, relativeX + 66, relativeY + 38).jeiCrafting());
        addChild(new GuiSlot(SlotType.NORMAL, gui, relativeX + 92, relativeY + 36));
    }

    @Override
    public void onFocused() {
        super.onFocused();
        onFocus.accept(this);
    }

    public int getIndex() {
        return index;
    }

    @Override
    public void renderForeground(MatrixStack matrix, int mouseX, int mouseY) {
        super.renderForeground(matrix, mouseX, mouseY);
        //TODO: Should this have its own translation key
        //drawTitleText(matrix, MekanismLang.CRAFTING.translate(), 5);
        //Display the index for debug purposes
        drawTitleText(matrix, TextComponentUtil.build(MekanismLang.CRAFTING, " ", index), 5);
    }
}