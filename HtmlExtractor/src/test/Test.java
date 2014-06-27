/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Jun 27, 2014</p>
 * <p>更新：</p>
 */
package test;

import java.util.List;

import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.Word;


/**
 * @author xiacc
 *
 * 描述：
 */
public class Test {

	public static void main(String[] args) {
		List<Word> words = WordSegmenter.seg("杨尚川是APDPlat应用级产品开发平台的作者");
		System.out.println(words);
	}
}
