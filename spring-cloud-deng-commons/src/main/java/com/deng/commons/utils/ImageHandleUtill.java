package com.deng.commons.utils;


import com.deng.commons.enums.ResultEnum;

import java.io.File;

public class ImageHandleUtill {
	public static int handle(File file, String filepath){
		String prefixtemp=filepath.substring(0,filepath.lastIndexOf("."));//不含点
		String extendtemp=filepath.substring(filepath.lastIndexOf("."),filepath.length());//包含点
		String filepathbig=prefixtemp+"_big"+extendtemp;
		String filepathmiddle=prefixtemp+"_mid"+extendtemp;
		String filepathsmall=prefixtemp+"_sma"+extendtemp;
		try {
			ImgUtil.createImage(file, filepathbig, ResultEnum.LARGE_WIDTH.getCode(), ResultEnum.LARGE_HEIGHT.getCode());
			ImgUtil.createImage(file, filepathmiddle, ResultEnum.MEDIUM_WIDTH.getCode(),  ResultEnum.MEDIUM_HEIGHT.getCode());
			ImgUtil.createImage(file, filepathsmall, ResultEnum.SMALL_WIDTH.getCode(),  ResultEnum.SMALL_HEIGHT.getCode());
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}
	
}
