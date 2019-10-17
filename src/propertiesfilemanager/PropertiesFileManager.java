/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertiesfilemanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author CREEDurango
 */
public class PropertiesFileManager {
    private final File archivo;
    private final Properties configuracion = new Properties();

    public PropertiesFileManager(String fileName) {
        this.archivo = new File(fileName + ".properties");
        if (!archivo.isFile()) {
            try {
                archivo.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(PropertiesFileManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void Guardar(String comentario){
        FileOutputStream fileOutput = null;
        try {
            fileOutput = new FileOutputStream(archivo);
            configuracion.store(fileOutput, comentario);
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }finally{
            try {
                if (fileOutput != null) {
                    fileOutput.close();
                }
            } catch (IOException ex) {
            }
        }
    }
    
    public boolean cargar(){
        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(archivo);
            configuracion.load(fileInput);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PropertiesFileManager.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (IOException ex) {
            Logger.getLogger(PropertiesFileManager.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if (fileInput != null) {
                    fileInput.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "No hay configuracion a cargar", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return true;
    }
    
    public void clear(){
        configuracion.clear();
    }
    
    public List<Object> obtenerLista(){
        return new ArrayList<>(configuracion.keySet());
    }

    public String obtenerPropiedad(String propiedad, String defaultValue) {
        return configuracion.getProperty(propiedad, defaultValue);
    }

    public void definirConfiguracion(String parametro, String valor) {
        configuracion.setProperty(parametro, valor);
    }

    public List<String> obtenerPropiedades(String propiedad, String defaultValue) {
        return Arrays.asList(configuracion.getProperty(propiedad, defaultValue).split(","));
    }

    public void definirConfiguraciones(String parametro, List<String> valores) {
        configuracion.setProperty(parametro, String.join(",", valores));
    }
}
