package spellchecker;

import static org.apache.commons.io.FileUtils.*;

import java.io.*;
import java.util.regex.*;

public class BasicSpellChecker implements SpellChecker {
	public BinaryTreeNode root;
	String parts[];
	String all;
	Matcher m;
	StringBuilder bd = new StringBuilder();
	BasicDictionary allWord = new BasicDictionary();
	int prevIndex = 0;

	@Override
	public void importDictionary(String filename) throws Exception {
		// TODO Auto-generated method stub
		allWord.importFile(filename);

	}

	@Override
	public void loadDictionary(String filename) throws Exception {
		// TODO Auto-generated method stub
		allWord.load(filename);
	}

	@Override
	public void saveDictionary(String filename) throws Exception {
		// TODO Auto-generated method stub
		allWord.save(filename);
	}

	@Override
	public void loadDocument(String filename) throws Exception {
		// TODO Auto-generated method stub
		bd = new StringBuilder(readFileToString(new File(filename)));
	}

	@Override
	public void saveDocument(String filename) throws Exception {
		// TODO Auto-generated method stub
		writeStringToFile(new File(filename), bd.toString());
	}

	@Override
	public String getText() {
		return bd.toString();
	}

	@Override
	public String[] spellCheck(boolean continueFromPrevious) {
		// TODO Auto-generated method stub
		String expr = "\\b[\\w']+\\b";
		Pattern p = Pattern.compile(expr);

		if (!continueFromPrevious || m == null) {
			m = p.matcher(bd.toString());
		}
		String alls[] = new String[4];
		while (m.find()) {
			String temp[] = allWord.find(m.group().trim());
			if (temp != null) {
				alls[0] = m.group();
				alls[1] = String.valueOf(m.start() + prevIndex);
				alls[2] = temp[0];
				alls[3] = temp[1];
				return alls;
			}
		}
		return null;

	}

	@Override
	public void addWordToDictionary(String word) {
		// TODO Auto-generated method stub
		allWord.add(word);
	}

	@Override
	public void replaceText(int startIndex, int endIndex, String replacementText) {
		// TODO Auto-generated method stub
		bd.replace(startIndex, endIndex, replacementText);
		prevIndex += replacementText.length() - (endIndex - startIndex);

	}

}
