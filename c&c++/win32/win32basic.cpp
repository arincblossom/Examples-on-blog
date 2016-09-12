#include <Windows.h>

LRESULT CALLBACK WndProc(HWND, UINT, WPARAM, LPARAM);
HINSTANCE g_hInst;
LPCTSTR lpszClass = TEXT("First");

int APIENTRY WinMain(HINSTANCE hInstance, HINSTANCE hPrevInstance, LPSTR lpszCmdParam, int nCmdShow) {
	// WinMain : Win32 API에서의 Entry Point, 창을 만들고 나타내는 역할
	// Instance : 클래스가 메모리에 실제로 구현된 실체
	HWND hWnd;
	MSG Message;
	WNDCLASS WndClass;
	g_hInst = hInstance;

	// 윈도우 클래스를 등록 한 후 CreateWindow 함수를 호출하여 윈도를 만든다.
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
	// 윈도 클래스 등록

	/** WNDCLASS 정의
	typedef struct tagWNDCLASS {
	UINT style; // 윈도의 스타일 정의 (윈도의 형태)
	WNDPROC lpfnWndProc; // 메시지 처리함수 지정 (WndProc로 보통 정해짐)
	int cbClsExtra; // 예약 영역, 보통 0
	int cbWndExtra; // 예약 영역, 보통 0
	HINSTANCE hInstance; // 이 윈도우 클래스를 사용하는 프로그램의 번호 (그대로 쓰자)
	HICON hIcon; // 창의 출력 아이콘
	HCURSOR hCursor; // 창의 마우스 포인터
	HBRUSH hbrBackground; // 윈도 배경색상, GetStockObject 함수 사용, 일반적으로 WHITE_BRUSH
	LPCSTR lpszMenuName; // 프로그램에서 사용할 메뉴, 리소스에서 추가. 없으면 NULL
	LPCSTR lpszClassName; // 윈도우 클래스의 이름, CreateWindow 함수에 전달되는 이름 사용
	} WNDCLASS;
	**/

	hWnd = CreateWindow(lpszClass, lpszClass, WS_OVERLAPPEDWINDOW, CW_USEDEFAULT, CW_USEDEFAULT, CW_USEDEFAULT, CW_USEDEFAULT,
	NULL, (HMENU)NULL, hInstance, NULL);
	// HWND CreateWindow(lpszClassName, lpszWindowName, dwStyle, x, y, nWidth, nHeight, hwndParent, hmenu, hinst, lpvParam);
	/* 클래스지정, 타이틀이름, 윈도형태, 크기와 위치(CW_USEDEFAULT는 윈도가 알아서),
	부모 윈도의 핸들러, 메뉴의 핸들, 프로그램의 핸들, CREATESTRUCT라는 구조체의 번지(보통 NULL)*/
	ShowWindow(hWnd, nCmdShow);
	/* nCmdShow
	SW_HIDE
	SW_MINIMIZE
	SW_RESTORE
	SW_SHOW
	SW_SHOWNORMAL
	*/
	// 따로 수정할 필요 없이 그냥 넘기면 됨

	// ==> WndClass 정의 -> 메모리 상에 Create -> Show -> Message Loop

	// Message Loop : 메시지를 꺼내고 필요에 따라 형태를 바꾼 후 프로그램으로 전달(WM_QUIT)가 전달 될 때 까지
	while(GetMessage(&Message, 0, 0, 0)) { // -> 시스템이 유지하는 메시지 큐에서 메시지를 읽음, 첫번째 인수에 저장
		TranslateMessage(&Message); // -> 키보드 입력 메시지를 가공하여 프로그램에서 사용가능 하게 함
		DispatchMessage(&Message); // -> 메시지를 WndProc 함수로 전달
	}
	return Message.wParam;
}

LRESULT CALLBACK WndProc(HWND hWnd, UINT iMessage, WPARAM wParam, LPARAM lParam) {
	// Messsage 발생시 프로그램의 반응을 처리
	switch(iMessage) {
	case WM_DESTROY:
		PostQuitMessage(0);
		return 0;
	}
	return (DefWindowProc(hWnd, iMessage, wParam, lParam));
}