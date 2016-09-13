// ���� �⺻��
public class Singleton {
	private static Singleton instance = new Singleton();

	private Singleton();

	public static Singleton getInstance() {
		return instance;
	}
}
// ������: �ν��Ͻ��� �Ź� �ε�� �� ���� new ������ �����Ͽ� �ð��̳� cpu�� ������ �ش�

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
// ������: instance�� �������� new�� ������ �� �־� �ӵ��� cpu�� �̵�
// ���� ���� ������: ��Ƽ�����忡���� �ҿ����

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
// ������: �ټ��� �����忡�� �ν��Ͻ��� �ҷ��� �������� ȣ��ȴ�
// ������: ���ÿ� ȣ��Ǹ� ó���ӵ��� �������� (���� Task ���Ḧ ���)

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
// ������: ���� ���� �Ϲ������� ���̴� �̱����� ���¶�� �Ҹ� ������ ����
// JVM�� Ŭ���� �ε� ������ �ε� ��Ŀ������ ����Ͽ� ����ȭ������ �ذ�