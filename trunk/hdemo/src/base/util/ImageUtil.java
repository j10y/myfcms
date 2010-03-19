package com.hxzy.base.util;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * 图片压缩工具类
 * 
 * @author huwei(jshuwei.org.cn)
 * @since 1.5
 */
public class ImageUtil {
	/**
	 * 压缩图片
	 * 
	 * @param srcImg
	 *            源图片
	 * @param tarImg
	 *            压缩后图片
	 * @param width
	 *            压缩后宽度
	 * @param height
	 *            压缩后高度
	 * @param flag
	 *            压缩方式(0/1/2:指定宽高压缩/按照宽度等比压缩/按照高度等比压缩,若为null采用0处理)
	 * @param type
	 *            java.awt.image.BufferedImage下的常用类型(若为null则默认采用BufferedImage.
	 *            TYPE_3BYTE_BGR)
	 * @param formatType
	 *            图片格式(若为null则默认采用"jpeg")
	 * @throws Exception
	 *             处理错误抛异常
	 */
	public static void compressionImage(File srcImg, File tarImg, Integer width, Integer height,
			Integer flag, Integer type, String formatType) throws Exception {
		BufferedImage bi = ImageIO.read(srcImg);
		int srcWidth = bi.getWidth();
		int srcHeight = bi.getHeight();
		if (flag == null)
			flag = 0;
		if (type == null)
			type = BufferedImage.TYPE_3BYTE_BGR;
		if (formatType == null)
			formatType = "jpeg";
		if (flag.equals(0))
			doCompression(bi, tarImg, width, height, type, formatType);
		else if (flag.equals(1))
			doCompression(bi, tarImg, width, srcHeight * width / srcWidth, type, formatType);
		else if (flag.equals(2))
			doCompression(bi, tarImg, srcWidth * height / srcHeight, height, type, formatType);
	}

	/**
	 * 压缩图片
	 * 
	 * @param srcImg
	 *            源图片
	 * @param tarImg
	 *            压缩后图片
	 * @param width
	 *            压缩后宽度
	 * @param height
	 *            压缩后高度
	 * @throws Exception
	 *             处理错误抛异常
	 */
	public static void compressionImage(File srcImg, File tarImg, Integer width, Integer height)
			throws Exception {
		compressionImage(srcImg, tarImg, width, height, null, null, null);
	}

	/**
	 * 压缩图片
	 * 
	 * @param srcImg
	 *            源图片
	 * @param tarImg
	 *            压缩后图片
	 * @param width
	 *            压缩后宽度
	 * @param height
	 *            压缩后高度
	 * @param flag
	 *            压缩方式(0/1/2:指定宽高压缩/按照宽度等比压缩/按照高度等比压缩,若为null采用0处理)
	 * @throws Exception
	 *             处理错误抛异常
	 */
	public static void compressionImage(File srcImg, File tarImg, Integer width, Integer height,
			Integer flag) throws Exception {
		compressionImage(srcImg, tarImg, width, height, flag, null, null);
	}

	private static void doCompression(BufferedImage bi, File tarImg, Integer width, Integer height,
			Integer type, String formatType) throws Exception {
		AffineTransform at = new AffineTransform();
		AffineTransformOp ato = new AffineTransformOp(at, null);
		BufferedImage bufferedImage = new BufferedImage(width, height, type);
		ato.filter(bi, bufferedImage);
		ImageIO.write(bufferedImage, formatType, tarImg);
	}
}