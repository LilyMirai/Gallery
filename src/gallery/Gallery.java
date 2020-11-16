package gallery;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.awt.Desktop;

/**
 * @author hoshi
 */

public class Gallery {

    static Reader fileReader = null;
    static BufferedReader bufferedReader;
    static List<Illustration> illustrations = new ArrayList<Illustration>();
    static String directory = "GalleryData/";
    static String saveData = directory+"savedata.txt"; // The name of the file to open.
    static Scanner scanner = new Scanner(System.in);
    static int menu;
    static boolean active = true;
    
    public static void main(String[] args) throws IOException {
        while(active) {
            menu();
        }

    }

    private static void menu() throws IOException {
        System.out.println(" --- Galeria --- \n");
        System.out.println("1. Ver Illustraciones");
        System.out.println("2. Añadir Illustracion");
        System.out.println("3. Editar Illustracion");
        System.out.println("4. Eliminar Illustracion");
        System.out.println("5. Salir");

        menu = Integer.parseInt(scanner.nextLine());

        switch(menu) {
            case 1:
                if(createIllustration()) {
                    System.out.println("Illustration added succesfully!");
                }
                else {
                    System.out.println("Illustration couldn't be added.");
                }

                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                System.out.println("\nGracias por usar el sistema de galeria!");
                active = false;
                break;
        }
    }

    private static boolean createIllustration() throws IOException {

        String illustName;
        do {
            System.out.println("Illustration name: ");
            illustName = scanner.nextLine();
            illustName = illustName.trim();
            if (illustName == "") {
                System.out.println("Illustration name cannot be empty!");
            }
        } while (illustName == "");

        System.out.println("Illustration's tags (separate with commas): ");
        String illustPreTags = scanner.nextLine();
        List<String> illustTags = Arrays.asList(separateCommas(illustPreTags));

        String illustDirectory = "GalleryData/" + illustName + "/";

        if (createDirectory(illustDirectory)) {
            System.out.println("Directory created succesfully!");
            System.out.println("Opening directory, please add your illustration files here!");
            openDirectory(illustDirectory);
        }

        saveTags(illustPreTags, illustDirectory);


        illustrations.add(new Illustration(illustName, illustTags, illustDirectory));
        return true;
    }

    private static void loadSaveData() {
        String line = null; // This will reference one line at a time
        int count = 0;

        try {
            FileReader fileReader = new FileReader(saveData); // FileReader reads text files in the default encoding.
            BufferedReader bufferedReader = new BufferedReader(fileReader); // Always wrap FileReader in BufferedReader.

            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                count = 0;
                for(int i = 0; i < line.length(); i++) {
                    if(line.charAt(i) == ',') count++;
                }
                String[] strParts = line.split(","); //Splits the line through commas.
            }

            bufferedReader.close(); // Always close files.
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" +saveData + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '"+ saveData + "'");
        }
    }

    private static List<String> loadTags(String illustName) throws IOException {
        String line = null;

        try {
            FileReader tagReader = new FileReader(directory + illustName + "/tags.txt");
            BufferedReader bufferedReader = new BufferedReader(tagReader);
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> result = Arrays.asList(separateCommas(line));
        return result;
    }

    private static void saveTags(String tags, String illustDirectory) throws IOException {
        File file = new File(illustDirectory + "/tags.txt");
        FileWriter tagWriter = new FileWriter(illustDirectory + "/tags.txt");
        tagWriter.write(tags);
        tagWriter.close();
    }

    private static String[] separateCommas(String line) {
        String[] result = line.split(","); //Splits the line through commas.
        return result;
    }

    private static boolean createDirectory(String illustDirectory) {
        Path path = Path.of(illustDirectory);
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private static void openDirectory(String illustDirectory) throws IOException {
        Desktop.getDesktop().open(new File(illustDirectory));
    }

}
