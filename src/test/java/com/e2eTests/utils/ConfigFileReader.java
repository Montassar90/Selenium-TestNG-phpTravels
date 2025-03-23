package com.e2eTests.utils;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

// This class is responsible for reading properties from a configuration file
public class ConfigFileReader {
    
    // Stores properties loaded from the configuration file
    private Properties properties;
    
    // The path to the configuration file
    private String propertyFilePath = "src/test/resources/configs/configFile.properties";

    // Constructor: loads properties from the configuration file when the object is created
    public ConfigFileReader() {
        BufferedReader reader = null;
        try {
            // Opens the file at the specified path for reading
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                // Loads the properties from the file
                properties.load(reader);
                reader.close();  // Closes the reader after loading
            } catch (IOException e) {
                // Prints an error message if there's an issue loading the properties
                System.out.println("Exception: ," + e);
            }
        } catch (FileNotFoundException e) {
            // If the file is not found, prints an error message and throws an exception
            System.out.println("Exception: ," + e);
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        } finally {
            // Ensures the reader is closed in the event of an exception
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Exception: ," + e);
                }
            }
        }
    }

    // Retrieves the value of a property by its key (parameter name)
    public String getProperties(String prop) {
        // Gets the property value associated with the given key
        String param = properties.getProperty(prop);
        
        // If the property exists, return it; otherwise, throw an exception
        if (param != null)
            return param;
        else
            throw new RuntimeException("url not specified in the configuration file!");
    }
}
