// 완전 기본형
public class Singleton {
	private static Singleton instance = new Singleton();

	private Singleton();

	public static Singleton getInstance() {
		return instance;
	}
}
// 문제점: 인스턴스가 매번 로드될 때 마다 new 구문을 실행하여 시간이나 cpu에 문제를 준다

// Lazy Initialization
public class Singleton {
	private static Singleton instance;

	private Singleton();

	public static Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}
}
// 개선점: instance가 없을때만 new를 선언할 수 있어 속도나 cpu에 이득
// 위의 둘의 문제점: 멀티스레드에서는 소용없다

// Thread Safe
public class Singleton {
	private static Singleton instance;

	private Singleton();

	public static synchronized Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}
}
// 개선점: 다수의 스레드에서 인스턴스를 불러도 같은것이 호출된다
// 문제점: 동시에 호출되면 처리속도가 느려진다 (이전 Task 종료를 대기)

// Initialization On Demand Holder Idiom
public class InitializationOnDemandHolderIdiom {
	private InitializationOnDemandHolderIdiom () {}
	private static class Singleton {
		private static final InitializationOnDemandHolderIdiom instance = new InitializationOnDemandHolderIdiom();
	}
	
	public static InitializationOnDemandHolderIdiom getInstance () {
		return Singleton.instance;
	}
}
// 개선점: 현재 가장 일반적으로 쓰이는 싱글톤의 형태라고 불릴 정도로 좋음
// JVM의 클래스 로드 시점과 로드 매커니즘을 사용하여 동기화문제를 해결