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
	while(1)
	{
		int n,q, a, b;
		int starts[200001], ends[200001], freq[200001], ar[100005];
		fscanf(fin,"%d",&n);
		if (n==0)
			break;
		fscanf(fin,"%d",&q);
		int x,cnt=1,p=0;
		scanf("%d",&x);
		starts[x+100000] = 0;
		ar[0] = x;
		for (int i=1;i<n;i++)
		{
			int t;
			scanf("%d",&t);
			ar[i] = t;
			if (t!=x)
			{
				ends[x+100000] = i-1;
				freq[x+100000] = cnt;
				m[++p]=cnt;
				cnt=1;
				x=t;
				starts[x+100000] = i;
			}
			else
				cnt++;
		}
		ends[x+100000] = n-1;
		freq[x+100000] = cnt;
		m[++p]=cnt;
		for (int i=0;i<q;i++)
		{
			scanf("%d%d",&a,&b);
			a--; b--;
			int keyz, ank;
			fscanf(fout_key,"%d",&keyz);
			fscanf(fout,"%d",&ank);
			if (ank == keyz)
			{
				continue;
			} else {
				int freqKeys, freqAnk;
				if (keyz == ar[a])
				{
					freqKeys = min(ends[keyz+100000],b) - a +1;
				} else if (keyz == ar[b])
				{
					freqKeys = b - max(starts[keyz+100000],a) +1;
				} else {
					freqKeys = freq[keyz+100000];
				}
				if (ank == ar[a])
				{
					freqAnk = min(ends[ank+100000],b) - a +1;
				} else if (ank == ar[b])
				{
					freqAnk = b - max(starts[ank+100000],a) +1;
				} else {
					freqAnk = freq[ank+100000];
				}
				if (freqAnk != freqKeys)
				{
					cout << FAILED << endl;
				}
			}
		}
	}
	cout << OK << endl;
	return 0;
}