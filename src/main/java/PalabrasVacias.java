import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.Map;


public class PalabrasVacias {
    public static void ordenamientoArreglos(String[] palabras, int[] apariciones) {
        for (int i = palabras.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (j + 1 <= i && apariciones[j] < apariciones[j + 1]) {
                    String aux = palabras[j];
                    int iux = apariciones[j];
                    palabras[j] = palabras[j + 1];
                    apariciones[j] = apariciones[j + 1];
                    palabras[j + 1] = aux;
                    apariciones[j + 1] = iux;
                }
            }
        }
    }
    static int palabrasTotales;
    public static void main(String args[]) throws IOException {
        palabrasTotales =0;
        Map<String, Integer> palabras = new HashMap();
        String argumentos[] = args[0].split(",");


        if (args.length == 0){
            System.out.println("falta el nombre del archivo");
            System.exit(1);

        }
        String filevacias = argumentos[0];
        FileReader leerArchivo;
        leerArchivo = new FileReader(filevacias);
        BufferedReader textoArchivo = new BufferedReader(leerArchivo);

        String cadena = "";
        while ((cadena= textoArchivo.readLine()) !=null) {
        }
        String arrayvacias[] = cadena.split(",");
        System.out.println(arrayvacias[0]);
        textoArchivo.close();
        leerArchivo.close();

        String fileName = argumentos[1];
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("el nombre del archivo no se encuentra");
            System.exit(4);
        }

        BufferedReader in = new BufferedReader(fileReader);
        String textLine = null;
        int contador = 0;
        while (true) {
            try {
                if (!((textLine = in.readLine())   != null))

                    break;
            } catch (IOException e) {
                System.out.println("error al leer el archivo");
                System.exit(5);
            }
            contador++;
            StringTokenizer st = new StringTokenizer(textLine);
            palabrasTotales = palabrasTotales + st.countTokens();
            for (String palabra : textLine.replace(",", "").replace(".", "").replace(";", "").replace(":", "").split(" ")) {

                palabras.put(palabra, palabras.containsKey(palabra) ? palabras.get(palabra) + 1 : 1);
            }

        }

        String[] arreglo_palabras = new String[palabras.size()];
        int[] arreglo_repeticiones = new int[palabras.size()];
        palabras.keySet().toArray(arreglo_palabras);
        for (int i = 0; i < arreglo_palabras.length; i++) {
            arreglo_repeticiones[i] = palabras.get(arreglo_palabras[i]);
        }

        ordenamientoArreglos(arreglo_palabras, arreglo_repeticiones);

        for (int i = 0; i < arreglo_repeticiones.length; i++) {
            System.out.println(arreglo_repeticiones[i] + " " + arreglo_palabras[i]);
        }
        System.out.println("Para este archivo de entrada, la palabra " + arreglo_palabras[0] + " ocurrió " + arreglo_repeticiones[0] + " veces, " + arreglo_palabras[1] + " " + arreglo_repeticiones[1] + " veces, etc.");

        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("el archivo " + fileName + " tiene " + contador + " lineas ");
        System.out.println("");
        System.out.println("el archivo tiene "+palabrasTotales+" palabras");


    }
}

