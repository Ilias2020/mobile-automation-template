# Mobile Automation Template — Project Context (Android-first)

## 0) Статус на сегодня
- Template: рабочий skeleton
- Android: 1 эмулятор (Pixel_7_API_34)
- App under test: Mseller (kg.mseller.app)
- Текущий стабильный тест: LoginNegativeTest (негативный логин)
- Ключевой фикс: для password нужен фокус → click() перед sendKeys()

Дата обновления: 2026-03-03

---

## 1) Цель
Корпоративный mobile automation template (не учебный).
Шаблон будут копировать другие команды и адаптировать под свои приложения.

---

## 2) Технологический стек
- Java 17
- Gradle 8.14 (Kotlin DSL)
- TestNG
- Allure
- Appium (UiAutomator2)
- Android-first (пока без iOS / параллельности / CI, но архитектура должна это пережить)

---

## 3) Принципы разработки (обязательные)
- Работаем строго пошагово: 1 шаг → проверка → следующий шаг
- Минимальные изменения, без лишних файлов
- Не ломаем существующую архитектуру
- Любое изменение объяснять “зачем”
- XPath допускается временно только если нет стабильных id
- Предпочтение локаторам: accessibilityId (content-desc) > resource-id > остальное
- Не делать ожидания на элементы, которые должны исчезнуть (иначе ловим таймауты)

---

## 4) Структура проекта
src/main/java — framework слой
- core
    - DriverManager (ThreadLocal driver)
    - BaseTest (создание драйвера только при -DrunMobile=true)
- pages
    - BasePage (driver + wait + базовые ожидания)
    - AuthChoicePage
    - LoginPage

src/test/java — тесты
- com.company.mobile.tests
    - LoginNegativeTest

Конфигурация:
- config.properties

TODO: уточнить package root проекта (com.company.mobile / другое)

---

## 5) Конфиг и запуск
### 5.1 runMobile флаг
Драйвер создаётся только при:
-DrunMobile=true

В gradle:
systemProperty("runMobile", System.getProperty("runMobile", "false"))

### 5.2 Команда запуска одного теста
.\gradlew clean test -DrunMobile=true --tests "com.company.mobile.tests.LoginNegativeTest"

TODO: уточнить точный package теста в команде

---

## 6) Что уже реализовано и считается стабильным
### 6.1 Appium / Emulator
- UiAutomator2
- Android emulator
- autoGrantPermissions = true
- Driver создаётся условно (по runMobile)

### 6.2 AuthChoicePage
- Кнопка входа: content-desc="Login"
- Переход на LoginPage работает

### 6.3 LoginPage (важное)
Якорь экрана:
- content-desc="Authorization"

Password field:
- локатор через XPath (временно)
- поле имеет password=true

Критический паттерн ввода пароля:
- нужен фокус:
    - click()
    - clear()
    - sendKeys()

Без click() пароль визуально не вводился.

---

## 7) Негативный логин (LoginNegativeTest)
Сценарий:
- ввести телефон
- ввести пароль
- triggerValidation()
- проверить isSubmitEnabled()
- submit()
- проверить модалку ошибки

Модалка:
- ловим по кнопке content-desc="OK"
- это самый стабильный якорь (не по тексту)

Логи (временные):
- After sendKeys text=XXXXXXXX
- okVisible=true
- errorVisible=true

---

## 8) Что было проблемой (и как починили)
1) Пароль не вводился
- Причина: не было фокуса
- Фикс: click() перед sendKeys()

2) Модалка не находилась
- Причины:
    - битая кодировка строки
    - поиск по русскому тексту
    - лишний isOpened() после submit() с ожиданием 10 сек
- Фиксы:
    - модалка ловится по OK
    - убрали isOpened() после submit()

---

## 9) Ограничения / Грабли
- waitVisible использует WebDriverWait (таймаут из config explicit.wait)
- Не вызывать wait на то, что должно исчезнуть
- Password поле может не возвращать text корректно → не проверять пароль через getText()
- Валидность формы лучше проверять по состоянию кнопки (enabled/disabled)

---

## 10) Следующие шаги (roadmap — коротко)
1) Вынести единый паттерн ввода текста в BasePage (click + clear + sendKeys)
2) Убрать временные диагностические принты
3) Держать LoginPage минимальным и чистым
4) Зафиксировать best practices локаторов (accessibilityId-first)

---

## 11) Правило обновления контекста
После каждого значимого шага обновлять:
- раздел 0 (статус)
- раздел 6/7 (что стабильно)
- раздел 8 (проблемы и фиксы)
- roadmap (что дальше)

## Текущая реализация тестов 04.03.26

На данный момент в проекте реализованы первые UI-тесты авторизации.

### Негативный тест логина

Тест проверяет, что пользователь **не может войти** с неверными учетными данными.

Поток теста:

AuthChoicePage  
→ LoginPage  
→ ввод неверного телефона и пароля  
→ нажатие кнопки Login  
→ проверка появления модального окна ошибки

Тест:  
LoginNegativeTest

---

### Позитивный тест логина

Тест проверяет, что пользователь **может успешно войти** в систему.

Поток теста:

AuthChoicePage  
→ LoginPage  
→ ввод корректного телефона и пароля  
→ нажатие кнопки Login  
→ открывается экран каталога

Для проверки используется якорный элемент страницы:
//android.widget.TextView[@text="Каталог"]

Тест:  
LoginPositiveTest

## Особенности тестирования

В процессе разработки была обнаружена особенность поведения Appium:

Метод

hideKeyboard()

на Android может вызывать навигацию назад (Back), что приводит к возврату на предыдущий экран.

Поэтому данный метод был удалён из triggerValidation(), чтобы избежать нестабильности тестов.