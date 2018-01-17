package aveh.database;

import com.sforce.soap.partner.sobject.SObject;

public class DataLoader {
    public static void loadData(String sObjectApiName, String fromCsvFile) {
        SObject[] sObjectsRecordList = SObjectFactory.buildSObjects(sObjectApiName, fromCsvFile);

        if (sObjectsRecordList != null && sObjectsRecordList.length > 0) {
            DatabaseOperation.insert(sObjectsRecordList);
        }
    }
}
