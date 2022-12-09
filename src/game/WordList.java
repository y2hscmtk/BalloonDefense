package game;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;

public class WordList {
	private Vector<String> wordVector = new Vector<String>();
   
	public WordList() {
		//������ ������쿡 ���� ����ó�� �ʿ�
		Scanner scanner;
		try {
			scanner = new Scanner(new FileReader("words.txt"));
			while(scanner.hasNext()) {
				//�� ���ξ� �о���� => words.txt�� �� ���ο� �� �ܾ� �� ������
				wordVector.add(scanner.nextLine()); 
			}
			scanner.close(); //��ĳ�� �ʿ������
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//������ ������쿡 ���� ó�� ����
			e.printStackTrace();
		}
   
	}
   
	public String getWord() {
		int index = (int)(Math.random()*wordVector.size());
		return wordVector.get(index);
	}
}