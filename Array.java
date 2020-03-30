package array;

import java.util.HashMap;
import java.util.Map;

public class Array {
	//存放数据
	private int[] data;
	//数组容量
	private int count;
	//数组实际长度
	private int length;
	
	//有参构造芳法
	public Array(int initCount) {
		this.count = initCount;
		this.length = 0;
		this.data = new int[initCount];
	}
	
	//无参构造方法
	public Array() {
		this.count = 10;
		this.length = 0;
		this.data = new int[this.count];
	}
	
	//插入数据
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
			this.length++;
			return true;
		}
	}
	
	//删除数据
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
			this.length--;
			return true;
		}
	}
	
	//随机访问
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
	
	//全部获取
	private Map<Integer, Integer> getAll() {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		if (this.length <= 0) {
			return map;
		}else {
			for (int i = 0; i < this.length; i++) {
				map.put(i, this.data[i]);;
			}
			return map;
		}
	}

	public static void main(String[] args) {
		Array array = new Array();
		array.insert(0, 0);
		array.insert(0, 1);
		array.insert(0, 2);
		array.insert(0, 3);
		array.delete(0);
		System.out.println(array.getAll().toString());
		
	}
}
