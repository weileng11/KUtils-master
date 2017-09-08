package com.zwy.kutils.widget.loadingdialog.adapter;

public class StrericWheelAdapter implements WheelAdapter {
	
	/** The default min value */
	private String[] strContents;
	/**
	 * 构造方法
	 * @param strContents
	 */
	public StrericWheelAdapter(String[] strContents){
		this.strContents=strContents;
	}
	
	
	public String[] getStrContents() {
		return strContents;
	}


	public void setStrContents(String[] strContents) {
		this.strContents = strContents;
	}


	public String getItem(int index) {
		if (index >= 0 && index < getItemsCount()) {
			return strContents[index];
		}
		return null;
	}
	
	public int getItemsCount() {
		return strContents.length;
	}
	/**
	 * 设置最大的宽度
	 */
	public int getMaximumLength() {
		int maxLen=7;
		return maxLen;
	}


	@Override
	public String getCurrentId(int index) {
		// TODO Auto-generated method stub
		return null;
	}
}
