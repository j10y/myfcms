package com.zving.framework.utility;

import java.awt.Dimension;
import java.io.IOException;
import java.io.PrintStream;
import magick.DrawInfo;
import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;
import magick.PixelPacket;

public class ImageMagickUtil {
	public static void main(String[] args) throws IOException, MagickException {
		scaleRateImageFile("i://test.bmp", "i:\\test1.bmp", 200, 150);
	}

	public static Dimension getDimension(String fileName) throws MagickException {
		System.setProperty("jmagick.systemclassloader", "no");
		ImageInfo info = null;
		MagickImage fromImage = null;
		Dimension dim = null;
		try {
			info = new ImageInfo(fileName);
			fromImage = new MagickImage(info);
			dim = fromImage.getDimension();
		} finally {
			if (fromImage != null) {
				fromImage.destroyImages();
			}
		}
		return dim;
	}

	public static void scaleRateImageFile(String fromFileName, String toFileName, int toWidth,
			int toHeight) throws MagickException {
		System.setProperty("jmagick.systemclassloader", "no");
		ImageInfo info = null;
		MagickImage fromImage = null;
		double w;
		try {
			info = new ImageInfo(fromFileName);

			fromImage = new MagickImage(info);
			Dimension dim = fromImage.getDimension();
			w = dim.getWidth();
			double h = dim.getHeight();
			if ((w < toWidth) && (h < toHeight))
				FileUtil.copy(fromFileName, toFileName);
			while (true) {
				if (toWidth == 0) {
					if (h <= toHeight) {
						FileUtil.copy(fromFileName, toFileName);
					}
					scaleRateImageFile(fromFileName, toFileName, toHeight / h);
				}

				if (toHeight != 0)
					break;
				if (w <= toWidth) {
					FileUtil.copy(fromFileName, toFileName);
				}
				scaleRateImageFile(fromFileName, toFileName, toWidth / w);
			}

			if (toWidth / w > toHeight / h) {
				scaleRateImageFile(fromFileName, toFileName, toHeight / h);
			}
		} finally {
			if (fromImage != null)
				fromImage.destroyImages();
		}
		if (fromImage != null)
			fromImage.destroyImages();
	}

	public static void scaleRateImageFile(String fromFileName, String toFileName, double rate)
			throws MagickException {
		LogUtil.info("ImageMagickUtil正在生成缩略图--" + toFileName);
		System.setProperty("jmagick.systemclassloader", "no");
		MagickImage fromImage = null;
		try {
			ImageInfo info = new ImageInfo(fromFileName);
			fromImage = new MagickImage(info);
			Dimension dim = fromImage.getDimension();
			double w = dim.getWidth();
			double h = dim.getHeight();
			MagickImage toImage = fromImage.scaleImage((int) (w * rate), (int) (h * rate));
			toImage.setFileName(toFileName);
			toImage.writeImage(new ImageInfo(fromImage.getFileName()));
		} finally {
			if (fromImage != null) {
				fromImage.destroyImages();
			}
		}
		LogUtil.info("生成缩略图完毕");
	}

	public static void scaleFixedImageFile(String fromFileName, String toFileName, int toWidth,
			int toHeight) throws MagickException {
		LogUtil.info("ImageMagickUtil正在生成缩略图--" + toFileName);
		System.setProperty("jmagick.systemclassloader", "no");
		MagickImage fromImage = null;
		MagickImage toImage = null;
		try {
			ImageInfo info = new ImageInfo(fromFileName);
			fromImage = new MagickImage(info);
			toImage = fromImage.scaleImage(toWidth, toHeight);
			toImage.setFileName(toFileName);
			toImage.writeImage(info);
		} finally {
			if (fromImage != null) {
				fromImage.destroyImages();
			}
			if (toImage != null) {
				toImage.destroyImages();
			}
		}
		LogUtil.info("生成缩略图完毕");
	}

	public static void pressText(String filePath, String pressText, int color, int fontSize,
			int position) {
		LogUtil.info("ImageMagickUtil正在打文字水印--" + filePath);
		System.setProperty("jmagick.systemclassloader", "no");
		MagickImage image = null;
		try {
			ImageInfo info = new ImageInfo(filePath);

			if ((filePath.toUpperCase().endsWith("JPG"))
					|| (filePath.toUpperCase().endsWith("JPEG"))) {
				info.setCompression(5);
				info.setPreviewType(29);
				info.setQuality(95);
			}

			image = new MagickImage(info);
			Dimension dim = image.getDimension();
			int wideth = (int) dim.getWidth();
			int height = (int) dim.getHeight();
			if ((wideth <= 300) && (height <= 300)) {
				return;
			}
			DrawInfo drawInfo = new DrawInfo(info);

			drawInfo.setFill(PixelPacket.queryColorDatabase("white"));

			drawInfo.setOpacity(0);
			drawInfo.setPointsize(100.0D);

			String fontPath = "C:/WINDOWS/Fonts/SIMSUN.TTC";

			drawInfo.setFont(fontPath);
			drawInfo.setTextAntialias(true);

			drawInfo.setText(pressText);
			drawInfo.setGeometry("+1500+1000");

			image.annotateImage(drawInfo);

			image.setFileName(filePath);
			image.writeImage(info);
			image.destroyImages();
			image = null;
		} catch (MagickException e) {
			e.printStackTrace();
		} finally {
			if (image != null) {
				image.destroyImages();
			}
		}
		System.out.println("完毕");
	}

	public static final void pressImage(String targetImg, String pressImg, int position) {
		LogUtil.info("ImageMagickUtil正在打图片水印--" + targetImg);
		System.setProperty("jmagick.systemclassloader", "no");
		MagickImage image = null;
		MagickImage press = null;
		try {
			ImageInfo imageInfo = new ImageInfo(targetImg);
			image = new MagickImage(imageInfo);
			Dimension dim = image.getDimension();
			int wideth = (int) dim.getWidth();
			int height = (int) dim.getHeight();
			if ((wideth <= 300) && (height <= 300)) {
				return;
			}
			press = new MagickImage(new ImageInfo(pressImg));
			dim = press.getDimension();
			int wideth_press = (int) dim.getWidth();
			int height_press = (int) dim.getHeight();
			int x = 0;
			int y = 0;
			int bianju = 20;
			int[][][] positions = {
					{ { bianju, bianju }, { (wideth - wideth_press) / 2, bianju },
							{ wideth - wideth_press - bianju, bianju } },
					{ { bianju, (height - height_press) / 2 },
							{ (wideth - wideth_press) / 2, (height - height_press) / 2 },
							{ wideth - wideth_press - bianju, (height - height_press) / 2 } },
					{ { bianju, height - height_press - bianju },
							{ (wideth - wideth_press) / 2, height - height_press - bianju },
							{ wideth - wideth_press - bianju, height - height_press - bianju } } };
			if (position == 0) {
				position = NumberUtil.getRandomInt(9) + 1;
			}
			x = positions[((position - 1) / 3)][((position - 1) % 3)][0];
			y = positions[((position - 1) / 3)][((position - 1) % 3)][1];

			image.compositeImage(3, press, x, y);
			image.setFileName(targetImg);
			image.writeImage(imageInfo);
		} catch (MagickException e) {
			e.printStackTrace();
		} finally {
			if (image != null) {
				image.destroyImages();
			}
			if (press != null) {
				press.destroyImages();
			}
		}
		System.out.println("完毕");
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.framework.utility.ImageMagickUtil JD-Core Version: 0.5.3
 */