package com.lab5_command;

public class Help {

    public static String help() {
        return "info : вывести информацию о коллекции в стандартный поток вывода (тип, дату инициализации, количество элементов и т.д.)\n" +
                "show : вывести на стандартный вывод все элементы коллекции в строковом представлении\n" +
                "insert null : добавить новый элемент с заданным ключом\n" +
                "update id : обновить значение элемента коллекции, идентификатор которого равен заданному\n" +
                "remove_key null : удалить элемент из коллекции по его ключу\n" +
                "clear : очистить коллекцию\n" +
                "save : сохранить коллекцию в файл\n" +
                "execute_script file_name : прочитать и выполнить скрипт из указанного файла, " +
                "скрипт содержит команды в том же виде, в каком их вводит пользователь в интерактивном режиме\n" +
                "exit : завершить программу клиентского приложения (без сохранения в файл)\n" +
                "remove_greater null: удалить из коллекции все элементы больше заданного, то же, что и remove_greater_key\n" +
                "remove_greater_key null : удалить из коллекции все элементы, ключ которых больше заданного\n" +
                "remove_lower_key null : удалить из коллекции все элементы, ключ которых меньше заданного\n" +
                "max_by_total_box_office : отображать любой объект из коллекции, значение поля totalBoxOffice которого является максимальным\n" +
                "filter_starts_with_name name : отображать элементы, значение поля name которых начинается с заданной подстроки\n" +
                "filter_less_than_oscars_count oscarsCount : отображать элементы, у которых значение поля oscarsCount меньше заданного\n" +
                "---";
    }
}
