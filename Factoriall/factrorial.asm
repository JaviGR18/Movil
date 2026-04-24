; ============================================================
; factorial.asm  -  Calcula N! de forma recursiva (MASM x86)
; ============================================================

.686
.MODEL FLAT, C
.CODE

PUBLIC factorial

; ----------------------------------------------------------
; factorial PROC
; ----------------------------------------------------------
factorial PROC
    ; --- Configuración inicial de la pila ---
    PUSH    EBP                     ; Guarda el puntero base anterior
    MOV     EBP, ESP                ; Establece el nuevo puntero base para esta función
    PUSH    EBX                     ; Guarda EBX porque lo vamos a usar

    MOV     EBX, [EBP+8]           ; Carga el argumento N desde la pila: EBX = N

    ; --- Verificar caso base ---
    CMP     EBX, 0                  ; Compara N con 0
    JNE     caso_recursivo          ; Si N != 0, salta al caso recursivo
    MOV     EAX, 1                  ; caso base: factorial(0) = 1
    JMP     fin_factorial           ; Salta directo al final

caso_recursivo:
    ; --- Preparar y hacer la llamada recursiva ---
    MOV     EAX, EBX                ; Copia N a EAX
    DEC     EAX                     ; EAX = N - 1
    PUSH    EAX                     ; Mete (N-1) como argumento al stack
    CALL    factorial               ; Llamada recursiva: factorial(N-1)
    ADD     ESP, 4                  ; Limpia el argumento que metimos al stack

    ; --- Multiplicar N * factorial(N-1) ---
    IMUL    EAX, EBX                ; EAX = factorial(N-1) * N  →  resultado final

fin_factorial:
    ; --- Restaurar registros y salir ---
    POP     EBX                     ; Restaura EBX a su valor original
    POP     EBP                     ; Restaura el puntero base
    RET                             ; Regresa al llamador con el resultado en EAX
factorial ENDP

END