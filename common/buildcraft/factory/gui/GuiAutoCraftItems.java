/* Copyright (c) 2016 SpaceToad and the BuildCraft team
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/. */
package buildcraft.factory.gui;

import buildcraft.lib.BCLib;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import buildcraft.factory.container.ContainerAutoCraftItems;
import buildcraft.lib.gui.GuiBC8;
import buildcraft.lib.gui.GuiIcon;
import buildcraft.lib.gui.pos.GuiRectangle;

public class GuiAutoCraftItems extends GuiBC8<ContainerAutoCraftItems> {
    private static final ResourceLocation TEXTURE_BASE = new ResourceLocation("buildcraftfactory:textures/gui/autobench_item.png");
    private static final int SIZE_X = 176, SIZE_Y = 197;
    private static final GuiIcon ICON_GUI = new GuiIcon(TEXTURE_BASE, 0, 0, SIZE_X, SIZE_Y);
    private static final GuiIcon ICON_PROGRESS = new GuiIcon(TEXTURE_BASE, SIZE_X, 0, 23, 10);
    private static final GuiRectangle RECT_PROGRESS = new GuiRectangle(90, 47, 23, 10);

    public GuiAutoCraftItems(ContainerAutoCraftItems container) {
        super(container);
        xSize = SIZE_X;
        ySize = SIZE_Y;
    }

    @Override
    protected void drawBackgroundLayer(float partialTicks) {
        ICON_GUI.drawAt(rootElement);

        drawProgress(
                RECT_PROGRESS,
                ICON_PROGRESS,
                container.tile.deltaProgress.getDynamic(partialTicks),
                1
        );

        if (BCLib.DEV) {
            fontRenderer.drawString("Start = " + container.tile.deltaProgress.getStatic(true), 10, 10, -1);
            fontRenderer.drawString("Dyn   = " + container.tile.deltaProgress.getDynamic(partialTicks), 10, 20, -1);
            fontRenderer.drawString("End   = " + container.tile.deltaProgress.getStatic(false), 10, 30, -1);
            fontRenderer.drawString("Count = " + container.tile.deltaProgress.changingEntries.size(), 10, 40, -1);
        }
        if (container.tile.currentRecipe != null) {
            RenderHelper.enableGUIStandardItemLighting();
            ItemStack output = container.tile.getOutput();
            int x = rootElement.getX() + 93;
            int y = rootElement.getY() + 27;
            this.itemRender.renderItemAndEffectIntoGUI(this.mc.player, output, x, y);
            this.itemRender.renderItemOverlayIntoGUI(this.mc.fontRenderer, output, x, y, null);
            RenderHelper.disableStandardItemLighting();
        }
    }
}
