package array;

import java.util.HashMap;
import java.util.Map;

public class Array {
	// 存放数据
	private int[] data;
	// 数组容量
	private int count;
	// 数组实际长度
	private int length;
	// 自动扩容系数
	private int AUTO_ADD_CAPACITY = 2;
	// 时间复杂度为O(1)的获取元素下标
	private Map<Integer, Integer> indexMap = null;

	// 有参构造芳法
	public Array(int initCount) {
		this.count = initCount;
		this.length = 0;
		this.data = new int[initCount];
		this.indexMap = new HashMap<Integer, Integer>(initCount);
	}

	// 无参构造方法
	public Array() {
		this.count = 10;
		this.length = 0;
		this.data = new int[this.count];
		this.indexMap = new HashMap<Integer, Integer>(this.count);
	}

	// 插入数据
	private boolean insert(int index, int value) {
		if (this.count == this.length) {
			return false;
		}
		if (index < 0 || index > length) {
			return false;
		} else {
			for (int i = length; i > index; i--) {
				this.data[i] = this.data[i - 1];
			}
			this.data[index] = value;
			this.indexMap.put(value, index);
			this.length++;
			return true;
		}
	}

	// 删除数据
	private boolean delete(int index) {
		if (this.length == 0) {
			return false;
		}
		if (index < 0 || index >= length) {
			return false;
		} else {
			if (index != this.length - 1) {
				for (int i = index; i < this.length; i++) {
					this.data[i] = this.data[i + 1];
				}
			}
			this.indexMap.remove(index);
			this.length--;
			return true;
		}
	}

	// 随机访问
	private int get(int index) {
		if (this.length <= 0) {
			return -1;
		}
		if (index < 0 || index >= this.length) {
			return -1;
		} else {
			return this.data[index];
		}
	}

	// 全部获取
	private Map<Integer, Integer> getAll() {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		if (this.length <= 0) {
			return map;
		} else {
			for (int i = 0; i < this.length; i++) {
				map.put(i, this.data[i]);
			}
			return map;
		}
	}

	// 自动扩容
	private void autoAddCapacity() {
		this.count = this.count * this.AUTO_ADD_CAPACITY;
		int[] newArray = new int[this.count];
		for (int i = 0; i < this.length; i++) {
			newArray[i] = this.data[i];
		}
		this.data = newArray;
		newArray = null;
	}

	// 利用数组实现LRU算法
	// LRU算法思想：排在最前面的是最近访问的，
	// 如果访问一个元素，数组里面没有，则判断数组是否已满，如果未满，则放入头部，
	// 如果已满，则删除尾部元素，所有元素往后移一位，将该元素放置在第一位。
	// 如果数组里面有，则将该元素之前的数据全部后移一位，该元素放置在数组的头部即index = 0
	private int find(int value) {
		Integer index = this.indexMap.get(value);
		if (index == null) {
			return -1;
		} else {
			return index;
		}
	}

	/**
	 * 没有找到数据，则添加进去
	 * @param value
	 */
	private void insertToFirstIndexForNotFind(int value) {
		if (this.length < this.count) {
			for (int i = this.length; i > 0; i--) {
				this.data[i] = this.data[i - 1];
			}
		} else {
			for (int i = this.length - 1; i > 0; i--) {
				this.data[i] = this.data[i - 1];
			}
		}
		this.data[0] = value;
		if (this.length < this.count) {
			this.length++;
		}
		this.refreshIndexMap();
	}

	/**
	 * 超找到数据，并放置第一位
	 * @param thisIndex 需要一定到第一位的下标
	 * @param value 需要移动到第一位的值
	 */
	private void moveToFirstIndex(int thisIndex, int value) {
		for (int i = thisIndex; i > 0; i--) {
			this.data[i] = this.data[i - 1];
		}
		this.data[0] = value;
		this.refreshIndexMap();
	}

	/**
	 * 刷新map
	 */
	private void refreshIndexMap() {
		this.indexMap.clear();
		for (int i = 0; i < this.length; i++) {
			indexMap.put(this.data[i], i);
		}
	}

	public static void main(String[] args) {
		Array array = new Array(4);
		array.insert(0, 0);
		array.insert(1, 1);
		array.insert(2, 2);
		array.insert(3, 3);
		int value = 10;
		int thisIndex = array.find(value);
		if (thisIndex == -1) {
			array.insertToFirstIndexForNotFind(value);
		} else {
			array.moveToFirstIndex(thisIndex, value);
		}
		for (int i = 0; i < array.length; i++) {
			System.out.println(array.data[i]);
		}
	}
}
