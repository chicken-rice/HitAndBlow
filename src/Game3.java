import java.util.Random;

/**
 * 
 */

/**
 * 128固定だったのをランダムに変更
 * @author hiroki
 *
 */
public class Game3 extends Game2 {

	/**
	 * 128固定だったのをランダムに変更
	 * makeAnsHBN()がHit & Blowに妥当な数を生成
	 */
	public Game3() {
		this(makeAnsHBN());
	}

	public Game3(int ans) {
		super(ans);
	}

	/**
	 * Hit & Blowに妥当な数をランダムに生成
	 * コンストラクタで呼び出すためstatic
	 */
	private static int makeAnsHBN() {
		int ans = 0;
		int[] ansList = new int[HBNumber.DIGIT_NUM];	//初期値は0
		boolean[] isUsed = new boolean[10];			//初期値はfalse
		Random rnd = new Random();
		
		/* 一番大きい位(3桁なら100の位)は０になってはいけないので特別処理 */
		ansList[0] = rnd.nextInt(9) + 1;
		isUsed[ansList[0]] = true;
		
		/* 固定回数(桁数)のランダム生成でansを作る
		 * ランダムの上限を減らしつつすでに入ってる数字は飛ばす
		 * 例:1回目：0-9のランダムで５だった時2回目は0-8でランダムを生成する
		 * ただし、5はすでに埋まってるので、５を飛ばす。具体的には5-8を６−９に割り当てる */
		for (int i = 1; i < HBNumber.DIGIT_NUM; i++) {
			ansList[i] = rnd.nextInt(10-i);
			for (int j=0; j <= ansList[i]; j++) {
				if (isUsed[j]) {
					ansList[i]++;
				}
			}
			isUsed[ansList[i]] = true;
		}
		
		/* 連結してansを作成 */
		for (int i = 0; i < HBNumber.DIGIT_NUM; i++) {
			ans *= 10;
			ans += ansList[i];
		}
		
		return ans;
	}
}
