/*
 * Official Checker
 * 
 * DNA (Tracy Filbert, with modification)
 * @author Ahmad Zaky
 */
 
#include <iostream>
#include <fstream>
#include <assert.h>
#include <cstdlib>
#include <cstring>

#define MAXN 1000
#define TANYA "TANYA"
#define JAWAB "JAWAB"
#define YA "YA"
#define TIDAK "TIDAK"

using namespace std;
 
void ac() {
    fprintf(stderr, "AC\n");
    exit(0);
}

void wa() {
    fprintf(stderr, "WA\n");
    exit(0);
}

void check(bool condition)
{
    if (!condition)
    {
        wa();
    }
}

int fail[MAXN + 5], n, q;
char cmd[10], str[MAXN + 5], text[MAXN + 5];

void buildFailTable(char *pattern, int m){
    int i = 0, j = -1;
    fail[0] = -1;
    while (i < m){
        while (j >= 0 && pattern[i] != pattern[j]) j = fail[j];
        i++; j++;
        fail[i] = j;
    }
}

bool kmpSearch(char *pattern, int m) {
    int i = 0, j = 0;
    buildFailTable(pattern, m);
    while (i < n){
        while (j >= 0 && text[i] != pattern[j]) j = fail[j];
        i++; j++;
        if (j == m){
            return true;
            j = fail[j];
        }
    }
    return false;
}

int main(int argc , char** argv) {
    ifstream tc;

    string S, header;

    tc.open(argv[1]);
    tc >> header;
    tc >> n >> q >> S;
    tc.close();

    cout << header << endl << flush;
    cout << n << " " << q << endl << flush;

    strcpy(text, S.c_str());

    while (true)
    {
        check(scanf("%s %s", cmd, str) == 2);

        if (!strcmp(cmd, TANYA)) {
            int m = strlen(str);
            check(1 <= m && m <= n);

            check(q > 0);
            q--;

            if (kmpSearch(str, m)) {
                cout << YA << endl << flush;
            } else {
                cout << TIDAK << endl << flush;
            }
        } else if (!strcmp(cmd, JAWAB)) {
            if (!strcmp(str, text)) {
                ac();
            } else {
                wa();
            }
            break;
        } else {
            // invalid query
            wa();
        }
    }

    return 0;
}
