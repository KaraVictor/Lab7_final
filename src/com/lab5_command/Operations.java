package com.lab5_command;

import com.lab5_data.Collection;
import com.utility.ServerMessage;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public class Operations {

    ExecuteScript executeScript = new ExecuteScript();
    Show show = new Show();
    RemoveKey removeKey = new RemoveKey();
    Clear clear = new Clear();
    RemoveGreater removeGreater = new RemoveGreater();
    RemoveGreaterKey removeGreaterKey = new RemoveGreaterKey();
    RemoveLowerKey removeLowerKey = new RemoveLowerKey();
    MaxByTotalBoxOffice maxByTotalBoxOffice = new MaxByTotalBoxOffice();
    FilterStartsWithName filterStartsWithName = new FilterStartsWithName();
    FilterLessThanOscarsCount filterLessThanOscarsCount = new FilterLessThanOscarsCount();

    public void operations(Connection connection, String[] command, Operations operations, Collection collection, ServerMessage serverMessage, File serverFile, String login) throws SQLException {
        if (command[0].equals("help") & command.length == 1){
            System.out.println("help");
            serverMessage.plusMessage(Help.help());
        }
        else if (command[0].equals("execute_script") & command.length == 2) {
            executeScript.executeScript(connection, command[1], operations, collection, serverMessage, serverFile, login);
        }
        else if (command[0].equals("info") & command.length == 1) {
            serverMessage.plusMessage(Info.info(collection));
        }
        else if (command[0].equals("show") & command.length == 1) {
            show.show(collection, serverMessage);
        }
        else if (command[0].equals("remove_key") & command.length == 2) {
            removeKey.removeKey(connection, command[1], collection, serverMessage, login);
        }
        else if (command[0].equals("clear") & command.length == 1) {
            clear.clear(connection, collection, login);
            serverMessage.plusMessage("коллекция очищена");
        }
        else if (command[0].equals("remove_greater") & command.length == 2) {
            removeGreater.removeGreater(connection, collection, command[1], serverMessage, login);
        }
        else if (command[0].equals("remove_greater_key") & command.length == 2) {
            removeGreaterKey.removeGreaterKey(connection, command[1], collection, serverMessage, login);
        }
        else if (command[0].equals("remove_lower_key") & command.length == 2) {
            removeLowerKey.removeLowerKey(connection, command[1], collection, serverMessage, login);
        }
        else if (command[0].equals("max_by_total_box_office") & command.length == 1) {
            maxByTotalBoxOffice.maxByTotalBoxOffice(collection, serverMessage);
        }
        else if (command[0].equals("filter_starts_with_name") & command.length == 2) {
            filterStartsWithName.filterStartsWithName(collection, command[1], serverMessage);
        }
        else if (command[0].equals("filter_less_than_oscars_count") & command.length == 2) {
            try {
                int id = Integer.parseInt(command[1]);
                filterLessThanOscarsCount.filterLessThanOscarsCount(collection, id, serverMessage);
            }
            catch (NumberFormatException ignored) {
                serverMessage.plusMessage("вы должны ввести значение типа int");
            }
        }
        else {
            serverMessage.plusMessage("вы ввели неверную команду");
        }
    }
}
