package com.company.mobile.config; // Отдельный пакет config — чтобы не мешать core

import java.io.InputStream; // Для чтения файла из resources
import java.util.Properties; // Для хранения key/value

public final class Config { // Утилитарный класс

    private static final Properties PROPS = new Properties(); // Загружаем один раз при старте JVM

    static { // static-block выполнится один раз при загрузке класса
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            // Достаём config.properties из src/main/resources
            if (in == null) { // Если файла нет — это критично
                throw new RuntimeException("config.properties not found in resources");
            }
            PROPS.load(in); // Загружаем свойства
        } catch (Exception e) { // Любая ошибка загрузки — останавливаем запуск
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    private Config() { // Запрещаем создавать экземпляр
    }

    public static String get(String key) { // Базовый getter по ключу
        // Сначала берём System property (-Dkey=value), потом из файла
        return System.getProperty(key, PROPS.getProperty(key));
    }

    public static String appPath() { // Путь к apk
        return get("app.path");
    }

    public static String appPackage() { // Ожидаемый package
        return get("app.package");
    }

    public static String appiumUrl() { // URL Appium
        return get("appium.url");
    }

    public static String deviceName() { // Имя девайса
        return get("device.name");
    }
}