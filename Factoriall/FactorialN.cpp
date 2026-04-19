#include <iostream>
using namespace std;


extern "C" int factorial(int n);

int main() {
    int n = 5;
    int resultado = factorial(n); 
    cout << n << "! = " << resultado << endl;
    return 0;
}