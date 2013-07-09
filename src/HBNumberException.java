/**
 * HBNumber(Hit & Blow Number)のコンストラクタに埋め込んでいる例外
 * 以下の状況が例外にあたる
 * -数字が重なっている
 * -3桁になっている
 * -負数の3桁になっている
 * 
 * @author hiroki
 */
public class HBNumberException extends Exception {
	private static final long serialVersionUID = -8400250302734542700L;
	final static int NOT_TRI = 0;
	final static int OVERLAP = 1;
	final static int MINUS = 2;
	
    private int status, num;
	/**
	 * @param status どの例外か
	 * @param num コンストラクタに渡された引数(int)
	 * @param message 例外の説明 
	 */
	public HBNumberException(int status, int num, String message) {
		super(message);
		this.status = status;
		this.num = num;
	}
	
	/**
	 * numを返すgetter
	 */
	public int getNum() {
		return this.num;
	}

	
	/**
	 * statusを返すgetter
	 */
	public int getStatus() {
		return this.status;
	}
}
