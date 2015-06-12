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

int ubah(char x)
{
	if (x == 's')
		return 0;
	if (x == 'p')
		return 1;
	if (x == 'g')
		return 2;
	if (x == 'd')
		return 3;
}

int main(int argc , char** argv) {
	//assert(argc == 4);
	
	FILE *fin = fopen(argv[1], "r");		// input
	FILE *fout = fopen(argv[2], "r");		// output si anak
	FILE *fout_key = fopen(argv[3], "r");	// output jawaban
	
	//read input
	int N;
	fscanf(fin,"%d",&N);
	for (int tc = 0; tc < N; tc++)
	{
		vector<char> sa,skey;
		while (1)
		{
			char x;
			fscanf(fout,"%c",&x);
			if (x==10)
				break;
			sa.push_back(x);
		}	
		while (1)
		{
			char x;
			fscanf(fout_key,"%c",&x);
			if (x==10)
				break;
			skey.push_back(x);
		}
		int la,lkey;
		la = sa.size();
		lkey = skey.size();
		if (la != lkey)
		{
			cout << FAILED << endl;
			return 0;
		}
		int ta[10],tkey[10];
		for (int i = 1; i < 10; i++)
			ta[i]=0, tkey[i]=0;
		for (int i = 0; i < la; i++)
			ta[sa[i]-'0']++;
		for (int i = 0; i < la; i++)
			tkey[skey[i]-'0']++;
		for (int i = 1; i < 10; i++)
			if (ta[i] != tkey[i])
			{
				cout << FAILED << endl;
				return 0;
			}
	}
	cout << OK << endl << 100 << endl;
		
	return 0;
}
