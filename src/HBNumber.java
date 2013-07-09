/**
 * DIGIT_NUM: 桁数
 * numData: triDigiをHit & Blowを計算しやすい形に変換したもの
 * triData: Hit & Blowで使用する数字。3桁で重複なし。
 * @author hiroki
 */
public class HBNumber {
	static final int DIGIT_NUM = 3;
    private int[] numData; // 675 -> numData[5]=0,numData[7]=1,numData[6]=2,numData[other]=-1
    private int triDigi;
	/**
	 * @throws HBNumberException: triDigiが妥当でない数字の時に例外 
	 * @param triDigit: コンストラクタの引数として3桁の重複なし整数。
	 */
	public HBNumber(int triDigi) throws HBNumberException {
		numData = new int[10]; //0-9で10
		this.triDigi = triDigi;
		
		/* 負の3桁の時の例外 */
		if (triDigi <= -(int)(Math.pow(10, DIGIT_NUM-1)) &&
				triDigi >= -(int)(Math.pow(10, DIGIT_NUM)-1)) {
			throw new HBNumberException(HBNumberException.MINUS, triDigi,
					                     "argument(int) should be plus");
		}
		
		/* 3桁でない時の例外 */
		if (triDigi < (int)(Math.pow(10, DIGIT_NUM-1)) ||
				triDigi > (int)(Math.pow(10, DIGIT_NUM)-1)) {
			throw new HBNumberException(HBNumberException.NOT_TRI, triDigi,
					                     "argument(int) should be triple-digit");
		}
		
		/* 初期化 */
		for (int i = 0; i < 10; i++) {
			numData[i] = -1;
		}
		
		int tempNum = triDigi;
		for (int i = 0; i < DIGIT_NUM; i++) {
			int mod = tempNum % 10;
			tempNum /= 10;
			/* 3桁の中に同じ数字が出てきた時の例外 */
			if (numData[mod] != -1) {
				throw new HBNumberException(HBNumberException.OVERLAP, triDigi,
	                                         "argument(int) should NOT be overlap");
			}
			numData[mod] = i;
		}
	}
	
	/**
	 * printで使用しやすいようにオーバーライド
	 */
	public String toString() {
		return ("" + triDigi);
	}

	/**
	 * triDigiのgeter
	 */
	public int getTriDigi() {
		return this.triDigi;
	}
	
	/**
	 * numDataのgetter。indexを与えて値を取り出す。
	 */
	public int getNumDataByInd(int i) {
		return this.numData[i];
	}
}
