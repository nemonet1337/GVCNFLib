package net.blacklab.lmr.entity.maidmodel;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelMultiBase extends ModelBase implements IModelCaps{
	public float heldItem[] = new float[] {0.0F, 0.0F};
	public boolean aimedBow;
	public boolean isSneak;
	public boolean isWait;
	
	public ModelRenderer mainFrame;
	public ModelRenderer HeadMount;
	public ModelRenderer HeadTop;
	public ModelRenderer Arms[];
	public ModelRenderer HardPoint[];
	
	public float entityIdFactor;
	public int entityTicksExisted;
	// ��������������������������?�?
	public float scaleFactor = 0.9375F;
	/**
	 * �����?�����������������?�������?����
	 */
	@SuppressWarnings("serial")
	private final Map<String, Integer> fcapsmap = new HashMap<String, Integer>() {{
		put("onGround",			caps_onGround);
		put("isRiding",			caps_isRiding);
		put("isSneak",			caps_isSneak);
		put("isWait",			caps_isWait);
		put("isChild",			caps_isChild);
		put("heldItemLeft",		caps_heldItemLeft);
		put("heldItemRight",	caps_heldItemRight);
		put("aimedBow",			caps_aimedBow);
		put("ScaleFactor", 		caps_ScaleFactor);
		put("entityIdFactor",	caps_entityIdFactor);
		put("dominantArm",	caps_dominantArm);
	}};
	@Override
	public Map<String, Integer> getModelCaps() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	@Override
	public Object getCapsValue(int pIndex, Object... pArg) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	@Override
	public boolean setCapsValue(int pIndex, Object... pArg) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
}
