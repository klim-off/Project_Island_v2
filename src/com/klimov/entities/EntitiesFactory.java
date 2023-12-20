package com.klimov.entities;

import com.klimov.annotations.Property;
import com.klimov.entities.Entity;
import com.klimov.entities.EntityType;
import com.klimov.entities.animals.herbivore.Deer;
import com.klimov.entities.animals.herbivore.Horse;
import com.klimov.entities.animals.herbivore.Sheep;
import com.klimov.entities.animals.predators.Fox;
import com.klimov.entities.animals.predators.Snake;
import com.klimov.entities.animals.predators.Wolf;
import com.klimov.entities.plants.Plant;
import javassist.tools.reflect.Reflection;
import lombok.SneakyThrows;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class EntitiesFactory {

    private static final String CURRENT_PATH = "com.klimov.entities";
    Map<Class<?>, Object> entitiesMap = new HashMap<>();

    public EntitiesFactory() {
        initEntitiesMap();
    }

    @SneakyThrows
    public void initEntitiesMap() {
       //read from file "*-properties" information, add in special class properties
        Properties properties = new Properties();
        try (FileReader reader = new FileReader("src/resourсes/animal-data.properties")) {
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();

        }

        // find all classes with annotation Entity
        Set<Class<?>> allClassesFromMyPackage = findAllClassesUsingClassLoader();
        //  go through each classes in a cycle
        for (Class<?> aClass : allClassesFromMyPackage) {
            //get name of annotation
            Annotation entityAnnotation = aClass.getAnnotation(com.klimov.annotations.Entity.class);
            String entityName = ((com.klimov.annotations.Entity) entityAnnotation).className();

            Class herbivoreClass = aClass.getSuperclass();
            Class parentClass = herbivoreClass.getSuperclass();
           // get list of fields from annotation
            Field[] parentClassFields = parentClass.getDeclaredFields();
            List<String> propertiesValues = new ArrayList<>();

            Annotation propertyAnnotation = null;
            for (Field parentClassField : parentClassFields) {
                if (parentClassField.isAnnotationPresent(Property.class)) {
                    propertyAnnotation = parentClassField.getAnnotation(Property.class);
                    String propertyName = ((Property) propertyAnnotation).propertyName();
                    propertiesValues.add(entityName + "." + propertyName);
                }
            }
            var valuesToSearch = propertiesValues
                    .stream()
                    .filter(el -> el.startsWith(entityName))
                    .sorted()
                    .collect(Collectors.toList());

            Constructor constructor = aClass.getDeclaredConstructor(Double.class, Integer.class, Integer.class, Double.class,String.class);
            Double weight = Double.valueOf((String) properties.get(valuesToSearch.get(4)));
            Integer maxOnCage = Integer.parseInt((String) properties.get(valuesToSearch.get(1)));
            Integer speed = Integer.parseInt((String) properties.get(valuesToSearch.get(2)));
            Double enoughAmountOfFood = Double.valueOf((String) properties.get(valuesToSearch.get(0)));
            String unicode = String.valueOf(properties.get(valuesToSearch.get(3)));
            entitiesMap.put(aClass, constructor.newInstance(weight, maxOnCage, speed, enoughAmountOfFood,unicode));
        }
        System.out.println(entitiesMap);
    }

    @SneakyThrows
    private Set<Class<?>> findAllClassesUsingClassLoader() {
        Class<com.klimov.annotations.Entity> annotationClass = com.klimov.annotations.Entity.class;

        Reflections reflections = new Reflections(
                new ConfigurationBuilder()
                        .forPackage(CURRENT_PATH)
                        .filterInputsBy(new FilterBuilder().includePackage(CURRENT_PATH)));

        return reflections.getTypesAnnotatedWith(annotationClass);
    }

    private Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }

    public Entity createEntity(EntityType entityType) {
        return switch (entityType) {
            case WOLF ->(Wolf) entitiesMap.get(Wolf.class);
            case FOX -> new Fox();
            case SNAKE -> new Snake();

            case DEAR -> (Deer) entitiesMap.get(Deer.class);
            case HORSE -> (Horse) entitiesMap.get(Horse.class);
            case SHEEP -> (Sheep) entitiesMap.get(Sheep.class);

            case PLANT -> new Plant();
        };
    }
}
