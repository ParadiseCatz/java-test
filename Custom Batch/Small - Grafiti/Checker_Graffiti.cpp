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
#define MAXN 1000
#define LOGMAXN 20
#define INF (int)1e9
#define lc(a) (2*a + 1)
#define rc(a) (2*a + 2)
using namespace std;

/**
 * This program will be called with command: [PROBLEM_PATH]/grader [INPUT] [OUTPUT] [OUTPUT_KEY], with argc == 4
 * You are free to re-arrange, modify or ignore arguments in your problem configuration.
 * */
int len(int l) {
	if(l <= 15) return l;
	else {
		l -= 15;
		return l/2 + 15;
	}
}

int toDecimal(char c) {
	if(c <= '9') return c - '0';
	return 10 + c - 'A';
}

bool check0(string ans) {
	if(ans.length() == 0) return false;
	return (ans[0] != ' ' && ans[ans.length() - 1] != ' ');
}

bool check1(string in, string ans) {
	int ptr = 0;
	int consecutiveSpace = 0;

	for(int i = 0; i < ans.length(); i++) {
		if(ans[i] != ' ') {
			if(ans[i] == in[ptr]) ptr++;
			else return false;

			consecutiveSpace = 0;
		} else {
			consecutiveSpace++;
		}

		if(consecutiveSpace > 1) return false;
	}

	return ptr == in.length();
}

bool check2(int l, string ans) {
	map<int, int> cnt;
	int value = 0;

	for(int i = 0; i <= ans.length(); i++) {
		if(ans[i] == ' ' || i == ans.length()) {
			if(cnt.count(value) > 0) 
				return false;

			cnt[value] = 1;
			value = 0;
		} else {
			value = value * 16 + toDecimal(ans[i]);
		}
	}

	for(int i = 1; i <= l; i++)
		if(cnt.count(i) != 1) return false;

	return true;
}

int main(int argc , char* argv[]) {
	//assert(argc == 4);
	
	FILE *fin 	= fopen(argv[1], "r");		// input
	// FILE *fout 	= fopen(argv[2], "r");		// output jawaban
	FILE *foutA = fopen(argv[3], "r");		// output si anak
	string input, userSolution, judgeSolution;
	char buff[1024];

	fgets(buff, 1024, fin);
	input = buff;
	input = input.substr(0, input.length() - 1);

	// fgets(buff, 1024, fout);
	// judgeSolution = buff;
	// judgeSolution = judgeSolution.substr(0, judgeSolution.length() - 1);

	if(fgets(buff, 1024, foutA) != NULL && strlen(buff) > 1) {
		userSolution = buff;
		userSolution = userSolution.substr(0, userSolution.length() - 1);
	
		bool accepted = check0(userSolution) && check1(input, userSolution) && check2(len(input.length()), userSolution);
		if (accepted) puts("AC");
		else puts("WA");
	} else puts("WA");

	return 0;
}
