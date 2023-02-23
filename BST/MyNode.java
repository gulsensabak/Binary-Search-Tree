
public class MyNode {
	MyNode left;
	MyNode right;
	String value;
	public int height = 1;

	public MyNode(String value) {
		this.left = null;
		this.right = null;
		this.value = value;
	}


	public MyNode(MyNode left, MyNode right, String value) {
		this.left = null;
		this.right = null;
		this.value = value;
	}


	public MyNode getLeft() {
		return left;
	}

	public void setLeft(MyNode left) {
		this.left = left;
	}

	public MyNode getRight() {
		return this.right;
	}

	public void setRight(MyNode right) {
		this.right = right;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value2) {
		this.value = value2;
	}


	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}



}




