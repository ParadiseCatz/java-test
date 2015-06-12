/**
 * Start of batchioi2010 checker template
 * */
 
#include <vector>
#include <map>
#include <set>
#include <queue>
#include <deque>
#include <stack>
#include <algorithm>
#include <utility>
#include <numeric>
#include <sstream>
#include <iostream>
#include <iomanip>
#include <cstdio>
#include <cstring>
#include <cmath>
#include <cstdlib>
#include <ctime>
#include <cassert>
using namespace std;

#define OK "[OK]"
#define FAILED "[FAILED]"

/**
 * This program will be called with command: [PROBLEM_PATH]/grader [INPUT] [OUTPUT] [OUTPUT_KEY], with argc == 4
 * You are free to re-arrange, modify or ignore arguments in your problem configuration.
 * */

int main(int argc , char** argv) {
	//assert(argc == 4);
	
	FILE *fin = fopen(argv[1], "r");		// input
	FILE *fout = fopen(argv[2], "r");		// output si anak
	FILE *fout_key = fopen(argv[3], "r");	// output jawaban
	
	//read input
	int N;
	fscanf(fin,"%d",&N);
	char a[N][N];
	for (int i = 0; i < N; ++i)
	{
		fscanf(fin,"%s",a[i]);
	}
	bool flag[N][N];
	bool hor[N], ver[N], diag1[2*N], diag2[2*N];

	memset(flag,false,sizeof(flag));
	memset(hor,false,sizeof(hor));
	memset(ver,false,sizeof(ver));
	memset(diag1,false,sizeof(diag1));
	memset(diag2,false,sizeof(diag2));

	int count = 0;
	for (int i = 0; i < N; ++i)
	{
		for (int j = 0; j < N; ++j)
		{
			if (a[i][j] == '*')
			{
				count++;
				int x, y;
				x = i+j;
				y = i-j+N;
				if (count > N || hor[i] || ver[j] || diag1[x] || diag2[y])
				{
					cout << FAILED << endl;
					return 0;
				}
				hor[i] = true;
				ver[j] = true;
				diag1[x] = true;
				diag2[y] = true;

			}
		}
	}
	cout << OK << endl;

	return 0;
}