#include "christmas_tree.h"
using namespace std; 
void main()
 {
	char ch;
    for (sy = 0.8f; sy > 0.0f; sy -= 0.02f,cout<<'\n')  
        for (sx = -0.35f; sx < 0.35f; sx += 0.01f)
		{//ch=f(0, 0, PI * 0.5f, 1.0f, n) < 0 ? '*' : ' ';
			ch=f(0, 0, PI * 0.5f, 1.0f, 3) < 0 ? '*' : ' ';
		cout<<ch;}
}
