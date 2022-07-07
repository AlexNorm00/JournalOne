# Journal
## Введение
This application was developed by Kel Alexey. A journal is a program with which you can keep a daily journal of students, mark their progress, and also control attendance.

## Stack
In this project we use:
*Intellij idea
*Java 8
*Maven
*Spring MVC
*Data Base: postgresql
*myAdmin 4
*HTML5
*CSS

## Запустите приложение Journal через "Intellij idea" 
1. Настройте соединение с базой данных.
``` подключение бд
    private static final String url = "jdbc:postgresql://localhost:5432/JournalDB";
    private static final String user = "postgres";
    private static final String password = "1234";
```
2. Нажмите "Запустить приложение" и перейдите по ссылке.
``` ссылка на старт
http://localhost:8081
```
## Идентификация пользователя

Приступаем к началу работы. Для начала нужно ввести логин и пароль

![password] (https://github.com/AlexNorm00/JournalOne/blob/master/Screens/Password.PNG)

1.Введите "Логин" и "Пароль"
2.Нажмите кнопку "Login"

## Главное меню

![Menu](https://github.com/AlexNorm00/JournalOne/blob/master/Screens/Journal.PNG)

1. Кнопка "Journal" для входа в журнал
2. Кнопка "Person" для ввода новых пользователей
3. Кнопка "Setting" для входа в настройки 

## Настройки 

Перед добавлением новых пользователей: учащихся, преподавателей и т.д. необходимо
добавить, в случаи отсутсвия, статус пользователя и наименования предметов. При не
верном вводе или в случаи не актуальности, можно удалить. Кроме пользоватея "Администратор".

![Setting](https://github.com/AlexNorm00/JournalOne/blob/master/Screens/Setting.PNG)

1. Введите в поле "id" уникальный номер
2. Введите в поле "name" наименование предмета/статуса
3. Нажмите "add" если хотите добавить, "delete" сли необходимо удалить

## Человек

Для добавления пользователей необходимо ввести данные в соответсвующие поля

![Person](https://github.com/AlexNorm00/JournalOne/blob/master/Screens/addPerson.PNG)

1. "Nom group" - номер группы учащегося, не обязательное поле для преподавателей или пользователей другого типа
2. "Surname" - фамилия пользователя
3. "Name" - имя пользователя
4. "Middle - name" отчество пользователя
5. "Date birthday" - дата рождения пользователя
6. "Classification" - статус пользователя (Admin, student и т.д.) выпадающий список, заполненный ранее
7. "Login" - логин пользователя (должен быть уникальным, иначе программа не сохранит пользователя)
8. "Password" - пароль пользователя
9. "Save" - кнопка сохранить

## Журнал

Рабочая область состоит из 2х разделов - панель упавления, а так же таблицы учащихся привязанных к номеру группы.

![Journal](https://github.com/AlexNorm00/JournalOne/blob/master/Screens/Journal.PNG)

1. "No group" - номер группы учащихся, числовое поле от 0 и выше, обязательное к заполнению
2. "Date" - дата журнала, по умолчанию стоит текущая дата
3. "Subject" - выпадающий список с наименованиями учебных предметов
4. "Search" - поиск учащихся по 3-м  полям, номер группы, дата, предмет - выводит всех учащихся группы учившихся
    в указанный день, по определённому предмету (последнее поле не обязательное);
    Во время поиска, изменения в журнале запрещены, только при начале урока.
5. "Create Lesson" - кнопка для создания листа журнала, на основании группы, и предмета, с датой указанной в поле "Date"
    выводит всех учеников определённой группы, начинает урок с текущей датой.
![createJournal](https://github.com/AlexNorm00/JournalOne/blob/master/Screens/CreateJournalList.PNG)
6. "Close Lesson" - закрывает и созраняет данные в журнал
7. Поле "Enter lesson topic" - Тема занятия, вводиться во время занятия
8. Строка выше - информртивное поле указывающая дату и номер группы.






