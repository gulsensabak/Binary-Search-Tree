import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class MethodsForBst {
	public MyNode root;
	public MyNode getRoot() {
		return root;
	}
	public void setRoot(MyNode root) {
		this.root = root;
	}
	
	
	public void add(MyNode node, String value, FileWriter myWriter) throws IOException {
		ArrayList<String> myArrayList = new ArrayList<>();
		MyNode copyroot = root;

		int compareResult = node.getValue().compareTo(value);

		if(compareResult > 0) {
			myWriter.write(node.getValue() + ": New node being added with IP:"+ value+ "\n");
			

			if(node.getLeft() != null) {
				add(node.getLeft(), value, myWriter);
				
			}
			else {

				while(node != null) {
					
					if(node != null && value.compareTo(node.getValue())>0) {
						MyNode addnew = new MyNode(value);
						node.setRight(addnew);
						myArrayList.add(value);
						return;
					}

					else if(node != null && value.compareTo(node.getValue())<0) {
						MyNode addnew = new MyNode(value);
						node.setLeft(addnew);
						myArrayList.add(value);
						return;
					}

				}

			}

		}

		else if(compareResult<0) {
			myWriter.write(node.getValue() + ": New node being added with IP:"+ value+"\n");
			

			if(node.getRight() != null) {
				add(node.getRight(), value, myWriter);
			}
			else {

				while(node != null) {

					if(node != null && value.compareTo(node.getValue())>0) {
						MyNode addRight = new MyNode(value);
						node.setRight(addRight);
						myArrayList.add(value);
						return;
					}

					else if(copyroot != null && value.compareTo(copyroot.getValue())<0) {
						MyNode addLeft = new MyNode(value);
						node.setLeft(addLeft);
						myArrayList.add(value);

						return;
					}
				}
			}

		}

		

	}
	ArrayList<MyNode> movingNumbers = new ArrayList<>();
	public MyNode search(MyNode root, String value) {
		MyNode temp = root;

		if(temp == null || temp.getValue().equals(value)) {
			if(movingNumbers.size() == 1){
				MyNode returnedVal = movingNumbers.get(0);
				return returnedVal;
			}
			MyNode returnedVal = movingNumbers.get(movingNumbers.size()-1);
			return returnedVal;
		}

		else if(temp.getValue().compareTo(value) >0) {
			movingNumbers.add(temp);
			return search(temp.getLeft(), value);
		}
		else if(temp.getValue().compareTo(value) < 0) {
			movingNumbers.add(temp);
			return search(temp.getRight(), value);
		}
		else {
			return temp;
		}
	}
	public MyNode findMin(MyNode node) {
		MyNode tempMyNode = node;
		while(tempMyNode.getLeft() != null){
			tempMyNode = tempMyNode.getLeft();
		}
		return tempMyNode;

	}
	public void deleteNode(MyNode node, String value, FileWriter myWriter) throws IOException {
		
		MyNode tempNode = node;
		MyNode resultNode = search(tempNode, value);
		movingNumbers.clear();

		
		
		if(resultNode.getValue().compareTo(value) > 0) {
			if(resultNode.getLeft().getRight() == null && resultNode.getLeft().getLeft() == null) {
				myWriter.write(resultNode.getValue()+": Leaf Node Deleted: "+ value +"\n");
				resultNode.setLeft(null);
				return;
			}
		}


		else if(resultNode.getValue().compareTo(value) < 0) {
			if(resultNode.getRight().getRight() == null && resultNode.getRight().getLeft() == null) {
				myWriter.write(resultNode.getValue()+": Leaf Node Deleted: "+ value+ "\n");
				resultNode.setRight(null);
				return;
			}

		}

		if(value.compareTo(resultNode.getValue()) <0) {
			
			MyNode doRemoved = resultNode.getLeft();
			MyNode doReplacement;
				

			if(doRemoved.getRight() != null) {doReplacement = findMin(doRemoved.getRight());}
			else { doReplacement = doRemoved.getLeft();}

	

			if(doRemoved.getRight().getValue().equals(doReplacement.getValue()) && doRemoved.getLeft() == null) {
				
				myWriter.write(resultNode.getValue() +": Node with single child Deleted: "+ doRemoved.getValue()+ "\n");
				
				resultNode.setLeft(doReplacement);
				return;
			}

		}

		else if(value.compareTo(resultNode.getValue()) >0) {
			MyNode remove = resultNode.getRight();
			MyNode replace;
			if(remove.getRight() != null) {replace = findMin(remove.getRight());}
			else { replace = remove.getLeft();}

			if(remove.getLeft() == replace && remove.getRight() == null) {
				myWriter.write(resultNode.getValue() +": Node with single child Deleted: "+ remove.getValue()+ "\n");
				
				resultNode.setRight(replace);
				return;
			}

		}


		if(resultNode.getValue().compareTo(value) > 0 ) {
			
			if(resultNode.getLeft().getRight() != null && resultNode.getLeft().getLeft() != null){
			MyNode removeNode = resultNode.getLeft();
			MyNode replaceNode;
			if(removeNode.getRight() != null) {replaceNode = findMin(removeNode.getRight());}
			else {replaceNode = removeNode.getLeft();}
			myWriter.write(resultNode.getValue()+ ": Non Leaf Node Deleted; removed: "+ removeNode.getValue()+" replaced: "+ replaceNode.getValue() +"\n");

			MyNode rightNode = resultNode.getLeft().getRight();
			MyNode leftNode = resultNode.getLeft().getLeft();

			if(!replaceNode.equals(rightNode)){
				replaceNode.setRight(rightNode);

			}
			if(!replaceNode.equals(leftNode)){
				replaceNode.setLeft(leftNode);
			}
			

			resultNode.setLeft(replaceNode);
			return;
		}
		}
		else if(resultNode.getValue().compareTo(value) < 0) {
		
			if( resultNode.getRight().getLeft() != null && resultNode.getRight().getRight() != null){
			MyNode removedNode = resultNode.getRight();
			MyNode replacedNode;
			if(removedNode.getRight() != null) {replacedNode = findMin(removedNode.getRight());}
			else { replacedNode = removedNode.getLeft();}
			myWriter.write(resultNode.getValue()+ ": Non Leaf Node Deleted; removed: "+ removedNode.getValue()+" replaced: "+ replacedNode.getValue() +"\n");
			
			MyNode rightNode = resultNode.getRight().getRight();
			MyNode leftNode = resultNode.getRight().getLeft();


			if(!replacedNode.equals(rightNode)){
				replacedNode.setRight(rightNode);

			}
			if(!replacedNode.equals(leftNode)){
				replacedNode.setLeft(leftNode);
			}



			resultNode.setRight(replacedNode);
			return;
		}
		}
	
	}





	private ArrayList<String> pathForSender = new ArrayList<>();
	public ArrayList<String> createPath(MyNode root, String sender) {
		MyNode tempRoot = root;
		if(tempRoot == null ) {
			return pathForSender;
		}

		if(tempRoot.getValue() == null){
			return pathForSender;
		}

		if(tempRoot.getValue().compareTo(sender) == 0) {
			pathForSender.add(tempRoot.getValue());
			return pathForSender;
		}
		else if(tempRoot.getValue().compareTo(sender) >0) {
			pathForSender.add(tempRoot.getValue());
			createPath(tempRoot.left, sender);
		}
		else {
			pathForSender.add(tempRoot.getValue());
			createPath(tempRoot.getRight(), sender);
		}
		return pathForSender;

	}


	public void sendMessage(MyNode root,String sender, String receiver, FileWriter myWriter) throws IOException {
		ArrayList<String> traveArrayList = createPath(root, sender);		
		ArrayList<String> copySender = new ArrayList<>(traveArrayList); //gönderen
		traveArrayList.clear();

		ArrayList<String> reciveArrayList = createPath(root, receiver);
		ArrayList<String> copyReceive = new ArrayList<>(reciveArrayList);


		reciveArrayList.clear();

		ArrayList<String> travelPath = new ArrayList<>();

		ArrayList<String> lastTime = new ArrayList<>();

		if(copySender.size() >= copyReceive.size()) {
			for (int i = copySender.size() -1; i >= 0 ; i--) {
				if(!copyReceive.contains(copySender.get(i))) {
					travelPath.add(copySender.get(i));
				}
				else {
					travelPath.add(copySender.get(i));
					break;
				}
			}

			for (int i = 0; i < copyReceive.size(); i++) {
				if(!travelPath.contains(copyReceive.get(i))) {
					travelPath.add(copyReceive.get(i));
				}
			}
		}


		else if(copySender.size() < copyReceive.size()) {

			for (int i = copyReceive.size()-1; i >=0; i--) {
				if(!copySender.contains(copyReceive.get(i))){
					travelPath.add(copyReceive.get(i));
				}
				else {
					travelPath.add(copyReceive.get(i));
					break;
				}
			}

			for(int i = copySender.size()-1; i>=0; i--) {
				if(!travelPath.contains(copySender.get(i))) {
					lastTime.add(copySender.get(i));
				}
				else {
					break;
				}
			}			
			Collections.reverse(lastTime);

			for (int i = 0; i < lastTime.size(); i++) {
				travelPath.add(lastTime.get(i));
			}

			Collections.reverse(travelPath);



		}
		myWriter.write(travelPath.get(0)+ ": Sending message to: "+ travelPath.get(travelPath.size()-1)+ "\n");
		


		for (int i = 1; i <travelPath.size()-1; i++) {
			myWriter.write(travelPath.get(i) + ": Transmission from: "+travelPath.get(i-1) + " receiver: "+ travelPath.get(travelPath.size()-1)+ " sender:"+ travelPath.get(0)+ "\n");
			
		}
		myWriter.write(travelPath.get(travelPath.size()-1)+ ": Received message from: "+ travelPath.get(0)+ "\n");
		
	}
}








