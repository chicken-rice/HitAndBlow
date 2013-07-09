/**
 * judgeでhit、blowを計算するようにオーバーライド
 * @author hiroki
 *
 */
public class Game2 extends Game1 {

	public Game2() {
		super();
	}

	public Game2(int ans) {
		super(ans);
	}

	/**
	 * 親クラス(execute())からアクセスできるようにprotectedまでの制限
	 */
	protected boolean judge(HBNumber inputHBN) {
		int hit = 0;
		int blow = 0;
		
		/* 0-9の配列を用意し、その数字が出現する桁番号(1の位:0、10の位:1,100の位:2・・・) 
		 * を格納しているので、-1以外であればどこかの桁に出現している。
		 * そのため、-1でない場所が重なればhitもしくはblowで、
		 * 格納されている数値が一緒の時出現桁数が一致しているのでhitとなる*/
		for (int i = 0; i < 10; i++) {
			if (inputHBN.getNumDataByInd(i) >= 0 &&
					this.ansHBN.getNumDataByInd(i) >= 0) {
				if (inputHBN.getNumDataByInd(i) == 
						this.ansHBN.getNumDataByInd(i)) {
					hit++;
				} else {
					blow++;
				}
			}
		}
		
		if (hit == HBNumber.DIGIT_NUM) {
			System.out.println("\n正解です！！\n");
			return true;
		} else {
			System.out.println("Hit : " + hit);
			System.out.println("blow: " + blow + "\n");
			return false;
		}
	}
	
	/**
	 * 親クラス(execute())からアクセスできるようにprotectedまでの制限
	 */
	protected void failMessage() {
		System.out.print("残念でした。");
		super.failMessage();
	}
}
