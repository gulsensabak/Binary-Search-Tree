import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) {
		ArrayList<String> myArrayList = new ArrayList<>();



		FileInputStream inputfile;

		File outputFileforAVL = new File(args[1]+ "_AVL.txt");
		File outputFileforBST = new File(args[1]+ "_BST.txt");



		
		try {
			inputfile = new FileInputStream(args[0]);
			Scanner sc = new Scanner(inputfile);


			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				line = line.replaceAll("\\s", " ").strip();
				myArrayList.add(line);

			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		
		try {

		MethodsForBst myBst = new MethodsForBst();
		Avl myAvl = new Avl();
		FileWriter myWriter1 = new FileWriter(outputFileforBST);
		FileWriter myWriter2 = new FileWriter(outputFileforAVL);

		MyNode root = new MyNode(myArrayList.get(0));
		MyNode root2 = new MyNode(myArrayList.get(0));

		myBst.setRoot(root);
		myAvl.setRoot(root2);



		for(int i=1; i< myArrayList.size(); i++) {
			String[] lines = myArrayList.get(i).split(" ");
			MyNode rootBSt = myBst.getRoot();
			MyNode rootAVl = myAvl.getRoot();
			
			

			if(lines[0].equals("ADDNODE")) {
				MyNode node = new MyNode(lines[1]);
				myBst.add(rootBSt, lines[1], myWriter1);
				rootAVl = myAvl.getRoot();
				root2 = myAvl.add(rootAVl, lines[1], myWriter2);
			}

			if(lines[0].equals("DELETE")) {
				MyNode node =  new MyNode(lines[1]);
				myBst.deleteNode(rootBSt, lines[1], myWriter1);
				rootAVl = myAvl.getRoot();
				root2 = myAvl.delete(rootAVl, rootAVl, lines[1], myWriter2);
			}

			if(lines[0].equals("SEND")) {
				String sender =  lines[1];
				String receiver = lines[2];
				
				myBst.sendMessage(rootBSt, sender, receiver, myWriter1);
				myAvl.sendMessage(rootAVl, sender, receiver, myWriter2);
			}
		}
		myWriter1.close();
		myWriter2.close();
		}
		catch(IOException e ){
			e.printStackTrace();
			
		}



























	}



}

