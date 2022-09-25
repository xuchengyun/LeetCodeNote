package LCQuestions.Solutions._2400_2499._2408_DesignSql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _2408_DesignSql {
    class SQL {

        private Map<String, List<List<String>>> db = new HashMap<>();

        public SQL(List<String> names, List<Integer> columns) {}

        public void insertRow(String name, List<String> row) {
            db.computeIfAbsent(name, l -> new ArrayList<>()).add(row);
        }

        public void deleteRow(String name, int rowId) {}

        public String selectCell(String name, int rowId, int columnId) {
            return db.get(name).get(rowId - 1).get(columnId - 1);
        }
    }
}
