import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Hit & Blow Game
 * @author hiroki
 *
 */
public class Game1 {
	final static int CHANCE_NUM = 10;
	
	HBNumber ansHBN;
	
	/**
	 * 正解を128に固定
	 */
	public Game1() {
		this(128);
	}
	
	/**
	 * 正解を設定。ふさわしくない正解の時は例外内容を出力
	 * @param ans: 正解
	 */
	public Game1(int ans) {
		 try {
			ansHBN = new HBNumber(ans);
		} catch (HBNumberException e) {
			System.out.println("HBNumber Exception");
			System.out.println("status:" + e.getStatus());
			System.out.println("argument:" + e.getNum());
			System.out.println(e.getMessage());
			System.exit(-1);
		}
	}
	
	/**
	 * ゲームの主要部。規定回数か、正解したら終了。
	 * 整数入力までの確認をinputInt()で行い、  そこから先の3桁で重複がないかはHBNumberクラスに任せている。
	 */
	
	public void execute() {
		System.out.println("Hit&Blowを開始します！");
		
		for (int i = 0; i < CHANCE_NUM; i++) {
			System.out.println((i+1) + "回目のチャレンジ！");
			
			HBNumber inputHBN = null;
			boolean validity;
			
			/* 妥当な整数の入力があるまで繰り返す */
			do {
				try {
					inputHBN = new HBNumber(inputInt());
					validity = true;
				} catch (HBNumberException e) {
					if (e.getStatus() == HBNumberException.NOT_TRI) {
						System.out.println("3桁になっていません");
					} else if (e.getStatus() == HBNumberException.OVERLAP) {
						System.out.println("同じ数を2回使ってます");
					} else if (e.getStatus() == HBNumberException.MINUS) {
						System.out.println("負数は使用不可です");
					}
					validity = false;
				}
			} while (!validity);
			
			
			if ( judge(inputHBN) ) {
				return;
			}
		}
		failMessage();
	}
	
	/**
	 * 正解不正解の判断
	 * オーバーライドするためにprotected
	 */
	protected boolean judge(HBNumber inputHBN) {
		if (this.ansHBN.getTriDigi() == inputHBN.getTriDigi()) {
			System.out.println("\n正解です！！\n");
			return true;
		} else {
			System.out.println("残念、違います。\n");
			return false;
		}
	}

	/**
	 * 整数の入力を行うmethod
	 */
	private int inputInt() {
		int input = 0;
		boolean isNum;
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in));
		
		/* 整数の入力があるまで繰り返す */
		do {
			System.out.print("3桁の数値を入力してください: ");
			try {
				String line = br.readLine();				
				input = Integer.parseInt(line);
				isNum = true;
			} catch (NumberFormatException e) {
				/* 整数以外の入力例外 */
				System.out.println("整数を入力してください ");
				isNum = false;
			} catch (IOException e) {
				/* 入力例外 */
				e.printStackTrace();
				isNum = false;
			}
		} while (!isNum);
		
		return input;
	}
	
	/**
	 * 規定回数失敗時のメッセージ
	 */
	protected void failMessage() {
		System.out.println("答えは" + this.ansHBN + "です。");
	}
}
