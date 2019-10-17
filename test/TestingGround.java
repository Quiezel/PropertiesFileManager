
import propertiesfilemanager.PropertiesFileManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author CREEDurango
 */
public class TestingGround {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        PropertiesFileManager pfm = new PropertiesFileManager("prueba");
        pfm.cargar();
        pfm.obtenerLista().forEach(System.out::println);
        pfm.definirConfiguracion("Prueba2", "intentando");
        pfm.Guardar("");
    }
    
}
