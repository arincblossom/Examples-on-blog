#include <Windows.h>

LRESULT CALLBACK WndProc(HWND, UINT, WPARAM, LPARAM);
HINSTANCE g_hInst;
LPCTSTR lpszClass = TEXT("First");

int APIENTRY WinMain(HINSTANCE hInstance, HINSTANCE hPrevInstance, LPSTR lpszCmdParam, int nCmdShow) {
	// WinMain : Win32 API������ Entry Point, â�� ����� ��Ÿ���� ����
	// Instance : Ŭ������ �޸𸮿� ������ ������ ��ü
	HWND hWnd;
	MSG Message;
	WNDCLASS WndClass;
	g_hInst = hInstance;

	// ������ Ŭ������ ��� �� �� CreateWindow �Լ��� ȣ���Ͽ� ������ �����.
	WndClass.cbClsExtra = 0;
	WndClass.cbWndExtra = 0;
	WndClass.hbrBackground = (HBRUSH)GetStockObject(WHITE_BRUSH);
	// WHITE_BRUSH, BLACK_BRUSH, LTGRAY_BRUSH, GRAY_BRUSH
	WndClass.hCursor = LoadCursor(NULL, IDC_ARROW);
	// IDC_... ARROW, CROSS, IBEAM, NO, WAIT
	WndClass.hIcon = LoadIcon(NULL,IDI_APPLICATION);
	WndClass.hInstance = hInstance;
	WndClass.lpfnWndProc = (WNDPROC)WndProc;
	WndClass.lpszClassName = (LPCWSTR)lpszClass;
	WndClass.lpszMenuName = NULL;
	WndClass.style = CS_HREDRAW | CS_VREDRAW;
	RegisterClass(&WndClass);
	// ���� Ŭ���� ���

	/** WNDCLASS ����
	typedef struct tagWNDCLASS {
	UINT style; // ������ ��Ÿ�� ���� (������ ����)
	WNDPROC lpfnWndProc; // �޽��� ó���Լ� ���� (WndProc�� ���� ������)
	int cbClsExtra; // ���� ����, ���� 0
	int cbWndExtra; // ���� ����, ���� 0
	HINSTANCE hInstance; // �� ������ Ŭ������ ����ϴ� ���α׷��� ��ȣ (�״�� ����)
	HICON hIcon; // â�� ��� ������
	HCURSOR hCursor; // â�� ���콺 ������
	HBRUSH hbrBackground; // ���� ������, GetStockObject �Լ� ���, �Ϲ������� WHITE_BRUSH
	LPCSTR lpszMenuName; // ���α׷����� ����� �޴�, ���ҽ����� �߰�. ������ NULL
	LPCSTR lpszClassName; // ������ Ŭ������ �̸�, CreateWindow �Լ��� ���޵Ǵ� �̸� ���
	} WNDCLASS;
	**/

	hWnd = CreateWindow(lpszClass, lpszClass, WS_OVERLAPPEDWINDOW, CW_USEDEFAULT, CW_USEDEFAULT, CW_USEDEFAULT, CW_USEDEFAULT,
	NULL, (HMENU)NULL, hInstance, NULL);
	// HWND CreateWindow(lpszClassName, lpszWindowName, dwStyle, x, y, nWidth, nHeight, hwndParent, hmenu, hinst, lpvParam);
	/* Ŭ��������, Ÿ��Ʋ�̸�, ��������, ũ��� ��ġ(CW_USEDEFAULT�� ������ �˾Ƽ�),
	�θ� ������ �ڵ鷯, �޴��� �ڵ�, ���α׷��� �ڵ�, CREATESTRUCT��� ����ü�� ����(���� NULL)*/
	ShowWindow(hWnd, nCmdShow);
	/* nCmdShow
	SW_HIDE
	SW_MINIMIZE
	SW_RESTORE
	SW_SHOW
	SW_SHOWNORMAL
	*/
	// ���� ������ �ʿ� ���� �׳� �ѱ�� ��

	// ==> WndClass ���� -> �޸� �� Create -> Show -> Message Loop

	// Message Loop : �޽����� ������ �ʿ信 ���� ���¸� �ٲ� �� ���α׷����� ����(WM_QUIT)�� ���� �� �� ����
	while(GetMessage(&Message, 0, 0, 0)) { // -> �ý����� �����ϴ� �޽��� ť���� �޽����� ����, ù��° �μ��� ����
		TranslateMessage(&Message); // -> Ű���� �Է� �޽����� �����Ͽ� ���α׷����� ��밡�� �ϰ� ��
		DispatchMessage(&Message); // -> �޽����� WndProc �Լ��� ����
	}
	return Message.wParam;
}

LRESULT CALLBACK WndProc(HWND hWnd, UINT iMessage, WPARAM wParam, LPARAM lParam) {
	// Messsage �߻��� ���α׷��� ������ ó��
	switch(iMessage) {
	case WM_DESTROY:
		PostQuitMessage(0);
		return 0;
	}
	return (DefWindowProc(hWnd, iMessage, wParam, lParam));
}