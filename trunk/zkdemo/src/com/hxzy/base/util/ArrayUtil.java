package com.hxzy.base.util;

/**
 * 
 * ��������Ĺ�����(��int������Ϊ��)
 * 
 * <b>�����㷨�ķ������£�</b><br />
 * 1.��������ֱ�Ӳ��������۰��������ϣ�����򣩣� <br />
 * 2.��������ð�������򡢿������򣩣�<br />
 * 3.ѡ������ֱ��ѡ�����򡢶����򣩣�<br />
 * 4.�鲢���� <br />
 * 5.��������<br />
 * 
 * <b>�������򷽷���ѡ��</b><br />
 * (1)��n��С(��n��50)���ɲ���ֱ�Ӳ����ֱ��ѡ������<br />
 * (2)���ļ���ʼ״̬��������(ָ����)����Ӧѡ��ֱ�Ӳ��ˡ�ð�ݻ�����Ŀ�������Ϊ�ˣ�<br />
 * (3)��n�ϴ���Ӧ����ʱ�临�Ӷ�ΪO(nlgn)�����򷽷����������򡢶������鲢����<br />
 * 
 * @author huwei(jshuwei.org.cn)
 * @since 1.1
 * 
 */
public class ArrayUtil {
	/**
	 * ������������Ԫ��
	 * 
	 * @since 1.1
	 * @param ints
	 *            ��Ҫ���н�������������
	 * @param x
	 *            �����е�λ��1
	 * @param y
	 *            �����е�λ��2
	 * @return �����������
	 */
	public static int[] swap(int[] ints, int x, int y) {
		int temp = ints[x];
		ints[x] = ints[y];
		ints[y] = temp;
		return ints;
	}

	/**
	 * ð������ ������������Ԫ�ؽ��бȽ� ���ܣ��Ƚϴ���O(n^2),n^2/2����������O(n^2),n^2/4
	 * 
	 * @since 1.1
	 * @param source
	 *            ��Ҫ�����������������
	 * @return ����������
	 */
	public static int[] bubbleSort(int[] source) {
		for (int i = 1; i < source.length; i++) {
			for (int j = 0; j < i; j++) {
				if (source[j] > source[j + 1]) {
					swap(source, j, j + 1);
				}
			}
		}
		return source;
	}

	/**
	 * ֱ��ѡ������ ������ÿһ�˴Ӵ����������Ԫ����ѡ����С������󣩵�һ��Ԫ�أ� ˳��������ź�������е����ֱ��ȫ�������������Ԫ�����ꡣ
	 * ���ܣ��Ƚϴ���O(n^2),n^2/2 ��������O(n),n
	 * ����������ð�������ٶ��ˣ����ڽ�������CPUʱ��ȱȽ������CUPʱ��࣬����ѡ�������ð������졣
	 * ����N�Ƚϴ�ʱ���Ƚ������CPUʱ��ռ��Ҫ��λ��������ʱ�����ܺ�ð������̫�࣬���������ʿ϶�Ҫ��Щ��
	 * 
	 * @since 1.1
	 * @param source
	 *            ��Ҫ�����������������
	 * @return ����������
	 */
	public static int[] selectSort(int[] source) {

		for (int i = 0; i < source.length; i++) {
			for (int j = i + 1; j < source.length; j++) {
				if (source[i] > source[j]) {
					swap(source, i, j);
				}
			}
		}
		return source;
	}

	/**
	 * �������� ��������һ����¼���뵽���ź����������п����ǿձ���,�Ӷ��õ�һ���µļ�¼����1������� ���ܣ��Ƚϴ���O(n^2),n^2/4
	 * ���ƴ���O(n),n^2/4 �Ƚϴ�����ǰ���ߵ�һ�㣬�����������CPUʱ��Ͻ����٣����������ϱ�ð���������һ���࣬����ѡ������ҲҪ�졣
	 * 
	 * @since 1.1
	 * @param source
	 *            ��Ҫ�����������������
	 * @return ����������
	 */
	public static int[] insertSort(int[] source) {

		for (int i = 1; i < source.length; i++) {
			for (int j = i; (j > 0) && (source[j] < source[j - 1]); j--) {
				swap(source, j, j - 1);
			}
		}
		return source;
	}

	/**
	 * �������� ��������ʹ�÷��η���Divide and conquer����������һ�����У�list����Ϊ���������У�sub-lists���� ����Ϊ��
	 * 1. ������������һ��Ԫ�أ���Ϊ "��׼"��pivot���� 2.
	 * �����������У�����Ԫ�رȻ�׼ֵС�İڷ��ڻ�׼ǰ�棬����Ԫ�رȻ�׼ֵ��İ��ڻ�׼�ĺ���
	 * ����ͬ�������Ե���һ�ߣ���������ָ�֮�󣬸û�׼���������λ�á������Ϊ�ָpartition�������� 3.
	 * �ݹ�أ�recursive����С�ڻ�׼ֵԪ�ص������кʹ��ڻ�׼ֵԪ�ص�����������
	 * �ݻص���ײ����Σ������еĴ�С�����һ��Ҳ������Զ���Ѿ����������
	 * ����Ȼһֱ�ݻ���ȥ����������㷨�ܻ��������Ϊ��ÿ�εĵ�����iteration���У������ٻ��һ��Ԫ�ذڵ�������λ��ȥ��
	 * 
	 * @since 1.1
	 * @param source
	 *            ��Ҫ�����������������
	 * @return ����������
	 */
	public static int[] quickSort(int[] source) {
		return qsort(source, 0, source.length - 1);
	}

	/**
	 * ��������ľ���ʵ�֣�������
	 * 
	 * @since 1.1
	 * @param source
	 *            ��Ҫ�����������������
	 * @param low
	 *            ��ʼ��λ
	 * @param high
	 *            ������λ
	 * @return ����������
	 */
	private static int[] qsort(int source[], int low, int high) {
		int i, j, x;
		if (low < high) {
			i = low;
			j = high;
			x = source[i];
			while (i < j) {
				while (i < j && source[j] > x) {
					j--;
				}
				if (i < j) {
					source[i] = source[j];
					i++;
				}
				while (i < j && source[i] < x) {
					i++;
				}
				if (i < j) {
					source[j] = source[i];
					j--;
				}
			}
			source[i] = x;
			qsort(source, low, i - 1);
			qsort(source, i + 1, high);
		}
		return source;
	}

	/**
	 * ���ַ����� �������Ա�����������б�
	 * 
	 * @since 1.1
	 * @param source
	 *            ��Ҫ���в��Ҳ���������
	 * @param key
	 *            ��Ҫ���ҵ�ֵ
	 * @return ��Ҫ���ҵ�ֵ�������е�λ�ã���δ�鵽�򷵻�-1
	 */
	public int binarySearch(int[] source, int key) {
		int low = 0, high = source.length - 1, mid;
		while (low <= high) {
			mid = (low + high) >>> 1;
			if (key == source[mid]) {
				return mid;
			} else if (key < source[mid]) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return -1;
	}

	/**
	 * ��ת����
	 * 
	 * @since 1.1
	 * @param source
	 *            ��Ҫ���з�ת����������
	 * @return ��ת�������
	 */
	public static int[] reverse(int[] source) {
		int length = source.length;
		int temp = 0;
		for (int i = 0; i < length / 2; i++) {
			temp = source[i];
			source[i] = source[length - 1 - i];
			source[length - 1 - i] = temp;
		}
		return source;
	}
}