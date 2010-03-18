package base.util;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * ͼƬѹ��������
 * 
 * @author huwei(jshuwei.org.cn)
 * @since 1.5
 */
public class ImageUtil {
	/**
	 * ѹ��ͼƬ
	 * 
	 * @param srcImg
	 *            ԴͼƬ
	 * @param tarImg
	 *            ѹ����ͼƬ
	 * @param width
	 *            ѹ������
	 * @param height
	 *            ѹ����߶�
	 * @param flag
	 *            ѹ����ʽ(0/1/2:ָ�����ѹ��/���տ�ȵȱ�ѹ��/���ո߶ȵȱ�ѹ��,��Ϊnull����0����)
	 * @param type
	 *            java.awt.image.BufferedImage�µĳ�������(��Ϊnull��Ĭ�ϲ���BufferedImage.
	 *            TYPE_3BYTE_BGR)
	 * @param formatType
	 *            ͼƬ��ʽ(��Ϊnull��Ĭ�ϲ���"jpeg")
	 * @throws Exception
	 *             ����������쳣
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
	 * ѹ��ͼƬ
	 * 
	 * @param srcImg
	 *            ԴͼƬ
	 * @param tarImg
	 *            ѹ����ͼƬ
	 * @param width
	 *            ѹ������
	 * @param height
	 *            ѹ����߶�
	 * @throws Exception
	 *             ����������쳣
	 */
	public static void compressionImage(File srcImg, File tarImg, Integer width, Integer height)
			throws Exception {
		compressionImage(srcImg, tarImg, width, height, null, null, null);
	}

	/**
	 * ѹ��ͼƬ
	 * 
	 * @param srcImg
	 *            ԴͼƬ
	 * @param tarImg
	 *            ѹ����ͼƬ
	 * @param width
	 *            ѹ������
	 * @param height
	 *            ѹ����߶�
	 * @param flag
	 *            ѹ����ʽ(0/1/2:ָ�����ѹ��/���տ�ȵȱ�ѹ��/���ո߶ȵȱ�ѹ��,��Ϊnull����0����)
	 * @throws Exception
	 *             ����������쳣
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