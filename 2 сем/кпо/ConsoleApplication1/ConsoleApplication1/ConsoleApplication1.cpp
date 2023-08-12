//#include "stdafx.h"
#include <iostream>

using namespace std;

int main()
{
	int i = 1;
	short s[] = { 1,2,3 };

	int* pi[10];

	float f[3] = { 123E-10f, 0.1f };
	double d[5];

	float* pf[] = { &(f[1]), &(f[2]) };
	double* pd[] = { &d[0] };

	char c[20] = "a";
	char* pch = "12345";
	int size = sizeof(d) + sizeof(pi) + sizeof(pch);
	cout << sizeof(d) << endl;//8*5=40
	cout << sizeof(pi) << endl;//4*10=40
	// ответ 84



  return 0;

}
