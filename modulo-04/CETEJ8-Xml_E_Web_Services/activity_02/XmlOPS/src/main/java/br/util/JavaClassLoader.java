package br.util;

import java.net.URL;
import java.net.URLClassLoader;

public class JavaClassLoader {

    private String classe_name; //nome da classe
    private URL url; //arquivo .jar

    public JavaClassLoader(String classe_name, URL url) {
        this.classe_name = classe_name;
        this.url = url;
    }

    public Class getXMLClass() {
        try {
            URL[] classLoaderUrls = new URL[]{url};
            URLClassLoader loader = new URLClassLoader(classLoaderUrls);
            Class c1 = loader.loadClass(classe_name);
            return c1;
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
