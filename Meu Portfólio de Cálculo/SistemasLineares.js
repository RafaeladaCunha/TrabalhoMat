let n ;
let quantidadeM;
let resultado;

console.log('Digite o número de equações');

    function g(matriz, c) {
        var n = matriz.length;
        var i, j, k;

      
        for (i = 0; i < n; i++) {
            matriz[i][n] = c[i];
        }



        for (i = 0; i < n; i++) {
            if (matriz[i][i] === 0) {
                for (j = i + 1; j < n; j++) {
                    if (matriz[j][i] !== 0) {
                        var temp = matriz[i];
                        matriz[i] = matriz[j];
                        matriz[j] = temp;
                        console.log( + (i + 1) + " com linha " + (j + 1));
                        break;
                    }
                }
            }

            
        }
    }

