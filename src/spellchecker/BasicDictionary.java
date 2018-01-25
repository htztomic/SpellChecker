package spellchecker;

import static java.lang.System.*;
import static org.apache.commons.io.FileUtils.*;

import java.io.*;
import java.util.*;

public class BasicDictionary implements Dictionary {
	public BinaryTreeNode root;
	StringBuilder saveAll = new StringBuilder();
	int count = 0;
	Deque<BinaryTreeNode> insertedAt = new LinkedList<>();

	@Override
	public void importFile(String filename) throws Exception {
		// TODO Auto-generated method stub
		delete(root);
		String allWords = readFileToString(new File(filename));
		String parts[] = allWords.split(lineSeparator());
		createBST(parts, true, parts.length);
	}

	public BinaryTreeNode createBST(String[] a, boolean k, int shift) {
		if (k) {
			int shifter;
			if (shift % 2 == 0) {
				shifter = 1;
			} else {
				shifter = 0;
			}
			BinaryTreeNode node = new BinaryTreeNode(a[(int) Math.ceil((double) (a.length - 1) / 2 + shifter)].trim());
			root = node;
			node.left = createBST(Arrays.copyOfRange(a, 0, (int) Math.ceil((double) (a.length - 1) / 2 + shifter)),
					false, 0);
			node.right = createBST(
					Arrays.copyOfRange(a, ((int) Math.ceil((double) (a.length - 1) / 2 + shifter)) + 1, a.length),
					false, 0);
			return null;
		} else if (a.length > 1 && !k) {

			BinaryTreeNode node = new BinaryTreeNode(a[(int) Math.ceil((double) (a.length - 1) / 2)].trim());
			// out.println(a.length);
			node.left = createBST(Arrays.copyOfRange(a, 0, (int) Math.ceil((double) (a.length - 1) / 2)), false, 0);
			node.right = createBST(Arrays.copyOfRange(a, ((int) Math.ceil((double) (a.length - 1) / 2)) + 1, a.length),
					false, 0);
			return node;
		} else if (a.length == 1) {
			BinaryTreeNode node = new BinaryTreeNode(a[0].trim());
			return node;
		}
		return null;
	}

	// public void display(BinaryTreeNode value) {
	// if (value != null) {
	// out.println(value.value);
	// display(value.left);
	// display(value.right);
	// }
	// }

	@Override
	public void load(String filename) throws Exception {
		// TODO Auto-generated method stub
		String allWords = readFileToString(new File(filename));
		String parts[] = allWords.split(lineSeparator());
		for (String A : parts) {
			add(A);
		}
	}

	@Override
	public void save(String filename) throws Exception {
		// TODO Auto-generated method stub
		append(root);
		writeStringToFile(new File(filename), saveAll.toString());
		// out.println(saveAll.toString());
		saveAll.delete(0, saveAll.length());
	}

	public void append(BinaryTreeNode latestPoint) {
		if (latestPoint != null) {
			saveAll.append(latestPoint.value);
			saveAll.append("\n");
			append(latestPoint.left);
			append(latestPoint.right);
		}
	}

	@Override
	public String[] find(String word) {
		// TODO Auto-generated method stub
		String[] fparts = new String[2];
		BinaryTreeNode latestPoint = root;
		fparts[1] = "";
		while (latestPoint != null) {
			if (latestPoint.value.compareToIgnoreCase(word) == 0) {
				return null;
			} else if (latestPoint.value.compareToIgnoreCase(word) > 0) {
				fparts[1] = latestPoint.value;
				latestPoint = latestPoint.left;
			} else {
				fparts[0] = latestPoint.value;
				latestPoint = latestPoint.right;
			}
		}
		return fparts;

	}

	@Override
	public void add(String word) {
		// TODO Auto-generated method stub

		BinaryTreeNode nNode = new BinaryTreeNode(word.trim());
		if (root == null) {
			root = nNode;
			count++;
			return;
		}
		insert(root, nNode);

	}

	public void delete(BinaryTreeNode latestPoint) {
		if (latestPoint != null) {
			delete(latestPoint.left);
			delete(latestPoint.right);
			latestPoint = null;
		}
	}

	public void insert(BinaryTreeNode latestPoint, BinaryTreeNode nNode) {
		if (latestPoint.value.compareToIgnoreCase(nNode.value) == 0) {
			return;
		}
		if (latestPoint.value.compareToIgnoreCase(nNode.value) > 0) {
			if (latestPoint.left == null) {
				// out.println(nNode.value + " " + latestPoint.value);
				// insertPoint.push(nNode);
				latestPoint.left = nNode;
				count++;
				return;
			}
			latestPoint = latestPoint.left;
			insert(latestPoint, nNode);
		}
		if (latestPoint.value.compareToIgnoreCase(nNode.value) < 0) {
			if (latestPoint.right == null) {
				// insertPoint.push(nNode);
				latestPoint.right = nNode;
				count++;
				// out.println(nNode.value + " " + latestPoint.value);
				return;
			}
			latestPoint = latestPoint.right;
			insert(latestPoint, nNode);
		}

		// if(insertPoint.isEmpty()) {
		// BinaryTreeNode test = root;
		// while(test!=null) {
		// insertPoint.push(test);
		// test = root.left;
		// }
		// }
		//
	}

	@Override
	public BinaryTreeNode getRoot() {
		// TODO Auto-generated method stub
		return root;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return count;
	}

}
