; ============================================================
; binario.asm  -  Convierte un entero N a su representacion
;                 binaria como cadena de caracteres (MASM x86)
; ============================================================

.686
.MODEL FLAT, C
.CODE

PUBLIC convertirBinario

; ----------------------------------------------------------
; convertirBinario PROC
; Parametros:
;   [EBP+8]  = N (entero a convertir)
;   [EBP+12] = buffer (puntero a la cadena resultado)
; ----------------------------------------------------------
convertirBinario PROC
    ; --- Configuración inicial de la pila ---
    PUSH    EBP                     ; Guarda el puntero base anterior
    MOV     EBP, ESP                ; Establece el nuevo puntero base para esta función
    PUSH    EBX                     ; Guarda EBX porque lo vamos a usar
    PUSH    ECX                     ; Guarda ECX porque lo vamos a usar
    PUSH    EDX                     ; Guarda EDX porque lo vamos a usar
    PUSH    EDI                     ; Guarda EDI porque lo vamos a usar

    MOV     EBX, [EBP+8]           ; Carga el argumento N desde la pila: EBX = N
    MOV     EDI, [EBP+12]          ; Carga la dirección del buffer: EDI = buffer

    ; --- Verificar caso especial: si N == 0 ---
    CMP     EBX, 0                  ; Compara N con 0
    JNE     iniciar_conversion      ; Si N != 0 salta a la conversión
    MOV     BYTE PTR [EDI], '0'    ; Caso especial: escribe el caracter '0'
    MOV     BYTE PTR [EDI+1], 0    ; Escribe el terminador de cadena '\0'
    JMP     fin_conversion          ; Salta directo al final

iniciar_conversion:
    ; --- Dividir N entre 2 y guardar residuos en la pila ---
    XOR     ECX, ECX               ; Pone ECX en 0, lo usamos como contador de bits

ciclo_division:
    CMP     EBX, 0                  ; Compara N con 0
    JE      ciclo_construccion      ; Si N == 0 ya terminamos de dividir

    XOR     EDX, EDX               ; Limpia EDX antes de dividir
    MOV     EAX, EBX               ; Copia N a EAX para poder dividir
    MOV     EBX, 2                 ; Carga el divisor 2 en EBX
    DIV     EBX                    ; Divide: EAX = cociente, EDX = residuo

    PUSH    EDX                    ; Mete el residuo a la pila para invertirlo después
    MOV     EBX, EAX               ; Actualiza N con el cociente
    INC     ECX                    ; Aumenta el contador de bits en 1
    JMP     ciclo_division         ; Repite el ciclo

ciclo_construccion:
    ; --- Sacar residuos de la pila y armar la cadena ---
    CMP     ECX, 0                  ; Compara el contador con 0
    JE      poner_terminador        ; Si ya no hay bits, salta al terminador

    POP     EDX                    ; Saca el residuo de la pila
    ADD     EDX, '0'               ; Convierte 0 o 1 al caracter '0' o '1'
    MOV     BYTE PTR [EDI], DL     ; Escribe el caracter en la posición actual del buffer
    INC     EDI                    ; Avanza al siguiente espacio del buffer
    DEC     ECX                    ; Reduce el contador en 1
    JMP     ciclo_construccion     ; Repite el ciclo

poner_terminador:
    MOV     BYTE PTR [EDI], 0      ; Escribe '\0' para cerrar la cadena

fin_conversion:
    ; --- Restaurar registros y salir ---
    POP     EDI                    ; Restaura EDI a su valor original
    POP     EDX                    ; Restaura EDX a su valor original
    POP     ECX                    ; Restaura ECX a su valor original
    POP     EBX                    ; Restaura EBX a su valor original
    POP     EBP                    ; Restaura el puntero base
    RET                            ; Regresa al programa principal
convertirBinario ENDP

END