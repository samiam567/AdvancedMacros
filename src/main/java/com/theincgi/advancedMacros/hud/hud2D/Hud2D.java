package com.theincgi.advancedMacros.hud.hud2D;

import org.luaj.vm2_v3_0_1.LuaTable;
import org.luaj.vm2_v3_0_1.LuaValue;
import org.luaj.vm2_v3_0_1.Varargs;
import org.luaj.vm2_v3_0_1.lib.VarArgFunction;
import org.luaj.vm2_v3_0_1.lib.ZeroArgFunction;

import com.theincgi.advancedMacros.AdvancedMacros;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public class Hud2D extends LuaTable{
	public Hud2D() {
		//rectangle, image, easy progress bar, text[, canvas?]
		
		this.set("newRectangle", new VarArgFunction() {
			@Override
			public Varargs invoke(Varargs var) {
				Hud2D_Rectangle rect = new Hud2D_Rectangle();
				rect.x = (float) var.optdouble(1, 0);
				rect.y = (float) var.optdouble(2, 0);
				rect.wid = (float) var.optdouble(3, 0);
				rect.hei = (float) var.optdouble(4, 0);
				return rect.controls;
			}
		});
		this.set("newBox", new VarArgFunction() {
			@Override
			public Varargs invoke(Varargs var) {
				Hud2D_Box box = new Hud2D_Box();
				box.x = (float) var.optdouble(1, 0);
				box.y = (float) var.optdouble(2, 0);
				box.wid = (float) var.optdouble(3, 0);
				box.hei = (float) var.optdouble(4, 0);
				box.thickness = (float) var.optdouble(5, 1);
				return box.controls;
			}
		});
		this.set("newImage", new VarArgFunction() {
			@Override
			public Varargs invoke(Varargs var) {
				Hud2D_Image img = new Hud2D_Image();
				img.setTexture(var.optvalue(1, LuaValue.NIL));
				img.x = (float) var.optdouble(2, 0);
				img.y = (float) var.optdouble(3, 0);
				img.wid = (float) var.optdouble(4, 0);
				img.hei = (float) var.optdouble(5, 0);
				return img.controls;
			}
		});
		//this.set("newProgressBar", new Hud2D_ProgressBar());
		this.set("newText", new VarArgFunction() {
			@Override
			public LuaValue call() {
				return new Hud2D_Text().getControls();
			}
			@Override
			public Varargs invoke(Varargs var) {
				Hud2D_Text text = new Hud2D_Text();
				text.text = var.optjstring(1, "");
				text.x = (float) var.optdouble(2, 0);
				text.y = (float) var.optdouble(3, 0);
				text.size = (float) var.optdouble(4, 12);
				return text.controls;
			}
		});
		set("newItem", new VarArgFunction() {
			@Override
			public Varargs invoke(Varargs args) {
				Hud2d_itemIcon gii = new Hud2d_itemIcon();
				if(!args.arg1().isnil())
					gii.setStack(args.checkjstring(1));
				gii.x = args.optint(2, 0);
				gii.y = args.optint(3, 0);
				gii.setCount(args.optint(4, 1));
				return gii.controls;
			}
		});
//		this.set("addLine", new ZeroArgFunction() {
//			@Override
//			public LuaValue call() {
//				return new Hud2D_Line().getControls();
//			}
//		});
		this.set("clearAll", new ZeroArgFunction() {
			@Override
			public LuaValue call() {
				AdvancedMacros.forgeEventHandler.clear2DHud();
				return LuaValue.NONE;
			}
		});
		this.set("getSize", new VarArgFunction() {
			@Override
			public Varargs invoke(Varargs v) {
				LuaTable temp = new LuaTable();
				Minecraft mc = Minecraft.getMinecraft();
				ScaledResolution scaled = new ScaledResolution(mc);
				temp.set(1, LuaValue.valueOf(scaled.getScaledWidth()));
				temp.set(2, LuaValue.valueOf(scaled.getScaledHeight()));
				return temp.unpack();
			}
		});
	}
}
