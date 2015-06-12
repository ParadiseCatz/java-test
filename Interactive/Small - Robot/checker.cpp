#include <iostream>
#include <fstream>
#include <assert.h>
#include <cstdlib>
#include <cstring>

#define MAXN 200
#define up "up"
#define down "down"
#define left "left"
#define right "right"
#define ok "ok"
#define fail "fail"

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

bool is_number(const std::string& s)
{
    std::string::const_iterator it = s.begin();
    while (it != s.end() && std::isdigit(*it)) ++it;
    return !s.empty() && it == s.end();
}

char cmd[10], key[MAXN][MAXN];
bool vis[MAXN][MAXN];
int n, q, m, rx, ry, ans;
const int dr[] = { -1, 1, 0, 0 };
const int dc[] = { 0, 0, -1, 1 };

void check (int pil) {
    check(q > 0);
    q--;
    int px, py;
    px = rx + dr[pil];
    py = ry + dc[pil];
    if (key[px][py] == 'X')
        cout << fail << endl << flush;
    else {
        cout << ok << endl << flush;
        rx = px;
        ry = py;
    }
}

void dfs( int r, int c )
{
   ++ans;
   vis[r][c] = 1;

   for ( int dir=0; dir<4; ++dir ) {
      int nr = r+dr[dir], nc = c+dc[dir];
      if (!vis[nr][nc] && key[nr][nc] == '.')
      {
          dfs(nr, nc);
      }
   }
}

int main(int argc , char** argv) {
    ifstream tc;

    string S, header;

    tc.open(argv[1]);
    tc >> n >> m;
    char dum;
    for (int i = 0; i < n; ++i)
    {
        for (int j = 0; j < m; ++j)
        {
            tc >> key[i][j];
            if (key[i][j] == 'R')
            {
                rx = i;
                ry = j;
            }
        }
    }
    tc.close();
    q = 5000;

    dfs(rx, ry);


    cout << n << " " << m << endl << flush;

    while (true)
    {
        check(scanf("%s", cmd) == 1);

        if (!strcmp(cmd, up)) {
            check(0);
            
        } else if (!strcmp(cmd, down)) {
            check(1);
            
        } else if (!strcmp(cmd, left)) {
            check(2);
            
        } else if (!strcmp(cmd, right)) {
            check(3);
            
        } else if (is_number(cmd)) {
            if (atoi(cmd) == ans)
            {
                ac();
            }
            else
            {
                wa();
            }
        } else {
            // invalid query
            wa();
        }
    }

    return 0;
}

