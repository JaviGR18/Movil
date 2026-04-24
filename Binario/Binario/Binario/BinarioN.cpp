#include <iostream>
using namespace std;

extern "C" void convertirBinario(int n, char* buffer);

int main() {
    int n;
    char buffer[33]; // 32 bits máximo para un entero + '\0'

    // Pedimos el número al usuario
    cout << "Ingresa el valor entero: ";
    cin >> n;

    // Validamos que no sea negativo
    if (n < 0) {
        cout << "Error: no se admiten numeros negativos." << endl;
        return 1;
    }

    // Llamamos a la función en ensamblador
    convertirBinario(n, buffer);

    // Mostramos el resultado igual al ejemplo
    cout << "El valor binario es: \"" << buffer << "\"" << endl;

    return 0;
}